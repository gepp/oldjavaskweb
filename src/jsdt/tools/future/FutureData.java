package jsdt.tools.future;


public class FutureData implements FutureQuery {
	private boolean isReady=false;
	private RealData realData=null;
	public synchronized Object getResult() {
		if(!isReady){
			try {
				System.out.println("==wait==");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("wait½áÊø");
		return realData.getResult();
	}
	
	public synchronized void setResult(RealData data){
		if(isReady){
			return;
		}else{
			isReady=true;
			System.out.println("setResult Íê³É");
			this.realData=data;
			notifyAll();
		}
	}

}
