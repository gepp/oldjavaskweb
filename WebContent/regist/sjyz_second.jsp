<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%
//String WJBMBC = (String)request.getAttribute("WJBMBC");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="/javaskweb/js/jquery-1.4.2.js"></script>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}

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
function add(){
	
	if(confirm('确定进行初始化网票机与税控器后台整合？')){
		sAlert('数据整合中，请稍等...');
		$.ajax({
		 type: "POST",
		 url: "/javaskweb/sjyzServlet?method=yz_second",
		 success: function(msg){
			 		if(msg){
			 			div_close();
			 			alert(msg);
						
					}else{
						div_close()
						alert(msg);	
					}
			 	} 
		}); 		
	}
}
</script>
</head>

<body>
<!--==========right部分==========-->
<div id="right">
<div id="tool">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle" style="padding-top:14px;">
      <tr>
      	<td style="font-size:16px; font-weight:800;">&nbsp;&nbsp;税控基础设置 &gt;&gt;&nbsp;&nbsp;<a href="#" class="nav">网票机与税控器后台整合</a></td>
      </tr>
    </table>
  </div>
  <div id="main"> <br />
      <br />
      <form action="" method="post">
	  <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" id="userLogin" >
      <tr>
        <th width="5%">网票机与税控器后台整合</th>
        </tr>
      
      <tr>
        <td height="152" style="text-align:center"><br />
          <input type="button" name="next_btn" value="网票机与税控器后台整合" onclick="add();" style="cursor:hand;"/>
          <br />
          <br />
          <br />
          </td>
        </tr>
    </table>
	  </form>
      <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
</html>