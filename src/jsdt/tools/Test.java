package jsdt.tools;

import java.util.ArrayList;

import jsdt.model.Sjyz;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
	
		Sjyz yz = new Sjyz();
		//系统表导入
		//税种税目表
	 
		//查询老数据库
		ArrayList alszsm = yz.selectSzsm();
		//插入新数据库
		int szsmResult = yz.insertSzsm(alszsm);
		
		//注册类型
		//查询老数据库
		ArrayList alZclx = yz.selectZclx();
		//插入新数据库
		int zclxResult = yz.insertZclx(alZclx);
		
		//纳税户信息导入
		ArrayList alnsrxx = yz.selectNsrxx();
		//插入新数据库
		int nsrxxResult = yz.insertNsrxx(alnsrxx);
		
		
	}

}
