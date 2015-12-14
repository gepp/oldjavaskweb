<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alUser = (ArrayList)request.getAttribute("alUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	window.location.href='/javaskweb/system.do?op=toUserAdd';
}

function edit(username){
	window.location.href='/javaskweb/system.do?op=toUserEdit&username='+username;
}

function del(sid){
	if(confirm('确定删除该用户？')){
		window.location.href='/javaskweb/system.do?op=userDelete&sid='+sid;
	}
}

function qxfp(username){
window.location.href='/javaskweb/system.do?op=toUserAddRole&username='+username;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">用户管理</a></td>
        <td align="right"><input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="10%">登录名</th>
        <th width="15%">真实姓名</th>
        <th width="20%">所属税务机关</th>
        <th width="10%">状态</th>
        <th width="15%">操作</th>
      </tr>
<%
if(alUser!=null&&!alUser.isEmpty()){
	Iterator it = alUser.iterator();
	while(it.hasNext()){
		User user = (User)it.next();
		
%>
      <tr>
        <td><%=user.getUsername() %>&nbsp;</td>
        <td><%=user.getActualname() %>&nbsp;</td>
        <td><%=user.getSwjgmc() %>&nbsp;</td>
        <td><%=user.getStatus()==1?"启用":"未启用" %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" style="cursor:hand;" onclick="edit('<%=user.getUsername() %>');" />&nbsp;<input type="button" style="cursor:hand;" name="btn" value="删 除" onclick="del(<%=user.getSid() %>);" />&nbsp;<input type="button" style="cursor:hand;" name="btn" value="权 限" onclick="qxfp('<%=user.getUsername() %>');" /></td>
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
