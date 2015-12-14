<%@ page contentType="application/vnd.ms-excel" language="java" import="java.util.*,jsdt.model.WriteExcelDjhz" pageEncoding="GBK"%><%
response.setHeader("Content-Disposition","attachment;filename=djhz.xls");//指定下载的文件名
response.setContentType("application/vnd.ms-excel");
String nsrwjbm = request.getParameter("nsrwjbm");
String startdate = request.getParameter("startdate");
String enddate = request.getParameter("enddate");
WriteExcelDjhz  we=new WriteExcelDjhz();
we.getDjhzExcel(response.getOutputStream(),nsrwjbm,startdate,enddate);
%>