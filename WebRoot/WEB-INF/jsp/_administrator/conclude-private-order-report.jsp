<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="/_administrator/skin/css/base.css">
<script>
function concludeReport() {
	document.getElementById("form2").submit();
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>

<!--  内容列表   -->
<form id="form2" name="form2" action="/_administrator/conclude-private-order-report.htm">
<select name="type" onchange="concludeReport()">
	<option value="1">总消费前100</option>
	<option value="2">最近30天消费前100</option>
	<option value="3">最精60天消费前100</option>
</select>
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title">私人订单统计列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">排名</td>
	<td width="15%">总消费（RMB）</td>
	<td width="20%">用户名</td>
	<td width="30%">email</td>
	<td width="25%">移动电话</td>
</tr>

<s:iterator value="recordList" id="record" status="st">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:property value="%{#st.index + 1}"/></td>
	<td><s:property value="#record.value"/></td>
	<td><s:property value="#record.key.userId"/></td>
	<td><s:property value="#record.key.email"/></td>
	<td><s:property value="#record.key.cellphone"/></td>
</tr>
</s:iterator>
</table>
</form>
</body>
</html>