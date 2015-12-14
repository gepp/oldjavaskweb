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

public class Fp extends baseClass {
	private int sid;
	private String fpbm;
	private String fpmc;
	private int status;

	/**
	 * @roseuid 4CA19C4802DE
	 */
	public Fp() {

	}

	/**
	 * @param fpbm
	 * @roseuid 4CA05B57008C
	 */
	public Fp(String fpbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FP where FPBM='" + fpbm + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.fpbm = fpbm;
					this.fpmc = Util.iso8859togbk(rs.getString("FPMC"));
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
	 * @roseuid 4CA05B6C007D
	 */
	public int getFromZG() {
		int result1 = 1;
		Connection conn = null;
		String FPBM = null;
		String FPMC = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select FPBM,FPMC from BM_FP where 1=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("FPBM", rs.getString("FPBM"));
					hm.put("FPMC", Util.iso8859togbk(rs.getString("FPMC")));
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

					String sql = "delete from SKQ_FP";
					stsm.addBatch(sql);

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_FP (FPBM,FPMC,STATUS) values('"
								+ (String) hm.get("FPBM")
								+ "','"
								+ Util.gbkto8859((String) hm.get("FPMC")) + "',1)";
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
	 * @roseuid 4CA05BA90128
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_FP set FPMC='" + Util.gbkto8859(this.fpmc)
						+ "',STATUS=" + this.status + " where SID=" + this.sid;
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
	public int update(int sid, String fpmc, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_FP set FPMC='" + Util.gbkto8859(fpmc) + "',STATUS="
						+ status + " where SID=" + sid;
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
						.prepareStatement("insert into SKQ_FP (FPBM,FPMC,STATUS) values(?,?,?)");
				ps.setString(1, this.fpbm);
				ps.setString(2, Util.gbkto8859(this.fpmc));
				ps.setInt(3, this.status);
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
	public int add(String fpbm, String fpmc, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_FP (FPBM,FPMC,STATUS) values(?,?,?)");
				ps.setString(1, fpbm);
				ps.setString(2, Util.gbkto8859(fpmc));
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
				String sql = "select * from SKQ_FP";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fp fp = new Fp();
					fp.setFpbm(rs.getString("FPBM"));
					fp.setFpmc(Util.iso8859togbk(rs.getString("FPMC")));
					fp.setSid(rs.getInt("SID"));
					fp.setStatus(rs.getInt("STATUS"));

					al.add(fp);
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
	 * @param fpbm
	 * @return ArrayList
	 * @roseuid 4CA05C8902BF
	 */
	public ArrayList selectFpbm(String fpbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FPBM where FPBM='" + fpbm + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Fpbm fp = new Fpbm();
					fp.setFpbm(fpbm);
					fp.setFpdm(rs.getString("FPDM"));

					al.add(fp);
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
	 * @roseuid 4CA06979035B
	 */
	public ArrayList selectQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_FP where STATUS=1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Fp fp = new Fp();
					fp.setFpbm(rs.getString("FPBM"));
					fp.setFpmc(Util.iso8859togbk(rs.getString("FPMC")));
					fp.setSid(rs.getInt("SID"));
					fp.setStatus(rs.getInt("STATUS"));

					al.add(fp);
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

	public String getFpbm() {
		return fpbm;
	}

	public void setFpbm(String fpbm) {
		this.fpbm = fpbm;
	}

	public String getFpmc() {
		return fpmc;
	}

	public void setFpmc(String fpmc) {
		this.fpmc = fpmc;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
