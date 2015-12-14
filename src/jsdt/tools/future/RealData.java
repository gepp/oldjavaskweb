package jsdt.tools.future;

import jsdt.tools.Query;

public class RealData implements FutureQuery {
	public Object o=null;
	String sql="";
	String fieldname="";
	public  RealData(String sql,String fieldname){
		this.sql=sql;
		this.fieldname=fieldname;
	}
	public Object getResult() {
		long start=System.currentTimeMillis();
		 
		double a=Query.getFieldDouble(sql, fieldname);
		long end=System.currentTimeMillis()-start;
		System.out.println("real_end:"+end);
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return a;
	}
	
	public Object getO(){
		return o;
	}
	

}
