<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>

<%
ArrayList alDjkjmx = new ArrayList();
alDjkjmx = (ArrayList)request.getAttribute("alDjkjmx");
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
// DecimalFormat dg = new DecimalFormat("0.00");
int fpqsh = Integer.parseInt((String)request.getAttribute("fpqsh"));
int fpjzh = Integer.parseInt((String)request.getAttribute("fpjzh"));
int qsh = Integer.parseInt((String)request.getAttribute("qsh"));
int jzh = Integer.parseInt((String)request.getAttribute("jzh"));
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
int pageSize = (Integer)request.getAttribute("pageSize");
int pageNo = (Integer)request.getAttribute("pageNo");
int maxPage = (Integer)request.getAttribute("maxPage");
int maxCount = (Integer)request.getAttribute("maxCount");

String up_show = "";
String down_show = "";

if(pageNo==maxPage){
	if(maxPage==1){
		up_show 	= "disabled";
		down_show 	= "disabled";
	}else{
		down_show 	= "disabled";
	}
}
if(pageNo<maxPage&&pageNo==1){
		up_show 	= "disabled";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">

function firstp(){
	var pageNo 			= 1;
	goto1(pageNo);
}
function goupp(){
	var pageNo 			= document.all('pageNo').value*1-1;
	goto1(pageNo);
}
function downp(){
	var pageNo 			= document.all('pageNo').value*1+1;
	goto1(pageNo);
}
function caudap(){
	var pageNo 			= document.all('maxPage').value*1;
	goto1(pageNo);
}
function check_page(){
	var pageNo 		= document.all('pageNo').value;
	var maxPage 	= document.all('maxPage').value;
	if(pageNo>maxPage){
		document.all('pageNo').value = maxPage;
	}
	else if(pageNo<1){
		document.all('pageNo').value = 1;
	}
}
function go(){
	check_page();
	var pageNo 		= document.all('pageNo').value;
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var fpdm 	= document.all('fpdm').value;
	var fpqsh 	= document.all('fpqsh').value;
	var fpjzh 	= document.all('fpjzh').value;
	var qsh 	= document.all('qsh').value;
	var jzh 	= document.all('jzh').value;	
	var startdate = document.all('startdate').value;
	var enddate = document.all('enddate').value;
	window.location.href='/javaskweb/cxtj.do?op=toDjkjmxList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&fpdm='+fpdm+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&qsh='+qsh+'&jzh='+jzh+'&startdate='+startdate+'&enddate='+enddate;
}
function goto1(pageNo){
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var fpdm 	= document.all('fpdm').value;
	var fpqsh 	= document.all('fpqsh').value;
	var fpjzh 	= document.all('fpjzh').value;
	var qsh 	= document.all('qsh').value;
	var jzh 	= document.all('jzh').value;
	var startdate = document.all('startdate').value;
	var enddate = document.all('enddate').value;
	window.location.href='/javaskweb/cxtj.do?op=toDjkjmxList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&fpdm='+fpdm+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&qsh='+qsh+'&jzh='+jzh+'&startdate='+startdate+'&enddate='+enddate;
}

function dc(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	document.all('iframe').src = '/javaskweb/cxtj/djkjmxexcel.jsp?nsrwjbm='+nsrwjbm+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm;
}

function back(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var fpdm 	= document.all('fpdm').value;
	var fpqsh 	= document.all('qsh').value;
	var fpjzh 	= document.all('jzh').value;
	var startdate = document.all('startdate').value;
	var enddate = document.all('enddate').value;
	window.location.href = '/javaskweb/cxtj.do?op=toDjkjhzList&nsrwjbm='+nsrwjbm+'&fpdm='+fpdm+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&startdate='+startdate+'&enddate='+enddate;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right" style="z-index: -1">
  <div id="main" style="z-index: -1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">单卷开具明细信息</a></div></td>
        <td align="right"><input type="button" name="dcexcel" value="导出EXCEL" onclick="dc();">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" name="dcexcel" value="返回" onclick="back();">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="12%">纳税人微机编码</th>
        <th width="12%">纳税人名称</th>
       <th width="12%">机器编号</th>
        <th width="12%">发票代码</th>
        <th width="10%">发票号码</th>
        <th width="11%">开票日期</th>
        <th width="11%">开票类型</th>
        <th width="10%">开票金额</th>
        <th width="10%">原发票号码</th>
      </tr>
    <%
      	for(int i = 0;i < alDjkjmx.size();i ++){
     		Fpkj fpkj = (Fpkj)alDjkjmx.get(i); 
       %> 
	  <tr>
        <td align="center"><%=nsrxx.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=nsrxx.getNsrmc() %>&nbsp;</td>
        <td align="center"><%=fpkj.getJqbh() %>&nbsp;</td>
        <td align="center"><%=fpkj.getFpdm() %>&nbsp;</td>
        <td align="center"><%=fpkj.getFphm() %>&nbsp;</td>
        <td align="center"><%=fpkj.getKprq().substring(0,10) %>&nbsp;</td>
        <td align="center"><%=fpkj.getKplx()==1?"正常票":fpkj.getKplx()==2?"退票":"废票" %>&nbsp;</td>
        <td align="center"><font <%=fpkj.getKplx()==2?"color='red'":"" %>><%=fpkj.getKplx()==2?"-":"" %><%=fpkj.getHjzje() %>&nbsp;</td>
        <td align="center"><%=fpkj.getYfphm()==0?"":fpkj.getYfphm()%>&nbsp;</td>
      </tr>
      <%}%> 
    </table>
   <table id="tablefoot" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr> 
        <td align="left">&nbsp;&nbsp;
          <input type="button" name="firstp_btn" value="首 页" onClick="firstp()" <%=up_show%>>
          <input type="button" name="goupp_btn" value="上 页" onClick="goupp()" <%=up_show%>>
          <input type="button" name="downp_btn" value="下 页" onClick="downp()" <%=down_show%>>
          <input type="button" name="caudap_btn" value="尾 页" onClick="caudap()" <%=down_show%>>
        </td>
        <td align="right">每页
          <input type="text" value="${pageSize}" name="pageSize" style="width:30px;">
          条记录 跳转到第
          <input type="text" value="${pageNo}" name="pageNo" style="width:30px;">
          页 
          <input type="button" name="go_btn" value="GO" onClick="go()"  />
          第${pageNo}页/共${maxPage}页 共${maxCount}条记录&nbsp;&nbsp;&nbsp;&nbsp; 
          <input type="hidden"  name="maxPage" value="${maxPage}">
	 	  <input type="hidden" name="maxCount" value="${maxCount}" />
	 	  <input type="hidden"  name="fpjzh" value="${fpjzh}">
          <input type="hidden"  name="fpqsh" value="${fpqsh}">
          <input type="hidden"  name="qsh" value="${qsh}">
          <input type="hidden"  name="jzh" value="${jzh}">
          <input type="hidden"  name="fpdm" value="${fpdm}">
          <input type="hidden" name="startdate" value="<%=startdate%>" />
	 	  <input type="hidden" name="enddate" value="<%=enddate%>" />
	 	  <input type="hidden" name="nsrwjbm" value="<%=nsrxx.getNsrwjbm()%>" />
        </td>
      </tr>
    </table> 
    <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>
