<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="jsdt.model.*"%>
<%@page import="jsdt.tools.*"%>
<%
	HttpSession session1 = request.getSession(false);
	Nsrxx nsrxx = (Nsrxx) request.getAttribute("nsrxx");
	Jqxx jqxx = (Jqxx) request.getAttribute("jqxx");
	ArrayList alFp1 = (ArrayList) request.getAttribute("alFp");
	ArrayList cardInvoice = (ArrayList) session1
			.getAttribute("cardInvoice");
	
%>
<html>
	<head>
	<script type="text/javascript" src="../js/jquery-1.4.2.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		
		<link href="images/css.css" rel="stylesheet" type="text/css" />
		<script language="javascript">
		
function get_id(){
	var s='';
	for(var i=0;i<document.all.form2.elements.length;i++){
		t= document.all.form2.elements[i].name;
		//alert(t)
		if(t!=''){
			//alert(document.all(t).checked)
			if(document.all(t).checked){
	   			var m = document.all(t).value;
				if(s==""){
			  		s=m;
				}
				else{
					s=s+','+m;
				}
			}
		}
	}
	return s;
	//alert(s);
}
function add(){
	if(confirm('确定选择的发票？')){
		var fpqshInfoStr = get_id();
		if(fpqshInfoStr==''){
			alert('请选择发票！');
		}
		else{
			var fpqshArr= new Array(); 
			fpqshArr = fpqshInfoStr.split(",");
			var num1 = fpqshArr.length;
			var num = document.all('num').value*1;
			var allcount = num+num1;
			if(allcount>10){
				alert('不能超过10卷！');
			}
			else{
				var nsrwjbm = document.getElementById('nsrwjbm').value;
				var jqbh = document.getElementById('jqbh').value;
				window.location.href='/javaskweb/fpService.do?op=fpxk&nsrwjbm='+nsrwjbm+'&jqbh='+jqbh+'&fpqshInfoStr='+fpqshInfoStr;
			}
		}
	}
}

function lgfp(){
	var nsrwjbm = document.getElementById('nsrwjbm').value;
	var jqbh = document.getElementById('jqbh').value;
	window.open('/javaskweb/fpService.do?op=add&nsrwjbm='+nsrwjbm+'&jqbh='+jqbh,'','width=800,height=600,left=200,top=200');
}

function zghq(){
	var nsrwjbm = document.getElementById('nsrwjbm').value;
	var jqbh = document.getElementById('jqbh').value;
	window.location.href = '/javaskweb/fpService.do?op=zghqfp&nsrwjbm='+nsrwjbm+'&jqbh='+jqbh;
}

function del(fpqsh,fpjzh,fpdm,nsrwjbm,jqbh){
	if(confirm('确定删除该发票？')){
		document.all('iframe').src='/javaskweb/fpService.do?op=delete&fpqsh='+fpqsh+'&fpjzh='+fpjzh+'&fpdm='+fpdm+'&nsrwjbm='+nsrwjbm+'&jqbh='+jqbh;
	}
}

function error(msg){
	alert(msg);
}

