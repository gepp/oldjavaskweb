package jsdt.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class testDB {
	private final String sybaseDriverName = "com.sybase.jdbc2.jdbc.SybDriver";
	private final String sybaseUrlToConnect = "jdbc:sybase:Tds:192.168.1.185:5000/fzk?charset=cp850&jconnect_version=3";
	private Connection conn = null;

	/**
	 * To load the jdbc driver
	 * 
	 */
	public testDB() {
		try {
			Class.forName(sybaseDriverName);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out
					.println(getErrorMessage(ex,
							"The Driver loaded error,please contact to your Software Designer!")
							.toString());
		}
	}

	public StringBuffer getErrorMessage(Exception ex, String alarmMessage) {
		StringBuffer errorStringBuffer = new StringBuffer();
		errorStringBuffer.append(alarmMessage);
		errorStringBuffer.append(ex.getMessage());
		return errorStringBuffer;
	}

	/**
	 * getConnection method
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		try {
			this.conn = DriverManager.getConnection(this.sybaseUrlToConnect,
					"sa", "");

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out
					.println(getErrorMessage(ex,
							"Can not get connection,please contact to your Software Designer!")
							.toString());
		}

		return this.conn;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testDB testdb = new testDB();
		try {
			Connection conn = testdb.getConnection();

			System.out.println("Now begin to excute.............");

			if (conn != null) {
				String sql = "select * from SKQ_NSRXX";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					System.out.println("smbm==" + rs.getString("NSRWJBM"));
				}
				rs.close();
				ps.close();
			}
			/*
			 * PreparedStatement myPreparedStatement = myConnection
			 * .prepareStatement(
			 * "select area_id, area_name,ip_address,tel,area_type,pc_id from c_area_info"
			 * ); // myPreparedStatement.setInt(1,2); ResultSet myResultSet =
			 * myPreparedStatement.executeQuery(); StringBuffer myStringBuffer =
			 * new StringBuffer();
			 * 
			 * while (myResultSet.next()) {
			 * 
			 * myStringBuffer.append(myResultSet.getInt(1) + "  ");
			 * myStringBuffer.append(myResultSet.getString(2) + "  ");
			 * myStringBuffer.append(myResultSet.getString(3) + "  ");
			 * myStringBuffer.append(myResultSet.getString(4) + "  ");
			 * myStringBuffer.append(myResultSet.getInt(5) + "  ");
			 * myStringBuffer.append(myResultSet.getInt(6) + "  \n"); }
			 * 
			 * System.out.println(new String(myStringBuffer.toString().getBytes(
			 * "ISO-8859-1"), "GBK"));
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out
					.println(testdb
							.getErrorMessage(ex,
									"Application error,please contact to your Software Designer!")
							.toString());
		}

	}

}
