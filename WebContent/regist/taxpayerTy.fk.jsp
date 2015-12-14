<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
DecimalFormat dg = new DecimalFormat("0.00");
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
Jqxx jqxx = (Jqxx)request.getAttribute("jqxx");
ArrayList jqszsm = jqxx.getJqszsm();

String jumpFlag = (String)request.getAttribute("jumpFlag");


String swjgbm = nsrxx.getSwjgbm();
if(swjgbm.length()<8){
	for(int m=swjgbm.length();m<8;m++){
		swjgbm = "0"+swjgbm;
	}
}
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
System.out.println(basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发卡</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript">
//more javascript from http://www.smallrain.net
        function sAlert(str){
        var msgw,msgh,bordercolor;
        msgw=400;//提示窗口的宽度
        msgh=100;//提示窗口的高度
        titleheight=25 //提示窗口标题高度
        bordercolor="#c51100";//提示窗口的边框颜色
        titlecolor="#c51100";//提示窗口的标题颜色
        
        var sWidth,sHeight;
        sWidth=screen.width;
        sHeight=screen.height;

        var bgObj=document.createElement("div");
        bgObj.setAttribute('id','bgDiv');
        bgObj.style.position="absolute";
        bgObj.style.top="0";
        bgObj.style.background="#cccccc";
        bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
        bgObj.style.opacity="0.6";
        bgObj.style.left="0";
        bgObj.style.width=sWidth + "px";
        bgObj.style.height=sHeight + "px";
        bgObj.style.zIndex = "10000";
        document.body.appendChild(bgObj);
        
        var msgObj=document.createElement("div")
        msgObj.setAttribute("id","msgDiv");
        msgObj.setAttribute("align","center");
        msgObj.style.background="white";
        msgObj.style.border="1px solid " + bordercolor;
        msgObj.style.position = "absolute";
        msgObj.style.left = "50%";
        msgObj.style.top = "50%";
        msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
        msgObj.style.marginLeft = "-225px" ;
        msgObj.style.marginTop = -75+document.documentElement.scrollTop+"px";
        msgObj.style.width = msgw + "px";
        msgObj.style.height =msgh + "px";
        msgObj.style.textAlign = "center";
        msgObj.style.lineHeight ="25px";
        msgObj.style.zIndex = "10001";

       var title=document.createElement("h4");
       title.setAttribute("id","msgTitle");
       title.setAttribute("align","right");
       title.style.margin="0";
       title.style.padding="3px";
       title.style.background=bordercolor;
       title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
       title.style.opacity="0.75";
       title.style.border="1px solid " + bordercolor;
       title.style.height="18px";
       title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif";
       title.style.color="white";
       title.style.cursor="pointer";
       /*title.innerHTML="关闭";
       title.onclick=function(){
    document.body.removeChild(bgObj);
    document.getElementById("msgDiv").removeChild(title);
document.body.removeChild(msgObj);
            }*/
       document.body.appendChild(msgObj);
       document.getElementById("msgDiv").appendChild(title);
       var txt=document.createElement("p");
       txt.style.margin="1em 0"
       txt.setAttribute("id","msgTxt");
       txt.innerHTML=str;
       document.getElementById("msgDiv").appendChild(txt);
        }
    </script>
<script language="javascript">
function writeUcard()
{
	//alert(document.getElementById("TAXPAYER_NO").value);
	document.getElementById('fyhk').disabled=true;
	if(confirm('确定发用户卡？')){
		sAlert('用户卡发卡中，请等待……');
		try{
			var v = document.WriteUCApplet.writeTYUserCard();
		//	var cno = document.getElementById("FISCAL_CARD_NO").value;
			if(v == 1){
				div_close();
				alert("用户卡 发卡成功！！！您已完成注册登记！");
				document.getElementById('fyhk').disabled=true;
				document.getElementById('jxtjjq').disabled=false;
				document.getElementById('fh').disabled=true;
				//document.getElementById('jxzcdj').disabled=false;
				//document.all('js_btn').style.display="none";
				//document.all('iframe').src = '../AddTaxPayerServlet?method=updateYHKZT&YHKH='+YHKH;
				//jiesuo();
			}
			else{
				div_close();
				alert("用户卡  发卡失败！！！");
				document.getElementById('fyhk').disabled=false;
			}
		}
		catch(err){
			div_close();
			alert("用户卡  发卡失败！！！");
			document.getElementById('fyhk').disabled=false;
		}
	}
}

function writeFcard()
{
	document.getElementById('fskk').disabled=true;
	if(confirm("确定发税控卡？")){
		sAlert('税控卡发卡中，请等待……');
		try{
			var v = document.WriteFCApplet.writeTYFiscalCard();
			//alert(v);
			//var cno = document.getElementById("FISCAL_CARD_NO").value;
			if(v == 1){
				div_close();
				alert("税控卡  发卡成功！！！");
				document.getElementById('fskk').disabled=true;
				document.getElementById('fh').disabled=true;
				document.getElementById('fyhk').disabled=false;
				//document.all('iframe').src = '../AddTaxPayerServlet?method=updateSKKZT&SKKH='+SKKH;
			}
			else{
				div_close();
				alert("税控卡   发卡失败！！！");
				document.getElementById('fskk').disabled=false;
			}
		}
		catch(err){
			div_close();
			alert("税控卡   发卡失败！！！");
			document.getElementById('fskk').disabled=false;
		}
	}
}

function div_close(){
	var bgObj=document.getElementById("bgDiv");
	var msgObj=document.getElementById("msgDiv");
    document.body.removeChild(bgObj);
	document.body.removeChild(msgObj);
}
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right">
  <div id="tool">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="tableListTitle" style="padding-top: 14px;">
      <tr>
        <td width="35%" style="font-size: 16px; font-weight: 800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/taxpayerTyServlet?method=import" class="nav">纳税户信息管理</a> &gt;&gt;&nbsp;&nbsp;注册登记</td>
        <td><div class="userTitleGreen">
            <a href="#" class="black">税务登记信息</a>
          </div>
          <div class="userTitleGreen"><a href="#" class="black">纳税人税种税目</a></div>
          <div class="userTitleGreen"><a href="#" class="black">税控装置信息录入</a></div>
          <div class="userTitleGreen"><a href="#" class="black">开票限额录入</a></div>
          <div class="userTitleRed"><ul>
              <li><a href="#" class="black">发卡</a></li>
            </ul></div></td>
      </tr>
    </table>
  </div>
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
      <tr>
        <th colspan="4">发卡信息确认</th>
      </tr>
    </table>
    <br />
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="tableListTitle">
      <tr>
        <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">基本信息</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
      <tr>
        <td>税控卡编号：</td>
        <td><font color="#0000FF"><strong><%=jqxx.getSkkh() %></strong></font>&nbsp;</td>
        <td>税控收款机编号：</td>
        <td><font color="#0000FF"><strong><%=jqxx.getJqbh() %></strong></font>&nbsp;</td>
      </tr>
      <tr>
        <td>用户卡编号：</td>
        <td><font color="#0000FF"><strong><%=jqxx.getYhkh() %></strong></font>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>卡启用日期：</td>
        <td><%=jqxx.getKqyrq() %>&nbsp;</td>
        <td>卡有效日期：</td>
        <td><%=jqxx.getKyxrq() %>&nbsp;</td>
      </tr>
      <tr>
        <td>申报截止日期：</td>
        <td><font color="#0000FF"><strong><%=jqxx.getKpjzrq() %></strong></font>&nbsp;</td>
        <td>机器数量：</td>
        <td>1&nbsp;</td>
      </tr>
      <tr>
        <td>单笔开票最高限额（元）：</td>
        <td><font color="#0000FF"><strong><%=dg.format(jqxx.getDzkpxe()) %></strong></font>&nbsp;</td>
        <td>月累计开票最高限额（元）：</td>
        <td><font color="#0000FF"><strong><%=dg.format(jqxx.getYljkpxe()) %></strong></font>&nbsp;</td>
      </tr>
      <tr>
        <td>月累计退票最高限额（元）：</td>
        <td><font color="#0000FF"><strong><%=dg.format(jqxx.getYljtpxe()) %></strong></font>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="20%">纳税人微机编码：</td>
        <td width="30%"><%=nsrxx.getNsrwjbm().trim() %>&nbsp;</td>
        <td width="20%">纳税人名称：</td>
        <td width="30%"><%=nsrxx.getNsrmc().trim() %>&nbsp;</td>
      </tr>
    </table>
    <br />
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="tableListTitle">
      <tr>
        <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">机器对应税种税目</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
      <tr>
        <th width="20%">税目编码</th>
        <th width="30%">税目名称</th>
        <th width="10%">税率（%）</th>
        <th>经营项目内容</th>
        <th width="10%">税目索引号</th>
      </tr>
      <%
if(jqszsm!=null&&!jqszsm.isEmpty()){
	Iterator it = jqszsm.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
		
		String smsy 	= ((Integer)hm.get("smsy")).toString();
		if(smsy.length()<2){
			smsy = "0"+smsy;
		}
%>
      <tr>
        <td align="center"><%=(String)hm.get("smbm") %></td>
        <td align="center"><%=(String)hm.get("smmc") %></td>
        <td align="center"><%=(Double)hm.get("sl") %></td>
        <td align="center"><%=(String)hm.get("smjc") %></td>
        <td align="center"><%=smsy %></td>
      </tr>
      <%
	}
}
%>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
      <tr>
        <td height="50" align="center"><!-- 纳税人微机代码-->
          <input type="hidden" name="TAXPAYER_NO" id="TAXPAYER_NO" value="<%=(nsrxx.getNsrwjbm().substring(0,16)) %>" />
          <!-- 税控卡号-->
          <input type="hidden" name="FISCAL_CARD_NO" id="FISCAL_CARD_NO" value="<%=jqxx.getSkkh() %>" />
          <!-- 机器编号-->
          <input type="hidden" name="MACHINE_NO" id="MACHINE_NO" value="<%=jqxx.getJqbh() %>" />
          <!-- 用户卡有效时间-->
          <input type="hidden" name="U_START_DATE" id="U_START_DATE" value="<%=Util.toxkrq(jqxx.getKqyrq()) %>" />
          <!-- 用户卡截止时间-->
          <input type="hidden" name="U_END_DATE" id="U_END_DATE" value="<%=Util.toxkrq(jqxx.getKyxrq()) %>" />
          <!-- 纳税户名称-->
          <input type="hidden" name="TAXPAYER_NAME" id="TAXPAYER_NAME" value="<%=nsrxx.getNsrmc().trim() %>" />
          <!-- 申报截止时间-->
          <input type="hidden" name="MAKE_END_DATE" id="MAKE_END_DATE" value="<%=Util.toxkrq(jqxx.getKpjzrq()) %>" />
          <!-- 单张发票开票限额-->
          <input type="hidden" name="MAKE_MAX_SINGLE" id="MAKE_MAX_SINGLE" value="<%=(long)(jqxx.getDzkpxe()*100)%>" />
          <!-- 月累计最高开票限额-->
          <input type="hidden" name="MAKE_MAX_SUM" id="MAKE_MAX_SUM" value="<%=(long)(jqxx.getYljkpxe()*100)%>" />
          <!-- 月累计最高退票限额-->
          <input type="hidden" name="BACK_MAX_SUM" id="BACK_MAX_SUM" value="<%=(long)(jqxx.getYljtpxe()*100)%>" />
          <!-- 明细申报标志-->
          <input type="hidden" name="DECLARE_MAKE_TYPE" id="DECLARE_MAKE_TYPE" value="<%=jqxx.getMxsbbz() %>" />
          <!-- 申报类型-->
          <input type="hidden" name="DECLARE_TYPE" id="DECLARE_TYPE" value="<%=jqxx.getSbfs() %>" />
          <!-- 税控卡有效时间-->
          <input type="hidden" name="F_START_DATE" id="F_START_DATE" value="<%=Util.toxkrq(jqxx.getKqyrq()) %>" />
          <!-- 税控卡截止时间-->
          <input type="hidden" name="F_END_DATE" id="F_END_DATE" value="<%=Util.toxkrq(jqxx.getKyxrq()) %>" />
          <!-- 主管科所-->
          <input type="hidden" name="ORGAN_CODE" id="ORGAN_CODE" value="<%=swjgbm %>" />
          <!-- 纳税人识别号-->
          <input type="hidden" name="TAXPAYER_CODE" id="TAXPAYER_CODE" value="<%=nsrxx.getNsrsbh() %>" />
          <!-- 下次申报月-->
          <input type="hidden" name="MAKE_END_DATE_MONTH" id="MAKE_END_DATE_MONTH" value="<%=SYSTEM.ENDMONTH %>" />
          <!-- 下次申报日-->
          <input type="hidden" name="MAKE_END_DATE_DAY" id="MAKE_END_DATE_DAY" value="<%=SYSTEM.ENDDAY %>" />
          <%
		int i=0;
		Iterator it = jqszsm.iterator();
		while(it.hasNext()){
        	HashMap hm = (HashMap)it.next();
			String SMBM 	= (String)hm.get("smbm");
			String KPXMMC 	= (String)hm.get("smjc");
			double SL 		= (Double)hm.get("sl");
			String SMINDEX 	= ((Integer)hm.get("smsy")).toString();
			if(SMINDEX.length()<2){
        		SMINDEX = "0"+SMINDEX;
			}
			String smStr = "";
			smStr = SMINDEX+","+SMBM+","+(int)SL+","+KPXMMC;
		%>
          <input type="hidden" name="tax<%=i%>" id="tax<%=i%>" value="<%=smStr%>" />
          <%
			i++;
		}
		if(i<20){
			for(int j=i;j<20;j++){
			String val = "";
		%>
          <input type="hidden" name="tax<%=j%>" id="tax<%=j%>" value="<%=val%>" />
          <%
			}
		}
		%>
          <input type="button" id="fh" name="btn3" onClick="window.location.href='/javaskweb/taxpayer.do?op=skzzxx&jqbh=<%=jqxx.getJqbh() %>&nsrwjbm=<%=nsrxx.getNsrwjbm() %>&jumpFlag=<%=jumpFlag %>';" value="返 回" style="cursor: hand;" />
          &nbsp;&nbsp;
          <input type="button" name="btn1" onClick="writeFcard()" value=" 发税控卡 " style="cursor: hand;" id="fskk" />
          &nbsp;&nbsp;
          <input type="button" name="btn2" onClick="writeUcard()" value=" 发用户卡 " style="cursor: hand;" id="fyhk" />
          &nbsp;&nbsp;
          	<jsp:plugin name="WriteFCApplet" type="applet" code="com.jsdt.web.applet.TYFcWritelet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
				<jsp:params>  
				  <jsp:param name="serverUrl" value="<%=basePath%>"   />
				</jsp:params>
			</jsp:plugin>
			<jsp:plugin name="WriteUCApplet" type="applet" code="com.jsdt.web.applet.TYUcWritelet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
          <!--  
          <applet name="WriteFCApplet" code="com.jsdt.web.applet.TYFcWritelet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
          </applet>
          <applet name="WriteUCApplet" code="com.jsdt.web.applet.TYUcWritelet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
          </applet>
          -->
          <input type="button" id="jxtjjq" name="btn2" value=" 继续添加机器 " style="cursor: hand;" onclick="window.location.href='/javaskweb/taxpayer.do?op=skzzxx&nsrwjbm=<%=nsrxx.getNsrwjbm() %>&jumpFlag=<%=jumpFlag %>';" />
          &nbsp;&nbsp; </td>
      </tr>
    </table>
    <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>
