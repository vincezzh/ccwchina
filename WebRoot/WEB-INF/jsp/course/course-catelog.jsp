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
<title><s:text name="COURSE_CATELOG"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdLeft = "left_course";
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>

  	<div class="right content">
        <div class="navcontainer_2">
       	  <p class="font_samll_2 color_gray">
       	  	<s:text name="COURSE_CATELOG_DESC"/>
       	  </p>
      	</div>
		<div class="course">
        	<ul>
            	<li class="course_li_1">
                	<ul>
                    	<li style="width:115px"><img src="/images/framework/1.png" width="108" height="209" /></li>
                        <li style="width:165px">
                        	<div class="color_orange">
                        		<s:url id="wok_url" action="course-desc" namespace="/course">
							    	<s:param name="trunk">2</s:param>
							    </s:url>
							    <s:a href="%{wok_url}"><s:text name="CHINESE_WOK_COURSE"/></s:a>
                        	</div>

                            <div class="font_samll_2 color_gray">
								<s:text name="CHINESE_WOK_COURSE_DESC_1"/><br/>
								<s:text name="CHINESE_WOK_COURSE_DESC_2"/>
							</div>
                            <ul class="font_samll_2 color_gray" style="margin-top:10px">
                            	<li class="course_li_3"><a href="/course/course-desc.htm?trunk=2&branch=6"><s:text name="CHINESE_WOK_COURSE_DESC_3"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=2&branch=7"><s:text name="CHINESE_WOK_COURSE_DESC_4"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=2&branch=8"><s:text name="CHINESE_WOK_COURSE_DESC_5"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=2&branch=9"><s:text name="CHINESE_WOK_COURSE_DESC_6"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=2&branch=10"><s:text name="CHINESE_WOK_COURSE_DESC_7"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=2&branch=11"><s:text name="CHINESE_WOK_COURSE_DESC_8"/></a></li>
                            </ul>
                        </li>

                    </ul>
                </li>
                
                <li class="course_li_1">
                	<ul>
                    	<li style="width:115px"><img src="/images/framework/2.png" width="108" height="209" /></li>
                        <li style="width:165px">
                        	<div class="color_orange">
                        		<s:url id="dim_sum_url" action="course-desc" namespace="/course">
							    	<s:param name="trunk">1</s:param>
							    </s:url>
							    <s:a href="%{dim_sum_url}"><s:text name="CHINESE_DIM_SUM_COURSE"/></s:a>
                        	</div>
                            <div class="font_samll_2 color_gray">
                            	<s:text name="CHINESE_DIM_SUM_COURSE_DESC1"/><br/>
                            	<s:text name="CHINESE_DIM_SUM_COURSE_DESC2"/>
							</div>

                            <ul class="font_samll_2 color_gray" style="margin-top:10px">
                            	<li class="course_li_3"><a href="/course/course-desc.htm?trunk=1&branch=1"><s:text name="CHINESE_DIM_SUM_COURSE_DESC3"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=1&branch=2"><s:text name="CHINESE_DIM_SUM_COURSE_DESC4"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=1&branch=3"><s:text name="CHINESE_DIM_SUM_COURSE_DESC5"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=1&branch=4"><s:text name="CHINESE_DIM_SUM_COURSE_DESC6"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=1&branch=5"><s:text name="CHINESE_DIM_SUM_COURSE_DESC7"/></a></li>
                            </ul>
                        </li>
                    </ul>

                </li>
                
                <li class="course_li_1">
                	<ul>
                    	<li style="width:115px"><img src="/images/framework/3.png" width="108" height="209" /></li>
                        <li style="width:165px">
                        	<div class="color_orange">
                        		<a href="http://www.bakerykitchen.org" target="_blank"><s:text name="BAKERY_COURSE"/></a>
                        	</div>
                            <div class="font_samll_2 color_gray">
                            	<s:text name="BAKERY_COURSE_DESC1"/>
                            </div>
                            <ul class="font_samll_2 color_gray" style="margin-top:10px">
                            	<li class="course_li_3"><s:text name="BAKERY_COURSE_DESC2"/></li>
                                <li class="course_li_3"><s:text name="BAKERY_COURSE_DESC3"/></li>
                                <li class="course_li_3"><s:text name="BAKERY_COURSE_DESC4"/></li>
                                <li class="course_li_3"><s:text name="BAKERY_COURSE_DESC5"/></li>
                                <li class="course_li_3"><s:text name="BAKERY_COURSE_DESC6"/></li>
                            </ul>
                        </li>
                    </ul>
                </li>

                
                <li class="course_li_1">
                	<ul>
                    	<li style="width:115px"><img src="/images/framework/4.png" width="108" height="209" /></li>
                        <li style="width:165px">
                        	<div class="color_orange">
                        		<s:url id="international_url" action="course-desc" namespace="/course">
							    	<s:param name="trunk">7</s:param>
							    </s:url>
							    <s:a href="%{international_url}"><s:text name="INTERNATIONAL_COURSE"/></s:a>
                        	</div>
                            <div class="font_samll_2 color_gray">
								<s:text name="INTERNATIONAL_COURSE_DESC1"/><br/>
								<s:text name="INTERNATIONAL_COURSE_DESC2"/>
							</div>
                            <ul class="font_samll_2 color_gray" style="margin-top:10px">
                            	<li class="course_li_3"><a href="/course/course-desc.htm?trunk=7&branch=12"><s:text name="INTERNATIONAL_COURSE_DESC3"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=7&branch=13"><s:text name="INTERNATIONAL_COURSE_DESC4"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=7&branch=14"><s:text name="INTERNATIONAL_COURSE_DESC5"/></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                
                <li class="course_li_1">
                	<ul>

                    	<li style="width:115px"><img src="/images/framework/5.png" width="108" height="209" /></li>
                        <li style="width:165px">
                        	<div class="color_orange">
                        		<s:url id="events_url" action="course-desc" namespace="/course">
							    	<s:param name="trunk">8</s:param>
							    </s:url>
							    <s:a href="%{events_url}"><s:text name="COOKING_EVENTS"/></s:a>
                        	</div>
                            <div class="font_samll_2 color_gray">
                            	<s:text name="COOKING_EVENTS_DESC1"/><br/>
                            	<s:text name="COOKING_EVENTS_DESC2"/>
                            </div>
                            <ul class="font_samll_2 color_gray" style="margin-top:10px">
                            	<li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=15"><s:text name="COOKING_EVENTS_DESC3"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=16"><s:text name="COOKING_EVENTS_DESC4"/></a></li>
								<li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=17"><s:text name="COOKING_EVENTS_DESC5"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=18"><s:text name="COOKING_EVENTS_DESC6"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=19"><s:text name="COOKING_EVENTS_DESC7"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=20"><s:text name="COOKING_EVENTS_DESC8"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=21"><s:text name="COOKING_EVENTS_DESC9"/></a></li>
                                <li class="course_li_3"><a href="/course/course-desc.htm?trunk=8&branch=22"><s:text name="COOKING_EVENTS_DESC10"/></a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>

    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>