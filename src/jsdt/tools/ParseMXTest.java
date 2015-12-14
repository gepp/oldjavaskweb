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
import java.util.List;
import java.util.Map;

public class ParseMXTest {

	private static int blocksize = 2048;
	private static final int PROJ_DATA_LENGTH = 33;

	public static HashMap parseMX(File mxfile) {
		long start = System.currentTimeMillis();
		HashMap map = new HashMap();
		int first = 0;// 2011-06-23gepp
		int drbz = 0;// 2011-06-21gepp
		String mouth = "";
		List<HashMap<String, Object>> invoices = new LinkedList<HashMap<String, Object>>();
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(
					mxfile));
			int bufferis_length = bufferedInputStream.available();
			////System.out.println(bufferis_length);
			int tmpdata = 0;
			StringBuffer sb = new StringBuffer();

			while ((tmpdata = bufferedInputStream.read()) != -1) {
				sb.append(Util.byte2hex((byte) tmpdata));
			}

			String data = sb.toString();
			System.out.println("解析成字符串耗时："+(System.currentTimeMillis()-start)+"ms");
			LinkedList<String> mxlist = splitMxData(data);
			 //System.out.println(mxlist);
			//System.out.println(mxlist.size());
			Iterator<String> it = mxlist.iterator();
			HashMap<String, Object> invoice = null;
			start=System.currentTimeMillis();
			while (it.hasNext()) {
				invoice = new HashMap<String, Object>();
				String subdata = (String) it.next();
				if ("AA".equals(subdata.substring(0, 2))) {
						////System.out.println(subdata.length());
					int subdata_length = Integer.valueOf(
							subdata.substring(2, 6), 16);
					if (subdata_length != 0&&subdata_length==119) {
						////System.out.println("明细标志：" + subdata.substring(0, 2));
						////System.out.println("数据长度：" + subdata_length);
						String sub_data = subdata.substring(6);

						String kprq = sub_data.substring(0, 8);
						////System.out.println(kprq);
						java.text.DecimalFormat format = new java.text.DecimalFormat("00");
						int yy = Integer.valueOf(kprq, 16);
						////System.out.println(yy);
						// //System.out.println(format.format(yy/1048576));
						int mm = yy % 1048576;
						// //System.out.println(mm/65536);
						int dd = mm % 65536;
						// //System.out.println(dd/2048);
						int hh = dd % 2048;
						String date = "20"
								+ String.valueOf(format.format(yy / 1048576))
								+ String.valueOf(format.format(mm / 65536))
								+ String.valueOf(format.format(dd / 2048))
								+ String.valueOf(format.format(hh / 64))
								+ String.valueOf(format.format(hh % 64));
						////System.out.println("开票日期:" + date);// TODO
						invoice.put("kprq", date);
						mouth = date.substring(0, 6);
						String type = sub_data.substring(8, 10);
						////System.out.println("开票类型:" + type);
						invoice.put("kplx", type);
						String invoiceno = String.valueOf(Long.parseLong(
								sub_data.substring(10, 18), 16));
						////System.out.println("发票号: " + invoiceno);
						invoice.put("fphm", invoiceno);
						String amount = String.valueOf(Long.parseLong(
								sub_data.substring(18, 26), 16));
						//System.out.println("合计总金额:" + amount);
						invoice.put("hjzje", amount);
						java.text.DecimalFormat format1 = new java.text.DecimalFormat(
								"0000000000");
						String taxcontrollid = sub_data.substring(26, 42);
						//System.out.println("taxcontrollid = " + taxcontrollid);
						String mac = format1.format(Long.valueOf(
								taxcontrollid.substring(0, 8), 16));
						String num = format1.format(Long.valueOf(
								taxcontrollid.substring(8), 16));
						//System.out.println("十进制税控码长度:"+ (mac.length() + num.length()));
						String taxid = mac + num;
						//System.out.println("税控码:" + taxid);
						invoice.put("skm", taxid);

						String old_invoiceno = String.valueOf(Long.valueOf(
								sub_data.substring(42, 50), 16));
						//System.out.println("原发票号:" + old_invoiceno);
						invoice.put("yfphm", old_invoiceno);

						String invoicecode = sub_data.substring(50, 70);
						//System.out.println("发票代码:" + invoicecode);
						invoice.put("fpdm", invoicecode);

						String payer = new String(Util.HexString2Bytes(sub_data
								.substring(70, 150))).trim();
						//System.out.println((sub_data.substring(70, 150)).trim());
						//System.out.println("付款单位(个人):" + payer);
						invoice.put("fkdw", payer);

						String reciver = new String(
								Util.HexString2Bytes(sub_data.substring(150,
										170))).trim();
						//System.out.println("收款员:" + reciver);
						invoice.put("sky", reciver);

						int projects = Integer.valueOf(
								sub_data.substring(170, 172), 16);
						//System.out.println("项目数:" + projects);
						if(projects>8){
							projects=1;
						}
						//System.out.println("项目数:" + projects);
						invoice.put("xms", String.valueOf(projects));
						if (projects != 0) {
							int prjdata_length = PROJ_DATA_LENGTH * projects
									* 2;
							 //System.out.println("prjdata_length:"+prjdata_length);
							 
							String projdata = sub_data.substring(172,
									172 + prjdata_length);
							//System.out.println(projdata);
							int startindex = 0;
							ArrayList<HashMap<String, String>> projectlist = new ArrayList<HashMap<String, String>>();
							HashMap<String, String> prj_detail = null;
							for (int i = 1; i <= projects; i++) {
								prj_detail = new HashMap<String, String>();
								//System.out.println("==========项目 " + i+ "==========");
								String tmp = projdata.substring(startindex,
										startindex + PROJ_DATA_LENGTH * 2);
								//System.out.println("项目 "+ i+ "名称："+ new String(Util.HexString2Bytes(tmp.substring(0, 40))));
								prj_detail.put(
										"prjname",
										new String(Util.HexString2Bytes(tmp
												.substring(0, 40))));
								String smbm = "";
								String prjname = prj_detail.get("prjname");
								String sql = "select SMBM from SKQ_PMSZ where SMMC like'%"
										+ prjname
										+ "%' or SMJC like '%"
										+ prjname
										+ "%'";
								String fieldname = "SMBM";
								//System.out.println(sql);
								smbm = Query.getField(sql, fieldname);
								if (smbm == null) {
									smbm = "10000006";
								}
								int decimal_digits = Integer.valueOf(
										tmp.substring(40, 42), 16);
								//System.out.println("项目 " + i + "数量小数位数："+ decimal_digits);
								prj_detail.put(
										"xsws",
										String.valueOf(Integer.parseInt(
												tmp.substring(40, 42), 16)));
								//System.out.println(tmp.substring(42, 48));
								switch (decimal_digits) {
								case 0:
									//System.out.println("项目 "+ i+ "数量："+ Integer.valueOf(tmp.substring(42, 48), 16));
									// //System.out.println("数量原来：++"+tmp.substring(42,
									// 48));

									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.parseInt(tmp.substring(42,
															48), 16))));
									break;
								case 1:
									//System.out.println("项目 "+ i+ "数量："+ Integer.valueOf(tmp.substring(42, 48), 16)/ 10);
									 
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
									//System.out.println("项目 "+ i+ "数量："+ Integer.valueOf(tmp.substring(42, 48), 16)/ 100);
									 
									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.valueOf(tmp.substring(42,
															48), 16))
													.divide(new BigDecimal(
															"100"))));
									break;
								case 3:
									//System.out.println("项目 "+ i+ "数量："+ Integer.valueOf(tmp.substring(42, 48), 16)/ 1000);
									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.valueOf(tmp.substring(42,
															48), 16))
													.divide(new BigDecimal(
															"1000"))));
									break;
								case 4:
									//System.out.println("项目 "
