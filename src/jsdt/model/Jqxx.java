package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import jsdt.tools.DBConnection;
import jsdt.tools.Query;
import jsdt.tools.SYSTEM;
import jsdt.tools.Util;

public class Jqxx extends baseClass {
	private int sid;
	private String nsrwjbm;
	private String nsrmc;
	private String jqxhbm;
	private String jqxhmc;
	private String jqbh;
	private String skkh;
	private String yhkh;
	private String kqyrq;
	private String kyxrq;
	private String sbfs;
	private String mxsbbz;
	private String kpjzrq;
	private double dzkpxe;
	private double yljkpxe;
	private double yljtpxe;
	private int status;
	private ArrayList jqszsm;
	private String jqszsmStr;
	private String fpbm;
	private int qybz;
	private int lxkpzs;
	private int lxkpts;
	private int lxkpzje;
	private int xgsmmc;
	public int getXgsmmc() {
		return xgsmmc;
	}

	public void setXgsmmc(int xgsmmc) {
		this.xgsmmc = xgsmmc;
	}

	public int getLxkpzs() {
		return lxkpzs;
	}

	public void setLxkpzs(int lxkpzs) {
		this.lxkpzs = lxkpzs;
	}

	public int getLxkpts() {
		return lxkpts;
	}

	public void setLxkpts(int lxkpts) {
		this.lxkpts = lxkpts;
	}

 
	 

	public int getLxkpzje() {
		return lxkpzje;
	}

	public void setLxkpzje(int lxkpzje) {
		this.lxkpzje = lxkpzje;
	}
	/**
	 * @roseuid 4CA19C500271
	 */
	public Jqxx() {

	}

