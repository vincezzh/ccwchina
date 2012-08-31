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
      <!-- Item 2 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")'><b>会员管理</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='<s:url action="archives-members" namespace="/_administrator"/>' target='mainFrame'>会员列表</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 2 End -->
      
	  </td>
  </tr>
</table>
</body>
</html>