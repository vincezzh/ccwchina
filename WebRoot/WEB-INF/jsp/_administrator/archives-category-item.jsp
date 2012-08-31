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
<caption class="form-title">
商品栏目管理
</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">ID</td>
	<td width="35%">名称</td>
	<td width="35%">网址显示</td>
	<td width="20%">操作</td>
</tr>

<s:iterator value="trunkList">
	<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
		<td><s:property value="itemTrunkTypeId"/></td>
		<td align="left"><s:property value="%{getText(itemTrunkTypeNameKey)}"/></td>
		<td><s:property value="itemTrunkTypePath"/></td>
		<td>
			<s:a action="update-category-item" namespace="/_administrator">
				<s:param name="itemTrunkTypeId"><s:property value="itemTrunkTypeId"/></s:param>
				更新
			</s:a> | 
			<s:a action="item-category-delete" namespace="/_administrator">
				<s:param name="itemTrunkTypeId"><s:property value="itemTrunkTypeId"/></s:param>
				删除
			</s:a>
		</td>
	</tr>
</s:iterator>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="9" align="center"><!--翻页代码 --></td>
</tr>
</table>
</form>
</body>
</html>