<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<script language="javascript" src="../images/Calendar2.js"></script>
<script type="text/javascript" src="../images/CheckBrower.js"></script>
<script language="javascript">
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}
function add(){
	//var swjgbm = trim(document.getElementById('swjgbm').value);
	var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
	if(nsrwjbm==''){
		alert('请输入纳税人微机编码');
	}
	else{
		var startdate = document.getElementById('startdate').value;
		var enddate = document.getElementById('enddate').value;
		var fpdm =trim(document.getElementById('fpdm').value);
		var fpqsh =trim(document.getElementById('fpqsh').value);
		var fpjzh =trim(document.getElementById('fpjzh').value);
	    var skygh=trim(document.getElementById('skygh').value);
	   
		parent.document.getElementById('fpkjList').src = '/javaskweb/cxtj.do?op=toFpkjList&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpdm='+fpdm+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&skygh='+skygh;
		//div_close();
	}
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
function deleteFpkj(id){
	if(confirm('请确认是否删除？')){
		sAlert('明细删除中，请稍等...');
		$.ajax({
		 type: "POST",
		 url: "/javaskweb/taxpayer.do?op=deleteRepeatFpkj",
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
function deleteFpkjmx(){
	var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
	if(nsrwjbm==''){
		alert('请输入纳税人微机编码');
	}
	var startdate = document.getElementById('startdate').value;
	var enddate = document.getElementById('enddate').value;
	if(startdate==''){
		alert('请输入开始时间');
	}
	if(enddate==''){
		alert('请输入截止时间');
	}
	if(confirm('请确认是否删除'+nsrwjbm+'起始时间：'+startdate+'到截止时间：'+enddate+'段内明细？')){
		sAlert('明细删除中，请稍等...');
		$.ajax({
		 type: "POST",
		 url: "/javaskweb/taxpayer.do?op=deleteRepeatFpkjmx&nsrwjbm="+nsrwjbm+"&startdate="+startdate+"&enddate="+enddate,
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
<div id="right">
  <div id="main" style="z-index: 1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="18%">纳税人微机编码：</td>
        <td><input type="text" name="nsrwjbm" id="nsrwjbm" maxlength="16" style="width: 270px;"><div id="auto"></div>
        
          收款员： 
         <input type="text" name="skygh" id="skygh"   />
     
        </td>
     </tr>
      <!--  <tr>
        <td>税务机关：</td>
        <td><input type="text" name="swjgmc" id="swjgmc" readonly style="width:50%" onClick="window.open('/javaskweb/cxNsrxxServlet?method=cxswjg','cxswjg','width=600,height=600,top=200,left=200,scrollbars=yes');">&nbsp;&nbsp;
        <input type="button" name="swjg_btn" value="选择税务机关" onClick="window.open('/javaskweb/cxNsrxxServlet?method=cxswjg','cxswjg','width=600,height=600,top=200,left=200,scrollbars=yes');">
        <input type="hidden" id="swjgbm" name="swjgbm">
        </td>
      </tr> -->
      <tr>
        <td width="18%">开票时间：</td>
        <!--  
        <td>开始时间：<input type="text" id="startdate" name="startdate" style="width:15%" onFocus="winOpen();" readonly>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        截止时间：&nbsp;&nbsp;&nbsp;<input type="text" name="enddate" style="width:15%" onClick="winopen_end();" readonly></td>
           -->  
        <td>开始时间：<input id="startdate" type="text" onfocus="WdatePicker({isShowWeek:true})"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        截止时间：&nbsp;&nbsp;&nbsp;<input id="enddate" type="text" onfocus="WdatePicker({isShowWeek:true})"/></td>
      </tr>
      <tr>
        <td width="18%">发票号码段：</td>
        <td>发票代码：<input type="text" id="fpdm" name="fpdm" style="width:15%" maxlength="12" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        发票号码起：<input type="text" id="fpqsh" name="fpqsh" style="width:15%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        发票号码止：<input type="text" id="fpjzh" name="fpjzh" style="width:15%" ></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="button" name="add_btn" value=" 查 询 " onClick="add();">&nbsp;&nbsp;
          <input type="button" name="ret_btn" value=" 重 置 " onClick="window.location.reload();">
          <input type="button" name="delete" value="删除重复明细数据" onclick="deleteFpkj();" />
          <input type="button" name="delete" value="删除时间段内明细" onclick="deleteFpkjmx();" />
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
