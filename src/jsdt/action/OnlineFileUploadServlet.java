package jsdt.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsdt.tools.ParseMXTest;
import jsdt.tools.Query;
import jsdt.tools.Util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eaio.uuid.UUID;

/**
 * Servlet implementation class OnlineFileUploadServlet
 */
public class OnlineFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OnlineFileUploadServlet() {
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
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) {
		String db_name=(String)request.getSession().getAttribute("db_name");
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("开始明细上传");
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
		String begin_name = "";
		File mx_file = null;
		File mxsy_file=null;
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
				if (begin_name.contains("MX000001.BIN")) {
					mx_file = saveFile;
				} else if (begin_name.contains("MXSY.BIN")) {
					mxsy_file=saveFile;
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
						saveFile.delete();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}

		}
		ParseMXTest p = new ParseMXTest();
		if (machineno.equals("")) {
			try {
				
				response.getWriter().print("请选择MXSY.BIN文件！！！");
				System.out.println("请选择MXSY.BIN文件！！！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (mx_file == null) {
			try {
				response.getWriter().print("请选择MX000001.BIN文件！！！");
				System.out.println("请选择MX000001.BIN文件！！！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			String sql_nsrwjbm = "select NSRWJBM from SKQ_JQXX where JQBH='"
					+ machineno + "'";
			String fieldname = "NSRWJBM";
			String nsrwjbm = Query.getField(sql_nsrwjbm, fieldname);
			System.out.println("微机编码：" + nsrwjbm);
			if (nsrwjbm == null || nsrwjbm.equals("")) {
				try {
					response.getWriter().print(
							"不存在机器编号：" + machineno + "对应的纳税人微机编码");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
			Map map = p.parseMX(mx_file);
			System.out.println("begin:"+mx_file.getName());
			System.out.println("sy:"+mxsy_file.getName());
			mx_file.delete();
			mxsy_file.delete();
			int drbz = (Integer) map.get("drbz");
			String mouth = (String) map.get("mouth");
			if (drbz == 1) {
				try {
					 
					response.getWriter().print(mouth + "月发票明细已经导入过！");
					System.out.println(mouth + "月发票明细已经导入过！");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				@SuppressWarnings("unchecked")
				List<HashMap<String, Object>> al = (LinkedList<HashMap<String, Object>>) map
						.get("invoices");

				int result = p.insertInvoiceDetail(al, machineno, nsrwjbm);

				System.out.println("插入库时间："
						+ (System.currentTimeMillis() - start) + "ms");
				if (result == 1) {
					try {
						response.getWriter().print(
							mouth+"发票明细数据上传成功,机器编号：" + machineno
										+ "本次成功导入发票：" + al.size() + "");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						response.getWriter().print("发票明细数据上传失败！");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
		}

	}
}

// response.setContentType("application/x-java-serialized-object");
// response.setCharacterEncoding("utf-8");
// try {
// request.setCharacterEncoding("utf-8");
// } catch (UnsupportedEncodingException e2) {
// // TODO Auto-generated catch block
// e2.printStackTrace();
// }
// String machineno = request.getParameter("machineno");
// System.out.println("Machine="+machineno);
//
// InputStream in=null;
// ObjectInputStream inputFromApplet=null;
// File data=null;
// try{
// in = request.getInputStream();
// inputFromApplet = new ObjectInputStream(in);
// data = (File)inputFromApplet.readObject();
// }catch (Exception e) {
// }
//
// try
// {
//
// String sql_nsrwjbm = "select NSRWJBM from SKQ_JQXX where JQBH='" + machineno
// + "'";
// String fieldname = "NSRWJBM";
// String nsrwjbm = Query.getField(sql_nsrwjbm, fieldname);
// System.out.println("微机编码："+nsrwjbm);
// ParseMXTest p = new ParseMXTest();
// Map map = p.parseMX(data,nsrwjbm);
// int drbz=(Integer)map.get("drbz");
// String mouth=(String)map.get("mouth");
// if(drbz==1){
// OutputStream outstr = response.getOutputStream();
// ObjectOutputStream oos = new ObjectOutputStream(outstr);
// response.setContentType("text/html");
// oos.writeObject(mouth+"月发票明细已经导入过！");
// oos.flush();
// oos.close();
// }else{
// long start = System.currentTimeMillis();
// List<HashMap<String, Object>>
// al=(LinkedList<HashMap<String,Object>>)map.get("invoices");
//
// int result = p.insertInvoiceDetail(al, machineno,nsrwjbm);
//
// System.out.println("插入库时间："+(System.currentTimeMillis() - start) + "ms");
// if (result == 1) {
// OutputStream outstr = response.getOutputStream();
// ObjectOutputStream oos = new ObjectOutputStream(outstr);
// response.setContentType("text/html");
// oos.writeObject("发票明细数据上传成功\r\n导入机器编号:"+machineno+"\r\n"+"本次成功导入发票："+al.size()+"\r\n");
// oos.flush();
// oos.close();
// }
// else
// {
// OutputStream outstr = response.getOutputStream();
// ObjectOutputStream oos = new ObjectOutputStream(outstr);
// response.setContentType("text/html");
// oos.writeObject("发票明细数据上传失败！");
// oos.flush();
// oos.close();
// }
// }
// }
//
//
// catch (Exception e)
// {
// try{
// // TODO Auto-generated catch block
// OutputStream outstr = response.getOutputStream();
// ObjectOutputStream oos = new ObjectOutputStream(outstr);
// response.setContentType("text/html");
// oos.writeObject("发票明细数据上传失败，请稍后再试！");
// oos.flush();
// oos.close();
// }catch (Exception e1) {
// // TODO: handle exception
// }
// }
// }
// }
