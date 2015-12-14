<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSzsm = (ArrayList)request.getAttribute("alSzsm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function get_id(){
	var s='';
	for(var i=0;i<document.all.form1.elements.length;i++){
		t= document.all.form1.elements[i].name;
		//alert(t)
		if(t!=''){
			//alert(document.all(t).checked)
			if(document.all(t).checked){
	   			var m = document.all(t).value;
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
	if(confirm('确定选择的品目？')){
		var SMBMS = get_id();
		if(SMBMS==''){
			alert('请选择税种税目！');
		}
		else{
			document.all('smbmStr').value = SMBMS;
			var frm = document.form1;
			frm.submit();
		}
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
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="szsm.html" class="nav">品目设置</a>&gt;&gt;&nbsp;&nbsp;选择品目</td>
        <td align="right"><input type="button" name="btn" value=" 确 定 " onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;<input type="button" name="btn" value=" 返 回 " style="cursor:hand;" onclick="window.location.href='/javaskweb/pmszServlet?method=list';" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/pmszServlet?method=addZg" method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="8%">&nbsp;</th>
        <th width="8%">品目编码</th>
        <th width="20%">品目名称</th>
        <th width="15%">品目简称</th>
        <th width="10%">税率（%）</th>
        <th width="*">&nbsp;</th>
      </tr>
<%
if(alSzsm!=null&&!alSzsm.isEmpty()){
	Iterator it = alSzsm.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
%>
	  <tr>
        <td><input type="checkbox" name="a<%=(String)hm.get("SMBM")%>" value="<%=(String)hm.get("SMBM")%>">&nbsp;</td>
        <td><%=(String)hm.get("SMBM")%>&nbsp;</td>
        <td><%=(String)hm.get("SMMC")%>&nbsp;<input type="hidden" name="smmc_<%=(String)hm.get("SMBM")%>" value="<%=(String)hm.get("SMMC")%>"></td>
        <td><%=(String)hm.get("SMJC")%>&nbsp;<input type="hidden" name="smjc_<%=(String)hm.get("SMBM")%>" value="<%=(String)hm.get("SMJC")%>"></td>
        <td><%=(Double)hm.get("SL")*100%>&nbsp;<input type="hidden" name="sl_<%=(String)hm.get("SMBM")%>" value="<%=(Double)hm.get("SL")%>"></td>
        <td>&nbsp;<input type="hidden" name="szbm_<%=(String)hm.get("SMBM")%>" value="<%=(String)hm.get("SZBM")%>"></td>
      </tr>
<%
	}
}
%>
    </table>
    <input type="hidden" name="smbmStr">
    </form>
  </div>
</div>
</body>
</html>
