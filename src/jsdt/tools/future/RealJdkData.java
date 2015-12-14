package jsdt.tools.future;

import java.util.concurrent.Callable;

import jsdt.tools.Query;

public class RealJdkData implements Callable<Double> {
	String sql="";
	String field="";
	public  RealJdkData(String sql,String field){
		this.sql=sql;
		this.field=field;
	}
	public Double call() throws Exception {
		return  Query.getFieldDouble(sql, field);
	}

}
