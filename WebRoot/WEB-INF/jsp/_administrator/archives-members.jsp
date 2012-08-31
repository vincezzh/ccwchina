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
<caption class="form-title">会员列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="8%">User ID</td>
	<td width="8%">昵称</td>
	<td width="20%">全名</td>
	<td width="10%">移动电话</td>
	<td width="10%">e-mail</td>
	<td width="10%">喜欢的厨房</td>
	<td width="5%">素食者</td>
	<td width="15%">敏感物品</td>
	<td width="5%">拥有积点</td>
	<td width="9%">操作</td>
</tr>

<s:iterator value="userList">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:property value="userId"/></td>
	<td><s:property value="nickName"/></td>
	<td><s:property value="peopletitle.peopleTitleName"/> <s:property value="firstName"/> <s:property value="lastName"/></td>
	<td><s:property value="cellphone"/></td>
	<td><s:property value="email"/></td>
	<td><s:property value="courselocation.courseLocationName"/></td>
	<td><s:property value="isVegetarian"/></td>
	<td><s:property value="allergic"/></td>
	<td><s:property value="points"/></td>
	<td>
		<s:a action="detail-member" namespace="/_administrator">
			<s:param name="userId"><s:property value="userId"/></s:param>
			详情
		</s:a> |
		<s:a action="detail-member-orders" namespace="/_administrator">
			<s:param name="userId"><s:property value="userId"/></s:param>
			订单
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