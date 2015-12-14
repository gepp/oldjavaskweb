<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList al = (ArrayList)request.getAttribute("al");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>未申报用户查询</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">未申报纳税户信息列表</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="15%">纳税人微机编码</th>
        <th width="30%">纳税人名称</th>
        <th width="15%">汇总申报状态</th>
      </tr>
<%

if(al!=null&&!al.isEmpty()){
	Iterator it = al.iterator();
	while(it.hasNext()){
		Wsbsj wsbsj = (Wsbsj)it.next();
%>
	  <tr>
        <td align="center"><%=wsbsj.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=wsbsj.getNsrmc() %>&nbsp;</td>
       
        <td align="center"><%=wsbsj.isWsbsj()?"已申报":"<font color=red>未申报</font>" %>&nbsp;</td>

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
