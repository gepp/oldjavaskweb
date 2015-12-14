package jsdt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SybaseTest {
	public static void main(String[] args) {
		try {
			Class.forName("com.sybase.jdbc2.jdbc.SybDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:sybase:Tds:test-15cc166426:5000/WLSK?charset=cp850&jconnect_version=4",
					"sa", "111111");
//			PreparedStatement ps = conn.prepareStatement("insert into SKQ_ZSFS(ZSFSBM,ZSFSMC,STATUS,SBBS) values('001','≤È’À’˜ ’',1,0)");
			PreparedStatement ps = conn.prepareStatement("select * from SKQ_PMSZ");
			ps.executeQuery();
			//ResultSet rs = ps.executeQuery();
//			while(rs.next())
//			{
//				System.out.println(rs.getString("ZSFSMC"));
//			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
