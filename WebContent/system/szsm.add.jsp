<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
ArrayList alFp = (ArrayList)request.getAttribute("alFp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发票票种增加</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/validator.js"></script>
<script language="javascript">
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;系统管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/system.do?op=toPmList" class="nav">品目设置</a>&gt;&gt;&nbsp;&nbsp;新增</td>
      </tr>
    </table>
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/system.do?op=pmAddAction" method="post" onSubmit="return validator(this)">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableForm">
       <tr>
          <td width="18%">品目索引：</td>
          <td><input type="text" style="width:30%" name="pmsy" required="required" dataType="number" controlName="品目索引" maxlength="8" /></td>
        </tr>
        <tr>
          <td width="18%">品目编码：</td>
          <td><input type="text" style="width:30%" name="smbm" required="required" dataType="number" controlName="品目编码" maxlength="8" /></td>
        </tr>
        <tr>
          <td>品目名称：</td>
          <td><input type="text" style="width:30%" name="smmc" required="required" controlName="品目名称" /></td>
        </tr>
        <tr>
          <td>品目简称：</td>
          <td><input type="text" style="width:30%" name="smjc" required="required" controlName="品目简称" maxlength="10" /></td>
        </tr>
        <tr>
          <td>税率（%）：</td>
          <td><input type="text" style="width:30%" name="sl" required="required" dataType="float" controlName="税率"/></td>
        </tr>
        <tr>
          <td>对应票种：</td>
          <td><select name="fpbm" id="select" required="required" controlName="对应票种">
            <option value="">--请选择对应票种--</option>
<% 
if(alFp!=null&&!alFp.isEmpty()){
	Iterator it1 = alFp.iterator();
	while(it1.hasNext()){
		Fp fp = (Fp)it1.next();
		
%>
			<option value="<%=fp.getFpbm() %>"><%=fp.getFpmc() %></option>
<%
	}
}
%>
          </select></td>
        </tr>
        
        <tr>
          <td>品目状态：</td>
          <td>
              <input type="radio" name="status" value="1" id="status_0" checked="checked" />
              启用
              <input type="radio" name="status" value="0" id="status_1" />
              未启用
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableFormFooter">
        <tr>
          <td height="40" align="center"><input type="submit" name="next_btn" value="确 定" style="cursor:hand;"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="ret_btn" value="返 回" onclick="window.location.href='/javaskweb/system.do?op=toPmList';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>