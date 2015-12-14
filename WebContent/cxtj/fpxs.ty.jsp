<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
HashMap hmTy = (HashMap)request.getAttribute("hmTy");
ArrayList alXm = (ArrayList)hmTy.get("alXm");
DecimalFormat dg = new DecimalFormat("0.00");

int kplx = Integer.parseInt((String)hmTy.get("kplx"));
String hjzjezw = "";
if(kplx==1){
	hjzjezw = Util.numtochinese((String)hmTy.get("hjzje"));
}
else if(kplx==2){
	hjzjezw = "退  "+Util.numtochinese((String)hmTy.get("hjzje"));
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>通用发票开具信息</title>
<style type="text/css">
<!--
body,td,th {
	font-size: 12px;
}
-->
</style></head>
<body>
<table width="800" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td width="934" style=" background-image:url(images/ty.gif); height:580px; background-repeat:no-repeat;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="90" colspan="5">&nbsp;</td>
      </tr>
      <tr>
        <td width="2%" height="20">&nbsp;</td>
        <td width="9%">&nbsp;</td>
        <td width="23%" align="left"><%=(String)hmTy.get("kprq") %>&nbsp;</td>
        <td width="11%" align="left">&nbsp;</td>
        <td width="56%" align="left"><%=(String)hmTy.get("hymc") %>&nbsp;</td>
      </tr>
      <tr>
        <td height="40">&nbsp;</td>
        <td height="40" colspan="4" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="14%" height="30" align="left">收款方名称：</td>
              <td width="35%" align="left"><%=(String)hmTy.get("nsrmc") %>&nbsp;</td>
              <td width="12%" align="left">机打代码：</td>
              <td width="39%" align="left"><%=(String)hmTy.get("fpdm") %>&nbsp;</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td height="40">&nbsp;</td>
        <td height="40" colspan="4" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="15%" height="30" align="left">税务登记证号：</td>
              <td width="34%" align="left"><%=(String)hmTy.get("nsrsbh") %>&nbsp;</td>
              <td width="12%" align="left">机打号码：</td>
              <td width="39%" align="left"><%=(String)hmTy.get("fphm") %>&nbsp;</td>
            </tr>
            <tr>
              <td height="30" align="left">付款方名称：</td>
              <td colspan="3" align="left"><%=(String)hmTy.get("fkdw") %>&nbsp;</td>
            </tr>
          </table></td>
      </tr>
      <tr>
      
      <td height="40">&nbsp;</td>
      <td height="250" colspan="4" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="60%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="25%" height="30" align="left">项目名称</td>
                  <td width="25%" height="30" align="left">单价</td>
                  <td width="25%" height="30" align="left">数量</td>
                  <td align="left">项目金额</td>
                </tr>
                <%
if(alXm!=null&&!alXm.isEmpty()){
	Iterator it = alXm.iterator();
	while(it.hasNext()){
		HashMap hmXm = (HashMap)it.next();
%>
                <tr>
                  <td width="25%" height="30" align="left"><%=(String)hmXm.get("xmmc") %>&nbsp;</td>
                  <td width="25%" height="30" align="left"><%=(String)hmXm.get("dj") %>&nbsp;</td>
                  <td width="25%" height="30" align="left"><%=(String)hmXm.get("sl") %>&nbsp;</td>
                  <td align="left"><%=(String)hmXm.get("je") %>&nbsp;</td>
                </tr>
                <%
	}
}
%>
 <tr>
                  <td width="25%" height="30" align="left">折扣总金额：<%=hmTy.get("zkzje") %>&nbsp;</td>
                  <td width="25%" height="30" align="left">&nbsp;</td>
                  <td width="25%" height="30" align="left">&nbsp;</td>
                  <td align="left">&nbsp;</td>
                </tr>
              </table></td>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="50%" align="left">备注</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td colspan="2"><%=(String)hmTy.get("bz")==null?"":(String)hmTy.get("bz") %>&nbsp;</td>
                </tr>
              </table></td>
          </tr>
          
          <!----------------->
        </table></td>
      </tr>
      
      <tr>
        <td height="80">&nbsp;</td>
        <td colspan="4" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="21%" height="30" align="left">金额合计（大写）：</td>
              <td width="28%" align="left"><%=hjzjezw %>&nbsp;</td>
              <td width="16%" align="left">（小写）</td>
              <td width="35%" align="left"><%=(String)hmTy.get("hjzje") %>&nbsp;</td>
            </tr>
            <!--循环此行 作为项目-->
            <tr>
              <td height="30" align="left">税控码：</td>
              <td align="left"><%=(String)hmTy.get("skm") %>&nbsp;</td>
              <td align="left">机器编号：</td>
              <td align="left"><%=(String)hmTy.get("jqbh") %>&nbsp;</td>
            </tr>
            <tr>
              <td height="30" align="left">收款员：</td>
              <td align="left"><%=(String)hmTy.get("sky") %>&nbsp;<br /></td>
              <td align="left"><p>主管税务机关：</p></td>
              <td align="left"><%=(String)hmTy.get("swjgbm") %>&nbsp;</td>
            </tr>
            <!----------------->
          </table></td>
      </tr>
    </table></td>
  </tr>
  
</table>
</body>
</html>
