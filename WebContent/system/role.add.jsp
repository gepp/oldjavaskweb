<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alMenu = (ArrayList)request.getAttribute("alMenu");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限增加</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
function checkall(obj){
	if(obj.checked==true){
		var frm = document.form1;
		for (var i=1;i<frm.elements.length;i++)
		{
			var e = frm.elements[i];
			//alert(e);
			e.checked = true;
		}
	}
	else{
		var frm = document.form1;
		for (var i=1;i<frm.elements.length;i++)
		{
			var e = frm.elements[i];
			e.checked = false;
		}
	}
	//get_id();
}
function get_id(){
var id_str = '';
$("[name='abc']").each(function(){
     if($(this).attr("checked"))
     {
    id_str += ","+$(this).attr("value");
   }
});
return id_str;

}
function add(){
	var menuStr = get_id();
	document.getElementById('menuStr').value=menuStr;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toRoleList" class="nav">权限管理</a>&gt;&gt;&nbsp;&nbsp;新增</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/system.do?op=roleAddAction" method="post" onSubmit="add();return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
        <tr>
          <td width="15%">角色名称：</td>
          <td><input type="text" style="width:30%" name="rolename" required="required" controlName="角色名称" /></td>
        </tr>
        <tr>
          <td>角色说明：</td>
          <td><textarea name="note" id="note" cols="45" rows="5"></textarea> </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="8%">&nbsp;<input type="checkbox" id="b" name="b" value="" onclick="checkall(this);"/></th>
        <th width="15%">模块代码</th>
        <th width="40%">模块名称</th>
        <th width="*">&nbsp;</th>
      </tr>
<%
if(alMenu!=null&&!alMenu.isEmpty()){
	Iterator it = alMenu.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		
%>
      <tr>
        <td>
<%
if((Integer)hm.get("parentid")!=0){
%>
			<input type="checkbox" id="a<%=(Integer)hm.get("menuid") %>" name="abc" value="<%=(String)hm.get("menucode") %>" />
<%
}
%>
&nbsp;
        </td>
        <td><%=(String)hm.get("menucode") %>&nbsp;</td>
        <td><%=(String)hm.get("menuname") %>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
<%
	}
}
%>
    </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
        <tr>
          <td height="40" align="center">
            <input type="hidden" name="menuStr" id="menuStr" />
            <input type="submit" name="next_btn" value="确 定" style="cursor:hand;"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/system.do?op=toRoleList';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
