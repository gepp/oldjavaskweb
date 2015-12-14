<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%@page import="java.text.DecimalFormat"%>

<%
ArrayList alFpkj = (ArrayList)request.getAttribute("alFpkj");
DecimalFormat dg = new DecimalFormat("0.00");

int pageSize = Integer.parseInt((String)request.getAttribute("pageSize"));
int pageNo = Integer.parseInt((String)request.getAttribute("pageNo"));
int maxPage = Integer.parseInt((String)request.getAttribute("maxPage"));
int maxCount = Integer.parseInt((String)request.getAttribute("maxCount"));
double kpzje = Double.parseDouble((String)request.getAttribute("kpzje"));

String nsrwjbm = (String)request.getAttribute("nsrwjbm");
String startdate = (String)request.getAttribute("startdate");
String enddate = (String)request.getAttribute("enddate");
String fpqsh = (String)request.getAttribute("fpqsh");
String fpjzh = (String)request.getAttribute("fpjzh");
String fpdm = (String)request.getAttribute("fpdm");
//String swjgbm = (String)request.getAttribute("swjgbm");
String fplx = (String)request.getAttribute("fplx");
String up_show = "";
String down_show = "";

if(pageNo==maxPage){
	if(pageNo==1){
		up_show 	= "disabled";
		down_show 	= "disabled";
	}
	else{
		down_show 	= "disabled";
	}
}
else{
	if(pageNo==1){
		up_show 	= "disabled";
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="/javaskweb/js/jquery-1.4.2.js"></script>
<link href="images/css.css" rel="stylesheet" type="text/css" />
<script language="javascript">
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
function seeFp(fpdm,fphm){
	window.open('/javaskweb/fpxsServlet?method=import&fpdm='+fpdm+'&fphm='+fphm,'','width=830,height=670,top=100,left=100');
}

function firstp(){
	var pageNo 			= 1;
	goto1(pageNo)
}
function goupp(){
	var pageNo 			= document.all('pageNo').value*1-1;
	goto1(pageNo)
}
function downp(){
	var pageNo 			= document.all('pageNo').value*1+1;
	goto1(pageNo)
}
function caudap(){
	var pageNo 			= document.all('maxPage').value*1;
	goto1(pageNo)
}
function check_page(){
	var pageNo 		= document.all('pageNo').value;
	var maxPage 	= document.all('maxPage').value;
	if(pageNo>maxPage){
		document.all('pageNo').value = maxPage;
	}
	else if(pageNo<1){
		document.all('pageNo').value = 1;
	}
	else{
		document.all('pageNo').value = 1;
	}
}
function goto(){
	check_page();
	var pageNo 		= document.all('pageNo').value;
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	var swjgbm 		= document.all('swjgbm').value;
	var fplx 		= document.all('fplx').value;
	
	window.location.href='/javaskweb/cxtj.do?op=toFpkjList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&swjgbm='+swjgbm+'&fplx='+fplx;
}
function goto1(pageNo){
	var pageSize 	= document.all('pageSize').value;
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	var swjgbm 		= document.all('swjgbm').value;
	var fplx 		= document.all('fplx').value;
	window.location.href='/javaskweb/cxtj.do?op=toFpkjList&pageSize='+pageSize+'&pageNo='+pageNo+'&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&swjgbm='+swjgbm+'&fplx='+fplx;
//	$.ajax({ 
//		   type:'post', 
//		   url:'/javaskweb/cxtj.do?op=toFpkjList', 
//		   cache:false, 
//		   async:true, 
//		   data:{pageSize:pageSize,pageNo:pageNo,nsrwjbm:nsrwjbm,startdate:startdate,enddate:enddate,fpqsh:fpqsh,fpjzh:fpjzh,swjgbm:swjgbm,fpdm:fpdm,fplx:fplx}
//	});
}

function dc(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	var skygh=document.all('skygh').value;
//	var skyxm=document.all('skyxm').value;
document.all('iframe').src = '/javaskweb/cxtj/fpkjexcel.jsp?nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh;
}

function dczcpxml(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	var skygh       =document.all('skygh').value;
	sAlert('明细导出中，请稍等...');
	$.ajax({
		 url:'/javaskweb/cxtj.do?op=toFpkjxml&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh+'&type=1',
		 type:'post',
		 async:true, 
		 success:function(data){
			 div_close();
			 alert(data);
			 }
          })
	
	//document.all('iframe').src = '/javaskweb/cxtj/fpkjxml.jsp?nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh;
}

function dczfpxml(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	var skygh       =document.all('skygh').value;
	sAlert('明细导出中，请稍等...');
	$.ajax({
		 url:'/javaskweb/cxtj.do?op=toFpkjxml&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh+'&type=3',
		 type:'post',
		 async:true, 
		 success:function(data){
			 div_close();
			 alert(data);
			 }
          })
	
	//document.all('iframe').src = '/javaskweb/cxtj/fpkjxml.jsp?nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh;
}
function dchzpxml(){
	var nsrwjbm 	= document.all('nsrwjbm').value;
	var startdate 	= document.all('startdate').value;
	var enddate 	= document.all('enddate').value;
	var fpqsh 		= document.all('fpqsh').value;
	var fpjzh 		= document.all('fpjzh').value;
	var fpdm 		= document.all('fpdm').value;
	var skygh       =document.all('skygh').value;
	sAlert('明细导出中，请稍等...');
	$.ajax({
		 url:'/javaskweb/cxtj.do?op=toFpkjxml&nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh+'&type=2',
		 type:'post',
		 async:true, 
		 success:function(data){
			 div_close();
			 alert(data);
			 }
          })
	
	//document.all('iframe').src = '/javaskweb/cxtj/fpkjxml.jsp?nsrwjbm='+nsrwjbm+'&startdate='+startdate+'&enddate='+enddate+'&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&skygh='+skygh;
}
 
</script>
</head>
<body>
<!--==========right部分==========-->
<div id="right" style="z-index: -1">
  <div id="main" style="z-index: -1">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tableListTitle">
      <tr>
        <td width="51%" valign="bottom"><div class="listTitleBlue"><a href="JAVASCRIPT:">发票明细查询列表(${total_time })</a></div></td>
        <td align="right">
        <input type="button" name="dcexcel" value="导出EXCEL" onclick="dc();">&nbsp;&nbsp;
        <input type="button" name="dcexcel" value="导出正常票XML" onclick="dczcpxml();">&nbsp;&nbsp;
        <input type="button" name="dcexcel" value="导出作废票XML" onclick="dczfpxml();">&nbsp;&nbsp;
        <input type="button" name="dcexcel" value="导出红字票XML" onclick="dchzpxml();">&nbsp;&nbsp;
        </td>
        
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList2">
      <tr>
         <th width="10%">纳税人微机编码</th>
        <th width="15%">纳税人名称</th>
        <th width="8%">发票号码</th>
        <th width="8%">项目名称</th>
        <th width="6%">单价</th> 
        <th width="6%">数量</th> 
        <th width="6%">开票时间</th>
        <th width="7%">开票类型</th>
        <th width="8%">总金额</th>
         <th width="8%">折扣总金额</th>
        <th width="8%">收款员姓名</th>
          
        <th width="12%">付款单位名称</th>
        <th>查看详情</th>
      </tr>
<%
String gh="";
//Util.fpIntToString(fpkj.getFphm());
				
				int befor_fphm=0;
				int now_fphm=0;
				String befor_fphm_string="";
				String now_fphm_string="";
				String hjzje_string="";
				String zkzje_string="";
if(alFpkj!=null&&!alFpkj.isEmpty()){
	Iterator it = alFpkj.iterator();
	double allhjzje = 0;
	double zcpzje = 0;
	double tpzje = 0;
	int zcpfs = 0;
	int tpfs = 0;
	int fpfs = 0;
	String hjzje = "";
	
	while(it.hasNext()){
		Fpkj fpkj = (Fpkj)it.next();
		double dj=fpkj.getDj();
		double sl=fpkj.getSl();
		int kplx = fpkj.getKplx();
		String kplxmc = "";
		 
		if(kplx==1){
			kplxmc = "正常票";
			allhjzje +=  fpkj.getHjzje();
			zcpfs = zcpfs+1;
			zcpzje = zcpzje+fpkj.getHjzje();
			
			hjzje = dg.format(fpkj.getHjzje());
		}
		else if(kplx==2){
			kplxmc = "退票";
			allhjzje -=  fpkj.getHjzje();
			tpfs = tpfs+1;
			tpzje = tpzje+fpkj.getHjzje();
			
			hjzje = "<font color=\"#FF0000\">"+dg.format(fpkj.getHjzje()*-1)+"</font>";
		}
		else if(kplx==3){
			kplxmc = "废票";
			fpfs = fpfs+1;
			hjzje = "0.00";
		}
	 
		double zkzje=fpkj.getZkzje();
	 
		if(zkzje==0){
			zkzje=0.00;
		}
		String zkzjes=dg.format(zkzje); 
		if(befor_fphm==0)
		{
		befor_fphm=fpkj.getFphm();
		now_fphm=fpkj.getFphm();
		now_fphm_string=Util.fpIntToString(now_fphm);
		hjzje_string="<b>"+hjzje+"(合计)"+"</b>";
		zkzje_string=zkzjes;
		}
		else{
			now_fphm=fpkj.getFphm();
			if(now_fphm==befor_fphm){
				now_fphm_string="";
				hjzje_string=dg.format(dj*sl);
				zkzje_string="";
			}else{
				now_fphm_string=Util.fpIntToString(now_fphm);
				hjzje_string="<b>"+hjzje+"(合计)"+"</b>";
				befor_fphm=now_fphm;
				zkzje_string=zkzjes;
			}
		}
		
	    
//	    System.out.println("befor_fphm:"+befor_fphm);
//	    System.out.println("now_fphm:"+now_fphm);
		String sky=fpkj.getSky();
		if(Util.hasNumber(sky)){
			if(sky.equals("000000")){
				 gh="01";
				 sky="000000";
			}else{
			 if(gh!=null&&gh.length()>2){
			 gh=sky.substring(0,2);
			 sky=sky.substring(2,sky.length());
			 }
			}
		}else{
			gh="";
		}
		
		
%>
	  <tr>
        <td align="center"><%=fpkj.getNsrwjbm() %>&nbsp;</td>
        <td align="center"><%=fpkj.getNsrmc() %>&nbsp;</td>
        <td align="center"><%=now_fphm_string %>&nbsp;</td>
        <td align="center"><%=fpkj.getXmmc() %>&nbsp;</td>
		<!-- 项目名称 -->
		<td align="center"><%=dj %></td>
		<td ALIGH="center"><%=sl %></td>
		<td align="center"><%=fpkj.getKprq() %>&nbsp;</td>
        <td align="center"><%=kplxmc %>&nbsp;</td>
        <td align="center"><%=hjzje_string %>&nbsp;</td>
        <td align="center"><%=zkzje_string %>&nbsp;</td>
        <td align="center"><%=sky %>&nbsp;</td>
         
        <td align="center"><%=fpkj.getFkdw().equals("000000")?"个人":fpkj.getFkdw() %>&nbsp;</td>
        <td align="center">
<% 
if(fpkj.getXms()>0){
%>
		<input type="button" name="btn" value="详情 " onclick="seeFp('<%=fpkj.getFpdm() %>','<%=fpkj.getFphm() %>');" />
<%
}
%>
        	&nbsp;</td>
      </tr>
<%
	}
%>
	  <tr>
        <td align="center"><font color="red"><strong>汇总：</strong></font>&nbsp;</td>
        <td align="left" colspan="9">正常票份数：<%=zcpfs %>份&nbsp;退票份数：<%=tpfs %>份&nbsp;废票份数：<%=fpfs %>份<br/>正常发票累计金额：<%=dg.format(zcpzje) %>&nbsp;退票累计金额：<%=dg.format(tpzje) %></td>
      </tr>
	  <tr>
        <td align="center"><font color="red"><strong>分页合计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(allhjzje) %></strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
      </tr>
      <tr>
        <td align="center"><font color="red"><strong>总计：</strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center">&nbsp;</td>
        <td align="center"><font color="red"><strong><%=dg.format(kpzje) %></strong></font>&nbsp;</td>
        <td align="center">&nbsp;</td>
      </tr>
<%
}
%>
      
    </table>
    <table id="tablefoot" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left">&nbsp;&nbsp;
          <input type="button" name="firstp_btn" value="首 页" onClick="firstp()" <%=up_show%>>
          <input type="button" name="goupp_btn" value="上 页" onClick="goupp()" <%=up_show%>>
          <input type="button" name="downp_btn" value="下 页" onClick="downp()" <%=down_show%>>
          <input type="button" name="caudap_btn" value="尾 页" onClick="caudap()" <%=down_show%>>
        </td>
        <td align="right">每页
          <input type="text" value="<%=pageSize%>" name="pageSize" style="width:30px;">
          条记录 跳转到第
          <input type="text" value="<%=pageNo%>" name="pageNo" style="width:30px;">
          页 
          <input type="button" name="go_btn" value="GO" onClick="goto1()" />
          第<%=pageNo%>页/共<%=maxPage%>页 共<%=maxCount%> 条记录&nbsp;&nbsp;&nbsp;&nbsp; 
          <input type="hidden"  name="maxPage" value="<%=maxPage%>">
	 	  <input type="hidden" name="maxCount" value="<%=maxCount%>" />
	 	  <input type="hidden" name="nsrwjbm" value="<%=nsrwjbm%>" />
	 	  <input type="hidden" name="startdate" value="<%=startdate%>" />
	 	  <input type="hidden" name="enddate" value="<%=enddate%>" />
	 	  <input type="hidden" name="fpqsh" value="<%=fpqsh%>" />
	 	  <input type="hidden" name="fpjzh" value="<%=fpjzh%>" />
	 	  <input type="hidden" name="fpdm" value="<%=fpdm%>" />
	 	  <input type="hidden" name="swjgbm" value="" />
	 	  <input type="hidden" name="fplx" value="<%=fplx%>" />
	 	  <input type="hidden" name="skygh" value="<%=request.getAttribute("skygh") %>" />
        </td>
      </tr>
    </table>
    <iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
  </div>
</div>
</body>
 
</html>

