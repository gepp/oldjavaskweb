<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
HttpSession session1 = request.getSession(false);
Nsrxx nsrxx = (Nsrxx)request.getAttribute("nsrxx");
Jqxx jqxx = (Jqxx)request.getAttribute("jqxx");
ArrayList alFp = (ArrayList)request.getAttribute("alFp");
ArrayList cardInvoice = (ArrayList)session1.getAttribute("cardInvoice");
String fpqshInfoStr = (String)request.getAttribute("fpqshInfoStr");

String nsrwjbm = nsrxx.getNsrwjbm();
if(nsrwjbm.length()<16){
	for(int m=nsrwjbm.length();m<16;m++){
		nsrwjbm = "0"+nsrwjbm;
	}
}
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
System.out.println(basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
function fpxk(){
	document.getElementById('fpxk_btn').disabled=true;
	document.all('next_btn').disabled=true;
	document.all('ret_btn').disabled=true;
	var yhkh = document.all('YHKH').value;
	var fpxi_0 = document.all('fpxi_'+yhkh+'_0').value;
	if(fpxi_0!=''){
		if(confirm('确认用户卡已插入！')){
			sAlert('发票写卡中，请等待……');
			try{
				var result = document.writeInvoiceApplet.write(yhkh);
				if(result==1){
					div_close();
					var frm = document.addForm;
					frm.submit();
					
					alert('发票写卡成功！');
					 
					sAlert('读卡中，请等待……');
					var result1 =document.dtapplet.read();
					window.location='/javaskweb/fpService.do?op=info';
					if(result1==1){
						window.location.href='/javaskweb/fpService.do?op=info';
					}
					else{
						div_close();
						alert('卡基本信息读取失败！');
					}
					window.location='/javaskweb/fpService.do?op=info';
					 
					
				}
				else{
					div_close();
					alert('发票写卡失败！');
					document.all('next_btn').disabled=false;
					document.all('ret_btn').disabled=false;
				}
			}
			catch(err){
				div_close();
				alert('发票写卡失败！');
				document.all('next_btn').disabled=false;
				document.all('ret_btn').disabled=false;
			}
		}
	}
	else{
		alert('无发票信息');
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
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;发票管理 &gt;&gt;&nbsp;&nbsp;<a href="/javaskweb/fpService.do?op=toImport" class="nav">发票领购</a></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
  <div id="main">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">纳税户基本信息</a></div></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
            <td width="15%">纳税人微机编码：</td>
            <td>&nbsp;<%=nsrxx.getNsrwjbm() %></td>
            <td width="15%">纳税人识别号：</td>
            <td width="35%">&nbsp;<%=nsrxx.getNsrsbh() %></td>
          </tr>
          <tr>
            <td>纳税人名称：</td>
            <td>&nbsp;<%=nsrxx.getNsrmc() %></td>
            <td>经营地址：</td>
            <td>&nbsp;<%=nsrxx.getJydz() %></td>
          </tr>
          <tr>
            <td>机器编号：</td>
            <td>&nbsp;<%=jqxx.getJqbh() %></td>
            <td>税控卡号：</td>
            <td>&nbsp;<%=jqxx.getSkkh() %>
            <!-- 用户卡号-->
          <input type="hidden" name="YHKH" id="YHKH" value="<%=jqxx.getYhkh() %>" />
           <!-- 微机编码-->
          <input type="hidden" name="WJBM_<%=jqxx.getYhkh() %>" id="WJBM_<%=jqxx.getYhkh() %>" value="<%=nsrwjbm.substring(0,16) %>" />
          <!-- 税控卡号-->
          <input type="hidden" name="FISCAL_CARD_NO_<%=jqxx.getYhkh() %>" id="FISCAL_CARD_NO_<%=jqxx.getYhkh() %>" value="<%=jqxx.getSkkh() %>" />
          <!-- 机器编号-->
          <input type="hidden" name="MACHINE_NO_<%=jqxx.getYhkh() %>" id="MACHINE_NO_<%=jqxx.getYhkh() %>" value="<%=jqxx.getJqbh() %>" />
            </td>
          </tr>
      </table>
      <br />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">用户卡已有发票信息</a></div></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th width="20%">发票代码</th>
          <th width="20%">发票单位（卷）</th>
          <th>发票号码起止</th>
        </tr>
<%
int num = 0;
if(cardInvoice!=null&&cardInvoice.size()>0){
Iterator it = cardInvoice.iterator();
while(it.hasNext()){
	
	HashMap hm = (HashMap)it.next();
	String QSH = String.valueOf((Integer)hm.get("QSH"));
	int JS = (Integer) hm.get("JS");
	if(QSH!=null&&!"".equals(QSH)&&!"0".equals(QSH)&& JS > 0){
		String fpxi = (String)hm.get("FPDM")+","+(Integer)hm.get("QSH")+","+(Integer)hm.get("JZH")+","+(Integer)hm.get("JS");
%>
		  <tr>
            <td align="center"><%=(String)hm.get("FPDM")%>&nbsp;</td>
            <td align="center"><%=(Integer)hm.get("JS")%>&nbsp;</td>
            <td align="center"><%=(Integer)hm.get("QSH")%>--<%=(Integer)hm.get("JZH")%>&nbsp;
            <input type="hidden" id="fpxi_<%=jqxx.getYhkh() %>_<%=num %>" name="fpxi_<%=jqxx.getYhkh() %>_<%=num %>" value="<%=fpxi%>" />
            </td>
          </tr>
<%
		num++;
	}
}
}
%>
      </table>
      <br />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">税控发票领购情况</a></div></td>
          <td><font color="#FF0000">&nbsp;</font></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th width="10%">&nbsp;</th>
          <th width="15%">领票日期</th>
          <th width="15%">发票代码</th>
          <th width="15%">发票单位（卷）</th>
          <th>发票号码起止</th>
        </tr>
<%
if(alFp!=null&&alFp.size()>0){
Iterator it1 = alFp.iterator();
while(it1.hasNext()){
	Fpjmx fpjmx = (Fpjmx)it1.next();
	String fpqsh = String.valueOf(fpjmx.getFpqsh());
	if(fpqsh.length()<8){
		for(int i=fpqsh.length();i<8;i++){
			fpqsh = "0"+fpqsh;
		}
	}
	String fpjzh = String.valueOf(fpjmx.getFpjzh());
	if(fpjzh.length()<8){
		for(int i=fpjzh.length();i<8;i++){
			fpjzh = "0"+fpjzh;
		}
	}
	String fpxi = fpjmx.getFpdm()+","+fpqsh+","+fpjzh+","+fpjmx.getFpdw();
%>
		  <tr>
            <td align="center">&nbsp;</td>
            <td align="center"><%=fpjmx.getFplgrq() %>&nbsp;</td>
            <td align="center"><%=fpjmx.getFpdm() %>&nbsp;</td>
            <td align="center"><%=fpjmx.getFpdw() %>&nbsp;</td>
            <td align="center"><%=fpqsh %>--<%=fpjzh %>&nbsp;
            <input type="hidden" id="fpxi_<%=jqxx.getYhkh() %>_<%=num %>" name="fpxi_<%=jqxx.getYhkh() %>_<%=num %>" value="<%=fpxi%>" />
            </td>
            
          </tr>
<%
	num++;
}
}
%>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="40" align="center"><img src="images/ico_01.gif" width="22" height="12" /> 发票领购写卡！！ <br/>
            <br/>
<%
if(num<10){
	for(int m=num;m<10;m++){
%>
		<input type="hidden" id="fpxi_<%=jqxx.getYhkh() %>_<%=m %>" name="fpxi_<%=jqxx.getYhkh() %>_<%=m %>" value="" />
<%
	}
}
%>
            <input type="hidden" name="num" value="<%=num%>">            
            <input type="button" name="next_btn" id="fpxk_btn" value="发票写卡" onclick="fpxk();" />&nbsp;&nbsp;
           <jsp:plugin name="writeInvoiceApplet" type="applet" code="com.jsdt.web.applet.InvoiceWritelet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
            <jsp:plugin name="dtapplet" type="applet" archive="dtapplet.jar" codebase="." code="com.jsdt.web.applet.TYUcReadlet.class" height="1" width="1" >
            <jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
            </jsp:plugin>
            <!-- <applet name="writeInvoiceApplet" code="com.jsdt.web.applet.InvoiceWritelet.class" codebase="." archive="dtapplet.jar" width="1" height="1"></applet> -->
            <input type="button" name="ret_btn" value=" 返 回 " onclick="window.location.href='/javaskweb/fplgServlet?method=fpList&nsrwjbm=<%=nsrxx.getNsrwjbm() %>&jqbh=<%=jqxx.getJqbh() %>';" />
            <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
          </td>
        </tr>
      </table>
      <form name="addForm" method="post" action="/javaskweb/fpService.do?op=updateXkbz" target="iframe">
      <input type="hidden" name="fpqshInfoStr" value="<%=fpqshInfoStr%>"/>
      </form>
  </div>
</div>
</body>
</html>
