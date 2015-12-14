package jsdt.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.model.cxtj.cxFpkj;
import jsdt.tools.Query;
import jsdt.tools.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteExcelFpkj {
	public void getExcel(String sheetName, OutputStream output,String nsrwjbm,String startdate,String enddate,String fpqsh,String fpjzh,String fpdm,String skygh) {
		String strSql = "";
		System.out.println("��Ʊ����");
		String sqlNsr = "";
		if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
			for(int i=nsrwjbm.length();i<16;i++){
				nsrwjbm="0"+nsrwjbm;
			}
			nsrwjbm=Query.getFieldStr("select nsrwjbm from skq_nsrxx where nsrwjbm like '%"+nsrwjbm+"%'","nsrwjbm");
			sqlNsr = " and a.NSRWJBM = '"+nsrwjbm+"'";
		}
		
		strSql = sqlNsr+" and a.NSRWJBM in(select NSRWJBM from SKQ_NSRXX where STATUS=1)";
		
		if(startdate!=null&&!"".equals(startdate)){
			strSql = strSql+" and a.KPRQ>='"+startdate+" 00:00:00'";
		}
		if(enddate!=null&&!"".equals(enddate)){
			strSql = strSql+" and a.KPRQ<='"+enddate+" 23:59:59'";
		}
		if(fpdm!=null&&!"".equals(fpdm)){
			strSql = strSql+" and a.FPDM like '%"+fpdm+"%'";
		}
		if(fpqsh!=null&&!"".equals(fpqsh)){
			strSql = strSql+" and a.FPHM >="+Integer.parseInt(fpqsh) ;
		}
		if(fpjzh!=null&&!"".equals(fpjzh)){
			strSql = strSql+" and a.FPHM <="+Integer.parseInt(fpjzh) ;
		}
		if(skygh!=null&&!"".equals(skygh)){
			strSql = strSql+" and a.sky  like '%"+skygh+"%'";
		}
	 
		cxFpkj cx =new cxFpkj();
		ArrayList alFpkj = cx.selectFpkjDcExcel(strSql);
		System.out.println("������ϸ����"+alFpkj.size());
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		 
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alFpkj!=null&&!alFpkj.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			//��˰��΢������
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("��˰��΢������");
			
			//��˰������
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("��˰������");
			
			//��Ʊ����
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("��Ʊ����");
			
			//��Ŀ����
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("��Ŀ����");
			//����
			HSSFCell cell_90 = row0.createCell(4);
			cell_90.setCellValue("����");
			//����
			HSSFCell cell_100 = row0.createCell(5);
			cell_100.setCellValue("����");
			
			//��Ʊʱ��
			HSSFCell cell_40 = row0.createCell(6);
			cell_40.setCellValue("��Ʊʱ��");
			
			//��Ʊ����
			HSSFCell cell_50 = row0.createCell(7);
			cell_50.setCellValue("��Ʊ����");
			
			//���
			HSSFCell cell_60 = row0.createCell(8);
			cell_60.setCellValue("���");
			
			//�ܽ��
			HSSFCell cell_210 = row0.createCell(9);
			cell_210.setCellValue("�ܽ��");
			
			//�ۿ��ܽ��
			HSSFCell cell_200= row0.createCell(10);
			cell_200.setCellValue("�ۿ��ܽ��");
			//�տ�Ա����
			
			
			HSSFCell cell_70 = row0.createCell(11);
			cell_70.setCellValue("�տ�Ա����");
			
			//���λ����
			HSSFCell cell_80 = row0.createCell(12);
			cell_80.setCellValue("���λ����");
			
			HSSFCell cell_110 = row0.createCell(13);
			cell_110.setCellValue("����");
			
			Iterator it = alFpkj.iterator();
			int i = 1;
			int befor_fphm=0;
			int now_fphm=0;
			String befor_fphm_string="";
			String now_fphm_string="";
			String hjzje_string="";
			String zkzje_string="";
			while(it.hasNext()){
				Fpkj fpkj = (Fpkj)it.next();
				double dj=fpkj.getDj();
				double sl=fpkj.getSl();
				double zkzje=fpkj.getZkzje();
				if(zkzje==0){
					zkzje=0.00;
				}
				String zkzjes=dg.format(zkzje); 
			
				HSSFRow row = sheet1.createRow(i);
				
				int kplx = fpkj.getKplx();
				String kplxmc = "����Ʊ";
				String hjzje = "0";
				 
				if(kplx==1){
					kplxmc = "����Ʊ";
					hjzje = dg.format(fpkj.getHjzje());
					 
				}
				else if(kplx==2){
					kplxmc = "��Ʊ";
					hjzje = dg.format(fpkj.getHjzje()*-1);
				 
				}
				else if(kplx==3){
					kplxmc = "��Ʊ";
					hjzje = "0.00";
					 
				}
				else{
				 
				}
				if(befor_fphm==0)
				{
				befor_fphm=fpkj.getFphm();
				now_fphm=fpkj.getFphm();
				now_fphm_string=Util.fpIntToString(now_fphm);
				hjzje_string=""+hjzje+""+"";
				zkzje_string=zkzjes;
				}
				else{
					now_fphm=fpkj.getFphm();
					if(now_fphm==befor_fphm){
						now_fphm_string="";
						hjzje_string="";
						zkzje_string="";
					}else{
						now_fphm_string=Util.fpIntToString(now_fphm);
						hjzje_string=""+hjzje+""+"";
						befor_fphm=now_fphm;
						zkzje_string=zkzjes;
					}
				}
				//��˰��΢������
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpkj.getNsrwjbm());
				System.out.println("��˰��΢�����룺"+fpkj.getNsrwjbm());
				//��˰������
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(fpkj.getNsrmc());
				System.out.println("���ƣ�"+fpkj.getNsrmc());
				//��Ʊ����
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(Util.fpIntToString(fpkj.getFphm()));
				System.out.println(Util.fpIntToString(fpkj.getFphm()));
				//��Ŀ����
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpkj.getXmmc());
				System.out.println(fpkj.getXmmc());
				//����
				HSSFCell cell_9 = row.createCell(4);
				cell_9.setCellValue(fpkj.getDj());
				System.out.println(fpkj.getDj());
				//����
				HSSFCell cell_11 = row.createCell(5);
				cell_11.setCellValue(fpkj.getSl());
				System.out.println(fpkj.getSl());
				//��Ʊʱ��
				HSSFCell cell_4 = row.createCell(6);
				cell_4.setCellValue(fpkj.getKprq());
				System.out.println(fpkj.getKprq());
				String sky=fpkj.getSky();
				String gh="";
				 if(sky.length()>=2){
				if(Util.hasNumber(sky)){
					if(sky.equals("000000")){
						 gh="01";
						 sky="000000";
					}else{
					 gh=sky.substring(0,2);
					 sky=sky.substring(2,sky.length());
					}
				}else{
					gh="";
				}
				 }
				//��Ʊ����
				HSSFCell cell_5 = row.createCell(7);
				cell_5.setCellValue(kplxmc);
				System.out.println(kplxmc);
				//�ܽ��
				HSSFCell cell_6 = row.createCell(8);
				cell_6.setCellValue(hjzje_string);
				
				HSSFCell cell_202 = row.createCell(9);
				cell_202.setCellValue(hjzje_string);
				
				//�ۿ��ܽ��
				HSSFCell cell_201 = row.createCell(10);
				cell_201.setCellValue(zkzje_string);
				System.out.println(hjzje);
				//�տ�Ա����
				boolean hasNum=Util.hasNumber(sky);
				System.out.println(sky);
				if(hasNum==true){
				HSSFCell cell_7 = row.createCell(11);
				cell_7.setCellValue(sky);
				}else{
					HSSFCell cell_7 = row.createCell(11);
					cell_7.setCellValue(sky);
				}
				//���λ����
				
				String fkdw="";
					if(fpkj.getFkdw().equals("000000")){
						fkdw="����";
					}else{
						fkdw=fpkj.getFkdw();
					}
					System.out.println(fkdw);
				HSSFCell cell_8 = row.createCell(12);
				cell_8.setCellValue(fkdw);
				System.out.println();
				//����
				 
					HSSFCell cell_12 = row.createCell(13);
					cell_12.setCellValue(gh);
				 
				
				System.out.println(gh);
				i++;
			}
		}		

		try {
		  
			output.flush();
			wb.write(output);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		}
	}
	
	public void getExcel1(String sheetName, OutputStream output,String nsrwjbm,String startdate,String enddate,String fpqsh,String fpjzh,String fpdm,String skygh) {
		String strSql = "";
		System.out.println("��Ʊ����");
		String sqlNsr = "";
		if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
			for(int i=nsrwjbm.length();i<16;i++){
				nsrwjbm="0"+nsrwjbm;
			}
			
			sqlNsr = " and a.NSRWJBM like '%"+nsrwjbm+"%'";
		}
		
		strSql = sqlNsr+" and a.NSRWJBM in(select NSRWJBM from SKQ_NSRXX where STATUS=1)";
		
		if(startdate!=null&&!"".equals(startdate)){
			strSql = strSql+" and a.KPRQ>='"+startdate+" 00:00:00'";
		}
		if(enddate!=null&&!"".equals(enddate)){
			strSql = strSql+" and a.KPRQ<='"+enddate+" 23:59:59'";
		}
		if(fpdm!=null&&!"".equals(fpdm)){
			strSql = strSql+" and a.FPDM like '%"+fpdm+"%'";
		}
		if(fpqsh!=null&&!"".equals(fpqsh)){
			strSql = strSql+" and a.FPHM >="+Integer.parseInt(fpqsh) ;
		}
		if(fpjzh!=null&&!"".equals(fpjzh)){
			strSql = strSql+" and a.FPHM <="+Integer.parseInt(fpjzh) ;
		}
		if(skygh!=null&&!"".equals(skygh)){
			strSql = strSql+" and a.sky  like '%"+skygh+"%'";
		}
		
		cxFpkj cx =new cxFpkj();
		ArrayList alFpkj = cx.selectFpkjDcExcel(strSql);
		System.out.println("������ϸ����"+alFpkj.size());
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		 
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alFpkj!=null&&!alFpkj.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			//��˰��΢������
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("��˰��΢������");
			
			//��˰������
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("��˰������");
			
			//��Ʊ����
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("��Ʊ����");
			
			//��Ŀ����
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("��Ŀ����");
			//����
			HSSFCell cell_90 = row0.createCell(4);
			cell_90.setCellValue("����");
			//����
			HSSFCell cell_100 = row0.createCell(5);
			cell_100.setCellValue("����");
			
			//��Ʊʱ��
			HSSFCell cell_40 = row0.createCell(6);
			cell_40.setCellValue("��Ʊʱ��");
			
			//��Ʊ����
			HSSFCell cell_50 = row0.createCell(7);
			cell_50.setCellValue("��Ʊ����");
			
			//�ܽ��
			HSSFCell cell_60 = row0.createCell(8);
			cell_60.setCellValue("�ܽ��");
			
			//�տ�Ա����
			
			
			HSSFCell cell_70 = row0.createCell(9);
			cell_70.setCellValue("�տ�Ա����");
			
			//���λ����
			HSSFCell cell_80 = row0.createCell(10);
			cell_80.setCellValue("���λ����");
			
			HSSFCell cell_110 = row0.createCell(11);
			cell_110.setCellValue("����");
			
			Iterator it = alFpkj.iterator();
			int i = 1;
			while(it.hasNext()){
				Fpkj fpkj = (Fpkj)it.next();
				HSSFRow row = sheet1.createRow(i);
				
				int kplx = fpkj.getKplx();
				String kplxmc = "����Ʊ";
				String hjzje = "0";
				if(kplx==1){
					kplxmc = "����Ʊ";
					hjzje = dg.format(fpkj.getHjzje());
					System.out.println("��Ʊ���ͱ���"+kplx);
					System.out.println("hjzje="+fpkj.getHjzje());
				}
				else if(kplx==2){
					kplxmc = "��Ʊ";
					hjzje = dg.format(fpkj.getHjzje()*-1);
					System.out.println("��Ʊ���ͱ���"+kplx);
					System.out.println("hjzje="+fpkj.getHjzje());
				}
				else if(kplx==3){
					kplxmc = "��Ʊ";
					hjzje = "0.00";
					System.out.println("��Ʊ���ͱ���"+kplx);
					System.out.println("hjzje="+fpkj.getHjzje());
				}
				else{
				System.out.println("��Ʊ���ͱ���"+kplxmc);
				System.out.println("hjzje="+00);
				}
				
				
				//��˰��΢������
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpkj.getNsrwjbm());
				System.out.println("��˰��Σ�����룺"+fpkj.getNsrwjbm());
				//��˰������
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(fpkj.getNsrmc());
				System.out.println("���ƣ�"+fpkj.getNsrmc());
				//��Ʊ����
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(Util.fpIntToString(fpkj.getFphm()));
				System.out.println(Util.fpIntToString(fpkj.getFphm()));
				//��Ŀ����
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpkj.getXmmc());
				System.out.println(fpkj.getXmmc());
				//����
				HSSFCell cell_9 = row.createCell(4);
				cell_9.setCellValue(fpkj.getDj());
				System.out.println(fpkj.getDj());
				//����
				HSSFCell cell_11 = row.createCell(5);
				cell_11.setCellValue(fpkj.getSl());
				System.out.println(fpkj.getSl());
				//��Ʊʱ��
				HSSFCell cell_4 = row.createCell(6);
				cell_4.setCellValue(fpkj.getKprq());
				System.out.println(fpkj.getKprq());
				String sky=fpkj.getSky();
				String gh="";
				 if(sky.length()>=2){
				if(Util.hasNumber(sky)){
					if(sky.equals("000000")){
						 gh="01";
						 sky="000000";
					}else{
					 gh=sky.substring(0,2);
					 sky=sky.substring(2,sky.length());
					}
				}else{
					gh="";
				}
				 }
				//��Ʊ����
				HSSFCell cell_5 = row.createCell(7);
				cell_5.setCellValue(kplxmc);
				System.out.println(kplxmc);
				//�ܽ��
				HSSFCell cell_6 = row.createCell(8);
				cell_6.setCellValue(hjzje);
				System.out.println(hjzje);
				//�տ�Ա����
				boolean hasNum=Util.hasNumber(sky);
				System.out.println(sky);
				if(hasNum==true){
				HSSFCell cell_7 = row.createCell(9);
				cell_7.setCellValue(sky);
				}else{
					HSSFCell cell_7 = row.createCell(9);
					cell_7.setCellValue(sky);
				}
				//���λ����
				
				String fkdw="";
					if(fpkj.getFkdw().equals("000000")){
						fkdw="����";
					}else{
						fkdw=fpkj.getFkdw();
					}
					System.out.println(fkdw);
				HSSFCell cell_8 = row.createCell(10);
				cell_8.setCellValue(fkdw);
				System.out.println();
				//����
				 
					HSSFCell cell_12 = row.createCell(11);
					cell_12.setCellValue(gh);
				 
				
				System.out.println(gh);
				i++;
			}
		}		

		try {
			File file=new File("d:\\"+Util.getPreviousMonthFirst().substring(0,7)+"��Ʊ��ϸ");
			if(!file.isDirectory()){
				file.mkdirs(); 
			}
		 FileOutputStream fileOut = new FileOutputStream("d:\\"+Util.getPreviousMonthFirst().substring(0,7)+"��Ʊ��ϸ"+"\\"+sheetName);
		 fileOut.flush();
		 wb.write(fileOut);
		 fileOut.close();
			
		 
			 
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		}
	}
}
