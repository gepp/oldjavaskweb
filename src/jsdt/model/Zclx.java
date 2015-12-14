package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class Zclx extends baseClass {
	private int sid;
	private String zclxbm;
	private String zclxmc;
	private String zclxjc;
	private int status;

	/**
	 * @roseuid 4CA19C45038A
	 */
	public Zclx() {

	}

	/**
	 * @param zclxbm
	 * @roseuid 4CA031D20373
	 */
	public Zclx(String zclxbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ZCLX where ZCLXBM='" + zclxbm
						+ "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.zclxbm = zclxbm;
					this.zclxmc = Util.iso8859togbk(rs.getString("ZCLXMC"));
					this.zclxjc = Util.iso8859togbk(rs.getString("ZCLXJC"));
					this.status = rs.getInt("STATUS");
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
	 * @roseuid 4CA0320301BD
	 */
	public int getFromZG() {
		int result1 = 1;
		Connection conn = null;
		String ZCLXBM = null;
		String ZCLXMC = null;
		String ZCLXJC = null;
		int STATUS = 0;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select JJXZBM,JJXZMC,JJXZJC,QYBJ_JJXZ from BM_JJXZ where 1=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					ZCLXBM = rs.getString("JJXZBM").trim();
					ZCLXMC = Util.iso8859togbk(rs.getString("JJXZMC")).trim();
					ZCLXJC = Util.iso8859togbk(rs.getString("JJXZJC")).trim();
					STATUS = Integer.parseInt(rs.getString("QYBJ_JJXZ"));

					HashMap hm = new HashMap();
					hm.put("ZCLXBM", ZCLXBM);
					hm.put("ZCLXMC", ZCLXMC);
					hm.put("ZCLXJC", ZCLXJC);
					hm.put("STATUS", Integer.valueOf(STATUS));
					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}
			//System.out.println("al=="+al);
			if (al != null && !al.isEmpty()) {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					String sql = "delete from SKQ_ZCLX";
					stsm.addBatch(sql);

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_ZCLX (ZCLXBM,ZCLXMC,ZCLXJC,STATUS) values('"
								+ (String) hm.get("ZCLXBM")
								+ "','"
								+ Util.gbkto8859((String) hm.get("ZCLXMC"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("ZCLXJC"))
								+ "',"
								+ (Integer) hm.get("STATUS") + ")";
						//System.out.print(sql1);
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		return result1;
	}

	/**
	 * @return int
	 * @roseuid 4CA0322701EC
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_ZCLX set ZCLXMC='" + Util.gbkto8859(this.zclxmc)
						+ "',ZCLXJC='" + Util.gbkto8859(this.zclxjc) + "',STATUS="
						+ this.status + " where SID=" + this.sid;
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
	 * @param zclxmc
	 * @param zclxjc
	 * @param status
	 * @return int
	 * @roseuid 4CA0322B0363
	 */
	public int update(String zclxmc, String zclxjc, int status, int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_ZCLX set ZCLXMC='" + Util.gbkto8859(zclxmc)
						+ "',ZCLXJC='" + Util.gbkto8859(zclxjc) + "',STATUS=" + status
						+ " where SID=" + sid;
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
	 * @return int
	 * @roseuid 4CA0340F0298
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_ZCLX (ZCLXBM,ZCLXMC,ZCLXJC,STATUS) values(?,?,?,?)");
				ps.setString(1, this.zclxbm);
				ps.setString(2, Util.gbkto8859(this.zclxmc));
				ps.setString(3, Util.gbkto8859(this.zclxjc));
				ps.setInt(4, this.status);
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
	 * @param zclxbm
	 * @param zclxmc
	 * @param zclxjc
	 * @param status
	 * @return int
	 * @roseuid 4CA034170269
	 */
	public int add(String zclxbm, String zclxmc, String zclxjc, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_ZCLX (ZCLXBM,ZCLXMC,ZCLXJC,STATUS) values(?,?,?,?)");
				ps.setString(1, zclxbm);
				ps.setString(2, Util.gbkto8859(zclxmc));
				ps.setString(3, Util.gbkto8859(zclxjc));
				ps.setInt(4, status);
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
	 * @return ArrayList
	 * @roseuid 4CA034690334
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ZCLX";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Zclx zclx = new Zclx();
					zclx.setZclxbm(rs.getString("ZCLXBM"));
					zclx.setZclxmc(Util.iso8859togbk(rs.getString("ZCLXMC")));
					zclx.setZclxjc(Util.iso8859togbk(rs.getString("ZCLXJC")));
					zclx.setSid(rs.getInt("SID"));
					zclx.setStatus(rs.getInt("STATUS"));

					al.add(zclx);
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
		return al;
	}

	/**
	 * @return ArrayList
	 * @roseuid 4CA068C402CE
	 */
	public ArrayList selectQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ZCLX where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Zclx zclx = new Zclx();
					zclx.setZclxbm(rs.getString("ZCLXBM"));
					zclx.setZclxmc(Util.iso8859togbk(rs.getString("ZCLXMC")));
					zclx.setZclxjc(Util.iso8859togbk(rs.getString("ZCLXJC")));
					zclx.setSid(rs.getInt("SID"));
					zclx.setStatus(rs.getInt("STATUS"));

					al.add(zclx);
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
		return al;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getZclxbm() {
		return zclxbm;
	}

	public void setZclxbm(String zclxbm) {
		this.zclxbm = zclxbm;
	}

	public String getZclxmc() {
		return zclxmc;
	}

	public void setZclxmc(String zclxmc) {
		this.zclxmc = zclxmc;
	}

	public String getZclxjc() {
		return zclxjc;
	}

	public void setZclxjc(String zclxjc) {
		this.zclxjc = zclxjc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
