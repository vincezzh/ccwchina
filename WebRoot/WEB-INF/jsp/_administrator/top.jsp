<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>top</title>
<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script>
<link href="/_administrator/skin/css/base.css" rel="stylesheet" type="text/css">
<style>
body { padding:0px; margin:0px; }
#tpa {float:right;padding-right:10px;font-size:12px;margin:0px;}
#tpa dd {float:left;margin-right:2px;}

#tpa dd.img {padding-top:6px;}
.item{text-align:center;height:26px;line-height:26px;width:90px}
.item a:link, .item a:visited {color: #fff;text-decoration: none;font-weight: bold;}

.itemsel {text-align:center;background:#226411;border-left:1px solid #c5f097;border-right:1px solid #c5f097;
border-top:1px solid #c5f097;height:26px;width:88px; line-height:26px;}
.itemsel a:link, .itemsel a:visited {color: #fff;text-decoration: none;font-weight: bold;}
.itemsel a:hover {font-weight: bold;}

</style>
<script language='javascript'>
var preFrameW = '206,*';
var FrameHide = 0;
var curStyle = 1;
var totalItem = 9;
var isRefreshing = false;
function ChangeMenu(way){
	var addwidth = 10;
	var fcol = top.document.all.btFrame.cols;
	if(way==1) addwidth = 10;
	else if(way==-1) addwidth = -10;
	else if(way==0){
		if(FrameHide == 0){
			preFrameW = top.document.all.btFrame.cols;
			top.document.all.btFrame.cols = '0,*';
			FrameHide = 1;
			return;
		}else{
			top.document.all.btFrame.cols = preFrameW;
			FrameHide = 0;
			return;
		}
	}
	fcols = fcol.split(',');
	fcols[0] = parseInt(fcols[0]) + addwidth;
	top.document.all.btFrame.cols = fcols[0]+',*';
}


function mv(selobj,moveout,itemnum)
{
   if(itemnum==curStyle) return false;
   if(moveout=='m') selobj.className = 'itemsel';
   if(moveout=='o') selobj.className = 'item';
   return true;
}

function changeSel(itemnum)
{
  curStyle = itemnum;
  for(i=1;i<=totalItem;i++)
  {
     if(document.getElementById('item'+i)) document.getElementById('item'+i).className='item';
  }
  document.getElementById('item'+itemnum).className='itemsel';
}

function refreshWebsite() {
	if(isRefreshing == false) {
		isRefreshing = true;
		$("#loading").html('<img height="12px" src="/_administrator/skin/images/loading.gif"/>');
		$.ajax({
			   type: "POST",
			   url: "<s:url action='refresh-website' namespace='/_administrator'/>",
			   success: function(content){
			   		$('#loading').html('');
				}
		});
		isRefreshing = false;
	}
}

function refreshDatabase() {
	if(isRefreshing == false) {
		isRefreshing = true;
		$("#loading").html('<img height="12px" src="/_administrator/skin/images/loading.gif"/>');
		$.ajax({
			   type: "POST",
			   url: "<s:url action='refresh-database' namespace='/_administrator'/>",
			   success: function(content){
			   		$('#loading').html('');
				}
		});
		isRefreshing = false;
	}
}
</script>
</head>
<body bgColor='#ffffff'>
<table width="100%" align="right" border="0" cellpadding="0" cellspacing="0" background="/_administrator/skin/images/frame/topbg.gif">
  <tr>
    <td width='10%' height="60"><img src="/_administrator/skin/images/frame/logo.gif" /></td>
    <td width='90%' align="right" valign="bottom">
      <table width="960px" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <td align="right" height="26" style="padding-right:10px;line-height:26px;">
        	您好：<span class="username"><s:property value="#session.admin_session.adminId"/></span>，欢迎使用内容管理系统！
        	<span id="loading" style="width:20px"></span>
        	[<a href="#" onclick="refreshWebsite()">数据更新</a>]
        	[<a href="/" target="_blank">网站主页</a>]
        	[<a href="<s:url action="user-modify" namespace="/_administrator"/>" target='mainFrame'>修改密码</a>]
        	[<a href="<s:url action="admin_logout" namespace="/_administrator"/>" target="_top">注销退出</a>]
      		<!-- [<a href="#" onclick="refreshDatabase()">移植</a>] -->
      </td>
      </tr>
      <tr>
        <td align="right" height="34">
		<dl id="tpa">
            <dd>
                <div class='itemsel' id='item1' onMouseMove="mv(this,'move',1);" onMouseOut="mv(this,'o',1);">
                    <a href="<s:url action="nav_menu" namespace="/_administrator"/>" onclick="changeSel(1)" target="leftFrame">常用操作</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item2' onMouseMove="mv(this,'m',2);" onMouseOut="mv(this,'o',2);">
                    <a href="<s:url action="nav_menu-course" namespace="/_administrator"/>" onclick="changeSel(2)" target="leftFrame">课程管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item6' onMouseMove="mv(this,'m',6);" onMouseOut="mv(this,'o',6);">
                    <a href="<s:url action="nav_menu-market" namespace="/_administrator"/>" onclick="changeSel(6)" target="leftFrame">市场管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item3' onMouseMove="mv(this,'m',3);" onMouseOut="mv(this,'o',3);">
                    <a href="<s:url action="nav_menu-news" namespace="/_administrator"/>" onclick="changeSel(3)" target="leftFrame">资讯管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item4' onMouseMove="mv(this,'m',4);" onMouseOut="mv(this,'o',4);">
                    <a href="<s:url action="nav_menu-order" namespace="/_administrator"/>" onclick="changeSel(4)" target="leftFrame">订单管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item5' onMouseMove="mv(this,'m',5);" onMouseOut="mv(this,'o',5);">
                    <a href="<s:url action="nav_menu-photo" namespace="/_administrator"/>" onclick="changeSel(5)" target="leftFrame">图片管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item7' onMouseMove="mv(this,'m',7);" onMouseOut="mv(this,'o',7);">
                    <a href="<s:url action="nav_menu-user" namespace="/_administrator"/>" onclick="changeSel(7)" target="leftFrame">用户管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item8' onMouseMove="mv(this,'m',8);" onMouseOut="mv(this,'o',8);">
                    <a href="<s:url action="nav_menu-other" namespace="/_administrator"/>" onclick="changeSel(8)" target="leftFrame">其它管理</a>
                </div>
            </dd>
            <dd>
                <div class='item' id='item9' onMouseMove="mv(this,'m',9);" onMouseOut="mv(this,'o',9);">
                    <a href="<s:url action="main" namespace="/_administrator"/>" onclick="changeSel(9)" target="mainFrame">后台主页</a>
                </div>
            </dd>
		</dl>
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>