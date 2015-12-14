package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsdt.tools.DBConnection;
import jsdt.tools.Util;

public class baseClass {
	private String table;

	/**
	 * @roseuid 4CA19C410000
	 */
	public baseClass() {

	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * @param fieldname
	 * @param where
	 * @return String
	 * @roseuid 4CA00914016F
	 */
	
	public String getField(String fieldname, String where) {
		String result = null;
		if (fieldname == null || "".equals(fieldname) || where == null
				|| "".equals(where)) {
			return result;
		}
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select " + fieldname + " from " + this.table
						+ " " + where;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getString(fieldname);
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
		return result;
	}
	
	public String getFieldName(String fieldname, String where) {
		String result = null;
		if (fieldname == null || "".equals(fieldname) || where == null
				|| "".equals(where)) {
			return result;
		}
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select " + fieldname + " from " + this.table
						+ " " + where;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = Util.iso8859togbk(rs.getString(fieldname));
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
		return result;
	}
	
	public int getFieldInt(String fieldname, String where) {
		int result = 0;
		if (fieldname == null || "".equals(fieldname) || where == null
				|| "".equals(where)) {
			return result;
		}
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select " + fieldname + " from " + this.table
						+ " " + where;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt(fieldname);
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
		return result;
	}
	
	public double getFieldDouble(String fieldname, String where) {
		double result = 0;
		if (fieldname == null || "".equals(fieldname) || where == null
				|| "".equals(where)) {
			return result;
		}
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select " + fieldname + " from " + this.table
						+ " " + where;
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getDouble(fieldname);
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
		return result;
	}

	/**
	 * @param fieldname
	 * @param fieldvalue
	 * @param where
	 * @return int
	 * @roseuid 4CA00A7702E6
	 */
	public int updateField(String fieldname, String fieldvalue, String where) {
		int result = -1;
		if (fieldname == null || "".equals(fieldname) || where == null
				|| "".equals(where)) {
			return result;
		}
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "update " + this.table + " set " + fieldname + "='" + fieldvalue + "' " + where;
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
	 * @param sid
	 * @return int
	 * @roseuid 4CA00ADC01AD
	 */
	public int deleteById(int sid) {
		int result = -1;
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "delete "+this.table+" where SID="+sid;
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
	 * ��ȡ��ǰ��ѯ�ļ�¼��
	 * 2011-3-7
	 * @param strsql
	 * @return
	 */
	public static int getFieldCount(String strsql) {
		int result = 0;
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select count(1) as sumcount from ("+strsql+") as tabb";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt("sumcount");
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
		return result;
	}
	/**
	 * ��ȡ��ǰ��ѯ�Ľ���
	 * 2011-4-25
	 * @param strsql
	 * @param str
	 * @return
	 */
	public static int getFieldSum(String str,String strsql) {
		int result = 0;
		Connection conn = null;
		try {
			conn =DBConnection.getConnection();
			if (conn != null) {
				String sql = "select sum(" + str + ") as sum from ("+strsql+") as tabb";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt("sum");
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
		return result;
	}

//	public static void main(String[] args) {
//		String path = Thread.currentThread().getContextClassLoader()
//				.getResource("").getPath();
//		// System.out.println("path=="+path);
//		//String fieldname = "ZSFSMC";
//		String fieldname = "STATUS";
//		String where = " where ZSFSBM='001'";
//		String table = "SKQ_ZSFS";
//		//String fieldvalue = Util.gbkto8859("�������ջ�����");
//		String fieldvalue = Util.gbkto8859("�������ջ�����");
//
//		int sid = 1;
//		/*
//		 * String fieldname = "title"; String where = " where cars_id=3"; String
//		 * table = "tbl_cars";
//		 */
//		baseClass baseclass = new baseClass();
//		baseclass.setTable(table);
//		int result = baseclass.deleteById(sid,path);
//		if(result>0){
//			System.out.println("ɾ���ɹ���");
//		}
//		else{
//			System.out.println("ɾ��ʧ�ܣ�");
//		}
//		
//		// int result = baseclass.updateField(fieldname, fieldvalue, where,
//		// path);
//		// if(result>0){
//		// String zsfsmc = Util.iso8859togbk(baseclass.getField(fieldname,
//		// where, path));
//		// System.out.println("zsfsmc=="+zsfsmc);
//		// }
//		// else{
//		// System.out.println("zsfsmc==�޸�ʧ�ܣ�");
//		// }
//
//		// System.out.println(new baseClass().getField(fieldname,where,path));
//	}
}
