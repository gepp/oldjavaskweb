<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Zclx zclx = (Zclx)request.getAttribute("zclx");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册类型修改</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toZclxList" class="nav">注册类型管理</a>&gt;&gt;&nbsp;&nbsp;修改</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/system.do?op=zclxEditAction" method="post" onSubmit="return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
        <tr>
          <td width="15%">注册类型编码：</td>
          <td><%=zclx.getZclxbm() %></td>
        </tr>
        <tr>
          <td>注册类型名称：</td>
          <td><input type="text" style="width:30%" name="zclxmc" value="<%=zclx.getZclxmc() %>" required="required" controlName="注册类型名称" /></td>
        </tr>
        <tr>
          <td>注册类型简称：</td>
          <td><input type="text" style="width:30%" name="zclxjc" value="<%=zclx.getZclxjc() %>" /></td>
        </tr>
        <tr>
          <td>注册类型状态：</td>
          <td><input type="radio" name="status" value="1" <% if(zclx.getStatus()==1){out.println("checked");} %> />
            启用
            <input type="radio" name="status" value="0" <% if(zclx.getStatus()==0){out.println("checked");} %> />
            未启用 </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
        <tr>
          <td height="40" align="center">
            <input type="hidden" name=zclxbm value="<%=zclx.getZclxbm() %>">
            <input type="submit" name="next_btn" value="确 定" style="cursor:hand;"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/system.do?op=toZclxList';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>