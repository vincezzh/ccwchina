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
<title><s:text name="WELCOME_TO_CHINESE_COOKING_WORKSHOP"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="/plugin/image-slider/image-slider.js" ></script>
<link rel="stylesheet" type="text/css" href="/plugin/image-slider/image-slider.css" />
<script type="text/javascript">
$(document).ready(function() {
	setupImageSlider();
});
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both">
	<div class="left ccw_1">
        <a href="<s:url action="news-list" namespace="/news"/>">
        	<img src="/images/framework/22.gif" style="margin-left:38px" width="136" height="25" />
        </a>
        <div class="private_text">
        	<div style="height:110px;width:120px;overflow:hidden;margin:10px 10px 10px 10px;">
	        	<a href="/<s:property value="newsContext"/>/<s:property value="news.newsId"/>.html" target="_blank">
	        		<s:property value="news.newsTitle"/>
	        	</a>
        	</div>
        	<div style="margin:10px 10px 5px 10px;"><s:text name="BY"/> <s:property value="news.author"/></div>
        	<div style="margin:5px 10px 10px 10px;"><s:date name="news.issueDate" format="yyyy-MM-dd"/></div>
        </div>
    </div>
	<div class="right banner_nav">
        <div id="navcontainer">
	        <ul id="navlist">
	            <li id="active"><a href="<s:url action="about" namespace="/others"/>"><s:text name="ABOUT_CCW"/></a></li>
	            <li><a href="<s:url action="course-catelog" namespace="/course"/>"><s:text name="COURSE"/></a></li>
	            <li>
	            	<s:url id="calendar_url" action="course-calendar" namespace="/course">
				    	<s:param name="courseLocationId"><s:property value="#session.user_session.courselocation.courseLocationId"/></s:param>
				    </s:url>
				    <s:a href="%{calendar_url}"><s:text name="BOOKING"/></s:a>
	            </li>
	            <li><a href="<s:url action="contact" namespace="/others"/>"><s:text name="CONTACT"/></a></li>
	        </ul>
	        <div style="float:right;margin-top:-5px;">
	        	<a href="<s:url action="item-list" namespace="/store"/>">
	        		<img src="/images/common/market.png" height="35" title="<s:text name="CCW_MARKET"/>" alt="<s:text name="CCW_MARKET"/>"/>
	        	</a>
	        </div>
        </div>
    
	    <div class="slider-main_view" style="margin-top:8px">
	        <div class="slider-window">
	            <div class="slider-image_reel">
	            	<s:iterator value="imageSliderAdvertisement">
	            		<a href="<s:property value="advertisementLink"/>" target="_blank"><img src="<s:property value="advertisementPath"/>" alt="<s:property value="advertisementTitle"/>" width="508" height="240" /></a>
	            	</s:iterator>
	            </div>
	        </div>
	        <div class="slider-paging">
	        	<s:iterator value="imageSliderAdvertisement" status="s">
            		<a href="#" rel="<s:property value="%{#s.index+1}"/>"><s:property value="%{#s.index+1}"/></a>
            	</s:iterator>
	        </div>
	    </div>
    </div>
</div>
<div class="center interent both"><img src="/images/framework/interest.gif" width="557" height="31" /></div>
<div class="center show1 font_samll_2">
	<ul>
		<s:iterator value="courseList" status="s">
			<s:if test="#s.last">
				<li style="margin-right:0px;">
			</s:if>
			<s:else>
				<li>
			</s:else>
		        	<div><img src="<s:property value="pictureOne"/>" alt="<s:property value="courseNameEn"/>" width="98" height="97" /></div>
		            <div class="line_h_20">
		            	<a target="_blank" href="/<s:property value="courseContext"/>/<s:property value="coursetrunktype.courseTrunkTypePath"/>/<s:property value="coursebranchtype.courseBranchTypePath"/>/<s:property value="courseId"/>.html">
		            		<s:if test="language == 'zh'">
								<s:property value="courseNameCn"/>
							</s:if>
							<s:elseif test="language == 'ja'">
								<s:property value="courseNameJp"/>
							</s:elseif>
							<s:else>
								<s:property value="courseNameEn"/>
							</s:else>
		            	</a>
		            </div>
		        </li>
        </s:iterator>
        <br class="both" />
    </ul>
</div>

<div class="center bottom_adv">
	<ul>
		<s:iterator value="middleTwoAdvertisement">
			<li><a href="<s:property value="advertisementLink"/>" target="_blank"><img src="<s:property value="advertisementPath"/>" alt="<s:property value="advertisementTitle"/>" width="349" height="177" /></a></li>
    	</s:iterator>
        <br class="both" />
    </ul>
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>