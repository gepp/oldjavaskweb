package jsdt.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsdt.model.DBTest;

/**
 * Servlet implementation class DBTestServlet
 */
public class DBTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
//		ClassLoader cl = this.getClass().getClassLoader();
//      InputStream is = cl.getResourceAsStream("db.properties");
//      System.out.println(is);
//      Properties dbprops=new Properties();
//		dbprops.load(is);
//		System.out.println(dbprops.getProperty("SQLSERVER_DRIVER"));
		
//		DBConnection pool = new DBConnection();
//		Connection conn = null;
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println("path : "+path);
		int result = -1;
//		DBConnection pool = new DBConnection(path);
//		Connection conn = null;
//		int result = -1;
//		try {
//			conn = pool.getConnection();
//			if(conn != null)
//			{
//				String sql = "select count(*) from jobs";
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				while(rs.next())
//				{
//					result = rs.getInt(1); 
//				}
//				rs.close();
//				ps.close();
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally
//		{
//			if(conn != null)
//			{
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		result = new DBTest().queryDB(path);
		System.out.println("result :" +result);
		if(result != -1)
		{
			out.write("OK");
		}
		else
		{
			out.write("BAD");
		}
		
		out.flush();
		out.close();
	}

}
