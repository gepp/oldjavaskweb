<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alHymx = (ArrayList)request.getAttribute("alHymx");
String hymc = (String)request.getAttribute("hymc");
String hybm = (String)request.getAttribute("hybm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function addZg(hybm){
	window.location.href='/javaskweb/system.do?op=getFromZG&hybm='+hybm;
}
function add(hybm){
	window.location.href='/javaskweb/system.do?op=toHymxAdd&hybm='+hybm;
}

function edit(hymxbm){
	window.location.href='/javaskweb/system.do?op=toHymxEdit&hymxbm='+hymxbm;
}

function del(hybm,sid){
	if(confirm('确定删除该行业明细？')){
		window.location.href='/javaskweb/system.do?op=hymxDelete&sid='+sid+'&hybm='+hybm;
	}
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toHyList" class="nav">行业管理</a> &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">行业明细管理</a></td>
        <td align="right"><!--<input type="button" name="btn" value="从征管获取" onclick="addZg('<%=hybm %>');" style="cursor:hand;" />&nbsp;&nbsp;--><input type="button" name="btn" value="新 增" onclick="add('<%=hybm %>');" style="cursor:hand;" />&nbsp;&nbsp;<input type="button" name="btn" value="返 回" onclick="window.location.href='/javaskweb/system.do?op=toHyList';" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">所属行业：<font color="#FF0000"><%=hymc %></font></a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="15%">行业明细编码</th>
        <th width="*%">行业名称</th>
        <th width="25%">行业简称</th>
        <th width="15%">状态</th>
        <th width="15%">操作</th>
      </tr>
<%
if(alHymx!=null&&!alHymx.isEmpty()){
	Iterator it = alHymx.iterator();
	while(it.hasNext()){
		Hymx hymx = (Hymx)it.next();
		
%>
	  <tr>
        <td><%=hymx.getHymxbm() %>&nbsp;</td>
        <td><%=hymx.getHymxmc() %>&nbsp;</td>
        <td><%=hymx.getHymxjc() %>&nbsp;</td>
        <td><%=hymx.getStatus()==1?"启用":"未启用" %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" onclick="edit('<%=hymx.getHymxbm() %>');" style="cursor:hand;" />&nbsp;&nbsp;<!--<input type="button" name="btn" value="删 除" onclick="del('<%=hybm %>',<%=hymx.getSid() %>);" style="cursor:hand;" />--></td>
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
