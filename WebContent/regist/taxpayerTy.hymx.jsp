<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alHymx = (ArrayList)request.getAttribute("alHymx");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择所属行业明细</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	var frm = document.form1;
	var val = "";
	var j=0;
	for(var i=1;i<frm.radio.length;i++){   
   		if(frm.radio[i].checked){
			val = frm.radio[i].value;
			j=1;
    	}
	}
	if(j==0){
		alert('请选择所属行业明细！');
	}
	else{
		var val_arr = val.split(',');
		opener.document.all('hymxmc').value = val_arr[1];
		opener.document.all('hymxbm').value = val_arr[0];
		window.close();
	}
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td>&nbsp;&nbsp;<a href="#" class="nav">选择所属行业明细</a></td>
        <td align="right"><input type="button" name="addBtn" value="确 定" onClick="add();" style="cursor:hand;"/>
          &nbsp;&nbsp;&nbsp;&nbsp; </td>
      </tr>
    </table>
    <form name="form1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="15%">&nbsp;<input type="radio" name="radio" style="display:none;"></th>
        <th width="20%">代码</th>
        <th width="40%">名称</th>
        <th width="25%">简称</th>
      </tr>
      <%
if(alHymx!=null&&!alHymx.isEmpty()){
Iterator it = alHymx.iterator();
while(it.hasNext()){
	Hymx hymx = (Hymx)it.next();
%>
      <tr>
        <td><input type="radio" name="radio" value="<%=(hymx.getHymxbm()+","+hymx.getHymxmc()) %>"></td>
        <td><%=hymx.getHymxbm() %>&nbsp;</td>
        <td><%=hymx.getHymxmc() %>&nbsp;</td>
        <td><%=hymx.getHymxjc() %>&nbsp;</td>
      </tr>
      <%
}
}
%>
    </table>
    </form>
  </div>
</div>
</body>
</html>
