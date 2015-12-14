<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alFp = (ArrayList)request.getAttribute("alFp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票票种管理</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function addZg(){
	window.location.href='/javaskweb/fpServlet?method=getFromZG';
}

function add(){
	window.location.href='/javaskweb/system.do?op=toFpAdd';
}

function edit(fpbm){
	window.location.href='/javaskweb/system.do?op=toFpEdit&fpbm='+fpbm;
}

function del(sid){
	if(confirm('确定删除该票种？')){
		window.location.href='/javaskweb/system.do?op=fpDelete&sid='+sid;
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
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">发票票种管理</a></td>
        <td align="right"><input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="25%">发票票种编码</th>
        <th width="*">发票票种名称</th>
        <th width="15%">发票票种状态</th>
        <th width="15%">操作</th>
      </tr>
<%
if(alFp!=null&&!alFp.isEmpty()){
	Iterator it = alFp.iterator();
	while(it.hasNext()){
		Fp fp = (Fp)it.next();
%>
      <tr>
        <td><%=fp.getFpbm() %>&nbsp;</td>
        <td><%=fp.getFpmc() %>&nbsp;</td>
        <td><%=fp.getStatus()==1?"启用":"未启用" %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" onclick="edit('<%=fp.getFpbm() %>');" style="cursor:hand;" />&nbsp;&nbsp;<!-- <input type="button" name="btn" value="删 除" onclick="del(<%=fp.getSid() %>);" style="cursor:hand;" /> --></td>
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