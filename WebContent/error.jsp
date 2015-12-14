<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
String errorMsg = (String)request.getAttribute("errorMsg");
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
        <th width="5%">错误信息</th>
      </tr>
      <tr>
        <td height="152" align="center" style="text-align:center">
          <font color="#FF0000" style="font-size:16px;"><%=errorMsg%></font><br /><br />
          <input type="button" name="btn" value=" 返 回 " onclick="window.history.go(-1)" />
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>