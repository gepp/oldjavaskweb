package jsdt.model;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

import org.bouncycastle.util.encoders.Hex;

public class Fpkj extends baseClass {
	private int sid;
	private String nsrwjbm;
	private String nsrmc;
	private String jqbh;
	private String kprq;
	private int kplx;
	private String fpdm;
	private int fphm;
	private double hjzje;
	private int yfphm;
	private String fkdw;
	private String sky;
	private int xms;
	private String skm;
	private int qrbz;
	private int status;
	private String scsj;
	private ArrayList fpxmxx;
	private static final int PROJ_DATA_LENGTH = 33;
	//start-add by yinkun 20101221 
	private String xmmc;
	private double dj;//单价
	private double sl;//数量
	private double zkzje;
	public double getZkzje() {
		return zkzje;
	}

	public void setZkzje(double zkzje) {
		this.zkzje = zkzje;
	}
	public double getSl() {
		return sl;
	}

	public void setSl(double sl) {
		this.sl = sl;
	}

	public double getDj() {
		return dj;
	}

	public void setDj(double dj) {
		this.dj = dj;
	}

	//end-add by yinkun 20101221 
	/**
	 * @roseuid 4CA19C4C00DA
	 */
	public Fpkj() {

	}

	/**
	 * @param fpdm
	 * @param fphm
	 * @roseuid 4CA06D6002BF
	 */
	public Fpkj(String fpdm, int fphm) {

	}

	/**
	 * @param nsrwjbm
	 * @param fpdm
	 * @param fpqsh
	 * @param fpjzh
	 * @return int
	 * @roseuid 4CA07136005D
	 */
	public int fpkjInit(String nsrwjbm, String fpdm, int fpqsh, int fpjzh) {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 4CA06F6A0213
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_FPKJ set KPRQ=" + this.kprq + ",KPLX="
						+ this.kplx + ",HJZJE=" + this.hjzje + ",YFPHM="
						+ this.yfphm + ",FKDW='" + this.fkdw + "',SKY='"
						+ this.sky + "',XMS=" + this.xms + ",SKM='" + this.skm
						+ "',STATUS=1,SCSJ=" + this.scsj + " where FPDM='"
						+ this.fpdm + "' and FPHM=" + this.fphm;
				PreparedStatement ps = conn.prepareStatement(sql);
				result = ps.executeUpdate();
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * @param nsrwjbm
	 * @param jqbh
	 * @param kprq
	 * @param kplx
	 * @param fpdm
	 * @param fphm
	 * @param hjzje
	 * @param yfphm
	 * @param fkdw
	 * @param sky
	 * @param xms
	 * @param skm
	 * @param fpxmxx
	 * @return int
	 * @roseuid 4CA06F720177
	 */
	public int update(String nsrwjbm, String jqbh, int kprq, int kplx,
			String fpdm, int fphm, double hjzje, int yfphm, String fkdw,
			String sky, int xms, String skm, ArrayList fpxmxx) {
		return 0;
	}

	public int addFpxmmx() {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				if (this.fpxmxx != null && !this.fpxmxx.isEmpty()) {
					Iterator it = this.fpxmxx.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String sql = "insert into SKQ_FPKJXM(PARENTID,XMMC,XSWS,SL,DJ,JE,SMBM) values("
								+ this.sid
								+ ",'"
								+ (String) hm.get("PRJNAME")
								+ "',"
								+ (Integer) hm.get("XSWS")
								+ ","
								+ (Integer) hm.get("SL")
								+ ","
								+ (Double) hm.get("PRICE")
								+ ","
								+ (Double) hm.get("AMOUNT")
								+ ",'"
								+ (String) hm.get("SMBM") + "')";
						stsm.addBatch(sql);
					}
				}

				int[] result = stsm.executeBatch();
				conn.commit();
				for (int i = 0; i < result.length; i++) {
					if (result[i] == Statement.EXECUTE_FAILED
							|| result[i] == Statement.SUCCESS_NO_INFO) {
						conn.rollback();
						stsm.close();
						result1 = -1;
					}
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result1 = -1;
			e.printStackTrace();
		}
		return result1;
	}

