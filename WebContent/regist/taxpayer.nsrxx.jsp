<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
HttpSession session1 = request.getSession(false);
HashMap hmNsrxx = (HashMap)session1.getAttribute("hmNsrxx");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/Calendar.js" ></script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td width="35%" style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayerServlet?method=import" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;注册登记</td>
        <td><div class="userTitleRed">
        <ul>
          <li><a href="#" class="black">税务登记信息</a></li>
        </ul>
      </div>
      <div class="userTitleGreen"><a href="#" class="black">税控装置信息录入</a></div>
      <div class="userTitleGreen"><a href="#" class="black">发卡</a></div>
    </td>
      </tr>
    </table>      
  </div>
  <div id="main">
    <form name="form1" action="/javaskweb/taxpayerServlet?method=addNsr" method="post">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="4">纳税户信息</th>
        </tr>
        <tr>
          <td width="18%">纳税人微机编码：</td>
          <td><%=(String)hmNsrxx.get("nsrwjbm") %>&nbsp;</td>
          <td width="18%">纳税人识别号：</td>
          <td width="32%"><%=(String)hmNsrxx.get("nsrsbh") %>&nbsp;</td>
        </tr>
        <tr>
          <td>纳税人名称：</td>
          <td><%=(String)hmNsrxx.get("nsrmc") %>&nbsp;</td>
          <td>纳税人联系方式：</td>
          <td>13914710516&nbsp;</td>
        </tr>
        <tr>
          <td>经营地址：</td>
          <td colspan="3">经营地址&nbsp;</td>
        </tr>
        <tr>
          <td>法人代表：</td>
          <td><%=(String)hmNsrxx.get("frdb") %>&nbsp;</td>
          <td>注册类型：</td>
          <td><%=(String)hmNsrxx.get("zclxmc") %>&nbsp;</td>
        </tr>
        <tr>
          <td>所属行业：</td>
          <td><%=(String)hmNsrxx.get("hymc") %>&nbsp;</td>
          <td>经营项目：</td>
          <td><%=(String)hmNsrxx.get("hymxmc") %>&nbsp;</td>
        </tr>
        <tr>
          <td>征收方式：</td>
          <td><%=(Integer)hmNsrxx.get("zsfs")==0?"查账征收户":"核定户" %>&nbsp;</td>
          <td>月核定营业额（元）：</td>
          <td><%=(Double)hmNsrxx.get("yhde")%></td>
        </tr>
        <tr>
          <td>主管科(所)：</td>
          <td><%=(String)hmNsrxx.get("swjgmc") %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>办税员：</td>
          <td><%=(String)hmNsrxx.get("bsy") %>&nbsp;</td>
          <td>税收管理员:</td>
          <td><%=(String)hmNsrxx.get("ssgly") %>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="5">税种税目信息（红色为纳税人拥有税种税目，而本平台没有启用）</th>
        </tr>
        <tr>
          <th width="15%">税种</th>
          <th width="15%">税目编码</th>
          <th width="30%">税目名称</th>
          <th width="30%">税目简称</th>
          <th width="10%">税率（%）</th>
        </tr>
<%
ArrayList nsrszsm = (ArrayList)hmNsrxx.get("nsrszsm");
String qysmbmStr = (String)hmNsrxx.get("qysmbmStr");
int i=0;
if(nsrszsm!=null&&!nsrszsm.isEmpty()){
	Iterator it = nsrszsm.iterator();
	
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		String style = "";
		if(qysmbmStr==null||"".equals(qysmbmStr)){
			style = "style=\"color:#FF0000\"";
		}
		else{
			if(qysmbmStr.indexOf((String)hm.get("smbm"))<0){
				style = "style=\"color:#FF0000\"";
			}
			else{
				i++;
			}
		}
%>
		<tr>
		  <td align="center" <%=style %> ><%=(String)hm.get("szbm") %></td>
          <td align="center" <%=style %> ><%=(String)hm.get("smbm") %></td>
          <td align="center" <%=style %> ><%=(String)hm.get("smmc") %></td>
          <td align="center" <%=style %> ><%=(String)hm.get("smjc") %></td>
          <td align="center" <%=style %> ><%=(Double)hm.get("sl") %></td>
        </tr>
<%
	}
}
%>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center">
<%
if(i==0){
%>
	<br><font color="red"> 您没有平台允许税种税目，请先去征管鉴定</font><br>
<%
}
else{
%>
		    <input type="submit" name="next_btn" value="下一步" style="cursor:hand;"/>
            &nbsp;&nbsp;&nbsp;&nbsp;
<%
}
%>
            
            <input type="button" name="ret_btn" value="返 回" onclick="location.href='/javaskweb/taxpayerServlet?method=import';" />
          </td>
        </tr>
      </table>
    </form>
  </div>
</div>
</body>
</html>