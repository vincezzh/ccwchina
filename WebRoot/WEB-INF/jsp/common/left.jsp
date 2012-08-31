<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(document).ready(function() {
	$("#" + colorIdLeft).addClass("color_red");
});
</script>

<div class="left ccw_nav">
	<div class="leftHeader"><span class="color_green">CCW</span> in <span id="leftContent"></span></div>
	
    <ul>
    	<li style="margin-top:30px"><a href="<s:url action="index" namespace="/"/>"><span id="left_home"><s:text name="HOME"/></span></a></li>
    	<li><a href="<s:url action="about" namespace="/others"/>"><span id="left_about"><s:text name="ABOUT"/></span></a></li>
        <li><a href="<s:url action="course-catelog" namespace="/course"/>"><span id="left_course"><s:text name="COURSE"/></span></a></li>
        <li>
			<s:url id="calendar_url" action="course-calendar" namespace="/course">
		    	<s:param name="courseLocationId"><s:property value="#session.user_session.courselocation.courseLocationId"/></s:param>
		    </s:url>
		    <s:a href="%{calendar_url}"><span id="left_booking"><s:text name="BOOKING"/></span></s:a>
		</li>
        <li><a href="<s:url action="item-list" namespace="/store"/>"><span id="left_market"><s:text name="MARKET"/></span></a></li>
        
        <li style="margin-top:30px"><a href="<s:url action="news-list" namespace="/news"/>"><span id="left_news"><s:text name="NEWS"/></span></a></li>
        <li><a href="<s:url action="kitchen" namespace="/others"/>"><span id="left_kitchen"><s:text name="KITCHEN"/></span></a></li>
        <li><a href="<s:url action="photos" namespace="/others"/>"><span id="left_photos"><s:text name="PHOTOS"/></span></a></li>
        
        <li style="margin-top:30px"><a href="<s:url action="contact" namespace="/others"/>"><span id="left_contact"><s:text name="CONTACT"/></span></a></li>
        <li><a href="<s:url action="founder" namespace="/others"/>"><span id="left_founder"><s:text name="FOUNDER"/></span></a></li>
        <li><a href="<s:url action="team" namespace="/others"/>"><span id="left_team"><s:text name="TEAM"/></span></a></li>
        <li><a href="<s:url action="faqs" namespace="/others"/>"><span id="left_faqs"><s:text name="FAQS"/></span></a></li>
    </ul>
</div>