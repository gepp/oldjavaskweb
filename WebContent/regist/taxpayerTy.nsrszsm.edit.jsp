<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
ArrayList nsrszsm  = nsrxx.getNsrszsm();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function add(){
	var nsrwjbm = document.all('nsrwjbm').value;
	window.open('/javaskweb/taxpayer.do?op=updateNrsSzsm&nsrwjbm='+nsrwjbm,'','width=800,height=600,left=200,top=200,scrollbars=yes');
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayer.do?op=toImport" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;税种税目信息修改</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form action="" method="post" name="form1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <td width="15%">纳税人微机编码：</td>
          <td><%=nsrxx.getNsrwjbm() %>&nbsp;
            <input type="hidden" name="nsrwjbm" value="<%=nsrxx.getNsrwjbm() %>"></td>
          <td width="15%">纳税人识别号：</td>
          <td width="35%"><%=nsrxx.getNsrsbh() %>&nbsp;</td>
        </tr>
        <tr>
          <td>纳税人名称：</td>
          <td><%=nsrxx.getNsrmc() %>&nbsp;</td>
          <td>经营地址：</td>
          <td><%=nsrxx.getJydz() %>&nbsp;</td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="20%" valign="bottom"><a href="JAVASCRIPT:" class="nav">添加税种税目信息</a></td>
          <td align="right"><input type="button" name="add_btn" value=" 添 加 "  onClick="add();"/>
            &nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
        <tr>
          <th width="20%">税目编码</th>
          <th width="40%">税目名称</th>
          <th width="30%">税目简称</th>
          <th width="10%">税率</th>
        </tr>
        <%
int num = 0;
if(nsrszsm!=null&&!nsrszsm.isEmpty()){
Iterator it = nsrszsm.iterator();

while(it.hasNext()){
	num++;
	HashMap hm = (HashMap)it.next();
%>
        <tr>
          <td align="center"><%=(String)hm.get("smbm")%>&nbsp;</td>
          <td align="center"><%=(String)hm.get("smmc")%>&nbsp;</td>
          <td align="center"><%=(String)hm.get("smjc")%>&nbsp;</td>
          <td align="center"><%=(Double)hm.get("sl")%>&nbsp;</td>
        </tr>
        <%
}
}
%>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center">
            <input type="button" name="next_btn" value="返  回" onclick="window.location.href='/javaskweb/taxpayer.do?op=toCheckTaxpayer&nsrwjbm=<%=nsrxx.getNsrwjbm() %>';" style="cursor:hand;" />
            <input type="hidden" name="num" value="<%=num%>" />
            &nbsp;&nbsp;&nbsp;&nbsp; </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
