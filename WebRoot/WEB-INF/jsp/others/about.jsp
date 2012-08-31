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
<title><s:text name="ABOUT"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdLeft = "left_about";
$(document).ready(function() {
	$("#leftContent").html("about");
});
</script>
<script type="text/javascript">
var language = "";
jQuery(document).ready(function() {
	jQuery.ajax({
	   type: "POST",
	   url: "/check-language.htm",
	   success: function(content) {
	   		if(content == 'ja') {
	   			jQuery('.jaLanguage').show();
	   			language = content;
	   		}else if(content == 'zh') {
	   			jQuery('.zhLanguage').show();
	   			language = content;
	   		}else {
	   			jQuery('.enLanguage').show();
	   			language = content;
	   		}
		}
	});
});
</script>
<style>
.enLanguage p, .jaLanguage p, .zhLanguage p {
	font-family: Arial Black, Gadget, sans-serif;
	font-size: 16px;
}
</style>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>
	
  	<div class="right content">
        <div class="enLanguage" style="display:none;">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="100%" align="left" style="word-break: break-all;">
				<img src="/images/common/ccw_logo.jpg" alt="CCW Logo" width="150" align="left" style="margin:8px;" />
				<p>Chinese Cooking Workshop (www.chinesecookingworkshop.com) was established in the year of 2003 and it's the first full time and hands-on expats targeted cooking program in Shanghai. We open 7 days per week, all year round in Shanghai; only take holiday break during Chinese New Year festival. </p>
				<p>In the year of 2005, we open our Pudong kitchen after Puxi in Shanghai, in the year of 2008, we open our Suzhou private cooking program with the strong support of the local expats communities. </p>
				<p>Chinese Cooking Workshop (www.chinesecookingworkshop.com) is a unique lifestyle and culture network to be more just cooking. The network is committed to develop the authentic cooking fun and enjoyable travel experience for both expats living in the cities we have program running and the travelers to China.</p>
				<p>Through hands-on cooking program, wet market tour, gourmet tour, Farmer's Market, mini food fair and food publications, Chinese Cooking Workshop expands its business into a lifestyle hub to attract alike minds all over the world. </p>
			</td>
			</tr>
			</table>
        </div>
        
        <div class="jaLanguage" style="display:none;">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="100%" align="left" style="word-break: break-all;">
				<img src="/images/common/ccw_logo.jpg" alt="CCW Logo" width="150" align="left" style="margin:8px;" />
				<p>中华料理教室成立始于2003年，是中国上海第一间全日制针对外籍人士的中华美食动手体验的工作坊。我们每周七天，天天开放，除了春节，全年无休。</p>
				<p>在2005年，我们开设了上海的浦东厨房。2008年，我们开设了苏州的私人美食课程，都得到了现地外籍人士社区的大力支持。</p>
				<p>中华料理教室是一个超越了美食本身的富有独特生活方式美感和中国文化交流的项目。这个项目联系着的是地道烹制美食的乐趣和有趣的旅行经验，无论是外国人，还是中国人，都可以在中华料理教室的项目中得到享受。</p>
				<p>通过自己亲手制作美食的课程，到市场行，美食之旅，农夫市集，迷你食品会到美食出版，中华料理教室在试探着各种围绕着美食的商业可能性，为世界上有着相同愿望的相同头脑提供了一处优质磁场。</p>
			</td>
			</tr>
			</table>
        </div>
            
        <div class="zhLanguage" style="display:none;">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="100%" align="left" style="word-break: break-all;">
				<img src="/images/common/ccw_logo.jpg" alt="CCW Logo" width="150" align="left" style="margin:8px;" />
				<p>中华料理教室成立始于2003年，是中国上海第一间全日制针对外籍人士的中华美食动手体验的工作坊。我们每周七天，天天开放，除了春节，全年无休。</p>
				<p>在2005年，我们开设了上海的浦东厨房。2008年，我们开设了苏州的私人美食课程，都得到了现地外籍人士社区的大力支持。</p>
				<p>中华料理教室是一个超越了美食本身的富有独特生活方式美感和中国文化交流的项目。这个项目联系着的是地道烹制美食的乐趣和有趣的旅行经验，无论是外国人，还是中国人，都可以在中华料理教室的项目中得到享受。</p>
				<p>通过自己亲手制作美食的课程，到市场行，美食之旅，农夫市集，迷你食品会到美食出版，中华料理教室在试探着各种围绕着美食的商业可能性，为世界上有着相同愿望的相同头脑提供了一处优质磁场。</p>
			</td>
			</tr>
			</table>
        </div>
    </div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>