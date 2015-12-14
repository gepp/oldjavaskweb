package jsdt.tools.future;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import jsdt.model.cxtj.cxFpkj;
import jsdt.tools.Query;

public class RealJdkDataArrayList implements Callable<ArrayList> {
	String sql_select="";
	 
	public  RealJdkDataArrayList(String sql){
		this.sql_select=sql;
		 
	}
	public ArrayList call() throws Exception {
		return new cxFpkj().selectFpkj(sql_select);
	}

}
