package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

import org.apache.log4j.Logger;

public class Sbsj extends baseClass {
	Logger log=Logger.getLogger(Sbsj.class);
	private int sid;
	private String sbrq;
	private String sskssj;
	private String ssjzsj;
	private int zcpfs;
	private int tpfs;
	private int fpfs;
	private double zcpzje;
	private double tpzje;
	private String nsrwjbm;
	private String nsrmc;
	private String jqbh;
	private int sblx;
	private ArrayList sbzbxx;
	private int year;
	private int month;
	private double skje;
	private String hzrq;

	private String kpjzsj;
	private double dzkpxe;
	private double yljkpxe;
	private double yljtpxe;
	private String cjrq;
	private int xzzt;
	private String xzrq;

	private String mac;

	private int sbzbnum;
    private int ysbSum;
	public int getYsbSum() {
		return ysbSum;
	}

	public void setYsbSum(int ysbSum) {
		this.ysbSum = ysbSum;
	}

	/**
	 * @roseuid 4CA19C4F001F
	 */
	public Sbsj() {

	}

	/**
	 * @param sid
	 * @roseuid 4CA0ACCA02DE
	 */
	public Sbsj(int sid) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_SBSJ where SID=" + sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.nsrwjbm = rs.getString("NSRWJBM");
					this.jqbh = rs.getString("JQBH");
					this.sbrq = rs.getString("SBRQ") == null ? "" : rs
							.getString("SBRQ").substring(0, 10);
					this.sskssj = rs.getString("SSKSSJ") == null ? "" : rs
							.getString("SSKSSJ").substring(0, 10);
					this.ssjzsj = rs.getString("SSJZSJ") == null ? "" : rs
							.getString("SSJZSJ").substring(0, 10);
					this.zcpfs = rs.getInt("ZCPFS");
					this.tpfs = rs.getInt("TPFS");
					this.fpfs = rs.getInt("FPFS");
					this.zcpzje = rs.getDouble("ZCPZJE");
					this.tpzje = rs.getDouble("TPZJE");
					this.sblx = rs.getInt("SBLX");
					this.kpjzsj = rs.getString("KPJZSJ") == null ? "" : rs
							.getString("KPJZSJ").substring(0, 10);
					this.dzkpxe = rs.getDouble("DZKPXE");
					this.yljkpxe = rs.getDouble("YLJKPXE");
					this.yljtpxe = rs.getDouble("YLJTPXE");
					this.cjrq = rs.getString("CJRQ") == null ? "" : rs
							.getString("CJRQ").substring(0, 10);
					this.xzzt = rs.getInt("XZZT");
					this.xzrq = rs.getString("XZRQ") == null ? "" : rs
							.getString("XZRQ").substring(0, 10);
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

	}

	public Sbsj(int sid, int flag) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HZSJ where SID=" + sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.nsrwjbm = rs.getString("NSRWJBM");
					this.hzrq = rs.getString("HZRQ") == null ? "" : rs
							.getString("HZRQ").substring(0, 10);
					this.year = rs.getInt("YEAR");
					this.month = rs.getInt("MONTH");
					this.zcpfs = rs.getInt("ZCPFS");
					this.tpfs = rs.getInt("TPFS");
					this.fpfs = rs.getInt("FPFS");
					this.zcpzje = rs.getDouble("ZCPZJE");
					this.tpzje = rs.getDouble("TPZJE");
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

	}

	/**
	 * @return int
	 * @roseuid 4CA0ACE3007D
	 */
	public int add() {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "insert into SKQ_SBSJ (NSRWJBM,JQBH,SBRQ,SSKSSJ,SSJZSJ,ZCPFS,TPFS,FPFS,ZCPZJE,TPZJE,SBLX) values('"
						+ this.nsrwjbm
						+ "','"
						+ this.jqbh
						+ "','"
						+ this.sbrq
						+ "','"
						+ this.sskssj
						+ "','"
						+ this.ssjzsj
						+ "',"
						+ this.zcpfs
						+ ","
						+ this.tpfs
						+ ","
						+ this.fpfs
						+ ","
						+ this.zcpzje
						+ ","
						+ this.tpzje
						+ ","
						+ this.sblx
						+ ")";
				stsm.addBatch(sql);
                log.info("申报数据："+sql);
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

	public int addZb(ArrayList sbzbxx) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				if (sbzbxx != null && !sbzbxx.isEmpty()) {
					Iterator it = sbzbxx.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String sql = "insert into SKQ_SBSJMX(PARENTID,SMBM,JE,KPLX) values("
								+ (Integer) hm.get("parentid")
								+ ",'"
								+ (String) hm.get("smbm")
								+ "',"
								+ (Double) hm.get("je")
								+ ","
								+ (Integer) hm.get("kplx") + ")";
						log.info("申报数据明细："+sql);
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
	 * @param nsrwjbm
	 * @param jqbh
	 * @param sskssj
	 * @param ssjzsj
	 * @param zcpfs
	 * @param tpfs
	 * @param fpfs
	 * @param zcpzje
	 * @param tpzje
	 * @param sblx
	 * @param sbzbxx
	 * @return int
	 * @roseuid 4CA0ACE502FD
	 */
	public int add(String nsrwjbm, String jqbh, String sbrq, String sskssj,
			String ssjzsj, int zcpfs, int tpfs, int fpfs, double zcpzje,
			double tpzje, int sblx, ArrayList sbzbxx) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_SBSJ (NSRWJBM,JQBH,SBRQ,SSKSSJ,SSJZSJ,ZCPFS,TPFS,FPFS,ZCPZJE,TPZJE,SBLX) values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, nsrwjbm);
				ps.setString(2, jqbh);
				ps.setString(3, sbrq);
				ps.setString(4, sskssj);
				ps.setString(5, ssjzsj);
				ps.setInt(6, zcpfs);
				ps.setInt(7, tpfs);
				ps.setInt(8, fpfs);
				ps.setDouble(9, zcpzje);
				ps.setDouble(10, tpzje);
				ps.setInt(11, sblx);
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

	public int updateJksj() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("update SKQ_SBSJ set KPJZSJ=?,DZKPXE=?,YLJKPXE=?,YLJTPXE=?,CJRQ=?,XZZT=?,XZRQ=?,MAC=? where SID=?");
				ps.setString(1, this.kpjzsj);
				ps.setDouble(2, this.dzkpxe);
				ps.setDouble(3, this.yljkpxe);
				ps.setDouble(4, this.yljtpxe);
				ps.setString(5, this.cjrq);
				ps.setInt(6, this.xzzt);
				ps.setString(7, this.xzrq);
				ps.setString(8, this.mac);
				ps.setInt(9, this.sid);
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
	 * @roseuid 4CA0AE750196
	 */
	public int addSbhz() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_HZSJ (NSRWJBM,HZRQ,YEAR,MONTH,ZCPFS,TPFS,FPFS,ZCPZJE,TPZJE,CLBZ) values(?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, this.nsrwjbm);
				ps.setString(2, this.hzrq);
				ps.setInt(3, this.year);
				ps.setInt(4, this.month);
				ps.setInt(5, this.zcpfs);
				ps.setInt(6, this.tpfs);
				ps.setInt(7, this.fpfs);
				ps.setDouble(8, this.zcpzje);
				ps.setDouble(9, this.tpzje);
				ps.setInt(10, 1);
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
	 * @param year
	 * @param month
	 * @param zcpfs
	 * @param tpfs
	 * @param fpfs
	 * @param zcpzje
	 * @param tpzje
	 * @param skje
	 * @param sbzbxx
	 * @return int
	 * @roseuid 4CA0AE7E0232
	 */
	public int addSbhz(String nsrwjbm, String hzrq, int year, int month,
			int zcpfs, int tpfs, int fpfs, double zcpzje, double tpzje,
			double skje, ArrayList sbzbxx) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_HZSJ (NSRWJBM,HZRQ,YEAR,MONTH,ZCPFS,TPFS,FPFS,ZCPZJE,TPZJE,SKJE) values(?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1, nsrwjbm);
				ps.setString(2, hzrq);
				ps.setInt(3, year);
				ps.setInt(4, month);
				ps.setInt(5, zcpfs);
				ps.setInt(6, tpfs);
				ps.setInt(7, fpfs);
				ps.setDouble(8, zcpzje);
				ps.setDouble(9, tpzje);
				ps.setDouble(10, skje);
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

	public int addSbhzzb(ArrayList alSbsjzb, int parentid) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				if (alSbsjzb != null && !alSbsjzb.isEmpty()) {
					Iterator it = alSbsjzb.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();
						String sql = "insert into SKQ_HZSJMX(PARENTID,SMBM,KPJE,DKJE,YHDYYE,FLAG) values("
								+ parentid
								+ ",'"
								+ (String) hm.get("smbm")
								+ "',"
								+ (Double) hm.get("je")
								+ ","
								+ 0
								+ ","
								+ (Double) hm.get("yhde") + ","+0+")";
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

	// 查询本月已申报机器数量
	public int selectSbNum(String startdate, String enddate, String nsrwjbm) {
		int num = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select count(SID) as num from SKQ_SBSJ where NSRWJBM='"
						+ nsrwjbm
						+ "' and SSKSSJ>='"
						+ startdate
						+ "' and SSJZSJ<='" + enddate + "' and SBLX=1";
				System.out.println("sql==" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					num = rs.getInt("num");
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
		return num;
	}

	// 查询上月阶段申报数据
	public ArrayList selectJdsb(String startdate, String enddate, String nsrwjbm) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_SBSJ where NSRWJBM='" + nsrwjbm
						+ "' and SSKSSJ>='" + startdate + "' and SSJZSJ<='"
						+ enddate + "' and SBLX=2";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();

					hm.put("sskssj", rs.getString("SSKSSJ") == null ? "" : rs
							.getString("SSKSSJ").substring(0, 10));
					hm.put("ssjzsj", rs.getString("SSJZSJ") == null ? "" : rs
							.getString("SSJZSJ").substring(0, 10));
					hm.put("zcpfs", rs.getInt("ZCPFS"));
					hm.put("tpfs", rs.getInt("TPFS"));
					hm.put("fpfs", rs.getInt("FPFS"));
					hm.put("zcpzje", rs.getDouble("ZCPZJE"));
					hm.put("tpzje", rs.getDouble("TPZJE"));
					hm.put("jqbh", rs.getString("JQBH"));

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

	// 查询上月所有申报数据(主表)
	public ArrayList selectAllSbsj(String startdate, String enddate,
			String nsrwjbm) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_SBSJ where NSRWJBM='" + nsrwjbm
						+ "' and SSKSSJ>='" + startdate + "' and SSKSSJ<='"
						+ enddate + "'";
				System.out.println("sql11=" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Sbsj sbsj = new Sbsj();
					sbsj.setSid(rs.getInt("SID"));
					sbsj.setSskssj(rs.getString("SSKSSJ") == null ? "" : rs
							.getString("SSKSSJ").substring(0, 10));
					sbsj.setSsjzsj(rs.getString("SSJZSJ") == null ? "" : rs
							.getString("SSJZSJ").substring(0, 10));
					sbsj.setZcpfs(rs.getInt("ZCPFS"));
					sbsj.setTpfs(rs.getInt("TPFS"));
					sbsj.setFpfs(rs.getInt("FPFS"));
					sbsj.setZcpzje(rs.getDouble("ZCPZJE"));
					sbsj.setTpzje(rs.getDouble("TPZJE"));
					sbsj.setJqbh(rs.getString("JQBH"));
					sbsj.setNsrwjbm(rs.getString("NSRWJBM"));

					al.add(sbsj);
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

	// 查询上月所有申报数据(子表)
	public ArrayList selectAllSbsjzb(String sidStr) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select sum(a.JE) as JE,a.SMBM,b.SL,b.SMMC from SKQ_SBSJMX a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.PARENTID in("
						+ sidStr + ") group by a.SMBM,b.SL,b.SMMC";
				System.out.println("sql==" + sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("je", rs.getDouble("JE"));
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					hm.put("sl", rs.getDouble("SL"));
					DecimalFormat dg = new DecimalFormat("0.00");
					double sk = Double.parseDouble(dg.format(rs.getDouble("JE")
							* rs.getDouble("SL") / 100));
					hm.put("sk", sk);
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
	
	
	public double getZgYhdeSzsm(String nsrwjbm, String smbm, String yf) {
		double yhde = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select b.JSYJ from HD_HDZB a left outer join HD_HDMX b on a.WDH=b.WDH where a.QY_ID='"
						+ nsrwjbm
						+ "' and b.SMBM='"
						+ smbm
						+ "' and '"
						+ yf
						+ "'<=a.HDQZ and '" + yf + "' >=a.HDQQ and a.TZBS='1'";
				
				System.out.println("szsm==sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					yhde = rs.getDouble("JSYJ");
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
		return yhde;
	}
	//查询本月未申报数据
	public int selectWsb(String forSBSJ){
		Connection conn = null;
		int wsbsj = -1;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				 String sql="select top 1 SID from SKQ_SBSJ where 1=1 "+forSBSJ;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					wsbsj=rs.getInt("SID");
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
		 return wsbsj;
	}
	//查询本月未申报明细
	public int selectWsbMX(String forFPKJ){
		Connection conn = null;
		int wsbMX = -1;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				// System.out.println(System.currentTimeMillis());
				 String sql="select top 1 SID from SKQ_FPKJ where 1=1 "+forFPKJ+" ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					wsbMX=rs.getInt("SID");
				}
				rs.close();
				ps.close();
				//System.out.println(System.currentTimeMillis());
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
		 return wsbMX;
	}
	//查询本月未申报日交易
	public int selectWsbRJY(String forRJY){
		Connection conn = null;
		int wsbRJY = -1;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				 String sql="select TOP 1 SID from SKQ_RJYMX where 1=1 "+forRJY;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					wsbRJY=rs.getInt("SID");
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
		 return wsbRJY;
	}


	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSbrq() {
		return sbrq;
	}

	public void setSbrq(String sbrq) {
		this.sbrq = sbrq;
	}

	public String getSskssj() {
		return sskssj;
	}

	public void setSskssj(String sskssj) {
		this.sskssj = sskssj;
	}

	public String getSsjzsj() {
		return ssjzsj;
	}

	public void setSsjzsj(String ssjzsj) {
		this.ssjzsj = ssjzsj;
	}

	public int getZcpfs() {
		return zcpfs;
	}

	public void setZcpfs(int zcpfs) {
		this.zcpfs = zcpfs;
	}

	public int getTpfs() {
		return tpfs;
	}

	public void setTpfs(int tpfs) {
		this.tpfs = tpfs;
	}

	public int getFpfs() {
		return fpfs;
	}

	public void setFpfs(int fpfs) {
		this.fpfs = fpfs;
	}

	public double getZcpzje() {
		return zcpzje;
	}

	public void setZcpzje(double zcpzje) {
		this.zcpzje = zcpzje;
	}

	public double getTpzje() {
		return tpzje;
	}

	public void setTpzje(double tpzje) {
		this.tpzje = tpzje;
	}

	public String getNsrwjbm() {
		return nsrwjbm;
	}

	public void setNsrwjbm(String nsrwjbm) {
		this.nsrwjbm = nsrwjbm;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public int getSblx() {
		return sblx;
	}

	public void setSblx(int sblx) {
		this.sblx = sblx;
	}

	public ArrayList getSbzbxx() {
		return sbzbxx;
	}

	public void setSbzbxx(ArrayList sbzbxx) {
		this.sbzbxx = sbzbxx;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getSkje() {
		return skje;
	}

	public void setSkje(double skje) {
		this.skje = skje;
	}

	public String getHzrq() {
		return hzrq;
	}

	public void setHzrq(String hzrq) {
		this.hzrq = hzrq;
	}

	public String getKpjzsj() {
		return kpjzsj;
	}

	public void setKpjzsj(String kpjzsj) {
		this.kpjzsj = kpjzsj;
	}

	public double getDzkpxe() {
		return dzkpxe;
	}

	public void setDzkpxe(double dzkpxe) {
		this.dzkpxe = dzkpxe;
	}

	public double getYljkpxe() {
		return yljkpxe;
	}

	public void setYljkpxe(double yljkpxe) {
		this.yljkpxe = yljkpxe;
	}

	public double getYljtpxe() {
		return yljtpxe;
	}

	public void setYljtpxe(double yljtpxe) {
		this.yljtpxe = yljtpxe;
	}

	public String getCjrq() {
		return cjrq;
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public int getXzzt() {
		return xzzt;
	}

	public void setXzzt(int xzzt) {
		this.xzzt = xzzt;
	}

	public String getXzrq() {
		return xzrq;
	}

	public void setXzrq(String xzrq) {
		this.xzrq = xzrq;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public int getSbzbnum() {
		return sbzbnum;
	}

	public void setSbzbnum(int sbzbnum) {
		this.sbzbnum = sbzbnum;
	}

}
