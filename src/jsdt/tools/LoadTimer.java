package jsdt.tools;

import java.util.Timer;
import java.util.TimerTask;

public class LoadTimer {
	public static void main(String[] args) throws Exception {

		LoadTimer tt = new LoadTimer();

		tt.vick(5,200);

	}

	public void vick(int minSize,int maxSize) throws Exception {

		Timer timer = new Timer();
		MyTimerTask timerTask = new MyTimerTask(minSize, maxSize);
		long cycle = 10 * 1000; // ѭ�����õ�ʱ����
		timer.schedule(timerTask, 0, cycle);

	}

}
