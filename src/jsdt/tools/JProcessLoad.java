package jsdt.tools;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class JProcessLoad extends JFrame implements ActionListener, Runnable {

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
 int maxSize=0;
 int minSize=0;
 JPanel jp ;
 public JPanel getJp() {
	return jp;
}

public void setJp(JPanel jp) {
	this.jp = jp;
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

public JProcessLoad(){
	
}
public void genJProcessLoad() {
	 int x_size = (int)(Toolkit.getDefaultToolkit().getScreenSize().width);
	  int 	y_size = (int)(Toolkit.getDefaultToolkit().getScreenSize().height);
  jp=new JPanel();
  jb = new JProgressBar();
  jb.setStringPainted(true);
	 jb.setString("上传进度：");
	   jb.setMaximum(100);
	   jb.setMinimum(0);
  jbu = new JButton("运行");
  jp.add(jb);
  add(jp, BorderLayout.CENTER);
  add(jbu, BorderLayout.SOUTH);
  this.setSize(300, 100);
  this.setVisible(true);
  jbu.addActionListener(this);
  this.setLocation((x_size-60)/2, (y_size-355)/2);
  System.out.println("start");
  jb.setValue(0);
  jb.setStringPainted(true);

 }

 public void run() {
   while(i<=99){
//    System.out.println("run方法里!");
    //jb.setValue(++i);
	   jb.setValue((int) Math.floor(minSize*100/maxSize));
    jb.setString("上传进度："+minSize+"/"+maxSize+"  "+(new BigDecimal(minSize*100).divide(new BigDecimal(maxSize))) +"%");
   if(i==99){
	   this.dispose();
   }
	   try {
   // Thread.sleep(1000);
   } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  // jb.setStringPainted(true);
   }
  // System.out.println("走出RUN方法！");
  System.out.println("fuck");
 }

 public void actionPerformed(ActionEvent e) {
  this.flag = true;
  Thread t = new Thread(this);
  t.start();
 }

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  JProcessLoad jt = new JProcessLoad();
  jt.setMaxSize(200);
  jt.setMinSize(5);
  jt.genJProcessLoad();
  jt.setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
}