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
function updateAdv(advertisementId, pathId, linkId) {
	var advertisementPath = document.getElementById(pathId).value;
	var advertisementLink = document.getElementById(linkId).value;
	window.location.href = "/_administrator/advertisement-update.htm?advertisementId=" + advertisementId + "&advertisementPath=" + advertisementPath + "&advertisementLink=" + advertisementLink;
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<caption class="form-title">文档列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">ID</td>
	<td width="20%">广告标题</td>
	<td width="30%">广告链接</td>
	<td width="30%">广告图片</td>
	<td width="10%">操作</td>
</tr>

<s:iterator value="advertisementList" status="st">
	<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
		<td><s:property value="advertisementId"/></td>
		<td><s:property value="advertisementTitle"/></td>
		<td><s:textfield id="adv_link_%{#st.index}" name="advertisementLink" size="45"/></td>
		<td><s:textfield id="adv_%{#st.index}" name="advertisementPath" size="45"/></td>
		<td>
			<a href="#" onclick="updateAdv('<s:property value="advertisementId"/>', 'adv_<s:property value="%{#st.index}"/>', 'adv_link_<s:property value="%{#st.index}"/>')">更新</a>
		</td>
	</tr>
</s:iterator>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="9" align="left">
		<a href='<s:url action="archives-pictures" namespace="/_administrator"/>' target='_blank'>图片浏览</a>
	</td>
</tr>
</table>
</form>
</body>
</html>