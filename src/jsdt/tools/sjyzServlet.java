package jsdt.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsdt.model.Sjyz;

/**
 * Servlet implementation class sjyzServlet
 */
public class sjyzServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sjyzServlet() {
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
		if (session.getAttribute("username") == null) {
			request.setAttribute("errorMsg", "登录超时，请重新登录！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("error.session.jsp");
			dispatcher.forward(request, response);
		} else {
			String method = request.getParameter("method");
			if (method == null || "".equals(method)) {
				request.setAttribute("errorMsg", "类型判断错误！");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
			} else {
				if ("import".equals(method)) {
					
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("regist/sjyz.jsp");
					dispatcher.forward(request, response);
				}else if ("second".equals(method)) {
					
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("regist/sjyz_second.jsp");
					dispatcher.forward(request, response);
				}
				else if ("yz_second".equals(method)) {
					List<String> sqlList = null;
					try {
						sqlList = Util.loadSql(request.getRealPath("/")+"update.sql");
					} catch (Exception e) {
						System.out.println("文件未找到");
						e.printStackTrace();
					}
		           //  System.out.println("size:" + sqlList.size());
		             for (String sql : sqlList) {
		                 Query.updateField(sql);
		             }
					if(sqlList.size()>0)
					response.getWriter().print("整合成功！");
					else{
						response.getWriter().print("你已经整合过或整合失败");
					}
				}
				else if("yz".equals(method)){
					Sjyz yz = new Sjyz();
					//系统表导入
					//税种税目表
					
					//查询老数据库
					ArrayList alszsm = yz.selectSzsm();
					//插入新数据库
					int szsmResult = yz.insertSzsm(alszsm);
					
					//注册类型
					//查询老数据库
					ArrayList alZclx = yz.selectZclx();
					//插入新数据库
					int zclxResult = yz.insertZclx(alZclx);
					
					//纳税户信息导入
					ArrayList alnsrxx = yz.selectNsrxx();
					//插入新数据库
					int nsrxxResult = yz.insertNsrxx(alnsrxx);
				//	int oldResult=yz.insertOldSbsj();
				//	request.setAttribute("oldResult", oldResult);
					request.setAttribute("szsmResult", szsmResult);
					request.setAttribute("zclxResult", zclxResult);
					request.setAttribute("nsrxxResult", nsrxxResult);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("regist/sjyz.show.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}

}
