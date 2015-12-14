<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
String nsrwjbm = (String)request.getAttribute("nsrwjbm");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/jquery.funkyUI.js"></script>
<script language="javascript" src="images/Calendar.js" ></script>
<script language="javascript" src="images/validator.js"></script>
<script language="javascript" type="text/javascript">

//function getZclx(){
//$("#abc").load("/javaskweb/taxpayer.do?op=chooseZCLX","width=600,height=400,left=200,top=200,scrollbars=yes");
//}

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
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayer.do?op=toImport" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;注册登记</td>
        <td><div class="userTitleRed">
            <ul>
              <li><a href="#" class="black">税务登记信息</a></li>
            </ul>
          </div>
          <div class="userTitleGreen"><a href="#" class="black">纳税人税种税目</a></div>
          <div class="userTitleGreen"><a href="#" class="black">税控装置信息录入</a></div>
          <div class="userTitleGreen"><a href="#" class="black">开票限额录入</a></div>
          <div class="userTitleGreen"><a href="#" class="black">发卡</a></div></td>
      </tr>
    </table>
  </div>
  <div id="abc" ></div>
  <div id="main" style="z-index: 0">
    <form name="form1" action="/javaskweb/taxpayer.do?op=nsrxxAddAction" method="post" onSubmit="return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="4">纳税户信息 </th>
        </tr>
        <tr>
          <td width="18%">纳税人微机编码：</td>
          <td><input type="text" name="nsrwjbm" style="width:40%" readonly value="<%=nsrwjbm%>" /></td>
          <td width="18%">纳税人识别号：</td>
          <td width="32%"><input type="text" style="width:40%" name="nsrsbh" required="required" d controlName="纳税人识别号" maxlength="20" />
            &nbsp;<font color="#FF0000">*</font></td>
        </tr>
        <tr>
          <td>纳税人名称：</td>
          <td><input type="text" style="width:70%" id="nsrmc" name="nsrmc" required="required" controlName="纳税人名称" maxlength="20" />
            &nbsp;<font color="#FF0000">*</font></td>
          <td>经营地址：</td>
          <td><input type="text" style="width:70%" name="jydz" /></td>
        </tr>
        <tr>
          <td>法人代表：</td>
          <td><input type="text" style="width:40%" name="frdb" /></td>
          <td>注册类型：</td>
          <td><input type="text" style="width:60%" name="zclxmc" required="required" controlName="注册类型" readonly id="zclxmc" onClick="window.open('/javaskweb/taxpayer.do?op=chooseZCLX','chooseZCLX','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="window.open('/javaskweb/taxpayer.do?op=chooseZCLX','chooseZCLX','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            <input type="hidden" name="zclxbm" />
          </td>
        </tr>
        <tr>
          <td>所属行业：</td>
          <td><input type="text" style="width:60%" name="hymc" required="required" controlName="所属行业" readonly onClick="window.open('/javaskweb/taxpayer.do?op=chooseSSHY','chooseSSHY','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="window.open('/javaskweb/taxpayer.do?op=chooseSSHY','chooseSSHY','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            <input type="hidden" name="hybm" /></td>
          <td>经营项目：</td>
          <td><input type="text" style="width:60%" name="hymxmc" required="required" controlName="经营项目" readonly onClick="chooseHYMX();" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="chooseHYMX();" />
            <input type="hidden" name="hymxbm" /></td>
        </tr>
        <tr>
          <td>征收方式：</td>
          <td><input type="radio" name="zsfs" value="0" id="zsfs_0" checked="checked" />
            查账征收户
            <input type="radio" name="zsfs" value="1" id="zsfs_1" />
            核定户 </td>
          <td>月核定营业额（元）：</td>
          <td><input type="text" style="width:40%" name="yhde" dataType="float" /></td>
        </tr>
        <tr>
          <td>主管科(所)：</td>
          <td><input type="text" style="width:60%" name="swjgmc" required="required" controlName="主管科(所)" readonly onclick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            &nbsp;<font color="#FF0000">*</font>&nbsp;<img src="images/Select.gif" width="16" height="16" style="cursor:hand;" onClick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=400,left=200,top=200,scrollbars=yes');" />
            <input type="hidden" name="swjgbm" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>办税员：</td>
          <td><input type="text" style="width:40%" name="bsy" maxlength="10" /></td>
          <td>税收管理员:</td>
          <td><input type="text" style="width:40%" name="ssgly" maxlength="10" /></td>
        </tr>
         <tr>
         
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center"><img src="images/ico_01.gif" width="22" height="12" /> 确认提交信息，点击下一步进入选择税种税目！！ <br/>
            <br/>
            <input type="button" name="ret_btn" value="上一步" onclick="location.href='/javaskweb/taxpayer.do?op=toImport'" />
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" name="next_btn" value="下一步" style="cursor:hand;" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
