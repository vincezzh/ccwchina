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
<title><s:text name="CONTACT"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdLeft = "left_contact";
$(document).ready(function() {
	$("#leftContent").html("contact");
});
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>
	
  	<div class="right content">
			<div class="about2">
	            <p><div class="font_samll_2 color_green">book your course</div></p>
	            <ul>
	                <li>
	                	<div class="font_samll_2">Ms. Aga ZHU</div>
	                    <div class="font_samll"><s:text name="FOUNDER"/></div>
	                    <br />
	                    
	                    <div class="font_samll_2 color_gray">cell 86 1370 1873 243</div>
	                    <div class="font_samll_2 color_gray">(language En/Ch)</div>
	                    <div class="font_samll_2 color_gray">email</div>
	                    <div class="font_samll_2 color_orange">booking@chinesecookingworkshop.com</div>
	
	                </li>
	            </ul>
            </div>
            
            <div class="about2">
	            <p><div class="font_samll_2 color_green"><s:text name="NEW_BUSINESS_IDEAS"/></div></p>
	            <ul>
	            	<li>
	                	<div class="font_samll_2">Mr.Xiaojiao SUN</div>
	                    <div class="font_samll"><s:text name="FOUNDER"/></div>
	                    <br />
	
	                    <div class="font_samll_2 color_gray">tel. 86 21 5404 3310</div>
	                    <div class="font_samll_2 color_gray">fax. 86 21 5404 3310</div>
	                    <div class="font_samll_2 color_gray">cell 86 1381 7743 031</div>
	                    <div class="font_samll_2 color_gray">(language En/Ch/Jp)</div>
	                    <div class="font_samll_2 color_gray">email</div>
	                    <div class="font_samll_2 color_orange">booking@chinesecookingworkshop.com</div>
	                </li>
				</ul>
			</div>
			
            <br class="both" />
        </div>
    </div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>