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

public class Hymx extends baseClass {
	private int sid;
	private String hybm;
	private String hymxbm;
	private String hymxmc;
	private String hymxjc;
	private int status;

	/**
	 * @roseuid 4CA19C480177
	 */
	public Hymx() {

	}

	/**
	 * @param hymxbm
	 * @roseuid 4CA057CA0157
	 */
	public Hymx(String hymxbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HYMX where HYMXBM='" + hymxbm
						+ "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.hymxbm = hymxbm;
					this.hybm = rs.getString("HYBM");
					this.hymxmc = Util.iso8859togbk(rs.getString("HYMXMC"));
					this.hymxjc = Util.iso8859togbk(rs.getString("HYMXJC"));
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
	 * @roseuid 4CA057E80157
	 */
	public int getFromZG(String hybm) {
		int result1 = 1;
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select HYMX,HYMXMC,HYMXJC,QYBJ_HYMXBM from BM_HYMX where HYBM='"
						+ hybm + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("HYBM", hybm);
					hm.put("HYMXBM", rs.getString("HYMX"));
					hm.put("HYMXMC", Util.iso8859togbk(rs.getString("HYMXMC")));
					hm.put("HYMXJC", Util.iso8859togbk(rs.getString("HYMXJC")));
					hm.put("STATUS", Integer.valueOf(rs.getInt("QYBJ_HYMXBM")));
					al.add(hm);
				}
				rs.close();
				ps.close();
				conn.close();
			}
			
			if (al != null && !al.isEmpty()) {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					String sql = "delete from SKQ_HYMX where HYBM='" + hybm
							+ "'";
					stsm.addBatch(sql);

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_HYMX (HYBM,HYMXBM,HYMXMC,HYMXJC,STATUS) values('"
								+ (String) hm.get("HYBM")
								+ "','"
								+ (String) hm.get("HYMXBM")
								+ "','"
								+ Util.gbkto8859((String) hm.get("HYMXMC"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("HYMXJC"))
								+ "',"
								+ (Integer) hm.get("STATUS") + ")";
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
	 * @roseuid 4CA058390109
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_HYMX set HYMXMC='" + Util.gbkto8859(this.hymxmc)
						+ "',HYMXJC='" + Util.gbkto8859(this.hymxjc) + "',STATUS="
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
	 * @param hymxmc
	 * @param hymxjc
	 * @param status
	 * @return int
	 * @roseuid 4CA0580A0271
	 */
	public int update(String hymxmc, String hymxjc, int status, int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_HYMX set HYMXMC='" + Util.gbkto8859(hymxmc)
						+ "',HYMXJC='" + Util.gbkto8859(hymxjc) + "',STATUS=" + this.status
						+ " where SID=" + this.sid;
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
	 * @roseuid 4CA0584603B9
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_HYMX (HYBM,HYMXBM,HYMXMC,HYMXJC,STATUS) values(?,?,?,?,?)");
				ps.setString(1, this.hybm);
				ps.setString(2, this.hymxbm);
				ps.setString(3, Util.gbkto8859(this.hymxmc));
				ps.setString(4, Util.gbkto8859(this.hymxjc));
				ps.setInt(5, this.status);
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
	 * @param hybm
	 * @param hymxbm
	 * @param hymxmc
	 * @param hymxjc
	 * @param status
	 * @return int
	 * @roseuid 4CA05879004E
	 */
	public int add(String hybm, String hymxbm, String hymxmc, String hymxjc,
			int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_HYMX (HYBM,HYMXBM,HYMXMC,HYMXJC,STATUS) values(?,?,?,?,?)");
				ps.setString(1, hybm);
				ps.setString(2, hymxbm);
				ps.setString(3, Util.gbkto8859(hymxmc));
				ps.setString(4, Util.gbkto8859(hymxjc));
				ps.setInt(5, status);
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
	 * @roseuid 4CA05912005D
	 */
	public ArrayList selectByHybm(String hybm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HYMX where HYBM='"+hybm+"'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Hymx hymx = new Hymx();
					hymx.setHybm(rs.getString("HYBM"));
					hymx.setHymxbm(rs.getString("HYMXBM"));
					hymx.setHymxmc(Util.iso8859togbk(rs.getString("HYMXMC")));
					hymx.setHymxjc(Util.iso8859togbk(rs.getString("HYMXJC")));
					hymx.setSid(rs.getInt("SID"));
					hymx.setStatus(rs.getInt("STATUS"));

					al.add(hymx);
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
	 * @param hybm
	 * @return ArrayList
	 * @roseuid 4CA0693C02FD
	 */
	public ArrayList selectByHybmQy(String hybm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HYMX where HYBM='"+hybm+"' and STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Hymx hymx = new Hymx();
					hymx.setHybm(rs.getString("HYBM"));
					hymx.setHymxbm(rs.getString("HYMXBM"));
					hymx.setHymxmc(Util.iso8859togbk(rs.getString("HYMXMC")));
					hymx.setHymxjc(Util.iso8859togbk(rs.getString("HYMXJC")));
					hymx.setSid(rs.getInt("SID"));
					hymx.setStatus(rs.getInt("STATUS"));

					al.add(hymx);
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

	public String getHymxmc() {
		return hymxmc;
	}

	public void setHymxmc(String hymxmc) {
		this.hymxmc = hymxmc;
	}

	public String getHymxjc() {
		return hymxjc;
	}

	public void setHymxjc(String hymxjc) {
		this.hymxjc = hymxjc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
