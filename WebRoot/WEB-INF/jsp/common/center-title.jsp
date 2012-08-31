<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">

</style>

<script type="text/javascript">
$(document).ready(function() {
	if(colorIdCenter != null)
		$("#" + colorIdCenter).addClass("color_red");
});
</script>

<script type= "text/javascript">
$(function(){
	//Get our elements for faster access and set overlay width
	var div = $('div.sc_menu'),
		ul = $('ul.sc_menu'),
		ulPadding = 15;
	
	//Get menu width
	var divWidth = div.width();
 
	//Remove scrollbars	
	div.css({overflow: 'hidden'});
	
	//Find last image container
	var lastLi = ul.find('li:last-child');
	
	//When user move mouse over menu
	div.mousemove(function(e){
		//As images are loaded ul width increases,
		//so we recalculate it each time
		var ulWidth = lastLi[0].offsetLeft + lastLi.outerWidth() + ulPadding;	
		var left = (e.pageX - div.offset().left) * (ulWidth-divWidth) / divWidth;
		div.scrollLeft(left);
	});
});
</script>

<div class="navcontainer_2">
	<div class="sc_menu">
		<ul class="sc_menu">
			<li><a href="<s:url action="index" namespace="/"/>"><span id="center_home"><s:text name="HOME"/></span></a></li>
			<li><a href="<s:url action="about" namespace="/others"/>"><span id="center_about"><s:text name="ABOUT"/></span></a></li>
			<li><a href="<s:url action="course-catelog" namespace="/course"/>"><span id="center_course"><s:text name="COURSE"/></span></a></li>
			<s:url id="calendar_url" action="course-calendar" namespace="/course">
		    	<s:param name="courseLocationId"><s:property value="#session.user_session.courselocation.courseLocationId"/></s:param>
		    </s:url>
			<li><s:a href="%{calendar_url}"><span id="center_booking"><s:text name="BOOKING"/></span></s:a></li>
			<li><a href="<s:url action="item-list" namespace="/store"/>"><span id="center_market"><s:text name="MARKET"/></span></a></li>
			<li><a href="<s:url action="news-list" namespace="/news"/>"><span id="center_news"><s:text name="NEWS"/></span></a></li>
			<li><a href="<s:url action="kitchen" namespace="/others"/>"><span id="center_kitchen"><s:text name="KITCHEN"/></span></a></li>
			<li><a href="<s:url action="photos" namespace="/others"/>"><span id="center_photos"><s:text name="PHOTOS"/></span></a></li>
			<li><a href="<s:url action="contact" namespace="/others"/>"><span id="center_contact"><s:text name="CONTACT"/></span></a></li>
			<li><a href="<s:url action="founder" namespace="/others"/>"><span id="center_founder"><s:text name="FOUNDER"/></span></a></li>
			<li><a href="<s:url action="team" namespace="/others"/>"><span id="center_team"><s:text name="TEAM"/></span></a></li>
			<li><a href="<s:url action="faqs" namespace="/others"/>"><span id="center_faqs"><s:text name="FAQS"/></span></a></li>
		</ul>
	</div>
</div>