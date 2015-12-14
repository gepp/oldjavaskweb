<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="java.util.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<%
String startdate = Util.getPreviousMonthFirst();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String enddate = Util.getPreviousMonthEnd();
 %>
<script language="javascript">
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}
function add(){
	var nsrwjbm = (document.getElementById('nsrwjbm').value);
	var startdate=(document.getElementById("startdate").value);
	var enddate=(document.getElementById("enddate").value);
	parent.document.getElementById('bywsbList').src = '/javaskweb/cxtj.do?op=toBywsbList&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate;
}
</script>
</head>
<body>
<div id="right">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList"><tr><td>纳税人微机编码：</td>
        <td>&nbsp;&nbsp;
        <input type="text" id="nsrwjbm"  maxlength="16" style="width: 270px;"><div id="auto"></div></td>
      </tr>
      <tr>
        <td width="18%">查询日期：</td>
        <td>开始时间：<input type="text" id="startdate" value="<%=startdate %>" name="startdate" style="width:15%" onClick="WdatePicker({isShowWeek:true})" readonly>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        截止时间：&nbsp;&nbsp;&nbsp;<input type="text" id="enddate" value="<%=enddate %>" name="enddate" style="width:15%" onClick="WdatePicker({isShowWeek:true})" readonly></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="button" name="add_btn" value=" 查 询 " onClick="add();">&nbsp;&nbsp;
          <input type="button" name="ret_btn" value=" 重 置 " onClick="window.location.reload();">
        </td>
      </tr>
    </table>
  
</div>
</body>
</html>
