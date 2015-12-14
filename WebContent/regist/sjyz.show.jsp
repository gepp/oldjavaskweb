<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
int szsmResult = (Integer)request.getAttribute("szsmResult");
int zclxResult = (Integer)request.getAttribute("zclxResult");
int nsrxxResult = (Integer)request.getAttribute("nsrxxResult");
//int oldResult= (Integer)request.getAttribute("oldResult");
String msg1 = "";
String msg2 = "";
String msg3 = "";
//String msg4="";
if(szsmResult==-1){
	msg1 = "税种税目表导入失败";
}
else{
	msg1 = "税种税目表导入成功";
}
if(zclxResult==-1){
	msg2 = "注册类型表导入失败";
}
else{
	msg2 = "注册类型表导入成功";
}
if(nsrxxResult==-1){
	msg3 = "纳税人信息表导入失败";
}
else{
	msg3 = "纳税人信息表导入成功";
}
//if(oldResult==-1){
//	msg4 = "2011年1月2月数据导入失败";
//}
//else{
//	msg4 = "2011年1月2月数据导入成功";
//}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/javaskweb/images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="/javaskweb/images/string.js" ></script>
<script language="javascript" src="/javaskweb/images/Calendar.js" ></script>
<script language="javascript">
function switchImage(){
	if(document.getElementById('btn').src.search('hide_01.gif')!=-1){
		document.getElementById('btn').src = "/javaskweb/images/hide.gif";
	}else{
		document.getElementById('btn').src = "/javaskweb/images/hide_01.gif";
	}
}

function changeBar(){
	parent.switchMenuBar();
	switchImage();
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
  </div>
  <div id="main">
	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" id="userLogin2" >
      <tr>
        <th width="5%">信息提示</th>
      </tr>
      <tr>
        <td height="152" align="center" style="text-align:center">
          <font color="#FF0000" style="font-size:16px;"><%=msg1%></font><br />
          <font color="#FF0000" style="font-size:16px;"><%=msg2%></font><br />
          <font color="#FF0000" style="font-size:16px;"><%=msg3%></font><br />
       
          <br />
          <input type="button" name="btn" value=" 返 回 " onclick="window.history.go(-1)" />
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>