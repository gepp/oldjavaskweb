<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
ArrayList alKpxx = (ArrayList)request.getAttribute("alKpxx");
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String swjgbm = (String)request.getAttribute("swjgbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
String xmzt = (String)request.getAttribute("xmzt");
String htxz = (String)request.getAttribute("htxz");
String xmlx = (String)request.getAttribute("xmlx");
String xmmc = (String)request.getAttribute("xmmc");
String jumpflag = (String)request.getAttribute("jumpflag");
String parentid = (String)request.getAttribute("parentid");
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
	
	window.location.href='/javaskweb/cxJzazServlet?method=zfplist&xmid='+xmid+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx;
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
	var jumpflag 		= document.getElementById('jumpflag').value;
	var parentid 		= document.getElementById('parentid').value;
	
	if(jumpflag=='zb'){
		window.location.href='/javaskweb/cxJzazServlet?method=list&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
	}
	else{
		window.location.href='/javaskweb/cxJzazServlet?method=fblist&xmid='+parentid+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
	}
}

function seeFp(fpdm,fphm){
	window.open('/javaskweb/fpxsServlet?method=import&fpdm='+fpdm+'&fphm='+fphm,'','width=800,height=680,top=100,left=100');
}
</script>
</head>
<body onload="init();">
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">建筑安装项目开票信息列表</a></div></td>
        <td align="right">
          <input type="hidden" name="nsrwjbm" id="nsrwjbm" value="<%=nsrwjbm %>" />
          <input type="hidden" name="swjgbm" id="swjgbm" value="<%=swjgbm %>" />
          <input type="hidden" name="startdate" id="startdate" value="<%=startdate %>" />
          <input type="hidden" name="enddate" id="enddate" value="<%=enddate %>" />
          <input type="hidden" name="xmzt" id="xmzt" value="<%=xmzt %>" />
          <input type="hidden" name="htxz" id="htxz" value="<%=htxz %>" />
          <input type="hidden" name="xmlx" id="xmlx" value="<%=xmlx %>" />
          <input type="hidden" name="xmmc" id="xmmc" value="<%=xmmc %>" />
          <input type="hidden" name="jumpflag" id="jumpflag" value="<%=jumpflag %>" />
          <input type="hidden" name="parentid" id="parentid" value="<%=parentid %>" />
          <input type="button" name="ret_btn" value=" 返 回 " onclick="fh();" />
        &nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="14%">发票代码</th>
		<th width="14%">发票号码</th>
        <th width="14%">开票类型</th>
        <th width="16%">开票金额</th>
        <th width="14%">开票日期</th>
        <th width="14%">上传日期</th>
        <th width="14%">操作</th>
      </tr>
<%

if(alKpxx!=null&&!alKpxx.isEmpty()){
	double allhjzje = 0;
	Iterator it = alKpxx.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		int kplx = Integer.parseInt((String)hm.get("kplx"));
		
		if(kplx==1){
			allhjzje += Double.parseDouble((String)hm.get("hjzje"));
		}
		else if(kplx==2){
			allhjzje -= Double.parseDouble((String)hm.get("hjzje"));
		}
		else{
			
		}
		
%>
	  <tr>
        <td align="center"><%=(String)hm.get("fpdm") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("fphm") %>&nbsp;</td>
        <td align="center"><%=kplx==1?"正常票":"退票" %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("hjzje") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("kprq") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("scrq") %>&nbsp;</td>
        <td align="center">
        <input type="button" name="btn" value="查看明细" onclick="seeFp('<%=(String)hm.get("fpdm") %>',<%=Integer.parseInt((String)hm.get("fphm")) %>);" />
        </td>
      </tr>
<%
	}
%>
	  <tr>
        <td align="center"><font color="red"><strong>总计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(allhjzje) %></strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
      </tr>
<%
}
%>
      
    </table>
  </div>
</div>
</body>
</html>