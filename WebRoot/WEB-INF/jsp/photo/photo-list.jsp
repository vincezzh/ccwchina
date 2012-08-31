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
<title><s:text name="PHOTO_LIST"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="/plugin/lightbox/jquery.lightbox-0.5.js"></script>
<link rel="stylesheet" type="text/css" href="/plugin/lightbox/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" type="text/css" href="/plugin/lightbox/common.css" />
<script type="text/javascript">
var colorIdLeft = "left_photos";
$(document).ready(function() {
	$("#leftContent").html("photos");
});
</script>
<script type="text/javascript">
$(function() {
	<s:bean name="org.apache.struts2.util.Counter" id="counter">
		<s:param name="first" value="1"/>
		<s:param name="last" value="typeCount"/>
		<s:iterator>
			$('#gallery_<s:property value="%{current-2}"/> a').lightBox();
		</s:iterator>
	</s:bean>
});
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>
	
  	<div class="right content">

		<div>
			<s:iterator value="photoList" id="hm" status="st">
				<div id="gallery_<s:property value="#st.index"/>" class="photo_block" style="width:130px">
					<div>
			    		<ul>
			    			<s:iterator value="#hm.value" status="s">
				                <s:if test="#s.index == 0">
				                	<li>
				                		<a href="<s:property value="photoPath"/>" title="<s:property value="photoTitle"/>">
					                        <img src="<s:property value="photoIndexPath"/>" alt="<s:property value="photoTitle"/>" width="130" height="130"/>
					                    </a>
					                </li>
			                	</s:if>
			                	<s:else>
				                	<li style="display:none">
				                		<a href="<s:property value="photoPath"/>" title="<s:property value="photoTitle"/>">
					                        <img src="<s:property value="photoIndexPath"/>" alt="<s:property value="photoTitle"/>" width="130" height="130"/>
					                    </a>
					                </li>
			                	</s:else>
			                </s:iterator>
			            </ul>
		            </div>
		            <div style="text-align:center">
		            	<div class="photo_type"><s:property value="%{getText(#hm.key)}"/></div>
		            	<div class="color_blue photo_number"><s:property value="#hm.value.size()"/></div>
		            </div>
		        </div>
	        </s:iterator>
        </div>
    </div>

    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>