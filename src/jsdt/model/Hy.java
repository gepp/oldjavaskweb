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

public class Hy extends baseClass {
	private int sid;
	private String hybm;
	private String hymc;
	private String hyjc;
	private int status;
	private ArrayList alhymx;

	/**
	 * @roseuid 4CA19C4703B9
	 */
	public Hy() {

	}

	/**
	 * @param hybm
	 * @roseuid 4CA053CF00EA
	 */
	public Hy(String hybm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HY where HYBM='" + hybm
						+ "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.hybm = hybm;
					this.hymc = Util.iso8859togbk(rs.getString("HYMC"));
					this.hyjc = Util.iso8859togbk(rs.getString("HYJC"));
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
	 * @roseuid 4CA054E60196
	 */
	public int getFromZG() {
		int result1 = 1;
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select HYBM,HYMC,HYMCJC,QYBJ_HY from BM_HY where 1=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("HYBM", rs.getString("HYBM"));
					hm.put("HYMC", Util.iso8859togbk(rs.getString("HYMC")));
					hm.put("HYJC", Util.iso8859togbk(rs.getString("HYMCJC")));
					hm.put("STATUS", Integer.valueOf(rs.getInt("QYBJ_HY")));
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

					String sql = "delete from SKQ_HY";
					stsm.addBatch(sql);

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_HY (HYBM,HYMC,HYJC,STATUS) values('"
								+ (String) hm.get("HYBM")
								+ "','"
								+ Util.gbkto8859((String) hm.get("HYMC"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("HYJC"))
								+ "',"
								+ (Integer) hm.get("STATUS")
								+ ")";
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
	 * @roseuid 4CA055F1009C
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_HY set HYMC='" + Util.gbkto8859(this.hymc)
						+ "',HYJC='" + Util.gbkto8859(this.hyjc) + "',STATUS="
						+ this.status + " where SID="
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
	 * @param hymc
	 * @param hyjc
	 * @param status
	 * @return int
	 * @roseuid 4CA0564802DE
	 */
	public int update(String hymc, String hyjc, int status, int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_HY set HYMC='" + Util.gbkto8859(hymc)
						+ "',HYJC='" + Util.gbkto8859(hyjc) + "',STATUS="
						+ status + " where SID="
						+ sid;
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
	 * @roseuid 4CA0568A0186
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_HY (HYBM,HYMC,HYJC,STATUS) values(?,?,?,?)");
				ps.setString(1, this.hybm);
				ps.setString(2, Util.gbkto8859(this.hymc));
				ps.setString(3, Util.gbkto8859(this.hyjc));
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
	 * @param hybm
	 * @param hymc
	 * @param hyjc
	 * @param status
	 * @return int
	 * @roseuid 4CA056980128
	 */
	public int add(String hybm, String hymc, String hyjc, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_HY (HYBM,HYMC,HYJC,STATUS) values(?,?,?,?)");
				ps.setString(1, hybm);
				ps.setString(2, Util.gbkto8859(hymc));
				ps.setString(3, Util.gbkto8859(hyjc));
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
	 * @roseuid 4CA056E60261
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HY";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Hy hy = new Hy();
					hy.setHybm(rs.getString("HYBM"));
					hy.setHymc(Util.iso8859togbk(rs.getString("HYMC")));
					hy.setHyjc(Util.iso8859togbk(rs.getString("HYJC")));
					hy.setSid(rs.getInt("SID"));
					hy.setStatus(rs.getInt("STATUS"));

					al.add(hy);
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
	 * @roseuid 4CA057040157
	 */
	public ArrayList selectHymx(String hybm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HYMX where HYBM='"+hybm+"'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
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
	 * @return ArrayList
	 * @roseuid 4CA069150148
	 */
	public ArrayList selectQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_HY where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Hy hy = new Hy();
					hy.setHybm(rs.getString("HYBM"));
					hy.setHymc(Util.iso8859togbk(rs.getString("HYMC")));
					hy.setHyjc(Util.iso8859togbk(rs.getString("HYJC")));
					hy.setSid(rs.getInt("SID"));
					hy.setStatus(rs.getInt("STATUS"));

					al.add(hy);
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

	public String getHymc() {
		return hymc;
	}

	public void setHymc(String hymc) {
		this.hymc = hymc;
	}

	public String getHyjc() {
		return hyjc;
	}

	public void setHyjc(String hyjc) {
		this.hyjc = hyjc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList getAlhymx() {
		return alhymx;
	}

	public void setAlhymx(ArrayList alhymx) {
		this.alhymx = alhymx;
	}

}
