<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../images/CheckBrower.js"></script>
<script language="javascript">
	function add()
	{
		var jqbh = document.getElementById('jqbh').value;
		//alert(jqbh);
		var fpdm = document.getElementById('fpdm').value;
		//alert(fpdm);
		var fpqsh = document.getElementById('fpqsh').value;
		//alert(fpqsh);
		var fpjzh = document.getElementById('fpjzh').value;
		//alert(fpjzh);
		if(jqbh==''&&fpdm==''&&fpqsh==''&&fpjzh==''){
			alert('请输入机器编号，发票代码，发票起始号和发票终止号');
		}
		else{
			//alert(parent.document.all('mainFrame').src='/javaskweb/SkkFZServlet?method=query&jqbh='+jqbh);
			parent.document.all('mainFrame').src = '/javaskweb/SkkFZServlet?method=query&jqbh='+jqbh+'&fpdm='+fpdm+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh;
		}
	}
</script>
</head>

<body>
	<div id="right">
  		<div id="main">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
        <td width="18%">机器编号：</td>
        <td><input type="text" name="jqbh" id="jqbh" maxlength="16"></td>
        <div id="auto"></div>
      </tr>
      <tr>
        <td width="18%">发票号码段：</td>
        <td>发票代码：<input type="text" id="fpdm" name="fpdm" style="width:15%" maxlength="12" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        发票起始号：<input type="text" id="fpqsh" name="fpqsh" style="width:15%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        发票终止号：<input type="text" id="fpjzh" name="fpjzh" style="width:15%" ></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="button" name="add_btn" value=" 查 询 " onclick="add();"/>&nbsp;&nbsp;
          <input type="button" name="ret_btn" value=" 重 置 " onclick="window.location.reload();"/>
        </td>
        </table>
  		</div>
 	</div>
</body>
</html>