package jsdt.tools;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class JProcessWait extends JFrame implements ActionListener, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	JProgressBar jb;
	public JButton jbu;
	boolean flag = false;
	int i = 0;
	int maxSize = 0;
	int minSize = 0;
	JPanel jp;
	int countSum;
	int timer;
	private JPanel jContentPane = null;
	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getCountSum() {
		return countSum;
	}

	public void setCountSum(int countSum) {
		this.countSum = countSum;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void genJProcessWait() {
//		jContentPane = new JPanel();
//		jContentPane.setLayout(null);
//		jContentPane.setBackground(Color.WHITE);
//		jContentPane.add(jb);
//		jContentPane.add(jbu);
//		jContentPane.add(jp);
		System.out.println("countSum:==" + countSum);
		if (getCountSum() >= 10000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("10000"));
		} else if (getCountSum() >= 8000 && countSum < 10000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("8000"));
		} else if (getCountSum() >= 8000 && countSum < 10000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("8000"));
		} else if (getCountSum() >= 6000 && countSum < 8000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("6000"));
		} else if (getCountSum() >= 4000 && countSum < 6000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("4000"));
		} else if (getCountSum() >= 2000 && countSum < 4000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("2000"));
		} else if (getCountSum() >= 0 && countSum < 2000) {
			timer = Integer.parseInt(Env.getInstance().getProperty("1000"));
		} else {
			timer = 1;
		}
		int x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().width);
		int y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().height);
		jp = new JPanel();
		jb = new JProgressBar();
		jb.setStringPainted(true);
		jb.setString("正在分析，请稍等");
		jb.setMaximum(this.getMaxSize());
		jb.setMinimum(this.getMinSize());
		jbu = new JButton("运行");
		jp.add(jb);
		add(jp, BorderLayout.CENTER);
		add(jbu, BorderLayout.SOUTH);
		this.setSize(300, 70);
		this.setVisible(true);
		jbu.addActionListener(this);
		this.setLocation((x_size - 60) / 2, (y_size - 350) / 2);
		jb.setValue(0);
	}

	public void run() {
		while (i <= 99) {
			jb.setValue(++i);
			jb.setString("正在上传，请稍等...");
			jb.setString("剩余时间:"
					+ Math.round((new BigDecimal(60 * timer)
							.subtract((new BigDecimal(i)
									.multiply(new BigDecimal(timer * 60)
											.divide(new BigDecimal(100))))))
							.divide(new BigDecimal("60"), 2,
									BigDecimal.ROUND_HALF_UP).floatValue())
					+ "分钟" + i + "%");
			if (i > (timer * 60 - 10)) {
				jb.setString("剩余时间1分钟" + i + "%");
				jb.setValue(i);
			}
			try {
				Thread.sleep((long) (1000 * (new BigDecimal(timer * 60)
						.divide(new BigDecimal(100)).floatValue())));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		this.flag = true;
		Thread t = new Thread(this);
		t.start();

	}

	public static void main(String[] args) {
		JProcessWait jt = new JProcessWait();
		jt.setMaxSize(100);
		jt.setMinSize(0);
		jt.setCountSum(12000);
		jt.genJProcessWait();
		jt.jbu.setVisible(true);
		jt.jbu.doClick();
		jt.setTitle("上传文件");
		 jt.dispose();
		jt.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}