<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alUser = (ArrayList)request.getAttribute("alUser");
Role role = (Role)request.getAttribute("role");
String roleuser = (String)request.getAttribute("roleuser");
String swjgbm = (String)request.getAttribute("swjgbm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>分配用户</title>
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
	var usernameStr = get_id();
	if(usernameStr==''){
		alert("请选择角色对应用户！");
	}
	else{
		var sid = document.getElementById('sid').value;
		window.location.href = '/javaskweb/roleServlet?method=useraddzg&usernameStr='+usernameStr+'&sid='+sid;
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
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/roleServlet?method=roleswjg&sid=<%=role.getSid() %>" class="nav">权限管理</a>&gt;&gt;&nbsp;&nbsp;用户分配</td>
        <td align="right">
        <input type="hidden" id="sid" name="sid" value="<%=role.getSid() %>"/>
        <input type="button" name="btn" value=" 确 定 " onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;
        <input type="button" name="btn" value=" 返 回 " style="cursor:hand;" onclick="window.location.href='/javaskweb/roleServlet?method=roleswjg&sid=<%=role.getSid() %>';" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="8%">&nbsp;</th>
        <th width="15%">登录名</th>
        <th width="15%">编码</th>
        <th width="15%">姓名</th>
        <th width="30%">税务机关</th>
        <th width="*">&nbsp;</th>
      </tr>
<%
if(alUser!=null&&!alUser.isEmpty()){
	Iterator it = alUser.iterator();
	while(it.hasNext()){
		User user = (User)it.next();
		
%>
      <tr>
        <td><input type="checkbox" id="a<%=user.getUsername() %>" name="a<%=user.getUsername() %>" value="<%=user.getUsername() %>" <% if(roleuser.indexOf(user.getUsername()+",")>0){out.println("checked");} %> /></td>
        <td><%=user.getDlm() %>&nbsp;</td>
        <td><%=user.getUsername() %>&nbsp;</td>
        <td><%=user.getActualname() %>&nbsp;</td>
        <td><%=user.getSwjgmc() %>&nbsp;</td>
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