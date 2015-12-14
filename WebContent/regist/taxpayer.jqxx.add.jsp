<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alJqxh = (ArrayList)request.getAttribute("alJqxh");
String nsrwjbm = (String)request.getAttribute("nsrwjbm");

ArrayList nsrszsm = (ArrayList)request.getAttribute("nsrszsm");
String jumpFlag = (String)request.getAttribute("jumpFlag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/Calendar.js" ></script>
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
function get_id(){
	var s='';
	var length = document.getElementById('form1').elements.length
	for(var i=3;i<(length-1);i++){
		t= document.getElementById('form1').elements[i].name;
		//alert(t);
		if(t!=''){
			//alert(document.getElementById(t).checked)
			if(document.all.getElementById(t).checked){
	   			var m = document.all.getElementById(t).value;
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
function checkPZ(fpbm,obj){
	if(obj.checked){
		var old_pzbm = document.getElementById('pzbm').value;
		if(old_pzbm!=''){
			if(fpbm!=old_pzbm){
				alert('选择的品目对应票种与之前票种不通，不能选择！');
				obj.checked = false;
			}
			else{
				document.getElementById('num').value = document.getElementById('num').value*1+1;
			}
		}
		else{
			document.getElementById('pzbm').value = fpbm;
			document.getElementById('num').value = document.getElementById('num').value*1+1;
		}
	}
	else{
		var smbmStr = get_id();
		if(smbmStr==''){
			document.getElementById('pzbm').value = '';
		}
		document.getElementById('num').value = document.getElementById('num').value*1-1;
	}
}

function add(){
	var jqszsmStr = get_id();
	//alert(jqszsmStr);
	var num = document.getElementById('num').value*1;
	if(num==0){
		alert('请选择机器对应税种税目！');
	}
//	else if(num>6){
//		alert('机器对应税种税目不能超过6个!');
//	}
	else{
		var frm = document.form1;
		if(validator(frm)){
			document.getElementById('jqszsmStr').value = jqszsmStr;
			frm.action='/javaskweb/taxpayerServlet?method=skzzxxAction';
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
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayerServlet?method=import" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;注册登记</td>
        <td><div class="userTitleGreen"> <a href="#" class="black">税务登记信息</a> </div>
          <div class="userTitleRed">
            <ul>
              <li><a href="#" class="black">税控装置信息录入</a></li>
            </ul>
          </div>
          <div class="userTitleGreen"><a href="#" class="black">发卡</a></div></td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" id="form1" action="" method="post">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
        <tr>
          <th width="20%">机器型号</th>
          <th width="20%">机器编号</th>
          <th width="20%">税控卡号</th>
          <th width="*">&nbsp;</th>
        </tr>
        <tr>
          <td align="center"><select name="jqxhbm" required="required" controlName="机器型号">
              <option value="" >--请选择机器型号--</option>
<%
if(alJqxh!=null&&!alJqxh.isEmpty()){
	Iterator it = alJqxh.iterator();
	while(it.hasNext()){
		Jqxh jqxh = (Jqxh)it.next();
		
%>
              <option value="<%=jqxh.getJqxhbm() %>"><%=jqxh.getJqxhmc() %></option>
<%
	}
}
%>
            </select>
            &nbsp;<font color="#FF0000">*</font></td>
          <td align="center"><input id="jqbh" name="jqbh" type="text" maxlength="16" required="required" dataType="integer" controlName="机器编号" />
            &nbsp;<font color="#FF0000">*</font></td>
          <td align="center"><input id="skkh" name="skkh" type="text" maxlength="16" required="required" dataType="integer" controlName="税控卡号" />
            &nbsp;<font color="#FF0000">*</font></td>
          <td align="center">&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">税控装置可用税种税目</a></div></td>
          <td><font color="#FF0000">请选择税控装置对应税种税目，最少1个，最多6个！</font></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      
        <tr>
          <th width="20%">税目编码</th>
          <th width="30%">税目名称</th>
          <th width="10%">税率</th>
          <th>经营项目内容</th>
          <th width="10%">选择</th>
        </tr>
<%
if(nsrszsm!=null&&!nsrszsm.isEmpty()){
	Iterator it = nsrszsm.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		
%>
        <tr>
          <td align="center"><%=(String)hm.get("smbm") %></td>
          <td align="center"><%=(String)hm.get("smmc") %></td>
          <td align="center"><%=(Double)hm.get("sl") %></td>
          <td align="center"><%=(String)hm.get("smjc") %></td>
          <td align="center"><input type="checkbox" name="a<%=(String)hm.get("smbm") %>" id="a<%=(String)hm.get("smbm") %>" value="<%=(String)hm.get("smbm") %>" onclick="checkPZ('<%=(String)hm.get("fpbm") %>',this)" /></td>
        </tr>
<%
	}
}
%>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center">
            <input type="hidden" id="nsrwjbm" name="nsrwjbm" value="<%=nsrwjbm %>"/>
            <input type="hidden" id="jqszsmStr" name="jqszsmStr"/>
            <input type="hidden" name="pzbm" id="pzbm"/>
            <input type="hidden" name="num" id="num" value="0"/>
            <input type="button" name="next_btn" value="下一步" style="cursor:hand;" onclick="add();" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
