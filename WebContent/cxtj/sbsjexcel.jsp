
<%@page import="jsdt.tools.*"%>
<%@page import="jsdt.model.WriteExcelSbsjForHistory"%>
<%@ page contentType="application/vnd.ms-excel" language="java"
	import="java.util.*,jsdt.model.WriteExcelSbsj" pageEncoding="GBK"%>
<%

	String nsrwjbm = request.getParameter("nsrwjbm");
	String startdate = request.getParameter("startdate");
	String enddate = request.getParameter("enddate");
	String swjgbm = request.getParameter("swjgbm");
	
    enddate=Util.toxkrq(enddate);
		response.setHeader("Content-Disposition",
		"attachment;filename=sbsj.xls");//指定下载的文件名
		response.setContentType("application/vnd.ms-excel");
		WriteExcelSbsj wef = new WriteExcelSbsj();
		
		startdate=Util.toxkrq(startdate);
		wef.getExcel("sbsj.xls", response.getOutputStream(), nsrwjbm,
				startdate, enddate, swjgbm);
		   out.clear();
			out = pageContext.pushBody();

	
%>