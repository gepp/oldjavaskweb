package jsdt.action.taxpayer;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsdt.tools.Query;
import jsdt.tools.SYSTEM;

/**
 * Servlet implementation class nsrmcgxServlet
 */
public class nsrmcgxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nsrmcgxServlet() {
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
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String method = request.getParameter("method");
		if (method == null || "".equals(method)) {
			request.setAttribute("errorMsg", "ÀàÐÍÅÐ¶Ï´íÎó£¡");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		} 
		else {
			if ("import".equals(method)) {
				RequestDispatcher dispatcher = request
				.getRequestDispatcher("regist/nsrmcgx.dk.jsp");
				dispatcher.forward(request, response);
			}
			else if ("info".equals(method)) {
				HashMap CARDINFO = (HashMap) session
						.getAttribute("FCARDINFO");
				System.out.println("CARDINFO=="+CARDINFO);
				
				// System.out.println("CARDINFO==="+CARDINFO);
				if (CARDINFO == null || CARDINFO.isEmpty()) {
					request.setAttribute("errorMsg", "»ù´¡ÐÅÏ¢¶ÁÈ¡Ê§°Ü£¡");
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("error.jsp");
					dispatcher.forward(request, response);
				} else {
					HashMap EF02 = (HashMap)CARDINFO.get("EF02");
					String nsrwjbm = ((String)EF02.get("NSRWJDM"));
					//System.out.println("nsrwjbm=="+nsrwjbm);
					String sql = "select NSRMC from SKQ_NSRXX where NSRWJBM='"+nsrwjbm+"' and STATUS=1";
					String fieldname = "NSRMC";
					String nsrmc = Query.getFieldName(sql, fieldname);
					
					
					request.setAttribute("nsrmc", nsrmc);
					request.setAttribute("CARDINFO", CARDINFO);
					
					
					
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("regist/nsrmcgx.dk.show.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}

}
