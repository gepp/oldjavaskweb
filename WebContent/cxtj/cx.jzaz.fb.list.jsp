<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
ArrayList alJzazFb = (ArrayList)request.getAttribute("alJzazFb");
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String swjgbm = (String)request.getAttribute("swjgbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
String xmzt = (String)request.getAttribute("xmzt");
String htxz = (String)request.getAttribute("htxz");
String xmlx = (String)request.getAttribute("xmlx");
String xmmc = (String)request.getAttribute("xmmc");
DecimalFormat dg = new DecimalFormat("0.00");
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
	
	window.location.href='/javaskweb/cxJzazServlet?method=zfplist&xmid='+xmid+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc))+'&jumpflag=fb';
}
function init(){
	//alert(window.screen.height);
	/*var winHeight = parseInt(window.screen.height);
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
	document.getElementById('xs').style.height = height;*/
	//alert(document.getElementById('xs').style.height);
}

function fh(){
	var nsrwjbm 	= document.getElementById('nsrwjbm').value;
	var swjgbm 		= document.getElementById('swjgbm').value;
	var startdate 	= document.getElementById('startdate').value;
	var enddate 	= document.getElementById('enddate').value;
	var xmzt 		= document.getElementById('xmzt').value;
	var htxz 		= document.getElementById('htxz').value;
	var xmlx 		= document.getElementById('xmlx').value;
	var xmmc 		= document.getElementById('xmmc').value;
	
	
	window.location.href='/javaskweb/cxJzazServlet?method=list&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
}
</script>
</head>
<body onload="init();">
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">建筑安装分包项目信息列表</a></div></td>
        <td align="right">
          <input type="hidden" name="nsrwjbm" id="nsrwjbm" value="<%=nsrwjbm %>" />
          <input type="hidden" name="swjgbm" id="swjgbm" value="<%=swjgbm %>" />
          <input type="hidden" name="startdate" id="startdate" value="<%=startdate %>" />
          <input type="hidden" name="enddate" id="enddate" value="<%=enddate %>" />
          <input type="hidden" name="xmzt" id="xmzt" value="<%=xmzt %>" />
          <input type="hidden" name="htxz" id="htxz" value="<%=htxz %>" />
          <input type="hidden" name="xmlx" id="xmlx" value="<%=xmlx %>" />
          <input type="hidden" name="xmmc" id="xmmc" value="<%=xmmc %>" />
          <input type="button" name="ret_btn" value=" 返 回 " onclick="fh();" />
        &nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="7%">合同性质</th>
		<th width="10%">乙方编码</th>
        <th width="15%">乙方名称</th>
        <th width="20%">项目名称</th>
        <th width="9%">合同金额</th>
        <th width="9%">已开票金额</th>
        <th width="9%">未开票金额</th>
        <th width="9%">待抵扣金额</th>
        <th width="12%">操作</th>
      </tr>
<%

if(alJzazFb!=null&&!alJzazFb.isEmpty()){
	Iterator it = alJzazFb.iterator();
	double allhtzje = 0;
	double allykpzje = 0;
	double allsykpzje = 0;
	double allddkje = 0;
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		
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
        <td align="center"><%=(String)hm.get("htzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("ykpzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("sykpzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("ddkje") %>&nbsp;</td>
        <td align="center">
        <input type="button" name="btn" value="开票" onclick="search_kp(<%=Integer.parseInt((String)hm.get("id")) %>);" />
        </td>
      </tr>
<%
	}
%>
	  <tr>
        <td align="center"><font color="red"><strong>总计：</strong></font>&nbsp;</td>
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
<%
}
%>
      
    </table>
  </div>
</div>
</body>
</html>
