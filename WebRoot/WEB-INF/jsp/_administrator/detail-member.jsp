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
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>会员详情</caption>
    <tr>
      <td width="18%">User ID</td>
      <td><label for="name"></label>
      <s:property value="user.userId"/></td>
    </tr>
    <tr>
      <td width="18%">称谓</td>
      <td><label for="name"></label>
      <s:property value="user.peopletitle.peopleTitleName"/></td>
    </tr>
    <tr>
      <td width="18%">名</td>
      <td><label for="name"></label>
      <s:property value="user.firstName"/></td>
    </tr>
    <tr>
      <td width="18%">姓</td>
      <td><label for="name"></label>
      <s:property value="user.lastName"/></td>
    </tr>
    <tr>
      <td width="18%">喜欢的厨房</td>
      <td><label for="name"></label>
      <s:property value="courselocation.courseLocationName"/></td>
    </tr>
    <tr>
      <td width="18%">e-mail</td>
      <td><label for="name"></label>
      <s:property value="user.email"/></td>
    </tr>
    <tr>
      <td width="18%">昵称</td>
      <td><label for="name"></label>
      <s:property value="user.nickName"/></td>
    </tr>
    <tr>
      <td width="18%">移动电话</td>
      <td><label for="name"></label>
      <s:property value="user.cellphone"/></td>
    </tr>
    <tr>
      <td width="18%">固定电话</td>
      <td><label for="name"></label>
      <s:property value="user.telephone"/></td>
    </tr>
    <tr>
      <td width="18%">传真</td>
      <td><label for="name"></label>
      <s:property value="user.fax"/></td>
    </tr>
    <tr>
      <td width="18%">素食者</td>
      <td><label for="name"></label>
      <s:property value="user.isVegetarian"/></td>
    </tr>
    <tr>
      <td width="18%">过敏的物品</td>
      <td><label for="name"></label>
      <s:property value="user.allergic"/></td>
    </tr>
    <tr>
      <td width="18%">地址</td>
      <td><label for="name"></label>
      <s:property value="user.address"/></td>
    </tr>
    <tr>
      <td width="18%">邮编</td>
      <td><label for="name"></label>
      <s:property value="user.zipCode"/></td>
    </tr>
    <tr>
      <td width="18%">积点</td>
      <td><label for="name"></label>
      <s:property value="user.points"/></td>
    </tr>
    <tr>
      <td width="18%">需要优惠券</td>
      <td><label for="name"></label>
      <s:property value="user.needCoupon"/></td>
    </tr>
    <tr>
      <td width="18%">需要新闻</td>
      <td><label for="name"></label>
      <s:property value="user.needNews"/></td>
    </tr>
    <tr>
      <td width="18%">注册时间</td>
      <td><label for="name"></label>
      <s:date name="user.registerTime" format="yyyy-MM-dd HH:mm:ss"/></td>
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
</div>
</body>
</html>