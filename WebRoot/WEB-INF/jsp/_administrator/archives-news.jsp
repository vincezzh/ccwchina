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
<caption class="form-title">新闻列表</caption>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="20%">发布日期</td>
	<td width="40%">新闻标题</td>
	<td width="20%">作者</td>
	<td width="20%">操作</td>
</tr>

<s:iterator value="newsList">
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><s:date name="issueDate" format="yyyy-MM-dd"/></td>
	<td><s:property value="newsTitle"/></td>
	<td><s:property value="author"/></td>
	<td>
		<s:a action="update-news" namespace="/_administrator">
			<s:param name="newsId"><s:property value="newsId"/></s:param>
			编辑
		</s:a> | 
		<s:a action="news-delete" namespace="/_administrator">
			<s:param name="newsId"><s:property value="newsId"/></s:param>
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