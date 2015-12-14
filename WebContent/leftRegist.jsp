<%@ page contentType="text/html; charset=UTF-8" %>

<%@page import="java.util.*"%>

<%
	HttpSession sessions = request.getSession();
	String userMenu = "";
	String username = "";
	if (sessions.getAttribute("username") == null) {
%>
<script language=javascript type="text/javascript">
	alert('登录超时，请重新登录');
	parent.location.href = '/javaskweb/loginTy.do?op=toLoginOut';
</script>
<%
	} else {
		userMenu = (String) sessions.getAttribute("userMenu");
		username = (String) sessions.getAttribute("username");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!--==========LEFT部分==========-->
	<div id="left">
		<div id="date">
			<SCRIPT language=javascript type="text/javascript">
				function Year_Month() {
					var now = new Date();
					var yy = now.getYear();
					var mm = now.getMonth();
					var mmm = new Array();
					mmm[0] = "1月";
					mmm[1] = "2月";
					mmm[2] = "3月";
					mmm[3] = "4月";
					mmm[4] = "5月";
					mmm[5] = "6月";
					mmm[6] = "7月";
					mmm[7] = "8月";
					mmm[8] = "9月";
					mmm[9] = "10月";
					mmm[10] = "11月";
					mmm[11] = "12月";
					mm = mmm[mm];
					return (mm);
				}
				function thisYear() {
					var now = new Date();
					var yy = now.getYear();
					return (yy);
				}
				function Date_of_Today() {
					var now = new Date();
					return (now.getDate());
				}
				function CurentTime() {
					var now = new Date();
					var hh = now.getHours();
					var mm = now.getMinutes();
					var ss = now.getTime() % 60000;
					ss = (ss - (ss % 1000)) / 1000;
					var clock = hh + ':';
					if (mm < 10)
						clock += '0';
					clock += mm + ':';
					if (ss < 10)
						clock += '0';
					clock += ss;
					return (clock);
				}
				function refreshCalendarClock() {
					document.all.calendarClock1.innerHTML = Year_Month();
					document.all.calendarClock2.innerHTML = Date_of_Today()
							+ "日";
					document.all.calendarClock3.innerHTML = thisYear() + "年";
					document.all.calendarClock4.innerHTML = CurentTime();
				}
				document.write('<font  id="calendarClock3"  >  </font>');
				document.write('<font  id="calendarClock1"  >  </font>');
				document
						.write('<font  id="calendarClock2"  >  </font>&nbsp;&nbsp;');
				document.write('<font  id="calendarClock4"  >  </font>');
				setInterval('refreshCalendarClock()', 100);
			
</SCRIPT>
		</div>
		<div id="scroll">
			<ul id="menu">
				<li><a href="#">注册登记管理</a></li>
			</ul>
			<ul id="menuTwo">

				<%
					if (userMenu.indexOf("NSHXXGL,") >= 0) {
				%>
				<li><a href="/javaskweb/taxpayer.do?op=toImport"
					target="mainFrame">纳税户信息管理 </a></li>
                <li><a href="/javaskweb/nsrmcgxServlet?method=import" target="mainFrame">纳税人名称更新 </a></li>
				<%
					}
					
				%>
				<li><a href="/javaskweb/taxpayer.do?op=toImport"
					target="mainFrame">纳税户信息管理 </a></li>
						<li><a href="/javaskweb/jqgl/tyWlkp.jsp"
					target="mainFrame">停业</a></li>
				<li><a href="/javaskweb/jqgl/fyWlkp.jsp"
					target="mainFrame">复业</a></li>
					<li><a href="/javaskweb/jqgl/jqsdWlkp.jsp"
					target="mainFrame">锁定</a></li>
						<li><a href="/javaskweb/jqgl/jqjsWlkp.jsp"
					target="mainFrame">解锁</a></li>
						<li><a href="/javaskweb/jqgl/jqzxWlkp.jsp"
					target="mainFrame">注销</a></li>
				<%
				if (username.equals("system")) {
				%>
				<li><a href="/javaskweb/sjyzServlet?method=import"
					target="mainFrame">数据移植</a></li>
				 
				<li><a href="/javaskweb/taxpayer.do?op=deleteRepeatSzsm"
					target="mainFrame">删除多余纳税人税目</a></li>	
				<%
					}
				%>

			</ul>
		</div>
	</div>
</body>
</html>
<script language="javascript">
	document.getElementById('scroll').style.height = document.documentElement.clientHeight - 36;
</script>
