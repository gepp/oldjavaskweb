<%@ page contentType = "text/html;charset=utf-8" language="java"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
HashMap hmSb = (HashMap)request.getAttribute("hmSb");
Sbsj sbsj = (Sbsj)hmSb.get("sbsj");
Nsrxx nsrxx = (Nsrxx)hmSb.get("nsrxx");
ArrayList alJdsb = (ArrayList)hmSb.get("alJdsb");
String jqmsg = (String)hmSb.get("jqmsg");
int sbflag = (Integer)hmSb.get("sbflag");
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
	window.location.href = '/javaskweb/sbsj.do?op=sbhzsj&nsrwjbm='+nsrwjbm;
}

<%
if(sbflag==1){
%>
setTimeout('sbhz()',5000);
<%
}
%>  
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
          <td width="35%"><%=nsrxx.getNsrwjbm() %>
          <input type="hidden" name="nsrwjbm" id="nsrwjbm" value="<%=nsrxx.getNsrwjbm() %>"/>
          </td>
          <td width="15%">纳税人名称：</td>
          <td><%=nsrxx.getNsrmc() %></td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="20%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">申报数据&nbsp;&nbsp;</a></div></td>
          <td align="left">用户类型：<%=nsrxx.getZsfs()==1?"核定户":"查账征收户"%>&nbsp;&nbsp;<font color="#FF0000"><%=jqmsg %></font></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="6" style="text-align:left;">&nbsp;&nbsp;机器编号为【<%=sbsj.getJqbh() %>】申报信息&nbsp;</th>
        </tr>
        <tr>
          <td>所属开始时间：</td>
          <td><%=sbsj.getSskssj() %>&nbsp;</td>
          <td>所属结束时间：</td>
          <td><%=sbsj.getSsjzsj() %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td width="16%">正常票份数：</td>
          <td width="17%"><%=sbsj.getZcpfs() %>&nbsp;</td>
          <td width="16%">退票份数：</td>
          <td width="17%"><%=sbsj.getTpfs() %>&nbsp;</td>
          <td width="18%">废票份数：</td>
          <td><%=sbsj.getFpfs() %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票金额（元）：</td>
          <td><%=sbsj.getZcpzje() %>&nbsp;</td>
          <td>退票金额（元）：</td>
          <td><%=sbsj.getTpzje() %>&nbsp;</td>
          <td>实际开票金额（元）：</td>
          <td><%=(sbsj.getZcpzje()-sbsj.getTpzje()) %>&nbsp;</td>
        </tr>
      </table>
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="6" style="text-align:left;">&nbsp;&nbsp;阶段申报信息&nbsp;</th>
        </tr>
<%
if(alJdsb!=null&&!alJdsb.isEmpty()){
	Iterator it = alJdsb.iterator();
	while(it.hasNext()){
		HashMap hm = (HashMap)it.next();
%>
        <tr>
          <td>机器编号：</td>
          <td><%=(String)hm.get("jqbh") %>&nbsp;</td>
          <td>所属开始时间：</td>
          <td><%=(String)hm.get("sskssj") %>&nbsp;</td>
          <td>所属结束时间：</td>
          <td><%=(String)hm.get("ssjzsj") %>&nbsp;</td>
        </tr>
        <tr>
          <td width="16%">正常票份数：</td>
          <td width="17%"><%=(Integer)hm.get("zcpfs") %>&nbsp;</td>
          <td width="16%">退票份数：</td>
          <td width="17%"><%=(Integer)hm.get("tpfs") %>&nbsp;</td>
          <td width="18%">废票份数：</td>
          <td><%=(Integer)hm.get("fpfs") %>&nbsp;</td>
        </tr>
        <tr>
          <td>正常票金额（元）：</td>
          <td><%=(Double)hm.get("zcpzje") %>&nbsp;</td>
          <td>退票金额（元）：</td>
          <td><%=(Double)hm.get("tpzje") %>&nbsp;</td>
          <td>实际开票金额（元）：</td>
          <td><%=(Double)hm.get("zcpzje")-(Double)hm.get("tpzje")%>&nbsp;</td>
        </tr>

	  </table>
<%
	}
}
%>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="50" align="center">
<%
if(sbflag==0){
%>
			<input type="button" name="jxdk" value="监控回传" onclick="jxdk();" style="cursor:hand;" />&nbsp;&nbsp;
			<jsp:plugin name="dtapplet" type="applet" code="com.jsdt.web.applet.TYUcReadlet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
		 
<%
}
else{
%>
系统正在计算汇总数据，请等待...      
<%
}
%>  			  
          </td>
        </tr>
      </table>
    </div>
</div>
</body>
</html>