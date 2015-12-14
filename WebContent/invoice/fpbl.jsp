<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/auto_complete.js"></script>
<link href="images/css.css" rel="stylesheet" type="text/css" />
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
		var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
		if (nsrwjbm == '') {
			alert('请输入纳税人微机编码！');
		} else {
			//alert(111);

			var num = nsrwjbm.length;

			for ( var i = num; i < 16; i++) {
				nsrwjbm = "0" + nsrwjbm;
			}

			window.location.href = '/javaskweb/fpService.do?op=bllist&nsrwjbm='
					+ nsrwjbm;
		}
	}

	function kd() {
		if (event.keyCode == 13) {
			document.all("btn").focus();
		}
	}
</script>
</head>

<body>
	<!--==========right部分==========-->
	<div id="right">
		<div id="tool">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="tableListTitle" style="padding-top: 14px;">
				<tr>
					<td>&nbsp;&nbsp;发票管理 &gt;&gt;&nbsp;&nbsp;<a href="#"
						class="nav">发票补领</a>
					</td>
				</tr>
			</table>
		</div>
		<div id="main">
			<br /> <br />
			 
				<table width="50%" border="0" align="center" cellpadding="0"
					cellspacing="0" id="userLogin1">
					<tr>
						<th width="5%">发票补领</th>
					</tr>
					<tr>
						<td height="152" style="text-align: center"><br />
							&nbsp;&nbsp;<strong>请输入纳税户微机编码：</strong> <input name="nsrwjbm"
							id="nsrwjbm" type="text"  maxlength="16" style="width: 270px;" />&nbsp;&nbsp;<input
							type="button" name="btn" value="下一步" onclick="add();" /></td>
					</tr>
					<tr>
						<td>
							<div id="auto"></div></td>
					</tr>
				</table>
			 
			<iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
		</div>
	</div>
</body>
</html>