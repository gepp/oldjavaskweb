<%@ page contentType="application/vnd.ms-excel" language="java" import="java.util.*,jsdt.model.WriteExcelRjy" pageEncoding="GBK"%><%
response.setHeader("Content-Disposition","attachment;filename=rjymx.xls");//ָ�����ص��ļ���
response.setContentType("application/vnd.ms-excel");
String nsrwjbm = request.getParameter("nsrwjbm");
String startdate = request.getParameter("startdate");
String enddate = request.getParameter("enddate");
System.out.println("1111111");
WriteExcelRjy  we=new WriteExcelRjy();
we.getExcel("rjy.xls",response.getOutputStream(),nsrwjbm,startdate,enddate);
%>