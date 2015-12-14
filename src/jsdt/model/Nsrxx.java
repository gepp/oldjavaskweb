package jsdt.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.Util;

public class Nsrxx extends baseClass {
	private int sid;
	private String nsrwjbm;
	private String nsrsbh;
	private String nsrmc;
	private String jydz;
	private String frdb;
	private String zclxbm;
	private String zclxmc;
	private String hybm;
	private String hymc;
	private String hymxbm;
	private String hymxmc;
	private int zsfs;
	private double yhde;
	private String swjgbm;
	private String swjgmc;
	private String bsy;
	private String ssgly;
	private int status;
	private ArrayList nsrszsm;
	private ArrayList nsrjqxx;
	private String qysmbmStr;
	private String v_pid;
	public String getV_pid() {
		return v_pid;
	}

	public void setV_pid(String v_pid) {
		this.v_pid = v_pid;
	}

	/**
	 * @roseuid 4CA19C500138
	 */
	public Nsrxx() {

	}

	/**
	 * @param nsrwjbm
	 * @roseuid 4CA0B5F502EE
	 */
	public Nsrxx(String nsrwjbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.ZCLXMC,c.HYMC,d.HYMXMC,e.SWJGMC from SKQ_NSRXX a "
						+ "left outer join SKQ_ZCLX b on a.ZCLXBM=b.ZCLXBM "
						+ "left outer join SKQ_HY c on a.HYBM=c.HYBM "
						+ "left outer join SKQ_HYMX d on a.HYMXBM=d.HYMXBM "
						+ "left outer join SKQ_SWJG e on a.SWJGBM=e.SWJGBM where a.NSRWJBM='"
						+ nsrwjbm + "'";
				System.out.println("sql="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.nsrwjbm = rs.getString("NSRWJBM");
					this.nsrsbh = rs.getString("NSRSBH");
					this.nsrmc = Util.iso8859togbk(rs.getString("NSRMC"));
					this.jydz = Util.iso8859togbk(rs.getString("JYDZ"));
					this.frdb = Util.iso8859togbk(rs.getString("FRDB"));
					this.zclxbm = rs.getString("ZCLXBM");
					this.zclxmc = Util.iso8859togbk(rs.getString("ZCLXMC"));
					this.hybm = rs.getString("HYBM");
					this.hymc = Util.iso8859togbk(rs.getString("HYMC"));
					this.hymxbm = rs.getString("HYMXBM");
					this.hymxmc = Util.iso8859togbk(rs.getString("HYMXMC"));
					this.zsfs = rs.getInt("ZSFS");
					this.yhde = rs.getDouble("YHDE");
					this.swjgbm = rs.getString("SWJGBM");
					this.swjgmc = Util.iso8859togbk(rs.getString("SWJGMC"));
					this.bsy = Util.iso8859togbk(rs.getString("BSY"));
					this.ssgly = Util.iso8859togbk(rs.getString("SSGLY"));
					this.status = rs.getInt("STATUS");
				}
				rs.close();
				ps.close();

				if (this.status == 1) {
					// 纳税人对应税种税目
					ArrayList nsrszsm = new ArrayList();

					String sql1 = "select a.*,b.FPBM from SKQ_NSRSZSM a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM  where a.NSRWJBM='"
							+ nsrwjbm + "'";
					ps = conn.prepareStatement(sql1);
					rs = ps.executeQuery();
					while (rs.next()) {
						HashMap hm = new HashMap();

						hm.put("szbm", rs.getString("SZBM"));
						hm.put("smbm", rs.getString("SMBM"));
						hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
						hm.put("smjc", Util.iso8859togbk(rs.getString("SMJC")));
						hm.put("sl", rs.getDouble("SL"));
						hm.put("sid", rs.getInt("SID"));
						hm.put("fpbm", rs.getString("FPBM"));

						nsrszsm.add(hm);
					}
					rs.close();
					ps.close();

					this.nsrszsm = nsrszsm;

					// 机器信息
					ArrayList nsrjqxx = new ArrayList();

					String sql2 = "select a.*,b.JQXHMC from SKQ_JQXX a left outer join SKQ_JQXH b on a.JQXHBM=b.JQXHBM where a.NSRWJBM='"
							+ nsrwjbm + "'";
					ps = conn.prepareStatement(sql2);
					rs = ps.executeQuery();

					while (rs.next()) {
						Jqxx jqxx = new Jqxx();
						jqxx.setSid(rs.getInt("SID"));
						jqxx.setJqxhbm(rs.getString("JQXHBM"));
						jqxx.setJqxhmc(Util
								.iso8859togbk(rs.getString("JQXHMC")));
						jqxx.setJqbh(rs.getString("JQBH"));
						jqxx.setSkkh(rs.getString("SKKH"));
						jqxx.setYhkh(rs.getString("YHKH"));
						jqxx.setKqyrq(rs.getString("KQYRQ").substring(0,10));
						jqxx.setKyxrq(rs.getString("KYXRQ").substring(0,10));
						jqxx.setSbfs(rs.getString("SBFS"));
						jqxx.setMxsbbz(rs.getString("MXSBBZ"));
						jqxx.setKpjzrq(rs.getString("KPJZRQ").substring(0,10));
						jqxx.setDzkpxe(rs.getDouble("DZKPXE"));
						jqxx.setYljkpxe(rs.getDouble("YLJKPXE"));
						jqxx.setYljtpxe(rs.getDouble("YLJTPXE"));
						jqxx.setStatus(rs.getInt("STATUS"));

						// 机器对应税种税目
						ArrayList jqszsm = new ArrayList();
						String sql3 = "select a.*,b.SMMC,b.SMJC,b.SL,b.SMSY from SKQ_JQSZSM a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.JQBH='"
								+ rs.getString("JQBH") + "'";
						PreparedStatement ps1 = conn.prepareStatement(sql3);
						ResultSet rs1 = ps1.executeQuery();
						while (rs1.next()) {
							HashMap hm = new HashMap();
							hm.put("jqbh", rs1.getString("JQBH"));
							hm.put("smbm", rs1.getString("SMBM"));
							hm.put("smmc", Util.iso8859togbk(rs1
									.getString("SMMC")));
							hm.put("smjc", Util.iso8859togbk(rs1
									.getString("SMJC")));
							hm.put("sl", rs1.getDouble("SL"));
							hm.put("smsy", rs1.getInt("smsy"));

							jqszsm.add(hm);
						}

						rs1.close();
						ps1.close();
						jqxx.setJqszsm(jqszsm);
						nsrjqxx.add(jqxx);
					}
					rs.close();
					ps.close();

					this.nsrjqxx = nsrjqxx;
				}
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
		Query.updateField("delete from  skq_jqszsm where smbm=''");
	}

