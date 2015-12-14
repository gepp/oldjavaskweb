<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Xebg xebg = (Xebg)request.getAttribute("xebg");
double sqljkpxe = xebg.getSqljkpxe();
double sqljtpxe = xebg.getSqljtpxe();
double pzljkpxe = xebg.getPzljkpxe();
double pzljtpxe = xebg.getPzljtpxe();

if(pzljkpxe==0){
	pzljkpxe = sqljkpxe;
}
if(pzljtpxe==0){
	pzljtpxe = sqljtpxe;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>限额变更处理</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
function change_cz(val){
	document.getElementById('dzkpxe').value = val;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/bgServlet?method=import" class="nav">变更处理</a> &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/bgServlet?method=xeBgList" class="nav">限额变更</a> &gt;&gt;&nbsp;&nbsp;限额变更审核</td>
      </tr>
    </table>
  </div>
  <div id="main">
  <form name="form1" action="/javaskweb/bgServlet?method=xebgclAction" method="post" onSubmit="return validator(this)">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">变更限额申请信息</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="20%">纳税人微机编码：</td>
        <td width="30%"><%=xebg.getNsrwjbm() %>&nbsp;</td>
        <td width="20%">机器编号：</td>
        <td><%=xebg.getJqbh() %>&nbsp;</td>
      </tr>
      <tr>
        <td>纳税人名称：</td>
        <td colspan="3"><%=xebg.getNsrmc() %>&nbsp;</td>
      </tr>
      <tr>
        <td>剩余累计开票限额：</td>
        <td><%=xebg.getSyljkpxe() %>&nbsp;</td>
        <td>剩余累计退票限额：</td>
        <td><%=xebg.getSyljtpxe() %>&nbsp;</td>
      </tr>
      <tr>
        <td>申请累计开票限额：</td>
        <td><%=xebg.getSqljkpxe() %>&nbsp;</td>
        <td>申请累计退票限额：</td>
        <td><%=xebg.getSqljtpxe() %>&nbsp;</td>
      </tr>
      <tr>
        <td>变更说明：</td>
        <td colspan="3"><%=xebg.getBgsm() %>&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">变更处理</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
      <tr>
        <td width="15%">调整后单张开票限额</td>
        <td>
          <input type="text" id="dzkpxe" name="dzkpxe" value="<%=pzljkpxe %>" readonly />
        </td>
      </tr>
      <tr>
        <td width="15%">调整后月累计开票限额</td>
        <td>
          <input type="text" name="pzljkpxe" value="<%=pzljkpxe %>" onblur="change_cz(this.value);" />
        </td>
      </tr>
      <tr>
        <td width="15%">调整后月累计退票限额</td>
        <td>
          <input type="text" name="pzljtpxe" value="<%=pzljtpxe %>" />
        </td>
      </tr>
      <tr>
        <td>审核标记：</td>
        <td><input type="radio" name="clbz" value="1" id="RadioGroup1_0" <% if(xebg.getClbz()==0){out.println("checked");}else{if(xebg.getClbz()==1){out.println("checked");}}%> />
          审核通过
          <input type="radio" name="clbz" value="2" id="RadioGroup1_1" <% if(xebg.getClbz()==2){out.println("checked");} %>/>
          审核不通过 </td>
      </tr>
      <tr>
        <td>审核意见：</td>
        <td><textarea name="clyj" id="clyj" cols="45" rows="5"><%=xebg.getClyj() %></textarea>
        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
      <tr>
        <td height="40" align="center">
<%
if(xebg.getClbz()==0){
%>
		  <input type="submit" name="next_btn" value="确 定" />
          &nbsp;&nbsp;&nbsp;&nbsp;
<%
}
%>
          <input type="hidden" name="sid" value="<%=xebg.getSid() %>"/>
          <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/bgServlet?method=xeBgList';" />
        </td>
      </tr>
    </table>
    </form>
  </div>
</div>
</body>
</html>