//											+ i
//											+ "数量："
//											+ Integer.valueOf(
//													tmp.substring(42, 48), 16)
//											/ 10000);
									prj_detail.put("sl", String
											.valueOf(new BigDecimal(Integer
													.valueOf(tmp.substring(42,
															48), 16))
													.divide(new BigDecimal(
															"10000"))));
									break;
								}

								//System.out.println("项目 "
//										+ i
//										+ "单价："
//										+ Double.valueOf(
//												Long.valueOf(
//														tmp.substring(48, 56),
//														16) / 100)
//												.doubleValue());
								prj_detail.put(
										"dj",
										String.valueOf(Long.parseLong(
												tmp.substring(48, 56), 16)));
								//System.out.println("项目 "
//										+ i
//										+ "金额："
//										+ Double.valueOf(
//												Long.valueOf(
//														tmp.substring(56, 64),
//														16) / 100)
//												.doubleValue());
								prj_detail.put(
										"je",
										String.valueOf(Long.parseLong(
												tmp.substring(56, 64), 16)));
								//System.out.println("项目 "
//										+ i
//										+ "税种税目索引号："
//										+ Integer.valueOf(
//												tmp.substring(64, 66), 16));
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
									+ Integer.parseInt(invoiceno);
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
						 
						if (first != 0) {
							invoices.add(invoice);
						}

					}
					 
				}

				 
			}
		System.out.println("拆成list耗时："+(System.currentTimeMillis()-start)+"ms");
		} catch (FileNotFoundException e) {
			//System.out.println("Cannot open file " + mxfile);
			//System.exit(0);
		} catch (EOFException e) {
			////System.out.println("End of the file " + mxfile + " reached.");
		} catch (IOException e) {
			//System.out.println("Error reading the file " + mxfile);
			//System.exit(0);
		}  
		map.put("drbz", Integer.valueOf(drbz));
		map.put("invoices", invoices);
		map.put("mouth", mouth);
		 
		return map;
	}
	/**
	 * 2013-06-19优化发票明细上传
	 * @param map
	 * @param nsrwjbm
	 * @return
	 */
	 
	public static int insertInvoiceDetail(List<HashMap<String, Object>> al, String machineno,String nsrwjbm)
	 
	{
		 
//		String sql_nsrwjbm = "select NSRWJBM from SKQ_JQXX where JQBH='" + machineno
//				+ "' and STATUS=1";
//		String fieldname = "NSRWJBM";
//		 nsrwjbm = Query.getField(sql_nsrwjbm, fieldname);
		 
		int result = 1;
		if (al == null || al.isEmpty()) {
			return result;
		}
		Connection conn = null;
		Connection conn1 = null;
		if(nsrwjbm!=null){
		try {
			conn = DBConnection.getConnection();
			conn1 = DBConnection.getConnection();
			String sqlmx = "insert into SKQ_FPKJ (JQBH,KPRQ,KPLX,FPDM,FPHM,HJZJE,YFPHM,FKDW,SKY,XMS,SKM,NSRWJBM,STATUS,SCSJ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String sqlproj = "insert into SKQ_FPKJXM (XMMC,XSWS,SL,DJ,JE,SMBM,FPDM,FPHM) values (?,?,?,?,?,?,?,?)";
			 
			conn.setAutoCommit(false);
			conn1.setAutoCommit(false);
			PreparedStatement ps1 = conn.prepareStatement(sqlmx);
			PreparedStatement ps2 = conn1.prepareStatement(sqlproj);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			HashMap hm = null;
			 
			for(int i=0;i<al.size();i++){
				hm = (HashMap) al.get(i);
				ps1.setString(1, machineno);
				String kprq = (String) hm.get("kprq");
				ps1.setString(2, Util.tobzrqSf(kprq));
				ps1.setInt(3, Integer.parseInt((String) hm.get("kplx")));
				String fpdm = (String) hm.get("fpdm");
				ps1.setString(4, fpdm);
				String fphm = (String) hm.get("fphm");
				int afphm=0;
				if(fphm.length()>8){
					afphm=Integer.parseInt(fphm.substring(0,8));
					}else{
						afphm=Integer.parseInt(fphm);
					} 
				
				ps1.setInt(5,afphm);
				ps1.setDouble(6,
						Double.parseDouble((String) hm.get("hjzje")) / 100);
				int a=0;
				
				if(((String) hm.get("yfphm")).length()>8){
				 a=Integer.parseInt(((String) hm.get("yfphm")).substring(0,8));
				}else{
					a=Integer.parseInt((String) hm.get("yfphm"));
				}
				ps1.setInt(7,a);
				ps1.setString(8, (String) hm.get("fkdw"));
				ps1.setString(9, (String) hm.get("sky"));
				ps1.setInt(10, Integer.parseInt((String) hm.get("xms")));
				ps1.setString(11, (String) hm.get("skm"));
				ps1.setString(12, nsrwjbm);
				ps1.setInt(13, 1);
				ps1.setString(14, sdf.format(new Date()));
				////System.out.println("ps1:"+machineno+":"+Util.tobzrqSf(kprq)+":"+Integer.parseInt((String) hm.get("kplx"))+":"+Double.parseDouble((String)hm.get("hjzje")) / 100+":"+(String) hm.get("fkdw")+":"+Integer.parseInt((String) hm.get("xms"))+":");
			 
				ps1.addBatch();

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
						ps2.setString(8, fphm);
					 
						ps2.addBatch();
					}
				}

			}
			int[] res1 = ps1.executeBatch();
			//System.out.println("res1:"+res1.length);
			conn.commit();
			for (int i1 = 0; i1 < res1.length; i1++) {
				if (res1[i1] == PreparedStatement.EXECUTE_FAILED
						|| res1[i1] == PreparedStatement.SUCCESS_NO_INFO) {
					//System.out.println("conn1===roll back"+i1);
					conn.rollback();
					 
					ps1.close();
					result = -1;
				} else {
					int[] res2 = ps2.executeBatch();
					//System.out.println("res2:"+res2.length);
					conn1.commit();
					for (int i2 = 0; i2 < res2.length; i2++) {
						if (res2[i2] == PreparedStatement.EXECUTE_FAILED
								|| res2[i2] == PreparedStatement.SUCCESS_NO_INFO) {
							//System.out.println("conn2===roll back"+i2);
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
			//System.out.println("异常："+e.getMessage());
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
		}
		return result;
	}

	private static void parseMYSY() {
		try {
			// FileInputStream fis = new FileInputStream(new
			// File("c:\\MXSY.BIN"));
			FileInputStream fis = new FileInputStream(new File(
					"c://MXSY.BIN"));
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					fis));
			byte[] arr = new byte[dis.available()];
			dis.read(arr, 0, dis.available());
			// //System.out.println(Util.byte2hex(arr));
			String data = Util.byte2hex(arr);

			//System.out.println(data.substring(0, 16));
			//System.out.println(data.substring(16, 24));
			//System.out.println(data.substring(24, 32));
			//System.out.println(Integer.valueOf(data.substring(32, 36), 16).toString());
			//System.out.println(new String(Hex.decode(data.substring(36, 52))));
			//System.out.println(Integer.valueOf(data.substring(52, 56), 16).toString());
			//System.out.println(Integer.valueOf(data.substring(56, 60), 16).toString());
			//System.out.println(data.substring(60));
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
		long begin=System.currentTimeMillis();
		LinkedList<String> al = new LinkedList<String>();
		int data_length = data.length();
		////System.out.println("data_length = " + data_length);
		int beginIndex = 0;
		int endIndex = 0;

		while (endIndex != data_length) {
			int mx_length = Integer.parseInt(
					data.substring(beginIndex + 2, beginIndex + 6), 16) * 2 + 8;
			endIndex += mx_length;
			String str = "";
			try {
				str =(data.substring(beginIndex, endIndex));
			} catch (Exception ex) {
				str = "";
				al.removeLast();
				return al;
			}
			al.add(str);
			beginIndex = endIndex;
		}
		//System.out.println("创建splitMxData时间："+(System.currentTimeMillis()-begin));

		return al;
	}

	public static void main(String[] args) {
		// parseMYSY();
		long start = System.currentTimeMillis();
		//for(int i=0;i<10;i++){
		//	//System.out.println("i:"+i);
		ParseMXTest p = new ParseMXTest();
		 Map map = p.parseMX(new File("C:\\MX000001.BIN"));
		 ArrayList al=(ArrayList)map.get("invoices");
		 int result = p.insertInvoiceDetail(al,"0000000000012313","0000000032803657");
		//}
		System.out.println(("共花时间："+(System.currentTimeMillis() - start)) + "ms");
	}
}
