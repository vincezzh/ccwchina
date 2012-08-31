<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="/_administrator/skin/css/base.css">
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<style>
#calendar-course{ width:100%;}
#calendar-course{border-left:1px solid #e7e7e7;border-bottom:1px solid #e7e7e7;}
#calendar-course thead th{ color:#FFF} 
#calendar-course .calendar-title{ height:26px;}
#calendar-course td{padding:2px; border-right:1px solid #e7e7e7;}
#calendar-course td a:hover{ cursor:hand;}
#calendar-course td div{ margin-bottom:2px;}
#calendar-course .calendar-title th{ color:#999}
#calendar-course  thead th a{color:#a4c31d; cursor:hand;}
.bg-gray-title{ background:url(/images/framework/bg-title-gray.gif) left 2px repeat-x;}
#calendar-course h5 {margin: 0;padding: 0;font:100%/1.4 Arial, Helvetica, sans-serif;color:#a4c31d;}
.cal-blue{ background:#bbe2e9}
.cal-yellow{ background:#f4f29b}
.cal-green{ background:#dfeecf}
.cal-red{ background:#f9d1d2}
</style>

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>

<script type="text/javascript" src="/plugin/textSlider/jQuery.textSlider.js"></script>

<script type="text/javascript">
var currentMonth = '<s:date name="currentMonth" format="yyyyMMdd"/>';

jQuery(document).ready(function() {
	refreshCalendar('<s:property value="courseLocation.courseLocationId"/>', currentMonth);
});

function gotoRefreshCalendar(courseLocationId) {
	refreshCalendar(courseLocationId, currentMonth);
}

function refreshCalendar(courseLocationId, month) {
	if(courseLocationId == null)
		courseLocationId = "";
	if(month == null)
		month = "";
	$("#calendar").html('<div><img src="/images/common/loading.gif"/></div>');
	jQuery.ajax({
		type: "POST",
		url: "/_administrator/refresh-course-calendar.htm",
		data: "currentMonthDate=" + month + "&courseLocationId=" + courseLocationId,
		success: function(content) {
			jQuery('#calendar').html(content);
			startAllSlider();
		}
	});
}

function orderPublic(courseCalendarId) {
	window.open ("/_administrator/update-course-calendar.htm?courseCalendarId=" + courseCalendarId,"edit-course-calendar-page","height=500,width=700,top=50,left=50,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
}

function orderPrivate(date, classTimeId, courseLocationId) {
	window.open ("/_administrator/add-course-calendar.htm?courseDate=" + date + "&classTimeId=" + classTimeId + "&courseLocationId=" + courseLocationId,"add-course-calendar-page","height=500,width=700,top=50,left=50,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no");
}

function startAllSlider() {
	jQuery(".scrollDiv").textSlider({line:1,speed:500,timer:3000});
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>

<!--  内容列表   -->
<div>
	<s:form action="copy-course-calendar" namespace="/_administrator">
		复制从<s:textfield name="startDate"/>到<s:textfield name="endDate"/>的所有公共课程。<s:submit value="复制"/>
	</s:form>
</div>
<div id="location">
	<s:select list="locationList" listKey="courseLocationId" listValue="courseLocationName" value="courseLocation.courseLocationId" onchange="gotoRefreshCalendar(this.value)"></s:select>
</div>
<div id="calendar"></div>
</body>
</html>