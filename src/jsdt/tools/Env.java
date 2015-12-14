package jsdt.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 读取属性文件
 * @author Administrator
 *
 */
public class Env extends Properties {

	private static final long serialVersionUID = 1L;
	
	private static Env instance=null;
	
	/**
	 * 私有构造
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
	 * 初始化
	 * @return
	 */
	public static Env getInstance(){
		if(instance==null){
			makeInstance();
			return instance;	//以单例模式创建
		}else{
			return instance;
		}
	}
	
	//单例模式 同步方法,保证在同一时间,只能被一个人调用
	private static synchronized void makeInstance() {
		if(instance==null){
			instance = new Env();
		}
	}
}
