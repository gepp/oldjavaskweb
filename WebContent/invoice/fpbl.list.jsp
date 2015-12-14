<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
ArrayList alFpbl = (ArrayList)request.getAttribute("alFpbl");
SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票补领</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	var nsrwjbm = document.getElementById('nsrwjbm').value;
	window.location.href='/javaskweb/fpService.do?op=bladd&nsrwjbm='+nsrwjbm;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;发票管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/fpService.do?op=toFpbl" class="nav">发票补领</a></td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="18%">纳税人微机编码：</td>
        <td><%=nsrxx.getNsrwjbm() %>&nbsp;<input type="hidden" id="nsrwjbm" name="nsrwjbm" value="<%=nsrxx.getNsrwjbm() %>" /></td>
        <td width="17%">纳税人名称：</td>
        <td width="33%"><%=nsrxx.getNsrmc() %>&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="30%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票补领信息</a></div></td>
        <td align="right"><input type="button" name="btn" value=" 新 增 " onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;<input type="button" name="btn" value=" 返 回 " onclick="window.location.href='/javaskweb/fpService.do?op=toFpbl'" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="*">发票名称</th>
        <th width="15%">发票代码</th>
        <th width="15%">发票起始号码</th>
        <th width="15%">发票截止号码</th>
        <th width="15%">是否下载</th>
        <th width="15%">补领日期</th>
      </tr>
<%
if(alFpbl!=null&&!alFpbl.isEmpty()){
	Iterator it = alFpbl.iterator();
	while(it.hasNext()){
		Fpjmx fpjmx = (Fpjmx)it.next();
		String fpqsh = String.valueOf(fpjmx.getFpqsh());
		for(int i=fpqsh.length();i<8;i++){
			fpqsh = "0"+fpqsh;
		}
		String fpjzh = String.valueOf(fpjmx.getFpjzh());
		for(int j=fpjzh.length();j<8;j++){
			fpjzh = "0"+fpjzh;
		}
%>
	  <tr>
        <td><%=fpjmx.getFpmc() %>&nbsp;</td>
        <td><%=fpjmx.getFpdm() %>&nbsp;</td>
        <td><%=fpqsh %>&nbsp;</td>
        <td><%=fpjzh %>&nbsp;</td>
        <td><%=fpjmx.getFpxfzt()==1?"已下载":"未下载" %>&nbsp;</td>
        <td><%=fpjmx.getFplgrq() %>&nbsp;</td>
      </tr>
<%	
	}
}
%>
      
    </table>
  </div>
</div>
</body>
</html>
