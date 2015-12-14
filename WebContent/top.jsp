<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function jump(url_left,url_right){
	parent.document.all('leftFrame').src = url_left;
	parent.document.all('mainFrame').src = url_right;
}
</script>
</head>

<body>
<!--==========TOP部分==========-->
<div id="top">
<div id="banner">
<ul>
<li><img src="images/logo.jpg" width="487" height="42" /></li>
</ul>
</div>
<div id="nav">
<ul id="nav_left">
<li><a href="right.jsp" target="mainFrame">首页</a></li>
<li><a href="JAVASCRIPT:" onclick="jump('leftRegist.jsp','index_zcdj.jsp');">税控基础设置</a></li>
<li><a href="JAVASCRIPT:" onclick="jump('leftInvoice.jsp','index_fpgl.jsp');">发票管理</a></li>
<li><a href="JAVASCRIPT:" onclick="jump('leftReceiver.jsp','index_sbsj.jsp');">申报数据</a></li>
<!-- <li><a href="JAVASCRIPT:" onclick="jump('leftBdc.jsp','index_sbsj.jsp');">不动产项目信息管理</a></li>
<li><a href="JAVASCRIPT:" onclick="jump('leftJzaz.jsp','index_sbsj.jsp');">建安项目信息管理</a></li> -->
<li><a href="JAVASCRIPT:" onclick="jump('leftSystem.jsp','index_xtgl.jsp');">系统管理</a></li>
<li><a href="JAVASCRIPT:" onclick="jump('leftSearch.jsp','index_cxtj.jsp');">查询统计</a></li>
<!-- <li><a href="JAVASCRIPT:" onclick="jump('leftCard.jsp','index_card.jsp');">卡操作</a></li> -->
</ul>

<ul id="nav_right">
  <img src="images/exit.gif" width="52" height="20" style="cursor:hand;" onclick="parent.location.href='/javaskweb/loginTy.do?op=toLoginOut'"/>
</ul>
</div>
</div>
<div id='locked_top' style='position:absolute;width:1440px;height:300px;left:0;top:0;z-index:50;filter:progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=3,finishOpacity=75);background:#000; display:none;' ></div>
</body>
</html>
