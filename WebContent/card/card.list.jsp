<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	HashMap hm = (HashMap)request.getAttribute("UINFO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
 if(hm != null&&!hm.isEmpty())
 {
	 HashMap ef01 = (HashMap)hm.get("EF01");
%>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">监控管理数据文件</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0">
  <tr>
    <td width="194">税控卡编号：</td>
    <td width="183"><%=(String)ef01.get("SKKH")%>&nbsp;</td>
    <td width="261">数据加密密钥标识符：</td>
    <td width="208"><%=(String)ef01.get("BSF")%>&nbsp;</td>
  </tr>
  <tr>
    <td>开票截止日期：</td>
    <td><%=(String)ef01.get("KPJZRQ")%>&nbsp;</td>
    <td>单张发票开票金额限额：</td>
    <td><%=Integer.parseInt((String)ef01.get("DZLJJE"))/100%>&nbsp;</td>
  </tr>
  <tr>
    <td>单张发票开票金额限额：</td>
    <td><%=Integer.parseInt((String)ef01.get("YKPLJJE"))/100%>&nbsp;</td>
    <td>退票累计金额限额：</td>
    <td><%=Integer.parseInt((String)ef01.get("YTPLJJE"))/100%>&nbsp;</td>
  </tr>
  <tr>
    <td>税种税目索引号：</td>
    <td><%=(String)ef01.get("SZSMINDE")%>&nbsp;</td>
    <td>明细申报标志与税控码密钥标识符：</td>
    <td><%=(String)ef01.get("MXSBBZ")%>&nbsp;</td>
  </tr>
</table>
<br>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">基本信息文件</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <br>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">税种税目索引文件</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
<br>    
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">申报数据汇总文件</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
<br>    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票领购信息文件</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
<br>    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">税控收款机信息文件</a></div></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
<%
 }
%>
</body>
</html>