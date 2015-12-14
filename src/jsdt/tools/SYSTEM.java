package jsdt.tools;

import java.util.HashMap;

public class SYSTEM {
//	public static String WJBMBC="07010000";
	//public static String WJBMBC="0000000";
	public static int ENDMONTH= 1 ;
	public static int ENDDAY= 10 ;
	public static int FPZS= 200 ;
	public static int KYXNF = 10;
	public static String SBFS ="1";
	public static String MXSBBZ ="3";
	public static int TPXEBL = 20;
	public static String CZYBM = "B001001";
	public static String BDCSJ = "999999999999";
	public static String FPBLJQBH = "9999999999999999";
	public static String SJIP = "143.1";
	public static String DLM = "dtsk";
	public static String CSHSKKH = "0000000000000000";
	public static String CSHJQBH = "000000000000";
	public static String DBURL ="jdbc:sybase:Tds:192.168.1.185:5000/ahtax2009?charset=cp850&jconnect_version=3";
	
	public static String TYFPBM = "900";
	public static int TOPNUM = 10;
	public static int TOPMAXNUM = 30;
	public static int PAGESIZE = 10;
	public static int PAGENO = 1;
	
	public static int HOUROFDAY=0;
	public static int MINUTE=0;
	public static int PERIOD=0;
	
	public static double MAXKPXE = 42949672.95;
	public static HashMap XMLXHM = new HashMap();
	static {
		XMLXHM.put("1", "房地产工程");
		XMLXHM.put("2", "城市基础设施建设工程");
		XMLXHM.put("3", "企业设立改造工程");
		XMLXHM.put("4", "其他");
	}
}
