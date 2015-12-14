<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
HttpSession sessions = request.getSession();
String userMenu = "";
String username = "";
if(sessions.getAttribute("username")==null){
	%>
	<script language=javascript>
	alert('登录超时，请重新登录');
	parent.location.href='/javaskweb/loginTy.do?op=toLoginOut';
	</script>
	<%
	}
	else{
	userMenu =(String)sessions.getAttribute("userMenu");
	username =(String)sessions.getAttribute("username");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========LEFT部分==========-->
<div id="left">
  <div id="date">
    <SCRIPT  language=javascript>
<!--
function  Year_Month(){  
        var  now  =  new  Date();  
        var  yy  =  now.getYear();  
        var  mm  =  now.getMonth();  
	var  mmm=new  Array();
	mmm[0]="1月";
	mmm[1]="2月";
	mmm[2]="3月";
	mmm[3]="4月";
	mmm[4]="5月";
	mmm[5]="6月";
	mmm[6]="7月";
	mmm[7]="8月";
	mmm[8]="9月";
	mmm[9]="10月";
	mmm[10]="11月";
	mmm[11]="12月";
	mm=mmm[mm];
        return(mm  );  }
function  thisYear(){  
        var  now  =  new  Date();  
        var  yy  =  now.getYear();  
        return(yy  );  }
function  Date_of_Today(){  
        var  now  =  new  Date();  
        return(now.getDate()  );  }
function  CurentTime(){  
        var  now  =  new  Date();  
        var  hh  =  now.getHours();  
        var  mm  =  now.getMinutes();  
        var  ss  =  now.getTime()  %  60000;  
        ss  =  (ss  -  (ss  %  1000))  /  1000;  
        var  clock  =  hh+':';  
        if  (mm  <  10)  clock  +=  '0';  
        clock  +=  mm+':';  
        if  (ss  <  10)  clock  +=  '0';  
        clock  +=  ss;  
        return(clock);  }  
function  refreshCalendarClock(){  
document.all.calendarClock1.innerHTML  =  Year_Month();  
document.all.calendarClock2.innerHTML  =  Date_of_Today()+"日";  
document.all.calendarClock3.innerHTML  =thisYear()+"年";  
document.all.calendarClock4.innerHTML  =  CurentTime();  }
document.write('<font  id="calendarClock3"  >  </font>');
document.write('<font  id="calendarClock1"  >  </font>');
document.write('<font  id="calendarClock2"  >  </font>&nbsp;&nbsp;');
document.write('<font  id="calendarClock4"  >  </font>');
setInterval('refreshCalendarClock()',100);
//-->
</SCRIPT>
  </div>
  <div id="scroll">
    <ul id="menu">
      <li><a href="#">卡操作</a></li>
    </ul>
    <ul id="menuTwo">
<%
if(userMenu.indexOf("CARD,")>=0||username.equals("system")){
%>
      <li><a href="/javaskweb/ReadCardInfoServlet?method=import" target="mainFrame">读卡 </a></li>
<%
}
if(userMenu.indexOf("ZXGL,")>=0||username.equals("system")){
%>
      <!-- <li><a href="/jvaskweb/taxpayerServlet?method=zx" target="mainFrame">注销管理 </a></li> -->
<%
}
if(userMenu.indexOf("BGYWSL,")>=0||username.equals("system")){
%>
      <!--<li><a href="/javaskweb/bgServlet?method=import" target="mainFrame">变更业务受理 </a></li>-->
<%
}
%>
    </ul>
  </div>
</div>
</body>
</html>
<script language="javascript">
    document.getElementById('scroll').style.height=document.documentElement.clientHeight-36;
</script>
