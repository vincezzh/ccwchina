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
<title><s:text name="FAQS"/></title>
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

<script type="text/javascript">
var colorIdLeft = "left_faqs";
$(document).ready(function() {
	$("#leftContent").html("faqs");
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	$(".dialog").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});
});
function openDialog(did) {
	$("#" + did).dialog("open");
}
</script>
</head>

<body>
<s:include value="/top/toptitle.html"></s:include>

<div class="center both" style="margin-bottom:12px;">
	<s:include value="/WEB-INF/jsp/common/left.jsp"></s:include>
	
  	<div class="right content">

		<div class="faq">
        	<ul class="faq_ul">
            	<li class="faq_li_1">
                	<div class="color_orange margin_b_15 font_samll_3 margin_l_16">join the cooking course</div>
                    <ul>
                    	<li class="faq_li_2 font_samll_2 color_gray">
                    		<div class="hand" onclick="openDialog('dialog1')">where can I get your cooking schedule?</div>
                    		<div id="dialog1" class="dialog" title="where can I get your cooking schedule?">CCW: You can visit our website and click the ‘calendar’ and you will find the monthly cooking schedule updated. We have two kitchens in shanghai, one is in puxi and the other is in pudong. So you could choose different locations to view the cooking schedule. Feel free to book online.</div>
                    	</li>
                        <li class="faq_li_2 font_samll_2 color_gray">
                        	<div class="hand" onclick="openDialog('dialog2')">How much per person per lesson?</div>
                        	<div id="dialog2" class="dialog" title="How much per person per lesson?">CCW: For scheduled group lesson, wok and dim sum, we charge RMB 150 per person per lesson. For wet market tour and dining with the chef program in our schedule, we charge RMB 200 per person per lesson. If you want to book our kitchens for private cooking lessons, RMB 500 for 1-3 guests. RMB 150 per person extra if you’ve got more 3 guests per group.</div>
                        </li>
                        <li class="faq_li_2 font_samll_2 color_gray">
                        	<div class="hand" onclick="openDialog('dialog3')">I want to book private course, how do I do that?</div>
                        	<div id="dialog3" class="dialog" title="I want to book private course, how do I do that?">CCW: Please send us an email with the following information: date, time, number of the guests, preferred course ( dim sum  / work / dining with the chef / wet market tour / casual cooking party ) and the recipes you are interested in learning. E-mail address: <a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a> and we will get back to you asap. Price for private cooking lesson, RMB 150-200 per person per lesson. It depends on the details. You could also rent our kitchen and ask our chef to cook for your group. We could also send our kitchen team at your place to complete the cooking event with you. We open for your ideas.</div>
                        </li>
                        <li class="faq_li_2 font_samll_2 color_gray">
                        	<div class="hand" onclick="openDialog('dialog4')">Why there is no confirmation when I booked the course online?</div>
                        	<div id="dialog4" class="dialog" title="Why there is no confirmation when I booked the course online?"></div>
                        </li>
                        <li class="faq_li_2 font_samll_2 color_gray">
                        	<div class="hand" onclick="openDialog('dialog5')">Do you offer gift certificate?</div>
                        	<div id="dialog5" class="dialog" title="Do you offer gift certificate?">CCW: Yes, we do offer gift certificate. You could buy our cooking classes for your family and friends and you could complete the payment in the kitchen and we will print out the gift certificate for you. We could print your wishes on the certificate for you. Feel free to send the details to us via email <a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a> Please note the certificate you will buy for your family and friends will be valid for 6 months.</div>
                        </li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog6')">If I want to cancel the booking, how soon shall I tell you guys?</div>
							<div id="dialog6" class="dialog" title="If I want to cancel the booking, how soon shall I tell you guys?">CCW: Three days in advance at least, we appreciate early cancellation. That way, our booking team could update the availabilities to the other guests who are asking to join the cooking course.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog7')">I want to book the cooking course for my kids, birthday party. How many guests can I plan for this cooking party?</div>
							<div id="dialog7" class="dialog" title="I want to book the cooking course for my kids, birthday party. How many guests can I plan for this cooking party?">CCW: For both kitchens, we can host max. 30 guests, 20 for sitting down. So feel free to organize your kids birthday party, your team building party, welcoming party and other parties ideas. Feel free to send your preferred menu to us. And we could help with most fun interactive cooking party ever.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog8')">I have no experience of chinese cooking. Can I still join your cooking course?</div>
							<div id="dialog8" class="dialog" title="I have no experience of chinese cooking. Can I still join your cooking course?">CCW: Yes, most of the group scheduled wok and dim sum course are beginners-friendly. And each lesson starts from making the dough to complete the dim sum. All within 2 hours.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog9')">Your online booking doesn't work. Is there any other way I could book your course?</div>
							<div id="dialog9" class="dialog" title="Your online booking doesn't work. Is there any other way I could book your course?">CCW：Yes, you could send your requests to <a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a> or call Aga 86 1370 1873 243.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog10')">Do you do private cooking class? and I want to have the private cooking class at my home with my friends. Can you guys com over?</div>
							<div id="dialog10" class="dialog" title="Do you do private cooking class? and I want to have the private cooking class at my home with my friends. Can you guys com over?">CCW：Yes, we do. And we can send our kitchen team to your place. Just let us know the date and the address in advance. We can work on your schedule and budget.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog11')">Do you do catering? I have a home party of 20 guests. I don't think that I could cook for 20 people.</div>
							<div id="dialog11" class="dialog" title="Do you do catering? I have a home party of 20 guests. I don't think that I could cook for 20 people.">CCW：Yes, we do catering but more focus on bringing cooking to your kitchen serving your guests. Due to the busy teaching schedule, catering needs to be booked at least two weeks in advance.</div>
						</li>
                    </ul>
                </li>
                <li class="faq_li_1">
                	<div class="color_red margin_b_15 font_samll_3 margin_l_16">collaborate with ccw</div>
                     <ul>
                    	<li class="faq_li_2 font_samll_2 color_gray">
                    		<div class="hand" onclick="openDialog('dialog12')">My company is selling gift box to our customers. Can we have your cooking course in our gift box?</div>
                    		<div id="dialog12" class="dialog" title="My company is selling gift box to our customers. Can we have your cooking course in our gift box?">CCW: Sure. Please send us the details you have that you want to discuss with us. E-mail <a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a> and our manager will get back to you asap.</div>
                    	</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog13')">My company is selling organic vegetable. Can we work together?</div>
							<div id="dialog13" class="dialog" title="My company is selling organic vegetable. Can we work together?">CCW: Yes, we could work as your marketing partner and you could 1) buy our cooking lessons as gift to reward your guests; 2) book our kithen for your own VIP cooking lessons, we could ask our cooking team to use your vegetable to complete the cooking lessons. It’s an amazing way to host your VIP thanks events in our kitchen. Our pudong kitchen is located in the shopping mall, over the weekend, there are much more traffic so it will bring you new customers. For more details, please send email to <a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a> Aga, the founder will contact you.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog14')">We are a food and wine exhibition company and can we invite ccw to be the cooking guests?</div>
							<div id="dialog14" class="dialog" title="We are a food and wine exhibition company and can we invite ccw to be the cooking guests?">CCW: We’d love to. In the past eight years, we were invited by Salon du Chocolate, Cuisine Festival, China International Trade Fair for Household Products and Accessories by messe Frankfurt etc. and much more local charity fairs and events organized by the international schools and expats orgazations. We could offer live cooking demonstration, cooking class vouchers and sponsor some of the cooking courses and we could also work on your budget to make your events more fun and interactive. Feel free to email us: <a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a></div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog15')">I own a cooking school in Japan and I would like to send my students to your school for summer program. How can we work out this?</div>
							<div id="dialog15" class="dialog" title="I own a cooking school in Japan and I would like to send my students to your school for summer program. How can we work out this?">CCW: Welcome. We receive professional visiting group from all over the world, it could be a small group like 2 people, restaurant owners or it could be 18-35 people in the group, like what you said, the chef-to-be group visiting China to learn some authentic cooking. For your students, we will design the course, 3-day / 5-day or even longer to work around your schedule and budget. We also help with the airport pick up service and hotel booking to make your life easier in Shanghai. Just let us know.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog16')">I'm the editor of the cooking program from TV station. Can you help us with one of the show?</div>
							<div id="dialog16" class="dialog" title="I'm the editor of the cooking program from TV station. Can you help us with one of the show?">CCW: Yes, we used to help food related companies to do commercial TV program, designing recipes to help the new product launch, building networks between media friends and F&B industry etc. So let us know the needs and the schedule, our chefs can help. If you are looking for renting the kitchen for shooting, the PUDONG kitchen of the Chinese Cooking Workshop is ready for renting, feel free to visit the kitchen at your convenience.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog17')">I'm the manager of the adagency. I've got a client who is selling cooking paper and other cooking tools. Can you guys help us to complete the cooking part of the ad campaign?</div>
							<div id="dialog17" class="dialog" title="I'm the manager of the adagency. I've got a client who is selling cooking paper and other cooking tools. Can you guys help us to complete the cooking part of the ad campaign?">CCW: Yes, we can. On the top of the list, we could help with the recipes development, cooking demo. Or you could send some free give away products; we could share with our students when they finish the cooking lessons with us. We’ve got 2 kitchens in Shanghai, open everyday and both kitchens are busy from mornings to afternoons, so you will see lots of target customers to your products. Feel free to schedule meetings with us to discuss all the possibilities to work together serving your clients better.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog18')">I want to produce chinese cooking video and sell it through my networking. Can you help us?</div>
							<div id="dialog18" class="dialog" title="I want to produce chinese cooking video and sell it through my networking. Can you help us?">CCW: Yes, let us know the contents, the recipes and your budget. We can work it out.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog19')">I want to invite you guys design the lunch menu for my school. Are you interested?</div>
							<div id="dialog19" class="dialog" title="I want to invite you guys design the lunch menu for my school. Are you interested?">CCW: Yes, CCW team is offering cooking courses to Fudan University, JiaoTong University, university of shanghai for science & technology, Shanghai American School, French and German School etc. So we’ve got lots of experience to understand what the students like and dislike. We could help with the Chinese wok menu, Chinese dim sum menu, and other Asia recipes. Let’s schedule a meeting and discuss the details.</div>
						</li>
                    </ul>
                </li>
                <li class="faq_li_1">
                	<div class="color_purple margin_b_15 font_samll_3 margin_l_16">Organize the cooking events</div>
                     <ul>
                    	<li class="faq_li_2 font_samll_2 color_gray">
                    		<div class="hand" onclick="openDialog('dialog20')">I'm HR manager. We want to book your kitchen and cooking program for our new employee welcome party. Can you help us?</div>
                    		<div id="dialog20" class="dialog" title="I'm HR manager. We want to book your kitchen and cooking program for our new employee welcome party. Can you help us?">CCW: Yes, we have a cooking program called ‘dining with the chef’, it’s very popular among the friends, family and companies, it’s 2-3 hours long, mostly scheduled in the night after work, we will prepare a set menu: cold dishes, hot dishes and dim sum for your group. There are some recipes we will prepare for the team to join learning and cooking. Feel free to bring drinks or ask our kitchen staff to buy for you. All we need from you is the date and the number of the guests coming. The price for this event is RMB 150-200 per person. The max. number of the guests is no more than 35 guests for both kitchens. If you have your own venue, we could also do the cooking events at your place, there will be some extra cost for setting up the temporary kitchen at your place. Let’s talk about the details when we meet.</div>
                    	</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog21')">We are relocation company and we want to organize cooking events for our customers. Can we do that?</div>
							<div id="dialog21" class="dialog" title="We are relocation company and we want to organize cooking events for our customers. Can we do that?">CCW: Yes, we offer some promotion discount for your guests, and we could also print some vouchers for you to put into the welcome package. Thanks so much for choosing us, Chinese Cooking Workshop, ‘one of the best cooking schools in the world’ and also recommended by the New York Times and The Lonely Planet – China ( newest edition in 2011).</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog22')">I'm the customer manager of the serviced apartment in Xin Tian Di. Can you guys come over to our club house to do the cooking contact course to our guests?</div>
							<div id="dialog22" class="dialog" title="I'm the customer manager of the serviced apartment in Xin Tian Di. Can you guys come over to our club house to do the cooking contact course to our guests?">CCW: Yes, in the past 8 years, we’ve been moving around in different club houses in the city, from Xu Jia Hui to Xin Tian Di, from Jin Qiao to Hong Qiao. We do monthly or bi-weekly cooking events in the apartment and villa’s club house, bringing the cooking classes near to our guests, offering convenience and always the fun, interactive cooking experience. We also have the yearly cooking program for your reference. Feel free to email us: booking@chinesecookingworkshop.com</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog23')">I'm an English teacher and outreach events organizer for my school. I have a 20-students interested into your cooking class. Can you make me a proposal to that?</div>
							<div id="dialog23" class="dialog" title="I'm an English teacher and outreach events organizer for my school. I have a 20-students interested into your cooking class. Can you make me a proposal to that?">CCW: Yes, we have ‘young chef workshop’ ready for your team. We help teens to get ready for the independent campus lives back home, there are 4 lessons in the course, and each lesson is 3 hours long. If you only want to do one time, we could tailor the course for your team.CCW: Yes, we have ‘young chef workshop’ ready for your team. We help teens to get ready for the independent campus lives back home, there are 4 lessons in the course, and each lesson is 3 hours long. If you only want to do one time, we could tailor the course for your team.</div>
						</li>
						<li class="faq_li_2 font_samll_2 color_gray">
							<div class="hand" onclick="openDialog('dialog24')">I'm a personal tour guide in Shanghai and some of my clients would like to do cooking class. Can I send them over to your school?</div>
							<div id="dialog24" class="dialog" title="I'm a personal tour guide in Shanghai and some of my clients would like to do cooking class. Can I send them over to your school?">CCW: Yes, you can. In this case, you can call Aga 1370 1873 243 or email to booking@chinesecookingworkshop.com to let us know the date and the number of the guests, we could schedule the private course for your clients. Both wok and dim sum will be good options for your clients who are traveling to Shanghai. If they have more time in the morning, we could offer the wet market tour ( 1 hour ) + cooking lessons in the kitchen ( wok or dim sum ).</div>
						</li>
                    </ul>
                </li>
                <br class="both" />
            </ul>

            
            <div class="font_samll_2 color_gray">Not feel like your questions answered?</div>
            <div class="font_samll_2 color_gray">Please send your questions / requests / ideas / feedback to:</div>
            <div class="font_samll_2 color_blue"><a href="mailto:booking@chinesecookingworkshop.com">booking@chinesecookingworkshop.com</a></div>
        </div>

    </div>
    <br class="both" />
</div>

<s:include value="/bottom/bottomfoot.html"></s:include>

</body>
</html>