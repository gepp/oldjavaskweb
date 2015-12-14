<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alZclx = (ArrayList)request.getAttribute("alZclx");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function addZg(){
	window.location.href='/javaskweb/zclxServlet?method=getFromZG';
}

function add(){
	window.location.href='/javaskweb/system.do?op=toZclxAdd';
}

function edit(zclxbm){
	window.location.href='/javaskweb/system.do?op=toZclxEdit&zclxbm='+zclxbm;
}

function del(sid){
	if(confirm('确定删除该注册类型？')){
		window.location.href='/javaskweb/system.do?op=zclxDelete&sid='+sid;
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
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">注册类型管理</a></td>
        <td align="right"><!--<input type="button" name="btn" value="从征管获取" style="cursor:hand;" onclick="addZg();" />&nbsp;&nbsp;--><input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
   </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="15%">注册类型编码</th>
        <th width="*">注册类型名称</th>
        <th width="30%">注册类型简称</th>
        <th width="10%">启用标志</th>
        <th width="15%">操作</th>
      </tr>
<%
if(alZclx!=null&&!alZclx.isEmpty()){
	Iterator it = alZclx.iterator();
	while(it.hasNext()){
		Zclx zclx = (Zclx)it.next();
		
%>
	  <tr>
        <td><%=zclx.getZclxbm() %>&nbsp;</td>
        <td><%=zclx.getZclxmc() %>&nbsp;</td>
        <td><%=zclx.getZclxjc() %>&nbsp;</td>
        <td><%=zclx.getStatus()==1?"启用":"未启用" %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" onclick="edit('<%=zclx.getZclxbm() %>');" style="cursor:hand;" />&nbsp;&nbsp;<!--<input type="button" name="btn" value="删 除" onclick="del(<%=zclx.getSid() %>);" style="cursor:hand;" />--></td>
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
