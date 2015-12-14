package jsdt.tools.future;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import jsdt.model.cxtj.cxFpkj;
import jsdt.tools.Query;

public class Main1 {
public static void main(String[] args) throws InterruptedException, ExecutionException {
	long begin=System.currentTimeMillis();
	long end=0;
	String sql="select top 100  * from SKQ_FPKJ a where a.STATUS=1 and a.KPLX=1  order by kprq";
	String fieldname="sid";
	String  sql1="select sum(a.HJZJE) as tpzje from SKQ_FPKJ a where a.STATUS=1 and a.KPLX=1";
	String  fieldname1="tpzje";
	String sql2="SELECT top 100 SID  FROM SKQ_FPKJ a  where 1=1  and NSRWJBM ='0000000055580114' and KPRQ>='2010-08-01 00:00:00' and KPRQ<='2011-06-01 23:59:59' and (a.SID not in (select top 10 sid from SKQ_FPKJ a  where 1=1  and NSRWJBM ='0000000055580114' and KPRQ>='2010-08-01 00:00:00' and KPRQ<='2011-06-01 23:59:59' order by FPDM,FPHM asc ))  order by FPDM,FPHM asc ";
	FutureTask<Double> task=new FutureTask<Double>(new RealJdkData(sql, fieldname));
	FutureTask<ArrayList> task1=new FutureTask<ArrayList>(new RealJdkDataArrayList(sql2));
	FutureTask<Double> task2=new FutureTask<Double>(new RealJdkData(sql1, fieldname1));
	ExecutorService service=Executors.newFixedThreadPool(3);
	
	service.submit(task);
	service.submit(task1);
	service.submit(task2);
	
	double a=task.get();
	ArrayList list=task1.get();
	double b=task2.get();
	
	end=System.currentTimeMillis()-begin;
	System.out.println("a:"+a);
	System.out.println("b:"+b);
	System.out.println("c:size:"+list.size());
	System.out.println("jdk共计耗时："+end);
	System.out.println("================");
	
	begin=System.currentTimeMillis();
	System.out.println(Query.getFieldDouble(sql, fieldname));
	end=System.currentTimeMillis()-begin;
	System.out.println("sql消耗："+end);
	
	begin=System.currentTimeMillis();
	System.out.println(Query.getFieldDouble(sql1, fieldname1));
	end=System.currentTimeMillis()-begin;
	System.out.println("sql1消耗："+end);
	
	begin=System.currentTimeMillis();
	System.out.println(new cxFpkj().selectFpkj(sql2).size());
	end =System.currentTimeMillis()-begin;
	System.out.println("sql2："+end);
}
}
