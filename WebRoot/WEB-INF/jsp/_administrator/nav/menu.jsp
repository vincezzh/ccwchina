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
        <dt onClick='showHide("items1_1")'><b>常用操作</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <!-- <li>
              <div class='items'>
                <div class='fllct'><a href='../archives-category.jsp' target='mainFrame'>网站栏目管理</a></div>
                <div class='flrct'> <a href='../archives.jsp' target='mainFrame'><img src='/_administrator/skin/images/frame/gtk-sadd.png' alt='创建栏目' title='创建栏目'/></a> </div>
              </div>
            </li> -->
            <li><a href='<s:url action="archives-course-calendar" namespace="/_administrator"/>' target='mainFrame'>管理课程表</a> </li>
            <li><a href='<s:url action="add-news" namespace="/_administrator"/>' target='mainFrame'>添加资讯</a> </li>
            <li><a href='<s:url action="archives-news" namespace="/_administrator"/>' target='mainFrame'>管理资讯</a> </li>
            <li><a href='<s:url action="archives-message" namespace="/_administrator"/>' target='mainFrame'>管理订单</a> </li>
            <li><a href='<s:url action="add-link" namespace="/_administrator"/>' target='mainFrame'>管理相册</a> </li>
            
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      
	  </td>
  </tr>
</table>
</body>
</html>