<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="/_administrator/skin/css/base.css">
<script language="javascript">
function confirmOrder(orderId, userId, pointId) {
	var points = document.getElementById(pointId).value;
	window.location.href = "/_administrator/finish-public-order.htm?id=" + orderId + "&userId=" + userId + "&points=" + points;
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title">公共已确认订单列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">下单时间</td>
	<td width="15%">订单号</td>
	<td width="5%">课程</td>
	<td width="10%">厨房</td>
	<td width="15%">上课日期时间</td>
	<td width="5%">客人数</td>
	<td width="5%">总价</td>
	<td width="5%">联系人</td>
	<td width="15%">联系方式</td>
	<td width="5%">积点</td>
	<td width="10%">操作</td>
</tr>

<s:iterator value="orderList" status="st">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td><s:property value="orderPublicId"/></td>
	<td><s:property value="%{getText(coursecalendar.coursetrunktype.courseTrunkTypeNameKey)}"/></td>
	<td><s:property value="coursecalendar.courselocation.courseLocationName"/></td>
	<td><s:date name="coursecalendar.classDate" format="yyyy-MM-dd"/> <s:property value="coursecalendar.classtime.classTimeContent"/></td>
	<td><s:property value="totalPeopleNumber"/></td>
	<td><s:property value="totalPrice"/></td>
	<td><s:property value="orderbasic.peopletitle.peopleTitleName"/> <s:property value="orderbasic.contactPerson"/></td>
	<td><s:property value="orderbasic.cellphone"/> <s:property value="orderbasic.email"/></td>
	<td><s:textfield id="order_%{#st.index}" name="orderbasic.points" value="0" size="4"/></td>
	<td>
		<a href="#" onclick="confirmOrder('<s:property value="orderPublicId"/>', '<s:property value="orderbasic.userdetail.userId"/>', 'order_<s:property value="%{#st.index}"/>')">积点确认</a> | 
		<s:a action="detail-public-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderPublicId"/></s:param>
			<s:param name="orderStatusId"><s:property value="orderStatusId"/></s:param>
			详情
		</s:a>
	</td>
</tr>
</s:iterator>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="11" align="center">
		<s:include value="common/pages.jsp"></s:include>
	 </td>
</tr>
</table>
</form>
</body>
</html>