<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票补领</title>
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
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/fpService.do?op=bllist&nsrwjbm=<%=nsrwjbm %>" class="nav">发票补领</a>&gt;&gt;&nbsp;&nbsp;新增</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/fpService.do?op=addaction" method="post" onsubmit="return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
        <tr>
          <td width="15%">发票代码：</td>
          <td><input type="text" style="width:30%" name="fpdm" required="required" controlName="发票代码" maxlength="12" /></td>
        </tr>
        <tr>
          <td>发票起始号码：</td>
          <td><input type="text" style="width:30%" name="fpqsh" required="required" dataType="integer" controlName="发票起始号码" maxlength="8" /></td>
        </tr>
        <tr>
          <td>发票截止号码：</td>
          <td><input type="text" style="width:30%" name="fpjzh" required="required" dataType="integer" controlName="发票截止号码" maxlength="8" /></td>
        </tr>
        <tr>
          <td width="15%">发票单位(卷)：</td>
            <td><input type="text" name="fpdw" size="15"  value="1" required="required" controlName="发票单位(卷)"/>
              &nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
        <tr>
          <td height="40" align="center">
            <input type="submit" name="next_btn" value="确 定" style="cursor:hand;"/>
            <input type="hidden" name="nsrwjbm" value="<%=nsrwjbm %>"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/fpService.do?op=bllist&nsrwjbm=<%=nsrwjbm %>';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
