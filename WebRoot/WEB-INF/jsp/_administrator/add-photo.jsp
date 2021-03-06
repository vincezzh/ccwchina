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
.form-admin input, .form-admin select{ width:200px;}
.form-admin .radio{ border:0px; width:30px;}
.form-admin caption{ height:25px; text-align:left; padding-left:5px; line-height:25px; color:#333; font-weight:bold;
background-color:#E4F7CC;border-top:1px solid #D3EB8B;border-right:1px solid #D3EB8B;border-left:1px solid #D3EB8B;}
.save-button{ padding-top:5px; padding-bottom:26px;}
.save-button input{ width:100px; height:30px;}

</style>
<script type="text/javascript">
function showDemo(type) {
	if(type == "index") {
		var path = $("#photoIndexPicturePath").val();
		$("#photoIndexPicture").html("<img src='" + path + "' width='150'/>");
	}else {
		var path = $("#photoPicturePath").val();
		$("#photoPicture").html("<img src='" + path + "' width='150'/>");
	}
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>
<div class="form-admin">
<s:form action="photo-add" namespace="/_administrator" method="post" name="form" target="_self" id="form">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>新建照片</caption>
    <tr>
      <td width="18%">照片类别</td>
      <td><label for="name"></label>
      	<s:select list="trunkList" listKey="photoTrunkTypeId" listValue="%{getText(photoTrunkTypeNameKey)}" name="photo.phototrunktype.photoTrunkTypeId"></s:select>
	  </td>
    </tr>
    <tr>
      <td width="18%">照片标题</td>
      <td><label for="name"></label>
      <s:textfield name="photo.photoTitle"/></td>
    </tr>
    <tr>
      <td width="18%">照片描述</td>
      <td><label for="name"></label>
      <s:textfield name="photo.photoDesc"/></td>
    </tr>
    <tr>
      <td>索引照片地址</td>
      <td>
	      <label for="keywords"></label>
	      <s:textfield id="photoIndexPicturePath" name="photo.photoIndexPath" onblur="showDemo('index')"/>
	      <span id="photoIndexPicture"></span>
	      <a href='<s:url action="archives-pictures" namespace="/_administrator"/>' target='_blank'>图片浏览</a>
      </td>
    </tr>
    <tr>
      <td>照片地址</td>
      <td>
	      <label for="textarea"></label>
	      <s:textfield id="photoPicturePath" name="photo.photoPath" onblur="showDemo('no_index')"/>
	      <span id="photoPicture"></span>
      </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    </table>
  <div class="save-button">
    <input type='submit' class="coolbg np" value='保存' />
    <input type='reset' class="coolbg np" value='取消' />
  </div>
</s:form>
</div>
</body>
</html>