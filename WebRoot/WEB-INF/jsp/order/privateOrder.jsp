<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="PRIVATE_ORDER_PAGE"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js"></script>
<link rel="stylesheet" href="/js_base/theme/jquery.ui.all.css"> 
<script src="/js_base/ui/jquery.ui.core.js"></script> 
<script src="/js_base/ui/jquery.ui.widget.js"></script> 
<script src="/js_base/ui/jquery.ui.mouse.js"></script> 
<script src="/js_base/ui/jquery.ui.draggable.js"></script> 
<script src="/js_base/ui/jquery.ui.position.js"></script> 
<script src="/js_base/ui/jquery.ui.resizable.js"></script> 
<script src="/js_base/ui/jquery.ui.dialog.js"></script>
<script src="/js_base/ui/jquery.ui.tabs.js"></script>

<script src="/plugin/uniform/jquery.uniform.js" type="text/javascript"></script>
<link rel="stylesheet" href="/plugin/uniform/css/uniform.default.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" charset="utf-8">
$(function(){
	$("input, select, button").uniform();
});

jQuery(document).ready(function() {
	$("#package-dialog").dialog({
		autoOpen: false,
		height: 400,
		width: 600,
		modal: true
	});

	$("#package-tabs").tabs();
});

function openCourseDialog() {
	$("#package-dialog").dialog("open");
}

function closeCourseDialog() {
	$("#package-dialog").dialog("close");
}
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

<script type="text/javascript">
var courseBasicPrice;
function changeBasicPrice(price, pid, pvalue) {
	courseBasicPrice = price;
	adjustTotalPrice();
	jQuery("#coursePackageId").val(pvalue);
	jQuery("#show-course-package").html(jQuery("#" + pid).html());
	closeCourseDialog();
}

function adjustTotalPrice() {
	var totalPerson = jQuery("#totalPeopleNumber").val();
	if(3 >= totalPerson) {
		jQuery("#totalPrice").val(courseBasicPrice);
	}else {
		jQuery("#totalPrice").val(courseBasicPrice + (totalPerson - 3) * 150);
	}
}
</script>
</head>
<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>

  	<div class="right content">
  	
  	
<div class="order_page">
<s:form id="form1" namespace="/order" action="book-private-order">
<div><div><s:date name="date" format="yyyy-MM-dd"/></div></div>
<div><span onclick="javascript:history.go(-1);" class="order_close color_green hand"><s:text name="BACK"/></span></div>
<p/>
<div><s:text name="PRIVATE_ORDER_DETAIL1"/></div>
<p/>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC33"/> <a class="color_green" href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a></div>
<div><s:text name="PUBLIC_ORDER_PAGE_DESC34"/></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC13"/> </span><s:radio name="order.orderbasic.peopletitle.peopleTitleId" list="titleList" listKey="peopleTitleId" listValue="peopleTitleName" value="user.peopletitle.peopleTitleId" cssClass="validate[required] radio" /></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC14"/> </span><s:textfield id="contactPerson" name="order.orderbasic.contactPerson" value="%{user.firstName + ' ' + user.lastName}" cssClass="validate[required] text-input" /></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC15"/> </span><s:textfield id="cellNo" name="order.orderbasic.cellphone" value="%{user.cellphone}" cssClass="validate[required] text-input" /></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PUBLIC_ORDER_PAGE_DESC16"/> </span><s:textfield id="email" name="order.orderbasic.email" value="%{user.email}" cssClass="validate[required,custom[email]] text-input" /></div>
<div><span class="width150 inline_block color_green bold"><s:text name="PUBLIC_ORDER_PAGE_DESC17"/> </span><s:textfield name="order.orderbasic.telephone" value="%{user.telephone}"/></div>
<div><span class="width150 inline_block color_green bold"><s:text name="PUBLIC_ORDER_PAGE_DESC18"/> </span><s:textfield name="order.orderbasic.fax" value="%{user.fax}"/></div>
<div><span class="width150 inline_block color_green bold">*<s:text name="PRIVATE_ORDER_DETAIL2"/> </span><s:textfield id="totalPeopleNumber" name="order.totalPeopleNumber" value="3" cssClass="validate[required,custom[integer],min[1],max[10]]" onblur="adjustTotalPrice()"/></div>
<div><span class="inline_block color_green bold">* <s:text name="INDICATES_REQUIRED_FIELD"/></span></div>
<p/>
<div><s:text name="PRIVATE_ORDER_DETAIL3"/></div>
<div><s:text name="PRIVATE_ORDER_DETAIL4"/>: <a class="color_green" href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a></div>
<div><s:text name="PRIVATE_ORDER_DETAIL5"/></div>
<div><span class="color_green bold">*<s:text name="PRIVATE_ORDER_DETAIL6"/></span> </div>
<div><span class="color_red hand bold" onclick="openCourseDialog()"><s:text name="PRIVATE_ORDER_DETAIL7"/></span></div>
<div class="bold"><s:text name="PRIVATE_ORDER_DETAIL8"/>: <span id="show-course-package"></span></div>
<div><span class="color_green bold">*<s:text name="PRIVATE_ORDER_DETAIL9"/></span></div>
<div><input type="radio" name="order.courselocation.courseLocationId" value="<s:property value="courseLocation.courseLocationId"/>" checked="checked"/>at <span class="color_red bold"><s:property value="courseLocation.courseLocationName"/></span></div>
<p/>
<div><span class="color_green bold">*<s:text name="PRIVATE_ORDER_DETAIL10"/></span></div>
<div><input type="radio" name="order.classtime.classTimeId" value="<s:property value="classTime.classTimeId"/>" checked="checked"/><span class="color_red bold"><s:property value="classTime.classTimeContent"/></span></div>
<p/>
<div class="bold"><s:text name="PRIVATE_ORDER_DETAIL11"/></div>
<div class="color_orange bold">
<s:radio name="order.orderbasic.confirmtime.confirmTimeId" list="confirmTimeList" listKey="confirmTimeId" listValue="confirmTimeContent" value="1" />
</div>
<div class="color_green">* <s:text name="PRIVATE_ORDER_DETAIL12"/></div>
<div>
<div class="color_red bold"><s:text name="PRIVATE_ORDER_DETAIL13"/> <s:textfield id="totalPrice" name="order.totalPrice" value="" readonly="true" cssClass="validate[required,custom[integer]]"/></div>
</div>
<input class="width88" type="submit" value="<s:text name="BUY"/>" />
<br/>
<s:hidden name="courseDate"></s:hidden>
<s:hidden name="order.orderbasic.orderstatus.orderStatusId" value="1"/>
<s:hidden id="coursePackageId" name="order.coursepackage.coursePackageId"/>
<s:hidden name="order.flag" value="%{flag}"></s:hidden>
</s:form>
</div>

	</div>
    <br class="both" />