function success(){
	var nsrwjbm = document.getElementById('nsrwjbm').value;
	var jqbh = document.getElementById('jqbh').value;
	window.location.href='/javaskweb/fpService.do?op=fpList&nsrwjbm='+nsrwjbm+'&jqbh='+jqbh;
}
</script>
	</head>
	<body>
		<!--==========right部分==========-->
		<div id="right">
			<div id="tool">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="tableListTitle" style="padding-top: 14px;">
					<tr>
						<td>
							&nbsp;&nbsp;发票管理 &gt;&gt;&nbsp;&nbsp;
							<a href="/javaskweb/fpService.do?op=toImport" class="nav">发票领购</a>
						</td>
						<td align="right">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
			</div>
			<div id="main">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="tableListTitle">
					<tr>
						<td width="51%" valign="bottom">
							<div class="listTitleBlue">
								<a href="JAVASCRIPT:">纳税户基本信息</a>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="userList">
					<tr>
						<td width="15%">
							纳税人微机编码：
						</td>
						<td>
							&nbsp;<%=nsrxx.getNsrwjbm()%></td>
						<td width="15%">
							纳税人识别号：
						</td>
						<td width="35%">
							&nbsp;<%=nsrxx.getNsrsbh()%></td>
					</tr>
					<tr>
						<td>
							纳税人名称：
						</td>
						<td>
							&nbsp;<%=nsrxx.getNsrmc()%></td>
						<td>
							经营地址：
						</td>
						<td>
							&nbsp;<%=nsrxx.getJydz()%></td>
					</tr>
					<tr>
						<td>
							机器编号：
						</td>
						<td>
							&nbsp;<%=jqxx.getJqbh()%></td>
						<td>
							税控卡号：
						</td>
						<td>
							&nbsp;<%=jqxx.getSkkh()%></td>
					</tr>
				</table>
				<br />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="tableListTitle">
					<tr>
						<td width="51%" valign="bottom">
							<div class="listTitleBlue">
								<a href="JAVASCRIPT:">用户卡已有发票信息</a>
							</div>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="userList">
					<tr>
						<th width="20%">
							发票代码
						</th>
						<th width="20%">
							发票单位（卷）
						</th>
						<th>
							发票号码起止
						</th>
					</tr>
					<%
						int num = 0;
						//out.println("CardInvoice=="+CardInvoice);
						if (cardInvoice != null && cardInvoice.size() > 0) {
							Iterator it = cardInvoice.iterator();
							while (it.hasNext()) {
								HashMap hm = (HashMap) it.next();
								String QSH = String.valueOf((Integer) hm.get("QSH"));
								int JS = (Integer) hm.get("JS");
								if (QSH != null && !"".equals(QSH) && !"0".equals(QSH)
										&& JS > 0) {
									num++;
					%>
					<tr>
						<td align="center"><%=(String) hm.get("FPDM")%>&nbsp;
						</td>
						<td align="center"><%=(Integer) hm.get("JS")%>&nbsp;
						</td>
						<td align="center"><%=(Integer) hm.get("QSH")%>--<%=(Integer) hm.get("JZH")%>&nbsp;
						</td>
					</tr>
					<%
						}
							}
						}
					%>
				</table>
				<br />
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="tableListTitle">
					<tr>
						<td width="20%" valign="bottom">
							<div class="listTitleBlue">
								<a href="JAVASCRIPT:">税控发票领购情况</a>
							</div>
						</td>
						<td>
							<font color="#FF0000">&nbsp;</font>
						</td>
					</tr>
				</table>
				<form name="form2">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="userList">

						<tr>
							<th width="10%">
								&nbsp;
							</th>
							<th width="15%">
								领票日期
							</th>
							<th width="15%">
								发票代码
							</th>
							<th width="15%">
								发票单位（卷）
							</th>
							<th>
								发票号码起止
							</th>
							<th width="12%">
								操作
							</th>
						</tr>
						<%
							int checknum = 0;
						System.out.println("apFp1:大小："+alFp1.size());
							if (alFp1 != null && alFp1.size() > 0) {
								Iterator it1 = alFp1.iterator();
								while (it1.hasNext()) {
									Fpjmx fpjmx = (Fpjmx) it1.next();
									String fpqsh = String.valueOf(fpjmx.getFpqsh());
									if (fpqsh.length() < 8) {
										for (int i = fpqsh.length(); i < 8; i++) {
											fpqsh = "0" + fpqsh;
										}
									}
									String fpjzh = String.valueOf(fpjmx.getFpjzh());
									if (fpjzh.length() < 8) {
										for (int i = fpjzh.length(); i < 8; i++) {
											fpjzh = "0" + fpjzh;
										}
									}
						%>
						<tr>
							 <td align="center"><input type="checkbox" name="a_<%=checknum%>" value="<%=fpjmx.getFpqsh() %>|<%=fpjmx.getFpdm() %>">&nbsp;</td>
							<td align="center"><%=fpjmx.getFplgrq()%>&nbsp;
							</td>
							<td align="center"><%=fpjmx.getFpdm()%>&nbsp;
							</td>
							<td align="center"><%=fpjmx.getFpdw()%>&nbsp;
							</td>
							<td align="center"><%=fpqsh%>--<%=fpjzh%>&nbsp;
							</td>
							<td align="center">
								<input type="button" name="del_btn" value="删 除"
									onclick="del(<%=Integer.parseInt(fpqsh)%>,<%=Integer.parseInt(fpjzh)%>,'<%=fpjmx.getFpdm()%>','<%=nsrxx.getNsrwjbm()%>','<%=jqxx.getJqbh()%>')" />
								&nbsp;
							</td>
						</tr>
						<%
							checknum++;
								}
							}
						%>

					</table>
				</form>
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="tableListBottom">
					<tr>
						<td height="40" align="center">
							<img src="images/ico_01.gif" width="22" height="12" />
							发票领购写卡！！
							<br />
							<br />
							<input type="hidden" name="num" value="<%=num%>" />
							<input type="hidden" id="nsrwjbm" name="nsrwjbm"
								value="<%=nsrxx.getNsrwjbm()%>" />
							<input type="hidden" id="jqbh" name="jqbh"
								value="<%=jqxx.getJqbh()%>" />
							<input type="button" name="next_btn" value=" 发票增加 "
								onclick="lgfp();" />
							&nbsp;&nbsp;
							<!-- <input type="button" name="zghq" value="从征管获取" onclick="zghq();" />&nbsp;&nbsp;-->
							<input type="button" name="next_btn" value="发票领购确认"
								onclick="add();" />
							&nbsp;&nbsp;
							<input type="button" name="ret_btn" value=" 返 回 "
								onclick="window.location.href='/javaskweb/fpService.do?op=toImport';" />
						</td>
					</tr>
				</table>
				<iframe src="" name="iframe" frameborder="0" width="0" height="0"></iframe>
			</div>
		</div>
	</body>
</html>
