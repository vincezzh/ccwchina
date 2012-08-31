<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="EDIT_INFORMATION"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script src="/plugin/uniform/jquery.uniform.js" type="text/javascript"></script>
<link rel="stylesheet" href="/plugin/uniform/css/uniform.default.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" charset="utf-8">
$(function(){
	$("input, select, button").uniform();
});
</script>

<script src="/plugin/validation/js/jquery.validationEngine-<s:property value="language"/>.js" type="text/javascript" charset="utf-8"></script>
<script src="/plugin/validation/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="/plugin/validation/css/validationEngine.jquery.css" type="text/css"/>

<script type="text/javascript">
jQuery(document).ready(function(){
    jQuery("#form1").validationEngine();
    jQuery("#form2").validationEngine();
});
</script>

<script type="text/javascript">
var colorIdCenter = null;
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both">
	<s:include value="/WEB-INF/jsp/common/center-title.jsp"></s:include>
</div>

<div class="center both" style="margin-bottom:12px;">
	<s:form id="form2" name="form2" method="post" action="edit-password">
	 <table width="100%" border="0" cellspacing="3" cellpadding="0" class="font_samll_2 padding_right_5 line_h_20">
	  <tr align="center">
	    <td colspan="2" class="color_green"><strong><s:text name="EDIT_PERSONAL_PASSWORD"/></strong></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="PASSWORD"/></strong></td>
	    <td align="left"><s:password id="password" name="password" cssClass="validate[required],minSize[6],maxSize[20] text-input"></s:password></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="CONFIRM_PASSWORD"/></strong></td>
	    <td align="left"><input type="password" name="passwordConfirm" id="passwordConfirm" value="" class="validate[required,equals[password]] text-input"/></td>
	  </tr>
	  <tr>
        <td colspan="2" align="left"><span class="color_red"><s:property value="passwordResult"/></span></td>
      </tr>
	  <tr align="center">
	    <td colspan="2">
	      <input type="submit" value="<s:text name="SAVE"/>" />
	    </td>
	  </tr>
	</table>
	</s:form>
	
	<s:form id="form1" name="form1" method="post" action="edit-information">
	 <table width="100%" border="0" cellspacing="3" cellpadding="0" class="font_samll_2 padding_right_5 line_h_20">
	  <tr align="center">
	    <td colspan="2" class="color_green"><strong><s:text name="EDIT_PERSONAL_INFORMATION"/></strong></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="TITLE"/></strong></td>
	    <td align="left">
	      <span>
	      	<s:radio list="peopleTitleList" listKey="peopleTitleId" listValue="peopleTitleName" name="user.peopletitle.peopleTitleId" cssClass="validate[required] radio"></s:radio>
	      </span>
	    </td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="NICK_NAME"/></strong></td>
	    <td align="left"><s:textfield id="nickname" name="user.nickName" cssClass="validate[required] text-input"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="FIRST_NAME"/></strong></td>
	    <td align="left"><s:textfield id="firstname" name="user.firstName" cssClass="validate[required] text-input"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="LAST_NAME"/></strong></td>
	    <td align="left"><s:textfield id="lastname" name="user.lastName" cssClass="validate[required] text-input"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="EMAIL"/></strong></td>
	    <td align="left"><s:textfield id="email" name="user.email" cssClass="validate[required,custom[email]] text-input"/></td>
	  </tr>
	  <tr>
        <td align="right"><strong>*<s:text name="BIRTHDAY"/></strong></td>
        <td align="left">
        	<s:textfield name="birthday" cssClass="validate[required,custom[date]]">
        		<s:param name="value"><s:date name="user.birthday" format="yyyy-MM-dd"/></s:param>
        	</s:textfield>
        </td>
      </tr>
      <tr>
		<td align="right">&nbsp;</td>
	    <td align="left">("yyyy-MM-dd")</td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="CELLPHONE"/></strong></td>
	    <td align="left"><s:textfield name="user.cellphone"/></td>
	  </tr>
	  <tr>
		<td align="right">&nbsp;</td>
	    <td align="left">("+<s:text name="COUNTRY_CODE"/>" + "<s:text name="CELLPHONE"/> <s:text name="NUMBER"/>")</td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="TELEPHONE"/></strong></td>
	    <td align="left"><s:textfield name="user.telephone"/></td>
	  </tr>
	  <tr>
		<td align="right">&nbsp;</td>
	    <td align="left">("+<s:text name="COUNTRY_CODE"/>" + "<s:text name="AREA_CODE"/>" + "<s:text name="TELEPHONE"/> <s:text name="NUMBER"/>")</td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="FAX"/></strong></td>
	    <td align="left"><s:textfield name="user.fax"/></td>
	  </tr>
	  <tr>
		<td align="right">&nbsp;</td>
	    <td align="left">("+<s:text name="COUNTRY_CODE"/>" + "<s:text name="AREA_CODE"/>" + "<s:text name="FAX"/> <s:text name="NUMBER"/>")</td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="ADDRESS"/></strong></td>
	    <td align="left"><s:textfield name="user.address"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="ZIP_CODE"/></strong></td>
	    <td align="left"><s:textfield name="user.zipcode"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="PREFERRED_LOCATION"/></strong></td>
	    <td align="left">
			<s:select list="locationList" listKey="courseLocationId" listValue="courseLocationName" name="user.courselocation.courseLocationId"></s:select>
		</td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="PREFERRED_LANGUAGE"/></strong></td>
	    <td align="left">
			<s:select list="#{'en':'English','ja':'日文','zh':'中文'}" listKey="key" listValue="value" name="user.preferredLanguage"></s:select>
		</td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="ALLERGIC"/></strong></td>
	    <td align="left"><s:textfield name="user.allergic"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="VEGETARIAN"/></strong></td>
	    <td align="left"><s:checkbox name="user.isVegetarian" fieldValue="true"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="NEED_COUPON"/></strong></td>
	    <td align="left"><s:checkbox name="user.needCoupon" fieldValue="true"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong><s:text name="NEED_NEWS"/></strong></td>
	    <td align="left"><s:checkbox name="user.needNews" fieldValue="true"/></td>
	  </tr>
	  <tr>
        <td colspan="2" align="left"><span class="color_red"><s:property value="informationResult"/></span></td>
      </tr>
	  <tr align="left">
	    <td colspan="2" align="right" class="color_red">* <s:text name="INDICATES_REQUIRED_FIELD"/></td>
	  </tr>
	  <tr align="center">
	    <td colspan="2">
	      <input type="submit" value="<s:text name="SAVE"/>" />
	      <input type="reset" value="<s:text name="RESET"/>" />
	    </td>
	  </tr>
	</table>
	</s:form>

    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>