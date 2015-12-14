<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../js/auto_complete.js"></script>
<link href="../images/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../images/CheckBrower.js"></script>
<script language="javascript">
function winOpen()
{
	if(csfBrowser.ie)
	{
		if(csfBrowser.version=="6")
		{
		window.showModalDialog('../startDate.html',window,'scroll:off;status:off;dialogWidth:200px;dialogHeight:230px;toolbar=no;location=no;');
		}
		else if(csfBrowser.version=="7"||csfBrowser.version=="8")
		{
		window.open('../startDate.html','window','modal=yes,width=120,height=205,resizable=no,scrollbars=no;location=no;');
		}
		else
		{
			window.open('../startDate.html','window','modal=yes,width=120,height=205,resizable=no,scrollbars=no;location=no;');
		}
	}
	else
	{
	window.open('../startDate.html','','modal=yes,width=182,height=195,resizable=no,scrollbars=no;location=no;');
	}
}

function winopen_end()
{
	if(csfBrowser.ie)
	{
		if(csfBrowser.version=="6")
		{
		window.showModalDialog('../endDate.html',window,'scroll:off;status:off;dialogWidth:200px;dialogHeight:230px;toolbar=no;location=no;');
		}
		else if(csfBrowser.version=="7"||csfBrowser.version=="8")
		{
		window.open('../endDate.html','window','modal=yes,width=120,height=205,resizable=no,scrollbars=no;location=no;');
		}
		else
		{
			window.open('../endDate.html','window','modal=yes,width=120,height=205,resizable=no,scrollbars=no;location=no;');
		}
	}
	else
	{
	window.open('../endDate.html','','modal=yes,width=182,height=195,resizable=no,scrollbars=no;location=no;');
	}
}
function  trim(str){
    for(var  i  =  0  ;  i<str.length  &&  str.charAt(i)==" "  ;  i++  )  ;
    for(var  j  =str.length;  j>0  &&  str.charAt(j-1)==" "  ;  j--)  ;
    if(i>j)  return  "";  
    return  str.substring(i,j);  
}
function add(){
	var swjgbm = trim(document.getElementById('swjgbm').value);
	var nsrwjbm = trim(document.getElementById('nsrwjbm').value);
	var startdate = trim(document.getElementById('startdate').value);
	var enddate = trim(document.getElementById('enddate').value);
	var xmzt = trim(document.getElementById('xmzt').value);
	var htxz = trim(document.getElementById('htxz').value);
	var xmlx = trim(document.getElementById('xmlx').value);
	var xmmc = trim(document.getElementById('xmmc').value);
	//alert('/javaskweb/cxJzazServlet?method=list&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx);
	parent.document.all('mainFrame').src = '/javaskweb/cxJzazServlet?method=list&nsrwjbm='+nsrwjbm+'&swjgbm='+swjgbm+'&startdate='+startdate+'&enddate='+enddate+'&xmzt='+xmzt+'&htxz='+htxz+'&xmlx='+xmlx+'&xmmc='+encodeURI(encodeURI(xmmc));
}

function changeVal(val,name){
	//alert(val);
	document.getElementById(name).value = val;
	//alert(document.getElementById(name).value);
}
</script>
</head>
<body>
<div id="right">
  <div id="main">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="userList">
      <tr>
        <td width="12%">纳税人微机编码：</td>
        <td><input type="text" name="nsrwjbm" id="nsrwjbm" maxlength="16" style="width: 270px;"><div id="auto"></div></td>
        <td width="12%">税务机关：</td>
        <td><input type="text" name="swjgmc" id="swjgmc" readonly style="width:50%" onClick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=600,top=200,left=200,scrollbars=yes');">
          &nbsp;&nbsp;
          <input type="button" name="swjg_btn" value="选择税务机关" onClick="window.open('/javaskweb/taxpayer.do?op=chooseSWJG','chooseSWJG','width=600,height=600,top=200,left=200,scrollbars=yes');">
          <input type="hidden" id="swjgbm" name="swjgbm">
        </td>
      </tr>
      <tr>
        <td>登记时间：</td>
        <td>开始：
          <input type="text" id="startdate" name="startdate" style="width:25%" onClick="winOpen();" readonly>
          &nbsp;&nbsp;&nbsp;&nbsp;
          截止：
          <input type="text" id="enddate" name="enddate" style="width:25%" onClick="winopen_end();" readonly></td>
        <td>项目状态：</td>
        <td>
            <input type="radio" name="xmzt1" id="xmzt1" value="" checked onClick="changeVal(this.value,'xmzt');" />
            所有项目状态
            <input type="radio" name="xmzt1" value="0" id="xmzt1" onClick="changeVal(this.value,'xmzt');" />
            未完结
            <input type="radio" name="xmzt1" value="1" id="xmzt1" onClick="changeVal(this.value,'xmzt');" />
            已完结
        </td>
      </tr>
      <tr>
        <td>项目名称：</td>
        <td><input type="text" name="xmmc" id="xmmc"></td>
        <td>合同性质：</td>
        <td>
            <input type="radio" name="htxz1" id="htxz1" value="" onClick="changeVal(this.value,'htxz');" checked />
            所有合同性质
            <input type="radio" name="htxz1" value="0" id="htxz1" onClick="changeVal(this.value,'htxz');" />
            总包合同
            <input type="radio" name="htxz1" value="1" id="htxz1" onClick="changeVal(this.value,'htxz');" />
            分包合同
        </td>
      </tr>
      <tr>
        <td>合同类型：</td>
        <td colspan="3">
            <input type="radio" name="xmlx1" id="xmlx1" value="" checked onClick="changeVal(this.value,'xmlx');" />
            所有合同类型
            <input type="radio" name="xmlx1" value="1" id="xmlx1" onClick="changeVal(this.value,'xmlx');" />
            房地产工程
            <input type="radio" name="xmlx1" value="2" id="xmlx1" onClick="changeVal(this.value,'xmlx');" />
            城市基础设施建设工程
            <input type="radio" name="xmlx1" value="3" id="xmlx1" onClick="changeVal(this.value,'xmlx');" />
            企业设立改造工程
            <input type="radio" name="xmlx1" value="4" id="xmlx1" onClick="changeVal(this.value,'xmlx');" />
            其他</td>
      </tr>
      <tr>
        <td colspan="4" align="center">
          <input type="hidden" id="xmzt" name="xmzt" value="">
          <input type="hidden" id="htxz" name="htxz" value="">
          <input type="hidden" id="xmlx" name="xmlx" value="">
          <input type="button" name="add_btn" value=" 查 询 " onClick="add();">
          &nbsp;&nbsp;
          <input type="button" name="ret_btn" value=" 重 置 " onClick="window.location.reload();">
        </td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
