<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>U盘明细申报</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>

<body>
<table width="200" border="0" align="center">
  <tr>
    <td>
    <%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<jsp:plugin name="onlineRJYFileUpload" type="applet" archive="fileupload.jar" codebase="." code="com.jsdt.netdeclare.fileupload.OnlineRJYFileUploadApplet.class" height="125" width="538" >
<jsp:params>  
  <jsp:param name="serverUrl" value="<%=basePath%>"   />
</jsp:params>
<jsp:fallback>无法加载Applet</jsp:fallback>
</jsp:plugin>
    </td>
  </tr>
</table>



</body>
</html>