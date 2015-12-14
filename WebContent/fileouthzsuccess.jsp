<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="jsdt.tools.Util"%>
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
   <title>导出</title>
   <style>
    .errMainArea
    {
    	width:630px; 
    	margin:80px auto;
    	border:1px solid #95BAE3; 
    	text-align:left
    }
	  .errTopArea
	  {
	  	background-color:#73A2D6;
	  	border-bottom:1px solid #95BAE3; 
	  	font-size:14px; font-weight:bold;
	  	color:#fff; 
	  	padding:5px 10px 3px 10px
	  }
	  .errTxtArea
	  {
	  	background-color:#fff; 
	  	padding:54px 34px 54px 156px; 
	  	background: no-repeat 35px 35px; 
	  }	
	  .txt_title
	  { 
	  	font-size:14px; 
	  	font-weight:bold; 
	  	margin-left:12px;
	  	color:#96A9BA; 
	  } 
	  .txt_others
	  { font-size:14px; 
	  	color:#96A9BA; 
	  	width:630px; 
    	margin:80px auto;
    	border:1px solid #95BAE3; 
	  	 font-family:"宋体"; 
	  	 line-height:16px
	  }
	  
   </style>
 
 
<!--脚本结束--> 
</head>
<body>
<div class="txt_others" id="error163">
						该功能是将上月所有的纳税户申报汇总信息全部导出，目录存在为 <font color="red">D:盘</font>,<br/>
						例如：当月为2013年5月，则点完按钮后，D盘会出现 <font color="red">2013-04发票汇总</font>文件夹，<br/>
						所有纳税户的明细xls规则如下：<font color="red"> 纳税户名称2013-04汇总.xls</font><br/>
						如果想导出为3月的，则修改系统时间为4月份
						导出明细后，再将时间恢复正常，页面消失时，导出所有纳税户上月开票汇总完成
					 
						 <br/>
						 <br/>
						 <br/>
						 <br/>
						 <br/>
						 
<br/>&nbsp;&nbsp;&nbsp;<a href="/javaskweb/cxtj.do?op=hzexcel" class="txt_others">导出<%=Util.getPreviousMonthFirst().substring(0,7) %>月所有明细</a></div></div>
</body>
</html>
