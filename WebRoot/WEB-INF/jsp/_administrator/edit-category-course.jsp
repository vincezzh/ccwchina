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
<style>
.form-admin table{border-top:1px solid #D3EB8B;border-right:1px solid #D3EB8B;}
.form-admin td{ border-bottom:1px solid #D3EB8B;padding-left:5px; border-left:1px solid #D3EB8B}
.form-admin input, .form-admin select{}
.form-admin .radio{ border:0px; width:30px;}
.form-admin caption{ height:25px; text-align:left; padding-left:5px; line-height:25px; color:#333; font-weight:bold;
background-color:#E4F7CC;border-top:1px solid #D3EB8B;border-right:1px solid #D3EB8B;border-left:1px solid #D3EB8B;}
.save-button{ padding-top:5px; padding-bottom:26px;}
.save-button input{ width:100px; height:30px;}

</style>
<script type="text/javascript" src="/js_base/farbtastic/farbtastic.js"></script>
<link rel="stylesheet" href="/js_base/farbtastic/farbtastic.css" type="text/css" />
<script type="text/javascript" charset="utf-8">
  $(document).ready(function() {
	  $('#font-picker').farbtastic('#font-color');
      $('#background-picker').farbtastic('#background-color');
  });
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->

<div class="form-admin">
  <s:form action="course-category-update" namespace="/_administrator" method="post" name="form" target="_self" id="form-add-category">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>
     更新课程分类栏目
     </caption>
     <s:if test="trunk != null">
     	<tr>
	      <td width="18%">父级栏目</td>
	      <td width="82%"><label for="category-name"></label>无</td>
	    </tr>
		<tr>
	      <td width="18%">栏目英文名称</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="nameEN"/></td>
	    </tr>
		<tr>
	      <td width="18%">栏目中文名称</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="nameCN"/></td>
	    </tr>
	    <tr>
	      <td width="18%">栏目日文名称</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="nameJP"/></td>
	    </tr>
	    <tr>
	      <td width="18%">栏目网站路径</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="trunk.courseTrunkTypePath"/></td>
	    </tr>
	    <tr>
	      <td valign="top">英文内容描述</td>
	      <td><label for="textarea"></label>
	      	<s:textfield name="descEN"/></td>
	    </tr>
	    <tr>
	      <td valign="top">中文内容描述</td>
	      <td><label for="textarea"></label>
	      	<s:textfield name="descCN"/></td>
	    </tr>
	    <tr>
	      <td valign="top">日文内容描述</td>
	      <td><label for="textarea"></label>
	      	<s:textfield name="descJP"/></td>
	    </tr>
	    <tr>
	      <td>字体颜色</td>
	      <td><input type="text" id="font-color" name="trunk.fontColor" value="<s:property value="trunk.fontColor"/>" /></td>
	    </tr>
	    <tr>
	      <td colspan="2"><div id="font-picker"></div></td>
	    </tr>
	    <tr>
	      <td>背景颜色</td>
	      <td><input type="text" id="background-color" name="trunk.backgroundColor" value="<s:property value="trunk.backgroundColor"/>" /></td>
	    </tr>
	    <tr>
	      <td colspan="2"><div id="background-picker"></div></td>
	    </tr>
	    <tr>
      	<td colspan="2">&nbsp;
      		<s:hidden name="categoryLevel" value="1"></s:hidden>
      		<s:hidden name="trunk.courseTrunkTypeId"></s:hidden>
      		<s:hidden name="trunk.courseTrunkTypeNameKey"></s:hidden>
      		<s:hidden name="trunk.courseTrunkTypeDescriptionKey"></s:hidden>
      	</td>
    </tr>
    </s:if>
    <s:else>
    	<tr>
	      <td width="18%">父级栏目</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:property value="%{getText(branch.coursetrunktype.courseTrunkTypeNameKey)}"/></td>
	    </tr>
    	<tr>
	      <td width="18%">栏目英文名称</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="nameEN"/></td>
	    </tr>
		<tr>
	      <td width="18%">栏目中文名称</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="nameCN"/></td>
	    </tr>
	    <tr>
	      <td width="18%">栏目日文名称</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="nameJP"/></td>
	    </tr>
	    <tr>
	      <td width="18%">栏目网站路径</td>
	      <td width="82%"><label for="category-name"></label>
	        <s:textfield name="branch.courseBranchTypePath"/></td>
	    </tr>
	    <td colspan="2">&nbsp;
	    	<s:hidden name="categoryLevel" value="2"></s:hidden>
      		<s:hidden name="branch.courseBranchTypeId"></s:hidden>
      		<s:hidden name="branch.courseBranchTypeNameKey"></s:hidden>
      	</td>
    </s:else>
    
    </table>
  <div class="save-button">
    <input type='submit' class="coolbg np" value='保存' />
    <input type='reset' class="coolbg np" value='取消' />
  </div>
</s:form>
</div>
</body>
</html>