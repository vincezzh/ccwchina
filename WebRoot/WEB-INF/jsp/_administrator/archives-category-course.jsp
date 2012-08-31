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
课程栏目管理
</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="11%">ID</td>
	<td width="18%">名称</td>
	<td width="18%">描述</td>
	<td width="18%">颜色显示</td>
	<td width="18%">网址显示</td>
	<td width="17%">操作</td>
</tr>

<s:iterator value="trunkList" status="t">
	<s:if test="avaliable == 'yes'">
		<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			<td><s:property value="courseTrunkTypeId"/></td>
			<td align="left"><s:property value="%{getText(courseTrunkTypeNameKey)}"/></td>
			<td><s:property value="%{getText(courseTrunkTypeDescriptionKey)}"/></td>
			<td><div style="width:100px;height:16px;color:<s:property value='fontColor'/>;background-color:<s:property value='backgroundColor'/>;">课程颜色</div></td>
			<td><s:property value="courseTrunkTypePath"/></td>
			<td>
				<s:a action="update-category-course" namespace="/_administrator">
					<s:param name="courseTrunkTypeId"><s:property value="courseTrunkTypeId"/></s:param>
					<s:param name="categoryLevel">1</s:param>
					更新
				</s:a> | 
				<s:a action="course-category-delete" namespace="/_administrator">
					<s:param name="courseTrunkTypeId"><s:property value="courseTrunkTypeId"/></s:param>
					<s:param name="categoryLevel">1</s:param>
					删除
				</s:a>
			</td>
		</tr>
		
		<s:iterator value="coursebranchtypes" status="b">
			<s:if test="avaliable == 'yes'">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
					<td><s:property value="courseBranchTypeId"/></td>
					<td align="left" class="sec-category"><s:property value="%{getText(courseBranchTypeNameKey)}"/></td>
					<td></td>
					<td></td>
					<td><s:property value="courseBranchTypePath"/></td>
					<td>
						<s:a action="update-category-course" namespace="/_administrator">
							<s:param name="courseBranchTypeId"><s:property value="courseBranchTypeId"/></s:param>
							<s:param name="categoryLevel">2</s:param>
							更新
						</s:a> | 
						<s:a action="course-category-delete" namespace="/_administrator">
							<s:param name="courseBranchTypeId"><s:property value="courseBranchTypeId"/></s:param>
							<s:param name="categoryLevel">2</s:param>
							删除
						</s:a>
					</td>
				</tr>
			</s:if>
		</s:iterator>
	</s:if>
</s:iterator>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="9" align="center"><!--翻页代码 --></td>
</tr>
</table>
</form>
</body>
</html>