	/**
	 * @param jqbh
	 * @param fpdm
	 * @param fphm
	 * @return String
	 * @roseuid 4CA0729B00BB
	 */
	public String selectWscfp(String jqbh, String fpdm, int fphm) {
		return null;
	}

	public HashMap readSY(String path) {
		HashMap mx = new HashMap();
		try {
			// FileInputStream fis = new FileInputStream(new
			// File("c:\\MXSY.BIN"));
			File syfile = new File(path + File.separator + "MXSY.BIN");
			if (!syfile.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream(syfile);
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					fis));
			byte[] arr = new byte[dis.available()];
			dis.read(arr, 0, dis.available());
			// System.out.println(Util.byte2hex(arr));
			String data = Util.byte2hex(arr);

			System.out.println(data.substring(0, 16));
			mx.put("JQBH", data.substring(0, 16));
			System.out.println(data.substring(16, 24));
			mx.put("KSRQ", data.substring(16, 24));
			System.out.println(data.substring(24, 32));
			mx.put("JSRQ", data.substring(24, 32));
			System.out.println(Integer.valueOf(data.substring(32, 36), 16)
					.toString());
			System.out.println(new String(Hex.decode(data.substring(36, 52))));
			mx.put("WJBSF", new String(Hex.decode(data.substring(36, 52))));
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
		return mx;
	}

