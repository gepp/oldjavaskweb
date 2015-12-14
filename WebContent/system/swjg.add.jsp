<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSwjg = (ArrayList)request.getAttribute("alSwjg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>税务机关增加</title>
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
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toSwjgList" class="nav">税务机关管理</a>&gt;&gt;&nbsp;&nbsp;新增</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/system.do?op=swjgAddAction" method="post">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
        <tr>
          <td width="15%">税务机关编码：</td>
          <td><input type="text" style="width:30%" name="swjgbm" required="required" controlName="税务机关编码" /></td>
        </tr>
        <tr>
          <td>税务机关名称：</td>
          <td><input type="text" style="width:30%" name="swjgmc" required="required" controlName="税务机关名称" /></td>
        </tr>
        <tr>
          <td>税务机关简称：</td>
          <td><input type="text" style="width:30%" name="swjgjc" /></td>
        </tr>
        <tr>
          <td>上级税务机关：</td>
          <td>
            <select name="sjswjgbm" id="select">
              <option value="">--请选择上级税务机关--</option>
<%
if(alSwjg!=null&&!alSwjg.isEmpty()){
	Iterator it = alSwjg.iterator();
	while(it.hasNext()){
		Swjg swjg = (Swjg)it.next();
		
%>
			<option value="<%=swjg.getSwjgbm() %>"><%=swjg.getSwjgmc() %></option>
<%
	}
}
%>
            </select>          
          </td>
        </tr>
        <tr>
          <td>税务机关状态：</td>
          <td>
              <input type="radio" name="status" value="1" checked />
              启用
              <input type="radio" name="status" value="0" />
              未启用
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
        <tr>
          <td height="40" align="center">
            <input type="submit" name="next_btn" value="确 定" style="cursor:hand;"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/system.do?op=toSwjgList';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