	public HashMap getFromZg(String nsrwjbm) {
		HashMap hm = new HashMap();
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.QY_ID,a.SWJGBM,a.ZSFSBM,a.JJXZBM,a.HYBM,a.HYMX,a.DJZH_SWDJZB,a.NSRMC_SWDJZB,a.FDDB_SWDJZB,b.SDBJ from DJ_SWDJZB a left outer join BM_ZSFS b on a.ZSFSBM=b.ZSFSBM and b.QYBJ_ZSFS='1' where a.QY_ID='"
						+ nsrwjbm + "'";
				System.out.println("sql="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					hm.put("nsrwjbm", rs.getString("QY_ID"));
					hm.put("swjgbm", rs.getString("SWJGBM"));
					hm.put("zsfsbm", rs.getString("ZSFSBM"));
					hm.put("zclxbm", rs.getString("JJXZBM"));
					String hybm = rs.getString("HYBM");
					if(hybm==null){
						hybm="";
					}
					hm.put("hybm", hybm);
					String hymxbm = rs.getString("HYMX");
					if(hymxbm==null){
						hymxbm="";
					}
					hm.put("hymxbm", hymxbm);
					hm.put("nsrsbh", rs.getString("DJZH_SWDJZB"));
					hm.put("nsrmc", Util.iso8859togbk(rs
							.getString("NSRMC_SWDJZB")));
					hm.put("frdb", Util.iso8859togbk(rs
							.getString("FDDB_SWDJZB")));
					
					
					hm.put("zsfs", Integer.valueOf(rs.getString("SDBJ")));
					hm.put("bsy", "");
					hm.put("ssgly", "");
				}
				rs.close();
				ps.close();
				
				//获取核定
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String nowtime = sdf.format(new Date());
				sql = "select a.SDDE from HD_HDZB a where a.QY_ID='"+nsrwjbm+"' and '"+Util.toxkrq(nowtime).substring(0,6)+"'<=a.HDQZ and '"+Util.toxkrq(nowtime).substring(0,6)+"' >=a.HDQQ and a.TZBS='1'";
				System.out.println("sql==核定"+sql);
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				double yhde = 0;
				while (rs.next()) {
					yhde = rs.getDouble("SDDE");
				}
				hm.put("yhde", yhde);
				
				rs.close();
				ps.close();
				
				
				//查询纳税户对应税种税目
				String smbmStr = null;
				ArrayList nsrszsm = new ArrayList();
				sql = "select a.SMBM,b.SZBM,b.SMMC,b.SMJC,b.FDSL from DJ_SZDJHD a left outer join BM_SM b on a.SMBM=b.SMBM where a.QY_ID='"
						+ nsrwjbm + "' and b.SZBM='03'";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					if (smbmStr == null) {
						smbmStr = "'" + rs.getString("SMBM") + "'";
					} else {
						smbmStr = smbmStr + ",'" + rs.getString("SMBM") + "'";
					}

					HashMap hm1 = new HashMap();
					hm1.put("szbm", rs.getString("SZBM"));
					hm1.put("smbm", rs.getString("SMBM"));
					hm1.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					hm1.put("smjc", Util.iso8859togbk(rs.getString("SMJC")));
					
					DecimalFormat dg = new DecimalFormat("0.00");
					hm1.put("sl", Double.valueOf(dg.format(Double.valueOf(rs.getDouble("FDSL")*100))));

					nsrszsm.add(hm1);
				}
				//System.out.println("nsrszsm=="+nsrszsm);
				rs.close();
				ps.close();
				hm.put("nsrszsm", nsrszsm);
				hm.put("smbmStr", smbmStr);

