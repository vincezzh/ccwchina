<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="PUBLIC_ORDER_PAGE"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js"></script>
<script src="/plugin/uniform/jquery.uniform.js" type="text/javascript"></script>
<link rel="stylesheet" href="/plugin/uniform/css/uniform.default.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" charset="utf-8">
$(function(){
	$("input, select, button, textarea").uniform();
});
</script>

<script src="/plugin/validation/js/jquery.validationEngine-<s:property value="language"/>.js" type="text/javascript" charset="utf-8"></script>
<script src="/plugin/validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="/plugin/validation/css/validationEngine.jquery.css" type="text/css"/>

<script type="text/javascript">
jQuery(document).ready(function(){
    jQuery("#form1").validationEngine();
});
</script>

<script type="text/javascript">
function refreshTotalPrice() {
	var plusFriend = $("#plusFriend").val();
	$("#total_price").html((parseInt(plusFriend) + 1) * <s:property value="course.pricePerPerson"/>);
}

function showImage(pid) {
	$("#" + pid).slideDown();
}

function hideImage(pid) {
	$("#" + pid).slideUp();
}

function hideAllImages() {
	$(".course_picture").slideUp();
}
</script>
<script type="text/javascript">
var colorIdLeft = "left_booking";
$(document).ready(function() {
	$("#leftContent").html("booking");
});
</script>
</head>
<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>

  	<div class="right content">


<div class="order_page" onclick="hideAllImages()">
<s:form id="form1" namespace="/order" action="book-public-order">
<div><s:date name="course.classDate" format="yyyy-MM-dd"/></div>
<div><span onclick="javascript:history.go(-1);" class="order_close color_green hand">back</span></div>
<p/>
<div>
	<s:text name="PUBLIC_ORDER_PAGE_DESC3"/> <span class="background_red"><s:property value="course.courselocation.courseLocationName"/></span>
	<s:text name="PUBLIC_ORDER_PAGE_DESC5"/> <span class="background_red"><s:property value="course.classtime.classTimeContent"/></span> <s:text name="PUBLIC_ORDER_PAGE_DESC36"/> <span class="background_red"><s:date name="course.classDate" format="yyyy-MM-dd"/></span>.
</div>

