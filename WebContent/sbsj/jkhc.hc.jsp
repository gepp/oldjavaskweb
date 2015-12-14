<%@ page contentType = "text/html;charset=utf-8" language="java"%> 
<%@ page import="java.util.*" %>
<%@page import="jsdt.tools.*"%>
<%@ page import="java.text.DecimalFormat"%>
<%@page import="jsdt.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
int ENDMONTH = SYSTEM.ENDMONTH;
int ENDDAY = SYSTEM.ENDDAY;


HashMap hmJkhc = (HashMap)request.getAttribute("hmJkhc");
Jqxx jqxx = (Jqxx)hmJkhc.get("jqxx");
Nsrxx nsrxx = (Nsrxx)hmJkhc.get("nsrxx");
String old_nsrwjbm = (String)hmJkhc.get("old_nsrwjbm");
String kpjzrq = (String)hmJkhc.get("kpjzrq");
String[] smxkArr = (String[])hmJkhc.get("smxkArr");
System.out.println(smxkArr);
DecimalFormat dg=new DecimalFormat("0.00");
String xckpjzrq = (String)hmJkhc.get("xckpjzrq");

String QYRQ = (String)hmJkhc.get("QYRQ");
String YXRQ = (String)hmJkhc.get("YXRQ");
String MXSBBZ = (String)hmJkhc.get("MXSBBZ");
String JQLX = (String)hmJkhc.get("JQLX");
String JQSL = (String)hmJkhc.get("JQSL");
String LXBS = (String)hmJkhc.get("LXBS");
String YYBB = (String)hmJkhc.get("YYBB");
String FCI = (String)hmJkhc.get("FCI");
String MAC = (String)hmJkhc.get("MAC");

