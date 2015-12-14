package jsdt.model;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.model.cxtj.cxRjymx;
import jsdt.model.cxtj.cxSbsj;
import jsdt.tools.Query;
import jsdt.tools.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteExcelSbsjForHistory {
	public WriteExcelSbsjForHistory(){
		System.out.println("test,gpp");
	}
	public void getExcel(String sheetName, OutputStream output,String nsrwjbm,String startdate,String enddate,String swjgbm) {
		String strSql = "";
		String sqlSwjg = "";
		String sqlNsr = "";
		
		
		if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
			for(int i=nsrwjbm.length();i<16;i++){
				nsrwjbm="0"+nsrwjbm;
			}
			
			sqlNsr = " and a.CODE='"+nsrwjbm+"'";
		}
		
		strSql = sqlNsr+" and a.CODE in(select NSRWJBM from SKQ_NSRXX where STATUS=1 )";
		
		if(startdate!=null&&!"".equals(startdate)){
			strSql = strSql+" and a.STARTDATE>='"+startdate+"'";
		}
		if(enddate!=null&&!"".equals(enddate)){
			strSql = strSql+" and ENDDATE<='"+enddate+"'";
		}
		
			
		
		cxSbsj cx = new cxSbsj();
		System.out.println("strSql:"+strSql);
		ArrayList al = cx.selectSbsjExcelForHistory(strSql);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		DecimalFormat dg = new DecimalFormat("0.00");
		if(al!=null&&!al.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			//纳税人微机编码
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("纳税人微机编码");
			
			//纳税人名称
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("纳税人名称");
			
			//机器编号
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("机器编号");
			
			//开始时间
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("开始时间");
			
			//截止时间
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("截止时间");
			
			//正常票份数
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("正常票份数");
			
			//退票份数
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("退票份数");
			
			//废票份数
			HSSFCell cell_70 = row0.createCell(7);
			cell_70.setCellValue("废票份数");
			
			//正常票总金额
			HSSFCell cell_80 = row0.createCell(8);
			cell_80.setCellValue("正常票总金额");
			
			//退票总金额
			HSSFCell cell_90 = row0.createCell(9);
			cell_90.setCellValue("退票总金额");
			
			Iterator it = al.iterator();
			int i = 1;
			while(it.hasNext()){
				Sbsj sbsj = (Sbsj)it.next();
				HSSFRow row = sheet1.createRow(i);
				
				
				//纳税人微机编码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(sbsj.getNsrwjbm());
				
				//纳税人名称
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(sbsj.getNsrmc());
				
				//机器编号
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(sbsj.getJqbh());
				
				//开始时间
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(sbsj.getSskssj());
				
				//截止时间
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue(sbsj.getSsjzsj());
				
				//正常票份数
				HSSFCell cell_5 = row.createCell(5);
				cell_5.setCellValue(sbsj.getZcpfs());
				
				//退票份数
				HSSFCell cell_6 = row.createCell(6);
				cell_6.setCellValue(sbsj.getTpfs());
				
				//废票份数
				HSSFCell cell_7 = row.createCell(7);
				cell_7.setCellValue(sbsj.getFpfs());
				
				//正常票总金额
				HSSFCell cell_8 = row.createCell(8);
				cell_8.setCellValue(dg.format(sbsj.getZcpzje()));
				
				//退票总金额
				HSSFCell cell_9 = row.createCell(9);
				cell_9.setCellValue(dg.format(sbsj.getTpzje()));
				
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
}
