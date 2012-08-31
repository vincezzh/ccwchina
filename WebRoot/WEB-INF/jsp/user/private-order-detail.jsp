<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="ORDER_DETAIL25"/></title>
</head>
<body>

<table width="90%" border="0" cellspacing="0" cellpadding="2">
    <caption><strong><s:text name="ORDER_DETAIL25"/></strong></caption>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL2"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderPrivateId"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL3"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.totalPeopleNumber"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL4"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.totalPrice"/><s:text name="ORDER_DETAIL26"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL6"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.peopletitle.peopleTitleName"/> <s:property value="prOrder.orderbasic.contactPerson"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL7"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.cellphone"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL8"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.telephone"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL9"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.fax"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL10"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.email"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL11"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.confirmtime.confirmTimeContent"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL12"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.zipCode"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL13"/></td>
      <td><label for="name"></label>
      <s:date name="prOrder.orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL14"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.userdetail.userId"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL18"/></td>
      <td><label for="name"></label>
      <s:date name="prOrder.orderbasic.checkingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL19"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.orderbasic.points"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL20"/></td>
      <td><label for="name"></label>
      <s:property value="%{getText(prOrder.orderbasic.orderstatus.orderStatusName)}"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL23"/></td>
      <td><label for="name"></label>
      <s:date name="prOrder.courseDate" format="yyyy-MM-dd"/> <s:property value="prOrder.classtime.classTimeContent"/></td>
    </tr>
    <tr>
      <td width="40%"><s:text name="ORDER_DETAIL24"/></td>
      <td><label for="name"></label>
      <s:property value="prOrder.courselocation.courseLocationName"/></td>
    </tr>
    <tr>
      <td width="40%"></td>
      <td><label for="name"></label>
      	<s:iterator value="packageCourses">
			<s:if test="language == 'ja'">
				<s:property value="course.courseNameJp"/>; 
			</s:if>
			<s:elseif test="language == 'zh'">
				<s:property value="course.courseNameCn"/>; 
			</s:elseif>
			<s:else>
				<s:property value="course.courseNameEn"/>; 
			</s:else>
		</s:iterator>
	  </td>
    </tr>
</table>

</body>
</html>