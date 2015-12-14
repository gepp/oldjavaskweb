package jsdt.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eaio.uuid.UUID;

import jsdt.tools.JProcessLoad;
import jsdt.tools.JProcessWait;
import jsdt.tools.ParseMXTest;
import jsdt.tools.ParseRJY;
import jsdt.tools.Query;
import jsdt.tools.Util;

/**
 * Servlet implementation class OnlineRjyFileUploadServlet
 */
public class OnlineRjyFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnlineRjyFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String db_name=(String)request.getSession().getAttribute("db_name");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("开始日交易上传");
		long start = System.currentTimeMillis();
		long end = 0;
		System.out.println(request.getRealPath("/"));
		String savePath = request.getRealPath("/");
		String machineno = "";
		savePath = savePath + "/upload/";
		File f1 = new File(savePath);
		System.out.println(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			return;
		}
		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		String fiscalcardno="";
		String begin_name = "";
		File begin_file = null;
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				begin_name = name;
				if (name == null || name.trim().equals("")) {
					continue;
				}
				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}
				File file = null;
				do {
					name = new UUID().toString();
					file = new File(savePath + name + extName);
				} while (file.exists());
				File saveFile = new File(savePath + name + extName);
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (begin_name.contains("RJY00001.BIN")) {
					begin_file = saveFile;
				} else if (begin_name.contains("RJYSY.BIN")) {
					BufferedInputStream bufferedInputStream = null;
					try {
						bufferedInputStream = new BufferedInputStream(
								new FileInputStream(saveFile));
						int tmpdata = 0;
						StringBuffer sb = new StringBuffer();
						while ((tmpdata = bufferedInputStream.read()) != -1) {
							sb.append(Util.byte2hex((byte) tmpdata));
						}

						String data = sb.toString();
						machineno = data.substring(0, 16);
						fiscalcardno=data.substring(16,32);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}

		}
		 
		if (machineno.equals("")) {
			try {
				response.getWriter().print("请选择RJYSY.BIN文件！！！");
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (begin_file == null) {
			try {
				response.getWriter().print("请选择RJY00001.BIN文件！！！");
				System.out.println("请选择RJY00001.BIN文件！！！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			 ParseRJY p = new ParseRJY();
			 
			             ArrayList al = p.paresRjy(begin_file);
			 			int result = p.insertRjy(machineno, fiscalcardno, al);
			 			if (result == 1) {
			 				try {
								response.getWriter().print("日交易数据上传成功！");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			 			}
			 			else
			 			{
			 				try {
								response.getWriter().print("日交易数据上传失败！");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			 			}

		}

	}
}
//		// TODO Auto-generated method stub
//        try
//        {
//            response.setContentType("application/x-java-serialized-object");
//            response.setCharacterEncoding("utf-8");
//            request.setCharacterEncoding("utf-8");
//            String machineno = request.getParameter("machineno");
//            String fiscalcardno = request.getParameter("fiscalcardno");
//            System.out.println("Machine="+machineno);
//            System.out.println("fiscalcardno="+fiscalcardno);
//        	
//            InputStream in = request.getInputStream();
//            ObjectInputStream inputFromApplet = new ObjectInputStream(in);
//            File data = (File)inputFromApplet.readObject();
//            
//            ParseRJY p = new ParseRJY();
//
//            ArrayList al = p.paresRjy(data);
//			System.out.println("al = " + al);
//			System.out.println("al lenth= " + al.size());
//			int result = p.insertRjy(machineno, fiscalcardno, al);
//			if (result == 1) {
//		//		 jt.setVisible(false);
//	            OutputStream outstr = response.getOutputStream();
//	            ObjectOutputStream oos = new ObjectOutputStream(outstr);
//	            response.setContentType("text/html");
//	            oos.writeObject("日交易数据上传成功！");
//	            oos.flush();
//	            oos.close();
//			}
//			else
//			{
//	            OutputStream outstr = response.getOutputStream();
//	            ObjectOutputStream oos = new ObjectOutputStream(outstr);
//	            response.setContentType("text/html");
//	            oos.writeObject("日交易数据上传失败！");
//	            oos.flush();
//	            oos.close();
//			}
//        }
//        catch (ClassNotFoundException e)
//        {
//            // TODO Auto-generated catch block
//            OutputStream outstr = response.getOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(outstr);
//            response.setContentType("text/html");
//            oos.writeObject("日交易数据上传失败，请稍后再试！");
//            oos.flush();
//            oos.close();
//            e.printStackTrace();
//            
//        }
//	}
//
//}
