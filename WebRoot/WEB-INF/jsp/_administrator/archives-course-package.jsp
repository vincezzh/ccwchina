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
<caption class="form-title">课程套餐列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="10%">ID</td>
	<td width="20%">产品类别</td>
	<td width="50%">课程内容</td>
	<td width="20%">操作</td>
</tr>

<s:iterator value="coursePackages">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:property value="coursePackageId"/></td>
	<td><s:property value="%{getText(coursetrunktype.courseTrunkTypeNameKey)}"/></td>
	<td>
		<s:iterator value="packagewithcourses">
			<div><s:property value="course.courseNameCn"/></div>
		</s:iterator>
	</td>
	<td>
		<s:a action="update-course-package" namespace="/_administrator">
			<s:param name="coursePackageId"><s:property value="coursePackageId"/></s:param>
			编辑
		</s:a> | 
		<s:a action="course-package-delete" namespace="/_administrator">
			<s:param name="coursePackageId"><s:property value="coursePackageId"/></s:param>
			删除
		</s:a>
	</td>
</tr>
</s:iterator>
</table>
</form>
</body>
</html>