<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alPmbg = (ArrayList)request.getAttribute("alPmbg");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/bgServlet?method=import" class="nav">变更处理</a>  &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">税种税目变更处理</a></td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="12%">纳税人微机编码</th>
        <th width="18%">纳税人名称</th>
        <th width="13%">机器编号</th>
        <th width="18%">原税种税目</th>
        <th width="18%">申请变更的税种税目</th>
        <th width="10%">状态</th>
        <th>操作</th>
      </tr>
<%
if(alPmbg!=null&&!alPmbg.isEmpty()){
	Iterator it = alPmbg.iterator();
	while(it.hasNext()){
		Pmbg pmbg = (Pmbg)it.next();
		
		int clbz = pmbg.getClbz();
		String clbzmc = "";
		if(clbz==1){
			clbzmc = "审核通过";
		}
		else if(clbz==2){
			clbzmc = "审核未通过";
		}
		else{
			clbzmc = "未处理";
		}
%>
	  <tr>
        <td align="center"><%=pmbg.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=pmbg.getNsrmc() %>&nbsp;</td>
        <td align="center"><%=pmbg.getJqbh() %>&nbsp;</td>
        <td align="center"><%=pmbg.getYsmbm() %>&nbsp;</td>
        <td align="center"><%=pmbg.getBgsmbm() %>&nbsp;</td>
        <td align="center"><%=clbzmc %>&nbsp;</td>
        <td align="center">
<%
		if(clbz==1||clbz==2){
%>
		  <input type="button" name="btn" value="查  看" onclick="window.location.href='/javaskweb/bgServlet?method=pmbgcl&sid=<%=pmbg.getSid() %>';" />
<%			
		}
		else{
%>
		  <input type="button" name="btn" value="变更处理" onclick="window.location.href='/javaskweb/bgServlet?method=pmbgcl&sid=<%=pmbg.getSid() %>';" />
<%
		}
%>
        &nbsp;</td>
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
