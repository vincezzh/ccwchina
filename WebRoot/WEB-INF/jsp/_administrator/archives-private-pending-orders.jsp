<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="/_administrator/skin/css/base.css">

</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title">私人待处理订单列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="15%">下单时间</td>
	<td width="10%">订单号</td>
	<td width="10%">厨房</td>
	<td width="15%">上课日期时间</td>
	<td width="5%">客人数</td>
	<td width="5%">总价</td>
	<td width="15%">联系人</td>
	<td width="15%">联系方式</td>
	<td width="10%">操作</td>
</tr>

<s:iterator value="orderList">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td><s:property value="orderPrivateId"/></td>
	<td><s:property value="courselocation.courseLocationName"/></td>
	<td><s:date name="courseDate" format="yyyy-MM-dd"/> <s:property value="classtime.classTimeContent"/></td>
	<td><s:property value="totalPeopleNumber"/></td>
	<td><s:property value="totalPrice"/></td>
	<td><s:property value="orderbasic.peopletitle.peopleTitleName"/> <s:property value="orderbasic.contactPerson"/></td>
	<td><s:property value="orderbasic.cellphone"/> <s:property value="orderbasic.email"/></td>
	<td>
		<s:a action="confirm-private-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderPrivateId"/></s:param>
			确认
		</s:a> | 
		<s:a action="detail-private-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderPrivateId"/></s:param>
			<s:param name="orderStatusId"><s:property value="orderStatusId"/></s:param>
			详情
		</s:a>
	</td>
</tr>
</s:iterator>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">
		<s:include value="common/pages.jsp"></s:include>
	 </td>
</tr>
</table>
</form>
</body>
</html>