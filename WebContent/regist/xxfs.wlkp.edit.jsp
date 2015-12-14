<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Xxfs xxfs=(Xxfs)request.getAttribute("xxfs");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票票种修改</title>
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
        <td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toFpList" class="nav">发票票种管理</a>&gt;&gt;&nbsp;&nbsp;修改</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/taxpayer.do?op=xxfsEditAction" method="post" onSubmit="return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
        <tr>
          <td width="15%">消息发送内容：</td>
          
          <td><input type="text" value="<%=xxfs.getXxnr()%>" id="xxnr" name="xxnr"></input></td>
        </tr>
       
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
        <tr>
          <td height="40" align="center">
          <input type="hidden" value="<%=xxfs.getGuid() %>" name="guid"/>
            <input type="submit" name="next_btn" value="确 定" style="cursor:hand;"/>&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/taxpayer.do?op=xxfsWlkp';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>