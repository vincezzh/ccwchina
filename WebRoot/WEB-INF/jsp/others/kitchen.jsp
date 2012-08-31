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
<title><s:text name="KITCHEN"/></title>
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

<script type="text/javascript">
var colorIdLeft = "left_kitchen";
$(document).ready(function() {
	$("#leftContent").html("kitchen");
});
</script>

<link href="http://code.google.com/apis/maps/documentation/javascript/examples/standard.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript"
    src="http://maps.google.com/maps/api/js?sensor=false&language=en"></script> 
<script  type="text/javascript"> 
    function init() {
      var map1 = new google.maps.Map(document.getElementById("shanghai_puxi_kitchen"), {
        scaleControl: true});
      map1.setCenter(new google.maps.LatLng(31.20854,121.451177));
      map1.setZoom(16);
      map1.setMapTypeId(google.maps.MapTypeId.ROADMAP);
 
      var marker1 = new google.maps.Marker({map: map1, position:
        map1.getCenter()});
      var infowindow1 = new google.maps.InfoWindow();
      infowindow1.setContent('<div><p><b><s:text name="SHANGHAI_PUXI_ADDRESS1"/></b><br/><s:text name="SHANGHAI_PUXI_ADDRESS2"/></p></div>');
      google.maps.event.addListener(marker1, 'click', function() {
        infowindow1.open(map1, marker1);
      });
      infowindow1.open(map1, marker1);

      /*var map2 = new google.maps.Map(document.getElementById("suzhou_kitchen"), {
         scaleControl: true});
       map2.setCenter(new google.maps.LatLng(31.293169,120.556691));
       map2.setZoom(16);
       map2.setMapTypeId(google.maps.MapTypeId.ROADMAP);
  
       var marker2 = new google.maps.Marker({map: map2, position:
         map2.getCenter()});
       var infowindow2 = new google.maps.InfoWindow();
       infowindow2.setContent('<div><p><b><s:text name="SUZHOU_ADDRESS1"/></b><br/><s:text name="SUZHOU_ADDRESS2"/></p></div>');
       google.maps.event.addListener(marker2, 'click', function() {
         infowindow2.open(map2, marker2);
       });
       infowindow2.open(map2, marker2);*/

       var map3 = new google.maps.Map(document.getElementById("shanghai_pudong_kitchen"), {
           scaleControl: true});
         map3.setCenter(new google.maps.LatLng(31.256019,121.58055));
         map3.setZoom(16);
         map3.setMapTypeId(google.maps.MapTypeId.ROADMAP);
    
         var marker3 = new google.maps.Marker({map: map3, position:
           map3.getCenter()});
         var infowindow3 = new google.maps.InfoWindow();
         infowindow3.setContent('<div><p><b><s:text name="SHANGHAI_PUDONG_ADDRESS1"/></b><br/><s:text name="SHANGHAI_PUDONG_ADDRESS2"/></p></div>');
         google.maps.event.addListener(marker3, 'click', function() {
           infowindow3.open(map3, marker3);
         });
         infowindow3.open(map3, marker3);
    }
</script> 
</head>

<body onload="init()">
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>
	
  	<div class="right content">
		<div class="color_red" style="margin-bottom:20px;">
			Both kitchens open everyday, except the chinese new year holiday<br/>
			09:00-22:00, please email or call us to visit.<br/>

			thanks. looking forward to seeing you in the kitchens.<br/>
		</div>

		<div id="kitchen_1">
			<div><b>Chinese cooking workshop Shanghai Puxi Kitchen, Shanghai(中华料理教室上海浦西厨房)</b></div>
			<div>
				<a href="#"><input type="button" value="<s:text name="DOWNLOAD"/>"/></a>
			</div>
			<div id="shanghai_puxi_kitchen" style="width: 570px; height: 450px;"></div>
			<hr/>
		</div>
		
		<div id="kitchen_3">
			<div><b>Chinese cooking workshop Shanghai Pudong Kitchen, Shanghai(中华料理教室上海浦东厨房)</b></div>
			<div>
				<a href="#"><input type="button" value="<s:text name="DOWNLOAD"/>"/></a>
			</div>
			<div id="shanghai_pudong_kitchen" style="width: 570px; height: 450px;"></div>
			<hr/>
		</div>
    </div>
    <br class="both" />
</div>



<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>