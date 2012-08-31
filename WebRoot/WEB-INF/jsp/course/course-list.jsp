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
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<link rel="stylesheet" type="text/css" href="/plugin/zoomer/zoomer.css" />
<style type="text/css">
	#zoomer-logo,#zoomer-foot { margin-left: 10px; }
</style> 
<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdCenter = "center_course";
</script>
<script type="text/javascript" src="/plugin/zoomer/zoomer.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
 	$('ul.thumb li').Zoomer({speedView:200,speedRemove:400,altAnim:true,speedTitle:400,debug:false});
});
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both">
	<s:include value="/WEB-INF/jsp/common/center-title.jsp"></s:include>
	
	<div>
		<div class="course_desc_title" style="background-color:<s:property value="trunkType.backgroundColor"/>;color:<s:property value="trunkType.fontColor"/>">
			/ <a href="<s:url action="course-catelog" namespace="/course"/>"><s:text name="COURSE"/></a> 
			/ <s:property value="%{getText(trunkType.courseTrunkTypeNameKey)}"/> 
			/ <s:property value="%{getText(branchType.courseBranchTypeNameKey)}"/>
		</div>
		<ul class="thumb">
			<s:iterator value="courseList">
				<li>
					<s:if test="language == 'zh'">
						<a href="/<s:property value="courseContext"/>/<s:property value="coursetrunktype.courseTrunkTypePath"/>/<s:property value="coursebranchtype.courseBranchTypePath"/>/<s:property value="courseId"/>.html" target="_blank"><img src="<s:property value="pictureOne"/>" alt="<s:property value="courseNameCn"/>" /></a>
					</s:if>
					<s:elseif test="language == 'ja'">
						<a href="/<s:property value="courseContext"/>/<s:property value="coursetrunktype.courseTrunkTypePath"/>/<s:property value="coursebranchtype.courseBranchTypePath"/>/<s:property value="courseId"/>.html" target="_blank"><img src="<s:property value="pictureOne"/>" alt="<s:property value="courseNameJp"/>" /></a>
					</s:elseif>
					<s:else>
						<a href="/<s:property value="courseContext"/>/<s:property value="coursetrunktype.courseTrunkTypePath"/>/<s:property value="coursebranchtype.courseBranchTypePath"/>/<s:property value="courseId"/>.html" target="_blank"><img src="<s:property value="pictureOne"/>" alt="<s:property value="courseNameEn"/>" /></a>
					</s:else>
				</li>
			</s:iterator>
		</ul>
	</div>
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>