package jsdt.tools.future;

public class FutureThread {
		public FutureData getResult(final String sql,final String  fieldname){
			final FutureData data=new FutureData();
			new Thread(){
				public void run() {
					
					System.out.println("线程"+Thread.currentThread().getId()+"启动");
					RealData realData=new RealData(sql, fieldname);
					System.out.println("realData完成");
					data.setResult(realData);
					
				};
			}.start();
		  
			
			return data;
		}
}
