package jsdt.action.cxtj;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsdt.model.cxtj.CxSKK;

/**
 * Servlet implementation class SkkFZServlet
 */
public class SkkFZServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkkFZServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
//		if (session.getAttribute("username") != null) {
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
			} else {
				if ("query".equals(method)) {
					String jqbh = request.getParameter("jqbh").trim();
					String fpdm = request.getParameter("fpdm").trim();
					String fpqsh = request.getParameter("fpqsh").trim();
					String fpjzh = request.getParameter("fpjzh").trim();
					
					CxSKK cxskk = new CxSKK();
					HashMap query_result = cxskk.querySKKByMachine(jqbh);
					if(query_result !=null&&!query_result.isEmpty())
					{
						query_result.put("jqbh", jqbh);
						query_result.put("fpdm", fpdm);
						query_result.put("fpqsh", fpqsh);
						query_result.put("fpjzh", fpjzh);
						System.out.println(query_result);
						
						request.setAttribute("SKK", query_result);
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("cxtj/skk.fz.list.jsp");
						dispatcher.forward(request, response);
					}
					else {
						request.setAttribute("errorMsg", "Ë°¿Ø¿¨ÐÅÏ¢²éÑ¯Ê§°Ü£¡");
						RequestDispatcher dispatcher = request
								.getRequestDispatcher("error.jsp");
						dispatcher.forward(request, response);
					} 
				}
			}
		}
	}

}