				conn.close();
			}
			
			if ((String)hm.get("nsrwjbm") != null && !"".equals((String)hm.get("nsrwjbm"))) {
				conn = DBConnection.getConnection();
				String qysmbmStr = ",";
				if (conn != null) {
					String sql = "select SMBM from SKQ_PMSZ where SMBM in ("
							+ (String) hm.get("smbmStr") + ") and STATUS=1";
					PreparedStatement ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						qysmbmStr = qysmbmStr + rs.getString("SMBM") + ",";
					}
					rs.close();
					ps.close();
					conn.close();
				}

				hm.put("qysmbmStr", qysmbmStr);
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
		return hm;
	}

	/**
	 * @return int
	 * @roseuid 4CA0B61200FA
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_NSRXX (NSRWJBM,NSRSBH,NSRMC,JYDZ,FRDB,ZCLXBM,HYBM,HYMXBM,ZSFS,YHDE,SWJGBM,BSY,SSGLY,STATUS,V_PID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, this.nsrwjbm);
				ps.setString(2, this.nsrsbh);
				ps.setString(3, Util.gbkto8859(this.nsrmc));
				ps.setString(4, Util.gbkto8859(this.jydz));
				ps.setString(5, Util.gbkto8859(this.frdb));
				ps.setString(6, this.zclxbm);
				ps.setString(7, this.hybm);
				ps.setString(8, this.hymxbm);
				ps.setInt(9, this.zsfs);
				ps.setDouble(10, this.yhde);
				ps.setString(11, this.swjgbm);
				ps.setString(12, Util.gbkto8859(this.bsy));
				ps.setString(13, Util.gbkto8859(this.ssgly));
				ps.setInt(14, this.status);
				ps.setString(15,this.v_pid);
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
	 * @param nsrsbh
	 * @param nsrmc
	 * @param jydz
	 * @param frdb
	 * @param zclxbm
	 * @param hybm
	 * @param hymxbm
	 * @param zsfs
	 * @param yhde
	 * @param swjgbm
	 * @param bsy
	 * @param ssgly
	 * @return int
	 * @roseuid 4CA0B61401C5
	 */
	public int add(String nsrwjbm, String nsrsbh, String nsrmc, String jydz,
			String frdb, String zclxbm, String hybm, String hymxbm, int zsfs,
			double yhde, String swjgbm, String bsy, String ssgly, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_NSRXX (NSRWJBM,NSRSBH,NSRMC,JYDZ,FRDB,ZCLXBM,HYBM,HYMXBM,ZSFS,YHDE,SWJGBM,BSY,SSGLY,STATUS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, nsrwjbm);
				ps.setString(2, nsrsbh);
				ps.setString(3, Util.gbkto8859(nsrmc));
				ps.setString(4, Util.gbkto8859(jydz));
				ps.setString(5, Util.gbkto8859(frdb));
				ps.setString(6, zclxbm);
				ps.setString(7, hybm);
				ps.setString(8, hymxbm);
				ps.setInt(9, zsfs);
				ps.setDouble(10, yhde);
				ps.setString(11, swjgbm);
				ps.setString(12, Util.gbkto8859(bsy));
				ps.setString(13, Util.gbkto8859(ssgly));
				ps.setInt(14, status);
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
	 * @return int
	 * @roseuid 4CA0B6BE0148
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("update SKQ_NSRXX set NSRSBH=?,NSRMC=?,JYDZ=?,FRDB=?,ZCLXBM=?,HYBM=?,HYMXBM=?,ZSFS=?,YHDE=?,SWJGBM=?,BSY=?,SSGLY=?,STATUS=? where NSRWJBM=?");
				ps.setString(1, this.nsrsbh);
				ps.setString(2, Util.gbkto8859(this.nsrmc));
				ps.setString(3, Util.gbkto8859(this.jydz));
				ps.setString(4, Util.gbkto8859(this.frdb));
				ps.setString(5, this.zclxbm);
				ps.setString(6, this.hybm);
				ps.setString(7, this.hymxbm);
				ps.setInt(8, this.zsfs);
				ps.setDouble(9, this.yhde);
				ps.setString(10, this.swjgbm);
				ps.setString(11, Util.gbkto8859(this.bsy));
				ps.setString(12, Util.gbkto8859(this.ssgly));
				ps.setInt(13, this.status);
				ps.setString(14, this.nsrwjbm);
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
	 * @param nsrsbh
	 * @param nsrmc
	 * @param jydz
	 * @param frdb
	 * @param zclxbm
	 * @param hybm
	 * @param hymxbm
	 * @param zsfs
	 * @param yhde
	 * @param swjgbm
	 * @param bsy
	 * @param ssgly
	 * @return int
	 * @roseuid 4CA0B6C00000
	 */
	public int update(String nsrwjbm, String nsrsbh, String nsrmc, String jydz,
			String frdb, String zclxbm, String hybm, String hymxbm, int zsfs,
			double yhde, String swjgbm, String bsy, String ssgly) {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 4CA0B74C0196
	 */
	public int addSzsm() {
		ArrayList nsrszsm = this.nsrszsm;
		String qysmbmStr = this.qysmbmStr;
		//System.out.println("qysmbmStr111="+qysmbmStr);

		int result1 = 1;
		Connection conn = null;

		if (nsrszsm != null && !nsrszsm.isEmpty()) {
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					String sql = "delete from SKQ_NSRSZSM where NSRWJBM='"
							+ this.nsrwjbm + "'";
					stsm.addBatch(sql);
					Iterator it = nsrszsm.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						int flag = 0;
						if (qysmbmStr.indexOf((String) hm.get("smbm")) >= 0) {
							flag = 1;
						}

						String sql1 = "insert into SKQ_NSRSZSM (SMBM,SZBM,SMMC,SMJC,SL,NSRWJBM,STATUS) values('"
								+ (String) hm.get("smbm")
								+ "','"
								+ (String) hm.get("szbm")
								+ "','"
								+ Util.gbkto8859((String) hm.get("smmc"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("smjc"))
								+ "',"
								+ (Double) hm.get("sl")
								+ ",'"
								+ this.nsrwjbm + "'," + flag + ")";
						// System.out.println("sql1=="+sql1);
						stsm.addBatch(sql1);
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
		}
		return result1;
	}

	/**
	 * @param nsrwjbm
	 * @param nsrszsm
	 * @return int
	 * @roseuid 4CA0B75301E4
	 */
	public int addSzsm(String nsrwjbm, ArrayList nsrszsm) {
		int result1 = 1;
		Connection conn = null;

		if (nsrszsm != null && !nsrszsm.isEmpty()) {
			try {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					Iterator it = nsrszsm.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_NSRSZSM (SMBM,SZBM,SMMC,SMJC,SL,NSRWJBM) values('"
								+ (String) hm.get("smbm")
								+ "','"
								+ (String) hm.get("szbm")
								+ "','"
								+ Util.gbkto8859((String) hm.get("smmc"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("smjc"))
								+ "',"
								+ (Double) hm.get("sl")
								+ ",'"
								+ nsrwjbm
								+ "')";
						stsm.addBatch(sql1);
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
		}
		return result1;
	}

	/**
	 * @param nsrwjbm
	 * @return int
	 * @roseuid 4CA0B79F029F
	 */
	public int deleteSzsm(String nsrwjbm) {
		return 0;
	}

	/**
	 * @param nsrwjbm
	 * @return jsdt.model.Nsrxx
	 * @roseuid 4CA0B86C01E4
	 */
	public Nsrxx selectNsr(String nsrwjbm) {
		return null;
	}

	/**
	 * @param nsrwjbm
	 * @return jsdt.model.Nsrxx
	 * @roseuid 4CA0B88B00AB
	 */
	public ArrayList selectNsrSzsm(String nsrwjbm) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.FPBM from SKQ_NSRSZSM a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM  where a.NSRWJBM='"
						+ nsrwjbm + "' and a.STATUS = 1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();

					hm.put("szbm", rs.getString("SZBM"));
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					hm.put("smjc", Util.iso8859togbk(rs.getString("SMJC")));
					hm.put("sl", rs.getDouble("SL"));
					hm.put("sid", rs.getInt("SID"));
					hm.put("fpbm", rs.getString("FPBM"));

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

	public String selectNsrSzsmStr(String nsrwjbm) {
		String str = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select SMBM from SKQ_NSRSZSM where STATUS = 1 and NSRWJBM='"
						+ nsrwjbm + "' order by smbm";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					str = str + rs.getString("SMBM") + ",";
				}
				rs.close();
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
		return str;
	}
	
	
 
	public String getJqbh(String nsrwjbm){
	String jqbh=Query.getFieldStr("select top 1 jqbh from skq_jqxx where nsrwjbm='"+nsrwjbm+"'","jqbh");
	return jqbh;
	}
	public ArrayList<String> findAllNsr(String nsrwjbm) throws UnsupportedEncodingException{
		 ArrayList<String> alist = new ArrayList<String>();
		nsrwjbm=new String(nsrwjbm.getBytes("ISO-8859-1"),"UTF-8");
		Connection conn = null;
		 String NSRMC=null;
		 String NSRWJBM=null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_NSRXX where STATUS = 1 and nsrwjbm like '%"+nsrwjbm+"%' or nsrmc like '%"+nsrwjbm+"%'";
				System.out.println(sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					NSRMC=rs.getString("NSRMC");
					NSRWJBM=rs.getString("NSRWJBM");  
					alist.add(NSRMC+" "+NSRWJBM);
					  if(alist.size()==3){
						  break;
					  }
				}
//				for(String s:alist){
//					System.out.println(s);
//				}
				rs.close();
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
		return alist;
	}

	/**
	 * @param nsrwjbm
	 * @return jsdt.model.Nsrxx
	 * @roseuid 4CA0B8B0031C
	 */
	public Nsrxx selectNsrJqxx(String nsrwjbm) {
		return null;
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

	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getJydz() {
		return jydz;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public String getFrdb() {
		return frdb;
	}

	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}

	public String getZclxbm() {
		return zclxbm;
	}

	public void setZclxbm(String zclxbm) {
		this.zclxbm = zclxbm;
	}

	public String getHybm() {
		return hybm;
	}

	public void setHybm(String hybm) {
		this.hybm = hybm;
	}

	public String getHymxbm() {
		return hymxbm;
	}

	public void setHymxbm(String hymxbm) {
		this.hymxbm = hymxbm;
	}

	public int getZsfs() {
		return zsfs;
	}

	public void setZsfs(int zsfs) {
		this.zsfs = zsfs;
	}

	public double getYhde() {
		return yhde;
	}

	public void setYhde(double yhde) {
		this.yhde = yhde;
	}

	public String getSwjgbm() {
		return swjgbm;
	}

	public void setSwjgbm(String swjgbm) {
		this.swjgbm = swjgbm;
	}

	public String getBsy() {
		return bsy;
	}

	public void setBsy(String bsy) {
		this.bsy = bsy;
	}

	public String getSsgly() {
		return ssgly;
	}

	public void setSsgly(String ssgly) {
		this.ssgly = ssgly;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList getNsrszsm() {
		return nsrszsm;
	}

	public void setNsrszsm(ArrayList nsrszsm) {
		this.nsrszsm = nsrszsm;
	}

	public ArrayList getNsrjqxx() {
		return nsrjqxx;
	}

	public void setNsrjqxx(ArrayList nsrjqxx) {
		this.nsrjqxx = nsrjqxx;
	}

	public String getQysmbmStr() {
		return qysmbmStr;
	}

	public void setQysmbmStr(String qysmbmStr) {
		System.out.println("qysmbmStr333="+qysmbmStr);
		this.qysmbmStr = qysmbmStr;
		System.out.println("this.qysmbmStr="+this.qysmbmStr);
	}

	public String getZclxmc() {
		return zclxmc;
	}

	public void setZclxmc(String zclxmc) {
		this.zclxmc = zclxmc;
	}

	public String getHymc() {
		return hymc;
	}

	public void setHymc(String hymc) {
		this.hymc = hymc;
	}

	public String getHymxmc() {
		return hymxmc;
	}

	public void setHymxmc(String hymxmc) {
		this.hymxmc = hymxmc;
	}

	public String getSwjgmc() {
		return swjgmc;
	}

	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}
	public String toString(Nsrxx nsrxx){
		String nsrxxStr="";
		ArrayList jqxxList=nsrxx.getNsrjqxx();
		ArrayList szsmList=nsrxx.getNsrszsm();
		String jqxxStr="";
		for(int i=0;i<jqxxList.size();i++){
			Jqxx jqxx=(Jqxx)jqxxList.get(i);
			if(i==0){
				jqxxStr=jqxx.getJqbh();
			}else{
				jqxxStr=jqxxStr+"~"+jqxx.getJqbh();
			}
		}
		String szsmStr="";
		for(int i=0;i<szsmList.size();i++){
			HashMap szsm=(HashMap)szsmList.get(i);
			if(i==0){
				szsmStr=(String)szsm.get("smbm")+","+(String)szsm.get("smmc");
			}else{
				szsmStr=szsmStr+"~"+(String)szsm.get("smbm")+","+(String)szsm.get("smmc");
			}
		}
		 
		
		nsrxxStr="法人代表："+nsrxx.getFrdb()+",行业编码："+nsrxx.getHybm()+",行业名称："+nsrxx.getHymc()
		+",行业明细编码："+nsrxx.getHymxbm()+",行业明细名称："+nsrxx.getHymxmc()+",经营地址："+nsrxx.getJydz()
		+",纳税人信息："+nsrxx.getNsrmc()+",纳税人识别号："+nsrxx.getNsrsbh()+"纳税人微机编码："+nsrxx.getNsrwjbm()+
	",启用税种税目编码Str:"+nsrxx.getQysmbmStr()+",税务机关编码："+nsrxx.getSwjgbm()
		+",税务机关名称："+nsrxx.getSwjgmc()+",月核定额："+nsrxx.getYhde()+",注册类型编码："+nsrxx.getZclxbm()+",注册类型名称："+nsrxx.getZclxmc()+",征收方式："+nsrxx.getZsfs()
		+",机器信息："+jqxxStr+",税种税目信息："+szsmStr;
		;
		return nsrxxStr;
	}

}