	public HashMap declareMx(String path, HashMap hm) {
		if (path == null || "".equals(path)) {
			return null;
		}
		LinkedList<HashMap> mxlist = new LinkedList<HashMap>();
		try {
			File mxfile = new File(path + File.separator + hm.get("WJBSF")
					+ ".BIN");
			if (!mxfile.exists()) {
				hm.put("MX", null);
				return hm;
			}
			FileInputStream fis = new FileInputStream(mxfile);
			DataInputStream dis = new DataInputStream(new BufferedInputStream(
					fis));
			byte[] arr = new byte[dis.available()];
			dis.read(arr, 0, dis.available());
			String data = Util.byte2hex(arr);
			System.out.println("data = " + data);

			ArrayList al = splitMxData(data);
			Iterator it = al.iterator();

			while (it.hasNext()) {
				HashMap mx = new HashMap();
				String subdata = (String) it.next();
				System.out.println(subdata.length());
				System.out.println("明细标志：" + subdata.substring(0, 2));
				// System.out.println(subdata.substring(2, 6));
				int subdata_length = Integer.valueOf(subdata.substring(2, 6),
						16);
				System.out.println("数据长度：" + subdata_length);
				String sub_data = subdata.substring(6);

				String kprqtmp = sub_data.substring(0, 8);
				System.out.println(kprqtmp);
				java.text.DecimalFormat format = new java.text.DecimalFormat(
						"00");
				int yy = Integer.valueOf(kprqtmp, 16);
				// System.out.println(format.format(yy/1048576));
				int mm = yy % 1048576;
				// System.out.println(mm/65536);
				int dd = mm % 65536;
				// System.out.println(dd/2048);
				String kprq = "20"
						+ String.valueOf(format.format(yy / 1048576))
						+ String.valueOf(mm / 65536)
						+ String.valueOf(dd / 2048);
				System.out.println("开票日期:" + kprq);// TODO
				mx.put("KPRQ", kprq);
				System.out.println("开票类型:"
						+ Integer.valueOf(sub_data.substring(8, 10), 16));
				mx.put("KPLX", Integer.valueOf(sub_data.substring(8, 10), 16));
				System.out.println(sub_data.substring(10, 18));
				System.out.println("发票号: "
						+ Long.valueOf(sub_data.substring(10, 18), 16));
				mx.put("FPH", Long.valueOf(sub_data.substring(10, 18), 16));
				System.out.println(sub_data.substring(18, 26));
				System.out.println("合计总金额:"
						+ Double
								.valueOf(
										Integer.valueOf(sub_data.substring(18,
												26), 16) / 100).doubleValue());
				mx.put("HJZJE", Double.valueOf(
						Integer.valueOf(sub_data.substring(18, 26), 16) / 100)
						.doubleValue());
				java.text.DecimalFormat format1 = new java.text.DecimalFormat(
						"0000000000");
				String taxcontrollid = sub_data.substring(26, 42);
				System.out.println("taxcontrollid = " + taxcontrollid);
				String mac = format1.format(Long.valueOf(taxcontrollid
						.substring(0, 8), 16));
				String num = format1.format(Long.valueOf(taxcontrollid
						.substring(8), 16));
				System.out.println("十进制税控码长度:" + (mac.length() + num.length()));
				System.out.println("税控码:" + mac + num); // TODO
				mx.put("SKM", mac + num);
				System.out.println("原发票号:"
						+ Long.valueOf(sub_data.substring(42, 50), 16));
				mx.put("YFPH", Long.valueOf(sub_data.substring(42, 50), 16));
				System.out.println("发票代码:" + sub_data.substring(50, 70));
				mx.put("FPDM", sub_data.substring(50, 70));
				System.out.println("付款单位(个人):"
						+ new String(Util.HexString2Bytes(sub_data.substring(
								70, 150))));
				mx.put("FKDW", new String(Util.HexString2Bytes(sub_data
						.substring(70, 150))).trim());
				System.out.println("收款员:"
						+ new String(Util.HexString2Bytes(sub_data.substring(
								150, 170))));
				mx.put("SKY", new String(Util.HexString2Bytes(sub_data
						.substring(150, 170))).trim());
				int projects = Integer
						.valueOf(sub_data.substring(170, 172), 16);
				System.out.println("项目数:" + projects);
				mx.put("XMS", Integer.valueOf(projects));

				if (projects != 0) {
					LinkedList<HashMap> prjlist = new LinkedList<HashMap>();
					int prjdata_length = PROJ_DATA_LENGTH * projects * 2;
					// System.out.println(prjdata_length);
					String projdata = sub_data.substring(172,
							172 + prjdata_length);
					System.out.println(projdata);
					int startindex = 0;
					HashMap prjhm = new HashMap();
					for (int i = 1; i <= projects; i++) {
						System.out.println("==========项目 " + i + "==========");
						String tmp = projdata.substring(startindex, startindex
								+ PROJ_DATA_LENGTH * 2);

						// System.out.println("项目"+i+"数据: "+tmp);
						// System.out.println("项目"+i+"数据长度: "+tmp.length());
						System.out.println("项目 "
								+ i
								+ "名称："
								+ new String(Util.HexString2Bytes(tmp
										.substring(0, 40))));
						prjhm.put("PRJNAME", new String(Util
								.HexString2Bytes(tmp.substring(0, 40))).trim());
						Integer decimal_digits = Integer.valueOf(tmp.substring(
								40, 42), 16);
						System.out.println("项目 " + i + "数量小数位数："
								+ decimal_digits);
						prjhm.put("XSWS", decimal_digits);
						System.out.println(tmp.substring(42, 48));
						Integer count = 0;
						switch (decimal_digits) {
						case 0:
							count = Integer.valueOf(tmp.substring(42, 48), 16);

							break;
						case 1:
							count = Integer.valueOf(tmp.substring(42, 48), 16) / 10;
							break;
						case 2:
							count = Integer.valueOf(tmp.substring(42, 48), 16) / 100;
							break;
						case 3:
							count = Integer.valueOf(tmp.substring(42, 48), 16) / 1000;
							break;
						}
						System.out.println("项目 " + i + "数量：" + count);
						prjhm.put("SL", count);

						System.out.println("项目 "
								+ i
								+ "单价："
								+ Double.valueOf(
										Integer.valueOf(tmp.substring(48, 56),
												16) / 100).doubleValue());
						prjhm.put("PRICE", Double.valueOf(Integer.valueOf(tmp
								.substring(48, 56), 16) / 100));
						System.out.println("项目 "
								+ i
								+ "金额："
								+ Double.valueOf(
										Integer.valueOf(tmp.substring(56, 64),
												16) / 100).doubleValue());
						prjhm.put("AMOUNT", Double.valueOf(Integer.valueOf(tmp
								.substring(56, 64), 16) / 100));
						System.out.println("项目 " + i + "税种税目索引号："
								+ Integer.valueOf(tmp.substring(64, 66), 16));
						prjhm.put("SZSMSY", Integer.valueOf(tmp.substring(64,
								66), 16));

						prjlist.add(prjhm);
						startindex = startindex + PROJ_DATA_LENGTH * 2;
					}
					mx.put("PROJECTS", prjlist);
				}
				mxlist.add(mx);
				// System.out.println("\r\n");
				hm.put("MX", mxlist);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;
	}

	private static ArrayList splitMxData(String data) {
		// System.out.println(data);
		ArrayList al = new ArrayList();
		int subdata_length = 0;
		int start_index = 0;
		int end_index = 0;

		String subdata = "";
		String tmp = data;

		while (tmp != null && !"".equals(tmp)) {
			// System.out.println("明细标志："+tmp.substring(0,2));
			subdata_length = Integer.valueOf(tmp.substring(2, 6), 16);
			// System.out.println("数据长度："+subdata_length);
			// tmp = data.substring(end_index+subdata_length);
			end_index = subdata_length * 2 + 8;
			// System.out.println(tmp.substring(0,end_index));
			al.add(tmp.substring(0, end_index));
			tmp = tmp.substring(end_index);
			// System.out.println("下一次数据："+tmp);

		}

		return al;
	}

	public ArrayList selectSbmxProj(int parentid) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPKJXM where PARENTID"
						+ parentid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();

					hm.put("sid", rs.getInt("SID"));
					hm.put("parentid", rs.getInt("PARENTID"));
					hm.put("xmmc", rs.getString("XMMC"));
					hm.put("xsws", rs.getInt("XSWS"));
					hm.put("sl", rs.getInt("SL"));
					hm.put("dj", rs.getDouble("DJ"));
					hm.put("je", rs.getDouble("JE"));
					hm.put("smbm", rs.getString("SMBM"));

					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return al;
	}

	// 查询待确认退票
	public ArrayList selectDqrtp(String nsrwjbm, String starttime,
			String endtime) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPKJ where NSRWJBM='" + nsrwjbm
						+ "' and STATUS=1 and QRBZ=0 and KPLX=2 and KPRQ>='" + starttime
						+ "' and KPRQ<='" + endtime + "'";
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fpkj fpkj = new Fpkj();

					fpkj.setSid(Integer.valueOf(rs.getInt("SID")));
					fpkj.setNsrwjbm(rs.getString("NSRWJBM"));
					fpkj.setJqbh(rs.getString("JQBH"));
					fpkj.setKprq(rs.getString("KPRQ"));
					fpkj.setKplx(Integer.valueOf(rs.getInt("KPLX")));
					fpkj.setFpdm(rs.getString("FPDM"));
					fpkj.setFphm(Integer.valueOf(rs.getInt("FPHM")));
					fpkj.setHjzje(Double.valueOf(rs.getDouble("HJZJE")));
					fpkj.setYfphm(Integer.valueOf(rs.getInt("YFPHM")));
					fpkj.setFkdw(Util.iso8859togbk(rs.getString("FKDW")));
					fpkj.setSky(Util.iso8859togbk(rs.getString("SKY")));

					al.add(fpkj);
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return al;
	}
	
