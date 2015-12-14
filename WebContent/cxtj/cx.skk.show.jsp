<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.BigDecimal"%>
<%
HashMap CARDINFO = (HashMap)request.getAttribute("CARDINFO");
HashMap EF01 = (HashMap)CARDINFO.get("EF01");
HashMap EF02 = (HashMap)CARDINFO.get("EF02");
ArrayList EF041 = (ArrayList)CARDINFO.get("EF04");
ArrayList EF03=(ArrayList)CARDINFO.get("EF03");
System.out.println(EF03);
HashMap EF05 = (HashMap)CARDINFO.get("EF05");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
var old_tblname = 'EF01';
function changeTbl(tblname){
	if(tblname!=old_tblname){
		document.getElementById(tblname).className = 'listTitleGreen';
		document.getElementById(old_tblname).className = 'listTitleBlue';
		
		document.getElementById("tbl_"+tblname).style.display = "";
		document.getElementById("tbl_"+old_tblname).style.display = "none";
		
		/*if(tblname=='txshxx'){
			document.getElementById("tbl_txshxx_1").style.display = "";
		}
		else{
			document.getElementById("tbl_txshxx_1").style.display = "none";
		}*/
		old_tblname = tblname;
	}
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
  </div>
    <div id="main">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">税控卡信息</a></div></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="70%" valign="bottom"><div class="listTitleGreen" id="EF01"><a href="JAVASCRIPT:" onclick="changeTbl('EF01');">EF01</a></div>
            <div class="listTitleBlue" id="EF02"><a href="JAVASCRIPT:" onclick="changeTbl('EF02');">EF02</a></div>
            <div class="listTitleBlue" id="EF04"><a href="JAVASCRIPT:" onclick="changeTbl('EF04');">EF04</a></div>
            <div class="listTitleBlue" id="EF05"><a href="JAVASCRIPT:" onclick="changeTbl('EF05');">EF05</a></div>
          </td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_EF01">
        <tr>
          <td width="20%">开票截止日期：</td>
          <td width="30%"><%=(String)EF01.get("KPJZRQ") %>&nbsp;</td>
          <td width="20%">单张发票开票金额限额：</td>
          <td width="30%"><%=new BigDecimal((Long)EF01.get("DZLJJE")).divide(new BigDecimal(100))  %>&nbsp;</td>
        </tr>
        <tr>
          <td>发票累计金额限额：</td>
          <td><%=new BigDecimal((Long)EF01.get("YKPLJJE")).divide(new BigDecimal(100)) %>&nbsp;</td>
          <td>退票累计金额限额：</td>
          <td><%=new BigDecimal((Long)EF01.get("YTPLJJE")).divide(new BigDecimal(100))%>&nbsp;</td>
        </tr>
        <tr>
          <td>税种税目索引号：</td>
          <td><%=(String)EF01.get("SZSMINDE") %>&nbsp;</td>
          <td>明细申报标志：</td>
          <td><%=(String)EF01.get("MXSBBZ") %>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_EF02" style="display:none;">
        <tr>
          <td width="20%">卡类型标志：</td>
          <td width="30%"><%=(String)EF02.get("JQLX") %>&nbsp;</td>
          <td width="20%">注册标志：</td>
          <td width="30%"><%=(String)EF02.get("ZCBZ") %>&nbsp;</td>
        </tr>
        <tr>
          <td>税控卡号：</td>
          <td><%=(String)EF02.get("SKKH") %>&nbsp;</td>
          <td>机器编号：</td>
          <td><%=(String)EF02.get("JQBH") %>&nbsp;</td>
        </tr>
        <tr>
          <td>纳税人编码：</td>
          <td><%=(String)EF02.get("NSRWJDM") %>&nbsp;</td>
          <td>纳税人识别号：</td>
          <%
          String nsrsss=(String)EF02.get("NSRSBH");
          String newNsr=new String(Util.HexString2Bytes(nsrsss));
          %>
          <td><%=newNsr %>&nbsp;</td>
        </tr>
        <tr>
          <td>应用启用日期：</td>
          <td><%=(String)EF02.get("QYRQ") %>&nbsp;</td>
          <td>应用有效日期：</td>
          <td><%=(String)EF02.get("YXRQ") %>&nbsp;</td>
        </tr>
        <tr>
          <td>应用类型标识：</td>
          <td><%=(String)EF02.get("LXBS") %>&nbsp;</td>
          <td>应用版本：</td>
          <td><%=(String)EF02.get("YYBB") %>&nbsp;</td>
        </tr>
        <tr>
          <td>发卡方自定义FCI数据：</td>
          <td><%=(String)EF02.get("FCI") %>&nbsp;</td>
          <td>纳税人单位名称：</td>
          <td><%=(String)EF02.get("NSRMC") %>&nbsp;</td>
        </tr>
        <tr>
          <td>主管分局代码：</td>
          <td><%=(String)EF02.get("SWJGBM") %>&nbsp;</td>
          <td>申报方式：</td>
          <td><%=(String)EF02.get("SBFS") %>&nbsp;</td>
        </tr>
        <tr>
          <td>口令标志：</td>
          <td><%=(String)EF02.get("KLBZ") %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_EF04" style="display:none;">
<%
if(EF041!=null&&!EF041.isEmpty()){
	Iterator it = EF041.iterator();
	while(it.hasNext()){
		HashMap EF04 = (HashMap)it.next();
%>
		<tr>
          <td width="20%">开始时间：</td>
          <td width="30%"><%=(String)EF04.get("startdate") %>&nbsp;</td>
          <td width="20%">截止时间：</td>
          <td width="30%"><%=(String)EF04.get("enddate") %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票份数：</td>
          <td><%=(String)EF04.get("normalcnt") %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>退票份数：</td>
          <td><%=(String)EF04.get("backcnt") %>&nbsp;</td>
          <td>废票份数：</td>
          <td><%=(String)EF04.get("deposecnt") %>&nbsp;</td>
        </tr>
        <tr>
          <td>税目索引1：</td>
          <td><%=(String)EF04.get("index1") %>&nbsp;</td>
          <td>税目索引2：</td>
          <td><%=(String)EF04.get("index2") %>&nbsp;</td>
        </tr>
        <tr>
          <td>税目索引3：</td>
          <td><%=(String)EF04.get("index3") %>&nbsp;</td>
          <td>税目索引4：</td>
          <td><%=(String)EF04.get("index4") %>&nbsp;</td>
        </tr>
        <tr>
          <td>税目索引5：</td>
          <td><%=(String)EF04.get("index5") %>&nbsp;</td>
          <td>税目索引6：</td>
          <td><%=(String)EF04.get("index6") %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票金额1：</td>
          <td><%=(String)EF04.get("normalsum1") %>&nbsp;</td>
          <td>正常票金额2：</td>
          <td><%=(String)EF04.get("normalsum2") %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票金额3：</td>
          <td><%=(String)EF04.get("normalsum3") %>&nbsp;</td>
          <td>正常票金额4：</td>
          <td><%=(String)EF04.get("normalsum4") %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票金额5：</td>
          <td><%=(String)EF04.get("normalsum5") %>&nbsp;</td>
          <td>正常票金额6：</td>
          <td><%=(String)EF04.get("normalsum6") %>&nbsp;</td>
        </tr>
        <tr>
          <td>退票金额1：</td>
          <td><%=(String)EF04.get("backsum1") %>&nbsp;</td>
          <td>退票金额2：</td>
          <td><%=(String)EF04.get("backsum2") %>&nbsp;</td>
        </tr>
        <tr>
          <td>退票金额3：</td>
          <td><%=(String)EF04.get("backsum3") %>&nbsp;</td>
          <td>退票金额4：</td>
          <td><%=(String)EF04.get("backsum4") %>&nbsp;</td>
        </tr>
        <tr>
          <td>退票金额5：</td>
          <td><%=(String)EF04.get("backsum5") %>&nbsp;</td>
          <td>退票金额6：</td>
          <td><%=(String)EF04.get("backsum6") %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票总金额：</td>
          <td><%=(String)EF04.get("normalsum") %>&nbsp;</td>
          <td>退票总金额：</td>
          <td><%=(String)EF04.get("backsum") %>&nbsp;</td>
        </tr>
        <tr>
          <td>状态字：</td>
          <td><%=(String)EF04.get("state") %>&nbsp;</td>
          <td>MAC1：</td>
          <td><%=(String)EF04.get("mac") %>&nbsp;</td>
        </tr>
<%
	}
}
%>
        
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_EF05" style="display:none;">
        <tr>
          <th width="25%">发票有无标志</th>
          <th width="25%">发票代码</th>
          <th width="25%">发票起始号</th>
          <th width="25%">发票终止号</th>
        </tr>
<%
if(EF05!=null&&EF05.size()>0){
%>
        <tr>
            <td align="center"><%=(String)EF05.get("FPBZ") %>&nbsp;</td>
            <td align="center"><%=(String)EF05.get("FPDM") %>&nbsp;</td>
            <td align="center"><%=Integer.parseInt((String)EF05.get("QSH"),16) %>&nbsp;</td>
            <td align="center"><%=Integer.parseInt((String)EF05.get("JZH"),16) %>&nbsp;</td>
          </tr>
<%
}
%>
      </table>
    </div>
</div>
</body>
</html>