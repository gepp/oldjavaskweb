<%@ page contentType="application/vnd.ms-excel" language="java" import="java.util.*,jsdt.model.WriteExcelFpkj" pageEncoding="GBK"%><%
response.setHeader("Content-Disposition","attachment;filename=fpkjmx.xls");//指定下载的文件名
response.setContentType("application/vnd.ms-excel");
String nsrwjbm = request.getParameter("nsrwjbm");
String startdate = request.getParameter("startdate");
String enddate = request.getParameter("enddate");
String fpqsh = request.getParameter("fpqsh");
String fpjzh = request.getParameter("fpjzh");
String fpdm = request.getParameter("fpdm");
String skygh=request.getParameter("skygh");
WriteExcelFpkj  we=new WriteExcelFpkj();
System.out.println(nsrwjbm+" "+startdate+" "+enddate);
skygh=new String(skygh.getBytes("ISO-8859-1"),"UTF-8");
we.getExcel("fpmx.xls",response.getOutputStream(),nsrwjbm,startdate,enddate,fpqsh,fpjzh,fpdm,skygh);
%>