	/**
	 * @param jqbh
	 * @roseuid 4CA0B9EF0290
	 */
	public Jqxx(String jqbh) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.JQXHMC,c.QYBZ from SKQ_JQXX a left outer join SKQ_JQXH b on a.JQXHBM=b.JQXHBM left outer join SKQ_DHDE c on a.NSRWJBM=c.NSRWJBM and a.JQBH=c.JQBH where a.JQBH='"
						+ jqbh + "' and a.STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.nsrwjbm = rs.getString("NSRWJBM");
					this.jqxhbm = rs.getString("JQXHBM");
					this.jqxhmc = rs.getString("JQXHMC");
					this.jqbh = rs.getString("JQBH");
					this.skkh = rs.getString("SKKH");
					this.yhkh = rs.getString("YHKH");
					this.kqyrq = rs.getString("KQYRQ").substring(0,10);
					this.kyxrq = rs.getString("KYXRQ").substring(0,10);
					this.sbfs = rs.getString("SBFS");
					this.mxsbbz = rs.getString("MXSBBZ");
					this.kpjzrq = rs.getString("KPJZRQ").substring(0,10);
					this.dzkpxe = rs.getDouble("DZKPXE");
					this.yljkpxe = rs.getDouble("YLJKPXE");
					this.yljtpxe = rs.getDouble("YLJTPXE");
					this.status = rs.getInt("STATUS");
					this.qybz = rs.getInt("QYBZ");
					this.xgsmmc=rs.getInt("XGSMMC");
					this.lxkpzje=rs.getInt("lxkpzje");
					this.lxkpts=rs.getInt("lxkpsj");
					this.lxkpzs=rs.getInt("lxkpzs");
				}
				rs.close();
				ps.close();

				// 机器对应税种税目
				ArrayList jqszsm = new ArrayList();

				String sql1 = "select a.*,b.SZBM,b.SMMC,b.SMJC,b.SL,b.SMSY,b.FPBM from SKQ_JQSZSM a left outer join SKQ_PMSZ b on a.SMBM=b.SMBM where a.JQBH='"
						+ jqbh + "'"+"  order by  a.sid ";
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				String jqszsmStr = "";
				String fpbm = "";
				while (rs.next()) {
					HashMap hm = new HashMap();

					hm.put("szbm", rs.getString("SZBM"));
					hm.put("smbm", rs.getString("SMBM"));
					hm.put("smmc", Util.iso8859togbk(rs.getString("SMMC")));
					hm.put("smjc", Util.iso8859togbk(rs.getString("SMJC")));
					hm.put("smsy", rs.getInt("SMSY"));
					hm.put("sl", rs.getDouble("SL"));
					hm.put("fpbm", rs.getString("FPBM"));

					fpbm = rs.getString("FPBM");
					if ("".equals(jqszsmStr)) {
						jqszsmStr = rs.getString("SMBM");
					} else {
						jqszsmStr = jqszsmStr + "," + rs.getString("SMBM");
					}

					jqszsm.add(hm);
				}
				rs.close();
				ps.close();
				this.fpbm = fpbm;
				this.jqszsmStr = jqszsmStr;
				this.jqszsm = jqszsm;

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
	 * @roseuid 4CA0BA06031C
	 */
	public int add() {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "insert into SKQ_JQXX(NSRWJBM,JQXHBM,JQBH,SKKH,YHKH,KQYRQ,KYXRQ,SBFS,MXSBBZ,KPJZRQ,DZKPXE,YLJKPXE,YLJTPXE,STATUS,LXKPSJ,LXKPZS,LXKPZJE,XGSMMC) values('"
						+ this.nsrwjbm
						+ "','"
						+ this.jqxhbm
						+ "','"
						+ this.jqbh
						+ "','"
						+ this.skkh
						+ "','"
						+ this.yhkh
						+ "','"
						+ this.kqyrq
						+ "','"
						+ this.kyxrq
						+ "','"
						+ this.sbfs
						+ "','"
						+ this.mxsbbz
						+ "','"
						+ this.kpjzrq
						+ "',"
						+ this.dzkpxe
						+ ","
						+ this.yljkpxe
						+ ","
						+ this.yljtpxe 
						+ ","
						+ this.status 
						+","
						+this.lxkpts
						+","
						+this.lxkpzs
						+","
						+this.lxkpzje+
						","
						+this.xgsmmc+
						")";

				stsm.addBatch(sql);
				String jqszsmArr[] = this.jqszsmStr.split(",");
				for (int i = 0; i < jqszsmArr.length; i++) {
					String sql1 = "insert into SKQ_JQSZSM (SMBM,JQBH) values('"
							+ jqszsmArr[i] + "','" + this.jqbh + "')";
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result1;
	}

	/**
	 * @param nsrwjbm
	 * @param jqxhbm
	 * @param jqbh
	 * @param skkh
	 * @param yhkh
	 * @param kqyrq
	 * @param kyxrq
	 * @param sbfs
	 * @param mxsbbz
	 * @param kpjzrq
	 * @param dzkpxe
	 * @param yljkpxe
	 * @param yljtpxe
	 * @return int
	 * @roseuid 4CA0BA080251
	 */
	public int add(String nsrwjbm, String jqxhbm, String jqbh, String skkh,
			String yhkh, long kqyrq, long kyxrq, String sbfs, String mxsbbz,
			long kpjzrq, double dzkpxe, double yljkpxe, double yljtpxe,
			int status) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "insert into SKQ_JQXX(NSRWJBM,JQXHBM,JQBH,SKKH,YHKH,KQYRQ,KYXRQ,SBFS,MXSBBZ,KPJZRQ,DZKPXE,YLJKPXE,YLJTPXE,STATUS) values('"
						+ nsrwjbm
						+ "','"
						+ jqxhbm
						+ "','"
						+ jqbh
						+ "','"
						+ skkh
						+ "','"
						+ yhkh
						+ "','"
						+ kqyrq
						+ "','"
						+ kyxrq
						+ "','"
						+ sbfs
						+ "','"
						+ mxsbbz
						+ "','"
						+ kpjzrq
						+ "',"
						+ dzkpxe
						+ ","
						+ yljkpxe
						+ ","
						+ yljtpxe
						+ ","
						+ status
						+ ")";

				stsm.addBatch(sql);

				String jqszsmArr[] = this.jqszsmStr.split(",");
				for (int i = 0; i < jqszsmArr.length; i++) {
					String sql1 = "insert into SKQ_JQSZSM (SMBM,JQBH) values('"
							+ jqszsmArr[i] + "','" + this.jqbh + "')";
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result1;
	}
	/**
	 * @return int
	 * @roseuid 4CA0BBC600EA
	 */
	public int update(String oldJqbh) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

//				String sql = "update SKQ_JQXX set JQXHBM='" + this.jqxhbm
//						+ "',JQBH='" + this.jqbh + "',SKKH='" + this.skkh
//						+ "',YHKH='" + this.yhkh + "',KQYRQ='" + this.kqyrq
//						+ "',KYXRQ='" + this.kyxrq + "',SBFS='" + this.sbfs
//						+ "',MXSBBZ='" + this.mxsbbz + "',KPJZRQ='"
//						+ this.kpjzrq + "',DZKPXE=" + this.dzkpxe + ",YLJKPXE="
//						+ this.yljkpxe + ",YLJTPXE=" + this.yljtpxe
//						+ ",STATUS=" + this.status + " where JQBH='" + oldJqbh
//						+ "'";
				
				String sql = "update SKQ_JQXX set JQXHBM='"+this.jqxhbm+"',JQBH='"+this.jqbh+"',SKKH='"+this.skkh+"',YHKH='"+this.yhkh+"',KPJZRQ='"
						+ this.kpjzrq + "',DZKPXE=" + this.dzkpxe + ",YLJKPXE="
						+ this.yljkpxe + ",YLJTPXE=" + this.yljtpxe+",XGSMMC="+this.xgsmmc
						+",lxkpzje="+this.lxkpzje+",lxkpzs="+this.lxkpzs+",lxkpsj="+lxkpts
						+ " where JQBH='"+oldJqbh+"'";
				System.out.println("kaooo===="+sql);
				stsm.addBatch(sql);

				String sql2 = "delete from SKQ_JQSZSM where JQBH='" + oldJqbh
						+ "'";
				stsm.addBatch(sql2);

				String jqszsmArr[] = this.jqszsmStr.split(",");
				for (int i = 0; i < jqszsmArr.length; i++) {
					String sql1 = "insert into SKQ_JQSZSM (SMBM,JQBH) values('"
							+ jqszsmArr[i] + "','" + this.jqbh + "')";
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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result1;
	}
	
	public int updateDt(String oldJqbh) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

//				String sql = "update SKQ_JQXX set JQXHBM='" + this.jqxhbm
//						+ "',JQBH='" + this.jqbh + "',SKKH='" + this.skkh
//						+ "',YHKH='" + this.yhkh + "',KQYRQ='" + this.kqyrq
//						+ "',KYXRQ='" + this.kyxrq + "',SBFS='" + this.sbfs
//						+ "',MXSBBZ='" + this.mxsbbz + "',KPJZRQ='"
//						+ this.kpjzrq + "',DZKPXE=" + this.dzkpxe + ",YLJKPXE="
//						+ this.yljkpxe + ",YLJTPXE=" + this.yljtpxe
//						+ ",STATUS=" + this.status + " where JQBH='" + oldJqbh
//						+ "'";
				
				String sql = "update SKQ_JQXX set JQXHBM='"+this.jqxhbm+"',JQBH='"+this.jqbh+"',SKKH='"+this.skkh+"',YHKH='"+this.yhkh+"' where JQBH='"+oldJqbh+"'";
				stsm.addBatch(sql);

//				String sql2 = "delete from SKQ_JQSZSM where JQBH='" + oldJqbh
//						+ "'";
//				stsm.addBatch(sql2);
//
//				String jqszsmArr[] = this.jqszsmStr.split(",");
//				for (int i = 0; i < jqszsmArr.length; i++) {
//					String sql1 = "insert into SKQ_JQSZSM (SMBM,JQBH) values('"
//							+ jqszsmArr[i] + "','" + this.jqbh + "')";
//					stsm.addBatch(sql1);
//				}

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
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.rollback();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result1;
	}

	/**
	 * @param jqxhbm
	 * @param jqbh
	 * @param skkh
	 * @param yhkh
	 * @param kqyrq
	 * @param kyxrq
	 * @param sbfs
	 * @param mxsbbz
	 * @param kpjzrq
	 * @param dzkpxe
	 * @param yljkpxe
	 * @param yljtpxe
	 * @return int
	 * @roseuid 4CA0BBDF033C
	 */
	public int update(String jqxhbm, String jqbh, String skkh, String yhkh,
			String kqyrq, String kyxrq, String sbfs, String mxsbbz,
			String kpjzrq, double dzkpxe, double yljkpxe, double yljtpxe) {
		return 0;
	}

	/**
	 * @return int
	 * @roseuid 4CA0BCB80177
	 */
	public int addSzsm() {
		return 0;
	}

	/**
	 * @param jqbh
	 * @param jqszsm
	 * @return int
	 * @roseuid 4CA0BCBD030D
	 */
	public int addSzsm(String jqbh, ArrayList jqszsm) {
		return 0;
	}

	/**
	 * @param jqbh
	 * @return int
	 * @roseuid 4CA0BD110203
	 */
	public int deleteSzsm(String jqbh) {
		return 0;
	}

	/**
	 * @param jqbh
	 * @return ArrayList
	 * @roseuid 4CA0BD41001F
	 */
	public ArrayList selectJqszsm(String jqbh) {
		return null;
	}
	
	
	public ArrayList selectNsrFk() {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.NSRMC from SKQ_JQXX a left outer join SKQ_NSRXX b on a.NSRWJBM=b.NSRWJBM where substring(a.JQBH,5,16)='"+SYSTEM.CSHJQBH+"' or a.SKKH='"+SYSTEM.CSHSKKH+"' order by a.NSRWJBM";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Jqxx jqxx = new Jqxx();

					jqxx.setNsrwjbm(rs.getString("NSRWJBM"));
					jqxx.setNsrmc(Util.iso8859togbk(rs.getString("NSRMC")));
					jqxx.setJqbh(rs.getString("JQBH"));
					jqxx.setSkkh(rs.getString("SKKH"));
					jqxx.setYljkpxe(rs.getDouble("YLJKPXE"));
					jqxx.setYljtpxe(rs.getDouble("YLJTPXE"));
					jqxx.setKpjzrq(rs.getString("KPJZRQ").substring(0,10));
					
					al.add(jqxx);
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

	public String getJqxhbm() {
		return jqxhbm;
	}

	public void setJqxhbm(String jqxhbm) {
		this.jqxhbm = jqxhbm;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public String getSkkh() {
		return skkh;
	}

	public void setSkkh(String skkh) {
		this.skkh = skkh;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getSbfs() {
		return sbfs;
	}

	public void setSbfs(String sbfs) {
		this.sbfs = sbfs;
	}

	public String getMxsbbz() {
		return mxsbbz;
	}

	public void setMxsbbz(String mxsbbz) {
		this.mxsbbz = mxsbbz;
	}

	public String getKqyrq() {
		return kqyrq;
	}

	public void setKqyrq(String kqyrq) {
		this.kqyrq = kqyrq;
	}

	public String getKyxrq() {
		return kyxrq;
	}

	public void setKyxrq(String kyxrq) {
		this.kyxrq = kyxrq;
	}

	public String getKpjzrq() {
		return kpjzrq;
	}

	public void setKpjzrq(String kpjzrq) {
		this.kpjzrq = kpjzrq;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList getJqszsm() {
		return jqszsm;
	}

	public void setJqszsm(ArrayList jqszsm) {
		this.jqszsm = jqszsm;
	}

	public String getJqxhmc() {
		return jqxhmc;
	}

	public void setJqxhmc(String jqxhmc) {
		this.jqxhmc = jqxhmc;
	}

	public String getJqszsmStr() {
		return jqszsmStr;
	}

	public void setJqszsmStr(String jqszsmStr) {
		this.jqszsmStr = jqszsmStr;
	}

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public int getQybz() {
		return qybz;
	}

	public void setQybz(int qybz) {
		this.qybz = qybz;
	}

	 
	public String toString(Jqxx jqxx) {
		// TODO Auto-generated method stub
		String jqString="";
		jqString="发票编码："+jqxx.getFpbm()+",机器编号："+jqxx.getJqbh()+",机器税种税目str："+jqxx.getJqszsmStr()+",机器型号编码："+jqxx.getJqxhbm()
		+",机器型号名称："+jqxx.getJqxhmc()+",开票截止日期："+jqxx.getKpjzrq()+",卡启用日期："+jqxx.getKqyrq()+
		",卡有效日期："+jqxx.getKyxrq()+",纳税人微机编码："+jqxx.getNsrwjbm()+",税空卡号："+jqxx.getSkkh()
		+",用户卡号:"+jqxx.getYhkh()+"月累计开票限额："+jqxx.getYljkpxe()+",月累计退票限额："+jqxx.getYljtpxe()+",单张开票限额："+jqxx.dzkpxe;
		return jqString;
	}
	
}
