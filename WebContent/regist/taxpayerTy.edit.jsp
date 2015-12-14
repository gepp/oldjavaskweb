<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/Calendar.js" ></script>
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
function chooseHYMX(){
	var hybm = document.all('hybm').value;
	if(hybm==null||hybm==""){
		alert('请先选择所属行业！');
	}
	else{
		window.open('/javaskweb/taxpayer.do?op=chooseHYMX&hybm='+hybm,'chooseHYMX','width=600,height=400,left=200,top=200,scrollbars=yes');
	}
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayerTyServlet?method=import" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;纳税人信息修改</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/taxpayer.do?op=updateNsrAction" method="post" onSubmit="return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="4">纳税户信息</th>
        </tr>
        <tr>
          <td width="18%">纳税人微机编码：</td>
          <td><%=nsrxx.getNsrwjbm() %><input type="hidden" name="nsrwjbm" value="<%=nsrxx.getNsrwjbm() %>"/></td>
          <td width="18%">纳税人识别号：</td>
          <td width="32%"><input type="text" style="width:40%" name="nsrsbh" value="<%=nsrxx.getNsrsbh() %>"  maxlength="20" />
            &nbsp;<font color="#FF0000">*</font></td>
        </tr>
        <tr>
          <td>纳税人名称：</td>
          <td><input type="text" style="width:70%" name="nsrmc" value="<%=nsrxx.getNsrmc() %>" required="required" controlName="纳税人名称" maxlength="100" />
            &nbsp;<font color="#FF0000">*</font></td>
          <td>经营地址：</td>
          <td><input type="text" style="width:70%" name="jydz" value="<%=nsrxx.getJydz() %>" /></td>
        </tr>
        <tr>
          <td>法人代表：</td>
          <td><input type="text" style="width:40%" name="frdb" value="<%=nsrxx.getFrdb() %>" /></td>
          <td>注册类型：</td>
          <td><input type="text" style="width:60%" name="zclxmc" value="<%=nsrxx.getZclxmc() %>" required="required" controlName="注册类型" readonly onClick="window.open('/javaskweb/taxpayer.do?op=chooseZCLX','chooseZCLX','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="window.open('/javaskweb/taxpayer.do?op=chooseZCLX','chooseZCLX','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            <input type="hidden" name="zclxbm" value="<%=nsrxx.getZclxbm() %>" />
          </td>
        </tr>
        <tr>
          <td>所属行业：</td>
          <td><input type="text" style="width:60%" name="hymc" value="<%=nsrxx.getHymc() %>" required="required" controlName="所属行业" readonly onClick="window.open('/javaskweb/taxpayer.do?op=chooseSSHY','chooseSSHY','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="window.open('/javaskweb/taxpayer.do?op=chooseSSHY','chooseSSHY','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            <input type="hidden" name="hybm" value="<%=nsrxx.getHybm() %>" /></td>
          <td>经营项目：</td>
          <td><input type="text" style="width:60%" name="hymxmc" value="<%=nsrxx.getHymxmc() %>" required="required" controlName="经营项目" readonly onClick="chooseHYMX();" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="chooseHYMX();" />
            <input type="hidden" name="hymxbm" value="<%=nsrxx.getHymxbm() %>" /></td>
        </tr>
        <tr>
          <td>征收方式：</td>
          <td><input type="radio" name="zsfs" value="0" id="zsfs_0" <% if(nsrxx.getZsfs()==0){out.println("checked");} %> />
            查账征收户
            <input type="radio" name="zsfs" value="1" id="zsfs_1" <% if(nsrxx.getZsfs()==1){out.println("checked");} %> />
            核定户 </td>
          <td>月核定营业额（元）：</td>
          <td><input type="text" style="width:40%" name="yhde" value="<%=nsrxx.getYhde() %>" dataType="float" /></td>
        </tr>
        <tr>
          <td>主管科(所)：</td>
          <td><input type="text" style="width:60%" name="swjgmc" value="<%=nsrxx.getSwjgmc() %>" required="required" controlName="主管科(所)" readonly onclick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            <input type="hidden" name="swjgbm" value="<%=nsrxx.getSwjgbm() %>" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>办税员：</td>
          <td><input type="text" style="width:40%" name="bsy" value="<%=nsrxx.getBsy() %>" maxlength="10" /></td>
          <td>税收管理员:</td>
          <td><input type="text" style="width:40%" name="ssgly" value="<%=nsrxx.getSsgly() %>" maxlength="10" /></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center"><img src="images/ico_01.gif" width="22" height="12" /> 确认提交信息，点击下一步进入选择税种税目！！ <br/>
            <br/>
            <input type="submit" name="next_btn" value="保  存" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返  回" onclick="location.href='/javaskweb/taxpayer.do?op=toCheckTaxpayer&nsrwjbm=<%=nsrxx.getNsrwjbm() %>'" />
            
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
