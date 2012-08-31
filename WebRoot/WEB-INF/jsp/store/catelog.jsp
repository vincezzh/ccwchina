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
<title><s:text name="CCW_MARKET"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />
<link rel="stylesheet" href="/plugin/quicksand/quicksand.css">
<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<script type="text/javascript" src="/plugin/quicksand/jquery.quicksand.js"></script>
<script type="text/javascript" src="/plugin/quicksand/jquery.easing.js"></script>
<script type="text/javascript" src="/plugin/quicksand/script.js"></script>
<script type="text/javascript">
var colorIdCenter = "center_market";
</script>
</head>
<body>
<s:include value="/top/toptitle.html"></s:include>
<div class="center both">
	<s:include value="/WEB-INF/jsp/common/center-title.jsp"></s:include>
</div>
<div id="item-catelog">
	<div id="site">
		<div class="shop_title color_green"><s:text name="CCW_MARKET"/></div>
		<div id="filter">
			<fieldset>
				<legend class="color_blue"><s:text name="FILTER_BY_TYPE"/></legend>
				<label><input type="radio" name="type" value="all" checked="checked"><s:text name="ALL"/></label>
				<s:iterator value="trunkList">
					<label><input type="radio" name="type" value="<s:property value="itemTrunkTypeId"/>"><s:property value="%{getText(itemTrunkTypeNameKey)}"/></label>
				</s:iterator>
			</fieldset>
			<fieldset>
				<legend class="color_red"><s:text name="SORT_BY"/></legend>
				<label><input type="radio" name="sort" value="name" checked="checked"><s:text name="NAME"/></label>
				<label><input type="radio" name="sort" value="size"><s:text name="PRICE"/></label>
			</fieldset>
		</div>
		
		<div style="margin-top:20px;">
			<ul id="applications" class="image-grid">
				<s:iterator value="itemList" status="st">
					<li data-id="id-<s:property value="#st.index+1"/>" data-type="<s:property value="itemtrunktype.itemTrunkTypeId"/>">
						<a href="/<s:property value="itemContext"/>/<s:property value="itemtrunktype.itemTrunkTypePath"/>/<s:property value="itemId"/>.html">
							<img src="<s:property value="pictureOne"/>" alt="<s:property value="itemNameEn"/>" width="128" height="128" />
						</a>
						<s:if test="language == 'ja'">
							<strong><s:property value="itemNameJp"/></strong> 
						</s:if>
						<s:elseif test="language == 'zh'">
							<strong><s:property value="itemNameCn"/></strong> 
						</s:elseif>
						<s:else>
							<strong><s:property value="itemNameEn"/></strong> 
						</s:else>
						￥<span data-type="size"><s:property value="price"/></span>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>
<s:include value="/bottom/bottomfoot.html"></s:include>
</body>
</html>