</div>

<div id="package-dialog" title="Package Select">
	<div id="package-tabs">
		<ul>
			<s:iterator value="packages" status="t">
				<li><a href="#tabs-<s:property value="%{#t.index+1}"/>"><s:property value="%{getText(key.courseTrunkTypeNameKey)}"/></a></li>
			</s:iterator>
		</ul>
		<s:iterator value="packages" status="t">
			<div id="tabs-<s:property value="%{#t.index+1}"/>">
				<s:iterator value="value" status="s">
					<div class="course_margin">
						<s:if test="key.courseTrunkTypeId == 1">
							<s:set value="500" name="basicPrice"/> 
						</s:if>
						<s:else>
							<s:set value="650" name="basicPrice"/> 
						</s:else>
						<input type="radio" name="select-coursePackageId" value='<s:property value="coursePackageId"/>' onclick='changeBasicPrice(<s:property value="#basicPrice"/>, "course-package-<s:property value="%{#t.index}"/>-<s:property value="%{#s.index}"/>", this.value)'/>
						<span id="course-package-<s:property value="%{#t.index}"/>-<s:property value="%{#s.index}"/>">
							<span class="color_green bold"><s:property value="%{getText(key.courseTrunkTypeNameKey)}"/> </span>
							<span class="color_red bold"><s:text name="PRIVATE_ORDER_DETAIL14"/> <s:property value="%{#s.index+1}"/></span>
						</span>
						<s:iterator value="packagewithcourses" status="st">
							<div id="package-names-" class="color_blue">
								<b onclick="showImage('pic_<s:property value="%{#t.index}"/>_<s:property value="%{#s.index}"/>_<s:property value="%{#st.index}"/>')" class="hand">
									<s:if test="language == 'ja'">
										<s:property value="course.courseNameJp"/>
									</s:if>
									<s:elseif test="language == 'zh'">
										<s:property value="course.courseNameCn"/>
									</s:elseif>
									<s:else>
										<s:property value="course.courseNameEn"/>
									</s:else>
								</b>
							</div>
							<div id="pic_<s:property value="%{#t.index}"/>_<s:property value="%{#s.index}"/>_<s:property value="%{#st.index}"/>" class="course_picture" onclick="hideImage('pic_<s:property value="%{#t.index}"/>_<s:property value="%{#s.index}"/>_<s:property value="%{#st.index}"/>')"><img src="<s:property value="course.pictureOne"/>" alt="<s:property value="course.courseNameEn"/>"/></div>
						</s:iterator>
					</div>
				</s:iterator>
			</div>
		</s:iterator>
	</div>
</div>


<s:include value="/bottom/bottomfoot.html"></s:include>
</body>
</html>