<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	HttpSession sessions = request.getSession();
	String userMenu = "";
	String username = "";
	if (sessions.getAttribute("username") == null) {
%>
<script language=javascript>
alert('登录超时，请重新登录');
parent.location.href='/javaskweb/loginTy.do?op=toLoginOut';
</script>
<%
	}
	userMenu = (String) sessions.getAttribute("userMenu");
	username = (String) sessions.getAttribute("username");
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
</SCRIPT>
  </div>
  <div id="scroll">
    <ul id="menu1">
      <li><a href="#">查询统计</a></li>
    </ul>
    <!--
    <ul class="menuTwo1">
   	  <li><a href="#" onClick="switchMenu('ul1','im1');">清册查询 <img id="im1" src="images/ico_left_down.gif" width="10" height="10" border="0" /></a></li>
    </ul>
    -->
    <ul id="ul1" class="menuTwo1">
    
<%
	if (userMenu.indexOf("NSHXXCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toNsrxxImport" target="mainFrame">纳税户信息查询 </a></li>
<%
	}
	if (userMenu.indexOf("SKZZCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toJqxxImport" target="mainFrame">税控装置查询 </a></li>
<%
	}
	if (userMenu.indexOf("FPLGCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toFplgImport" target="mainFrame">发票领购查询 </a></li>
<%
	}
	if (userMenu.indexOf("FPMXCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toFpkjImport" target="mainFrame">发票明细查询 </a></li>
<%
	}
	if (userMenu.indexOf("SBSJCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toSbsjImport" target="mainFrame">申报汇总数据查询</a></li>
<%
	}
	if (userMenu.indexOf("RJYCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toRjyImport" target="mainFrame">日交易查询</a></li>
<%
	}
	if (userMenu.indexOf("YHKDK,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toYhkImport" target="mainFrame">用户卡信息读取</a></li>
<%
	}
	if (userMenu.indexOf("SKKDK,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toSkkImport" target="mainFrame">税控卡信息读取</a></li>
<%
	}
	if (userMenu.indexOf("WSBCX,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj.do?op=toBywsbImport" target="mainFrame">本月未申报户查询</a></li>

<%
	}
	if (userMenu.indexOf("SKKFZ,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj/skk.fz.jsp" target="mainFrame">税控卡复制</a></li>
<%
	}
	if (userMenu.indexOf("SKKJS,") >= 0 || "system".equals(username)) {
%>
      <li><a href="/javaskweb/cxtj/skk.js.jsp" target="mainFrame">税控卡使用口令解锁</a></li>
<%
	}
%>
  <li><a href="/QueryService/listInvoiceParticular.do?sn=" target="mainFrame">历史数据查询</a></li>
   <li><a href="/javaskweb/cxtj.do?op=toDjhzImport" target="mainFrame">发票单卷汇总信息查询 </a></li>
   <li><a href="/javaskweb/fileoutsuccess.jsp" target="mainFrame">上月明细集体导出</a></li>
   <li><a href="/javaskweb/fileouthzsuccess.jsp" target="mainFrame">上月明细汇总集体导出</a></li>
    </ul>
  </div>
</div>
<div id='locked_left' style='position:absolute;width:300px;height:800px;left:0;top:0;z-index:50;filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=3,finishOpacity=75);background:#000; display:none;' ></div>
</body>
</html>
<script language="javascript">
    document.getElementById('scroll').style.height=document.documentElement.clientHeight-36;
</script>
