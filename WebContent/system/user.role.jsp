<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alRole = (ArrayList)request.getAttribute("alRole");
User user = (User)request.getAttribute("user");
String userrole = (String)request.getAttribute("userrole");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分配角色</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function get_id(){
	var s='';
	for(var i=0;i<document.all.form1.elements.length;i++){
		t= document.all.form1.elements[i].name;
		//alert(t)
		if(t!=''){
			//alert(document.all(t).checked)
			if(document.getElementById(t).checked){
	   			var m = document.getElementById(t).value;
				if(s==""){
			  		s=m;
				}
				else{
					s=s+','+m;
				}
			}
		}
	}
	return s;
	//alert(s);
}
function add(){
	if(confirm('确认分配角色？')){
		var roleidStr = get_id();
		var username = document.getElementById('username').value;
		document.getElementById('iframe').src = '/javaskweb/system.do?op=userAddRoleAction&roleidStr='+roleidStr+'&username='+username;
	}	
}

function success(successMsg,url){
	alert(successMsg);
	window.location.href=url;
}

function error(errorMsg){
	alert(errorMsg);
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toUserList" class="nav">用户管理</a>&gt;&gt;&nbsp;&nbsp;角色分配</td>
        <td align="right">
        <input type="hidden" id="username" name="username" value="<%=user.getUsername() %>"/>
        <input type="button" name="btn" value=" 确 定 " onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;
        <input type="button" name="btn" value=" 返 回 " style="cursor:hand;" onclick="window.location.href='/javaskweb/system.do?op=toUserList';" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="8%">&nbsp;</th>
        <th width="15%">角色名称</th>
        <th width="40%">角色说明</th>
        <th width="*">&nbsp;</th>
      </tr>
<%
if(alRole!=null&&!alRole.isEmpty()){
	Iterator it = alRole.iterator();
	while(it.hasNext()){
		Role role = (Role)it.next();
		
%>
      <tr>
        <td><input type="checkbox" id="a<%=role.getSid() %>" name="a<%=role.getSid() %>" value="<%=role.getSid() %>" <% if(userrole.indexOf(role.getSid()+",")>0){out.println("checked");} %> /></td>
        <td><%=role.getRolename() %>&nbsp;</td>
        <td><%=role.getNote() %>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
<%
	}
}
%>
    </table>
    <iframe id="iframe" name="iframe" width="0" height="0" frameborder="0"></iframe>
    </form>
  </div>
</div>
</body>
</html>