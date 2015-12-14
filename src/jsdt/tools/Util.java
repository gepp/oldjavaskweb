package jsdt.tools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

public class Util {
	//发票拆分方法
		public static ArrayList spliteFplg(String fpdm,
				int fpqsh, int fpjzh,int fpzs) {
			ArrayList fpList = new ArrayList();
			HashMap fpMap=null;
			int fpjs = ((fpjzh - fpqsh + 1) % (fpzs)) == 0 ? (fpjzh - fpqsh + 1)
					/ (fpzs)
					: (fpjzh - fpqsh + 1) / (fpzs) + 1;
	        for(int i=0;i<fpjs;i++){
	        	fpMap=new HashMap();
	        	if(i!=fpjs-1){
	        	fpMap.put("FPDM",fpdm);
	        	fpMap.put("FPQSH", String.valueOf(fpqsh+fpzs*i));
	        	fpMap.put("FPJZH",String.valueOf(fpqsh+fpzs*i+fpzs-1));
	        	}else{
	        		fpMap.put("FPDM",fpdm);
	            	fpMap.put("FPQSH", String.valueOf(fpqsh+fpzs*i));
	            	fpMap.put("FPJZH",String.valueOf(fpjzh));
	        	}
	        	fpList.add(fpMap);
	        }
			return fpList;
		}
	public static String removeZero(String a)
    {
        int len = a.length();
        String b = "";
        int i;
        for(i = 0; a.charAt(i) == '0' && i < len; i++);
        if(i < len)
            b = a.substring(i, len);
        else
            b = "0";
        return b;
    }
	public static String getFormatDate(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static boolean hasNumber(String str) {
		boolean isNumber = false;
		for (int i = 0; i < str.length(); i++) {
			// 循环遍历字符串
			if (Character.isDigit(str.charAt(i))) {
				// 用char包装类中的判断数字的方法判断每一个字符
				isNumber = true;
			}
		}
		return isNumber;
	}

	public static String iso8859togbk(String strvalue) {
		// try {
		// if (strvalue == null)
		// return null;
		// else {
		// strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
		// return strvalue;
		// }
		// } catch (Exception e) {
		// return null;
		// }
		return strvalue;
	}

	public static String UTF8togbk(String strvalue) {
		// try {
		// if (strvalue == null)
		// return null;
		// else {
		// strvalue = new String(strvalue.getBytes("gbk"), "GBK");
		// return strvalue;
		// }
		// } catch (Exception e) {
		// return null;
		// }

		return strvalue;
	}

	public static String gbkto8859(String strvalue) {
		// try {
		// if (strvalue == null)
		// return null;
		// else {
		// strvalue = new String(strvalue.getBytes("gbk"), "ISO8859_1");
		// return strvalue;
		// }
		// } catch (Exception e) {
		// return null;
		// }
		return strvalue;
	}

	public static long datetotime(String date) {
		long time = 0;
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			time = date1.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	// 20101010转2010-10-10
	public static String tobzrq(String date) {

		date = date.substring(0, 4) + '-' + date.substring(4, 6) + '-'
				+ date.substring(6, 8);
		return date;
	}

	public static String tobzrqSf(String date) { // 20101010转2010-10-10 10:10

		date = date.substring(0, 4) + '-' + date.substring(4, 6) + '-'
				+ date.substring(6, 8) + " " + date.substring(8, 10) + ":"
				+ date.substring(10, 12);
		return date;
	}

	// 2010-10-10转20101010
	public static String toxkrq(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		date = sdf.format(Long.valueOf(Util.datetotime(date)));
		return date;
	}

	public static String byte2hex(byte[] b) // 二行制转字符串
	{

		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			// System.err.println(b[n]);
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;

		}
		return hs.toUpperCase();
	}

	public static String byte2hex(byte b) // 二行制转字符串
	{

		String hs = "";
		String stmp = "";
		// System.err.println(b[n]);
		stmp = (java.lang.Integer.toHexString(b & 0XFF));
		if (stmp.length() == 1)
			hs = hs + "0" + stmp;
		else
			hs = hs + stmp;

		return hs.toUpperCase();
	}

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		src = src.toUpperCase();
		int length = src.length() / 2;
		byte[] ret = new byte[length];
		byte[] tmp;
		try {
			tmp = src.getBytes("GBK");
			for (int i = 0; i < length; i++) {
				ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ret;
	}

	public static String tomd5(String plainText) {
		String str = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
			// System.out.println("result: " + buf.toString());// 32位的加密
			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return str;
	}

	public static String getPreviousMonthFirst() {

		String str = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();

		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号

		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号

		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());

		return str;

	}

	// 获得上月最后一天的日期

	public static String getPreviousMonthEnd() {

		String str = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();

		lastDate.add(Calendar.MONTH, -1);// 减一个月

		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天

		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天

		str = sdf.format(lastDate.getTime());

		return str;

	}

	// 获得下月最后一天的日期

	public static String getNextMonthEnd() {

		String str = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();

		lastDate.add(Calendar.MONTH, 1);// 减一个月

		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天

		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天

		str = sdf.format(lastDate.getTime());

		return str;

	}

	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	// 获取前一个月最后一天
	public static String getFirstOneMonthEndDate(String date) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Util.switchStringToCalendar(date);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 0);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	private static GregorianCalendar gc = null;

