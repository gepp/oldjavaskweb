package jsdt.tools.future;

public class FutureThread {
		public FutureData getResult(final String sql,final String  fieldname){
			final FutureData data=new FutureData();
			new Thread(){
				public void run() {
					
					System.out.println("�߳�"+Thread.currentThread().getId()+"����");
					RealData realData=new RealData(sql, fieldname);
					System.out.println("realData���");
					data.setResult(realData);
					
				};
			}.start();
		  
			
			return data;
		}
}
