package jsdt.tools.future;


public class FutureData1 implements FutureQuery {
	private boolean isReady=false;
	private RealData realData=null;
	public synchronized Object getResult() {
		if(!isReady){
			try {
				System.out.println("==wait1==");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("wait1½áÊø");
		return realData.getResult();
	}
	
	public synchronized void setResult(RealData data){
		if(isReady){
			return;
		}else{
			isReady=true;
			System.out.println("setResult1 Íê³É");
			this.realData=data;
			notifyAll();
		}
	}

}
