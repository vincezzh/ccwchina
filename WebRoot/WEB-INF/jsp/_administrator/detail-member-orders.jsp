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
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title"><s:property value="userId"/>的公共订单列表</caption>
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
	<td width="5%">状态</td>
	<td width="5%">操作</td>
</tr>

<s:iterator value="publicOrderList" status="st">
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
	<td><s:property value="orderbasic.points"/></td>
	<td><s:property value="%{getText(orderbasic.orderstatus.orderStatusName)}"/></td>
	<td>
		<s:a action="detail-public-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderPublicId"/></s:param>
			详情
		</s:a>
	</td>
</tr>
</s:iterator>
</table>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title"><s:property value="userId"/>的私人订单列表</caption>
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
	<td width="5%">状态</td>
	<td width="5%">操作</td>
</tr>

<s:iterator value="privateOrderList" status="st">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td><s:property value="orderPrivateId"/></td>
	<td><s:property value="%{getText(coursetrunktype.courseTrunkTypeNameKey)}"/></td>
	<td><s:property value="courseLocation"/></td>
	<td><s:date name="courseDate" format="yyyy-MM-dd"/> <s:property value="courseTime"/></td>
	<td><s:property value="totalPeopleNumber"/></td>
	<td><s:property value="totalPrice"/></td>
	<td><s:property value="orderbasic.peopletitle.peopleTitleName"/> <s:property value="orderbasic.contactPerson"/></td>
	<td><s:property value="orderbasic.cellphone"/> <s:property value="orderbasic.email"/></td>
	<td><s:property value="orderbasic.points"/></td>
	<td><s:property value="%{getText(orderbasic.orderstatus.orderStatusName)}"/></td>
	<td>
		<s:a action="detail-private-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderPrivateId"/></s:param>
			详情
		</s:a>
	</td>
</tr>
</s:iterator>
</table>

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title"><s:property value="userId"/>的商品订单列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">下单时间</td>
	<td width="15%">订单号</td>
	<td width="20%">商品名称</td>
	<td width="5%">价格</td>
	<td width="15%">联系人</td>
	<td width="20%">联系方式</td>
	<td width="5%">积点</td>
	<td width="5%">状态</td>
	<td width="5%">操作</td>
</tr>

<s:iterator value="itemOrderList" status="st">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td><s:property value="orderItemId"/></td>
	<td><s:property value="item.itemNameCn"/></td>
	<td><s:property value="price"/></td>
	<td><s:property value="contactPerson"/></td>
	<td><s:property value="cellphone"/> <s:property value="email"/></td>
	<td><s:property value="points"/></td>
	<td><s:property value="%{getText(orderstatus.orderStatusName)}"/></td>
	<td>
		<s:a action="detail-item-order" namespace="/_administrator">
			<s:param name="id"><s:property value="orderItemId"/></s:param>
			详情
		</s:a>
	</td>
</tr>
</s:iterator>
</table>

<div class="save-button">
  <input type='button' class="coolbg np" value='返回' onclick="javascript:history.go(-1);"/>
</div>
</body>
</html>