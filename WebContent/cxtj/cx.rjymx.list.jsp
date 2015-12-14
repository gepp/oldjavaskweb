<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="jsdt.tools.*"%>
<%
ArrayList al = (ArrayList)request.getAttribute("al");
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function dc(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	
	document.all('iframe').src = '/javaskweb/cxtj/rjyexcel.jsp?nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">日交易明细信息列表</a></div></td>
        <td align="right">
          <input type="hidden" name="nsrwjbm" id="nsrwjbm" value="<%=nsrwjbm %>" />
          <input type="hidden" name="startdate" id="startdate" value="<%=startdate %>" />
          <input type="hidden" name="enddate" id="enddate" value="<%=enddate %>" />
          <input type="button" name="dcexcel" value="导出EXCEL" onclick="dc();">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
          <th width="10%">纳税人微机编码</th>
          <th>纳税人名称</th>
          <th width="10%">机器编号</th>
          <th width="10%">日期</th>
          <th width="9%">正常票份数</th>
          <th width="9%">退票份数</th>
          <th width="9%">废票份数</th>
          <th width="11%">正常票总金额</th>
          <th width="11%">退票总金额</th>
          <th width="8%">税目名称</th>
        </tr>
<%
if(al!=null&&!al.isEmpty()){
	Iterator it = al.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
%>
        <tr>
          <td align="center"><%=(String)hm.get("nsrwjbm") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("nsrmc") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("jqbh") %>&nbsp;</td>
          <td align="center"><%=((String)hm.get("dqrq")).substring(0,10) %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("zcpfs") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("tpfs") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("fpfs") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("zcpzje") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("tpzje") %>&nbsp;</td>
          <td align="center"><%=(String)hm.get("smmc") %>&nbsp;</td>
        </tr>
        <%
	}
}
%>
      </table>
      <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>
