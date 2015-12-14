<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="jsdt.tools.*"%>
<%
//String WJBMBC = SYSTEM.WJBMBC;
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
System.out.println("basePath = "+basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
function add(){
	if(confirm('确定用户卡已插入！')){
		sAlert('读卡中，请等待……');
		try{
			var result = document.dtapplet.read();
			//var result1 = document.ReadInvoiceApplet.read();
			if(result==1){
				div_close();
				window.location.href='/javaskweb/fpService.do?op=info';
			}
			else{
				div_close();
				alert('卡基本信息读取失败！');
			}
		}
		catch(e){
			div_close();
			alert('卡基本信息读取失败！');
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
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td>&nbsp;&nbsp;发票管理 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">发票领购</a></td>
        <td align="right">&nbsp;&nbsp;&nbsp;&nbsp;</td>
      </tr>
    </table>
  </div>
    <div id="main"> <br />
      <br />
      <form name="form1" action="" method="post">
      <table width="50%" border="0" align="center" cellpadding="0" cellspacing="0" id="userLogin1" >
        <tr>
          <th width="5%">购买发票 </th>
        </tr>
        <tr>
          <td height="152" style="text-align:center">
          
          <br>
          <input type="hidden" name="userinfo" />
          <input type="button" name="btn" value=" 读 卡 " style="cursor:hand;" onclick="add();" />
          <jsp:plugin name="dtapplet" type="applet" archive="dtapplet.jar" codebase="." code="com.jsdt.web.applet.TYUcReadlet.class" height="1" width="1" >
<jsp:params>  
  <jsp:param name="serverUrl" value="<%=basePath%>"   />
</jsp:params>
</jsp:plugin>
<!--            <applet name="dtapplet" code="com.jsdt.web.applet.TYUcReadlet.class" codebase="." archive="dtapplet.jar" width="1" height="1"></applet>-->
           </td>
        </tr>
        </table>
        </form>
        <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
    </div>
</div>
</body>
</html>