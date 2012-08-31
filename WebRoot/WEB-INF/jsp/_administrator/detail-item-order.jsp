<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="/_administrator/skin/css/base.css">
<style>
.form-admin table{border-top:1px solid #D3EB8B;border-right:1px solid #D3EB8B;}
.form-admin td{ border-bottom:1px solid #D3EB8B;padding-left:5px; border-left:1px solid #D3EB8B}
.form-admin input, .form-admin select{ width:200px;}
.form-admin .radio{ border:0px; width:30px;}
.form-admin caption{ height:25px; text-align:left; padding-left:5px; line-height:25px; color:#333; font-weight:bold;
background-color:#E4F7CC;border-top:1px solid #D3EB8B;border-right:1px solid #D3EB8B;border-left:1px solid #D3EB8B;}
.save-button{ padding-top:5px; padding-bottom:26px;}
.save-button input{ width:100px; height:30px;}

</style>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>
<div class="form-admin">
<s:form action="" namespace="/_administrator" method="post" name="form" target="_self" id="form">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>商品课程订单详情</caption>
    <tr>
      <td width="18%">订单号</td>
      <td><label for="name"></label>
      <s:property value="order.orderItemId"/></td>
    </tr>
    <tr>
      <td width="18%">商品名称</td>
      <td><label for="name"></label>
      <s:property value="order.item.itemNameCn"/></td>
    </tr>
    <tr>
      <td width="18%">价格</td>
      <td><label for="name"></label>
      <s:property value="order.item.price"/>元</td>
    </tr>
    <tr>
      <td width="18%">商品上架日期</td>
      <td><label for="name"></label>
      <s:property value="order.item.startTime"/></td>
    </tr>
    <tr>
      <td width="18%">商品下架日期</td>
      <td><label for="name"></label>
      <s:property value="order.item.endTime"/></td>
    </tr>
    <tr>
      <td width="18%">联系人</td>
      <td><label for="name"></label>
      <s:property value="order.contactPerson"/></td>
    </tr>
    <tr>
      <td width="18%">移动电话</td>
      <td><label for="name"></label>
      <s:property value="order.cellphone"/></td>
    </tr>
    <tr>
      <td width="18%">email</td>
      <td><label for="name"></label>
      <s:property value="order.email"/></td>
    </tr>
    <tr>
      <td width="18%">预订时间</td>
      <td><label for="name"></label>
      <s:date name="order.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="18%">预订会员</td>
      <td><label for="name"></label>
      <s:property value="order.userdetail.userId"/></td>
    </tr>
    <tr>
      <td width="18%">订单确认时间</td>
      <td><label for="name"></label>
      <s:date name="order.checkTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="18%">积点</td>
      <td><label for="name"></label>
      <s:property value="order.points"/></td>
    </tr>
    <tr>
      <td width="18%">订单状态</td>
      <td><label for="name"></label>
      <s:property value="%{getText(order.orderstatus.orderStatusName)}"/></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    </table>
    
  <div class="save-button">
    <input type='button' class="coolbg np" value='返回' onclick="javascript:history.go(-1);"/>
  </div>
</s:form>
</div>
</body>
</html>