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
<title><s:text name="NEWS_LIST"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdLeft = "left_news";
$(document).ready(function() {
	$("#leftContent").html("news");
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	var year = '<s:property value="year"/>';
	$("#year_" + year).addClass("color_green");
});
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>

  	<div class="right content">
        <div class="navcontainer_2">
        	<span>
        		<s:url id="2011_news_url" action="news-list" namespace="/news">
			    	<s:param name="year">2011</s:param>
			    </s:url>
			    <s:a href="%{2011_news_url}"><div id="year_2011">2011</div></s:a>
        	</span>
        	<span>
        		<s:url id="2010_news_url" action="news-list" namespace="/news">
			    	<s:param name="year">2010</s:param>
			    </s:url>
			    <s:a href="%{2010_news_url}"><div id="year_2010">2010</div></s:a>
        	</span>
        	<span>
        		<s:url id="2009_news_url" action="news-list" namespace="/news">
			    	<s:param name="year">2009</s:param>
			    </s:url>
			    <s:a href="%{2009_news_url}"><div id="year_2009">2009</div></s:a>
        	</span>
        </div>

		<ul class="news_list_block">
			<s:iterator value="newsList">
				<li>
					<div class="private_text">
				       	<div style="height:110px;width:120px;overflow:hidden;margin:10px 10px 10px 10px;">
				        	<a href="/<s:property value="newsContext"/>/<s:property value="newsId"/>.html" target="_blank">
				        		<s:property value="newsTitle"/>
				        	</a>
				       	</div>
				       	<div style="margin:10px 10px 5px 10px;"><s:text name="BY"/> <s:property value="author"/></div>
				       	<div style="margin:5px 10px 10px 10px;"><s:date name="issueDate" format="yyyy-MM-dd"/></div>
				    </div>
		        </li>
	        </s:iterator>
	    </ul>
    </div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>