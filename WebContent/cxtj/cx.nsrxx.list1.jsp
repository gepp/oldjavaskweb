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
<title>无标题文档</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息列表</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="15%">纳税人微机编码</th>
        <th width="30%">纳税人名称</th>
        <th width="15%">纳税人识别号</th>
        <th width="20%">所属税务机关</th>
        <th width="10%">状态</th>
        <th>操作</th>
      </tr>
<%

if(al!=null&&!al.isEmpty()){
	Iterator it = al.iterator();
	while(it.hasNext()){
		Nsrxx nsrxx = (Nsrxx)it.next();
%>
	  <tr>
        <td align="center"><%=nsrxx.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=nsrxx.getNsrmc() %>&nbsp;</td>
        <td align="center"><%=nsrxx.getNsrsbh() %>&nbsp;</td>
        <td align="center"><%=nsrxx.getSwjgbm() %>&nbsp;</td>
        <td align="center"><%=nsrxx.getStatus()==1?"正常":"注销" %>&nbsp;</td>
        <td align="center"><input type="button" name="btn" value="查看详细信息" onclick="window.location.href='/javaskweb/cxtj.do?op=toNsrxxDetail&nsrwjbm=<%=nsrxx.getNsrwjbm() %>';" /></td>
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
