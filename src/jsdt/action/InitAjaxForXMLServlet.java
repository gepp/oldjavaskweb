package jsdt.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsdt.model.Nsrxx;

/**
 * Servlet implementation class InitAjaxForXMLServlet
 */
public class InitAjaxForXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitAjaxForXMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String nsrwjbm = URLDecoder.decode(request.getParameter("nsrwjbm"), "UTF-8");
		//String nsrwjbm=new String(request.getParameter("nsrwjbm").getBytes("ISO-8859-1"),"UTF-8");
		 List qlist = new ArrayList<String>();   
		 Nsrxx nsr=new Nsrxx();
		qlist= nsr.findAllNsr(nsrwjbm);
		request.setAttribute("nsrxxs", qlist);
		 request.getRequestDispatcher("ajaxResponseHTMLBetween.jsp").forward(request, response); 
		 
	}

}
