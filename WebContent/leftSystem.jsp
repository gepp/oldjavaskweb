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
parent.location.href='/javaskweb/loginTy.do?op=toLoginOuts';
</script>
<%
}
userMenu =(String)sessions.getAttribute("userMenu");
username =(String)sessions.getAttribute("username");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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

var old_obj_id = "ul1";
var old_im_id = "im1";
function switchMenu(obj_id,im_id){
		obj = document.getElementById(obj_id);
		im_obj = document.getElementById(im_id);
		old_obj = document.getElementById(old_obj_id);
		old_im_obj = document.getElementById(old_im_id);
	if(old_obj_id != obj_id){
		old_obj_id = obj_id;
		old_im_id = im_id;
		obj.style.display==""?obj.style.display="none":obj.style.display="";
		im_obj.src.search("ico_left_down.gif")==-1?im_obj.src="images/ico_left_down.gif":im_obj.src="images/ico_left_left.gif";
		old_obj.style.display==""?old_obj.style.display="none":old_obj.style.display="";
		old_im_obj.src.search("ico_left_down.gif")==-1?old_im_obj.src="images/ico_left_down.gif":old_im_obj.src="images/ico_left_left.gif";
	}
}

function print(){
　　document.getElementById('WebBrowser').execwb(8,1);
}
</SCRIPT>
  </div>
  <div id="scroll">
    <ul id="menu">
      <li><a href="#">系统管理</a></li>
    </ul>
    <ul id="menuTwo">
<%
if(userMenu.indexOf("FPPZGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toFpList" target="mainFrame">发票票种管理 </a></li>
<%
}
if(userMenu.indexOf("PMSZ,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toPmList" target="mainFrame">品目设置 </a></li>
<%
}
if(userMenu.indexOf("JQXHGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toJqxhList" target="mainFrame">机器型号管理 </a></li>
<%
}
if(userMenu.indexOf("ZCLXGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toZclxList" target="mainFrame">注册类型管理 </a></li>
<%
}
if(userMenu.indexOf("HYJMXGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toHyList" target="mainFrame">行业及明细管理 </a></li>
<%
}
if(userMenu.indexOf("SWJGGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toSwjgList" target="mainFrame">税务机关管理 </a></li>
<%
}
if(userMenu.indexOf("YHGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toUserList" target="mainFrame">用户管理 </a></li>
<%
}
if(userMenu.indexOf("JSQXGL,")>=0||"system".equals(username)){
%>
      <li><a href="/javaskweb/system.do?op=toRoleList" target="mainFrame">角色权限管理 </a></li>
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
