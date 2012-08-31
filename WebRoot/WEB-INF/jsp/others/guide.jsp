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
<title><s:text name="GUIDE"/></title>

<script type="text/javascript" src="/js_base/jquery-1.4.2.js"></script>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript">
var colorIdLeft = "left_booking";
$(document).ready(function() {
	$("#leftContent").html("booking");
});
</script>

<style type="text/css">
	.box_main {width:750px;}
	.box_main ul.hovershow {
		list-style-type: none;
		float: left;
		display: inline;
		clear: both;
	}
	.box_main ul.hovershow li{
		float: left;
		display: inline;
		width: 600px;
		margin: 1px 0 1px 0;
	}
	.box_main ul.hovershow li a {
		display: block;
		width: 600px;
		positon: relative;
		text-decoration: none;
	}
	.box_main ul.hovershow li a img{ 
		width:100%;
		display: block;
		border:1px solid #a4c41b;
	}
	.box_main ul.hovershow li.t1 a:hover{
		z-index:100;
		margin: -50px 0 0 -200px;
		position: absolute;
	}
	.box_main ul.hovershow li.t1 a:hover img{
		width:800px;
		border:3px solid #a4c41b;
	}
</style>

</head>
<body>
<s:include value="/top/toptitle.html"></s:include>
<div class="center both" style="margin-bottom:12px;">
	
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>

  	<div class="right content">
  	
  	<div class="color_red" style="font-size:20px;margin-bottom:20px;"><s:text name="GUIDE_DESC1"/></div>
  	<div class="color_purple" style="font-size:18px;margin-bottom:20px;"><s:text name="GUIDE_DESC2"/></div>
  	<div class="box_main">
		<ul class="hovershow">
			<li class="t1"><a href="#"><img src="/images/common/step1.png"/></a></li>
			<li class="t1"><a href="#"><img src="/images/common/step2.png"/></a></li>
			<li class="t1"><a href="#"><img src="/images/common/step3.png"/></a></li>
		</ul>
	</div>
	<div class="color_purple" style="font-size:18px;margin-bottom:20px;"><s:text name="GUIDE_DESC3"/></div>
  	<div class="box_main">
		<ul class="hovershow">
			<li class="t1"><a href="#"><img src="/images/common/step4.png"/></a></li>
			<li class="t1"><a href="#"><img src="/images/common/step5.png"/></a></li>
		</ul>
	</div>
	
	</div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>
</body>
</html>