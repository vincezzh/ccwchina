<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<span><a href="<s:property value="pageContainer.allPages.get(0).pageLink"/>">首页</a></span>
<s:if test="pageContainer.currentPageNumber > 1">
	<span><a href="<s:property value="pageContainer.allPages.get(pageContainer.currentPageNumber-2).pageLink"/>">上一页</a></span>
</s:if>
<span>第<s:property value="pageContainer.currentPageNumber"/>页</span>
<s:if test="pageContainer.totalPagesNumber > pageContainer.currentPageNumber">
	<span><a href="<s:property value="pageContainer.allPages.get(pageContainer.currentPageNumber).pageLink"/>">下一页</a></span>
</s:if>
<span><a href="<s:property value="pageContainer.allPages.get(pageContainer.totalPagesNumber-1).pageLink"/>">末页</a></span>
<span>
	第
		<select name="pageNumber" onchange="javascript:window.location.href=this.value;">
			<s:iterator value="pageContainer.allPages">
				<s:if test="currentPageNumber == pageContainer.currentPageNumber">
					<option value="<s:property value="pageLink"/>" selected="true"><s:property value="currentPageNumber"/></option>
				</s:if>
				<s:else>
					<option value="<s:property value="pageLink"/>"><s:property value="currentPageNumber"/></option>
				</s:else>
			</s:iterator>
		</select>
	页
</span>