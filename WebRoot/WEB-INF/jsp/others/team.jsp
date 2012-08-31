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
<title><s:text name="TEAM"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdLeft = "left_team";
$(document).ready(function() {
	$("#leftContent").html("team");
});
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>
	
  	<div class="right content">

		<!-- One founder -->
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td valign="top">
					<table border="0" cellpadding="3" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td>
									<h3 style="color:#b9980e;">Vince Zhang 张哲涵</h3>
								</td>
							</tr>
							<tr>
								<td><strong>Location: </strong>Shanghai, China</td>
							</tr>
							<tr>
								<td><strong>email: </strong><a href="mailto:vincezzh@chinesecookingworkshop.com">vincezzh@chinesecookingworkshop.com</a></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>
									<p>
										<strong>Self Description</strong>
									</p>
									<p>
										A pro software programmer / consultant and a journeyman software craftsman working in Shanghai China. In my spare time I am an active open source contributor and pragmatic agile advocate. 
									</p>
								</td>
							</tr>
						</tbody>
					</table>
					</td>
					<td align="center" valign="top" width="310">
						<div style="overflow: hidden; width: 310px;">
							<img src="/images/common/no_picture.gif" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- One founder end -->

    </div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>