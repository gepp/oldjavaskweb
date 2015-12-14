//CaiShaoFa   2009-01-09 AM
//兼容IE 6.0/7.0 / FF 2.0
//Cai SHao Fa       2009-01-11 PM
chkBrowser();
function chkBrowser(){
window["csfBrowser"]={};(function()
{
if(csfBrowser.platform) return;
var ua = window.navigator.userAgent;
csfBrowser.platform = window.navigator.platform;
csfBrowser.firefox = ua.indexOf("Firefox")>-1;
csfBrowser.opera = typeof(window.opera)=="object";
csfBrowser.ie = !csfBrowser.opera && ua.indexOf("MSIE")>-1;
csfBrowser.mozilla = window.navigator.product == "Gecko";
csfBrowser.netscape= window.navigator.vendor=="Netscape";
csfBrowser.safari = ua.indexOf("Safari")>-1;
csfBrowser.width = window.screen.width;
if(csfBrowser.firefox) var re = /Firefox(\s|\/)(\d+(\.\d+)?)/;
else if(csfBrowser.ie) var re = /MSIE( )(\d+(\.\d+)?)/;
else if(csfBrowser.opera) var re = /Opera(\s|\/)(\d+(\.\d+)?)/;
else if(csfBrowser.netscape) var re = /Netscape(\s|\/)(\d+(\.\d+)?)/;
else if(csfBrowser.safari) var re = /Version(\/)(\d+(\.\d+)?)/;
else if(csfBrowser.mozilla) var re = /rv(\:)(\d+(\.\d+)?)/;
if("undefined"!=typeof(re)&&re.test(ua))csfBrowser.version = parseFloat(RegExp.$2);
})();
var app = navigator.appName;
var verStr = navigator.appVersion;
if(app.indexOf("Netscape") != -1){}else if(app.indexOf("Microsoft") != -1){
if(verStr.indexOf('MSIE 3.0') != -1 || verStr.indexOf('MSIE 4.0') != -1 || verStr.indexOf('MSIE 5.0') != -1 || verStr.indexOf('MSIE 5.1') != -1){window.parent.location.replace("/*.htm");}else{if(csfBrowser.version >= 6 && csfBrowser.version <= 8){}else{window.parent.location.replace("/*.htm");}
}
}
}
//Cai SHao Fa       2009-01-09 AM
//obj:getElementById
//val:当为null时,表示取innerText的值
function wrInnerText(obj,val)
{if(iBrowser.isNS6){if(val==null){return obj.innerText;}else{obj.innerText=val;}}else{if(val==null){return obj.textContent;}else{obj.textContent=val;}}}
var iBrowser={};
iBrowser.agt=navigator.userAgent.toLowerCase();
iBrowser.isW3C=document.getElementById ? true:false;
iBrowser.isIE=(iBrowser.agt.indexOf("msie")!=-1) && (iBrowser.agt.indexOf("opera")==-1) && (iBrowser.agt.indexOf("omniweb")==-1);
iBrowser.isNS6=iBrowser.isW3C && (navigator.appName!="Netscape");
iBrowser.isOpera=iBrowser.agt.indexOf("opera")!=-1;
iBrowser.isGecko=iBrowser.agt.indexOf("gecko")!=-1;
iBrowser.ieTrueBody=function(){return (document.compatMode && document.compatMode!="BackCompat")?document.documentElement:document.body;}
//
function PopupCalendar(InstanceName)
{
	///Global Tag
	this.instanceName=InstanceName;
	///Properties
	this.separator="-";
	this.oBtnTodayTitle="Today";
	this.oBtnCancelTitle="Cancel";
	this.weekDaySting=new Array("S","M","T","W","T","F","S");
	this.monthSting=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
	this.Width=200;
	this.currDate=new Date();
	this.today=new Date();
	this.startYear=2000;
	this.endYear=2050;
	///Css
	this.normalfontColor="#666666";
	this.selectedfontColor="red";
	this.divBorderCss="1px solid #BCD0DE";
	this.titleTableBgColor="#98B8CD";
	this.tableBorderColor="#CCCCCC";
	///Method
	this.Init=CalendarInit;
	this.Fill=CalendarFill;
	this.Refresh=CalendarRefresh;
	this.Restore=CalendarRestore;
	///HTMLObject
	this.oTaget=null;
	this.oPreviousCell=null;
	this.sDIVID=InstanceName+"_Div";
	this.sTABLEID=InstanceName+"_Table";
	this.sMONTHID=InstanceName+"_Month";
	this.sYEARID=InstanceName+"_Year";
	this.sTODAYBTNID=InstanceName+"_TODAYBTN";
	//val
	this.valId="DT1";
}

