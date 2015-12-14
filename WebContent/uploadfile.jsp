<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线发票明细上传</title>
<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
</head>
<body>
 <form id="myform" enctype="multipart/form-data" action="/javaskweb/OnlineFileUploadServlet" method="post">
 <table>
 <tr>
 <td><font color="red">请选择MX000001.BIN</font></td>
 <td> <input type="file" name="MX000001.BIN"  value="" />*</td>
 </tr>
 <tr>
 <td><font color="red">请选择MXSY.BIN</font></td>
 <td><input type="file" name="MXSY.BIN"  value="" />*</td>
 </tr>
  <tr>
 <td><input id="tosubmit" type="submit" value="上传明细" /> </td>
 <td><input id="reset" type="reset" value="取消上传" /></td>
  
 </tr>
 </table>
 </form>
</body>
<script type="text/javascript">
function sAlert(str){
    var msgw,msgh,bordercolor;
    msgw=400;//提示窗口的宽度
    msgh=100;//提示窗口的高度
    titleheight=25 //提示窗口标题高度
    bordercolor="#c51100";//提示窗口的边框颜色
    titlecolor="#c51100";//提示窗口的标题颜色
    
    var sWidth,sHeight;
    sWidth=screen.width-50;
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
function div_close(){
	var bgObj=document.getElementById("bgDiv");
	var msgObj=document.getElementById("msgDiv");
    document.body.removeChild(bgObj);
	document.body.removeChild(msgObj);
}

$(document).ready(function() {
	
 
	$("#tosubmit").click(function(){ 
	   sAlert("明细导入中，请稍后...");
	    
	$("#myform").ajaxForm({
		success:function(msg){//文件上传成功后执行,msg为服务器端返回的信息
			div_close();
			alert(msg); 
			$("#reset").click();
		}
	});
	 
	});
	 
	}); 
 
</script>
</html>