String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
System.out.println(basePath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监控回传信息</title>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="images/Calendar1.js" ></script>
<script language="javascript" src="images/validator.js"></script>
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
function declareback(){
	var KPJZRQ = document.all('KPJZRQ').value;
	var MAKE_END_DATE = document.all('MAKE_END_DATE').value;
	var MAC=document.getElementById('MAC').value;

	 
	if(MAKE_END_DATE<=KPJZRQ){
		alert('申报日期必须大于  '+KPJZRQ);
	}
	else
 
		if(MAC=='null'||MAC==''){
		alert('MAC为空，请重新插卡进行监控回传！');
	}
	 
	else{
		var frm = document.form1;
		if(validator(frm)){
			document.all('MAKE_MAX_SINGLE').value = document.all('MAKE_MAX_SINGLE1').value*100;
			document.all('MAKE_MAX_SUM').value = document.all('MAKE_MAX_SUM1').value*100;
			document.all('BACK_MAX_SUM').value = document.all('BACK_MAX_SUM1').value*100;
			
			sAlert('清零解锁中，请等待……');
			try{
				var v = document.DeclareBackApplet.declardBackData();
				
				if(v == 1){
					var JQBH = document.all('JQBH').value;
					var src = "/javaskweb/sbsj.do?op=updateKpxe&kpjzrq="+document.all('MAKE_END_DATE').value+"&jqbh="+JQBH+"&dzkpxe="+document.all('MAKE_MAX_SINGLE1').value+"&yljkpxe="+document.all('MAKE_MAX_SUM1').value+"&yljtpxe="+document.all('BACK_MAX_SUM1').value+'&mac='+document.all('MAC').value;
					document.all('updateJZRQ').src = src;
					alert("清零解锁成功");
					window.close();
				}
				else{
					div_close()
					alert("清零解锁失败");
					
				}
			}
			catch(e){
				div_close()
				alert("清零解锁失败!");
			}
		}
	}
	
}

function changeDate(){
	var month_num = document.all('MAKE_END_DATE_MONTH').value*1;
	var day = document.all('MAKE_END_DATE_DAY').value*1;
	var end_date = document.all('end_date').value;
	var nowDate = end_date.substring(0,4)+'/'+end_date.substring(4,6)+'/'+end_date.substring(6,8);
	
	var date = dateAdd('m',  month_num,  nowDate);
	
	var year = date.getFullYear();
	var month = date.getMonth()*1+1;
	if(month<10){
		month = '0'+month;
	}
	if(day>=28){
		day=28
	}
	if(day<10){
		day = '0'+day;
	}
	var MAKE_END_DATE = year+""+month+""+day;
	document.all('MAKE_END_DATE').value = MAKE_END_DATE;
}

function  dateAdd(strInterval,  NumDay,  dtDate)  {  

    var  dtTmp  =  new  Date(dtDate);  
    if  (isNaN(dtTmp))  dtTmp  =  new  Date();  
    switch  (strInterval)  {  
     case  "s":return  new  Date(Date.parse(dtTmp)  +  (1000  *  NumDay));  
     case  "n":return  new  Date(Date.parse(dtTmp)  +  (60000  *  NumDay));  
     case  "h":return  new  Date(Date.parse(dtTmp)  +  (3600000  *  NumDay));  
     case  "d":return  new  Date(Date.parse(dtTmp)  +  (86400000  *  NumDay));  
     case  "w":return  new  Date(Date.parse(dtTmp)  +  ((86400000  *  7)  *  NumDay));  
     case  "m":return  new  Date(dtTmp.getFullYear(),  (dtTmp.getMonth())  +  NumDay,  dtTmp.getDate(),  dtTmp.getHours(),  dtTmp.getMinutes(),  dtTmp.getSeconds());  
     case  "y":return  new  Date((dtTmp.getFullYear()  +  NumDay),  dtTmp.getMonth(),  dtTmp.getDate(),  dtTmp.getHours(),  dtTmp.getMinutes(),  dtTmp.getSeconds());  
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

<!--<body onLoad="changeDate();">-->
<body onload="changeDate();">
<!--==========right部分==========-->
<div id="right">
    <div id="main">
    <form action="" method="post" name="form1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
        <tr>
          <th colspan="2">监控回传信息</th>
        </tr>
        <tr>
          <td colspan="2"><strong><font color="#FF0000">上次开票截止日期是：<%=kpjzrq%>,本次申报日期必须大于该日期</font></strong></td>
        </tr>
        <tr>
          <td>下次申报截止日期：</td>
          <td><input type="text" id="MAKE_END_DATE" name="MAKE_END_DATE" size="20"  class="input" value="<%=xckpjzrq %>" onclick="SelectDate(this)"/>
          <input type="hidden" id="end_date" name="end_date" value=""/>
          <input type="hidden" id="MAKE_END_DATE_MONTH" name="MAKE_END_DATE_MONTH" size="20" class="input" value="<%=ENDMONTH%>"  onblur="changeDate();"/>
          <input type="hidden" id="MAKE_END_DATE_DAY" name="MAKE_END_DATE_DAY" size="20"  class="input" value="<%=ENDDAY%>" onblur="changeDate();"/>
          <input type="hidden" name="KPJZRQ" value="<%=kpjzrq%>" id="KPJZRQ" />
          </td>
        </tr>
        <tr>
          <td>单笔开票最高限额（元）：</td>
          <td><input type="text" name="MAKE_MAX_SINGLE1" size="20" class="input" value="<%=dg.format(jqxx.getDzkpxe()) %>" dataBetween="1,42000000"/>
          <input type="hidden" name="MAKE_MAX_SINGLE" id="MAKE_MAX_SINGLE" />
          </td>
        </tr>
       
        
        <tr>
          <td>月累计开票最高限额（元）：</td>
          <td>
          <input type="text" value="<%=dg.format(Double.valueOf(jqxx.getYljkpxe())) %>" name="MAKE_MAX_SUM1" size="20"  class="input"  dataBetween="1,42000000"/>
          <input type="hidden" name="MAKE_MAX_SUM" id="MAKE_MAX_SUM" />
          </td>
        </tr>
        <tr>
          <td>月累计退票最高限额（元）：</td>
          <td>
            <input type="text" name="BACK_MAX_SUM1" size="20"  class="input" value="<%=dg.format(jqxx.getYljtpxe()) %>" dataBetween="1,42000000"/>
            <input type="hidden" name="BACK_MAX_SUM" id="BACK_MAX_SUM" />
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListBottom">
        <tr>
          <td height="50" align="center">
          
            <%
		if(smxkArr!=null){
			ArrayList smxkArr1 =new ArrayList();
			for(int j=0;j<smxkArr.length;j++){
				System.out.println("smxkArr.length:"+smxkArr.length);
				String a=smxkArr[j];
				if(a!=null){
				String smbm=smxkArr[j].split(",")[1];
				System.out.println(j+":"+(a!=null));
				smxkArr1.add(smxkArr[j]);
				}
				System.out.println(smxkArr[j]);
				System.out.println("smxkArr1大小："+smxkArr1.size());
			}
			 System.out.println("1231231231231");
			for(int i=0;i<20;i++){
				String s="";
				System.out.println("s:"+s+"i:"+i);
				 if(i>=smxkArr1.size()){
					 s="";
				 }else{
					s=(String)smxkArr1.get(i);
				 }
				 System.out.println("s:"+s);
				%>
				<input type="hidden" id="tax<%=i%>" name="tax<%=i%>" value="<%=s%>" />
				<%
			}
		}
		%>
           <input type="hidden" id="TAXPAYER_NO" name="TAXPAYER_NO" value="<%=old_nsrwjbm%>" />
           <input type="hidden" id="JQBH" name="JQBH" value="<%=jqxx.getJqbh() %>" />
           <input type="hidden" id="SKKH" name="SKKH" value="<%=jqxx.getSkkh() %>" />
           <input type="hidden" id="TAXPAYER_NAME" name="TAXPAYER_NAME" value="<%=nsrxx.getNsrmc().trim() %>">
           <input type="hidden" id="QYRQ" name="QYRQ" value="<%=QYRQ%>">
           <input type="hidden" id="YXRQ" name="YXRQ" value="<%=YXRQ%>">
           <input type="hidden" id="MXSBBZ" name="MXSBBZ" value="<%=MXSBBZ%>">
           <input type="hidden" id="JQLX" name="JQLX" value="<%=JQLX%>">
           <input type="hidden" id="JQSL" name="JQSL" value="<%=JQSL%>">
           <input type="hidden" id="LXBS" name="LXBS" value="<%=LXBS%>">
           <input type="hidden" id="YYBB" name="YYBB" value="<%=YYBB%>">
           <input type="hidden" id="FCI" name="FCI" value="<%=FCI%>">
           <input type="hidden" id="MAC" name="MAC" value="<%=MAC%>">

			<input type="button" name="addBtn" value="确 定" onClick="declareback();"/>
			<jsp:plugin name="DeclareBackApplet" type="applet" code="com.jsdt.web.applet.DeclareBacklet.class" codebase="." archive="dtapplet.jar" width="1" height="1">
			<jsp:params>  
			  <jsp:param name="serverUrl" value="<%=basePath%>"   />
			</jsp:params>
			</jsp:plugin>
			<!--  
            <applet name="DeclareBackApplet" code="com.jsdt.web.applet.DeclareBacklet.class" codebase="." archive="dtapplet.jar" width="1" height="1"></applet>
			-->
			<iframe src="" name="updateJZRQ" frameborder="0" width="0" height="0"></iframe>
		  </td>
        </tr>
      </table>
      </form>
    </div>
</div>
</body>
</html>