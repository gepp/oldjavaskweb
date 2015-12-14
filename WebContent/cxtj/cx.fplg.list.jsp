<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="jsdt.tools.*"%>
<%
ArrayList alFplg = (ArrayList)request.getAttribute("alFplg");
int pageSize = Integer.parseInt((String)request.getAttribute("pageSize"));
int pageNo = Integer.parseInt((String)request.getAttribute("pageNo"));
int maxPage = Integer.parseInt((String)request.getAttribute("maxPage"));
int maxCount = Integer.parseInt((String)request.getAttribute("maxCount"));
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String swjgbm = (String)request.getAttribute("swjgbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");

DecimalFormat dg = new DecimalFormat("0.00");
String mouth=startdate.substring(5,7);
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
}
function goto(){
	check_page();
	var pageNo 		= document.all('pageNo').value;
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var swjgbm 		= document.all('swjgbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	
	window.location.href='/javaskweb/cxtj.do?op=toFplgList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate;
}
function goto1(pageNo){
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var swjgbm 		= document.all('swjgbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	window.location.href='/javaskweb/cxtj.do?op=toFplgList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票领购信息列表</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
          <th width="12%">纳税人微机编码</th>
          <th width="12%">纳税人名称</th>
          <th width="8%">领票日期</th>
          <th width="12%">发票名称</th>
          <th width="10%">机器编号</th>
          <th>发票号码起止</th>
          <th width="14%"><%=mouth %>月发票领用张数</th>
          <th width="14%"><%=mouth %>月已使用发票张数</th>
        </tr>
<%
if(alFplg!=null&&!alFplg.isEmpty()){
	Iterator it_fpxx = alFplg.iterator();
	while(it_fpxx.hasNext()){
		Fpjmx fpjmx = (Fpjmx)it_fpxx.next();
		int xfzt = fpjmx.getFpxfzt();
		String xfztmc = "";
		if((fpjmx.getJqbh()==null||"".equals(fpjmx.getJqbh()))&&xfzt==0){
			xfztmc = "未下发";
		}
		else if((fpjmx.getJqbh()!=null&&!"".equals(fpjmx.getJqbh()))&&xfzt==0){
			xfztmc = "已下发，未使用";
		}
		else{
			xfztmc = "已使用";
		}
%>
        <tr>
          <td align="center"><%=fpjmx.getNsrwjbm() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getNsrmc() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getFplgrq() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getFpmc() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getJqbh() %>&nbsp;</td>
          <td align="center"><%=Util.fpIntToString(fpjmx.getFpqsh()) %> ———  <%=Util.fpIntToString(fpjmx.getFpjzh()) %>&nbsp;</td>
          <td align="center"><%=(fpjmx.getFpzs()) %></td>
          <td align="center"><%=fpjmx.getHasUsed() %></td>
        </tr>
        <%
	}
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
          <input type="button" name="go_btn" value="GO" onClick="goto1()" />
          第<%=pageNo%>页/共<%=maxPage%>页 共<%=maxCount%> 条记录&nbsp;&nbsp;&nbsp;&nbsp; 
          <input type="hidden"  name="maxPage" value="<%=maxPage%>">
	 	  <input type="hidden" name="maxCount" value="<%=maxCount%>" />
	 	  <input type="hidden" name="nsrwjbm" value="<%=nsrwjbm%>" />
	 	  <input type="hidden" name="swjgbm" value="<%=swjgbm%>" />
	 	  <input type="hidden" name="startdate" id="startdate" value="<%=startdate %>" />
          <input type="hidden" name="enddate" id="enddate" value="<%=enddate %>" />
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
