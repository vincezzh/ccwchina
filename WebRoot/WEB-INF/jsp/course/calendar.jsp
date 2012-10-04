<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="<s:text name="KEYWORDS_KEYWORDS"/>" />
<meta name="description" content="<s:text name="KEYWORDS_DESC"/>" />
<meta name="author" content="www.chinesecookingworkshop.com, Chinese Cooking Workshop, 中华料理教室"/>
<title><s:text name="CALENDAR"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script src="/plugin/uniform/jquery.uniform.js" type="text/javascript"></script>
<link rel="stylesheet" href="/plugin/uniform/css/uniform.default.css" type="text/css" media="screen" charset="utf-8" />

<script type="text/javascript" src="/plugin/textSlider/jQuery.textSlider.js"></script>

<script type="text/javascript" charset="utf-8">
$(function(){
	$("input, select, button").uniform();
});
</script>
    
<style>
.tooltip {
	display:none;
	background:url(/images/common/white_big.png);
	height:140px;
	padding:40px 30px 10px 30px;
	width:310px;
	font-size:11px;
	color:#000;
}
.calendar-size {margin-bottom:12px;width:980px;}
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

<script type="text/javascript">
var colorIdCenter = "center_booking";
</script>
<script type="text/javascript" src="/js_base/jquery.tools.min.js" ></script>
<script type="text/javascript">
jQuery(document).ready(function() {
	refreshCalendar(<s:property value="location.courseLocationId"/>, '<s:date name="month" format="yyyyMMdd"/>');
});

function gotoRefreshCalendar(courseLocationId) {
	refreshCalendar(courseLocationId, '<s:date name="month" format="yyyyMMdd"/>');
}

function exportCalendar() {
	var courseLocationId = $("#courseLocationSelector").val();
	window.location.href = "/course/export-calendar.htm?" + "courseLocationId=" + courseLocationId + "&monthDate=" + '<s:date name="month" format="yyyyMMdd"/>';
}

function refreshCalendar(courseLocationId, month) {
	$("#calendar").html('<div style="height:605px;"><img src="/images/common/loading.gif" alt="loading"/></div>');
	jQuery.ajax({
		type: "POST",
		url: "/course/calendar.htm",
		data: "courseLocationId=" + courseLocationId + "&monthDate=" + month,
		success: function(content) {
			jQuery('#calendar').html(content);
			startAllSlider();
		}
	});
}

function orderPublic(courseCalendarId) {
	window.location.href = "/order/go-to-public-order.htm?courseCalendarId=" + courseCalendarId;
}

function orderPrivate(date, classTimeId, courseLocationId) {
	window.location.href = "/order/go-to-private-order.htm?courseDate=" + date + "&classTimeId=" + classTimeId + "&courseLocationId=" + courseLocationId;
}

function startAllSlider() {
	jQuery(".scrollDiv").textSlider({line:1,speed:500,timer:3000});
}
</script>
</head>
<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both">
	<s:include value="/WEB-INF/jsp/common/center-title.jsp"></s:include>
</div>

<div class="center both calendar-size">
  	
    <div class="navcontainer_2">

		<div id="location">
			<s:select list="locationList" listKey="courseLocationId" listValue="courseLocationName" id="courseLocationSelector" value="location.courseLocationId" onchange="gotoRefreshCalendar(this.value)"></s:select>
			<a href="javascript:void(0);" onclick="exportCalendar()"><b><s:text name="DOWNLOAD"/> <s:text name="CALENDAR"/></b></a>
		</div>
		<div id="calendar"></div>
   	</div>
      	
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>