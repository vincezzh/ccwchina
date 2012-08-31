<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片管理</title>
<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
function getCalendarTemplate() {
	$("#course-template").val('Loading...');
	jQuery.ajax({
		type: "POST",
		url: "/_administrator/get-calendar-template.htm",
		data: "from=" + $("#fromDate").val() + "&to=" + $("#toDate").val(),
		success: function(content) {
			jQuery('#course-template-show').html(content);
			jQuery('#course-template').val(content);
		}
	});
}
</script>
</head>

<body>
<div class="picture-manage-area">
<div>日期区间：</div>
从<div><s:textfield id="fromDate" name="from"/></div>
到<div><s:textfield id="toDate" name="to"/></div>
<div><input type="button" value="获取课程表" onclick="getCalendarTemplate()"/></div>
<div id="course-template-show"></div>
<textarea id="course-template" cols="100" rows="25"></textarea>
</div>
</body>
</html>
