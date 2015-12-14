package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class Role extends baseClass {
	private int sid;
	private String rolename;
	private String note;
	private ArrayList alMenu;
	private String menuStr;

	public Role() {
	}

	public Role(int sid) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ROLE where SID=" + sid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					this.sid = sid;
					this.rolename = Util.iso8859togbk(rs.getString("ROLENAME"));
					this.note = Util.iso8859togbk(rs.getString("NOTE"));
				}
				rs.close();
				ps.close();

				sql = "select MENUCODE from SKQ_ROLEMENU where ROLEID=" + sid;
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				String menuStr = ",";
				while (rs.next()) {
					menuStr = menuStr + rs.getString("MENUCODE") + ",";
				}
				this.menuStr = menuStr;

				this.alMenu = this.selectMenu();
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

	public ArrayList selectMenu() {
		ArrayList al = new ArrayList();

		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_MENU where PARENTID is null or PARENTID=0";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HashMap hm = new HashMap();
					hm.put("menuid", rs.getInt("SID"));
					hm.put("menucode", rs.getString("MENUCODE"));
					hm.put("menuname", Util.iso8859togbk(rs
							.getString("MENUNAME")));
					hm.put("parentid", rs.getInt("PARENTID"));

					al.add(hm);

					String sql1 = "select * from SKQ_MENU where PARENTID="
							+ rs.getInt("SID");
					PreparedStatement ps1 = conn.prepareStatement(sql1);
					ResultSet rs1 = ps1.executeQuery();
					while (rs1.next()) {
						HashMap hm1 = new HashMap();
						hm1.put("menuid", rs1.getInt("SID"));
						hm1.put("menucode", rs1.getString("MENUCODE"));
						hm1.put("menuname", Util.iso8859togbk(rs1
								.getString("MENUNAME")));
						hm1.put("parentid", rs1.getInt("PARENTID"));

						al.add(hm1);
					}
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

	public int add() {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_ROLE (ROLENAME,NOTE) values(?,?)");
				ps.setString(1, Util.gbkto8859(this.rolename));
				ps.setString(2, Util.gbkto8859(this.note));
				result = ps.executeUpdate();
				ps.close();
				
				if(result!=-1){
					String sql = "select max(SID) as SID from SKQ_ROLE";
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					int roleid = 0;
					while (rs.next()) {
						result = rs.getInt("SID");
					}
					rs.close();
					ps.close();
				}
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

	public int add(String rolename, String note) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = null;
				ps = conn
						.prepareStatement("insert into SKQ_ROLE (ROLENAME,NOTE) values(?,?)");
				ps.setString(1, Util.gbkto8859(rolename));
				ps.setString(2, Util.gbkto8859(note));
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

	public int addMenu(int roleid) {
		int result1 = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				

				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String[] menuArr = this.menuStr.split(",");

				for (int i = 0; i < menuArr.length; i++) {
					String menucode = menuArr[i];
					String sql1 = "insert into SKQ_ROLEMENU (MENUCODE,ROLEID) values('"
							+ menucode + "'," + roleid + ")";
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

	public int update() {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "update SKQ_ROLE set ROLENAME='"
						+ Util.gbkto8859(this.rolename) + "',NOTE='"
						+ Util.gbkto8859(this.note) + "' where SID=" + this.sid;
				stsm.addBatch(sql);

				String sql1 = "delete from SKQ_ROLEMENU where ROLEID="
						+ this.sid;
				stsm.addBatch(sql1);

				String[] menuArr = this.menuStr.split(",");
				for (int i = 0; i < menuArr.length; i++) {
					String menucode = menuArr[i];
					String sql2 = "insert into SKQ_ROLEMENU (MENUCODE,ROLEID) values('"
							+ menucode + "'," + this.sid + ")";
					stsm.addBatch(sql2);
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

	public ArrayList selectAll() {
		ArrayList al = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select * from SKQ_ROLE";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				al = new ArrayList();
				while (rs.next()) {
					Role role = new Role();
					role.setRolename(Util.iso8859togbk(rs.getString("ROLENAME")));
					role.setNote(Util.iso8859togbk(rs.getString("NOTE")));
					role.setSid(rs.getInt("SID"));

					al.add(role);
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

	public String selectRoleUser(int roleid) {
		String roleuser = ",";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				String sql = "select USERNAME from SKQ_ROLEUSER where ROLEID="
						+ roleid;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					roleuser = roleuser + rs.getString("USERNAME") + ",";
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
		return roleuser;
	}

	public int addUser(String usernameStr, int roleid) {
		int result1 = 1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				conn.setAutoCommit(false);
				Statement stsm = conn.createStatement();

				String sql = "delete from SKQ_ROLEUSER where ROLEID=" + roleid;
				stsm.addBatch(sql);

				String[] usernameArr = usernameStr.split(",");

				for (int i = 0; i < usernameArr.length; i++) {
					String username = usernameArr[i];
					String sql1 = "insert into SKQ_ROLEUSER (USERNAME,ROLEID) values('"
							+ username + "'," + roleid + ")";
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

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ArrayList getAlMenu() {
		return alMenu;
	}

	public void setAlMenu(ArrayList alMenu) {
		this.alMenu = alMenu;
	}

	public String getMenuStr() {
		return menuStr;
	}

	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

}
