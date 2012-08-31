<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="ORDER_SUCCESSFUL"/></title>

<script type="text/javascript" src="/js_base/jquery-1.4.2.js"></script>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript">
var colorIdLeft = "left_market";
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
  	
  	
<div class="class_order_successful_bg">
	<div><s:text name="DEAR"/>, <s:property value="contactPerson"/></div>
	<div><s:text name="SUCCESSFUL_DESC1"/> </div>
	<div><s:text name="SUCCESSFUL_DESC2"/></div>
	<p>
	<s:text name="CLASS_EXPIRED"/><s:text name="SUCCESSFUL_DESC3"/>
	<div><s:property value="oId"/></div>
	</p>
	<div style="margin: 50px 0 0 100px"><img src="/images/emotion/biaoqing_009.png"/></div>
</div>


	</div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>
</body>
</html>