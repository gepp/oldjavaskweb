<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>U盘明细申报</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
</head>
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
<body>
<table width="200" border="0" align="center">
  <tr>
    <td>
    <%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<jsp:plugin name="onlinFileUpload" type="applet" archive="fileupload.jar" codebase="." code="com.jsdt.netdeclare.fileupload.OnlineFileUploadApplet.class" height="500" width="538" >
<jsp:params>  
  <jsp:param name="serverUrl" value="<%=basePath%>"   />
</jsp:params>
<jsp:fallback>无法加载Applet</jsp:fallback>
</jsp:plugin>
    </td>
  </tr>
</table>



</body>
</html>