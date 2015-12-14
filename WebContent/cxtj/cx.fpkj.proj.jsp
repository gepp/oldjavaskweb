<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
ArrayList alFpkjProj = (ArrayList)request.getAttribute("alFpkjProj");
DecimalFormat dg = new DecimalFormat("0.00");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票项目信息</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票项目信息列表</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="*">项目名称</th>
        <!--<th width="10%">小数位数</th>-->
        <th width="10%">数量</th>
        <th width="10%">单价</th>
        <th width="10%">金额</th>
        <th width="10%">税目编码</th>
        <th width="15%">税目名称</th>
      </tr>
<%

if(alFpkjProj!=null&&!alFpkjProj.isEmpty()){
	Iterator it = alFpkjProj.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		String xmmc = (String)hm.get("xmmc");
		if("null".equals(xmmc)){
			xmmc = "";
		}
%>
	  <tr>
        <td align="center"><%=xmmc %>&nbsp;</td>
         <!--<td align="center"><%=(Integer)hm.get("xsws") %>&nbsp;</td>-->
        <td align="center"><%=dg.format((Double)hm.get("sl")) %>&nbsp;</td>
        <td align="center"><%=dg.format((Double)hm.get("dj")) %>&nbsp;</td>
        <td align="center"><%=dg.format((Double)hm.get("je")) %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("smbm") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("smmc") %>&nbsp;</td>
      </tr>
<%
	}
}
%>
      
    </table>
  </div>
</div>
</body>
</html>