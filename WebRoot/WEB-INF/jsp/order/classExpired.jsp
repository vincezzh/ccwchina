<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="CLASS_EXPIRED"/></title>

<script type="text/javascript" src="/js_base/jquery-1.4.2.js"></script>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript">
var colorIdLeft = "left_booking";
$(document).ready(function() {
	$("#leftContent").html("booking");
});
</script>
</head>
<body>
<s:include value="/top/toptitle.html"></s:include>
<div class="center both" style="margin-bottom:12px;">
	
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>

  	<div class="right content">
  	
  	
<div class="class_order_bg">
	<div><s:text name="DEAR"/>, <s:property value="contactPerson"/></div>
	<div><s:text name="EXPIRED_DESC1"/></div>
	<div class="hand color_green" onclick="javascript:history.go(-1);"><s:text name="BACK"/></div>
	<div style="margin:100px 0 0 100px;"><img src="/images/emotion/biaoqing_003.png"/></div>
</div>

	</div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>
</body>
</html>