<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	HashMap hm = (HashMap)request.getAttribute("SKK");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
System.out.println(basePath);
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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
function copyFcard()
{
	//document.getElementById('fzskk').disabled=true;
	if(confirm("确定发税控卡？")){
		//sAlert('税控卡发卡中，请等待……');
		try{
			var v = document.CopyFCApplet.copyTYFiscalCard();
			//alert(v);
			//var cno = document.getElementById("FISCAL_CARD_NO").value;
			if(v == 1){
				//div_close();
				alert("税控卡 复制成功！！！");
				//document.getElementById('fzskk').disabled=true;
				//document.getElementById('fh').disabled=true;
				//document.getElementById('fyhk').disabled=false;
				//document.all('iframe').src = '../AddTaxPayerServlet?method=updateSKKZT&SKKH='+SKKH;
			}
			else{
				//div_close();
				alert("税控卡  复制失败！！！");
				//document.getElementById('fzskk').disabled=false;
			}
		}
		catch(err){
			//div_close();
			alert("税控卡  复制失败！！！");
			//document.getElementById('fzskk').disabled=false;
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

<div id="right">
  <div id="main">
  <%
	if(hm!=null&&!hm.isEmpty())
	{
		String sbfs = (String)hm.get("sbfs");
		//String sbfsmc = "";
		//if(sbfs.equals("1"))
		//{
		//	sbfsmc = "用户卡传递"；
		//}
		//else
		//{
		//	sbfsmc = "其他"；
		//}
		
		String mxsbfs = (String)hm.get("mxsbbz");
		//String mxsbfsmc = "";
		//if(mxsbfs.equals("1"))
		//{
		//	mxsbfsmc = "申报明细"；
		//}
		//else 
		//{
		//	mxsbfsmc = "不申报明细"；
		//}
%>
<!-- 
   <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
      <tr>
        <th colspan="4">税控卡信息确认</th>
      </tr>
    </table>
     <br />
      -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="tableListTitle">

    <tr>
        <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">基本信息</a></div></td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
      <tr>
        <td width="20%">税控卡编号：</td>
        <td width="30%"><%=(String)hm.get("skkh") %>&nbsp;</td>
        <td width="20%">税控收款机编号：</td>
        <td width="30%"><%=(String)hm.get("jqbh") %>&nbsp;</td>
      </tr>
      <tr>
        <td>纳税人微机编码：</td>
        <td><%=(String)hm.get("nsrwjbm")%>&nbsp;</td>
        <td>纳税人识别号：</td>
        <td><%=(String)hm.get("nsrsbh")%>&nbsp;</td>
      </tr>
      <tr>
        <td>纳税人单位名称：</td>
        <td colspan="3"><%=(String)hm.get("nsrmc")%>&nbsp;</td>
      </tr>
      <tr>
        <td>应用启用日期：</td>
        <td><%=(String)hm.get("kqyrq")%>&nbsp;</td>
        <td>应用有效日期：</td>
        <td><%=(String)hm.get("kyxrq")%>&nbsp;</td>
      </tr>
      <tr>
        <td>主管分局代码：</td>
        <td><%=(String)hm.get("swjgbm")%>&nbsp;</td>
        <td>申报方式：</td>
        <td><%=sbfs%>&nbsp;</td>
      </tr>
    </table>
    <br />
        <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="tableListTitle">
    <tr>
        <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">税控信息</a></div></td>
    </tr>
    </table>
    <br/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
      <tr>
        <td width="21%">开票截止日期：</td>
        <td width="29%"><%=(String)hm.get("kpjzrq")%>&nbsp;</td>
        <td width="25%">单张发票开票金额限额(元)：</td>
        <td width="25%"><%=(String)hm.get("dzkpxe")%>&nbsp;</td>
      </tr>
      <tr>
        <td>发票累计金额限额（元）：</td>
        <td><%=(String)hm.get("yljkpxe")%>&nbsp;</td>
        <td>退票累计金额限额（元）：</td>
        <td><%=(String)hm.get("yljtpxe")%>&nbsp;</td>
      </tr>
      <tr>
        <td>税种税目索引号：</td>
        <td><%=(String)hm.get("szsmsy")%>&nbsp;</td>
        <td>明细申报标志：</td>
        <td><%=mxsbfs%>&nbsp;</td>
      </tr>
      </table>
      <br/>
         <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="tableListTitle">
    </table>
    <tr>
        <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票信息</a></div></td>
    </tr>
     <table width="100%" border="0" cellspacing="0" cellpadding="0"
	id="userList">
         <tr>
        <td width="10%">发票代码：</td>
        <td width="30%"><%=(String)hm.get("fpdm")%>&nbsp;</td>
        <td width="13%">发票起始号：</td>
        <td width="17%"><%=(String)hm.get("fpqsh")%>&nbsp;</td>
        <td width="13%">发票终止号：</td>
        <td width="17%"><%=(String)hm.get("fpjzh")%>&nbsp;</td>
       </tr>
    </table>
    <br/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
    <tr>
    	<td height="50" align="center">
        <!-- 纳税人微机代码-->
           <%
           String nsrwjbm=(String)hm.get("nsrwjbm");
             if(nsrwjbm.length()>16){
            	 nsrwjbm=nsrwjbm.substring(0,nsrwjbm.length()-2);
             }
           %>
          <input type="hidden"  id="TAXPAYER_NO" name="TAXPAYER_NO" value="<%=nsrwjbm%>" />
          <!-- 税控卡号-->
          <input type="hidden" id="FISCAL_CARD_NO" name="FISCAL_CARD_NO" value="<%=(String)hm.get("skkh") %>" />
          <!-- 机器编号-->
          <input type="hidden" id="MACHINE_NO" name="MACHINE_NO" value="<%=(String)hm.get("jqbh") %>" />
          <!-- 纳税户名称-->
          <input type="hidden" id="TAXPAYER_NAME" name="TAXPAYER_NAME" value="<%=(String)hm.get("nsrmc") %>" />
          <!-- 申报截止时间-->
          <input type="hidden" id="MAKE_END_DATE" name="MAKE_END_DATE" value="<%=(String)hm.get("kpjzrq") %>" />
          <!-- 单张发票开票限额-->
          <input type="hidden" id="MAKE_MAX_SINGLE" name="MAKE_MAX_SINGLE" value="<%=Long.toString((long)(Double.parseDouble((String)hm.get("dzkpxe"))*100))%>" />
          <!-- 月累计最高开票限额-->
          <input type="hidden" id="MAKE_MAX_SUM" name="MAKE_MAX_SUM" value="<%=Long.toString((long)(Double.parseDouble((String)hm.get("yljkpxe"))*100))%>" />
          <!-- 月累计最高退票限额-->
          <input type="hidden" id="BACK_MAX_SUM" name="BACK_MAX_SUM" value="<%=Long.toString((long)(Double.parseDouble((String)hm.get("yljtpxe"))*100))%>" />
          <!-- 明细申报标志-->
          <input type="hidden" id="DECLARE_MAKE_TYPE" name="DECLARE_MAKE_TYPE" value="<%=mxsbfs %>" />
          <!-- 申报类型-->
          <input type="hidden" id="DECLARE_TYPE" name="DECLARE_TYPE" value="<%=sbfs%>" />
          <!-- 税控卡有效时间-->
          <input type="hidden" id="F_START_DATE" name="F_START_DATE" value="<%=(String)hm.get("kqyrq") %>" />
          <!-- 税控卡截止时间-->
          <input type="hidden" id="F_END_DATE" name="F_END_DATE" value="<%=(String)hm.get("kyxrq") %>" />
          <!-- 主管科所-->
          <input type="hidden" id="ORGAN_CODE" name="ORGAN_CODE" value="<%=(String)hm.get("swjgbm") %>" />
          <!-- 纳税人识别号-->
          <input type="hidden" id="TAXPAYER_CODE" name="TAXPAYER_CODE" value="<%=(String)hm.get("nsrsbh") %>" />
          <!-- 发票代码-->
          <input type="hidden" id="INVOICE_CODE" name="INVOICE_CODE" value="<%=(String)hm.get("fpdm") %>" />
          <!-- 发票起始号-->
          <input type="hidden" id="INVOICE_START_NO" name="INVOICE_START_NO" value="<%=(String)hm.get("fpqsh") %>" />
           <!-- 发票终止号-->
          <input type="hidden" id="INVOICE_END_NO" name="INVOICE_END_NO" value="<%=(String)hm.get("fpjzh") %>" />
          <!-- 税种税目索引-->
          <input type="hidden" id="SZSM_INDEX" name="SZSM_INDEX" value="<%=(String)hm.get("szsmsy") %>" />
          
          <input type="button" id="fh" name="btn3" onClick="" value="返 回" style="cursor: hand;" />
          &nbsp;&nbsp;
          <input type="button" name="btn1" onClick="copyFcard()" value=" 复制税控卡 " style="cursor: hand;" id="fzskk" />
          	<jsp:plugin name="CopyFCApplet" type="applet" code="com.jsdt.web.applet.TYFcCopylet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
        </td>
    </tr>

    </table>
     <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
    <%
	}
%>
  </div>
</div>

</body>
</html>