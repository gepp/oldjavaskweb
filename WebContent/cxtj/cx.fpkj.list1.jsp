<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%
ArrayList alFpkj = (ArrayList)request.getAttribute("alFpkj");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main" style="z-index: -1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票明细查询列表</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="10%">纳税人微机编码</th>
        <th width="15%">纳税人名称</th>
        <th width="8%">发票号码</th>
        <!-- <th width="10%">机器编号</th> -->
        <th width="10%">项目名称</th>
        <th width="8%">开票时间</th>
        <th width="7%">开票类型</th>
        <th width="10%">总金额</th>
        <th width="10%">折扣总金额</th>
        <th width="5%">收款员姓名</th>
        <th width="5%">工号</th>
        <th width="16%">付款单位名称</th>
        <th>查看详情</th>
      </tr>
<%

if(alFpkj!=null&&!alFpkj.isEmpty()){
	Iterator it = alFpkj.iterator();
	while(it.hasNext()){
		Fpkj fpkj = (Fpkj)it.next();
		int kplx = fpkj.getKplx();
		String kplxmc = "";
		if(kplx==1){
			kplxmc = "正常票";
		}
		else if(kplx==2){
			kplxmc = "退票";
		}
		else if(kplx==3){
			kplxmc = "废票";
		}
		else{
			
		}
%>
	  <tr>
        <td align="center"><%=fpkj.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=fpkj.getNsrmc() %>&nbsp;</td>
        <td align="center"><%=fpkj.getJqbh() %>&nbsp;</td>
        <td align="center"><%=fpkj.getKprq() %>&nbsp;</td>
        <td align="center"><%=fpkj.getFpdm() %>&nbsp;</td>
        <td align="center"><%=Util.fpIntToString(fpkj.getFphm()) %>&nbsp;</td>
        <td align="center"><%=kplxmc %>&nbsp;</td>
        <td align="center"><%=Util.fpIntToString(fpkj.getYfphm()) %>&nbsp;</td>
        <td align="center"><%=fpkj.getXms() %>&nbsp;</td>
        <td align="center"><input type="button" name="btn" value="查看项目信息" onclick="window.location.href='/javaskweb/cxNsrxxServlet?method=nsrxxMx&sid=<%=fpkj.getSid() %>';" /></td>
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
