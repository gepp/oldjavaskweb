<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
int xebgNum = ((Integer)request.getAttribute("xebgNum")).intValue();
int szsmNum = ((Integer)request.getAttribute("szsmNum")).intValue();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>变更处理</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">变更处理</a></td>
      </tr>
    </table>
  </div>
  <div id="main">
    <div id="main" style="text-align:center"> <br />
      <br />
      <table width="60%" border="0" cellspacing="0" cellpadding="0" id="userList2">
        <tr>
          <th width="50%">变更类型</th>
          <th width="20%">未处理数量</th>
          <th width="30%">操作</th>
        </tr>
        <tr>
          <td align="center">税种税目变更</td>
          <td align="center"><%=szsmNum %>&nbsp;</td>
          <td align="center"><input type="button" name="btn" value="变更处理" onclick="window.location.href='/javaskweb/bgServlet?method=szsmBgList';" /></td>
        </tr>
        <tr>
          <td align="center">限额变更</td>
          <td align="center"><%=xebgNum %>&nbsp;</td>
          <td align="center"><input type="button" name="xebtn" value="变更处理" onclick="window.location.href='/javaskweb/bgServlet?method=xeBgList';" /></td>
        </tr>
      </table>
    </div>
  </div>
</div>
</body>
</html>
