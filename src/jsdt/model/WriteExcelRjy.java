package jsdt.model;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import jsdt.model.cxtj.cxFpkj;
import jsdt.model.cxtj.cxRjymx;
import jsdt.tools.Query;
import jsdt.tools.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class WriteExcelRjy {
	public void getExcel(String sheetName, OutputStream output,String nsrwjbm,String startdate,String enddate) {
		String sqlStr = "";
		
		String jqbh = "";
		if(nsrwjbm!=null&&!"".equals(nsrwjbm)){
			for(int i=nsrwjbm.length();i<16;i++){
				nsrwjbm="0"+nsrwjbm;
			}
			
			
			String sql = "select JQBH from SKQ_JQXX where NSRWJBM='"+nsrwjbm+"'";
			
			String fieldname = "JQBH";
			
			jqbh = Query.getFieldStrCx(sql, fieldname);
			
		}
		
		if(jqbh!=null&&!"".equals(jqbh)){
			sqlStr = sqlStr+" and a.JQBH in("+jqbh+")";
		}
		if(startdate!=null&&!"".equals(startdate)){
			sqlStr = sqlStr+" and a.DQRQ>='"+startdate+"'";
		}
		if(enddate!=null&&!"".equals(enddate)){
			sqlStr = sqlStr+" and a.DQRQ<='"+enddate+"'";
		}
		
			
		
		cxRjymx cx = new cxRjymx();
		
		ArrayList al = cx.selectRjymx(sqlStr);
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
			
			//日期
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("日期");
			
			//正常票份数
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("正常票份数");
			
			//退票份数
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("退票份数");
			
			//废票份数
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("废票份数");
			
			//正常票总金额
			HSSFCell cell_70 = row0.createCell(7);
			cell_70.setCellValue("正常票总金额");
			
			//退票总金额
			HSSFCell cell_80 = row0.createCell(8);
			cell_80.setCellValue("退票总金额");
			
			//税目名称
			HSSFCell cell_90 = row0.createCell(9);
			cell_90.setCellValue("税目名称");
			
			Iterator it = al.iterator();
			int i = 1;
			while(it.hasNext()){
				HashMap hm = (HashMap)it.next();
				HSSFRow row = sheet1.createRow(i);
				
				
				//纳税人微机编码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue((String)hm.get("nsrwjbm"));
				
				//纳税人名称
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue((String)hm.get("nsrmc"));
				
				//机器编号
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue((String)hm.get("jqbh"));
				
				//日期
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(((String)hm.get("dqrq")).substring(0,10));
				
				//正常票份数
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue((String)hm.get("zcpfs"));
				
				//退票份数
				HSSFCell cell_5 = row.createCell(5);
				cell_5.setCellValue((String)hm.get("tpfs"));
				
				//废票份数
				HSSFCell cell_6 = row.createCell(6);
				cell_6.setCellValue((String)hm.get("fpfs"));
				
				//正常票总金额
				HSSFCell cell_7 = row.createCell(7);
				cell_7.setCellValue((String)hm.get("zcpzje"));
				
				//退票总金额
				HSSFCell cell_8 = row.createCell(8);
				cell_8.setCellValue((String)hm.get("tpzje"));
				
				//税目名称
				HSSFCell cell_9 = row.createCell(9);
				cell_9.setCellValue((String)hm.get("smmc"));
				
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
