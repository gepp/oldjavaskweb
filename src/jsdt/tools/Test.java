package jsdt.tools;

import java.util.ArrayList;

import jsdt.model.Sjyz;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
	
		Sjyz yz = new Sjyz();
		//ϵͳ����
		//˰��˰Ŀ��
	 
		//��ѯ�����ݿ�
		ArrayList alszsm = yz.selectSzsm();
		//���������ݿ�
		int szsmResult = yz.insertSzsm(alszsm);
		
		//ע������
		//��ѯ�����ݿ�
		ArrayList alZclx = yz.selectZclx();
		//���������ݿ�
		int zclxResult = yz.insertZclx(alZclx);
		
		//��˰����Ϣ����
		ArrayList alnsrxx = yz.selectNsrxx();
		//���������ݿ�
		int nsrxxResult = yz.insertNsrxx(alnsrxx);
		
		
	}

}
