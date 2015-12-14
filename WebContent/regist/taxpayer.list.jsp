<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
ArrayList nsrjqxx = nsrxx.getNsrjqxx();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function jqzx(nsrwjbm,jqbh){
	if(confirm('确定注销该机器？')){
		document.all('iframe').src='taxpayerServlet?method=skqzx&nsrwjbm='+nsrwjbm+'&jqbh='+jqbh;
	}	
}

function error(errorMsg){
	alert(errorMsg);
}

function success(jqsl,nsrwjbm){
	if(jqsl>0){
		alert('注销成功！');
		window.location.href='taxpayerServlet?method=checkTaxpayer&nsrwjbm='+nsrwjbm;
	}
	else{
		if(confirm('注销成功，该纳税户无正常状态机器，点击确定注销该纳税户，点击取消不注销纳税户')){
			document.all('iframe').src = 'taxpayerServlet?method=nshzx&nsrwjbm='+nsrwjbm;
		}
		else{
			window.location.href='taxpayerServlet?method=checkTaxpayer&nsrwjbm='+nsrwjbm;
		}
	}
}

function successnsr(){
	alert('纳税人注销成功');
	window.location.href = 'taxpayerServlet?method=import';
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayerTyServlet?method=import" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;机器信息管理</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
        <td align="right"><!--<input type="button" name="btn" value="同步纳税人信息" onclick="window.location.href='taxpayerServlet?method=tbNsr&nsrwjbm=<%=nsrxx.getNsrwjbm() %>';" />-->&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
        <td><%=nsrxx.getJydz()==null?"":nsrxx.getJydz() %>&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">机器信息</a></div></td>
        <td align="right"><input type="button" name="btn" value="添加机器" onclick="window.location.href='taxpayerServlet?method=skzzxx&nsrwjbm=<%=nsrxx.getNsrwjbm() %>&jumpFlag=1';" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
        <th width="15%">机器型号</th>
        <th width="20%">机器编号</th>
        <th width="20%">税控卡号</th>
        <th width="20%">用户卡号</th>
        <th width="10%">状态</th>
        <th>操作</th>
      </tr>
<%

if(nsrjqxx!=null&&!nsrjqxx.isEmpty()){
	Iterator it = nsrjqxx.iterator();
	while(it.hasNext()){
		Jqxx jqxx = (Jqxx)it.next();
%>
	  <tr>
        <td align="center"><%=jqxx.getJqxhmc() %>&nbsp;</td>
        <td align="center"><%=jqxx.getJqbh() %>&nbsp;</td>
        <td align="center"><%=jqxx.getSkkh() %>&nbsp;</td>
        <td align="center"><%=jqxx.getYhkh() %>&nbsp;</td>
        <td align="center"><%=jqxx.getStatus()==1?"正常":"注销" %>&nbsp;</td>
        <td align="center">
          <input type="button" name="btn" value="修 改" onclick="window.location.href='taxpayerServlet?method=skzzxx&nsrwjbm=<%=nsrxx.getNsrwjbm() %>&jqbh=<%=jqxx.getJqbh() %>&jumpFlag=1';" />&nbsp;&nbsp;
          <input type="button" name="btn" value="注 销" onclick="jqzx('<%=nsrxx.getNsrwjbm() %>','<%=jqxx.getJqbh() %>');" />&nbsp;&nbsp;
          <input type="button" name="btn" value="发 卡" onclick="window.location.href='taxpayerServlet?method=fk&nsrwjbm=<%=nsrxx.getNsrwjbm() %>&jqbh=<%=jqxx.getJqbh() %>&jumpFlag=1';" />
        </td>
      </tr>
<%
	}
}
%>
      
    </table>
  </div>
</div>
<iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
</body>
</html>
