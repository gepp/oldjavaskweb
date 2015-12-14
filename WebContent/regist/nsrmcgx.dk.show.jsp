<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>
<%
HashMap CARDINFO = (HashMap)request.getAttribute("CARDINFO");
HashMap EF01 = (HashMap)CARDINFO.get("EF01");
HashMap EF02 = (HashMap)CARDINFO.get("EF02");
ArrayList EF041 = (ArrayList)CARDINFO.get("EF04");
HashMap EF05 = (HashMap)CARDINFO.get("EF05");

String nsrmc = (String)request.getAttribute("nsrmc");

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
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

function add(){
	if(confirm('确定税控卡已插入！')){
		sAlert('读卡中，请等待……');
		try{
			var result = document.dtapplet.updateFCName();
			//var result1 = document.ReadInvoiceApplet.read();
			if(result==1){
				div_close();
				alert('纳税户名称更新成功！');
				window.location.href='/javaskweb/nsrmcgxServlet?method=import';
			}
			else{
				div_close();
				alert('纳税户名称更新失败！');
			}
		}
		catch(e){
			div_close();
			alert('纳税户名称更新失败！');
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
  </div>
    <div id="main">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
        <tr>
          <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">信息</a></div></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userList1" id="tbl_EF02">
        <tr>
          <td width="20%">税控卡号：</td>
          <td width="30%"><%=(String)EF02.get("SKKH") %>&nbsp;</td>
          <td width="20%">机器编号：</td>
          <td width="30%"><%=(String)EF02.get("JQBH") %>&nbsp;</td>
        </tr>
        <tr>
          <td>纳税人编码：</td>
          <td><%=(String)EF02.get("NSRWJDM") %>&nbsp;</td>
          <td>纳税人识别号：</td>
          <td><%=(String)EF02.get("NSRSBH") %>&nbsp;</td>
        </tr>
        <tr>
          <td>原纳税人名称：</td>
          <td><%=(String)EF02.get("NSRMC") %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>更新后纳税人名称：</td>
          <td><%=nsrmc %>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" align="center">
            <input type="hidden" name="new_name" id="new_name" value="<%=nsrmc %>" />
            <input type="button" name="btn" value=" 更 新 " style="cursor:hand;" onclick="add();" />
            <jsp:plugin name="dtapplet" type="applet" archive="dtapplet.jar" codebase="." code="com.jsdt.web.applet.UpdateFClet.class" height="1" width="1" >
<jsp:params>  
  <jsp:param name="serverUrl" value="<%=basePath%>"   />
</jsp:params>
</jsp:plugin>
          </td>
        </tr>
      </table>
    </div>
</div>
</body>
</html>