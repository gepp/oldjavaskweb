<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script language="javascript">
//无提示关闭IE窗口，目前适用于IE所有版本(目前最新为IE7.0）
function CloseWindowNoAsk(){ 
	var ua=navigator.userAgent 
	var ie=navigator.appName == "Microsoft Internet Explorer" ? true : false 
	
	if(ie) { 
		var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE ")))) 
		if(IEversion< 5.5){ 
			var str  = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">' 
			str += '<param name="Command" value="Close"></object>'; 
			document.body.insertAdjacentHTML("beforeEnd", str); 
			document.all.noTipClose.Click(); 
		} 
		else{ 
			window.opener =null; 
			window.open("","_self");
			window.close(); 
		} 
	} 
	else{ 
		window.close() 
	}
}

window.open('/javaskweb/login.jsp','','');
//window.open('index.php','','');
//window.location.href="logininfo.php";
CloseWindowNoAsk();
</script>
</head>

<body>
</body>
</html>