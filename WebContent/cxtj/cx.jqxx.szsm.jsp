<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSzsm = (ArrayList)request.getAttribute("alSzsm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>品目信息列表</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
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
	var s='';
	for(var i=1;i<document.all.form1.elements.length;i++){
		t= document.all.form1.elements[i].name;
		//alert(t)
		if(t!=''){
			//alert(document.all(t).checked)
			if(document.getElementById(t).checked){
	   			var m = document.getElementById(t).value;
	   			if(m!=''){
	   				if(s==""){
				  		s=m;
					}
					else{
						s=s+','+m;
					}
		   		}
			}
		}
	}
	return s;
	//alert(s);
}

function add(){
	var str = get_id();
	var strArr = str.split(",");
	var smbmStr = "";
	var smmcStr = "";
	for(var i=0;i<strArr.length;i++){
		var valStr = strArr[i];
		var valArr = valStr.split("|");
		if(smbmStr==""){
			smbmStr = "'"+valArr[0]+"'";
		}
		else{
			smbmStr = smbmStr+",'"+valArr[0]+"'";
		}
		if(smmcStr==""){
			smmcStr = valArr[1];
		}
		else{
			smmcStr = smmcStr+","+valArr[1];
		}
	}

	opener.document.getElementById('smbm').value=smbmStr;
	opener.document.getElementById('smmc').value=smmcStr;
	window.close();
	//alert(str);
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;</td>
        <td align="right"><input type="button" name="btn" value=" 确 定 " onClick="add();"></td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="10%"><input type="checkbox" name="checkbox" onclick="checkall(this);" /></th>
        <th width="15%">税目编码</th>
        <th width="40%">税目名称</th>
        <th>&nbsp;</th>
        
      </tr>
<%
if(alSzsm!=null&&!alSzsm.isEmpty()){
	Iterator it = alSzsm.iterator();
	while(it.hasNext()){
		Szsm szsm = (Szsm)it.next();
		
%>
      <tr>
        <td><input type="checkbox" name="a<%=szsm.getSmbm() %>" id="a<%=szsm.getSmbm() %>" value="<%=szsm.getSmbm()+"|"+szsm.getSmmc() %>" /></td>
        <td><%=szsm.getSmbm() %>&nbsp;</td>
        <td><%=szsm.getSmmc() %>&nbsp;</td>
        <td>&nbsp;</td>
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
