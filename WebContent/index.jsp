<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>税控发票管理平台</title>
<script language="javascript">
var x = 162;
function switchMenuBar(){
	obj = document.getElementById('main');
	arrRows = obj.cols.split(',');
	if(arrRows[0] == 0){
		x = 162;
		switchMBarDown();
	}else{
		x = 162;
		switchMBarUp();
	}
}
function switchMBarUp(){
	obj = document.getElementById('main');
	arrRows = obj.cols.split(',');
	a = Math.round(parseInt(arrRows[0])/2);
	arrRows[0] = parseInt(arrRows[0])-a;
	newRows = arrRows[0]+','+arrRows[1];
	obj.cols = newRows;
	if(arrRows[0]!=0){
		setTimeout("switchMBarUp()",50);
	}else{
		return;
	}
}
function switchMBarDown(){
	x = Math.round(x/2);
	obj = document.getElementById('main');
	arrRows = obj.cols.split(',');
	arrRows[0] = parseInt(arrRows[0])+x;
	newRows = arrRows[0]+','+arrRows[1];
	obj.cols = newRows;
	if(arrRows[0]<162){
		setTimeout("switchMBarDown()",50);
	}else{
		return;
	}
}
</script>
</head>
<frameset rows="116,*" cols="*" frameborder="no" border="0" framespacing="0">
<frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />  
  <frameset cols="163,*" frameborder="no" border="0" framespacing="0" id="main">
    <frame src="leftRegist.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
 <frame src="right.jsp" name="mainFrame" scrolling="yes" id="mainFrame" title="mainFrame" />
  </frameset>
</frameset>
<noframes><body>
</body>
</noframes>
</html>
