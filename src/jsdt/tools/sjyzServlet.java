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
			request.setAttribute("errorMsg", "��¼��ʱ�������µ�¼��");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("error.session.jsp");
			dispatcher.forward(request, response);
		} else {
			String method = request.getParameter("method");
			if (method == null || "".equals(method)) {
				request.setAttribute("errorMsg", "�����жϴ���");
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
						System.out.println("�ļ�δ�ҵ�");
						e.printStackTrace();
					}
		           //  System.out.println("size:" + sqlList.size());
		             for (String sql : sqlList) {
		                 Query.updateField(sql);
		             }
					if(sqlList.size()>0)
					response.getWriter().print("���ϳɹ���");
					else{
						response.getWriter().print("���Ѿ����Ϲ�������ʧ��");
					}
				}
				else if("yz".equals(method)){
					Sjyz yz = new Sjyz();
					//ϵͳ����
					//˰��˰Ŀ��
					
					//��ѯ�����ݿ�
					ArrayList alszsm = yz.selectSzsm();
					//���������ݿ�
					int szsmResult = yz.insertSzsm(alszsm);
					
					//ע������
					//��ѯ�����ݿ�
					ArrayList alZclx = yz.selectZclx();
					//���������ݿ�
					int zclxResult = yz.insertZclx(alZclx);
					
					//��˰����Ϣ����
					ArrayList alnsrxx = yz.selectNsrxx();
					//���������ݿ�
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
