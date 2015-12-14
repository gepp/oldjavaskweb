<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList xxfsList = (ArrayList)request.getAttribute("xxfsList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息发送</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function addZg(){
	window.location.href='/javaskweb/fpServlet?method=getFromZG';
}

function add(){
	window.location.href='/javaskweb/taxpayer.do?op=toXxfsWlkpAdd';
}

function edit(guid){
	window.location.href='/javaskweb/taxpayer.do?op=toXxfsWlkpEdit&guid='+guid;
}

function del(guid){
	if(confirm('确定删除该消息？')){
		window.location.href='/javaskweb/taxpayer.do?op=xxfsDelete&guid='+guid;
	}
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">消息发送管理</a></td>
      <td align="right">  <!--  <input type="button" name="btn" value="从征管获取" style="cursor:hand;" onclick="window.location.href='/javaskweb/system.do?op=getPzFromZg';" /> -->
       &nbsp;&nbsp;<input type="button" name="btn" value="新 增" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="25%">消息发送时间</th>
        <th width="25%">消息内容</th>
        <th width="*">消息操作员</th> 
        
         <th width="*">操作</th> 
      </tr>
<%
if(xxfsList!=null&&!xxfsList.isEmpty()){
	Iterator it = xxfsList.iterator();
	while(it.hasNext()){
		Xxfs xxfs = (Xxfs)it.next();
%>
      <tr>
        <td><%=xxfs.getCjsj()%>&nbsp;</td>
        <td><%=xxfs.getXxnr() %>&nbsp;</td>
        <td><%=xxfs.getCjz() %>&nbsp;</td>
        <td><input type="button" name="btn" value="修 改" onclick="edit('<%=xxfs.getGuid() %>');" style="cursor:hand;" />&nbsp;&nbsp;<input type="button" name="btn" value="删 除" onclick="del('<%=xxfs.getGuid() %>');" style="cursor:hand;" /> </td>
      </tr>
<%
	}
}
%>
    </table>
  </div>
</div>
</body>
</html>