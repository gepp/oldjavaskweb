package jsdt.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReadUserCardServlet
 */
public class ReadUserCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadUserCardServlet() {
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
		try
        {
			System.out.println("test");
            response.setContentType("application/x-java-serialized-object");
            request.setCharacterEncoding("utf-8");
    		HttpSession session = request.getSession();
            InputStream in = request.getInputStream();
            ObjectInputStream inputFromApplet = new ObjectInputStream(in);
            HashMap echo = new HashMap();
            echo = (HashMap) inputFromApplet.readObject();
            System.out.println("echo = "+echo);
            //session.removeAttribute("CARDINFO");
             
            session.setAttribute("UCARDINFO", echo);
            
            OutputStream outstr = response.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outstr);
            response.setContentType("text/html");
            oos.writeObject("");
            oos.flush();
            oos.close();
            System.out.println("session:"+session.getAttribute("UCARDINFO"));
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
