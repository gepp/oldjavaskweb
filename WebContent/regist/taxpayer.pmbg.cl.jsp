<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Pmbg pmbg = (Pmbg)request.getAttribute("pmbg");
ArrayList alBgszsm = pmbg.getAlBgszsm();
ArrayList alYszsm = pmbg.getAlYszsm();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>品目变更审核</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/bgServlet?method=import" class="nav">变更处理</a> &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/bgServlet?method=szsmBglist" class="nav">税种税目变更</a> &gt;&gt;&nbsp;&nbsp;变更审核</td>
      </tr>
    </table>
  </div>
  <div id="main">
  <form name="form1" action="/javaskweb/bgServlet?method=pmbgclAction" method="post" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="20%">纳税人微机编码：</td>
        <td><%=pmbg.getNsrwjbm() %>&nbsp;</td>
      </tr>
      <tr>
        <td>纳税人名称：</td>
        <td><%=pmbg.getNsrmc() %>&nbsp;</td>
      </tr>
      <tr>
        <td>机器编号：</td>
        <td><%=pmbg.getJqbh() %>&nbsp;</td>
      </tr>
      <tr>
        <td>变更说明：</td>
        <td><%=pmbg.getBgsm() %>&nbsp;</td>
      </tr>
    </table>
    <br />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">原税种税目</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <th width="20%">税目编码</th>
        <th width="30%">税目名称</th>
      </tr>
<%
if(alYszsm!=null&&!alYszsm.isEmpty()){
	Iterator it = alYszsm.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		
%>
	  <tr>
        <td align="center"><%=(String)hm.get("smbm") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("smmc") %></td>
      </tr>
<%	
	}
}
%>
      
    </table>
    <br />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">申请变更后税种税目</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <th width="20%">税目编码</th>
        <th width="30%">税目名称</th>
      </tr>
<%
if(alBgszsm!=null&&!alBgszsm.isEmpty()){
	Iterator it = alBgszsm.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		
%>
      <tr>
        <td align="center"><%=(String)hm.get("smbm") %>&nbsp;</td>
        <td align="center"><%=(String)hm.get("smmc") %></td>
      </tr>
<%	
	}
}
%>
    </table>
    <br />
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">变更处理</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
      <tr>
        <td width="15%">审核标记：</td>
        <td><input type="radio" name="clbz" value="1" id="RadioGroup1_0" <% if(pmbg.getClbz()==0){out.println("checked");}else{if(pmbg.getClbz()==1){out.println("checked");}}%> />
          审核通过
          <input type="radio" name="clbz" value="2" id="RadioGroup1_1" <% if(pmbg.getClbz()==2){out.println("checked");} %> />
          审核不通过 </td>
      </tr>
      <tr>
        <td>审核意见：</td>
        <td><textarea name="clyj" id="clyj" cols="45" rows="5"><%=pmbg.getClyj()==null?"":pmbg.getClyj() %></textarea>
        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
      <tr>
        <td height="40" align="center">
          <input type="hidden" name="sid" value="<%=pmbg.getSid() %>" />
<%
if(pmbg.getClbz()==0){
%>
          <input type="submit" name="next_btn" value="确 定" style="cursor:hand;" />
          &nbsp;&nbsp;&nbsp;&nbsp;
<%
}
%>
          <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/bgServlet?method=szsmBglist';" />
        </td>
      </tr>
    </table>
    </form>
  </div>
</div>
</body>
</html>
