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
        <dt onClick='showHide("items1_1")'><b>公共订单管理</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
          	<li><a href='<s:url action="archives-public-pending-orders" namespace="/_administrator"/>' target='mainFrame'>待处理订单</a> </li>
            <li><a href='<s:url action="archives-public-confirmed-orders" namespace="/_administrator"/>' target='mainFrame'>已审阅订单</a> </li>
            <li><a href='<s:url action="archives-public-finished-orders" namespace="/_administrator"/>' target='mainFrame'>已完成订单</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      <!-- Item 2 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>私人订单管理</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
          	<li><a href='<s:url action="archives-private-pending-orders" namespace="/_administrator"/>' target='mainFrame'>待处理订单</a> </li>
            <li><a href='<s:url action="archives-private-confirmed-orders" namespace="/_administrator"/>' target='mainFrame'>已审阅订单</a> </li>
            <li><a href='<s:url action="archives-private-finished-orders" namespace="/_administrator"/>' target='mainFrame'>已完成订单</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 2 End -->
      <!-- Item 4 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items4_1")'><b>商品订单统计</b></dt>
        <dd style='display:block' class='sitem' id='items4_1'>
          <ul class='sitemu'>
          	<li><a href='<s:url action="archives-item-pending-orders" namespace="/_administrator"/>' target='mainFrame'>待处理订单</a> </li>
            <li><a href='<s:url action="archives-item-confirmed-orders" namespace="/_administrator"/>' target='mainFrame'>已审阅订单</a> </li>
            <li><a href='<s:url action="archives-item-finished-orders" namespace="/_administrator"/>' target='mainFrame'>已完成订单</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 3 End -->
      <!-- Item 3 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items3_1")'><b>订单信息统计</b></dt>
        <dd style='display:block' class='sitem' id='items3_1'>
          <ul class='sitemu'>
          	<li><a href='<s:url action="archives-order-calendar" namespace="/_administrator"/>' target='mainFrame'>订单日历表</a> </li>
          	<li><a href='<s:url action="conclude-public-order-report" namespace="/_administrator"/>' target='mainFrame'>公共订单统计表</a> </li>
          	<li><a href='<s:url action="conclude-private-order-report" namespace="/_administrator"/>' target='mainFrame'>私人订单统计表</a> </li>
          	<li><a href='<s:url action="conclude-item-order-report" namespace="/_administrator"/>' target='mainFrame'>商品订单统计表</a> </li>
          	<li><a href='<s:url action="archives-monthly-order-calendar" namespace="/_administrator"/>' target='mainFrame'>每月统计表</a> </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 3 End -->
	</td>
  </tr>
</table>
</body>
</html>