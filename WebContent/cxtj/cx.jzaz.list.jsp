<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
ArrayList alJzaz = (ArrayList)request.getAttribute("alJzaz");
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String swjgbm = (String)request.getAttribute("swjgbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
String xmzt = (String)request.getAttribute("xmzt");
String htxz = (String)request.getAttribute("htxz");
String xmlx = (String)request.getAttribute("xmlx");
String xmmc = (String)request.getAttribute("xmmc");
DecimalFormat dg = new DecimalFormat("0.00");

double hjhtje = Double.parseDouble((String)request.getAttribute("hjhtje"));
double hjykpje = Double.parseDouble((String)request.getAttribute("hjykpje"));
double hjwkpje = Double.parseDouble((String)request.getAttribute("hjwkpje"));
double hjddkje = Double.parseDouble((String)request.getAttribute("hjddkje"));

int pageSize = Integer.parseInt((String)request.getAttribute("pageSize"));
int pageNo = Integer.parseInt((String)request.getAttribute("pageNo"));
int maxPage = Integer.parseInt((String)request.getAttribute("maxPage"));
int maxCount = Integer.parseInt((String)request.getAttribute("maxCount"));

String up_show = "";
String down_show = "";

if(pageNo==maxPage){
	if(pageNo==1){
		up_show 	= "disabled";
		down_show 	= "disabled";
	}
	else{
		down_show 	= "disabled";
	}
}
else{
	if(pageNo==1){
		up_show 	= "disabled";
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function search_kp(xmid){
	var nsrwjbm 	= document.getElementById('nsrwjbm').value;
	var swjgbm 		= document.getElementById('swjgbm').value;
	var startdate 	= document.getElementById('startdate').value;
	var enddate 	= document.getElementById('enddate').value;
	var xmzt 		= document.getElementById('xmzt').value;
	var htxz 		= document.getElementById('htxz').value;
	var xmlx 		= document.getElementById('xmlx').value;
	var xmmc 		= document.getElementById('xmmc').value;
	
	window.location.href='/javaskweb/cxJzazServlet?method=zfplist&xmid='+xmid+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc))+'&jumpflag=zb';
}

function search_fb(xmid){
	var nsrwjbm 	= document.getElementById('nsrwjbm').value;
	var swjgbm 		= document.getElementById('swjgbm').value;
	var startdate 	= document.getElementById('startdate').value;
	var enddate 	= document.getElementById('enddate').value;
	var xmzt 		= document.getElementById('xmzt').value;
	var htxz 		= document.getElementById('htxz').value;
	var xmlx 		= document.getElementById('xmlx').value;
	var xmmc 		= document.getElementById('xmmc').value;
	
	window.location.href='/javaskweb/cxJzazServlet?method=fblist&xmid='+xmid+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
}
/*function init(){
	//alert(window.screen.height);
	var winHeight = parseInt(window.screen.height);
	var height = 0;
	if(winHeight==720){
		height = parseInt(window.screen.height)-170-28-40-116-80;
	}
	else if(winHeight==900){
		height = parseInt(window.screen.height)-170-28-40-116-90;
	}
	else if(winHeight==768){
		height = parseInt(window.screen.height)-170-28-40-116-120;
	}
	else if(winHeight==800){
		height = parseInt(window.screen.height)-170-28-40-116-100;
	}
	else {
		height = parseInt(window.screen.height)-170-28-40-116-120;
	}
	//alert(height);
	document.getElementById('xs').style.height = height;
	//alert(document.getElementById('xs').style.height);
}*/


function firstp(){
	var pageNo 			= 1;
	goto1(pageNo)
}
function goupp(){
	var pageNo 			= document.all('pageNo').value*1-1;
	goto1(pageNo)
}
function downp(){
	var pageNo 			= document.all('pageNo').value*1+1;
	goto1(pageNo)
}
function caudap(){
	var pageNo 			= document.all('maxPage').value*1;
	goto1(pageNo)
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
	else{
		document.all('pageNo').value = 1;
	}
	//document.all('pageNo').value = 1;
}
function goto(){
	check_page();
	var pageNo 		= document.all('pageNo').value;
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.getElementById('nsrwjbm').value;
	var swjgbm 		= document.getElementById('swjgbm').value;
	var startdate 	= document.getElementById('startdate').value;
	var enddate 	= document.getElementById('enddate').value;
	var xmzt 		= document.getElementById('xmzt').value;
	var htxz 		= document.getElementById('htxz').value;
	var xmlx 		= document.getElementById('xmlx').value;
	var xmmc 		= document.getElementById('xmmc').value;
	
	window.location.href='/javaskweb/cxJzazServlet?method=list&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
}
function goto1(pageNo){
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.getElementById('nsrwjbm').value;
	var swjgbm 		= document.getElementById('swjgbm').value;
	var startdate 	= document.getElementById('startdate').value;
	var enddate 	= document.getElementById('enddate').value;
	var xmzt 		= document.getElementById('xmzt').value;
	var htxz 		= document.getElementById('htxz').value;
	var xmlx 		= document.getElementById('xmlx').value;
	var xmmc 		= document.getElementById('xmmc').value;
	window.location.href='/javaskweb/cxJzazServlet?method=list&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">建筑安装项目信息列表</a></div></td>
        <td align="right">
          <input type="hidden" name="nsrwjbm" id="nsrwjbm" value="<%=nsrwjbm %>" />
          <input type="hidden" name="swjgbm" id="swjgbm" value="<%=swjgbm %>" />
          <input type="hidden" name="startdate" id="startdate" value="<%=startdate %>" />
          <input type="hidden" name="enddate" id="enddate" value="<%=enddate %>" />
          <input type="hidden" name="xmzt" id="xmzt" value="<%=xmzt %>" />
          <input type="hidden" name="htxz" id="htxz" value="<%=htxz %>" />
          <input type="hidden" name="xmlx" id="xmlx" value="<%=xmlx %>" />
          <input type="hidden" name="xmmc" id="xmmc" value="<%=xmmc %>" />
        &nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="6%">合同性质</th>
		<th width="9%">乙方编码</th>
        <th width="10%">乙方名称</th>
        <th width="14%">项目名称</th>
        <th width="9%">甲方编码</th>
        <th width="10%">甲方名称</th>
        <th width="8%">合同金额</th>
        <th width="8%">已开票金额</th>
        <th width="8%">未开票金额</th>
        <th width="8%">待抵扣金额</th>
        <th width="12%">操作</th>
      </tr>
<%

if(alJzaz!=null&&!alJzaz.isEmpty()){
	Iterator it = alJzaz.iterator();
	double allhtzje = 0;
	double allykpzje = 0;
	double allsykpzje = 0;
	double allddkje = 0;
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		int fbflag = Integer.parseInt((String)hm.get("fbflag"));
		int kpflag = Integer.parseInt((String)hm.get("kpflag"));
		
		allhtzje = allhtzje+Double.parseDouble((String)hm.get("htzje"));
		allykpzje = allykpzje+Double.parseDouble((String)hm.get("ykpzje"));
		allsykpzje = allsykpzje+Double.parseDouble((String)hm.get("sykpzje"));
		allddkje = allddkje+Double.parseDouble((String)hm.get("ddkje"));
%>
	  <tr>
        <td align="center"><%=(String)hm.get("htxz") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("yfwjbm") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("yfmc") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("xmmc") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("jfwjbm") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("jfmc") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("htzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("ykpzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("sykpzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("ddkje") %>&nbsp;</td>
        <td align="center">
<%
if(kpflag==1){
%>
	<input type="button" name="btn" value="开票" onclick="search_kp(<%=Integer.parseInt((String)hm.get("id")) %>);" />
<%
}
%>
<%
if(fbflag==1){
%>
	<input type="button" name="btn" value="分包" onclick="search_fb(<%=Integer.parseInt((String)hm.get("id")) %>);"  />
<%
}
%>
        
		&nbsp;
        </td>
      </tr>
<%
	}
%>
	  <tr>
        <td align="center" colspan="2"><font color="red"><strong>分页合计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(allhtzje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(allykpzje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(allsykpzje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(allddkje) %></strong></font>&nbsp;</td>
        <td align="center">        
		&nbsp;
        </td>
      </tr>
      <tr>
        <td align="center" colspan="2"><font color="red"><strong>总计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(hjhtje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(hjykpje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(hjwkpje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(hjddkje) %></strong></font>&nbsp;</td>
        <td align="center">        
		&nbsp;
        </td>
      </tr>
<%
}
%>
      
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
          <input type="text" value="<%=pageSize%>" name="pageSize" style="width:30px;">
          条记录 跳转到第
          <input type="text" value="<%=pageNo%>" name="pageNo" style="width:30px;">
          页 
          <input type="button" name="go_btn" value="GO" onClick="goto()" />
          第<%=pageNo%>页/共<%=maxPage%>页 共<%=maxCount%> 条记录&nbsp;&nbsp;&nbsp;&nbsp; 
          <input type="hidden"  name="maxPage" value="<%=maxPage%>">
	 	  <input type="hidden" name="maxCount" value="<%=maxCount%>" />
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
