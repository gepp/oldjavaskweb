<%@ page contentType = "text/html;charset=utf-8" language="java"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
HashMap hmSbhz = (HashMap)request.getAttribute("hmSbhz");
Sbsj sbhz = (Sbsj)hmSbhz.get("sbhz");
Nsrxx nsrxx = (Nsrxx)hmSbhz.get("nsrxx");
ArrayList alSbsjzb = (ArrayList)hmSbhz.get("alSbsjzb");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申报数据显示</title>
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
function jxdk(){
	if(confirm('确定用户卡已插入！')){
		sAlert('读卡中，请等待……');
		try{
			var result = document.dtapplet.read();
			if(result==1){
				window.location.href='/javaskweb/sbsj.do?op=sbsjdk';
			}
			else{
				div_close();
				alert('基本信息读取失败，有可能卡文件损坏！汇总信息读取失败，请检查是否在机器上已做申报！');
			}
		}
		catch(err){
			div_close();
			alert('读卡失败，请检查读卡器是否连接！');
		}
	}
}
function add(){
	if(confirm('确定用户卡已插入！')){
		sAlert('读卡中，请等待……');
		try{
			var result = document.dtapplet.read();
			if(result==1){
				div_close();
				window.open('/javaskweb/sbsj.do?op=jkhcdk','','width=600 height=400');
			}
			else{
				div_close();
				alert('基本信息读取失败，有可能卡文件损！');
			}
		}
		catch(err){
			div_close();
			alert('读卡失败，请检查读卡器是否连接！');
		}
	}	
}
function div_close(){
	var bgObj=document.getElementById("bgDiv");
	var msgObj=document.getElementById("msgDiv");
    document.body.removeChild(bgObj);
	document.body.removeChild(msgObj);
}

function sbhz(){
	var nsrwjbm = document.getElementById('nsrwjbm').value;
	window.location.href = '/javaskweb/sbsj.do?op=sbhz?nsrwjbm='+nsrwjbm;
}
</script>
</head>
<body>
<!--==========right部份==========-->
<div id="right">
  <div id="tool">
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
          <td width="35%"><%=nsrxx.getNsrwjbm() %></td>
          <td width="15%">纳税人名称：</td>
          <td><%=nsrxx.getNsrmc() %></td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">申报数据&nbsp;&nbsp;</a></div></td>
          <td align="left">用户类型：<%=nsrxx.getZsfs()==1?"核定户":"查账征收户"%>&nbsp;&nbsp;</td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="6" style="text-align:left;">&nbsp;&nbsp;申报汇总信息&nbsp;</th>
        </tr>
        <tr>
          <td>年份：</td>
          <td><%=sbhz.getYear() %>&nbsp;</td>
          <td>月份：</td>
          <td><%=sbhz.getMonth() %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="16%">正常票份数：</td>
          <td width="17%"><%=sbhz.getZcpfs() %>&nbsp;</td>
          <td width="16%">退票份数：</td>
          <td width="17%"><%=sbhz.getTpfs() %>&nbsp;</td>
          <td width="18%">废票份数：</td>
          <td><%=sbhz.getFpfs() %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票金额（元）：</td>
          <td><%=sbhz.getZcpzje() %>&nbsp;</td>
          <td>退票金额（元）：</td>
          <td><%=sbhz.getTpzje() %>&nbsp;</td>
          <td>实际开票金额（元）：</td>
          <td><%=(sbhz.getZcpzje()-sbhz.getTpzje()) %>&nbsp;</td>
        </tr>
      </table>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="5" style="text-align:left;">&nbsp;&nbsp;税款信息&nbsp;</th>
        </tr>
        <tr>
          <td width="15%">税目编码：</td>
          <td width="20%">税目名称：</td>
          <td width="15%">税率(%)</td>
          <td width="15%">税款(元)</td>
          <td width="*">&nbsp;</td>
        </tr>
<%
if(alSbsjzb!=null&&!alSbsjzb.isEmpty()){
	Iterator it = alSbsjzb.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
%>
        <tr>
          <td><%=(String)hm.get("smbm") %>&nbsp;</td>
          <td><%=(String)hm.get("smmc") %>&nbsp;</td>
          <td><%=(Double)hm.get("sl") %>&nbsp;</td>
          <td><%=(Double)hm.get("sk") %>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        
	  
<%
	}
}
%>
	  </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="50" align="center">
			<input type="button" name="jxdk" value="监控回传" onclick="add();" style="cursor:hand;" />&nbsp;&nbsp;
			<jsp:plugin name="dtapplet" type="applet" code="com.jsdt.web.applet.TYUcReadlet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
			<input type="button" name="btn" value="返回申报首页" onclick="window.location.href='/javaskweb/sbsj.do?op=toZrysb';" />
          </td>
        </tr>
      </table>
      <iframe src="" width="0" height="0" frameborder="0" name="addiframe"></iframe>
    </div>
</div>
</body>
</html>