	private static void initCalendar(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("argument date must be not null");
		}
		gc.clear();
		gc.setTime(date);
	}

	static {
		gc = new GregorianCalendar(Locale.CHINA);
		gc.setLenient(true);
		gc.setFirstDayOfWeek(Calendar.MONDAY);
	}

	/** */
	/**
	 * 取得日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		int maxDaysOfMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		gc.add(Calendar.DAY_OF_MONTH, maxDaysOfMonth - dayOfMonth);
		return gc.getTime();
	}

	public static Date switchStringToDate(String sDate) {
		Date date = null;
		System.out.println("sDate " + sDate);
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format1.parse(sDate);

		} catch (Exception e) {
			System.out.println("日期转换失败: " + e.getMessage());
		}
		return date;
	}

	public static Calendar switchStringToCalendar(String sDate) {
		Date date = Util.switchStringToDate(sDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	/**
	 * 将一个日期转化成Calendar
	 * 
	 * @param date
	 *            Date
	 * @return Calendar
	 */
	public static Calendar switchStringToCalendar(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}

	// 取得某个时间后n天,格式为yyyy-mm-dd
	public static String getNDayAfterOneDate(String sDate, int n) {
		Calendar c = switchStringToCalendar(sDate);
		c.add(c.DAY_OF_MONTH, n);
		return c.get(c.YEAR) + "-" + (c.get(c.MONTH) + 1) + "-" + c.get(c.DATE);
	}

	// 发票int转string 并补足8位
	public static String fpIntToString(int fp) {
		String str = String.valueOf(fp);
		if (str == null) {
			str = "";
		}
		for (int i = str.length(); i < 8; i++) {
			str = "0" + str;
		}
		return str;
	}

	public static int getAsc(String st) {
		byte[] gc = st.getBytes();
		int asnum = (int) gc[0];
		// String str = Util.toHexString(String.valueOf(asnum).getBytes());
		// return Integer.parseInt(str);
		return asnum;
	}

	public static String toHexString(byte[] value) {
		String newString = "";
		for (int i = 0; i < value.length; i++) {
			byte b = value[i];
			String str = Integer.toHexString(b);
			if (str.length() > 2) {
				str = str.substring(str.length() - 2);
			}
			if (str.length() < 2) {
				str = "0" + str;
			}
			newString += str;
		}
		return newString.toUpperCase();
	}

	// 获取监控回传日期
	public static String hqjkhcrq() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// String xtrq = sdf.format(new Date());
		String newkpjzrq = "";

		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);

		if (month == 12) {
			newkpjzrq = (year + 1) + "-1-" + SYSTEM.ENDDAY;
		} else {
			newkpjzrq = year + "-" + (month + SYSTEM.ENDMONTH) + "-"
					+ SYSTEM.ENDDAY;
		}

		return Util.toxkrq(newkpjzrq);
	}

	// 登录密码算法
	public static String jiami(String username, String password, String key) {
		String encrypt = "";
		int mode = 40;
		int usernameLen = username.length();
		int passwordLen = password.length();
		int i = 0;
		int j = 0;
		long mm1 = 0;
		long mm2 = 0;

		for (i = 0; i < passwordLen; i++) {
			// System.out.println("mm1="+password.substring(i, i+1));
			mm1 = Util.getAsc(password.substring(i, i + 1));
			System.out.println("mm111=" + mm1);
			System.out.println("mm2=" + username.substring(j, j + 1));
			System.out.println("mm222="
					+ Util.getAsc(username.substring(j, j + 1)));
			mm2 = Util.getAsc(username.substring(j, j + 1));

			j++;
			if (j >= usernameLen) {
				j = 0;
			}

			mm1 = 7 * (mm1 + mm2);
			System.out.println("mm112=" + mm1);
			mm2 = mm1 % mode + mode * 2;
			mm1 = (int) Math.floor(mm1 / mode) + 80;
			System.out.println("Math.floor(mm1 / mode)="
					+ (int) Math.floor(mm1 / mode));
			System.out.println("mm2==" + mm2);
			System.out.println("mm1==" + mm1);
			encrypt = encrypt + (char) mm1 + (char) mm2;

			System.out.println("encrypt==" + encrypt);
		}
		return encrypt;
	}

	// 首位截字符串
	public static String substringFront(String str, int num) {
		String rt = str;

		if (str != null && !"".equals(str)) {
			int jqws = str.length() - num;
			if (jqws > 0) {
				rt = str.substring(jqws);
			}
		}
		return rt;
	}

	// 首位补0
	public static String zerosFront(String str, int num) {
		String rt = str;
		if (str != null && !"".equals(str)) {
			for (int i = str.length(); i < num; i++) {
				rt = "0" + rt;
			}
		}
		return rt;
	}

	public static String genRandomNum() {
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 10;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		/*
		 * char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		 * 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
		 * 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		 */

		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < 6) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	public static String subString(String text, int length) {
		int textLength = text.length();
		int byteLength = 0;
		StringBuffer returnStr = new StringBuffer();
		for (int i = 0; i < textLength && byteLength < length * 2; i++) {
			String str_i = text.substring(i, i + 1);
			if (str_i.getBytes().length == 1) {// 英文
				byteLength++;
			} else {// 中文
				byteLength += 2;
			}
			returnStr.append(str_i);
		}
		return returnStr.toString();
	}

	public static String numtochinese(String input) {
		// 单位数组
		String[] units = new String[] { "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿" };

		// 中文大写数字数组
		String[] numeric = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆",
				"柒", "捌", "玖" };

		String temp = "";
		String tempxs = "";
		String res = "";
		if (input.indexOf(".") >= 0) {
			System.out.println("123" + input.indexOf("."));
			String[] inputArr = input.split("\\.");
			temp = inputArr[0];
			tempxs = inputArr[1];
		} else {
			temp = input;
		}

		// 遍历一行中所有数字
		for (int k = -1; temp.length() > 0; k++) {
			// 解析最后一位
			int j = Integer.parseInt(temp.substring(temp.length() - 1, temp
					.length()));
			String rtemp = numeric[j];

			// 数值不是0且不是个位 或者是万位或者是亿位 则去取单位
			if (j != 0 && k != -1 || k % 8 == 3 || k % 8 == 7) {
				rtemp += units[k % 8];
			}

			// 拼在之前的前面
			res = rtemp + res;

			// 去除最后一位
			temp = temp.substring(0, temp.length() - 1);
		}

		// 去除后面连续的零零..
		while (res.endsWith(numeric[0])) {
			res = res.substring(0, res.lastIndexOf(numeric[0]));
		}

		// 将零零替换成零
		while (res.indexOf(numeric[0] + numeric[0]) != -1) {
			res = res.replaceAll(numeric[0] + numeric[0], numeric[0]);
		}

		// 将 零+某个单位 这样的窜替换成 该单位 去掉单位前面的零
		for (int m = 1; m < units.length; m++) {
			res = res.replaceAll(numeric[0] + units[m], units[m]);
		}

		// 取小数
		if (!"".equals(tempxs)) {
			if (!"00".equals(tempxs)) {
				res = res + "元"
						+ numeric[Integer.parseInt(tempxs.substring(0, 1))]
						+ "角"
						+ numeric[Integer.parseInt(tempxs.substring(1, 2))]
						+ "分";
			} else {
				res = res + "元 整";
			}
		}

		return res;
	}

	public static long getMonth(String startDate, String endDate) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		long monthday;
		try {
			Date startDate1 = f.parse(startDate);
			// 开始时间与今天相比较
			Date endDate1 = new Date();

			Calendar starCal = Calendar.getInstance();
			starCal.setTime(startDate1);

			int sYear = starCal.get(Calendar.YEAR);
			int sMonth = starCal.get(Calendar.MONTH);
			int sDay = starCal.get(Calendar.DATE);

			Calendar endCal = Calendar.getInstance();
			endCal.setTime(endDate1);
			int eYear = endCal.get(Calendar.YEAR);
			int eMonth = endCal.get(Calendar.MONTH);
			int eDay = endCal.get(Calendar.DATE);

			monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));

			if (sDay < eDay) {
				monthday = monthday + 1;
			}
			return monthday;
		} catch (ParseException e) {
			monthday = 0;
		}
		return monthday;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	 
	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		// date2 = date2==null?DateTest.getCurrentDate():date2;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;

		if (stype == 2) {
			n = (int) n / 365;
		}

		System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":"
				+ n);
		return n;
	}

	public static int compareData(String date1, String date2) {
		int a = -1;
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date dt1;
		Date dt2;

		try {
			dt1 = format.parse(date1);
			dt2 = format.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				a = 1;

			} else if (dt1.getTime() < dt2.getTime()) {

				a = -1;
			} else {
				a = 0;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a = 0;
		}
		return a;
	}

	// public static void main(String args[]) {
	// //
	// // String username = "xp";
	// // String password = "111111";
	// // String key = "1";
	// // String encrypt = Util.jiami(username, password, key);
	// // System.out.println("加密结果：" + encrypt);
	//
	// /*
	// * String str = "2010-9"; DateFormat format1 = new
	// * SimpleDateFormat("yyyy-MM"); Date date = null; try { date =
	// * format1.parse(str); } catch (ParseException e) { // TODO
	// * Auto-generated catch block e.printStackTrace(); } SimpleDateFormat
	// * fm1 = new SimpleDateFormat("yyyy-MM-dd");
	// * System.out.println("date="+fm1
	// * .format(Util.getLastMonthDay(date).getTime()));
	// */
	//
	// /*
	// * String time = "1292342400000"; SimpleDateFormat fm1 = new
	// * SimpleDateFormat("yyyy-MM-dd");
	// * System.out.println("date="+fm1.format(Long.parseLong(time)));
	// */
	//
	// // String time = "1292342400000";
	// // SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
	// // System.out.println("date==" + fm1.format(Long.parseLong(time)));
	// // String date = fm1.format(Long.parseLong(time));
	// // System.out.println("date2==" + Util.getNDayAfterOneDate(date, 1));
	// // Map m = Charset.availableCharsets();
	// // Set names = m.keySet();
	// // Iterator it = names.iterator();
	// // while (it.hasNext()) {
	// // System.out.println(it.next());
	// // }
	// // System.out.println();
	// // System.out.println(Util.getNextMonthEnd());
	// System.out.println(Util.numtochinese("12510000.12"));
	// }
	 
	public static String getSwjg(String nsrwjbm){
		String swjgbm="00000001";
		nsrwjbm=Util.zerosFront(nsrwjbm,16);
		String query_swjgbm=Query.getFieldStr("select top 1 swjgbm from skq_nsrxx where nsrwjbm like '%"+nsrwjbm+"%'", "swjgbm");
		if(!query_swjgbm.equals("")){
			swjgbm=query_swjgbm;
		}
		return swjgbm;
	}
	
	public static String getNsrsbh(String nsrwjbm){
		String nsrsbh="";
		nsrwjbm=Util.zerosFront(nsrwjbm,16);
		String query_nsrsbh=Query.getFieldStr("select top 1 nsrsbh from skq_nsrxx where nsrwjbm like '%"+nsrwjbm+"%'", "nsrsbh");
		if(!query_nsrsbh.equals("")){
			nsrsbh=query_nsrsbh;
		}
		return nsrsbh;
	}
	
	public static String getZfDate(int fphm){
		String date="";
		String query_date=Query.getFieldStr("select top 1 kprq from skq_fpkj where yfphm="+fphm +" ", "kprq");
		if(!query_date.equals("")){
			date=query_date;
		}
		return date;
	}
	public static String digitUppercase(double n){ 
		String fraction[] = {"角", "分"}; 
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" }; 
		String unit[][] = {{"元", "万", "亿"}, {"", "拾", "佰", "仟"}}; 
		String head = n < 0? "负": ""; 
		n = Math.abs(n); 
		String s = ""; 
		for (int i = 0; i < fraction.length; i++) { 
			s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", ""); 
		} 
		if(s.length()<1){ 
			s = "整";     
		} 
		int integerPart = (int)Math.floor(n); 
		for (int i = 0; i < unit[0].length && integerPart > 0; i++) { 
			String p =""; 
			for (int j = 0; j < unit[1].length && n > 0; j++) { 
				p = digit[integerPart%10]+unit[1][j] + p; 
				integerPart = integerPart/10; 
			} 
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s; 
	
		}
		return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整"); 
	}
	public static String getNsrmc(String nsrwjbm){
		String nsrmc="";
		nsrwjbm=Util.zerosFront(nsrwjbm,16);
		String query_nsrsbh=Query.getFieldStr("select top 1 nsrmc from skq_nsrxx where nsrwjbm like '%"+nsrwjbm+"%'", "nsrsbh");
		if(!query_nsrsbh.equals("")){
			nsrmc=query_nsrsbh;
		}
		return nsrmc;
	}
	/**
	 * 读取 SQL 文件，获取 SQL 语句 
	 * @param sqlFile
	 *            SQL 脚本文件
	 * @return List<sql> 返回所有 SQL 语句的 List
	 * @throws Exception
	 */
	public static List<String> loadSql(String sqlFile) throws Exception {
		List<String> sqlList = new ArrayList<String>();
		try {
			InputStream sqlFileIn = new FileInputStream(sqlFile);
			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}

			// Windows 下换行是 \r\n, Linux 下是 \n
			String[] sqlArr = sqlSb.toString()
					.split("(;\\s*\\r\\n)|(;\\s*\\n)");
			for (int i = 0; i < sqlArr.length; i++) {
				String sql = sqlArr[i].replaceAll("--.*", "").trim();
				if (!sql.equals("")) {
					sqlList.add(sql);
				}
			}
			return sqlList;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	/**
	 * 传入连接来执行 SQL 脚本文件，这样可与其外的数据库操作同处一个事物中
	 * 
	 * @param conn
	 *            传入数据库连接
	 * @param sqlFile 
	 *            SQL 脚本文件    可选参数，为空字符串或为null时 默认路径为 src/test/resources/config/script.sql
	 * @throws Exception
	 */
	public static void execute(Connection conn,String sqlFile) throws Exception {
		Statement stmt = null;
		if(sqlFile==null||"".equals(sqlFile)){
			sqlFile="update.sql";
		}
		List<String> sqlList = loadSql(sqlFile);
		stmt = conn.createStatement();
		for (String sql : sqlList) {
			stmt.addBatch(sql);
		}
		int[] rows = stmt.executeBatch();
		System.out.println("Row count:" + Arrays.toString(rows));
	}
	public static void main(String[] args) throws Exception {
					List<String> sqlList = loadSql("E:\\companyProject\\中石化后台\\sourcecode\\javaskweb\\src\\update.sql");
		             System.out.println("size:" + sqlList.size());
		             for (String sql : sqlList) {
		                 System.out.println(sql);
		                 Query.updateField(sql);
		             }
		 
		  

	}
}
