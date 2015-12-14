<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSwjg = (ArrayList)request.getAttribute("alSwjg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>税务机关列表</title>
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
	var swjgbmStr = "";
	var swjgmcStr = "";
	for(var i=0;i<strArr.length;i++){
		var valStr = strArr[i];
		var valArr = valStr.split("|");
		if(swjgbmStr==""){
			swjgbmStr = "'"+valArr[0]+"'";
		}
		else{
			swjgbmStr = swjgbmStr+",'"+valArr[0]+"'";
		}
		if(swjgmcStr==""){
			swjgmcStr = valArr[1];
		}
		else{
			swjgmcStr = swjgmcStr+","+valArr[1];
		}
	}

	opener.document.getElementById('swjgbm').value=swjgbmStr;
	opener.document.getElementById('swjgmc').value=swjgmcStr;
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
        <th width="10%">税务机关编码</th>
        <th width="*%">税务机关名称</th>
        <th width="25%">上级税务机关</th>
        
      </tr>
<%
if(alSwjg!=null&&!alSwjg.isEmpty()){
	Iterator it = alSwjg.iterator();
	while(it.hasNext()){
		Swjg swjg = (Swjg)it.next();
		
%>
      <tr>
        <td><input type="checkbox" name="a<%=swjg.getSwjgbm() %>" id="a<%=swjg.getSwjgbm() %>" value="<%=swjg.getSwjgbm()+"|"+swjg.getSwjgmc() %>" /></td>
        <td><%=swjg.getSwjgbm() %>&nbsp;</td>
        <td><%=swjg.getSwjgmc() %>&nbsp;</td>
        <td><%=swjg.getSjswjgmc() %>&nbsp;</td>
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
