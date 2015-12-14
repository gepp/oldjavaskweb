<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
ArrayList alSbsj = (ArrayList)request.getAttribute("alSbsj");
DecimalFormat dg = new DecimalFormat("0.00");
String username=(String)session.getAttribute("username");
int pageSize = Integer.parseInt((String)request.getAttribute("pageSize"));
int pageNo = Integer.parseInt((String)request.getAttribute("pageNo"));
int maxPage = Integer.parseInt((String)request.getAttribute("maxPage"));
int maxCount = Integer.parseInt((String)request.getAttribute("maxCount"));

int hjzcpfs = 0;
int hjtpfs = 0;
int hjfpfs = 0;
double hjzcpzje = 0;
double hjtpzje = 0;
HashMap hmSb = (HashMap)request.getAttribute("hmSb");
if(hmSb!=null&&!hmSb.isEmpty()){
	hjzcpfs = Integer.parseInt((String)hmSb.get("hjzcpfs"));
	hjtpfs = Integer.parseInt((String)hmSb.get("hjtpfs"));
	hjfpfs = Integer.parseInt((String)hmSb.get("hjfpfs"));
	
	hjzcpzje = Double.parseDouble((String)hmSb.get("hjzcpzje"));
	hjtpzje = Double.parseDouble((String)hmSb.get("hjtpzje"));
}

String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
String swjgbm = (String)request.getAttribute("swjgbm");

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
function cxsb(sid){
	if(confirm("确定重新申报吗？"))
	window.location.href="/javaskweb/cxtj.do?op=deleteSbsj&sid="+sid;
	
}
function firstp(){
	var pageNo 			= 1;
	goto1(pageNo)
}
function goupp(){
	var pageNo 			= document.all('pageNo').value*1-1;
	goto1(pageNo)
}
function downp(){
	var pageNo= document.all('pageNo').value*1+1;
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
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var swjgbm 		= document.all('swjgbm').value;
	
	window.location.href='/javaskweb/cxtj.do?op=toSbsjList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&swjgbm='+swjgbm;
}
function goto1(pageNo){
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var swjgbm 		= document.all('swjgbm').value;
	//alert('/javaskweb/cxtj.do?op=toSbsjList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&swjgbm='+swjgbm);
	window.location.href='/javaskweb/cxtj.do?op=toSbsjList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&swjgbm='+swjgbm;
}

function dc(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var swjgbm 		= document.all('swjgbm').value;
	
	document.all('iframe').src = '/javaskweb/cxtj/sbsjexcel.jsp?nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&swjgbm='+swjgbm;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">申报信息列表</a></div></td>
        <td align="right">
        <input type="button" name="dcexcel" value="导出EXCEL" onclick="dc();">&nbsp;&nbsp;&nbsp;&nbsp;</td> 
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="9%">纳税人微机编码</th>
        <th width="12%">纳税人名称</th>
        <th width="10%">机器编号</th>
        <th width="8%">开始时间</th>
        <th width="8%">截止时间</th>
        <th width="7%">正常票份数</th>
        <th width="7%">退票份数</th>
        <th width="7%">废票份数</th>
      <th width="7%">应申报发票总数</th>
         <th width="8%">已申报明细条数</th>
        <th width="9%">正常票总金额</th>
        <th width="9%">退票总金额</th>
        <th>操作</th>
      </tr>
<%

if(alSbsj!=null&&!alSbsj.isEmpty()){
	Iterator it = alSbsj.iterator();
	int allzcpfs = 0;
	int alltpfs = 0;
	int allfpfs = 0;
	double allzcpzje = 0;
	double alltpzje = 0;
	while(it.hasNext()){
		Sbsj sbsj = (Sbsj)it.next();
		allzcpfs = allzcpfs+sbsj.getZcpfs();
		alltpfs = alltpfs+sbsj.getTpfs();
		allfpfs = allfpfs+sbsj.getFpfs();
		allzcpzje = allzcpzje+sbsj.getZcpzje();
		alltpzje = alltpzje+sbsj.getTpzje();
%>
	  <tr>
        <td align="center"><%=sbsj.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=sbsj.getNsrmc() %>&nbsp;</td>
        <td align="center"><%=sbsj.getJqbh() %>&nbsp;</td>
        <td align="center"><%=sbsj.getSskssj() %>&nbsp;</td>
        <td align="center"><%=sbsj.getSsjzsj() %>&nbsp;</td>
        <td align="center"><%=sbsj.getZcpfs() %>&nbsp;</td>
        <td align="center"><%=sbsj.getTpfs() %>&nbsp;</td>
        <td align="center"><%=sbsj.getFpfs() %>&nbsp;</td>
        <td aligh="center"><%=sbsj.getZcpfs()+sbsj.getTpfs()+sbsj.getFpfs() %></td>
           <td align="center"><%=sbsj.getYsbSum() %>&nbsp;</td>
        <td align="center"><%=dg.format(sbsj.getZcpzje()) %>&nbsp;</td>
        <td align="center"><%=dg.format(sbsj.getTpzje()) %>&nbsp;</td>
        <td align="center">
<% 
if(sbsj.getSbzbnum()>0){
if(username.equals("system")){
%>
     
        <input type="button" name="btn2" value=" 重新申报 " onclick="cxsb('<%=sbsj.getSid() %>')" />
        
        <%
}
        %>
		<input type="button" name="btn" value=" 查 看 " onclick="window.open('/javaskweb/cxtj.do?op=toSbsjDetail&sid=<%=sbsj.getSid() %>','sbsjmx','width=600,height=400,left=200,top=200');" />
<%
}
%>
        	&nbsp;</td>
      </tr>
<%
	}
%>
	  <tr>
        <td align="center"><font color="red"><strong>分页合计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=allzcpfs %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=alltpfs %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=allfpfs %></strong></font>&nbsp;</td>
        <td></td>
        <td></td>
        <td align="center"><font color="red"><strong><%=dg.format(allzcpzje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(alltpzje) %></strong></font>&nbsp;</td>
        <td align="center">
        	&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><font color="red"><strong>总计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=hjzcpfs %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=hjtpfs %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=hjfpfs %></strong></font>&nbsp;</td>
         <td></td>
         <td></td>
        <td align="center"><font color="red"><strong><%=dg.format(hjzcpzje) %></strong></font>&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(hjtpzje) %></strong></font>&nbsp;</td>
        <td align="center">
        	&nbsp;</td>
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
          <input type="button" name="go_btn" value="GO" onClick="goto1()" />
          第<%=pageNo%>页/共<%=maxPage%>页 共<%=maxCount%> 条记录&nbsp;&nbsp;&nbsp;&nbsp; 
          <input type="hidden"  name="maxPage" value="<%=maxPage%>">
	 	  <input type="hidden" name="maxCount" value="<%=maxCount%>" />
	 	  <input type="hidden" name="nsrwjbm" value="<%=nsrwjbm%>" />
	 	  <input type="hidden" name="startdate" value="<%=startdate%>" />
	 	  <input type="hidden" name="enddate" value="<%=enddate%>" />
	 	  <input type="hidden" name="swjgbm" value="<%=swjgbm%>" />
        </td>
      </tr>
    </table>
    <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>
