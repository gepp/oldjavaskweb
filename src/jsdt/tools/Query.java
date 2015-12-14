package jsdt.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import jsdt.model.baseClass;

public class Query {
	public static String getField(String sql, String fieldname) {
		String result = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getString(fieldname);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static int getFieldInt(String sql, String fieldname) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt(fieldname);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static double getFieldDouble(String sql, String fieldname) {
		double result = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getDouble(fieldname);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String getFieldName(String sql, String fieldname) {
		String result = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = Util.iso8859togbk(rs.getString(fieldname));
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static int updateField(String sql) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				result = ps.executeUpdate();
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=1;
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
		System.out.println("updateFieldSQL:==="+sql);
		return result;
	}
	public static int executeSql(String sql) {
		int result = -1;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.executeQuery();
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=1;
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
	
	public static String getFieldZg(String sql, String fieldname) {
		String result = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getString(fieldname);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	public static int getFieldZgInt(String sql, String fieldname) {
		int result = 0;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = rs.getInt(fieldname);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String getFieldZgName(String sql, String fieldname) {
		String result = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					result = Util.iso8859togbk(rs.getString(fieldname));
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	public static String getFieldIntStr(String sql, String fieldname) {
		String result = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				//System.out.println("sql11==="+sql);
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(fieldname);
					if("".equals(result)){
						result = String.valueOf(id);
					}
					else{
						result = result+","+id;
					}
					//System.out.println("result=="+result);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	public static String getFieldStr(String sql, String fieldname) {
		String result = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String field = rs.getString(fieldname);
					if("".equals(result)){
						result = field;
					}
					else{
						result = result+","+field;
					}
					//System.out.println("result=="+result);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String getFieldStrCx(String sql, String fieldname) {
		String result = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String field = rs.getString(fieldname);
					if("".equals(result)){
						result = "'"+field+"'";
					}
					else{
						result = result+",'"+field+"'";
					}
					//System.out.println("result=="+result);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public static String getFieldStrTjFpkj(String sql) {
		String result = "";
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					String szbm = rs.getString("SZBM");
					String szmc = Util.iso8859togbk(rs.getString("SZMC"));
					String smbm = rs.getString("SMBM");
					String smmc = Util.iso8859togbk(rs.getString("SMMC"));
					String jsje = rs.getString("JSJE");
					String sl = rs.getString("SL");
					String yjse = rs.getString("YJSE");
					
					String field = szbm+","+szmc+","+smbm+","+smmc+","+jsje+","+sl+","+yjse;
					if("".equals(result)){
						result = field;
					}
					else{
						result = result+"|"+field;
					}
					//System.out.println("result=="+result);
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	 
	public static HashMap fySql(int pageSize,int pageNo,String sqlStr,String fieldname,String countSql,String tablename,String order){
		HashMap hm = new HashMap();
		
		String sql="";
		String field = "SID";
		int nextPage=(pageNo-1)*pageSize;
		System.out.println("pageNo:==="+pageNo);
		int maxCount = baseClass.getFieldCount(countSql);
		int maxPage = (int)Math.ceil((double)maxCount/pageSize);
		//sql="SELECT top "+pageSize+" SID  FROM "+tablename+" a"+ " "+sqlStr+" and (a.SID not in (select top "+nextPage+" sid from "+tablename+" a "+sqlStr+order+"))"+order;
		sql="SELECT top "+pageSize+" SID  FROM "+tablename+" a"+ " "+sqlStr+" and (a.SID not in (select top "+nextPage+" sid from "+tablename+" a "+sqlStr+order+")) "+order;
       System.out.println("fenye_sql:"+sql);
		String resultStr=Query.getFieldStr(sql, "SID");
		hm.put("maxCount", String.valueOf(maxCount));
		hm.put("maxPage", String.valueOf(maxPage));
		hm.put("resultStr", resultStr);
		return hm;
	}
}
