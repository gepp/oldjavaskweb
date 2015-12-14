package jsdt.tools;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.bouncycastle.util.encoders.Hex;

public class ParseMX {

	private static int blocksize = 2048;
	private static final int PROJ_DATA_LENGTH = 33;

	public static HashMap parseMX(File mxfile) {
		long start = System.currentTimeMillis();
		HashMap map = new HashMap();
		int first = 0;// 2011-06-23gepp
		int drbz = 0;// 2011-06-21gepp
		String mouth = "";
		ArrayList<HashMap<String, Object>> invoices = new ArrayList<HashMap<String, Object>>();
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(
					mxfile));
			int bufferis_length = bufferedInputStream.available();
			System.out.println(bufferis_length);
			int tmpdata = 0;
			StringBuffer sb = new StringBuffer();

			while ((tmpdata = bufferedInputStream.read()) != -1) {
				sb.append(Util.byte2hex((byte) tmpdata));
			}

			String data = sb.toString();

			LinkedList<String> mxlist = splitMxData(data);
			// System.out.println(mxlist);
			System.out.println(mxlist.size());
			Iterator<String> it = mxlist.iterator();
			HashMap<String, Object> invoice = null;
			while (it.hasNext()) {

				invoice = new HashMap<String, Object>();
				String subdata = (String) it.next();
				if ("AA".equals(subdata.substring(0, 2))) {
					System.out.println(subdata.length());

					int subdata_length = Integer.valueOf(
							subdata.substring(2, 6), 16);
					if (subdata_length != 0) {
						System.out.println("明细标志：" + subdata.substring(0, 2));
						System.out.println("数据长度：" + subdata_length);
						String sub_data = subdata.substring(6);

						String kprq = sub_data.substring(0, 8);
						System.out.println(kprq);
						java.text.DecimalFormat format = new java.text.DecimalFormat(
								"00");
						int yy = Integer.valueOf(kprq, 16);
						System.out.println(yy);
						// System.out.println(format.format(yy/1048576));
						int mm = yy % 1048576;
						// System.out.println(mm/65536);
						int dd = mm % 65536;
						// System.out.println(dd/2048);
						int hh = dd % 2048;
						String date = "20"
								+ String.valueOf(format.format(yy / 1048576))
								+ String.valueOf(format.format(mm / 65536))
								+ String.valueOf(format.format(dd / 2048))
								+ String.valueOf(format.format(hh / 64))
								+ String.valueOf(format.format(hh % 64));
						System.out.println("开票日期:" + date);// TODO
						invoice.put("kprq", date);
						mouth = date.substring(4, 6);
						String type = sub_data.substring(8, 10);
						System.out.println("开票类型:" + type);
						invoice.put("kplx", type);
						String invoiceno = String.valueOf(Long.parseLong(
								sub_data.substring(10, 18), 16));
						System.out.println("发票号: " + invoiceno);
						invoice.put("fphm", invoiceno);
						String amount = String.valueOf(Long.parseLong(
								sub_data.substring(18, 26), 16));
						System.out.println("合计总金额:" + amount);
						invoice.put("hjzje", amount);
						java.text.DecimalFormat format1 = new java.text.DecimalFormat(
								"0000000000");
						String taxcontrollid = sub_data.substring(26, 42);
						System.out.println("taxcontrollid = " + taxcontrollid);
						String mac = format1.format(Long.valueOf(
								taxcontrollid.substring(0, 8), 16));
						String num = format1.format(Long.valueOf(
								taxcontrollid.substring(8), 16));
						System.out.println("十进制税控码长度:"
								+ (mac.length() + num.length()));
						String taxid = mac + num;
						System.out.println("税控码:" + taxid);
						invoice.put("skm", taxid);

						String old_invoiceno = String.valueOf(Long.valueOf(
								sub_data.substring(42, 50), 16));
						System.out.println("原发票号:" + old_invoiceno);
						invoice.put("yfphm", old_invoiceno);

						String invoicecode = Util.removeZero(sub_data.substring(50, 70));
						System.out.println("发票代码:" + invoicecode);
						invoice.put("fpdm", invoicecode);

						String payer = new String(Util.HexString2Bytes(sub_data
								.substring(70, 150))).trim();
						System.out
								.println((sub_data.substring(70, 150)).trim());
						System.out.println("付款单位(个人):" + payer);
						invoice.put("fkdw", payer);

						String reciver = new String(
								Util.HexString2Bytes(sub_data.substring(150,
										170))).trim();
						System.out.println("收款员:" + reciver);
						invoice.put("sky", reciver);

						int projects = Integer.valueOf(
								sub_data.substring(170, 172), 16);
						System.out.println("项目数:" + projects);
						invoice.put("xms", String.valueOf(projects));
						
						
						//发票ID
						String sql = "select SID from SKQ_FPKJ where FPDM='"+invoicecode+"' and FPHM="+Integer.parseInt(invoiceno);
						String fieldname = "SID";
						int parentid = Query.getFieldInt(sql, fieldname);
						invoice.put("parentid", String.valueOf(parentid));
						
						if (projects != 0) {
							int prjdata_length = PROJ_DATA_LENGTH * projects
									* 2;
							// System.out.println(prjdata_length);
							String projdata = sub_data.substring(172,
									172 + prjdata_length);
							System.out.println(projdata);
							int startindex = 0;
							ArrayList<HashMap<String, String>> projectlist = new ArrayList<HashMap<String, String>>();
							HashMap<String, String> prj_detail = null;
							for (int i = 1; i <= projects; i++) {
								prj_detail = new HashMap<String, String>();
								System.out.println("==========项目 " + i
										+ "==========");
								String tmp = projdata.substring(startindex,
										startindex + PROJ_DATA_LENGTH * 2);
								System.out.println("项目 "
										+ i
										+ "名称："
										+ new String(Util.HexString2Bytes(tmp
												.substring(0, 40))));
								prj_detail.put(
										"prjname",
										new String(Util.HexString2Bytes(tmp
												.substring(0, 40))));
								String smbm = "";
								String prjname = prj_detail.get("prjname");
								sql = "select SMBM from SKQ_PMSZ where SMMC='"
										+ prjname
										+ "' or SMJC='"
										+ prjname
										+ "'";
								fieldname = "SMBM";
								System.out.println(sql);
								smbm = Query.getField(sql, fieldname);
								if (smbm == null) {
									smbm = "10000006";
								}
								int decimal_digits = Integer.valueOf(
										tmp.substring(40, 42), 16);
								System.out.println("项目 " + i + "数量小数位数："
										+ decimal_digits);
								prj_detail.put(
										"xsws",
										String.valueOf(Integer.parseInt(
												tmp.substring(40, 42), 16)));
								System.out.println(tmp.substring(42, 48));
								switch (decimal_digits) {
								case 0:
									System.out.println("项目 "
											+ i
											+ "数量："
											+ Integer.valueOf(
													tmp.substring(42, 48), 16));
									// System.out.println("数量原来：++"+tmp.substring(42,
									// 48));

									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.parseInt(tmp.substring(42,
															48), 16))));
									break;
								case 1:
									System.out.println("项目 "
											+ i
											+ "数量："
											+ Integer.valueOf(
													tmp.substring(42, 48), 16)
											/ 10);
									System.out.println("数量原来：++"
											+ tmp.substring(42, 48));
									prj_detail
											.put("sl",
													String.valueOf(new BigDecimal(
															Integer.valueOf(
																	tmp.substring(
																			42,
																			48),
																	16))
															.divide(new BigDecimal(
																	"10"))));
									break;
								case 2:
									System.out.println("项目 "
											+ i
											+ "数量："
											+ Integer.valueOf(
													tmp.substring(42, 48), 16)
											/ 100);
									System.out.println("数量原来：++"
											+ tmp.substring(42, 48));
									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.valueOf(tmp.substring(42,
															48), 16))
													.divide(new BigDecimal(
															"100"))));
									break;
								case 3:
									System.out.println("项目 "
											+ i
											+ "数量："
											+ Integer.valueOf(
													tmp.substring(42, 48), 16)
											/ 1000);
									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.valueOf(tmp.substring(42,
															48), 16))
													.divide(new BigDecimal(
															"1000"))));
									break;
								case 4:
									System.out.println("项目 "
											+ i
											+ "数量："
											+ Integer.valueOf(
													tmp.substring(42, 48), 16)
											/ 10000);
									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.valueOf(tmp.substring(42,
															48), 16))
													.divide(new BigDecimal(
															"10000"))));
									break;
								}

								System.out.println("项目 "
										+ i
										+ "单价："
										+ Double.valueOf(
												Integer.valueOf(
														tmp.substring(48, 56),
														16) / 100)
												.doubleValue());
								prj_detail.put(
										"dj",
										String.valueOf(Integer.parseInt(
												tmp.substring(48, 56), 16)));
								System.out.println("项目 "
										+ i
										+ "金额："
										+ Double.valueOf(
												Integer.valueOf(
														tmp.substring(56, 64),
														16) / 100)
												.doubleValue());
								prj_detail.put(
										"je",
										String.valueOf(Integer.parseInt(
												tmp.substring(56, 64), 16)));
								System.out.println("项目 "
										+ i
										+ "税种税目索引号1："
										+ Integer.valueOf(
												tmp.substring(64, 66), 16));
								System.out.println("明细列表："+prj_detail);
								prj_detail.put(
										"szsmsy",
										String.valueOf(Integer.parseInt(
												tmp.substring(64, 66), 16)));
								prj_detail.put("szsmsy", smbm);
								 
								projectlist.add(prj_detail);
								startindex = startindex + PROJ_DATA_LENGTH * 2;
							}
							invoice.put("prjlist", projectlist);
						}
						/**
						 * 2011-06-24 每次导入只判断第一张发票明细是否重复 String sqlstr =
						 * "select SID from SKQ_FPKJ where FPDM='" + invoicecode
						 * + "' and FPHM=" + Integer.parseInt(invoiceno); String
						 * fieldname1 = "SID"; int sid =
						 * Query.getFieldInt(sqlstr, fieldname1); if(sid>0){
						 * drbz=1; break; } if (sid <= 0) {
						 * invoices.add(invoice); }
						 **/
						if (first == 0) {
							String sqlstr = "select SID from SKQ_FPKJ where FPDM='"
									+ invoicecode
									+ "' and FPHM="
									+ Integer.parseInt(invoiceno)+" and STATUS=1";
							String fieldname1 = "SID";
							int sid = Query.getFieldInt(sqlstr, fieldname1);
							if (sid > 0) {
								drbz = 1;
								break;
							}
							if (sid <= 0) {
								// invoices.add(invoice);
								first++;
							}
						}
						System.out.println("=============" + first);
						if (first != 0) {
							invoices.add(invoice);
						}

					}
					System.out.println("明细文件大小：" + mxlist.size());
				}

				System.out.println("first:==" + first);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file " + mxfile);
			System.exit(0);
		} catch (EOFException e) {
			System.out.println("End of the file " + mxfile + " reached.");
		} catch (IOException e) {
			System.out.println("Error reading the file " + mxfile);
			System.exit(0);
		} finally {
			if (bufferedInputStream != null) {
				try {
					map.put("invoices", invoices);
					map.put("drbz", Integer.valueOf(drbz));
					map.put("mouth", mouth);
					System.out.println("发票导入：" + invoices.size());
					bufferedInputStream.close();
					return map;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("invoices", invoices);
					map.put("drbz", Integer.valueOf(drbz));
					map.put("mouth", mouth);
					System.out.println("发票导入：" + invoices.size());
					System.out.println(e.getMessage());
				}
			}

		}
		map.put("drbz", Integer.valueOf(drbz));
		map.put("invoices", invoices);
		map.put("mouth", mouth);
		System.out
				.println("共耗时：" + (System.currentTimeMillis() - start) / 1000);
		return map;
	}

	public static int insertInvoiceDetail(ArrayList al, String machineno)
	// public static int insertInvoiceDetail()
	{
		// ArrayList al = parseMX("");
		// String propfile_path =
		// Thread.currentThread().getContextClassLoader().getResource("").getPath();
		// SYSCOMMON.dbpool = new DBConnection("DT",propfile_path);
		String sql1 = "select NSRWJBM from SKQ_JQXX where JQBH='" + machineno
				+ "' and STATUS=1";
		String fieldname = "NSRWJBM";
		String nsrwjbm = Query.getField(sql1, fieldname);

		int result = 1;
		if (al == null || al.isEmpty()) {
			return result;
		}
		Connection conn = null;
		Connection conn1 = null;
		Connection conn2 = null;
		try {
			conn = DBConnection.getConnection();
			conn1 = DBConnection.getConnection();
			conn2 = DBConnection.getConnection();
			//String sqlmx = "insert into SKQ_FPKJ (JQBH,KPRQ,KPLX,FPDM,FPHM,HJZJE,YFPHM,FKDW,SKY,XMS,SKM,NSRWJBM,STATUS,SCSJ,QRBZ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sqlmx = "update SKQ_FPKJ set KPRQ=?,KPLX=?,HJZJE=?,YFPHM=?,FKDW=?,SKY=?,XMS=?,SKM=?,STATUS=?,SCSJ=? where FPDM=? and FPHM=?";
			String sqlproj = "insert into SKQ_FPKJXM (XMMC,XSWS,SL,DJ,JE,SMBM,FPDM,FPHM,PARENTID) values (?,?,?,?,?,?,?,?,?)";
			String sqlUpdate="update SKQ_FPKJXM SET PARENTID=(SELECT SID FROM SKQ_FPKJ WHERE FPDM=? and FPHM=?)";
			conn.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement(sqlmx);
			PreparedStatement ps2 = conn1.prepareStatement(sqlproj);
			PreparedStatement ps3 = conn2.prepareStatement(sqlUpdate);
			// int count = 1;
			Iterator it = al.iterator();
			HashMap hm = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (it.hasNext()) {
				hm = (HashMap) it.next();
//				ps1.setString(1, machineno);
//				String kprq = (String) hm.get("kprq");
//				ps1.setString(2, Util.tobzrqSf(kprq));
//				ps1.setInt(3, Integer.parseInt((String) hm.get("kplx")));
//				String fpdm = (String) hm.get("fpdm");
//				ps1.setString(4, fpdm);
//				String fphm = (String) hm.get("fphm");
//				
//					System.out.println("发票号码：=========== " + fphm);
//				ps1.setInt(5, Integer.parseInt(fphm));
//				ps1.setDouble(6,
//						Double.parseDouble((String) hm.get("hjzje")) / 100);
//				ps1.setInt(7, Integer.parseInt((String) hm.get("yfphm")));
//				ps1.setString(8, (String) hm.get("fkdw"));
//				ps1.setString(9, (String) hm.get("sky"));
//				ps1.setInt(10, Integer.parseInt((String) hm.get("xms")));
//				ps1.setString(11, (String) hm.get("skm"));
//				ps1.setString(12, nsrwjbm);
//				ps1.setInt(13, 1);
//				ps1.setString(14, sdf.format(new Date()));
				//ps1.setString(15, "Y");
				String kprq = (String) hm.get("kprq");
				ps1.setString(1, Util.tobzrqSf(kprq));
				ps1.setInt(2, Integer.parseInt((String) hm.get("kplx")));
				ps1.setDouble(3,Double.parseDouble((String) hm.get("hjzje")) / 100);
				ps1.setInt(4, Integer.parseInt((String) hm.get("yfphm")));
				ps1.setString(5, (String) hm.get("fkdw"));
				ps1.setString(6, (String) hm.get("sky"));
				ps1.setInt(7, Integer.parseInt((String) hm.get("xms")));
				ps1.setString(8, (String) hm.get("skm"));
				ps1.setInt(9, 1);
				ps1.setString(10, sdf.format(new Date()));
				String fpdm = (String) hm.get("fpdm");
				System.out.println("fpdm=="+fpdm);
				ps1.setString(11, fpdm);
				String fphm = (String) hm.get("fphm");
				System.out.println("fphm=="+fphm);
				ps1.setInt(12, Integer.parseInt(fphm));
				ps1.addBatch();
				int parentid = Integer.parseInt((String) hm.get("parentid"));
				ArrayList prj = (ArrayList) hm.get("prjlist");
				if (prj != null) {
					Iterator it1 = prj.iterator();
					while (it1.hasNext()) {
						HashMap project = (HashMap) it1.next();
						ps2.setString(1, (String) project.get("prjname"));
						ps2.setInt(2,
								Integer.parseInt((String) project.get("xsws")));
						ps2.setDouble(3,
								Double.parseDouble((String) project.get("sl")));
						ps2.setDouble(
								4,
								Double.parseDouble((String) project.get("dj")) / 100);
						ps2.setDouble(
								5,
								Double.parseDouble((String) project.get("je")) / 100);
						ps2.setString(6, (String) project.get("szsmsy"));
						ps2.setString(7, fpdm);
						ps2.setInt(8, Integer.parseInt(fphm));
						ps2.setInt(9, parentid);
						ps2.addBatch();
						
						ps3.setString(1, fpdm);
						ps3.setInt(2, Integer.parseInt(fphm));
						ps3.addBatch();
						
					}
					
				}
			}
			int[] res1 = ps1.executeBatch();
			conn.commit();
			for (int i1 = 0; i1 < res1.length; i1++) {
				if (res1[i1] == PreparedStatement.EXECUTE_FAILED
						|| res1[i1] == PreparedStatement.SUCCESS_NO_INFO) {
					conn.rollback();
					ps1.close();
					result = -1;
				} else {
					int[] res2 = ps2.executeBatch();
					conn1.commit();
					ps3.executeBatch();
					conn2.commit();
					for (int i2 = 0; i2 < res2.length; i2++) {
						if (res2[i2] == PreparedStatement.EXECUTE_FAILED
								|| res2[i2] == PreparedStatement.SUCCESS_NO_INFO) {
							conn1.rollback();
							ps2.close();
							result = -1;
						}
						break;
					}
				}
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (conn1 != null) {
					conn1.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private static void parseMYSY() {
		try {
			// FileInputStream fis = new FileInputStream(new
			// File("c:\\MXSY.BIN"));
			FileInputStream fis = new FileInputStream(new File(
					"F:/MyProjects/Sinopec/SinopeMX/23800//MXSY.BIN"));
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					fis));
			byte[] arr = new byte[dis.available()];
			dis.read(arr, 0, dis.available());
			// System.out.println(Util.byte2hex(arr));
			String data = Util.byte2hex(arr);

			System.out.println(data.substring(0, 16));
			System.out.println(data.substring(16, 24));
			System.out.println(data.substring(24, 32));
			System.out.println(Integer.valueOf(data.substring(32, 36), 16)
					.toString());
			System.out.println(new String(Hex.decode(data.substring(36, 52))));
			System.out.println(Integer.valueOf(data.substring(52, 56), 16)
					.toString());
			System.out.println(Integer.valueOf(data.substring(56, 60), 16)
					.toString());
			System.out.println(data.substring(60));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String querySMBM(String smsy) {
		Connection conn = null;
		String smbm = null;
		try {
			conn = DBConnection.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select SMBM from SKQ_PMSZ where SMSY="
							+ smsy);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				smbm = rs.getString("SMBM");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return smbm;

	}

	private static LinkedList<String> splitMxData(String data) {
		LinkedList<String> al = new LinkedList<String>();
		int data_length = data.length();
		System.out.println("data_length = " + data_length);
		int beginIndex = 0;
		int endIndex = 0;

		while (endIndex != data_length) {
			int mx_length = Integer.parseInt(
					data.substring(beginIndex + 2, beginIndex + 6), 16) * 2 + 8;
			// System.out.println("mx_length = "+mx_length);
			endIndex += mx_length;
			String str = "";
			try {
				str = data.substring(beginIndex, endIndex);
			} catch (Exception ex) {
				str = "";
				al.removeLast();
				System.out.println(al);
				return al;
			}
			al.add(str);
			beginIndex = endIndex;

			// System.out.println("beginIndex = "+beginIndex+"|endIndex = "+endIndex);
		}
		System.out.println(al);

		return al;
	}

	public static void main(String[] args) {
		// parseMYSY();
//		String projdata="3023B2F1D3CD202020202020202020202020202002001AF1000002D50000C35003";
//		
//
//		String tmp = projdata.substring(0,
//				0 + PROJ_DATA_LENGTH * 2);
//		System.out.println("项目 "
//				+ 1
//				+ "名称："
//				+ new String(Util.HexString2Bytes(tmp
//						.substring(0, 40))));
//		int decimal_digits = Integer.valueOf(
//				tmp.substring(40, 42), 16);
//		System.out.println("项目 " + 1 + "数量小数位数："
//				+ decimal_digits);
//		System.out.println("项目 "
//				+ 1
//				+ "数量："
//				+ Integer.valueOf(
//						tmp.substring(42, 48), 16)
//				/ 100);
//		System.out.println("数量原来：++"
//				+ tmp.substring(42, 48));
//		System.out.println("项目 "
//				+ 1
//				+ "单价："
//				+ Double.valueOf(
//						Integer.valueOf(
//								tmp.substring(48, 56),
//								16) / 100)
//						.doubleValue());
//		System.out.println("项目 "
//				+ 1
//				+ "税种税目索引号："
//				+ Integer.valueOf(
//						tmp.substring(64, 66), 16));
	}
}
