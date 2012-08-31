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
<s:form action="item-update" namespace="/_administrator" method="post" name="form" target="_self" id="form">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>更新商品</caption>
    <tr>
      <td width="18%">商品英文名称</td>
      <td><label for="name"></label>
      <s:textfield name="item.itemNameEn"/></td>
    </tr>
    <tr>
      <td width="18%">商品中文名称</td>
      <td><label for="name"></label>
      <s:textfield name="item.itemNameCn"/></td>
    </tr>
    <tr>
      <td width="18%">商品日文名称</td>
      <td><label for="name"></label>
      <s:textfield name="item.itemNameJp"/></td>
    </tr>
    <tr>
      <td width="18%">类别</td>
      <td width="82%"><label for="category"></label>
        <s:select list="trunkList" listKey="itemTrunkTypeId" listValue="%{getText(itemTrunkTypeNameKey)}" name="item.itemtrunktype.itemTrunkTypeId" />
		</td>
	</tr>
	<tr>
      <td>价格</td>
      <td><label for="keywords"></label>
      <s:textfield name="item.price"/>（填入整数）</td>
    </tr>
    <tr>
      <td>上架时间</td>
      <td><label for="keywords"></label>
      <s:textfield name="startTime">
      	<s:param name="value"><s:date format="yyyy-MM-dd" name="item.startTime"/></s:param>
  	  </s:textfield>（格式：2011-01-01）</td>
    </tr>
    <tr>
      <td>下架时间</td>
      <td><label for="keywords"></label>
      <s:textfield name="endTime">
      	<s:param name="value"><s:date format="yyyy-MM-dd" name="item.endTime"/></s:param>
  	  </s:textfield>（格式：2011-01-01）</td>
    </tr>
    <tr>
      <td>已出售数量</td>
      <td><label for="keywords"></label>
      <s:textfield name="item.saledNumber"/>（填入整数）</td>
    </tr>
    <tr>
      <td>网络搜索关键字</td>
      <td><label for="keywords"></label>
      <s:textfield name="item.searchKeyWords"/></td>
    </tr>
    <tr>
      <td valign="top">网络搜索内容描述</td>
      <td><label for="textarea"></label>
      <s:textfield name="item.searchDescription"/></td>
    </tr>
    <tr>
      <td>课程索引图(128px*128px)</td>
      <td><label for="title"></label>
      <s:textfield name="item.pictureOne"/>
      <a href='<s:url action="archives-pictures" namespace="/_administrator"/>' target='_blank'>图片浏览</a>
      </td>
    </tr>
    <tr>
      <td>课程展示图(600px*600px)</td>
      <td><label for="title"></label>
      <s:textfield name="item.pictureTwo"/></td>
    </tr>
    <tr>
      <td>其它图片</td>
      <td><label for="title"></label>
      <s:textfield name="item.pictureThree"/></td>
    </tr>
    <tr>
      <td>其它图片</td>
      <td><label for="title"></label>
      <s:textfield name="item.pictureFour"/></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td>英文描述</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="item.itemDescriptionEn" rows="10"><s:property value="item.itemDescriptionEn"/></textarea></td>
    </tr>
    <tr>
      <td>中文描述</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="item.itemDescriptionCn" rows="10"><s:property value="item.itemDescriptionCn"/></textarea></td>
    </tr>
    <tr>
      <td>日文描述</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="item.itemDescriptionJp" rows="10"><s:property value="item.itemDescriptionJp"/></textarea></td>
    </tr>
    </table>
  <s:hidden name="item.itemId"></s:hidden>
  <div class="save-button">
    <input type='submit' class="coolbg np" value='保存' />
    <input type='reset' class="coolbg np" value='取消' />
  </div>
</s:form>
</div>
</body>
</html>