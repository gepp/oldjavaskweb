<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx) request.getAttribute("nsrxx");
ArrayList jqZxList =(ArrayList)request.getAttribute("jqZxList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function zxxxlr( nsrbm,jqbh){
	 window.location.href='taxpayer.do?op=zxxxlr&nsrbm='+nsrbm+'&jqbh='+jqbh;
	 
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;机器管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayer.do?op=toImport" class="nav">注销</a> &gt;&gt;&nbsp;&nbsp;机器注销</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
        <td align="right"></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="18%">纳税人微机编码：</td>
        <td><%=nsrxx.getNsrwjbm() %>&nbsp;</td>
        <td width="17%">纳税人识别号：</td>
        <td width="33%"><%=nsrxx.getNsrsbh() %>&nbsp;</td>
      </tr>
      <tr>
        <td>纳税人名称：</td>
        <td><%=nsrxx.getNsrmc() %>&nbsp;</td>
        <td>经营地址：</td>
        <td><%=nsrxx.getJydz() %>&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">机器信息</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="15%">机器型号</th>
        <th width="20%">机器编号</th>
        <th width="20%">机器名称</th>
        <th width="20%">机器状态</th>
        <th width="10%">注销时间</th>
        <th>操作</th>
      </tr>
<%

if(jqZxList!=null&&!jqZxList.isEmpty()){
	Iterator it = jqZxList.iterator();
	while(it.hasNext()){
		JqZx jqzx = (JqZx)it.next();
%>
	  <tr>
        <td align="center"><%=jqzx.getJqxhbm() %>&nbsp;</td>
        <td align="center"><%=jqzx.getJqbh() %>&nbsp;</td>
        <td align="center"><%=jqzx.getJqxhmc() %>&nbsp;</td>
      
        <td align="center"><%=jqzx.getJqzt().equals("1")?"正常":"注销" %>&nbsp;</td>
          <td align="center"> <%jqzx.getZxsj();%>&nbsp;</td>
        <td align="center"><input type="button" name="btn" value="注销" onclick="zxxxlr('<%=nsrxx.getNsrwjbm()%>','jqzx.getJqbh() ')"/></td>
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
