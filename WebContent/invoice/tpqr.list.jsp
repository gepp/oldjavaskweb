<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%
ArrayList alTp = (ArrayList)request.getAttribute("alTp");
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
String tprq = (String)request.getAttribute("tprq");
int tpfs = 0;
if(alTp!=null&&!alTp.isEmpty()){
	tpfs = alTp.size();
}
ArrayList alSm = (ArrayList)request.getAttribute("alSm");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function qr(fphm,fpdm,nsrwjbm,tprq){
	if(confirm('确认该退票？')){
		document.getElementById('iframe').src = '/javaskweb/tpqrServlet?method=updateQRBZ&fphm='+fphm+'&fpdm='+fpdm+'&nsrwjbm='+nsrwjbm+'&tprq='+tprq;
	}
}

function error(errorMsg){
	alert(errorMsg);
}

function success(successMsg,url){
	alert(successMsg);
	window.location.href=url;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;发票管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/tpqrServlet?method=import" class="nav">退票确认</a>&gt;&gt;&nbsp;&nbsp;退票确认处理</td>
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
        <td><%=nsrxx.getNsrwjbm() %>&nbsp;</td>
        <td width="17%">纳税人名称：</td>
        <td width="33%"><%=nsrxx.getNsrmc() %>&nbsp;</td>
      </tr>
    </table>
    <br>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="30%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">退票信息</a></div></td>
        <td align="center">您共有<font color="#FF0000"> <%=tpfs %> </font>张退票没有确认！</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="6%">编号</th>
        <th width="14%">退票发票代码</th>
        <th width="14%">退票发票号码</th>
        <th width="14%">退票开票日期</th>
        <th width="14%">原发票代码</th>
        <th width="14%">原发票号码</th>
        <th width="12%">退票金额（元）</th>
        <th width="*">操作</th>
      </tr>
<%
if(alTp!=null&&!alTp.isEmpty()){
	Iterator it = alTp.iterator();
	int i=1;
	while(it.hasNext()){
		Fpkj fpkj = (Fpkj)it.next();
%>
	  <tr>
        <td><%=i %>&nbsp;</td>
        <td><%=fpkj.getFpdm() %>&nbsp;</td>
        <td><%=Util.fpIntToString(fpkj.getFphm()) %>&nbsp;</td>
        <td><%=fpkj.getKprq() %>&nbsp;</td>
        <td><%=fpkj.getFpdm() %>&nbsp;</td>
        <td><%=Util.fpIntToString(fpkj.getYfphm()) %>&nbsp;</td>
        <td><%=fpkj.getHjzje() %>&nbsp;</td>
        <td><input type="button" name="btn" value="确认处理" onclick="qr(<%=fpkj.getFphm() %>,'<%=fpkj.getFpdm() %>','<%=nsrxx.getNsrwjbm() %>','<%=tprq %>');" /></td>
      </tr>
<%
		i++;
	}
}
%>
    </table>
    <br>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">退票异常处理</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="15%">税目编码</th>
        <th width="25%">税目名称</th>
        <th width="15%">开票金额</th>
        <th width="15%">核定金额</th>
        <th width="15%">未缴销退票金额</th>
        <th width="15%">转待征金额</th>
      </tr>
<%
if(alSm!=null&&!alSm.isEmpty()){
	Iterator it = alSm.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
%>
      <tr>
        <td><%=(String)hm.get("smbm") %>&nbsp;</td>
        <td><%=(String)hm.get("smmc") %>&nbsp;</td>
        <td><%=(Double)hm.get("kpje") %>&nbsp;</td>
        <td><%=(Double)hm.get("yhde") %>&nbsp;</td>
        <td><%=(Double)hm.get("tpzje") %>&nbsp;</td>
        <td><input type="text" value="<%=(Double)hm.get("tpzje") %>" /></td>
      </tr>
<%
	}
}
%>
      <tr>
        <td colspan="6" style="height:40" align="center"><input type="button" name="btn" value="转待征" onclick="document.all('btn_qbjx').disabled='';" />&nbsp;&nbsp;<input type="button" name="btn_qbjx" value="全部缴销" disabled />&nbsp;&nbsp;<input type="button" name="btn_qbjx" value="返 回" onclick="window.location.href='/javaskweb/tpqrServlet?method=import';" /></td>
      </tr>
    </table>
    <iframe id="iframe" name="iframe" width="0" height="0" frameborder="0"></iframe>
  </div>
</div>
</body>
</html>
