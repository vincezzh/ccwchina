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
<title><s:text name="SIGN_IN_AND_SIGN_UP"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<link rel="stylesheet" href="/js_base/theme/jquery.ui.all.css"> 
<script src="/js_base/ui/jquery.ui.core.js"></script> 
<script src="/js_base/ui/jquery.ui.widget.js"></script> 
<script src="/js_base/ui/jquery.ui.mouse.js"></script> 
<script src="/js_base/ui/jquery.ui.draggable.js"></script> 
<script src="/js_base/ui/jquery.ui.position.js"></script> 
<script src="/js_base/ui/jquery.ui.resizable.js"></script> 
<script src="/js_base/ui/jquery.ui.dialog.js"></script>

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
	$("#form_submit").attr("disabled", true);
    jQuery("#form2").validationEngine();
    jQuery("#form3").validationEngine();
});
</script>

<script type="text/javascript">
var checkedUserId = false;
function chkUser(field, rules, i, options) {
	jQuery.ajax({
	   type: "POST",
	   url: "/user/check-userId.htm",
	   data: "username=" + field.val(),
	   success: function(content) {
		   	if(content == "passed") {
		   		$("#form_submit").attr("disabled", false);
		   		checkedUserId = true;
		   	}else {
		   		$("#form_submit").attr("disabled", true);
		   		if(!checkedUserId) {
		   			$('#userId').validationEngine('showPrompt', '* This userId is already taken', 'error', 'topRight', true);
		   		}
		   		checkedUserId = false;
		   	}
		}
	});
}
</script>

<script type="text/javascript">
var colorIdCenter = null;
</script>

<script type="text/javascript">
jQuery(document).ready(function() {
$("#forget-password-dialog").dialog({
	autoOpen: false,
	height: 100,
	width: 520,
	modal: true
});

<s:if test="retrievePasswordResult != null && retrievePasswordResult != ''">
	openDialog();
</s:if>
});

function openDialog() {
	$("#forget-password-dialog").dialog("open");
}
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both">
	<s:include value="/WEB-INF/jsp/common/center-title.jsp"></s:include>
</div>

<div class="center both" style="margin-bottom:12px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top">
        <s:form id="form1" name="form1" method="post" action="login" namespace="/user">
        <table width="100%" border="0" cellspacing="3" cellpadding="3" class="font_samll_2">
          <tr align="center">
            <td colspan="2" class="color_green"><s:text name="SIGN_IN"/></td>
          </tr>
          <tr align="center">
            <td colspan="2"><p><strong><s:text name="WELCOME_BACK"/></strong></p></td>
          </tr>
          <tr>
            <td width="100" align="right"><s:text name="USERNAME"/></td>
            <td align="left"><s:textfield name="username"/></td>
          </tr>

          <tr>
            <td align="right"><s:text name="PASSWORD"/></td>
            <td align="left"><s:password name="password"/></td>
          </tr>
          <tr>
            <td colspan="2" align="right"><span class="color_red"><s:actionerror/></span></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input type="submit" value="<s:text name="SIGN_IN"/>" />
            </td>
          </tr>
       </table>
        </s:form>
        <table width="100%" border="0" cellspacing="3" cellpadding="3" class="font_samll_2">
        <tr>
          	<td colspan="2" align="right">
          		<div class="hand" onclick="openDialog()"><s:text name="FORGOT_PASSWORD"/>?</div>
          		<div id="forget-password-dialog" title="<s:text name="RETRIEVE_PASSWORD"/>"> 
					<s:form id="form3" name="form3" action="retrieve-password" namespace="/user" method="post">
						<div><s:text name="USERNAME"/>: 
						<s:textfield name="username" cssClass="validate[required] text-input"></s:textfield>
						<input type="submit" value="<s:text name="RETRIEVE_PASSWORD"/>" />
						</div>
						<div class="color_red"><s:property value="retrievePasswordResult"/></div>
					</s:form>
				</div>
          	</td>
          </tr>
          </table>
        </td>
        
        
        <td valign="top">
        <s:form id="form2" name="form2" method="post" action="register" namespace="/user">
        <table width="100%" border="0" cellspacing="3" cellpadding="3" class="font_samll_2 border_left">
          <tr align="center">
            <td colspan="2" class="color_green"><s:text name="SIGN_UP"/></td>
          </tr>
          <tr align="center">
            <td colspan="2"><p><strong><s:text name="JOIN_THE_CCW_COMMUNITY"/></strong></p></td>
          </tr>
          <tr>
            <td width="150" align="right">*<s:text name="USERNAME"/></td>
            <td align="left">
            	<s:textfield id="userId" name="user.userId" cssClass="validate[required,custom[onlyLetterNumber],minSize[6],maxSize[20],funcCall[chkUser]] text-input"></s:textfield>
            	<span id="userId_error"></span>
            </td>
          </tr>
          <tr>
            <td align="right">*<s:text name="PASSWORD"/></td>
            <td align="left"><s:password id="password" name="user.password" cssClass="validate[required],minSize[6],maxSize[20] text-input"></s:password></td>
          </tr>
          <tr>
            <td align="right">*<s:text name="CONFIRM_PASSWORD"/></td>
            <td align="left"><input type="password" name="passwordConfirm" id="passwordConfirm" value="" class="validate[required,equals[password]] text-input"/></td>
          </tr>
          <tr>
            <td width="150" align="right">*<s:text name="EMAIL"/></td>
            <td align="left"><s:textfield id="email" name="user.email" cssClass="validate[required,custom[email]] text-input"></s:textfield></td>
          </tr>
          <tr>
            <td width="150" align="right">*<s:text name="BIRTHDAY"/></td>
            <td align="left"><s:textfield id="birthday" name="birthday" cssClass="validate[required,custom[date]]" value="2011-01-01"></s:textfield></td>
          </tr>
          <tr>
            <td align="right">*<s:text name="PREFERRED_LOCATION"/></td>
            <td align="left">
            	<s:select list="locationList" listKey="courseLocationId" listValue="courseLocationName" name="user.courselocation.courseLocationId"></s:select>
            </td>
          </tr>
          <tr>
            <td align="right">*<s:text name="PREFERRED_LANGUAGE"/></td>
            <td align="left">
            	<s:select list="#{'en':'English','ja':'日文','zh':'中文'}" listKey="key" listValue="value" name="user.preferredLanguage"></s:select>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="right" class="color_red">
              <span class="color_red">* <s:text name="INDICATES_REQUIRED_FIELD"/></span>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input type="submit" id="form_submit" value="<s:text name="SIGN_UP_FOR_CCW"/>"/>
            </td>
          </tr>
        </table>
        </s:form>
        </td>
    </tr>
    </table>
	<br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>