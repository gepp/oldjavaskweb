<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}
function add(){
	var swjgbm = trim(document.getElementById('swjgbm').value);
	var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
	
	parent.document.all('mainFrame').src = '/javaskweb/cxtj.do?op=toNsrxxList&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm;
}
</script>
</head>
<body>
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="18%">纳税人微机编码：</td>
        <td><input type="text" name="nsrwjbm" id="nsrwjbm" maxlength="16" style="width: 270px;"><div id="auto"></div></td>
      </tr>
 
		
    
      <tr>
        <td>税务机关：</td>
        <td><input type="text" name="swjgmc" id="swjgmc" readonly style="width:50%" onClick="window.open('/javaskweb/cxtj.do?op=toNsrxxSwjg','cxswjg','width=600,height=600,top=200,left=200,scrollbars=yes');">&nbsp;&nbsp;
        <input type="button" name="swjg_btn" value="选择税务机关" onClick="window.open('/javaskweb/cxtj.do?op=toNsrxxSwjg','cxswjg','width=600,height=600,top=200,left=200,scrollbars=yes');">
        <input type="hidden" id="swjgbm" name="swjgbm">
        </td>
      </tr>
       
      <tr>
        <td colspan="2" align="center">
          <input type="button" name="add_btn" value=" 查 询 " onClick="add();">&nbsp;&nbsp;
          <input type="button" name="ret_btn" value=" 重 置 " onClick="window.location.reload();">
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
