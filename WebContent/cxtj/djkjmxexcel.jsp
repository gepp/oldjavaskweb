<%@ page contentType="application/vnd.ms-excel" language="java" import="java.util.*,jsdt.model.WriteExcelDjhz" pageEncoding="GBK"%><%
response.setHeader("Content-Disposition","attachment;filename=djkjmx.xls");//ָ�����ص��ļ���
response.setContentType("application/vnd.ms-excel");
String nsrwjbm = request.getParameter("nsrwjbm");
String fpqsh = request.getParameter("fpqsh");
String fpjzh = request.getParameter("fpjzh");
String fpdm = request.getParameter("fpdm");
WriteExcelDjhz  we=new WriteExcelDjhz();
we.getDjkjmxExcel(response.getOutputStream(),nsrwjbm,fpqsh,fpjzh,fpdm);
%>