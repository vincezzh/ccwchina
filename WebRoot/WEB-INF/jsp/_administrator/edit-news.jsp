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
<s:form action="news-update" namespace="/_administrator" method="post" name="form" target="_self" id="form">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>更新咨询</caption>
    <tr>
      <td width="18%">咨询标题</td>
      <td><label for="name"></label>
      <s:textfield name="news.newsTitle"/></td>
    </tr>
    <tr>
      <td width="18%">作者</td>
      <td><label for="name"></label>
      <s:textfield name="news.author"/></td>
    </tr>
    <tr>
      <td width="18%">发布日期(日期格式位：2011-01-01)</td>
      <td><label for="name"></label>
	      <s:textfield name="news.issueDate">
	      	<s:param name="value"><s:date name="news.issueDate" format="yyyy-MM-dd"/></s:param>
	      </s:textfield>
      </td>
    </tr>
    <tr>
      <td>网络搜索关键字</td>
      <td><label for="keywords"></label>
      <s:textfield name="news.searchKeyWords"/></td>
    </tr>
    <tr>
      <td valign="top">网络搜索内容描述</td>
      <td><label for="textarea"></label>
      <s:textfield name="news.searchDescription"/></td>
    </tr>
    <tr>
      <td valign="top">显示在首页</td>
      <td><label for="textarea"></label>
      	<s:if test='news.flag.contains("highlight")'>
      		<s:checkbox name="highlight" value="true" fieldValue="highlight"/>
      	</s:if>
      	<s:else>
      		<s:checkbox name="highlight" value="false" fieldValue="highlight"/>
      	</s:else>
      </td>
    </tr>
    <tr>
      <td>咨询内容</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="news.newsContent" rows="10"><s:property value="news.newsContent"/></textarea></td>
    </tr>
    </table>
    <s:hidden name="news.newsId"></s:hidden>
    <s:hidden name="news.flag"></s:hidden>
  <div class="save-button">
    <input type='submit' class="coolbg np" value='保存' />
    <input type='reset' class="coolbg np" value='取消' />
  </div>
</s:form>
</div>
</body>
</html>