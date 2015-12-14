<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../images/CheckBrower.js"></script>
<script type="text/javascript">
function readUserCard(){
	if(confirm('确定用户卡已插入！')){
		sAlert('读卡中，请等待……');
		try{
			var result = document.ReadUCApplet.read();
			if(result==1){
				div_close();
				window.location.href='/javaskweb/ReadCardInfoServlet?method=read&type=1';
			}
			else{
				div_close();
				alert('卡基本信息读取失败！');
			}
		}
		catch(e){
			div_close();
			alert('卡基本信息读取失败！');
		}
	}
}

function readFiscalCard(){
	if(confirm('确定税控卡卡已插入！')){
		sAlert('读卡中，请等待……');
		try{
			var result = document.ReadFCApplet.read();
			//var result1 = document.ReadInvoiceApplet.read();
			if(result==1){
				div_close();
				window.location.href='/javaskweb/ReadCardInfoServlet?method=read&type=0';
			}
			else{
				div_close();
				alert('卡基本信息读取失败！');
			}
		}
		catch(e){
			div_close();
			alert('卡基本信息读取失败！');
		}
	}
}
</script>
</head>
<body>
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
    <tr>
    <td width="18%">读卡操作：</td>
        <td><p>
          <input type="button" name="btn_user" id="btn_user" value="读用户卡" onClick="readUserCard()">
			<jsp:plugin name="ReadUCApplet" type="applet" code="com.jsdt.web.applet.TYUcReadlet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="button" name="btn_fiscal" id="btn_fiscal" value="读税控卡" onClick="readFiscalCard()">
            <jsp:plugin name="ReadFCApplet" type="applet" code="com.jsdt.web.applet.TYFcReadlet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
          <br>
        </p></td>
      </tr>
      <tr>
        <td colspan="2" align="center">&nbsp;&nbsp;

        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>