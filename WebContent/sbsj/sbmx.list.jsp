<%@ page contentType = "text/html;charset=utf-8" language="java"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
ArrayList alSbmx = (ArrayList)request.getAttribute("alSbmx");

SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>明细数据</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">

</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;申报数据&gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/sbmxTyServlet?method=import" class="nav">申报明细</a>&gt;&gt;&nbsp;&nbsp;明细信息</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="10%">开票日期</th>
        <th width="8%">开票类型</th>
        <th width="10%">发票号</th>
        <th width="8%">合计总金额</th>
        <th width="10%">原发票号</th>
        <th width="10%">发票代码</th>
        <th>付款单位</th>
        <th width="8%">收款员</th>
        <th width="8%">项目数</th>
        <th width="8%">操作</th>
      </tr>
      <%
Iterator it = alSbmx.iterator();
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
	else {
		kplxmc = "废票";
	}
	int xms = fpkj.getXms();
	
	String fphm = String.valueOf(fpkj.getFphm());
	for(int i=fphm.length();i<8;i++){
		fphm = "0"+fphm;
	}
%>
      <tr>
        <td align="center"><%=fm1.format(fpkj.getKprq()) %>&nbsp;</td>
        <td align="center"><%=kplxmc %>&nbsp;</td>
        <td align="center"><%=fphm %>&nbsp;</td>
        <td align="center"><%=fpkj.getHjzje() %>&nbsp;</td>
        <td align="center"><%=fpkj.getYfphm() %>&nbsp;</td>
        <td align="center"><%=fpkj.getFpdm() %>&nbsp;</td>
        <td align="center"><%=fpkj.getFkdw() %>&nbsp;</td>
        <td align="center"><%=fpkj.getSky() %>&nbsp;</td>
        <td align="center"><%=xms %>&nbsp;</td>
        <td align="center">
<%
if(xms>0){
%>
			<img src="images/ico_edit.gif" width="13" height="13" alt=" 查 看 " style="cursor:hand;" onClick="window.open('/javaskweb/sbmxTyServlet?method=showmxdt&parentid=<%=fpkj.getSid() %>','','width=800,height=600,left=200,top=200,scrollbars=yes');"/> 
<%
}
%>
        &nbsp;
        </td>
      </tr>
      <%
}
%>
    </table>
  </div>
</div>
</body>
</html>
