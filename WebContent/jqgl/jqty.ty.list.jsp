<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx) request.getAttribute("nsrxx");
ArrayList tfyList =(ArrayList)request.getAttribute("tfyList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
 //停业信息录入页面
 function tyxxlr(nsrbm,jqbh){
	 window.location.href='taxpayer.do?op=tyxxlr&nsrbm='+nsrbm+'&jqbh='+jqbh;
 }

</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;机器管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayer.do?op=toImport" class="nav">停业</a> &gt;&gt;&nbsp;&nbsp;机器停业</td>
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
     
        <th width="10%">停业时间</th>
        <th>操作</th>
      </tr>
<%

if(tfyList!=null&&!tfyList.isEmpty()){
	Iterator it = tfyList.iterator();
	while(it.hasNext()){
		Tfy tfy = (Tfy)it.next();
%>
	  <tr>
        <td align="center"><%=tfy.getJqxhbm() %>&nbsp;</td>
        <td align="center"><%=tfy.getJqbh() %>&nbsp;</td>
        <td align="center"><%=tfy.getJqxhmc() %>&nbsp;</td>
   
          <td align="center"><%=tfy.getTysj() %>&nbsp;</td>
        <td align="center"><input type="button" name="btn" value="停 业" onclick="tyxxlr('<%=nsrxx.getNsrwjbm() %>','<%=tfy.getJqbh() %>')" /></td>
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
