<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alRole = (ArrayList)request.getAttribute("alRole");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色权限管理</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	window.location.href='/javaskweb/system.do?op=toRoleAdd';
}

function edit(sid){
	window.location.href='/javaskweb/system.do?op=toRoleEdit&sid='+sid;
}

function del(sid){
	if(confirm('确定删除角色？')){
		window.location.href='/javaskweb/system.do?op=roleDelete&sid='+sid;
	}
}

function adduser(sid){
	//window.location.href='/javaskweb/roleServlet?method=roleuser&sid='+sid;
	window.location.href='/javaskweb/system.do?op=toRoleAddUser&sid='+sid;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">角色权限管理</a></td>
        <td align="right"><input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
   </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="30%">角色名称</th>
        <th width="50%">角色说明</th>
        <th width="20%">操作</th>
      </tr>
<%
if(alRole!=null&&!alRole.isEmpty()){
	Iterator it = alRole.iterator();
	while(it.hasNext()){
		Role role = (Role)it.next();
		
%>
      <tr>
        <td><%=role.getRolename() %>&nbsp;</td>
        <td><%=role.getNote() %>&nbsp;</td>
        <td>
          <input type="button" name="btn" value="修 改" onclick="edit(<%=role.getSid() %>);" />&nbsp;
          <input type="button" name="btn" value="删 除" onclick="del(<%=role.getSid() %>);" />&nbsp;
           <input type="button" name="btn" value="人 员" onclick="adduser(<%=role.getSid() %>);" />
        </td>
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
