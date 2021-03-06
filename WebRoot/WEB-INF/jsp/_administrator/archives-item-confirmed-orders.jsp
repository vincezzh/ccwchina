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
	window.location.href = "/_administrator/finish-item-order.htm?id=" + orderId + "&userId=" + userId + "&points=" + points;
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title">商品已确认订单列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="15%">下单时间</td>
	<td width="15%">订单号</td>
	<td width="15%">商品</td>
	<td width="5%">价格</td>
	<td width="10%">联系人</td>
	<td width="15%">email</td>
	<td width="10%">手机</td>
	<td width="5%">积点</td>
	<td width="10%">操作</td>
</tr>

<s:iterator value="orderList" status="st">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td><s:property value="orderItemId"/></td>
	<td><s:property value="item.itemNameCn"/></td>
	<td><s:property value="item.price"/></td>
	<td><s:property value="contactPerson"/></td>
	<td><s:property value="email"/></td>
	<td><s:property value="cellphone"/></td>
	<td><s:textfield id="order_%{#st.index}" name="points" value="0" size="4"/></td>
	<td>
		<a href="#" onclick="confirmOrder('<s:property value="orderItemId"/>', '<s:property value="userdetail.userId"/>', 'order_<s:property value="%{#st.index}"/>')">积点确认</a> | 
		<s:a action="detail-item-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderItemId"/></s:param>
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