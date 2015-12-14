package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class Jqxh extends baseClass {
	private int sid;
	private String jqxhbm;
	private String jqxhmc;
	private String sccs;
	private int status;

	/**
	 * @roseuid 4CA19C4802DE
	 */
	public Jqxh() {

	}

	/**
	 * @param fpbm
	 * @roseuid 4CA05B57008C
	 */
	public Jqxh(String jqxhbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_JQXH where JQXHBM='" + jqxhbm
						+ "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.jqxhbm = jqxhbm;
					this.jqxhmc = Util.iso8859togbk(rs.getString("JQXHMC"));
					this.sccs = Util.iso8859togbk(rs.getString("SCCS"));
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
	 * @roseuid 4CA05BA90128
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_JQXH set JQXHMC='" + Util.gbkto8859(this.jqxhmc)
						+ "',SCCS='" + Util.gbkto8859(this.sccs) + "',STATUS=" + this.status
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
	 * @param fpmc
	 * @return int
	 * @roseuid 4CA05BAF01E4
	 */
	public int update(int sid, String jqxhmc, String sccs, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_JQXH set JQXHMC='" + Util.gbkto8859(jqxhmc)
						+ "',SCCS='" + Util.gbkto8859(sccs) + "',STATUS=" + status
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
	 * @roseuid 4CA05BC8007D
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_JQXH (JQXHBM,JQXHMC,SCCS,STATUS) values(?,?,?,?)");
				ps.setString(1, this.jqxhbm);
				ps.setString(2, Util.gbkto8859(this.jqxhmc));
				ps.setString(3, Util.gbkto8859(this.sccs));
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
	 * @param fpbm
	 * @param fpmc
	 * @param status
	 * @return int
	 * @roseuid 4CA05BCA02DE
	 */
	public int add(String jqxhbm, String jqxhmc, String sccs, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_JQXH (JQXHBM,JQXHMC,SCCS,STATUS) values(?,?,?,?)");
				ps.setString(1, jqxhbm);
				ps.setString(2, Util.gbkto8859(jqxhmc));
				ps.setString(2, Util.gbkto8859(sccs));
				ps.setInt(3, status);
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
	 * @roseuid 4CA05C450261
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_JQXH";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Jqxh jqxh = new Jqxh();
					jqxh.setJqxhbm(rs.getString("JQXHBM"));
					jqxh.setJqxhmc(Util.iso8859togbk(rs.getString("JQXHMC")));
					jqxh.setSccs(Util.iso8859togbk(rs.getString("SCCS")));
					jqxh.setSid(rs.getInt("SID"));
					jqxh.setStatus(rs.getInt("STATUS"));

					al.add(jqxh);
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
	
	public ArrayList selectQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_JQXH where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Jqxh jqxh = new Jqxh();
					jqxh.setJqxhbm(rs.getString("JQXHBM"));
					jqxh.setJqxhmc(Util.iso8859togbk(rs.getString("JQXHMC")));
					jqxh.setSccs(Util.iso8859togbk(rs.getString("SCCS")));
					jqxh.setSid(rs.getInt("SID"));
					jqxh.setStatus(rs.getInt("STATUS"));

					al.add(jqxh);
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

	public String getJqxhbm() {
		return jqxhbm;
	}

	public void setJqxhbm(String jqxhbm) {
		this.jqxhbm = jqxhbm;
	}

	public String getJqxhmc() {
		return jqxhmc;
	}

	public void setJqxhmc(String jqxhmc) {
		this.jqxhmc = jqxhmc;
	}

	public String getSccs() {
		return sccs;
	}

	public void setSccs(String sccs) {
		this.sccs = sccs;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