<div><s:text name="PUBLIC_ORDER_PAGE_DESC6"/> <span class="background_red"><s:property value="course.seatLeft"/></span> <s:text name="PUBLIC_ORDER_PAGE_DESC7"/></div>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC8"/> <span class="background_red"><s:property value="course.pricePerPerson"/></span> <s:text name="PUBLIC_ORDER_PAGE_DESC9"/></div>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC10"/>:</div>
<s:iterator value="course.calendarwithcourses" status="st">
	<div class="course_margin">
		<div class="color_blue">
		<b onmouseover="showImage('pic_<s:property value="%{#st.index}"/>')">
			<s:property value="%{#st.index+1}"/>. 
		<s:if test="language == 'ja'">
			<s:property value="course.courseNameJp"/>
		</s:if>
		<s:elseif test="language == 'zh'">
			<s:property value="course.courseNameCn"/>
		</s:elseif>
		<s:else>
			<s:property value="course.courseNameEn"/>
		</s:else>
		</b></div>
		<div id="pic_<s:property value="%{#st.index}"/>" class="course_picture" onmouseout="hideImage('pic_<s:property value="%{#st.index}"/>')"><img src="<s:property value="course.pictureOne"/>" alt="<s:property value="course.courseNameEn"/>"/></div>
	</div>
</s:iterator>
<p/>
<div><s:checkbox name="order.needMap" value="false" fieldValue="true"/><s:text name="PUBLIC_ORDER_PAGE_DESC4"/></div>
<div><s:checkbox name="order.orderbasic.isVegetarian" value="%{user.isVegetarian}" fieldValue="true"/><s:text name="PUBLIC_ORDER_PAGE_DESC11"/></div>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC12"/> <s:textfield name="order.orderbasic.allergic" value="%{user.allergic}"/></div>
<hr/>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC13"/> </span><s:radio name="order.orderbasic.peopletitle.peopleTitleId" list="titleList" listKey="peopleTitleId" listValue="peopleTitleName" value="user.peopletitle.peopleTitleId" cssClass="validate[required] radio" /></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC14"/> </span><s:textfield id="contactPerson" name="order.orderbasic.contactPerson" value="%{user.firstName + ' ' + user.lastName}" cssClass="validate[required] text-input" /></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC15"/> </span><s:textfield id="cellNo" name="order.orderbasic.cellphone" value="%{user.cellphone}" cssClass="validate[required] text-input" /></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC16"/> </span><s:textfield id="email" name="order.orderbasic.email" value="%{user.email}" cssClass="validate[required,custom[email]] text-input" /></div>
<div><span class="width150 inline_block color_green bold"><s:text name="PUBLIC_ORDER_PAGE_DESC17"/> </span><s:textfield name="order.orderbasic.telephone" value="%{user.telephone}"/></div>
<div><span class="width150 inline_block color_green bold"><s:text name="PUBLIC_ORDER_PAGE_DESC18"/> </span><s:textfield name="order.orderbasic.fax" value="%{user.fax}"/></div>
<div><span class="width150 inline_block color_green bold"><s:text name="PUBLIC_ORDER_PAGE_DESC24"/> </span><s:textfield name="order.orderbasic.address" value="%{user.address}"/></div>
<div><span class="width150 inline_block color_green bold"><s:text name="PUBLIC_ORDER_PAGE_DESC25"/> </span><s:textfield name="order.orderbasic.zipCode" value="%{user.zipcode}"/></div>
<div><span class="inline_block color_green bold">* <s:text name="INDICATES_REQUIRED_FIELD"/></span></div>
<p/>
<div>
	<s:text name="PUBLIC_ORDER_PAGE_DESC19"/> 
	<s:textfield id="plusFriend" name="order.totalPeopleNumber" value="0" onchange="refreshTotalPrice()" size="2" maxlength="1"/> 
	<s:text name="PUBLIC_ORDER_PAGE_DESC20"/>
	<s:text name="PUBLIC_ORDER_PAGE_DESC21"/> <s:property value="course.pricePerPerson"/> 
	<s:text name="PUBLIC_ORDER_PAGE_DESC22"/> <span id="total_price" class="background_red"><s:property value="course.pricePerPerson"/></span>
</div>
<div>
	<s:text name="PUBLIC_ORDER_PAGE_DESC23"/>:
</div>
<s:textarea rows="5" cols="90" name="order.orderbasic.description"></s:textarea>
<p/>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC26"/></div>
<div class="color_orange bold"><s:radio name="order.orderbasic.confirmtime.confirmTimeId" list="confirmTimeList" listKey="confirmTimeId" listValue="confirmTimeContent" value="1" /></div>
<s:hidden name="orderFullMonth" value="false"></s:hidden>
<!-- 
<s:if test="packageClassesDate == null || packageClassesDate.size() == 0">
	<s:hidden name="orderFullMonth" value="false"></s:hidden>
</s:if>
<s:else>
	<div>
		<s:text name="PUBLIC_ORDER_PAGE_DESC27"/>
		<s:text name="PUBLIC_ORDER_PAGE_DESC28"/> <s:date name="course.classDate" format="yyyy-MM"/> <s:property value="%{getText(course.coursetrunktype.courseTrunkTypeNameKey)}"/> <s:text name="PUBLIC_ORDER_PAGE_DESC29"/>
		<s:text name="PUBLIC_ORDER_PAGE_DESC35"/>: 
		<div>
			<s:iterator value="packageClassesDate" id="classDateTime">
				<span class="color_blue"><s:date name="classDateTime" format="MM-dd"/></span>, 
			</s:iterator>
		</div>
	</div>
	<div><s:checkbox name="orderFullMonth" value="false" fieldValue="true"/> <s:text name="PUBLIC_ORDER_PAGE_DESC30"/></div>
</s:else>
-->
<p/>
<div>
<input type="submit" value="<s:text name="PUBLIC_ORDER_PAGE_DESC31"/>"/> 
<input type="reset" value="<s:text name="PUBLIC_ORDER_PAGE_DESC32"/>"/>
</div>
<br/>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC33"/> <a class="color_green" href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a></div>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC34"/></div>

<s:hidden id="seatLeft" name="seatLeft" value="%{course.seatLeft}"/>
<s:hidden name="courseCalendarId" value="%{course.courseCalendarId}"/>
<s:hidden name="order.orderbasic.orderstatus.orderStatusId" value="1"/>
<s:hidden name="plusCourseCalendarid"/>
<s:hidden name="pricePerPerson" value="%{course.pricePerPerson}"></s:hidden>
<s:hidden name="order.flag" value="%{flag}"></s:hidden>
</s:form>
</div>

	</div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>