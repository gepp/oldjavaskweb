<%@ page contentType = "text/html;charset=utf-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登陆</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<link href="images/login.css" rel="stylesheet" type="text/css" />

<script language="javascript">
$(document).ready(function(){
document.getElementById('username').focus();

});
function login(){
	var USER_NAME = document.getElementById('username').value;
	var PASSWORD = document.getElementById('password').value;
	
	if(USER_NAME==''){
		alert('请输入用户名');
	}
	else if(PASSWORD==''){
		alert('请输入密码');
	}
	else{
		document.forms[0].submit();
	}
}

function add(){
	if(event.keyCode==13){
		login();
	}
}
</script>
</head>
  <body onkeydown="add();">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="login_leftBg">&nbsp;</td>
      <td width="439" height="287" valign="top"><table width="439" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="8"><img src="images/login_03.gif" width="8" height="55" /></td>
            <td width="266" class="login_bg"><img src="images/login_logo.gif" width="212" height="33" /></td>
            <td width="158" align="center" valign="bottom" class="login_bg"><img src="images/login_06.gif" width="82" height="28" /></td>
            <td width="7"><img src="images/login_04.gif" width="7" height="55" /></td>
          </tr>
          <tr>
            <td height="42" bgcolor="#D2E2F2"><img src="images/login_07.gif" width="7" height="42" /></td>
            <td colspan="2" bgcolor="#D2E2F2" ><img src="images/login_09.gif" width="209" height="30" /></td>
            <td align="right" bgcolor="#D2E2F2"><img src="images/login_08.gif" width="6" height="42" /></td>
          </tr>
        </table>
        <table width="439" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="login_bg01">
            <form action="/javaskweb/loginTy.do?op=toLogin" method="post">
			            <table width="400" border="0" cellpadding="0" cellspacing="0" class="login">
                <tr>
                  <td width="193" height="38">&nbsp;</td>
                  <td width="50">用户名：</td>
                  <td width="157"><input type="text" name="username" class="loginInput" id="username" /></td>
                </tr>
                <tr>
                  <td height="38">&nbsp;</td>
                  <td>密&nbsp;&nbsp;码：</td>
                  <td><input type="password" name="password" class="loginInput" id="password"/></td>
                </tr>
                <tr>
                  <td height="38">&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><img src="images/ico_login.gif" border="0" style="cursor:hand;" onClick="login();"/> <img src="images/ico_exit.gif" border="0" style="cursor:hand;" onClick="window.close();"/> </td>
                </tr>
              </table>
			</form>
              </td>
          </tr>
        </table></td>
      <td class="login_rightBg">&nbsp;</td>
    </tr>
    <tr>
      <td >&nbsp;</td>
      <td height="10" valign="top"><img src="images/login_11.gif" width="439" height="57" /></td>
      <td >&nbsp;</td>
    </tr>
  </table>
  </body>
</html>