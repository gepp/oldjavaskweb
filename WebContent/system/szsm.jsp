<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alSzsm = (ArrayList)request.getAttribute("alSzsm");
ArrayList alFp = (ArrayList)request.getAttribute("alFp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
function add(){
	window.location.href='szsm.add.html';
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">品目设置</a></td>
        <td ><input type="button" name="btn" value="新 增" style="cursor:hand;" onclick="window.location.href='/javaskweb/system.do?op=toPmAdd';" />&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
  <form name="form1" action="/javaskweb/system.do?op=pmEditAction" method="post" onSubmit="return validator(this)">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableList">
      <tr>
        <th width="6%">品目索引</th>
        <th width="8%">品目编码</th>
        <th width="14%">品目名称</th>
        <th width="10%">品目简称</th>
        <th width="6%">税率（%）</th>
        <th width="*">对应票种</th>
        
        <th width="8%">品目状态</th>
      </tr>
<%
if(alSzsm!=null&&!alSzsm.isEmpty()){
	Iterator it = alSzsm.iterator();
	while(it.hasNext()){
		Szsm szsm = (Szsm)it.next();
%>
	  <tr>
        <td><%=szsm.getSmsy() %>&nbsp;</td>
        <td><%=szsm.getSmbm() %>&nbsp;</td>
        <td><%=szsm.getSmmc() %>&nbsp;</td>
        <td><%=szsm.getSmjc() %>&nbsp;</td>
        <td><%=szsm.getSl() %>&nbsp;</td>
        <td><select name="fpbm_<%=szsm.getSmbm() %>" id="select" required="required" controlName="对应票种">
            <option value="">--请选择对应票种--</option>
<% 
if(alFp!=null&&!alFp.isEmpty()){
	Iterator it1 = alFp.iterator();
	while(it1.hasNext()){
		Fp fp = (Fp)it1.next();
		
%>
			<option value="<%=fp.getFpbm() %>" <% if(szsm.getFpbm()!=null){if(szsm.getFpbm().equals(fp.getFpbm())){out.println("selected");}}%>><%=fp.getFpmc() %></option>
<%
	}
}
%>
          </select></td>
          
        <td>
          <select name="status_<%=szsm.getSmbm() %>" id="select">
            <option value="1" <% if(szsm.getStatus()==1){out.println("selected");}%>>启用</option>
            <option value="0" <% if(szsm.getStatus()==0){out.println("selected");}%>>未启用</option>
          </select>
        </td>
      </tr>
<%
	}
}
%>
      <tr>
        <td align="center" colspan="10" style="height:40px;"><input type="submit" name="btn" value=" 保 存 " /></td>
      </tr>
    </table>
    </form>
  </div>
</div>
</body>
</html>
