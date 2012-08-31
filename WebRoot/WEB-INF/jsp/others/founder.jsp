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
<title><s:text name="FOUNDER"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />
<style>
p {
	font-size: 12px;
	margin-bottom: 10px;
}
td {
	font-size: 12px;
	color: #666;
	padding-bottom: 10px;
}
.picture_bottom {
	font-size: 10px;
}
</style>

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript">
var colorIdLeft = "left_founder";
$(document).ready(function() {
	$("#leftContent").html("founder");
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
									<s:text name="FOUNDER_DESC1"/>
								</td>
							</tr>
							<tr>
								<td>
									<s:text name="FOUNDER_DESC2"/>
									<a href="mailto:aga.sun@chinesecookingworkshop.com">aga.sun@chinesecookingworkshop.com</a>
								</td>
							</tr>
							<tr>
								<td>
									<s:text name="FOUNDER_DESC3"/>
								</td>
							</tr>
							<tr>
								<td>
									<s:text name="FOUNDER_DESC4"/>
									<s:text name="FOUNDER_DESC5"/> 
									<a href="mailto:newsletter@chinesecookingworkshop.com">newsletter@chinesecookingworkshop.com</a>  
									<s:text name="FOUNDER_DESC6"/>
								</td>
							</tr>
						</tbody>
					</table>
					</td>
					<td width="25">&nbsp;</td>
					<td align="center" valign="top" width="310">
						<div style="overflow: hidden; width: 310px;">
							<img src="/images/common/founder.jpg" alt="Founder" />
						</div>
						<div class="picture_bottom">
							<s:text name="FOUNDER_DESC7"/>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		<p><br></p>
		<hr/>
		<p>
			<s:text name="FOUNDER_DESC8"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC9"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC10"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC11"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC12"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC13"/> 
		</p>
		<p>
			<s:text name="FOUNDER_DESC14"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC15"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC16"/>
		</p>
		<p>
			<s:text name="FOUNDER_DESC17"/>
			<img src="/images/common/founder_signiture.jpg" alt="Founder Signiture"/>
		</p>
		<!-- One founder end -->

    </div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>