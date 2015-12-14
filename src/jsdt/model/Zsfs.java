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

public class Zsfs extends baseClass {
	private int sid;
	private String zsfsbm;
	private String zsfsmc;
	private String zsfsjc;
	private int status;
	private int sbbs;

	/**
	 * @roseuid 4CA19C46033C
	 */
	public Zsfs() {

	}

	/**
	 * @param zsfsbm
	 * @roseuid 4CA03A670150
	 */
	public Zsfs(String zsfsbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ZSFS where ZSFSBM='" + zsfsbm
						+ "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.zsfsbm = zsfsbm;
					this.zsfsmc = Util.iso8859togbk(rs.getString("ZSFSMC"));
					this.zsfsjc = Util.iso8859togbk(rs.getString("ZSFSJC"));
					this.status = rs.getInt("STATUS");
					this.sbbs = rs.getInt("SBBS");
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
	 * @roseuid 4CA03AF90036
	 */
	public int getFromZG() {
		int result1 = 1;
		Connection conn = null;
		String ZSFSBM = null;
		String ZSFSMC = null;
		String ZSFSJC = null;
		int STATUS = 0;
		int SBBS = 0;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select ZSFSBM,ZSFSMC,ZSFSJC,QYBJ_ZSFS,SDBJ from BM_ZSFS where 1=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("ZSFSBM", rs.getString("ZSFSBM"));
					hm.put("ZSFSMC", Util.iso8859togbk(rs.getString("ZSFSMC")));
					hm.put("ZSFSJC", Util.iso8859togbk(rs.getString("ZSFSJC")));
					hm.put("STATUS", rs.getInt("QYBJ_ZSFS"));
					hm.put("SBBS", rs.getInt("SDBJ"));
					al.add(hm);
				}
				rs.close();
				ps.close();
			}
			conn.close();
			if (al != null && !al.isEmpty()) {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					String sql = "delete from SKQ_ZSFS";
					stsm.addBatch(sql);

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_ZSFS (ZSFSBM,ZSFSMC,ZSFSJC,STATUS,SBBS) values('"
								+ (String) hm.get("ZSFSBM")
								+ "','"
								+ Util.gbkto8859((String) hm.get("ZSFSMC"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("ZSFSJC"))
								+ "',"
								+ (Integer) hm.get("STATUS")
								+ ","
								+ (Integer) hm.get("SBBS") + ")";
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
	 * @roseuid 4CA03B11017F
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_ZSFS set ZSFSMC='" + Util.gbkto8859(this.zsfsmc)
						+ "',ZSFSJC='" + Util.gbkto8859(this.zsfsjc) + "',STATUS="
						+ this.status + ",SBBS=" + this.sbbs + " where SID="
						+ this.sid;
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
	 * @param zsfsmc
	 * @param zsfsjc
	 * @param status
	 * @param sbbs
	 * @return int
	 * @roseuid 4CA03B15016F
	 */
	public int update(String zsfsmc, String zsfsjc, int status, int sbbs,
			int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_ZSFS set ZSFSMC='" + Util.gbkto8859(zsfsmc)
						+ "',ZSFSJC='" + Util.gbkto8859(zsfsjc) + "',STATUS=" + status
						+ ",SBBS=" + sbbs + " where SID=" + sid;
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
	 * @roseuid 4CA03E7F0150
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_ZSFS (ZSFSBM,ZSFSMC,ZSFSJC,STATUS,SBBS) values(?,?,?,?,?)");
				ps.setString(1, this.zsfsbm);
				ps.setString(2, Util.gbkto8859(this.zsfsmc));
				ps.setString(3, Util.gbkto8859(this.zsfsjc));
				ps.setInt(4, this.status);
				ps.setInt(5, this.sbbs);
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
	 * @param zsfsbm
	 * @param zsfsmc
	 * @param zsfsjc
	 * @param status
	 * @param sbbs
	 * @return int
	 * @roseuid 4CA03E890363
	 */
	public int add(String zsfsbm, String zsfsmc, String zsfsjc, int status,
			int sbbs) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_ZSFS (ZSFSBM,ZSFSMC,ZSFSJC,STATUS,SBBS) values(?,?,?,?,?)");
				ps.setString(1, zsfsbm);
				ps.setString(2, Util.gbkto8859(zsfsmc));
				ps.setString(3, Util.gbkto8859(zsfsjc));
				ps.setInt(4, status);
				ps.setInt(5, sbbs);
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
	 * @roseuid 4CA03F590315
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ZSFS";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Zsfs zsfs = new Zsfs();
					zsfs.setZsfsbm(rs.getString("ZSFSBM"));
					zsfs.setZsfsmc(Util.iso8859togbk(rs.getString("ZSFSMC")));
					zsfs.setZsfsjc(Util.iso8859togbk(rs.getString("ZSFSJC")));
					zsfs.setSid(rs.getInt("SID"));
					zsfs.setStatus(rs.getInt("STATUS"));
					zsfs.setSbbs(rs.getInt("SBBS"));

					al.add(zsfs);
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
	 * @roseuid 4CA068D80399
	 */
	public ArrayList selectQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ZSFS where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Zsfs zsfs = new Zsfs();
					zsfs.setZsfsbm(rs.getString("ZSFSBM"));
					zsfs.setZsfsmc(Util.iso8859togbk(rs.getString("ZSFSMC")));
					zsfs.setZsfsjc(Util.iso8859togbk(rs.getString("ZSFSJC")));
					zsfs.setSid(rs.getInt("SID"));
					zsfs.setStatus(rs.getInt("STATUS"));
					zsfs.setSbbs(rs.getInt("SBBS"));

					al.add(zsfs);
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

	public String getZsfsbm() {
		return zsfsbm;
	}

	public void setZsfsbm(String zsfsbm) {
		this.zsfsbm = zsfsbm;
	}

	public String getZsfsmc() {
		return zsfsmc;
	}

	public void setZsfsmc(String zsfsmc) {
		this.zsfsmc = zsfsmc;
	}

	public String getZsfsjc() {
		return zsfsjc;
	}

	public void setZsfsjc(String zsfsjc) {
		this.zsfsjc = zsfsjc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSbbs() {
		return sbbs;
	}

	public void setSbbs(int sbbs) {
		this.sbbs = sbbs;
	}

}
