<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx) request.getAttribute("nsrxx");
String jqbh = (String) request.getAttribute("jqbh");
System.out.println("...."+jqbh);
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
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;机器管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayer.do?op=toImport" class="nav">注销</a> &gt;&gt;&nbsp;&nbsp;机器注销</td>
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
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="listTab">
    <form name="form1" action="taxpayer.do?op=updateTyxx" method="post" >
     <input type="hidden" value="<%= nsrxx.getNsrwjbm()%>" name="nsrbm" id="nsrbm"/>
      <input type="hidden" value="<%=jqbh %>" name="jqbh" id="jqbh"/>
  
        <tr>
            <td width="10%" height="30" bgcolor="#f2f2f2" class="left_txt2">注销原因：</td>
            <td bgcolor="#f2f2f2"><textarea name="zxyy" id="tyyy" value="" style=" width:460px;height:100px" ></textarea></td>
            <td width="10%" height="30" bgcolor="#f2f2f2" class="left_txt2">审核意见：</td>
            <td bgcolor="#f2f2f2"><textarea name="shyj" id="shyj" value="" style=" width:460px;height:100px" ></textarea></td>
	     </tr>
		 <tr>
            <td colspan="4" align="center" height="40"><input type="submit" class="btn" name="add_btn" value=" 确 定 " />
              &nbsp;&nbsp;
              <input type="button" name="ret_btn" class="btn" value=" 返  回 " onclick="history.go(-1)" />
            </td>
          </tr>
          
      </form> 
      </table>
  </div>
</div>
</body>
</html>
