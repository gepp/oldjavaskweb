<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSzsm = (ArrayList)request.getAttribute("alSzsm");
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String nsrszsmStr = (String)request.getAttribute("nsrszsmStr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>税目管理</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
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
	if(confirm('确定选择的税种税目？')){
		var nsrszsmStr = get_id();
		if(nsrszsmStr==''){
			alert('请选择税种税目！');
		}
		else{
			var nsrwjbm = document.all('nsrwjbm').value;
			//alert(NSRWJBM);
			document.all('iframe').src='/javaskweb/taxpayer.do?op=addNrsSzsmAction&nsrwjbm='+nsrwjbm+'&nsrszsmStr='+nsrszsmStr;
		}
	}
}
function success(successMsg,url){
	alert(successMsg);
	opener.location.href = url;
	window.close();
}
function error(errMsg){
	alert(errMsg);
	window.close();
}

function checkAll(){
	 var check = document.getElementsByName("abc");
	    for(var i=0;i<check.length;i++){
	        check[i].checked=document.getElementById("mycheckbox").checked;
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
        <td>&nbsp;&nbsp;<a href="#" class="nav">税目管理</a></td>
        <td align="right">
          <input type="button" name="addBtn" value="增 加" onClick="add();" style="cursor:hand;"/>
          <input type="hidden" name="nsrwjbm" value="<%=nsrwjbm%>">
          &nbsp;&nbsp;&nbsp;&nbsp; </td>
      </tr>
    </table>
    <form name="form2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="10%"><input type="checkbox" id="mycheckbox" onClick="checkAll() " /></th>
        <th width="15%">税目编码</th>
        <th width="25%">税目名称</th>
        <th width="25%">简称</th>
        <th width="10%">税率</th>
        <th width="10%">税目索引</th>
      </tr>
      <%
if(alSzsm!=null&&!alSzsm.isEmpty()){
Iterator it = alSzsm.iterator();
while(it.hasNext()){
	Szsm szsm = (Szsm)it.next();
%>
      <tr>
        <td><input type="checkbox" name="abc" value="<%=szsm.getSmbm() %>" <% if(nsrszsmStr.indexOf(szsm.getSmbm())>=0){out.println("checked");}%>></td>
        <td><%=szsm.getSmbm() %>&nbsp;</td>
        <td><%=szsm.getSmmc() %>&nbsp;</td>
        <td><%=szsm.getSmjc() %>&nbsp;</td>
        <td><%=szsm.getSl() %>&nbsp;</td>
        <td><%=szsm.getSmsy() %>&nbsp;</td>
      </tr>
      <%
}
}
%>
	  
    </table>
    </form>
    <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>
