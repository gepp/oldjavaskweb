package jsdt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jsdt.tools.DBConnection;

public class DBTest {

	public DBTest()
	{
	}
	
	public int queryDB(String path)
	{
		int result = -1;
		if(path == null ||"".equals(path))
		{
			return result;
		}
//		DBConnection pool = new DBConnection("DT",path);
		Connection conn = null;
		
		try {
			conn =DBConnection.getConnection();
			if(conn != null)
			{
				String sql = "select count(*) from SKQ_PMSZ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					System.out.println("aaa : "+rs.getString(1));
					result = 1;
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(conn != null)
			{
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
//	public static void main(String[] args)
//	{
//		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		System.out.println(new DBTest().queryDB(path));
//	}
}
