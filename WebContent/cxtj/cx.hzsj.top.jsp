<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<script language="javascript" src="../images/Calendar2.js"></script>
<script type="text/javascript" src="../images/CheckBrower.js"></script>
<script type="text/javascript">
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}
function add(){
	var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
	var startdate = trim(document.getElementById('startdate').value);
	var enddate = trim(document.getElementById('enddate').value);
	if(nsrwjbm==''){
		alert('请输入纳税人微机编码');
	}
	else if (startdate==''){
		alert('请输入申报汇总开始时间');
	}
	else if (enddate==''){
		alert('请输入申报汇总截止时间');
	}
	else{
		parent.document.all('mainFrame').src = '/javaskweb/CxHzsjServlet?method=query&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate;
	}
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
        <td width="18%">申报汇总时间：</td>
        <td>开始时间：<input type="text" id="startdate" name="startdate" style="width:15%" onFocus="winOpen();" readonly>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        截止时间：&nbsp;&nbsp;&nbsp;<input type="text" name="enddate" style="width:15%" onClick="winopen_end();" readonly></td>
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