	// 获取汇总数据明细信息
	public ArrayList selectHzsjmx(int parentid,String startdate,String enddate,String nsrwjbm) {
		ArrayList al = new ArrayList();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SMMC from SKQ_HZSJMX a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.PARENTID="
						+ parentid;
				System.out.println("获取汇总数据明细信息sql：" + sql);
				System.out.println("获取汇总数据明细信息sql222222222：" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();

					hm.put("smbm", rs.getString("SMBM"));
					System.out.println("smbm==="+rs.getString("SMBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					System.out.println("sql==select SID from SKQ_FPKJ where NSRWJBM='"+nsrwjbm+"' and KPLX=2 and QRBZ=0 and KPRQ>='"+startdate+"' and KPRQ<='"+enddate+"'");
					String sql1 = "select sum(JE) as tpzje from SKQ_FPKJXM where SMBM='"+rs.getString("SMBM")+"' and PARENTID in(select SID from SKQ_FPKJ where NSRWJBM='"+nsrwjbm+"' and KPLX=2 and QRBZ=0 and KPRQ>='"+startdate+"' and KPRQ<='"+enddate+"' )";
					
					String fieldname = "tpzje";
					double tpzje = Query.getFieldDouble(sql1, fieldname);
					hm.put("tpzje", Double.valueOf(tpzje));
					hm.put("kpje", Double.valueOf(rs.getDouble("KPJE")));
					hm.put("dkje", Double.valueOf(rs.getDouble("DKJE")));
					hm.put("yhde", Double.valueOf(rs.getDouble("YHDYYE")));

					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return al;
	}

	// 退票确认
	public int updateDqrtp(String fpdm, int fphm) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_FPKJ set QRBZ=1 where FPDM='" + fpdm
						+ "' and FPHM=" + fphm;
				PreparedStatement ps = conn.prepareStatement(sql);
				result = ps.executeUpdate();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getNsrwjbm() {
		return nsrwjbm;
	}

	public void setNsrwjbm(String nsrwjbm) {
		this.nsrwjbm = nsrwjbm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public String getKprq() {
		return kprq;
	}

	public void setKprq(String kprq) {
		this.kprq = kprq;
	}

	public int getKplx() {
		return kplx;
	}

	public void setKplx(int kplx) {
		this.kplx = kplx;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public int getFphm() {
		return fphm;
	}

	public void setFphm(int fphm) {
		this.fphm = fphm;
	}

	public double getHjzje() {
		return hjzje;
	}

	public void setHjzje(double hjzje) {
		this.hjzje = hjzje;
	}

	public int getYfphm() {
		return yfphm;
	}

	public void setYfphm(int yfphm) {
		this.yfphm = yfphm;
	}

	public String getFkdw() {
		return fkdw;
	}

	public void setFkdw(String fkdw) {
		this.fkdw = fkdw;
	}

	public String getSky() {
		return sky;
	}

	public void setSky(String sky) {
		this.sky = sky;
	}

	public int getXms() {
		return xms;
	}

	public void setXms(int xms) {
		this.xms = xms;
	}

	public String getSkm() {
		return skm;
	}

	public void setSkm(String skm) {
		this.skm = skm;
	}

	public int getQrbz() {
		return qrbz;
	}

	public void setQrbz(int qrbz) {
		this.qrbz = qrbz;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getScsj() {
		return scsj;
	}

	public void setScsj(String scsj) {
		this.scsj = scsj;
	}

	public ArrayList getFpxmxx() {
		return fpxmxx;
	}

	public void setFpxmxx(ArrayList fpxmxx) {
		this.fpxmxx = fpxmxx;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		//System.out.println("xmmc111="+xmmc);
		this.xmmc = xmmc;
	}

	public static int getPROJ_DATA_LENGTH() {
		return PROJ_DATA_LENGTH;
	}

}
