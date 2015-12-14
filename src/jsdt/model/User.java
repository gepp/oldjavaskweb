package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class User extends baseClass {
	private int sid;
	private String username;
	private String actualname;
	private String password;
	private String swjgbm;
	private String swjgmc;
	private int status;

	private String dlm;

	public User() {

	}

	public User(String username) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SWJGMC from SKQ_USER a left outer join SKQ_SWJG b on a.SWJGBM=b.SWJGBM where USERNAME='"
						+ username + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = rs.getInt("SID");
					this.username = username;
					this.actualname = Util.iso8859togbk(rs
							.getString("ACTUALNAME"));
					this.password = rs.getString("PASSWORD");
					this.swjgbm = rs.getString("SWJGBM");
					this.swjgmc = Util.iso8859togbk(rs.getString("SWJGMC"));
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

	public ArrayList getFromZG(String swjgbm) {
		Connection conn = null;
		ArrayList al = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.SWJGBM,a.CZYDLM,a.CZYBM,a.CZYMC,b.SWJGMC from GG_CZY a left outer join BM_SWJG b on a.SWJGBM=b.SWJGBM where QYBJ_CZY='1' and a.SWJGBM='"
						+ swjgbm + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					User user = new User();
					user.setSwjgbm(rs.getString("SWJGBM").trim());
					user.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC"))
							.trim());
					// user.setUsername(rs.getString("CZYDLM").trim());
					user.setUsername(rs.getString("CZYBM").trim());
					user.setDlm(rs.getString("CZYDLM").trim());
					user.setActualname(Util.iso8859togbk(rs.getString("CZYMC"))
							.trim());
					user.setStatus(Integer.valueOf(1));
					user.setSid(Integer.valueOf(1));
					al.add(user);
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

	public int update() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_USER set ACTUALNAME='"
						+ Util.gbkto8859(this.actualname) + "',PASSWORD='"
						+ this.password + "',SWJGBM='" + this.swjgbm
						+ "',STATUS=" + this.status + " where SID=" + this.sid;
				System.out.println("sql=" + "update SKQ_USER set ACTUALNAME='"
						+ Util.gbkto8859(this.actualname) + "',PASSWORD='"
						+ this.password + "',SWJGBM='" + this.swjgbm
						+ "',STATUS=" + this.status + " where SID=" + this.sid);
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

	public int update(int sid, String actualname, String password,
			String swjgbm, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "update SKQ_USER set ACTUALNAME='"
						+ Util.gbkto8859(actualname) + "',PASSWORD='"
						+ password + "',SWJGBM='" + swjgbm + "',STATUS="
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

	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_USER (USERNAME,ACTUALNAME,PASSWORD,SWJGBM,STATUS) values(?,?,?,?,?)");
				ps.setString(1, this.username);
				ps.setString(2, Util.gbkto8859(this.actualname));
				ps.setString(3, this.password);
				ps.setString(4, this.swjgbm);
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

	public int add(String username, String actualname, String password,
			String swjgbm, int status) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_USER (USERNAME,ACTUALNAME,PASSWORD,SWJGBM,STATUS) values(?,?,?,?,?)");
				ps.setString(1, username);
				ps.setString(2, Util.gbkto8859(actualname));
				ps.setString(3, password);
				ps.setString(4, swjgbm);
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

	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select a.*,b.SWJGMC from SKQ_USER a left outer join SKQ_SWJG b on a.SWJGBM=b.SWJGBM ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("USERNAME"));
					user.setActualname(Util.iso8859togbk(rs
							.getString("ACTUALNAME")));
					user.setPassword(rs.getString("PASSWORD"));
					user.setSwjgmc(Util.iso8859togbk(rs.getString("SWJGMC")));
					user.setSid(rs.getInt("SID"));
					user.setStatus(rs.getInt("STATUS"));

					al.add(user);
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

	public String selectUserRole(String username) {
		String userrole = ",";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select ROLEID from SKQ_ROLEUSER where USERNAME='"
						+ username + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					userrole = userrole + rs.getInt("ROLEID") + ",";
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
		return userrole;
	}

	public int addRole(String roleidStr, String username) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "delete from SKQ_ROLEUSER where USERNAME='"
						+ username + "'";
				stsm.addBatch(sql);
				if (roleidStr != null && !"".equals(roleidStr)) {
					String[] roleidArr = roleidStr.split(",");

					for (int i = 0; i < roleidArr.length; i++) {
						int roleid = Integer.valueOf(roleidArr[i]);
						String sql1 = "insert into SKQ_ROLEUSER (USERNAME,ROLEID) values('"
								+ username + "'," + roleid + ")";
						stsm.addBatch(sql1);
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

	public String selectUserMenu(String username) {
		String userMenu = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select distinct MENUCODE from SKQ_ROLEMENU where ROLEID in (select ROLEID from SKQ_ROLEUSER where USERNAME='"
						+ username + "')";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					userMenu = userMenu + rs.getString("MENUCODE") + ",";
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

		return userMenu;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActualname() {
		return actualname;
	}

	public void setActualname(String actualname) {
		this.actualname = actualname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDlm() {
		return dlm;
	}

	public void setDlm(String dlm) {
		this.dlm = dlm;
	}

}
