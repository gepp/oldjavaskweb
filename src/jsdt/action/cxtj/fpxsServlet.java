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

import jsdt.model.cxtj.Fpxs;
import jsdt.tools.Query;

/**
 * Servlet implementation class fpxsServlet
 */
public class fpxsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fpxsServlet() {
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
					String fpdm = request.getParameter("fpdm");
					int fphm = Integer.parseInt(request.getParameter("fphm"));
					
					int flag = 0;
					
//					String sql = "select SID from SKQ_BDCKP where FPDM='"+fpdm+"' and FPHM="+fphm;
//					String fieldname = "SID";
//					int bdckpid = Query.getFieldInt(sql, fieldname);
//					
//					if(bdckpid>0){
//						flag=1;
//					}
//					
//					sql = "select SID from SKQ_JZAZKP where FPDM='"+fpdm+"' and FPHM="+fphm;
//					fieldname = "SID";
//					int jzazkpid = Query.getFieldInt(sql, fieldname);
//					
//					if(jzazkpid>0){
//						flag=2;
//					}
					
					HashMap hmTy = new HashMap();
					HashMap hmBdc = new HashMap();
					HashMap hmJzaz = new HashMap();
					
					Fpxs xs = new Fpxs();
					
					hmTy = xs.selectTyfp(fpdm, fphm);
					if(flag==1){//不动产发票
						hmBdc = xs.selectBdcfp(fpdm, fphm);
						
						request.setAttribute("hmTy", hmTy);
						request.setAttribute("hmBdc", hmBdc);
						
						RequestDispatcher dispatcher = request
						.getRequestDispatcher("cxtj/fpxs.bdc.jsp");
						dispatcher.forward(request, response);
					}
					else if(flag==2){//建筑安装发票
						hmJzaz = xs.selectJzazfp(fpdm, fphm);
						
						request.setAttribute("hmTy", hmTy);
						request.setAttribute("hmJzaz", hmJzaz);
						
						RequestDispatcher dispatcher = request
						.getRequestDispatcher("cxtj/fpxs.jzaz.jsp");
						dispatcher.forward(request, response);
					}
					else{//通用发票
						request.setAttribute("hmTy", hmTy);
						RequestDispatcher dispatcher = request
						.getRequestDispatcher("cxtj/fpxs.ty.jsp");
						dispatcher.forward(request, response);
					}
				}
			}
		}
	}

}
