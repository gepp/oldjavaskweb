<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alHy = (ArrayList)request.getAttribute("alHy");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function addZg(){
	window.location.href='/javaskweb/hyServlet?method=getFromZG';
}

function add(){
	window.location.href='/javaskweb/system.do?op=toHyAdd';
}

function edit(hybm){
	window.location.href='/javaskweb/system.do?op=toHyEdit&hybm='+hybm;
}

function del(sid){
	if(confirm('确定删除该行业？')){
		window.location.href='/javaskweb/system.do?op=deleteHy&sid='+sid;
	}
}

function hymx(hybm){
	window.location.href='/javaskweb/system.do?op=toHymxList&hybm='+hybm;
}
</script>
</head>
<body>

<!--==========right部分==========-->
<div id="right">
  <div id="tool"> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">行业管理</a></td>
        <td align="right"><!--<input type="button" name="btn" value="从征管获取" style="cursor:hand;" onclick="addZg();" />&nbsp;&nbsp;--><input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="15%">行业编码</th>
        <th width="*">行业名称</th>
        <th width="20%">行业简称</th>
        <th width="10%">启用标志</th>
        <th width="20%">操作</th>
      </tr>
<%
if(alHy!=null&&!alHy.isEmpty()){
	Iterator it = alHy.iterator();
	while(it.hasNext()){
		Hy hy = (Hy)it.next();
		
%>
	  <tr>
        <td><%=hy.getHybm() %>&nbsp;</td>
        <td><%=hy.getHymc() %>&nbsp;</td>
        <td><%=hy.getHyjc() %>&nbsp;</td>
        <td><%=hy.getStatus()==1?"启用":"未启用" %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" onclick="edit('<%=hy.getHybm() %>');" style="cursor:hand;" />&nbsp;&nbsp;<!--<input type="button" name="btn" value="删 除" onclick="del(<%=hy.getSid() %>);" style="cursor:hand;" />&nbsp;&nbsp;--><input type="button" name="btn" value="行业明细" onclick="hymx('<%=hy.getHybm() %>');" style="cursor:hand;" /></td>
      </tr>
<%
	}
}
%>
      
    </table>
    <iframe id="iframe" name="iframe" src="" width="0" height="0" frameborder="0"></iframe> 
  </div>
</div>
</body>
</html>
