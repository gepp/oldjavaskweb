<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%
DecimalFormat dg = new DecimalFormat("0.00");
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
Jqxx jqxx = (Jqxx)request.getAttribute("jqxx");
String jumpFlag = (String)request.getAttribute("jumpFlag");
SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript" src="images/Calendar.js"></script>
<script language="javascript">
function checktext(){
	var flag = -1;
	var yljkpxe = document.all('yljkpxe').value*1;
	var yljtpxe = document.all('yljtpxe').value*1;
	var lxkpts=document.all('lxkpts').value*1;
	var lxkpzs=document.all('lxkpzs').value*1;
	var dzkpxe = document.all('dzkpxe').value*1;
	if(lxkpts>255){
		
		flag=0;
	}
	if(lxkpzs>255){
		flag=1;
	}
	if(yljkpxe-yljtpxe<0){
		flag=2;
	}
	if(yljkpxe<dzkpxe){
		flag=3;
		
	}
	
	return flag;
}
function next(){
	var flag = checktext();
	 
	 
	 if(flag==2){
		alert('月累计退票最高限额不能大于月累计开票最高限额');
		return false;
	}
	else if(flag==3){
		alert('单笔开票最高限额不能大于月累计开票最高限额');
		return false;
	}
	else{
			var frm = document.form1;
			if(validator(frm)){
				frm.action='/javaskweb/taxpayer.do?op=skxxlrAction';
				frm.submit();
			}
		 
	}
}

function fh(){
	var jqbh 		= document.getElementById('jqbh').value;
	var nsrwjbm 	= document.getElementById('nsrwjbm').value;
	var jumpFlag 	= document.getElementById('jumpFlag').value;
	window.location.href='/javaskweb/taxpayer.do?op=skzzxx&jqbh='+jqbh+'&nsrwjbm='+nsrwjbm+'&jumpFlag='+jumpFlag;
	
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
        <td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayerTyServlet?method=import" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;注册登记</td>
        <td><div class="userTitleGreen"> <a href="#" class="black">税务信息登记</a> </div>
          <div class="userTitleGreen">
            <a href="#" class="black">选择税种税目</a>
          </div>
          <div class="userTitleGreen"><a href="#" class="black">税控装置信息录入</a></div>
          <div class="userTitleRed"><ul>
              <li><a href="#" class="black">开票限额录入</a></li>
            </ul></div>
          <div class="userTitleGreen"><a href="#" class="black">发卡</a></div></td>
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
        <tr>
          <td>机器型号：</td>
          <td><%=jqxx.getJqxhmc() %>&nbsp;</td>
          <td>机器编号：</td>
          <td><%=jqxx.getJqbh() %>&nbsp;</td>
        </tr>
        <tr>
          <td>税控卡号：</td>
          <td><%=jqxx.getSkkh() %>&nbsp;</td>
          <td>用户卡号：</td>
          <td><%=jqxx.getYhkh() %>&nbsp;</td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="20%" valign="bottom"><a href="JAVASCRIPT:" class="nav">开票限额信息</a></td>
          <td align="right">
            &nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <td width="18%">开票截止日期：</td>
          <td><input type="text" name="kpjzrq" style="width:40%" onClick="SelectDate(this)" readonly value="<%=jqxx.getKpjzrq() %>" required="required" controlName="开票截止时间" /></td>
          <td width="18%">单张开票限额：</td>
          <td width="32%"><input type="text" style="width:40%" name="dzkpxe" required="required" dataType="float" dataBetween="1,42949672.95" controlName="单张开票限额" value="<%=dg.format(jqxx.getDzkpxe()) %>" />
            &nbsp;<font color="#FF0000">*</font></td>
        </tr>
        <tr>
          <td>月累计开票限额：</td>
          <td><input type="text" style="width:40%" name="yljkpxe" required="required" dataType="float" dataBetween="1,42949672.95" controlName="月累计开票限额" value="<%=dg.format(jqxx.getYljkpxe()) %>" />
            &nbsp;<font color="#FF0000">*</font></td>
          <td>月累计退票限额：</td>
          <td><input type="text" style="width:40%" name="yljtpxe" required="required" dataType="float" dataBetween="1,42949672.95" controlName="月累计退票限额" value="<%=dg.format(jqxx.getYljtpxe()) %>" /></td>
          </tr>
          <tr>
          <td colspan="4"><font color="red">***以下信息为网络智能终端离线开票参数设置，如果为大唐税控收款机，则默认为0，不填 &nbsp;&nbsp;&nbsp;***</td></font>
          </tr>
          <tr>
           <td>离线开票张数：</td>
          <td><input type="text" style="width:40%" name="lxkpzs" required="required"   value="<%=(jqxx.getLxkpzs()) %>" /> 
            &nbsp;<font color="#FF0000"></font></td>
          <td>离线开票天数：</td>
          <td><input type="text" style="width:40%" name="lxkpts" required="required"   value="<%=jqxx.getLxkpts() %>" /> 
     </tr>     <tr>
          <td>离线开票总金额：</td>
          <td><input type="text" style="width:40%" name="lxkpzje" required="required"   value="<%=dg.format(jqxx.getLxkpzje()) %>" /></td>
          
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center"><img src="images/ico_01.gif" width="22" height="12" /> 点击下一步进入发卡！！ <br/>
            <br/>
            <input type="hidden" id="nsrwjbm" name="nsrwjbm" value="<%=nsrxx.getNsrwjbm() %>" />
            <input type="hidden" id="jqbh" name="jqbh" value="<%=jqxx.getJqbh() %>" />
            <input type="hidden" id="jumpFlag" name="jumpFlag" value="<%=jumpFlag %>" />
            <input type="button" name="btn1" value="上一步" onclick="fh();" style="cursor:hand;" />&nbsp;&nbsp;
            <input type="button" name="next_btn" value="下一步" onclick="next();" style="cursor:hand;" /></td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>
