<%@ page contentType = "text/html;charset=utf-8" language="java"%>
<%@ page import="java.util.*" %>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
ArrayList alFpjmx = (ArrayList)request.getAttribute("alFpjmx");
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
DecimalFormat dg = new DecimalFormat("0.00");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>纳税人信息详情</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function switchImage(){
	if(document.getElementById('btn').src.search('hide_01.gif')!=-1){
		document.getElementById('btn').src = "images/hide.gif";
	}else{
		document.getElementById('btn').src = "images/hide_01.gif";
	}
}

function changeBar(){
	parent.switchMenuBar();
	switchImage();
}

var old_tblname = 'xmjcxx';
function changeTbl(tblname){
	if(tblname!=old_tblname){
		document.getElementById(tblname).className = 'listTitleGreen';
		document.getElementById(old_tblname).className = 'listTitleBlue';
		
		document.getElementById("tbl_"+tblname).style.display = "";
		document.getElementById("tbl_"+old_tblname).style.display = "none";
		document.getElementById("tbl_"+tblname+"_1").style.display = "";
		document.getElementById("tbl_"+old_tblname+"_1").style.display = "none";
		
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
    <div id="main">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="70%" valign="bottom"><div class="listTitleGreen" id="xmjcxx"><a href="JAVASCRIPT:" onclick="changeTbl('xmjcxx');">纳税人基本信息</a></div>
            <div class="listTitleBlue" id="lphzxx"><a href="JAVASCRIPT:" onclick="changeTbl('lphzxx');">机器信息</a></div>
            <div class="listTitleBlue" id="lpxxxx"><a href="JAVASCRIPT:" onclick="changeTbl('lpxxxx');">发票领购信息</a></div>
          <td>&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_xmjcxx">
        <tr>
          <td width="18%">纳税人微机编码：</td>
          <td>&nbsp;<%=nsrxx.getNsrwjbm() %></td>
          <td width="18%">纳税人识别号：</td>
          <td width="32%">&nbsp;<%=nsrxx.getNsrsbh() %></td>
        </tr>
        <tr>
          <td>纳税人名称：</td>
          <td>&nbsp;<%=nsrxx.getNsrmc() %></td>
          <td>经营地址：</td>
          <td>&nbsp;<%=nsrxx.getJydz() %></td>
        </tr>
        <tr>
          <td>法人代表：</td>
          <td>&nbsp;<%=nsrxx.getFrdb() %></td>
          <td>注册类型：</td>
          <td>&nbsp;<%=nsrxx.getZclxmc() %></td>
        </tr>
        <tr>
          <td>所属行业：</td>
          <td>&nbsp;<%=nsrxx.getHymc() %></td>
          <td>经营项目：</td>
          <td>&nbsp;<%=nsrxx.getHymxmc() %></td>
        </tr>
        <tr>
          <td>征收方式：</td>
          <td>&nbsp;<%=nsrxx.getZsfs()==1?"核定户":"查账征收户" %></td>
          <td>月核定营业额（元）：</td>
          <td>&nbsp;
            <%=dg.format(nsrxx.getYhde()) %>
          </td>
        </tr>
        <tr>
          <td>主管科(所)：</td>
          <td>&nbsp;<%=nsrxx.getSwjgmc() %></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>办税员：</td>
          <td>&nbsp;<%=nsrxx.getBsy() %></td>
          <td>税收管理员:</td>
          <td>&nbsp;<%=nsrxx.getSsgly() %></td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;<strong>税种税目信息</strong></td>
        </tr>
        <tr>
          <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1">
              <tr>
                <th width="25%">税目编码</th>
                <th width="25%">税目名称</th>
                <th width="25%">税目简称</th>
                <th width="25%">税率</th>
              </tr>
<%
ArrayList alNsrszsm = nsrxx.getNsrszsm();
if(alNsrszsm!=null&&!alNsrszsm.isEmpty()){
	Iterator it_szsm = alNsrszsm.iterator();
	while(it_szsm.hasNext()){
		HashMap hm_szsm = (HashMap)it_szsm.next();
%>
              <tr>
                <td align="center"><%=(String)hm_szsm.get("smbm") %></td>
                <td align="center"><%=(String)hm_szsm.get("smmc") %></td>
                <td align="center"><%=(String)hm_szsm.get("smjc")%></td>
                <td align="center"><%=(Double)hm_szsm.get("sl") %></td>
              </tr>
              <%
	}
}
%>
            </table></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableListBottom" id="tbl_xmjcxx_1">
        <tr>
          <td height="40" align="center"><input type="button" name="lphzxx_btn" value="机器信息" onclick="changeTbl('lphzxx');" style="cursor:hand;" />
            &nbsp;&nbsp;
            <input type="button" name="lpxxxx_btn" value="发票领购信息" onclick="changeTbl('lpxxxx');" style="cursor:hand;" />
            &nbsp;&nbsp;
            <input type="button" name="ret_btn" value=" 返 回 " onclick="window.location.href='cxtj/cx.nsrxx.list1.jsp';" style="cursor:hand;" />
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_lphzxx" style="display:none;">
        <%
ArrayList alNsrjqxx = nsrxx.getNsrjqxx();
if(alNsrjqxx!=null&&!alNsrjqxx.isEmpty()){
	Iterator it_jqxx = alNsrjqxx.iterator();
	while(it_jqxx.hasNext()){
		Jqxx jqxx = (Jqxx)it_jqxx.next();
		ArrayList jq_szsm = (ArrayList)jqxx.getJqszsm();
%>
        <tr>
          <td width="18%">机器型号：</td>
          <td>&nbsp;<%=jqxx.getJqxhmc() %></td>
          <td width="18%">机器编号：</td>
          <td width="32%">&nbsp;<%=jqxx.getJqbh() %></td>
        </tr>
        <tr>
          <td>税控卡号：</td>
          <td>&nbsp;<%=jqxx.getSkkh() %></td>
          <td>用户卡号：</td>
          <td>&nbsp;<%=jqxx.getYhkh() %></td>
        </tr>
        <tr>
          <td>单笔开票限额：</td>
          <td>&nbsp;<%=dg.format(jqxx.getDzkpxe()) %></td>
          <td>月累计开票限额：</td>
          <td>&nbsp;<%=dg.format(jqxx.getYljkpxe()) %></td>
        </tr>
        <tr>
          <td>月累计退票限额：</td>
          <td>&nbsp;<%=dg.format(jqxx.getYljtpxe()) %></td>
          <td>开票截止时间：</td>
          <td>&nbsp;<%=jqxx.getKpjzrq() %></td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;<strong>机器对应税种税目信息</strong></td>
        </tr>
        <tr>
          <td colspan="4"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1">
              <tr>
                <th width="25%">税目编码</th>
                <th width="25%">税目名称</th>
                <th width="25%">税目简称</th>
                <th>税率(%)</th>
              </tr>
              <%
		if(jq_szsm!=null&&!jq_szsm.isEmpty()){
			Iterator it_jq_szsm = jq_szsm.iterator();
			while(it_jq_szsm.hasNext()){
				HashMap hm_jq_szsm = (HashMap)it_jq_szsm.next();
%>
              <tr>
                <td align="center"><%=(String)hm_jq_szsm.get("smbm") %></td>
                <td align="center"><%=(String)hm_jq_szsm.get("smmc") %></td>
                <td align="center"><%=(String)hm_jq_szsm.get("smjc")%></td>
                <td align="center"><%=(Double)hm_jq_szsm.get("sl") %></td>
              </tr>
              <%
			}
		}
		%>
            </table></td>
        </tr>
        <%
	}
}
%>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableListBottom" id="tbl_lphzxx_1" style="display:none;">
        <tr>
          <td height="40" align="center"><input type="button" name="xmjcxx_btn" value="纳税人基本信息" onclick="changeTbl('xmjcxx');" style="cursor:hand;" />
            &nbsp;&nbsp;
            <input type="button" name="lpxxxx_btn" value="发票领购信息" onclick="changeTbl('lpxxxx');" style="cursor:hand;" />
            &nbsp;&nbsp;
            <input type="button" name="ret_btn" value=" 返 回 " onclick="window.location.href='cxtj/cx.nsrxx.list1.jsp';" style="cursor:hand;" />
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_lpxxxx" style="display:none;">
        <tr>
          <th width="15%">领票日期</th>
          <th width="30%">发票名称</th>
          <th width="10%">发票单位</th>
          <th width="10%">机器编号</th>
          <th>发票号码起止</th>
          <th width="10%">下发状态</th>
        </tr>
<%
if(alFpjmx!=null&&!alFpjmx.isEmpty()){
	Iterator it_fpxx = alFpjmx.iterator();
	while(it_fpxx.hasNext()){
		Fpjmx fpjmx = (Fpjmx)it_fpxx.next();
		int xfzt = fpjmx.getFpxfzt();
		String xfztmc = "";
		if((fpjmx.getJqbh()==null||"".equals(fpjmx.getJqbh()))&&xfzt==0){
			xfztmc = "未下发";
		}
		else if((fpjmx.getJqbh()!=null&&!"".equals(fpjmx.getJqbh()))&&xfzt==0){
			xfztmc = "已下发，未使用";
		}
		else{
			xfztmc = "已使用";
		}
%>
        <tr>
          <td align="center"><%=fpjmx.getFplgrq() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getFpmc() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getFpdw() %>&nbsp;</td>
          <td align="center"><%=fpjmx.getJqbh() %>&nbsp;</td>
          <td align="center"><%=Util.fpIntToString(fpjmx.getFpqsh()) %> ———  <%=Util.fpIntToString(fpjmx.getFpjzh()) %>&nbsp;</td>
          <td align="center"><%=xfztmc %>&nbsp;</td>
        </tr>
        <%
	}
}
%>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableListBottom" id="tbl_lpxxxx_1" style="display:none;">
        <tr>
          <td height="40" align="center"><input type="button" name="xmjcxx_btn" value="纳税人基本信息" onclick="changeTbl('xmjcxx');" style="cursor:hand;" />
            &nbsp;&nbsp;
            <input type="button" name="lphzxx_btn" value="机器信息" onclick="changeTbl('lphzxx');" style="cursor:hand;" />
            &nbsp;&nbsp;
            <input type="button" name="ret_btn" value=" 返 回 " onclick="window.location.href='cxtj/cx.nsrxx.list1.jsp';" style="cursor:hand;" />
          </td>
        </tr>
      </table>
    </div>
</div>
</body>
</html>
