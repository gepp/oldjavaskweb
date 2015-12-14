package jsdt.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimerTask;

import javax.servlet.ServletContext;

public class MyTimeTask extends TimerTask {
	private static final int C_SCHEDULE_HOUR = 0;
	private static boolean isRunning = false;
	private ServletContext context = null;

	public MyTimeTask(ServletContext context) {
		this.context = context;
	}

	public void run(){
		// Calendar cal = Calendar.getInstance();
		if (!isRunning) {
			// if (C_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY)) {
			isRunning = true;
			context.log("开始执行指定任务");

			// TODO 添加自定义的详细任务，以下只是示例
			// int i = 0;
			// while (i++ < 10) {
			// System.out.println("已完成任务的" + i + "/" + 10);
			// // context.log("已完成任务的" + i + "/" + 10);
			// }
			String upload_dir = context.getRealPath("/") + "upload/mx/";
			System.out.println(upload_dir);
			File dir = new File(upload_dir);
			if (dir.exists() && dir.isDirectory()) {
//				ParseMXTest p = null;
//				File[] files = dir.listFiles();
//				if (files.length != 0) {
//					for (int i = 0; i < files.length; i++) {
//						try{
//						String machine = files[i].getName();
//						p = new ParseMXTest();
//						HashMap map=p.parseMX(files[i]);
//						
//						ArrayList al =(ArrayList)map.get("invoices");
//						System.out.println("al = " + al);
//						int result = p.insertInvoiceDetail(al, machine);
//						if (result == 1) {
//							files[i].delete();
//						}
//						}catch (Exception e) {
//							// TODO: handle exception
//						}
//					}
//				}
			}
			isRunning = false;
			context.log("指定任务执行结束");
			// }
		} else {
			context.log("上一次任务执行还未结束");
		}
	}
}
