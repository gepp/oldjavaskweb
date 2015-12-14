package jsdt.tools.future;

import jsdt.tools.Query;

public class Main {
	public static void main(String[] args) {
		String sql="select top 100  * from SKQ_FPKJ a where a.STATUS=1 and a.KPLX=1  order by kprq";
		String fieldname="sid";
		String  sql1="select sum(a.HJZJE) as tpzje from SKQ_FPKJ a where a.STATUS=1 and a.KPLX=1";
		String  fieldname1="tpzje";
		
		FutureThread thread=new FutureThread();
		FutureThread1 thread1=new FutureThread1();
		long start=System.currentTimeMillis();
		long end=0;
		FutureData data=thread.getResult(sql, fieldname);
		System.out.println("����ִ��");
		FutureData1 data1=thread1.getResult(sql1, fieldname1);
		System.out.println("����ִ��");
		start=System.currentTimeMillis();
		System.out.println("data:"+data.getResult());
		end=System.currentTimeMillis()-start;
		System.out.println("data:"+end);
		start=System.currentTimeMillis();
		System.out.println("data1:"+data1.getResult());
		end=System.currentTimeMillis()-start;
		System.out.println("data1:"+end);
		
		end=System.currentTimeMillis()-start;
		System.out.println("���߳����ģ�"+end);
		
		System.out.println("============================");
		System.out.println("�Ƕ��߳�����");
		start=System.currentTimeMillis();
		System.out.println(Query.getFieldDouble(sql, fieldname));
		end=System.currentTimeMillis()-start;
		System.out.println("Query���ģ�"+end);
		start=System.currentTimeMillis();
		System.out.println(Query.getFieldDouble(sql1, fieldname1));
		end =System.currentTimeMillis()-start;
		System.out.println("�Ƕ��߳����ģ�"+end);
	}
}
