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
		System.out.println("发票导出");
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
		System.out.println("导出明细共："+alFpkj.size());
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		 
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alFpkj!=null&&!alFpkj.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			//纳税人微机编码
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("纳税人微机编码");
			
			//纳税人名称
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("纳税人名称");
			
			//发票号码
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("发票号码");
			
			//项目名称
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("项目名称");
			//单价
			HSSFCell cell_90 = row0.createCell(4);
			cell_90.setCellValue("单价");
			//数量
			HSSFCell cell_100 = row0.createCell(5);
			cell_100.setCellValue("数量");
			
			//开票时间
			HSSFCell cell_40 = row0.createCell(6);
			cell_40.setCellValue("开票时间");
			
			//开票类型
			HSSFCell cell_50 = row0.createCell(7);
			cell_50.setCellValue("开票类型");
			
			//金额
			HSSFCell cell_60 = row0.createCell(8);
			cell_60.setCellValue("金额");
			
			//总金额
			HSSFCell cell_210 = row0.createCell(9);
			cell_210.setCellValue("总金额");
			
			//折扣总金额
			HSSFCell cell_200= row0.createCell(10);
			cell_200.setCellValue("折扣总金额");
			//收款员姓名
			
			
			HSSFCell cell_70 = row0.createCell(11);
			cell_70.setCellValue("收款员姓名");
			
			//付款单位名称
			HSSFCell cell_80 = row0.createCell(12);
			cell_80.setCellValue("付款单位名称");
			
			HSSFCell cell_110 = row0.createCell(13);
			cell_110.setCellValue("工号");
			
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
				String kplxmc = "正常票";
				String hjzje = "0";
				 
				if(kplx==1){
					kplxmc = "正常票";
					hjzje = dg.format(fpkj.getHjzje());
					 
				}
				else if(kplx==2){
					kplxmc = "退票";
					hjzje = dg.format(fpkj.getHjzje()*-1);
				 
				}
				else if(kplx==3){
					kplxmc = "废票";
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
				//纳税人微机编码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpkj.getNsrwjbm());
				System.out.println("纳税人微机编码："+fpkj.getNsrwjbm());
				//纳税人名称
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(fpkj.getNsrmc());
				System.out.println("名称："+fpkj.getNsrmc());
				//发票号码
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(Util.fpIntToString(fpkj.getFphm()));
				System.out.println(Util.fpIntToString(fpkj.getFphm()));
				//项目名称
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpkj.getXmmc());
				System.out.println(fpkj.getXmmc());
				//单价
				HSSFCell cell_9 = row.createCell(4);
				cell_9.setCellValue(fpkj.getDj());
				System.out.println(fpkj.getDj());
				//数量
				HSSFCell cell_11 = row.createCell(5);
				cell_11.setCellValue(fpkj.getSl());
				System.out.println(fpkj.getSl());
				//开票时间
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
				//开票类型
				HSSFCell cell_5 = row.createCell(7);
				cell_5.setCellValue(kplxmc);
				System.out.println(kplxmc);
				//总金额
				HSSFCell cell_6 = row.createCell(8);
				cell_6.setCellValue(hjzje_string);
				
				HSSFCell cell_202 = row.createCell(9);
				cell_202.setCellValue(hjzje_string);
				
				//折扣总金额
				HSSFCell cell_201 = row.createCell(10);
				cell_201.setCellValue(zkzje_string);
				System.out.println(hjzje);
				//收款员姓名
				boolean hasNum=Util.hasNumber(sky);
				System.out.println(sky);
				if(hasNum==true){
				HSSFCell cell_7 = row.createCell(11);
				cell_7.setCellValue(sky);
				}else{
					HSSFCell cell_7 = row.createCell(11);
					cell_7.setCellValue(sky);
				}
				//付款单位名称
				
				String fkdw="";
					if(fpkj.getFkdw().equals("000000")){
						fkdw="个人";
					}else{
						fkdw=fpkj.getFkdw();
					}
					System.out.println(fkdw);
				HSSFCell cell_8 = row.createCell(12);
				cell_8.setCellValue(fkdw);
				System.out.println();
				//工号
				 
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
		System.out.println("发票导出");
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
		System.out.println("导出明细共："+alFpkj.size());
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		 
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alFpkj!=null&&!alFpkj.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			//纳税人微机编码
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("纳税人微机编码");
			
			//纳税人名称
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("纳税人名称");
			
			//发票号码
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("发票号码");
			
			//项目名称
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("项目名称");
			//单价
			HSSFCell cell_90 = row0.createCell(4);
			cell_90.setCellValue("单价");
			//数量
			HSSFCell cell_100 = row0.createCell(5);
			cell_100.setCellValue("数量");
			
			//开票时间
			HSSFCell cell_40 = row0.createCell(6);
			cell_40.setCellValue("开票时间");
			
			//开票类型
			HSSFCell cell_50 = row0.createCell(7);
			cell_50.setCellValue("开票类型");
			
			//总金额
			HSSFCell cell_60 = row0.createCell(8);
			cell_60.setCellValue("总金额");
			
			//收款员姓名
			
			
			HSSFCell cell_70 = row0.createCell(9);
			cell_70.setCellValue("收款员姓名");
			
			//付款单位名称
			HSSFCell cell_80 = row0.createCell(10);
			cell_80.setCellValue("付款单位名称");
			
			HSSFCell cell_110 = row0.createCell(11);
			cell_110.setCellValue("工号");
			
			Iterator it = alFpkj.iterator();
			int i = 1;
			while(it.hasNext()){
				Fpkj fpkj = (Fpkj)it.next();
				HSSFRow row = sheet1.createRow(i);
				
				int kplx = fpkj.getKplx();
				String kplxmc = "正常票";
				String hjzje = "0";
				if(kplx==1){
					kplxmc = "正常票";
					hjzje = dg.format(fpkj.getHjzje());
					System.out.println("开票类型报错："+kplx);
					System.out.println("hjzje="+fpkj.getHjzje());
				}
				else if(kplx==2){
					kplxmc = "退票";
					hjzje = dg.format(fpkj.getHjzje()*-1);
					System.out.println("开票类型报错："+kplx);
					System.out.println("hjzje="+fpkj.getHjzje());
				}
				else if(kplx==3){
					kplxmc = "废票";
					hjzje = "0.00";
					System.out.println("开票类型报错："+kplx);
					System.out.println("hjzje="+fpkj.getHjzje());
				}
				else{
				System.out.println("开票类型报错："+kplxmc);
				System.out.println("hjzje="+00);
				}
				
				
				//纳税人微机编码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpkj.getNsrwjbm());
				System.out.println("纳税人危机编码："+fpkj.getNsrwjbm());
				//纳税人名称
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(fpkj.getNsrmc());
				System.out.println("名称："+fpkj.getNsrmc());
				//发票号码
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(Util.fpIntToString(fpkj.getFphm()));
				System.out.println(Util.fpIntToString(fpkj.getFphm()));
				//项目名称
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpkj.getXmmc());
				System.out.println(fpkj.getXmmc());
				//单价
				HSSFCell cell_9 = row.createCell(4);
				cell_9.setCellValue(fpkj.getDj());
				System.out.println(fpkj.getDj());
				//数量
				HSSFCell cell_11 = row.createCell(5);
				cell_11.setCellValue(fpkj.getSl());
				System.out.println(fpkj.getSl());
				//开票时间
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
				//开票类型
				HSSFCell cell_5 = row.createCell(7);
				cell_5.setCellValue(kplxmc);
				System.out.println(kplxmc);
				//总金额
				HSSFCell cell_6 = row.createCell(8);
				cell_6.setCellValue(hjzje);
				System.out.println(hjzje);
				//收款员姓名
				boolean hasNum=Util.hasNumber(sky);
				System.out.println(sky);
				if(hasNum==true){
				HSSFCell cell_7 = row.createCell(9);
				cell_7.setCellValue(sky);
				}else{
					HSSFCell cell_7 = row.createCell(9);
					cell_7.setCellValue(sky);
				}
				//付款单位名称
				
				String fkdw="";
					if(fpkj.getFkdw().equals("000000")){
						fkdw="个人";
					}else{
						fkdw=fpkj.getFkdw();
					}
					System.out.println(fkdw);
				HSSFCell cell_8 = row.createCell(10);
				cell_8.setCellValue(fkdw);
				System.out.println();
				//工号
				 
					HSSFCell cell_12 = row.createCell(11);
					cell_12.setCellValue(gh);
				 
				
				System.out.println(gh);
				i++;
			}
		}		

		try {
			File file=new File("d:\\"+Util.getPreviousMonthFirst().substring(0,7)+"发票明细");
			if(!file.isDirectory()){
				file.mkdirs(); 
			}
		 FileOutputStream fileOut = new FileOutputStream("d:\\"+Util.getPreviousMonthFirst().substring(0,7)+"发票明细"+"\\"+sheetName);
		 fileOut.flush();
		 wb.write(fileOut);
		 fileOut.close();
			
		 
			 
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output   is   closed ");
		}
	}
}
