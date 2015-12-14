package jsdt.model;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.model.cxtj.cxFpkj;
import jsdt.tools.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteExcelDjhz {
	//��Ʊ����
	public void getDjhzExcel(OutputStream output,String nsrwjbm,String startdate,String enddate) {
		cxFpkj cx =new cxFpkj();
		ArrayList alFpkj = cx.selectDjhzDcExcel( nsrwjbm, startdate, enddate);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		sheet1.setColumnWidth(0, 5000);
		sheet1.setColumnWidth(1, 4000);
		sheet1.setColumnWidth(2, 4000);
		sheet1.setColumnWidth(3, 4000);
		sheet1.setColumnWidth(4, 4000);
		sheet1.setColumnWidth(5, 4000);
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alFpkj!=null&&!alFpkj.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			//��˰��΢������
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("��˰��΢������");
			
			//��˰������
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("��˰������");
			
			//�������
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("�������");
			
			//��Ʊ����
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("��Ʊ����");
			
			//��Ʊ��ʼ��
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("��Ʊ��ʼ��");
			
			//��Ʊ��ֹ��
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("��Ʊ��ֹ��");
			 
			//��Ʊ��λ
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("��Ʊ��λ");
			
			
			Iterator it = alFpkj.iterator();
			int i = 1;
			while(it.hasNext()){
				Fpjmx fpjmx = (Fpjmx)it.next();
				HSSFRow row = sheet1.createRow(i);
				
				
				//��˰��΢������
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpjmx.getNsrwjbm());
				
				//��˰������
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(fpjmx.getNsrmc());
				
				//��������
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(fpjmx.getJqbh());
				
				//��Ʊ����
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpjmx.getFpdm());
				
				//��Ʊ��ʼ��
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue(fpjmx.getFpqsh());
				
				//��Ʊ��ֹ��
				HSSFCell cell_5 = row.createCell(5);
				cell_5.setCellValue(fpjmx.getFpjzh());
				
				//��Ʊ��λ
				HSSFCell cell_6 = row.createCell(6);
				cell_6.setCellValue(fpjmx.getFpdw());
				
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
	
	//����Ʊ���߻���
	public void getDjkjhzExcel(OutputStream output,String nsrwjbm,String fpqsh,String fpjzh,String fpdm) {
		
		cxFpkj cx =new cxFpkj();
		ArrayList alDjkjhz = cx.selectDjkjhzDcExcel( nsrwjbm, fpqsh, fpjzh,fpdm);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alDjkjhz!=null&&!alDjkjhz.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			
			//��Ʊ����
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("��Ʊ����");
			
			//��Ʊ��ʼ��
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("��Ʊ��ʼ��");
			
			//��Ʊ��ֹ��
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("��Ʊ��ֹ��");
			
			//����Ʊ�ܷ���
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("����Ʊ�ܷ���");
			
			//��Ʊ�ܷ���
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("��Ʊ�ܷ���");
			
			//��Ʊ�ܷ���
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("��Ʊ�ܷ���");
			
			//����Ʊ�ܽ��
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("����Ʊ�ܽ��");
			
			//��Ʊ�ܽ��
			HSSFCell cell_70 = row0.createCell(7);
			cell_70.setCellValue("��Ʊ�ܽ��");
			
			//����Ʊ�ܽ��
			HSSFCell cell_80 = row0.createCell(8);
			cell_80.setCellValue("��Ʊ��ʼʱ��");
			
			//��Ʊ�ܽ��
			HSSFCell cell_90 = row0.createCell(9);
			cell_90.setCellValue("��Ʊ��ֹʱ��");
			
			for(int i = 0 ;i < alDjkjhz.size();i++){
				
				HashMap map = (HashMap) alDjkjhz.get(i);
				HSSFRow row = sheet1.createRow(i+1);
				
				//��Ʊ����
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpdm);
				
				//��Ʊ��ʼ��
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue((Integer) map.get("fpqsh"));
				
				//��Ʊ��ֹ��
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue((Integer) map.get("fpjzh"));
				
				//����Ʊ�ܷ���
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue((Integer) map.get("zcphz"));
				
				//��Ʊ�ܷ���
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue((Integer) map.get("tphz"));
				
				//��Ʊ�ܷ���
				HSSFCell cell_5 = row.createCell(5);
				cell_5.setCellValue((Integer) map.get("fphz"));
				
				//����Ʊ�ܽ��
				HSSFCell cell_6 = row.createCell(6);
				cell_6.setCellValue((Integer) map.get("zcpzje"));
				
				//��Ʊ�ܽ��
				HSSFCell cell_7 = row.createCell(7);
				cell_7.setCellValue((Integer) map.get("tpzje"));
				
				HSSFCell cell_8 = row.createCell(8);
				cell_8.setCellValue((String) map.get("first"));
				
				//��Ʊ�ܽ��
				HSSFCell cell_9 = row.createCell(9);
				cell_9.setCellValue((String) map.get("end"));
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
	
	//����Ʊ������ϸ
	public void getDjkjmxExcel(OutputStream output,String nsrwjbm,String fpqsh,String fpjzh,String fpdm) {
		
		cxFpkj cx =new cxFpkj();
		ArrayList alDjkjhz = cx.selectDjkjmxDcExcel(fpqsh, fpjzh,fpdm);
		HSSFWorkbook wb = new HSSFWorkbook();
		Nsrxx nsrxx = new Nsrxx(nsrwjbm);
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		sheet1.setColumnWidth(0, 5000);
		sheet1.setColumnWidth(1, 4000);
		sheet1.setColumnWidth(2, 3000);
		sheet1.setColumnWidth(3, 3000);
		sheet1.setColumnWidth(4, 3000);
		sheet1.setColumnWidth(5, 3000);
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alDjkjhz!=null&&!alDjkjhz.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			
			//��˰��΢������
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("��˰��΢������");
			
			//��˰������
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("��˰������");
			
			//�������
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("�������");
			
			//��Ʊ����
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("��Ʊ����");
			
			//��Ʊ����
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("��Ʊ����");
			
			//��Ʊ����
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("��Ʊ����");
			
			//��Ʊ����
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("��Ʊ����");
			
			//��Ʊ���
			HSSFCell cell_70 = row0.createCell(7);
			cell_70.setCellValue("��Ʊ���");
			
			//ԭ��Ʊ����
			HSSFCell cell_80 = row0.createCell(8);
			cell_80.setCellValue("ԭ��Ʊ����");
			
			for(int i = 0 ;i < alDjkjhz.size();i++){
				
				Fpkj fpkj = (Fpkj) alDjkjhz.get(i);
				HSSFRow row = sheet1.createRow(i+1);
				
				//��˰��΢������
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(nsrwjbm);
				
				//��˰������
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(nsrxx.getNsrmc());
				
				//�������
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(fpkj.getJqbh());
				
				//��Ʊ����
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpdm);
				
				//��Ʊ����
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue(fpkj.getFphm());
				
				//��Ʊ����
				HSSFCell cell_5 = row.createCell(5);
				System.out.println("��Ʊ���ڣ�"+fpkj.getKprq().subSequence(0, 10));
				cell_5.setCellValue((String)fpkj.getKprq().subSequence(0, 10));
				
				//��Ʊ����
				HSSFCell cell_6 = row.createCell(6);
				System.out.println("��Ʊ���ͣ�"+fpkj.getKplx());
				cell_6.setCellValue(fpkj.getKplx()==1?"������Ʊ":fpkj.getKplx()==2?"��Ʊ":"��Ʊ");
				
				//��Ʊ���
				HSSFCell cell_7 = row.createCell(7);
				cell_7.setCellValue(fpkj.getHjzje());
				
				//ԭ��Ʊ����
				HSSFCell cell_8 = row.createCell(8);
				System.out.println("ԭ��Ʊ����:"+fpkj.getYfphm());
				if(fpkj.getYfphm()==0){
					cell_8.setCellValue("");
				}else{
					cell_8.setCellValue(fpkj.getYfphm());
				}
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
}
