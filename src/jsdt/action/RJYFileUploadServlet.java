package jsdt.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RJYFileUploadServlet
 */
public class RJYFileUploadServlet extends HttpServlet {
    private String realpath = "";
    private String msg = "";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RJYFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
        realpath = config.getServletContext().getRealPath("/");
        System.out.println(realpath);
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
        response.setContentType("application/x-java-serialized-object");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
//        HttpSession session = request.getSession(false);
        String uploadpath = realpath+"upload/rjy/";
        String machineno = request.getParameter("machineno");
        String fiscalcardno = request.getParameter("fiscalcardno");
        System.out.println("Machine="+machineno);
        System.out.println("fiscalcardno="+fiscalcardno);
        File dir = new File(uploadpath);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        else
        {
            try
            {
                InputStream in = request.getInputStream();
                ObjectInputStream inputFromApplet = new ObjectInputStream(in);
                byte[] data = (byte[])inputFromApplet.readObject();
//                System.out.println(new String(data));
                
//                FileOutputStream output = new FileOutputStream(uploadpath+createRandomFile(uploadpath)); 
                FileOutputStream output = new FileOutputStream(uploadpath+machineno+fiscalcardno); 
                BufferedOutputStream outBuff=new BufferedOutputStream(output); 
                outBuff.write(data);
                outBuff.flush();
                outBuff.close();
                
                OutputStream outstr = response.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outstr);
                response.setContentType("text/html");
                oos.writeObject("文件上传成功！");
                oos.flush();
                oos.close();

            }
            catch (ClassNotFoundException e)
            {
                // TODO Auto-generated catch block
                OutputStream outstr = response.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outstr);
                response.setContentType("text/html");
                oos.writeObject("文件上传失败，请稍后再试！");
                oos.flush();
                oos.close();
                e.printStackTrace();
                
            }
        }

        System.out.println("===========");
	}

    /**
     * 生成随机文件名
     */
    private String createRandomFile(String storepath) throws IOException {
        // 获取原始文件的后缀
            String fileName = System.currentTimeMillis() +
                "" + new Double(899999 * Math.random() + 100000).intValue();
            File file = new File(storepath + fileName);
            if(!file.exists())
            {
                file.createNewFile();
                return fileName;
            }
            else
            {
                return "";
            }
    }
    
    /**
     * 规范路径，路径必须最前加/最后不要/
     */
    private String standardPath(String path, boolean stIndex) {
        if (path == null) return null;
        if (stIndex && !path.startsWith("/")) return "/" + path;
        if (path.endsWith("/")) return path.substring(0, path.length());
        return path;
    }

    private void write(HttpServletResponse response, String message)
            throws IOException {
        response.getWriter().write(message);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
