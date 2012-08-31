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
<script type="text/javascript" src="/js_base/jquery-1.4.2.js" ></script> 
<link href="/js_base/jquery.uploadify/default.css"  rel="stylesheet" type="text/css"/>  
<link href="/js_base/jquery.uploadify/uploadify.css"  rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js_base/jquery.uploadify/swfobject.js" ></script>  
<script type="text/javascript" src="/js_base/jquery.uploadify/jquery.uploadify.v2.1.0.min.js" ></script>  
<script type="text/javascript">
    $(document).ready(function(){  
        $("#upload").uploadify({
            'uploader'       : '/js_base/jquery.uploadify/uploadify.swf',  
            'script'         : '/common/upload.htm',  
			'scriptData'	 : {'folderName': 'test'},
            'cancelImg'      : '/js_base/jquery.uploadify/cancel.png',  
            'fileDataName'   : 'myFile',  
            'multi'          : true,  
            'sizeLimit'      : 1024000000,  
            'fileDesc'       : '支持格式：(.JPG)',  
            'fileExt'        : '*.jpg',
            'queueSizeLimit' : 10,
            'simUploadLimit' : 1
        });  
    });

    function changeData(folderName) {
		if(folderName != '')
    		$('#upload').uploadifySettings('scriptData',{'folderName':folderName});
    }
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->

<div class="form-admin">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>
     添加图片目录
     </caption>
    <tr>
      <td width="18%">目录名称</td>
      <td width="82%"><label for="category-name"></label>
      	<s:form action="add-picture-folder" namespace="/_administrator">
	        <s:textfield name="folderName"/>
	      	<input type='submit' value='保存' />
      	</s:form>
      </td>
    </tr>
    <tr>
      <td width="18%">添加图片</td>
      <td width="82%"><label for="category-name"></label>
      	<s:form action="" namespace="/_administrator">
      		<div>选择上传图片的目录：
      			<s:select id="" list="folderNames" value="folderName" onchange="changeData(this.value);"></s:select>
      		</div>
	        <input type="file" name="myFile" id="upload" >  
	        <a href="javascript:$('#upload').uploadifyUpload();" >上传</a>
        </s:form>
       </td>
    </tr>
    <!-- <tr>
      <td width="18%">是否根栏目</td>
      <td><p>
        <label>
          <input style="width:20px; border:0;" type="radio" name="RadioGroup1" value="单选" id="RadioGroup1_0">
          是</label>
        <br>
        <label>
          <input style="width:20px; border:0;" type="radio" name="RadioGroup1" value="单选" id="RadioGroup1_1">
          否</label>
        <br>
      </p></td>
    </tr>
    <tr>
      <td>所属父栏目</td>
      <td><label for="category-parent"></label>
        <select name="category-parent" id="category-parent">
          <option selected>=请选择=</option>
          <option>资讯分类一</option>
          <option>资讯分类二</option>
        </select></td>
    </tr> -->
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    </table>
</div>
</body>
</html>