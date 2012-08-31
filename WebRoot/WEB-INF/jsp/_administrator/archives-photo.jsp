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
<caption class="form-title">相册列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="15%">上传时间</td>
	<td width="35%">照片标题</td>
	<td width="25%">类别</td>
	<td width="15%">上传者</td>
	<td width="10%">操作</td>
</tr>

<s:iterator value="photoList">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="updatedTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	<td><s:property value="photoTitle"/></td>
	<td><s:property value="%{getText(phototrunktype.photoTrunkTypeNameKey)}"/></td>
	<td><s:property value="userdetail.nickName"/></td>
	<td>
		<s:a action="update-photo" namespace="/_administrator">
			<s:param name="photoId"><s:property value="photoId"/></s:param>
			编辑
		</s:a> | 
		<s:a action="photo-delete" namespace="/_administrator">
			<s:param name="photoId"><s:property value="photoId"/></s:param>
			删除
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