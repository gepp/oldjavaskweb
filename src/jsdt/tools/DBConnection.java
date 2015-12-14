package jsdt.tools;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;

/**
 * 驱动类
 * 
 * @author Administrator
 * 
 */
public class DBConnection {
	
	public static Object getInnter(Object con){
		Object re=null;
		Field f;
		try {
			f = con.getClass().getDeclaredField("inner");
			f.setAccessible(true);
			re= f.get(con);
			f.setAccessible(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return re;
	}
	
	/**
	 * JDBC链接
	 * 
	 * @return Connection
	 */
	private static DataSource dataSource = null;

	public static synchronized Connection getConnection() throws SQLException {
		Connection conn = null;
		init();
		conn=dataSource.getConnection();
		return conn;
	}
	
	
 

	private static void init() {
		if (dataSource == null) {
			try {
				Class.forName(Env.getInstance().getProperty("driverClassName") ) ;
				DataSource unpooled = DataSources.unpooledDataSource(Env.getInstance().getProperty("url") ,Env.getInstance().getProperty("username") ,Env.getInstance().getProperty("password"));
				dataSource= DataSources.pooledDataSource(unpooled);
			} catch (Exception e) {
				 throw new RuntimeException("数据库连接失败！", e); 
			}
 
		}

	}
    
	public static void closeAll(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws SQLException, InterruptedException {
		Connection conn=null;
		for(int i=0;i<5;i++){
			
			conn=DBConnection.getConnection();
			Object o1=getInnter(conn);
			System.out.println("o1:="+o1);
			conn.close();
			Thread.sleep(1000);
			conn=DBConnection.getConnection();
			Object o2=getInnter(conn);
			System.out.println("o2:="+o2);
			if(o1==o2){
			System.out.println("o1==o2");
			}
			conn.close();
		}
	}
	
 
}
