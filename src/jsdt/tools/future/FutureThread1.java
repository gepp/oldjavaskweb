package jsdt.tools.future;

public class FutureThread1 {
		public FutureData1 getResult(final String sql,final String  fieldname){
			final FutureData1 data=new FutureData1();
			new Thread(){
				public void run() {
					
					System.out.println("�߳�"+Thread.currentThread().getId()+"����");
					RealData realData=new RealData(sql, fieldname);
					System.out.println("realData1���");
					data.setResult(realData);
					
				};
			}.start();
		  
			
			return data;
		}
}
