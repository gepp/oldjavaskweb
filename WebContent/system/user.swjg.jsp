<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSwjg = (ArrayList)request.getAttribute("alSwjg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function serch_user(swjgbm){
	window.location.href='/javaskweb/userServlet?method=userlist&swjgbm='+swjgbm;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">用户管理</a></td>
        <td align="right">&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="10%">税务机关编码</th>
        <th width="*%">税务机关名称</th>
        <th width="15%">税务机关简称</th>
        <th width="25%">上级税务机关</th>
        <th width="10%">操作</th>
      </tr>
<%
if(alSwjg!=null&&!alSwjg.isEmpty()){
	Iterator it = alSwjg.iterator();
	while(it.hasNext()){
		Swjg swjg = (Swjg)it.next();
		
%>
      <tr>
        <td><%=swjg.getSwjgbm() %>&nbsp;</td>
        <td><%=swjg.getSwjgmc() %>&nbsp;</td>
        <td><%=swjg.getSwjgjc() %>&nbsp;</td>
        <td><%=swjg.getSjswjgmc() %>&nbsp;</td>
        <td><input type="button" name="btn" value="查看用户" style="cursor:hand;" onclick="serch_user('<%=swjg.getSwjgbm() %>');" />&nbsp;</td>
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
