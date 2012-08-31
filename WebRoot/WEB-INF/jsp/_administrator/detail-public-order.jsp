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
     <caption>公共课程订单详情</caption>
    <tr>
      <td width="18%">订单号</td>
      <td><label for="name"></label>
      <s:property value="order.orderPublicId"/></td>
    </tr>
    <tr>
      <td width="18%">总人数</td>
      <td><label for="name"></label>
      <s:property value="order.totalPeopleNumber"/></td>
    </tr>
    <tr>
      <td width="18%">总价</td>
      <td><label for="name"></label>
      <s:property value="order.totalPrice"/>元</td>
    </tr>
    <tr>
      <td width="18%">是否需要地图</td>
      <td><label for="name"></label>
      <s:property value="order.needMap"/></td>
    </tr>
    <tr>
      <td width="18%">联系人</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.peopletitle.peopleTitleName"/> <s:property value="order.orderbasic.contactPerson"/></td>
    </tr>
    <tr>
      <td width="18%">移动电话</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.cellphone"/></td>
    </tr>
    <tr>
      <td width="18%">固定电话</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.telephone"/></td>
    </tr>
    <tr>
      <td width="18%">传真</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.fax"/></td>
    </tr>
    <tr>
      <td width="18%">email</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.email"/></td>
    </tr>
    <tr>
      <td width="18%">允许确认时间</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.confirmtime.confirmTimeContent"/></td>
    </tr>
    <tr>
      <td width="18%">邮编</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.zipCode"/></td>
    </tr>
    <tr>
      <td width="18%">预订时间</td>
      <td><label for="name"></label>
      <s:date name="order.orderbasic.bookingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="18%">预订会员</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.userdetail.userId"/></td>
    </tr>
    <tr>
      <td width="18%">食素者</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.isVegetarian"/></td>
    </tr>
    <tr>
      <td width="18%">过敏</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.allergic"/></td>
    </tr>
    <tr>
      <td width="18%">联系地址</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.address"/></td>
    </tr>
    <tr>
      <td width="18%">订单确认时间</td>
      <td><label for="name"></label>
      <s:date name="order.orderbasic.checkingTime" format="yyyy-MM-dd HH:mm:ss"/></td>
    </tr>
    <tr>
      <td width="18%">积点</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.points"/></td>
    </tr>
    <tr>
      <td width="18%">订单状态</td>
      <td><label for="name"></label>
      <s:property value="%{getText(order.orderbasic.orderstatus.orderStatusName)}"/></td>
    </tr>
    <tr>
      <td width="18%">客户留言</td>
      <td><label for="name"></label>
      <s:property value="order.orderbasic.description"/></td>
    </tr>
    <tr>
      <td width="18%">课程类型</td>
      <td><label for="name"></label>
      <s:property value="%{getText(order.coursecalendar.coursetrunktype.courseTrunkTypeNameKey)}"/></td>
    </tr>
    <tr>
      <td width="18%">上课时间</td>
      <td><label for="name"></label>
      <s:date name="order.coursecalendar.classDate" format="yyyy-MM-dd"/> <s:property value="order.coursecalendar.classtime.classTimeContent"/></td>
    </tr>
    <tr>
      <td width="18%">上课地点</td>
      <td><label for="name"></label>
      <s:property value="order.coursecalendar.courselocation.courseLocationName"/></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    </table>
    <s:hidden name="id" value="%{order.orderPublicId}"></s:hidden>
    <s:hidden name="userId" value="%{order.orderbasic.userdetail.userId}"></s:hidden>
  <div class="save-button">
    <input type='button' class="coolbg np" value='返回' onclick="javascript:history.go(-1);"/>
  </div>
</s:form>
</div>
</body>
</html>