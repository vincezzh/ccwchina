<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="ORDER_DETAIL1"/></title>
</head>
<body>

<table width="90%" border="0" cellspacing="0" cellpadding="2">
    <caption><strong><s:text name="ORDER_DETAIL1"/></strong></caption>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL2"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderPublicId"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL3"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.totalPeopleNumber"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL4"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.totalPrice"/><s:text name="ORDER_DETAIL26"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL5"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.needMap"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL6"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.peopletitle.peopleTitleName"/> <s:property value="puOrder.orderbasic.contactPerson"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL7"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.cellphone"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL8"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.telephone"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL9"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.fax"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL10"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.email"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL11"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.confirmtime.confirmTimeContent"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL12"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.zipCode"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL13"/></td>
      <td><label for="name"></label>
      <s:date name="puOrder.orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL14"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.userdetail.userId"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL15"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.isVegetarian"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL16"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.allergic"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL17"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.address"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL18"/></td>
      <td><label for="name"></label>
      <s:date name="puOrder.orderbasic.checkingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL19"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.points"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL20"/></td>
      <td><label for="name"></label>
      <s:property value="%{getText(puOrder.orderbasic.orderstatus.orderStatusName)}"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL21"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.orderbasic.description"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL22"/></td>
      <td><label for="name"></label>
      <s:property value="%{getText(puOrder.coursecalendar.coursetrunktype.courseTrunkTypeNameKey)}"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL23"/></td>
      <td><label for="name"></label>
      <s:date name="puOrder.coursecalendar.classDate" format="yyyy-MM-dd"/> <s:property value="puOrder.coursecalendar.classtime.classTimeContent"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL24"/></td>
      <td><label for="name"></label>
      <s:property value="puOrder.coursecalendar.courselocation.courseLocationName"/></td>
    </tr>
</table>

</body>
</html>