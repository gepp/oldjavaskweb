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
	//发票汇总
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
			//纳税人微机编码
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("纳税人微机编码");
			
			//纳税人名称
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("纳税人名称");
			
			//机器编号
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("机器编号");
			
			//发票代码
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("发票代码");
			
			//发票起始号
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("发票起始号");
			
			//发票截止号
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("发票截止号");
			 
			//发票单位
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("发票单位");
			
			
			Iterator it = alFpkj.iterator();
			int i = 1;
			while(it.hasNext()){
				Fpjmx fpjmx = (Fpjmx)it.next();
				HSSFRow row = sheet1.createRow(i);
				
				
				//纳税人微机编码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpjmx.getNsrwjbm());
				
				//纳税人名称
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(fpjmx.getNsrmc());
				
				//机器编码
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(fpjmx.getJqbh());
				
				//发票代码
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpjmx.getFpdm());
				
				//发票起始号
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue(fpjmx.getFpqsh());
				
				//发票截止号
				HSSFCell cell_5 = row.createCell(5);
				cell_5.setCellValue(fpjmx.getFpjzh());
				
				//发票单位
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
	
	//单卷发票开具汇总
	public void getDjkjhzExcel(OutputStream output,String nsrwjbm,String fpqsh,String fpjzh,String fpdm) {
		
		cxFpkj cx =new cxFpkj();
		ArrayList alDjkjhz = cx.selectDjkjhzDcExcel( nsrwjbm, fpqsh, fpjzh,fpdm);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("sheet1");
		DecimalFormat dg = new DecimalFormat("0.00");
		if(alDjkjhz!=null&&!alDjkjhz.isEmpty()){
			HSSFRow row0 = sheet1.createRow(0);
			
			//发票代码
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("发票代码");
			
			//发票起始号
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("发票起始号");
			
			//发票截止号
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("发票截止号");
			
			//正常票总份数
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("正常票总份数");
			
			//退票总份数
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("退票总份数");
			
			//废票总份数
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("废票总份数");
			
			//正常票总金额
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("正常票总金额");
			
			//退票总金额
			HSSFCell cell_70 = row0.createCell(7);
			cell_70.setCellValue("退票总金额");
			
			//正常票总金额
			HSSFCell cell_80 = row0.createCell(8);
			cell_80.setCellValue("开票起始时间");
			
			//退票总金额
			HSSFCell cell_90 = row0.createCell(9);
			cell_90.setCellValue("开票截止时间");
			
			for(int i = 0 ;i < alDjkjhz.size();i++){
				
				HashMap map = (HashMap) alDjkjhz.get(i);
				HSSFRow row = sheet1.createRow(i+1);
				
				//发票代码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(fpdm);
				
				//发票起始号
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue((Integer) map.get("fpqsh"));
				
				//发票截止号
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue((Integer) map.get("fpjzh"));
				
				//正常票总份数
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue((Integer) map.get("zcphz"));
				
				//退票总份数
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue((Integer) map.get("tphz"));
				
				//废票总份数
				HSSFCell cell_5 = row.createCell(5);
				cell_5.setCellValue((Integer) map.get("fphz"));
				
				//正常票总金额
				HSSFCell cell_6 = row.createCell(6);
				cell_6.setCellValue((Integer) map.get("zcpzje"));
				
				//退票总金额
				HSSFCell cell_7 = row.createCell(7);
				cell_7.setCellValue((Integer) map.get("tpzje"));
				
				HSSFCell cell_8 = row.createCell(8);
				cell_8.setCellValue((String) map.get("first"));
				
				//退票总金额
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
	
	//单卷发票开具明细
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
			
			//纳税人微机编码
			HSSFCell cell_00 = row0.createCell(0);
			cell_00.setCellValue("纳税人微机编码");
			
			//纳税人名称
			HSSFCell cell_10 = row0.createCell(1);
			cell_10.setCellValue("纳税人名称");
			
			//机器编号
			HSSFCell cell_20 = row0.createCell(2);
			cell_20.setCellValue("机器编号");
			
			//发票代码
			HSSFCell cell_30 = row0.createCell(3);
			cell_30.setCellValue("发票代码");
			
			//发票号码
			HSSFCell cell_40 = row0.createCell(4);
			cell_40.setCellValue("发票号码");
			
			//开票日期
			HSSFCell cell_50 = row0.createCell(5);
			cell_50.setCellValue("开票日期");
			
			//开票类型
			HSSFCell cell_60 = row0.createCell(6);
			cell_60.setCellValue("开票类型");
			
			//开票金额
			HSSFCell cell_70 = row0.createCell(7);
			cell_70.setCellValue("开票金额");
			
			//原发票号码
			HSSFCell cell_80 = row0.createCell(8);
			cell_80.setCellValue("原发票号码");
			
			for(int i = 0 ;i < alDjkjhz.size();i++){
				
				Fpkj fpkj = (Fpkj) alDjkjhz.get(i);
				HSSFRow row = sheet1.createRow(i+1);
				
				//纳税人微机编码
				HSSFCell cell_0 = row.createCell(0);
				cell_0.setCellValue(nsrwjbm);
				
				//纳税人名称
				HSSFCell cell_1 = row.createCell(1);
				cell_1.setCellValue(nsrxx.getNsrmc());
				
				//机器编号
				HSSFCell cell_2 = row.createCell(2);
				cell_2.setCellValue(fpkj.getJqbh());
				
				//发票代码
				HSSFCell cell_3 = row.createCell(3);
				cell_3.setCellValue(fpdm);
				
				//发票号码
				HSSFCell cell_4 = row.createCell(4);
				cell_4.setCellValue(fpkj.getFphm());
				
				//开票日期
				HSSFCell cell_5 = row.createCell(5);
				System.out.println("开票日期："+fpkj.getKprq().subSequence(0, 10));
				cell_5.setCellValue((String)fpkj.getKprq().subSequence(0, 10));
				
				//开票类型
				HSSFCell cell_6 = row.createCell(6);
				System.out.println("开票类型："+fpkj.getKplx());
				cell_6.setCellValue(fpkj.getKplx()==1?"正常开票":fpkj.getKplx()==2?"退票":"废票");
				
				//开票金额
				HSSFCell cell_7 = row.createCell(7);
				cell_7.setCellValue(fpkj.getHjzje());
				
				//原发票号码
				HSSFCell cell_8 = row.createCell(8);
				System.out.println("原发票号码:"+fpkj.getYfphm());
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
