<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
//String WJBMBC = (String)request.getAttribute("WJBMBC");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}
function add(){
	var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
	if(nsrwjbm==''){
		alert('请输入纳税人微机编码！');
	}
	else{
		
		//var wjbmbc = trim(document.getElementById('WJBMBC').value);

		
		var num = nsrwjbm.length;

		for(var i=num;i<16;i++){
			nsrwjbm="0"+nsrwjbm;
		}

		
		window.location.href='/javaskweb/taxpayerServlet?method=checkTaxpayer&nsrwjbm='+nsrwjbm;
	}
}

function kd(){
	//alert(event.keyCode);
	if(event.keyCode==13){
		document.getElementById("next_btn").focus(); 
	}
}
</script>
</head>

<body onkeydown="kd();">
<!--==========right部分==========-->
<div id="right">
<div id="tool">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">纳税户信息管理</a></td>
      </tr>
    </table>
  </div>
  <div id="main"> <br />
      <br />
      <form action="" method="post">
	  <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" id="userLogin" >
      <tr>
        <th width="5%">纳税户信息管理</th>
        </tr>
      
      <tr>
        <td height="152"><br />
          <strong>请输入纳税户微机编码：</strong>
          <input name="nsrwjbm" id="nsrwjbm" type="text" maxlength="8"/>
          
          <input type="button" id="next_btn" name="next_btn" value="下一步" onclick="add();" style="cursor:hand;"/>
          <br />
          <br />
          <br />
          </td>
        </tr>
    </table>
	  </form>
      <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>