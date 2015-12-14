<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx) request.getAttribute("nsrxx");
ArrayList zcJqxxList =(ArrayList)request.getAttribute("zcJqxxList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	window.location.href='regist.fk.html';
}
function get_id(){
	var s='';
	for(var i=0;i<document.all.form2.elements.length;i++){
		t= document.all.form2.elements[i].name;
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
function jqsd(){
	if(confirm('确定选择机器？')){
	    var nsrJqxxStr = get_id();

		if(nsrJqxxStr==''){
			alert('请选择机器！');
		}
		else{
		    document.getElementById('nsrJqxxStr').value = nsrJqxxStr;
		    var frm = document.form2;
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
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;机器管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayer.do?op=toImport" class="nav">锁定</a> &gt;&gt;&nbsp;&nbsp;机器锁定</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
        <td align="right"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="18%">纳税人微机编码：</td>
        <td><%=nsrxx.getNsrwjbm() %>&nbsp;</td>
        <td width="17%">纳税人识别号：</td>
        <td width="33%"><%=nsrxx.getNsrsbh() %>&nbsp;</td>
      </tr>
      <tr>
        <td>纳税人名称：</td>
        <td><%=nsrxx.getNsrmc() %>&nbsp;</td>
        <td>经营地址：</td>
        <td><%=nsrxx.getJydz() %>&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">机器信息</a></div></td>
      </tr>
    </table>
    <form name="form2" action="taxpayer.do?op=sdJqxx"  method="post">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="5"></th>
        <th width="20%">机器编号</th>
        <th width="20%">机器名称</th>
        <th width="15%">单张开票限额</th>
        <th width="10%">离线开票总金额</th>
        
      </tr>
<%

if(zcJqxxList!=null&&!zcJqxxList.isEmpty()){
	Iterator it = zcJqxxList.iterator();
	while(it.hasNext()){
		Jqxx jqxx = (Jqxx)it.next();
%>
	  <tr>
	    <td align="center"><input type="checkbox" value="<%=jqxx.getJqbh()%>" name="a<%=jqxx.getJqbh()%>"></input></td>
        <td align="center"><%=jqxx.getJqbh() %>&nbsp;</td>
        <td align="center"><%=jqxx.getJqxhmc() %>&nbsp;</td>
        <td align="center"><%=jqxx.getDzkpxe() %>&nbsp;</td>
          <td align="center"><%=jqxx.getLxkpzje() %>&nbsp;</td>
        
      </tr>
<%
	}
}
%>      </table>
<table>
      <tr> 
      <td bgcolor="#f2f2f2"><textarea name="sdyy" id="sdyy" value="" style=" width:300px;height:100px" ></textarea></td>
     </tr><tr> <td><input type="button" class="btn" name="add_btn" value=" 确 定 " onclick="jqsd();"   align="right"/></td>
    </tr>
    <input type="hidden" id="nsrJqxxStr" name="nsrJqxxStr">
    <tr></tr>
  </table>
  </div>
  <input type="hidden" value=<%=nsrxx.getNsrwjbm() %> name="nsrwjbm"/>
</div>
</body>
</html>
