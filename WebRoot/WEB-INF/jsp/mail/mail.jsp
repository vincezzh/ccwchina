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
<title><s:text name="MAIL_HEAD"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script src="/plugin/uniform/jquery.uniform.js" type="text/javascript"></script>
<link rel="stylesheet" href="/plugin/uniform/css/uniform.default.css" type="text/css" media="screen" charset="utf-8" />
<script type="text/javascript" charset="utf-8">
$(function(){
	$("input, button").uniform();
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
var colorIdCenter = null;
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both">
	<s:include value="/WEB-INF/jsp/common/center-title.jsp"></s:include>
</div>

<div class="center both" style="margin-bottom:12px;">
	        
	<s:form id="form1" name="form1" method="post" action="add-mail">
	 <table width="100%" border="0" cellspacing="3" cellpadding="0" class="font_samll_2 padding_right_5 line_h_20">
	  <tr align="center">
	    <td colspan="2" class="color_green"><strong><s:text name="MAIL_TITLE"/></strong></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="FIRST_NAME"/></strong></td>
	    <td align="left"><s:textfield id="firstname" name="mail.firstname" cssClass="validate[required] text-input"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="LAST_NAME"/></strong></td>
	    <td align="left"><s:textfield id="lastname" name="mail.lastname" cssClass="validate[required] text-input"/></td>
	  </tr>
	  <tr>
	    <td align="right"><strong>*<s:text name="EMAIL"/></strong></td>
	    <td align="left"><s:textfield id="email" name="mail.email" cssClass="validate[required,custom[email]] text-input"/></td>
	  </tr>
	  <tr align="left">
	    <td colspan="2" align="right" class="color_red">* <s:text name="INDICATES_REQUIRED_FIELD"/></td>
	  </tr>
	  <tr align="center">
	    <td colspan="2">
	      <input type="submit" value="<s:text name="SAVE"/>" />
	    </td>
	  </tr>
	  <tr>
        <td colspan="2" align="right"><span class="color_red"><s:property value="feedback"/></span></td>
      </tr>
	</table>
	</s:form>

    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>