<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票项目信息</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<%
ArrayList alProj = (ArrayList)request.getAttribute("alProj");
%>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableListTitle">
  <tr>
    <td width="70%" valign="bottom"><div class="listTitleGreen" id="xmjcxx"><a href="JAVASCRIPT:">发票项目信息</a></div></td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1">
  <tr>
    <th>项目名称</th>
    <th width="15%">小数位数</th>
    <th width="10%">数量</th>
    <th width="10%">单价</th>
    <th width="10%">金额</th>
    <th width="15%">税目编码</th>
  </tr>
  <%
  	Iterator it = alProj.iterator();
  	while(it.hasNext())
  	{
  		HashMap proj = (HashMap)it.next();
  %>
  <tr>
    <td align="center"><%=(String)proj.get("xmmc") %>&nbsp;</td>
    <td align="right"><%=(Integer)proj.get("xsws") %>&nbsp;</td>
    <td align="right"><%=(Integer)proj.get("sl") %>&nbsp;</td>
    <td align="right"><%=(Double)proj.get("dj") %>&nbsp;</td>
    <td align="right"><%=(Double)proj.get("je") %>&nbsp;</td>
    <td align="right"><%=(Integer)proj.get("smbm")%>&nbsp;</td>
  </tr>
  <%
  	}
	%>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableListBottom">
  <tr>
    <td height="40" align="center"><input type="button" name="button" value="关 闭" onclick="window.close();" />
    </td>
  </tr>
</table>
</body>
</html>
