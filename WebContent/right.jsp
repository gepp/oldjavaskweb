<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function switchImage(){
	if(document.getElementById('btn').src.search('hide_01.gif')!=-1){
		document.getElementById('btn').src = "images/hide.gif";
	}else{
		document.getElementById('btn').src = "images/hide_01.gif";
	}
}

function changeBar(){
	parent.switchMenuBar();
	switchImage();
}

function change_url(left,main){
	parent.document.all('leftFrame').src=left;
	location.href=main;
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="main">
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="45" height="34" align="center" bgcolor="#DAEC94"><img src="images/20090402_02.gif" width="31" height="34" /></td>
        <td width="291" bgcolor="#DAEC94" class="font14">欢迎使用江苏大唐税控通用管理平台</td>
        <td width="16"><img src="images/20090402_03.gif" width="16" height="34" /></td>
        <td width="*">&nbsp;</td>
      </tr>
    </table>
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="49%" align="right"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="sbManage" >
            <tr>
              <th width="5%" align="left"  class="sbManageTH">&nbsp;&nbsp;<img src="images/20090402_01.gif" width="13" height="13" />&nbsp;&nbsp;设备登记管理</th>
            </tr>
            <tr>
              <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="2" class="sbManageTD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="160" height="88" align="center"><a href="#"><img src="images/20090402_04.gif" width="127" height="89" border="0" /></a></td>
                          <td width="*">备注：请按照以下步骤完成税控收款机用　过程。用户税务信息登记→用登记→税控机和税控卡登记</td>
                        </tr>
                        <tr>
                          <td height="28" width="160" align="center"><span class="sbIcoWidth"><img src="images/20090402_05.gif" width="16" height="17" /></span></td>
                          <td>&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td colspan="2" class="sbManageTD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="88" width="160" align="center"><a href="#"><img src="images/20090402_06.gif" width="127" height="89" border="0" /></a></td>
                          <td width="*">备注：请按照以下步骤完成税控收款机用　过程。用户税务信息登记→用登记→税控机和税控卡登记</td>
                        </tr>
                        <tr>
                          <td height="28" width="160" align="center"><span class="sbIcoWidth"><img src="images/20090402_05.gif" width="16" height="17" /></span></td>
                          <td>&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td colspan="2" class="sbManageTD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="88" width="160" align="center"><a href="#"><img src="images/20090402_07.gif" width="127" height="89" border="0" /></a></td>
                          <td width="*">备注：请按照以下步骤完成税控收款机用　过程。用户税务信息登记→用登记→税控机和税控卡登记</td>
                        </tr>
                      </table></td>
                  </tr>
                </table></td>
            </tr>
          </table>
          <br />
        </td>
        <td width="2%" valign="top">&nbsp;</td>
        <td width="49%" valign="top"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"  class="sbManage" >
            <tr>
              <th width="5%" align="left"  class="sbManageTH">&nbsp;&nbsp;<img src="images/20090402_01.gif" width="13" height="13" />&nbsp;&nbsp;税控业务</th>
            </tr>
            <tr>
              <td height="152" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="2" class="sbManageTD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="160" height="88" align="center"><a href="#"><img src="images/20090402_08.gif" width="127" height="89" border="0" /></a></td>
                          <td width="*">备注：请按照以下步骤完成税控收款机用　过程。用户税务信息登记→用登记→税控机和税控卡登记</td>
                        </tr>
                        <tr>
                          <td height="28" width="160" align="center"><span class="sbIcoWidth"><img src="images/20090402_05.gif" width="16" height="17" /></span></td>
                          <td>&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td colspan="2" class="sbManageTD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="88" width="160" align="center"><a href="#"><img src="images/20090402_09.gif" width="127" height="89" border="0" /></a></td>
                          <td>备注：请按照以下步骤完成税控收款机用　过程。用户税务信息登记→用登记→税控机和税控卡登记</td>
                        </tr>
                        <tr>
                          <td height="28" width="160" align="center">&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                  <tr>
                    <td colspan="2" class="sbManageTD"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td height="88" align="center">&nbsp;</td>
                          <td >&nbsp;</td>
                        </tr>
                      </table></td>
                  </tr>
                </table></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>