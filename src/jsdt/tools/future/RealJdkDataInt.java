package jsdt.tools.future;

import java.util.concurrent.Callable;

import jsdt.model.baseClass;

public class RealJdkDataInt implements Callable<Integer> {
	String sql="";
	 
	public  RealJdkDataInt(String sql){
		this.sql=sql;
	 
	}
	public Integer call() throws Exception {
		return  baseClass.getFieldCount(sql);
	}

}
