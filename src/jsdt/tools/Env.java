package jsdt.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * ��ȡ�����ļ�
 * @author Administrator
 *
 */
public class Env extends Properties {

	private static final long serialVersionUID = 1L;
	
	private static Env instance=null;
	
	/**
	 * ˽�й���
	 */
	private Env(){
		InputStream is = getClass().getResourceAsStream("/db.properties");
		try {
			this.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��ʼ��
	 * @return
	 */
	public static Env getInstance(){
		if(instance==null){
			makeInstance();
			return instance;	//�Ե���ģʽ����
		}else{
			return instance;
		}
	}
	
	//����ģʽ ͬ������,��֤��ͬһʱ��,ֻ�ܱ�һ���˵���
	private static synchronized void makeInstance() {
		if(instance==null){
			instance = new Env();
		}
	}
}
