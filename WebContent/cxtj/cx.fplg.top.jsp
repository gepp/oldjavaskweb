<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="jsdt.tools.*"%>
<%
	String startdate = Util.getPreviousMonthFirst();
	String enddate = Util.getPreviousMonthEnd();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script language="javascript" type="text/javascript"
	src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../images/CheckBrower.js"></script>
<script language="javascript">
	function trim(str) {
		for ( var i = 0; i < str.length && str.charAt(i) == " "; i++)
			;
		for ( var j = str.length; j > 0 && str.charAt(j - 1) == " "; j--)
			;
		if (i > j)
			return "";
		return str.substring(i, j);
	}
	function add() {
		var swjgbm = document.getElementById('swjgbm').value;
		var nsrwjbm = document.getElementById('nsrwjbm').value;
		var startdate = document.getElementById('startdate').value;
		var enddate = document.getElementById('enddate').value;
		parent.document.getElementById('fplgList').src = '/javaskweb/cxtj.do?op=toFplgList&nsrwjbm='
				+ nsrwjbm
				+ '&swjgbm='
				+ swjgbm
				+ '&startdate='
				+ startdate
				+ '&enddate=' + enddate;
		//parent.document.all('mainFrame').src = '/javaskweb/cxtj.do?op=toFplgList&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate;
	}
</script>
</head>
<body>
	<div id="right">
		<div id="main">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="userList">
				<tr>
					<td width="12%">纳税人微机编码：</td>
					<td width="30%"><input type="text" name="nsrwjbm" id="nsrwjbm"
						maxlength="16" style="width: 270px;">
					<div id="auto"></div>
					</td>
					<td width="12%">税务机关：</td>
					<td><input type="text" name="swjgmc" id="swjgmc" readonly
						style="width: 50%"
						onClick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=60,height=600,top=200,left=200,scrollbars=yes');">
						&nbsp;&nbsp; <input type="button" name="swjg_btn" value="选择税务机关"
						onClick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=600,top=200,left=200,scrollbars=yes');">
						<input type="hidden" id="swjgbm" name="swjgbm"></td>
				</tr>
				<tr>
					<td>领购时间：</td>
					<td colspan="3">开始时间：<input id="startdate" type="text"
						onfocus="WdatePicker({isShowWeek:true})"  value=<%=startdate %> />


						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						截止时间：&nbsp;&nbsp;&nbsp;<input id="enddate" type="text"
						onfocus="WdatePicker({isShowWeek:true})"  value=<%=enddate %> />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="button"
						name="add_btn" value=" 查 询 " onClick="add();">
						&nbsp;&nbsp; <input type="button" name="ret_btn" value=" 重 置 "
						onClick="window.location.reload();"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>