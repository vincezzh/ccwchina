<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<link rel="stylesheet" type="text/css" href="/_administrator/skin/css/base.css">
<script type="text/javascript" src="/js_base/jquery-1.4.2.js"></script>
<script type="text/javascript" src="/js_base/ckeditor/ckeditor.js"></script>
<script src="/js_base/ckeditor/_samples/sample.js" type="text/javascript"></script>
<link href="/js_base/ckeditor/_samples/sample.css" rel="stylesheet" type="text/css" />
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
<s:form action="send-mail-list" namespace="/_administrator" method="post" name="form" target="_self" id="form">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>群发邮件</caption>
    <tr>
      <td width="18%">邮件标题</td>
      <td><label for="name"></label>
      <s:textfield name="title"/></td>
    </tr>
    <tr>
      <td width="18%">收件人称谓</td>
      <td><label for="name"></label>
      <s:textfield name="guestTitle"/></td>
    </tr>
    <tr>
      <td width="18%">收件人种类</td>
      <td>
      	<s:radio cssStyle="width:30px;" name="userType" list="#{'member':'注册会员', 'maillist':'邮件列表用户', 'all':'所有用户', 'custom':'自定义用户'}" value="'member'"></s:radio>
      </td>
    </tr>
    <tr>
      <td width="18%">自定义用户邮件列表</td>
      <td><label for="name"></label>
      <s:select list="mailList" name="receivers" multiple="true" size="15"/>
	  </td>
    </tr>
    <tr>
      <td>邮件内容</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="mailContent" rows="10"></textarea></td>
    </tr>
    <tr>
      <td width="18%">发送每月课程表</td>
      <td><label for="name"></label>
      <s:checkbox name="replacement" value="false" fieldValue="true"/></td>
    </tr>
    </table>
  <div class="save-button">
    <input type='submit' class="coolbg np" value='发送' />
  </div>
</s:form>
</div>
</body>
</html>