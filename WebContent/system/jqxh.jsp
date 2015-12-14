<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alJqxh = (ArrayList)request.getAttribute("alJqxh");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机器型号管理</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	window.location.href='/javaskweb/system.do?op=toJqxhAdd';
}

function edit(jqxhbm){
	window.location.href='/javaskweb/system.do?op=toJqxhEdit&jqxhbm='+jqxhbm;
}

function del(sid){
	if(confirm('确定删除该机器型号？')){
		window.location.href='/javaskweb/system.do?op=jqxhDelete&sid='+sid;
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
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">机器型号管理</a></td>
        <td align="right"><input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="25%">机器型号编码</th>
        <th width="*">机器型号名称</th>
        <th width="25%">生产厂商编码</th>
        <th width="15%">机器型号状态</th>
        <th width="15%">操作</th>
      </tr>
<%
if(alJqxh!=null&&!alJqxh.isEmpty()){
	Iterator it = alJqxh.iterator();
	while(it.hasNext()){
		Jqxh jqxh = (Jqxh)it.next();
%>
      <tr>
        <td><%=jqxh.getJqxhbm() %>&nbsp;</td>
        <td><%=jqxh.getJqxhmc() %>&nbsp;</td>
        <td><%=jqxh.getSccs() %>&nbsp;</td>
        <td><%=jqxh.getStatus()==1?"启用":"未启用" %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" onclick="edit('<%=jqxh.getJqxhbm() %>');" style="cursor:hand;" />&nbsp;&nbsp;<input type="button" name="btn" value="删 除" onclick="del(<%=jqxh.getSid() %>);" style="cursor:hand;" /></td>
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