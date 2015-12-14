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

public class Swjg extends baseClass {
	private int sid;
	private String swjgbm;
	private String swjgmc;
	private String swjgjc;
	private String sjswjgbm;
	private String sjswjgmc;
	private int status;

	/**
	 * @roseuid 4CA19C470186
	 */
	public Swjg() {

	}

	/**
	 * @param swjgbm
	 * @roseuid 4CA046B30119
	 */
	public Swjg(String swjgbm) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_SWJG where SWJGBM='" + swjgbm
						+ "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.swjgbm = swjgbm;
					this.swjgmc = Util.iso8859togbk(rs.getString("SWJGMC"));
					this.swjgjc = Util.iso8859togbk(rs.getString("SWJGJC"));
					this.sjswjgbm = rs.getString("SJSWJGBM");
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
	 * @roseuid 4CA046E6035B
	 */
	public int getFromZG() {
		int result1 = 1;
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				al = new ArrayList();
				al = getFromZGDG(al, 0, "1", "", conn);
				conn.close();
			}
			
			if (al != null && !al.isEmpty()) {
				conn = DBConnection.getConnection();
				if (conn != null) {
					conn.setAutoCommit(false);
					Statement stsm = conn.createStatement();

					String sql = "delete from SKQ_SWJG";
					stsm.addBatch(sql);

					Iterator it = al.iterator();
					while (it.hasNext()) {
						HashMap hm = (HashMap) it.next();

						String sql1 = "insert into SKQ_SWJG (SWJGBM,SWJGMC,SWJGJC,SJSWJGBM,STATUS) values('"
								+ (String) hm.get("SWJGBM")
								+ "','"
								+ Util.gbkto8859((String) hm.get("SWJGMC"))
								+ "','"
								+ Util.gbkto8859((String) hm.get("SWJGJC"))
								+ "','"
								+ (String) hm.get("SJSWJGBM")
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

	public ArrayList getFromZGDG(ArrayList al, int cj, String SWSXJGX,
			String SJSWJGBM, Connection conn) {
		String sql = null;
		if (cj == 0) {
			sql = "select SWJGBM,SWJGMC,SWJGJC,QYBJ_SWJG,convert(varchar,substring(SWSXJGX,1,1)) as swsxjgx1 from BM_SWJG where convert(varchar,substring(SWSXJGX,1,1))='"
					+ SWSXJGX + "' and convert(varchar,substring(SWSXJGX,2,1))='0'";
		} else {
			sql = "select SWJGBM,SWJGMC,SWJGJC,QYBJ_SWJG,convert(varchar,substring(SWSXJGX,1,"
					+ (cj+1)
					+ ")) as swsxjgx1 from BM_SWJG where convert(varchar,substring(SWSXJGX,1,"
					+ cj
					+ "))='"
					+ SWSXJGX
					+ "' and convert(varchar,substring(SWSXJGX,"
					+ (cj + 1)
					+ ",1))!='0' and convert(varchar,substring(SWSXJGX,"
					+ (cj + 2)
					+ ",1))='0'";
		}

		PreparedStatement ps;
		try {
			System.out.println("sql=="+sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				HashMap hm = new HashMap();

				String SWJGBM = rs.getString("SWJGBM");
				String SWJGMC = Util.iso8859togbk(rs.getString("SWJGMC"));
				String SWJGJC = Util.iso8859togbk(rs.getString("SWJGJC"));
				int QYBJ_SWJG = Integer.valueOf(rs.getString("QYBJ_SWJG"));
				String NEW_SWSXJGX = rs.getString("swsxjgx1");

				hm.put("SWJGBM", SWJGBM);
				hm.put("SWJGMC", SWJGMC);
				hm.put("SWJGJC", SWJGJC);
				hm.put("SJSWJGBM", SJSWJGBM);
				hm.put("STATUS", QYBJ_SWJG);
				al.add(hm);

				getFromZGDG(al, (cj + 1), NEW_SWSXJGX, SWJGBM, conn);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * @return int
	 * @roseuid 4CA0470401B5
	 */
	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_SWJG set SWJGMC='" + Util.gbkto8859(this.swjgmc)
						+ "',SWJGJC='" + Util.gbkto8859(this.swjgjc) + "',SJSWJGBM='"
						+ this.sjswjgbm + "',STATUS=" + this.status
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
	 * @param swjgmc
	 * @param swjgjc
	 * @param sjswjgbm
	 * @param status
	 * @return int
	 * @roseuid 4CA047080271
	 */
	public int update(String swjgmc, String swjgjc, String sjswjgbm,
			int status, int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_SWJG set SWJGMC='" + Util.gbkto8859(swjgmc)
						+ "',SWJGJC='" + Util.gbkto8859(swjgjc) + "',SJSWJGBM='" + sjswjgbm
						+ "',STATUS=" + status + " where SID=" + sid;
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
	 * @roseuid 4CA04C2C01E4
	 */
	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_SWJG (SWJGBM,SWJGMC,SWJGJC,SJSWJGBM,STATUS) values(?,?,?,?,?)");
				ps.setString(1, this.swjgbm);
				ps.setString(2, Util.gbkto8859(this.swjgmc));
				ps.setString(3, Util.gbkto8859(this.swjgjc));
				ps.setString(4, this.sjswjgbm);
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
	 * @param swjgbm
	 * @param swjgmc
	 * @param swjgjc
	 * @param sjswjgbm
	 * @param status
	 * @return int
	 * @roseuid 4CA04C3503A9
	 */
	public int add(String swjgbm, String swjgmc, String swjgjc,
			String sjswjgbm, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_SWJG (SWJGBM,SWJGMC,SWJGJC,SJSWJGBM,STATUS) values(?,?,?,?,?)");
				ps.setString(1, swjgbm);
				ps.setString(2, Util.gbkto8859(swjgmc));
				ps.setString(3, Util.gbkto8859(swjgjc));
				ps.setString(4, sjswjgbm);
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
	 * @roseuid 4CA051470109
	 */
	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SWJGMC as SJSWJGMC from SKQ_SWJG a left outer join SKQ_SWJG b on a.SJSWJGBM=b.SWJGBM";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Swjg swjg = new Swjg();
					swjg.setSwjgbm(rs.getString("SWJGBM"));
					swjg.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC")));
					swjg.setSwjgjc(Util.iso8859togbk(rs.getString("SWJGJC")));
					swjg.setSid(rs.getInt("SID"));
					swjg.setStatus(rs.getInt("STATUS"));
					swjg.setSjswjgbm(rs.getString("SJSWJGBM"));
					swjg.setSjswjgmc(Util.iso8859togbk(rs.getString("SJSWJGMC")));

					al.add(swjg);
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
	 * @roseuid 4CA06901006D
	 */
	public ArrayList selectQy() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SWJGMC as SJSWJGMC from SKQ_SWJG a left outer join SKQ_SWJG b on a.SJSWJGBM=b.SWJGBM where a.STATUS = 1";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Swjg swjg = new Swjg();
					swjg.setSwjgbm(rs.getString("SWJGBM"));
					swjg.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC")));
					swjg.setSwjgjc(Util.iso8859togbk(rs.getString("SWJGJC")));
					swjg.setSid(rs.getInt("SID"));
					swjg.setStatus(rs.getInt("STATUS"));
					swjg.setSjswjgbm(rs.getString("SJSWJGBM"));
					swjg.setSjswjgmc(Util.iso8859togbk(rs.getString("SJSWJGMC")));

					al.add(swjg);
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

	public ArrayList selectNotInSwjgbm(String swjgbm) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_SWJG where STATUS = 1 and SWJGBM not in('"
						+ swjgbm + "')";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Swjg swjg = new Swjg();
					swjg.setSwjgbm(rs.getString("SWJGBM"));
					swjg.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC")));
					swjg.setSwjgjc(Util.iso8859togbk(rs.getString("SWJGJC")));
					swjg.setSid(rs.getInt("SID"));
					swjg.setStatus(rs.getInt("STATUS"));
					swjg.setSjswjgbm(rs.getString("SJSWJGBM"));

					al.add(swjg);
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
	
	
	public ArrayList selectQyBySwjgbmStr(String swjgbmStr) {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sqlStr = "";
				if(swjgbmStr!=null&&!"".equals(swjgbmStr)){
					sqlStr = " and a.SWJGBM in("+swjgbmStr+")";
				}
				
				String sql = "select a.*,b.SWJGMC as SJSWJGMC from SKQ_SWJG a left outer join SKQ_SWJG b on a.SJSWJGBM=b.SWJGBM where a.STATUS = 1 "+sqlStr;
				System.out.println("sql=="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Swjg swjg = new Swjg();
					swjg.setSwjgbm(rs.getString("SWJGBM"));
					swjg.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC")));
					swjg.setSwjgjc(Util.iso8859togbk(rs.getString("SWJGJC")));
					swjg.setSid(rs.getInt("SID"));
					swjg.setStatus(rs.getInt("STATUS"));
					swjg.setSjswjgbm(rs.getString("SJSWJGBM"));
					swjg.setSjswjgmc(Util.iso8859togbk(rs.getString("SJSWJGMC")));

					al.add(swjg);
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

	public String getSwjgbm() {
		return swjgbm;
	}

	public void setSwjgbm(String swjgbm) {
		this.swjgbm = swjgbm;
	}

	public String getSwjgmc() {
		return swjgmc;
	}

	public void setSwjgmc(String swjgmc) {
		this.swjgmc = swjgmc;
	}

	public String getSwjgjc() {
		return swjgjc;
	}

	public void setSwjgjc(String swjgjc) {
		this.swjgjc = swjgjc;
	}

	public String getSjswjgbm() {
		return sjswjgbm;
	}

	public void setSjswjgbm(String sjswjgbm) {
		this.sjswjgbm = sjswjgbm;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSjswjgmc() {
		return sjswjgmc;
	}

	public void setSjswjgmc(String sjswjgmc) {
		this.sjswjgmc = sjswjgmc;
	}

}
