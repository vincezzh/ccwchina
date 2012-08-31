<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="CLASS_EXPIRED"/></title>

<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript">
function closeWindow() {
	window.opener = null; 
	window.close();
}
</script>
</head>
<body>
<div class="user_not_existed_bg">
	<div><s:text name="DEAR"/>, User Id "<s:property value="user.userId"/>" or password is not correct.</div>
	<div class="hand color_red" onclick="closeWindow()"><s:text name="CLOSE"/></div>
	<div style="margin:100px 0 0 100px;"><img src="/images/emotion/biaoqing_005.png.png"/></div>
</div>
</body>
</html>