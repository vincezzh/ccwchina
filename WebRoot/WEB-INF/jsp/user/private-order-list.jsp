<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="PRIVATE_ORDER_LIST"/></title>
<link rel="stylesheet" type="text/css" href="/css/csss.css" />
<link rel="stylesheet" type="text/css" href="/css/top_nav.css" />
<link rel="stylesheet" type="text/css" href="/css/csss_plus.css" />

<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<link type="text/css" href="/js_base/theme/jquery.ui.all.css" rel="stylesheet" />
<script type="text/javascript" src="/js_base/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="/js_basey/ui/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.effects.core.js"></script>
<script type="text/javascript" src="/js_base/ui/jquery.effects.fold.js"></script>
<script type="text/javascript">
function openDialog(dId) {
	$.get("/user/private-order-detail.htm", { id: dId }, function(data) {
		$('#'+dId).html(data);
	});
	$('#'+dId).dialog('open');
}

$(function() {
	<s:iterator value="privateOrderList">
		$('#<s:property value="orderPrivateId"/>').dialog({
			autoOpen: false,
			show: 'fold',
			hide: 'fold',
			resizable: false,
			modal: true,
			width: 500,
			height: 400
		});
	</s:iterator>
});
</script>

<script src="/plugin/uniform/jquery.uniform.js" type="text/javascript"></script>
<link rel="stylesheet" href="/plugin/uniform/css/uniform.default.css" type="text/css" media="screen" charset="utf-8" />
<style>
div.selector {
  width: 40px;
  font-size: 12px;
}
div.selector select {
  min-width: 40px;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 12px;
  border: solid 1px #fff;
}
div.selector span {
  color: #666;
  width: 8px;
  text-shadow: 0 1px 0 #fff;
}
</style>
<script type="text/javascript" charset="utf-8">
$(function(){
	$("select").uniform();
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
	        
<div>
	<span><a href="<s:url action="public-orders" namespace="/user"/>"><s:text name="GROUP_COOKING_LESSONS"/></a></span> | 
	<span class="color_green bold"><s:text name="PRIVATE_COOKING_LESSONS"/></span> | 
	<span><a href="<s:url action="item-orders" namespace="/user"/>"><s:text name="ITEMS"/></a></span>
</div>
<p/>
<table cellspacing="0" cellpadding="2" width="100%" class="font_samll_2 order_table">
    <tr class="background_green bold">
    	<td colspan="8" align="center"><span><s:text name="PRIVATE_COOKING_LESSONS"/></span></td>
    </tr>
    <tr align="left">
        <td width="22%"><strong><s:text name="NO."/></strong></td>
        <td width="12%"><strong><s:text name="DATE"/></strong></td>
        <td width="13%"><strong><s:text name="TIME"/></strong></td>
        <td width="18%"><strong><s:text name="LOCATION"/></strong></td>
        <td width="5%"><strong><s:text name="NO._OF_GUESTS"/></strong></td>
        <td width="20%"><strong><s:text name="BOOKING_TIME"/></strong></td>
        <td width="10%"><strong><s:text name="STATUS"/></strong></td>
    </tr>
    
    <s:iterator value="privateOrderList" status="s">
    	<tr class="<s:if test="#s.odd==true">background_green</s:if><s:else>background_white</s:else>">
	    	<td>
	    		<a class="hand" onclick="openDialog('<s:property value="orderPrivateId"/>')"><strong><s:property value="orderPrivateId"/></strong></a>
	    		<div id="<s:property value="orderPrivateId"/>" title="<s:property value="orderPrivateId"/>">
	            </div>
	    	</td>
	    	<td><s:date name="courseDate" format="yyyy-MM-dd"/></td>
	    	<td><s:property value="classtime.classTimeContent"/></td>
	    	<td><s:property value="courselocation.courseLocationName"/></td>
	    	<td><s:property value="totalPeopleNumber"/></td>
	    	<td><s:date name="orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	    	<td><s:property value="%{getText(orderbasic.orderstatus.orderStatusName)}"/></td>
	    </tr>
    </s:iterator>
    
    <tr>
    	<td colspan="4" align="left">
    		<s:if test="privateOrderList.size() != 0">
    			<s:include value="../common/pages.jsp"></s:include>
    		</s:if>
    	</td>
        <td colspan="4" align="right">
        	<a href="<s:url action="private-orders-history" namespace="/user"/>"><s:text name="VIEW_MY_COOKING_HISTORY"/></a>
        </td>
    </tr>
</table>

<br class="both" />
</div>


<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>