function CalendarInit()				///Create panel
{
	var htmlAll,sMonth,sYear
	sMonth=this.currDate.getMonth();
	sYear=this.currDate.getFullYear();
	htmlAll="<div id='"+this.sDIVID+"' style='display:none;width:"+this.Width+";border:"+this.divBorderCss+";padding:2px;background-color:#FFFFFF'>";
	htmlAll+="<div align='left'>";
	/// Month
	htmloMonth="<select id='"+this.sMONTHID+"' onchange='CalendarMonthChange("+this.instanceName+")' style='width:50%'>";
	for(i=0;i<12;i++)
	{			
		htmloMonth+="<option value='"+i+"'>"+this.monthSting[i]+"</option>";
	}
	htmloMonth+="</select>";
	/// Year
	htmloYear="<select id='"+this.sYEARID+"' onchange='CalendarYearChange("+this.instanceName+")' style='width:50%'>";
	for(i=this.startYear;i<=this.endYear;i++)
	{
		htmloYear+="<option value='"+i+"'>"+i+"</option>";
	}
	htmloYear+="</select></div>";
	/// Day
	htmloDayTable="<table id='"+this.sTABLEID+"' name='"+this.sTABLEID+"' width='100%' border=0 cellpadding=0 cellspacing=1 bgcolor='"+this.tableBorderColor+"'>";
	htmloDayTable+="<tbody bgcolor='#ffffff'style='font-size:13px'>";
	var cellDay="day0";
	var _instanceName=this.instanceName;
	var _valId=this.valId;
	for(i=0;i<=6;i++)
	{
		if(i==0)
			htmloDayTable+="<tr bgcolor='" + this.titleTableBgColor + "'>";
		else
			htmloDayTable+="<tr>";
		for(j=0;j<7;j++)
		{
			if(i==0)
			{
				htmloDayTable+="<td height='20' align='center' valign='middle' style='cursor:pointer'>";
				htmloDayTable+=this.weekDaySting[j]+"</td>"
			}
			else
			{
				htmloDayTable+="<td id='day_"+i+"_"+j+"' height='20' align='center' valign='middle' style='cursor:pointer'";
				htmloDayTable+=" onmouseover='CalendarCellsMsOver("+this.instanceName+")' ";
				htmloDayTable+=" onmouseout='CalendarCellsMsOut("+this.instanceName+")' ";
				htmloDayTable+=" onclick=\"CalendarCellsClick('day_"+i+"_"+j+"',"+this.instanceName+",'"+_valId+"')\">";
				htmloDayTable+="&nbsp;</td>";
			}
		}
		htmloDayTable+="</tr>";
	}
	htmloDayTable+="</tbody></table>";
	/// Today Button
	htmloButton="<div align='center' style='padding:3px'>";
	htmloButton+="<button id='"+this.sTODAYBTNID+"' style='width:40%;border:1px solid #BCD0DE;background-color:#eeeeee;cursor:hand'"
	htmloButton+=" onclick='CalendarTodayClick("+this.instanceName+")'>"+this.oBtnTodayTitle+"</button>&nbsp;"
	htmloButton+="<button style='width:40%;border:1px solid #BCD0DE;background-color:#eeeeee;cursor:pointer'";
	htmloButton+=" onclick='CalendarCancel("+this.instanceName+")'>"+this.oBtnCancelTitle+"</button> ";
	htmloButton+="</div>";
	document.write(htmloMonth+htmloYear+htmloDayTable+htmloButton+"</div></div>");
	this.Fill();
}
function CalendarFill()			///
{
	var sMonth,sYear,sWeekDay,sToday,oTable,currRow,MaxDay,iDaySn,sIndex,rowIndex,cellIndex,oSelectMonth,oSelectYear
	sMonth=this.currDate.getMonth();
	sYear=this.currDate.getFullYear();
	sWeekDay=(new Date(sYear,sMonth,1)).getDay();
	sToday=this.currDate.getDate();
	iDaySn=1
	oTable=document.getElementById(this.sTABLEID);
	currRow=oTable.rows[1];
	MaxDay=CalendarGetMaxDay(sYear,sMonth);
	
	oSelectMonth=document.getElementById(this.sMONTHID);
	oSelectMonth.selectedIndex=sMonth;
	oSelectYear=document.getElementById(this.sYEARID);
	for(i=0;i<oSelectYear.length;i++)
	{
		if(parseInt(oSelectYear.options[i].value)==sYear)oSelectYear.selectedIndex=i;
	}
	for(rowIndex=1;rowIndex<=6;rowIndex++)
	{
		if(iDaySn>MaxDay)break;
		currRow = oTable.rows[rowIndex];
		cellIndex = 0;
		if(rowIndex==1)cellIndex = sWeekDay;
		for(;cellIndex<currRow.cells.length;cellIndex++)
		{
			if(iDaySn==sToday)
			{
				currRow.cells[cellIndex].innerHTML="<font color='"+this.selectedfontColor+"'><i><b>"+iDaySn+"</b></i></font>";
				this.oPreviousCell=currRow.cells[cellIndex];
			}
			else
			{
				currRow.cells[cellIndex].innerHTML=iDaySn;	
				currRow.cells[cellIndex].style.color=this.normalfontColor;
			}
			CalendarCellSetCss(0,currRow.cells[cellIndex]);
			iDaySn++;
			if(iDaySn>MaxDay)break;	
		}
	}
}
function CalendarRestore()					/// Clear Data
{	
	var i,j,oTable
	oTable=document.getElementById(this.sTABLEID)
	for(i=1;i<oTable.rows.length;i++)
	{
		for(j=0;j<oTable.rows[i].cells.length;j++)
		{
			CalendarCellSetCss(0,oTable.rows[i].cells[j]);
			oTable.rows[i].cells[j].innerHTML="&nbsp;";
		}
	}	
}
function CalendarRefresh(newDate)					///
{
	this.currDate=newDate;
	this.Restore();	
	this.Fill();	
}
function CalendarCellsMsOver(oInstance)				/// Cell MouseOver
{
	var myCell = event.srcElement;
	CalendarCellSetCss(0,oInstance.oPreviousCell);
	if(myCell)
	{
		CalendarCellSetCss(1,myCell);
		oInstance.oPreviousCell=myCell;
	}
}
function CalendarCellsMsOut(oInstance)				////// Cell MouseOut
{
	var myCell = event.srcElement;
	CalendarCellSetCss(0,myCell);	
}
function CalendarYearChange(oInstance)				/// Year Change
{
	var sDay,sMonth,sYear,newDate
	sDay=oInstance.currDate.getDate();
	sMonth=oInstance.currDate.getMonth();
	sYear=document.getElementById(oInstance.sYEARID).value
	newDate=new Date(sYear,sMonth,sDay);
	oInstance.Refresh(newDate);
}
function CalendarMonthChange(oInstance)				/// Month Change
{
	var sDay,sMonth,sYear,newDate
	sDay=oInstance.currDate.getDate();
	sMonth=document.getElementById(oInstance.sMONTHID).value
	sYear=oInstance.currDate.getYear();
	newDate=new Date(sYear,sMonth,sDay);
	oInstance.Refresh(newDate);	
}
function CalendarCellsClick(oCell,oInstance,valId)
{
	var sDay,sMonth,sYear,newDate,sDateString,_Date,_separator;
	sYear=oInstance.currDate.getFullYear();
	sMonth=oInstance.currDate.getMonth();
	sDay=oInstance.currDate.getDate();
    var dayCell=wrInnerText(document.getElementById(oCell),null);
	if(dayCell!=" " && dayCell!=null)
	{
		sDay=parseInt(dayCell);
        if(!isNaN(sDay))
        {
		if(sDay!=oInstance.currDate.getDate())
		{
			newDate=new Date(sYear,sMonth,sDay);
			oInstance.Refresh(newDate);
		}
	    sDateString=sYear+oInstance.separator+CalendarDblNum(sMonth+1)+oInstance.separator+CalendarDblNum(sDay);		///return sDateString
        if(csfBrowser.version=="6")
        {
        dialogArguments.document.all(valId).value=sDateString;
	    }
	    else
	    {
	    window.opener.document.getElementById(valId).value=sDateString;
	    }
	    }
	    this.close();
	    return sDateString;
	}
}
function CalendarTodayClick(oInstance)				/// "Today" button Change
{	
	oInstance.Refresh(new Date());
}	
function CalendarCellSetCss(sMode,oCell)			/// Set Cell Css
{
	// sMode
	// 0: OnMouserOut 1: OnMouseOver 
	if(sMode)
	{
		oCell.style.border="1px solid #5589AA";
		oCell.style.backgroundColor="#BCD0DE";
	}
	else
	{
		oCell.style.border="1px solid #FFFFFF";
		oCell.style.backgroundColor="#FFFFFF";
	}	
}
function CalendarGetMaxDay(nowYear,nowMonth)			/// Get MaxDay of current month
{
	var nextMonth,nextYear,currDate,nextDate,theMaxDay
	nextMonth=nowMonth+1;
	if(nextMonth>11)
	{
		nextYear=nowYear+1;
		nextMonth=0;
	}
	else	
	{
		nextYear=nowYear;	
	}
	currDate=new Date(nowYear,nowMonth,1);
	nextDate=new Date(nextYear,nextMonth,1);
	theMaxDay=(nextDate-currDate)/(24*60*60*1000);
	return theMaxDay;
}
function CalendargetPos(el,ePro)				/// Get Absolute Position
{
    var left=0;
	var ePos=0;
	while(el!=null)
	{		
		ePos+=el["offset"+ePro];
		el=el.offsetParent;
	}
	return ePos;
}
function CalendarDblNum(num)
{
	if(num < 10) 
		return "0"+num;
	else
		return num;
}
function CalendarCancel(oInstance)			///Cancel
{
	this.close();
}