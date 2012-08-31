lastScrollY=0;
function heartBeat(){ 
var diffY;
if (document.documentElement && document.documentElement.scrollTop)
    diffY = document.documentElement.scrollTop;
else if (document.body)
    diffY = document.body.scrollTop
else
    {/*Netscape stuff*/}

percent=.1*(diffY-lastScrollY); 
if(percent>0)percent=Math.ceil(percent); 
else percent=Math.floor(percent); 
document.getElementById("full").style.top=parseInt(document.getElementById("full").style.top)+percent+"px";

lastScrollY=lastScrollY+percent; 
}
suspendcode="<div id=\"full\" style='right:2px; top:170px; position:absolute;'>" +
		"<table width='20' border='0' cellpadding='0' cellspacing='0'>" +
		"<tr><td align='center'><a href='javascript:window.scrollTo(0,0);' onfocus='blur()'><img border='0' src='/images/toolsbar/icon_top.gif' title='top' /></a></td></tr>" +
		"<tr><td align='center'><a href='mailto:vincezzh@chinesecookingworkshop.com' onfocus='blur()'><img border='0' src='/images/toolsbar/mail.png' title='Report Bugs' /></a></td></tr>" +
		"<tr><td align='center'><a href='/others/guide.htm'><img src='/images/toolsbar/big.png' border='0' title='User Guide' /></a></td>" +
		"<tr><td align='center'><a href='/store/item-list.htm'><img src='/images/toolsbar/cart.png' border='0' title='CCW Market' /></a></td>" +
		"<tr><td align='center'><a href='tencent://message/?uin=16653960&Menu=yes' onfocus='blur()'><img src='/images/toolsbar/icon_oicq.gif' border='0' title='QQ me' /></a></td>" +
		"<tr><td align='center'><a href='javascript: void(window.open(\"http://www.facebook.com/share.php?u=\".concat(encodeURIComponent(location.href)) ));'><img src='/images/common/facebookc1.png' title='Send to Facebook' alt='Push to Facebook' width='18'/></a></td>" +
		"<tr><td align='center'><a href='javascript: void(window.open(\"http://twitter.com/home/?status=\".concat(encodeURIComponent(document.title)) .concat(\" \") .concat(encodeURIComponent(location.href))));'><img src='/images/common/twitterc1.png' title='Push to Twitter' alt='Push to Twitter' height='18'/></a></td>" +
		"<tr><td align='center'><a href='javascript:window.scroll(0,99999)' onfocus='blur()'><img border='0' src='/images/toolsbar/icon_bottom.gif' title='foot' /></a></td></tr>" +
		"</table></div>"

document.write(suspendcode);
window.setInterval("heartBeat()",1);