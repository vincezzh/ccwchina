<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="ORDER_DETAIL27"/></title>
</head>
<body>

<table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption><strong><s:text name="ORDER_DETAIL27"/></strong></caption>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL2"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.orderItemId"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL28"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.item.itemNameCn"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL4"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.item.price"/><s:text name="ORDER_DETAIL26"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL29"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.item.startTime"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL30"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.item.endTime"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL6"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.contactPerson"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL7"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.cellphone"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL10"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.email"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL13"/></td>
      <td><label for="name"></label>
      <s:date name="itOrder.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL14"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.userdetail.userId"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL18"/></td>
      <td><label for="name"></label>
      <s:date name="itOrder.checkTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL19"/></td>
      <td><label for="name"></label>
      <s:property value="itOrder.points"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL20"/></td>
      <td><label for="name"></label>
      <s:property value="%{getText(itOrder.orderstatus.orderStatusName)}"/></td>
    </tr>
</table>

</body>
</html>