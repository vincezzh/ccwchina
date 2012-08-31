<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu</title>
<link rel="stylesheet" href="/_administrator/skin/css/base.css" type="text/css" />
<link rel="stylesheet" href="/_administrator/skin/css/menu.css" type="text/css" />
<script language='javascript'>var curopenItem = '1';</script>
<script language="javascript" type="text/javascript" src="/_administrator/skin/js/frame/menu.js"></script>
<base target="main" />
</head>
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")'><b>相册管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <li><a href='<s:url action="add-photo" namespace="/_administrator"/>' target='mainFrame'>添加照片</a> </li>
            <li><a href='<s:url action="archives-photo" namespace="/_administrator"/>' target='mainFrame'>管理照片</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      <!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>图片管理</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='<s:url action="add-picture" namespace="/_administrator"/>' target='mainFrame'>上传图片</a> </li>
            <li><a href='<s:url action="archives-pictures" namespace="/_administrator"/>' target='mainFrame'>图片浏览</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
	  </td>
  </tr>
</table>
</body>
</html>