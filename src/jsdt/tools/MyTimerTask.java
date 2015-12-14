package jsdt.tools;

import java.math.BigDecimal;
import java.util.TimerTask;
public class MyTimerTask extends TimerTask{
//	 private int i=1;
	private int maxSize=0;
    private	 int minSize=0;
     public MyTimerTask(int minSize,int maxSize){
    	 this.maxSize=maxSize;
    	 this.minSize=minSize;
     }
	    public int getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public int getMinSize() {
		return minSize;
	}
	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}
		public void run() {
//	       System.out.println("这是第 "+i+" 次调用，时间为:"+new Date());
//
//	       i++;
	  	 JProcessLoad jt = new JProcessLoad();
         jt.setTitle("上传文件");
         jt.setMaxSize(this.maxSize);
         jt.setMinSize(this.minSize);
         jt.genJProcessLoad();
         jt.jbu.setVisible(false);
         jt.jbu.doClick();
         try {
			Thread.sleep(1000);
			jt.jb.setVisible(false);
			jt.dispose();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
		public static void main(String[] args) {
			System.out.println((new BigDecimal(5).divide(new BigDecimal(100))));
		}


}
