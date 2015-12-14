<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%
	Nsrxx nsrxx = (Nsrxx) request.getAttribute("nsrxx");
String fpdm=(String)request.getAttribute("fpjdm") ;
    if(fpdm==null||fpdm.equals(null)){
    	fpdm="";
    }
    Jcxx jcxx=Jcxx.getJcxx();
    Fp fp = new Fp();
	ArrayList alFp = fp.selectAll();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>增加发票</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">

function change_sl(){
	var fpqsh = document.all('fpqsh').value*1;
	var fpdw = document.all('fpdw').value*1;
	var fpzs = document.all('fpzs').value*1;
	if(fpqsh!=''&&fpdw!=''){
		document.all('fpsl').value = fpzs*fpdw;
		document.all('fpjzh').value = fpqsh+fpzs*fpdw-1;
	}
}

function div_close(){
	var bgObj=document.getElementById("bgDiv");
	var msgObj=document.getElementById("msgDiv");
    document.body.removeChild(bgObj);
	document.body.removeChild(msgObj);
}

function add(){
	var frm = document.form1;
	//if(confrm('确定添加发票？')){
		if(validator(frm)){
			//alert(document.getElementById('fpdm').value);
			var fpsl = document.getElementById('fpsl').value*1;
			var fpdw = document.getElementById('fpdw').value*1;
			if(document.getElementById("fpjy").checked){
				document.getElementById("fpjy1").value=1;
			}
			var num = fpsl/fpdw;
			//var fpdmVal = document.getElementById("fpdm").value;
			//frm.submit();
			if(num>200){
				alert('每卷不能超过200张');
			}
			else if(document.getElementById("fpdm").value==''){
				alert("请输入发票代码");
			}
			else{
				//alert(document.getElementById('fpdm').value);
				frm.submit();
				//alert(document.getElementById('fpdm').value);
			}
		}
	//}
}

function error(msg){
	alert(msg);
}

function success(){
	var nsrwjbm = document.getElementById('nsrwjbm').value;
	opener.window.location.href='/javaskweb/fpService.do?op=fpListWlkp&nsrwjbm='+nsrwjbm;
	window.close();
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
    <div id="main">
      <form action="/javaskweb/fpService.do?op=addActionWlkp" name="form1" method="post" target="iframe" >
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
          <tr>
            <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
          <tr>
            <td width="15%">纳税人微机编码：</td>
            <td>&nbsp;<%=nsrxx.getNsrwjbm()%></td>
            <td width="15%">纳税人识别号：</td>
            <td width="35%">&nbsp;<%=nsrxx.getNsrsbh()%></td>
          </tr>
          <tr>
            <td>纳税人名称：</td>
            <td>&nbsp;<%=nsrxx.getNsrmc()%></td>
            <td>经营地址：</td>
            <td>&nbsp;<%=nsrxx.getJydz()%></td>
          </tr>
          <tr>
            
          </tr>
        </table>
        <br />
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
          <tr>
            <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">输入发票领购信息</a></div></td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
          <tr>
           <td width="15%">发票代码：</td>
            <td><input type="text" id="fpdm" name="fpdm" size="15" required="required" dataLength="1,12" controlName="发票代码" maxlength="12" value="<%=fpdm%>" />
               <input type="checkbox" id="fpjy" name="fpjy" checked="checked">发票校验</input> &nbsp;</td>
          </tr>
          <tr>
            <td width="15%">发票起始号码：</td>
            <td><input type="text" name="fpqsh" id="fpqsh" size="15" onblur="change_sl();" required="required" dataLength="1,8" maxlength="8" controlName="发票起始号码"/>
            &nbsp;</td>
          </tr>
          <tr>
            <td width="15%">发票截止号码：</td>
            <td><input type="text" name="fpjzh" id="fpjzh" size="15"  readonly/>
              &nbsp;</td>
          </tr>
          <tr>
            <td width="15%">发票单位(卷)：</td>
            <td><input type="text" name="fpdw" id="fpdw" size="15" onblur="change_sl();" value="1" required="required" controlName="发票单位(卷)"/>
              &nbsp;</td>
          </tr>
          <tr>
            <td width="15%">发票数量：</td>
            <td><input type="text" name="fpsl" id="fpsl" size="15" readonly/>
           
              &nbsp;</td>
              
          </tr>
          <tr>
           <td width="15%">每卷发票张数：</td>
            <td><input id="fpzs" type="text" name="fpzs"  size="15" value="<%=jcxx.getFpzs()%>"/>
           
              &nbsp;</td>
          </tr>
             <tr>
           <td width="15%">发票票种：</td>
            <td>
                  <select id="fppz" name="fppz">
                  <%
                   for(int i=0;i<alFp.size();i++){
                	   Fp fppz=(Fp)alFp.get(i);
                   
                  %>
                  <option value="<%=fppz.getFpbm() %>"><%=fppz.getFpmc() %></option>
                  <%
                   }
                  %>
                  </select>
           
              &nbsp;</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
          <tr>
            <td height="40" align="center"><img src="images/ico_01.gif" width="22" height="12" /> 确认发票领购！！ <br/>
              <br/>
              <input type="hidden" id="nsrwjbm" name="nsrwjbm" value="<%=nsrxx.getNsrwjbm()%>" />
              <input type="hidden" id="fpjy1" name="fpjy1" value="0" />
              <input type="button" name="next_btn" value=" 确 认 " onclick="add();" />
              <input type="button" name="ret_btn" value=" 关 闭 " onclick="window.close();" />
            </td>
          </tr>
        </table>
      </form>
      <iframe src="" name="iframe" id="iframe" frameborder="0" width="0" height="0"></iframe>
    </div>
</div>
</body>
</html>