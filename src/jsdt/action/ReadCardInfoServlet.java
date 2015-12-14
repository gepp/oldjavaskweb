package jsdt.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReadCardInfoServlet
 */
public class ReadCardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadCardInfoServlet() {
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
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			request.setAttribute("errorMsg", "µÇÂ¼³¬Ê±£¬ÇëÖØÐÂµÇÂ¼£¡");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("error.session.jsp");
			dispatcher.forward(request, response);
		} else {
			String method = request.getParameter("method");
			if (method == null || "".equals(method)) {
				request.setAttribute("errorMsg", "ÀàÐÍÅÐ¶Ï´íÎó£¡");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			} else if ("import".equals(method)) {
//					request.setAttribute("WJBMBC", SYSTEM.WJBMBC);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("card/card.read.jsp");
					dispatcher.forward(request, response);
			}else if("read".equals(method))
			{
				HashMap data = new HashMap();
				String type = request.getParameter("type");
				if("0".equals(type))
				{
					data = (HashMap)session.getAttribute("FCARDINFO");
					request.setAttribute("FCINFO", data);
					RequestDispatcher dispatcher = request
					.getRequestDispatcher("card/card.list1.jsp");
					dispatcher.forward(request, response);
				}
				else if("1".equals(type))
				{
					data = (HashMap)session.getAttribute("UCARDINFO");
					request.setAttribute("UCINFO", data);
					RequestDispatcher dispatcher = request
					.getRequestDispatcher("card/card.list.jsp");
					dispatcher.forward(request, response);
				}
			}
		}

	}

}
