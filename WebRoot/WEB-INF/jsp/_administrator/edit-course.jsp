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
<script type="text/javascript">
function freshCourseBranchtype(courseTrunkTypeId) {
	$.ajax({
		   type: "POST",
		   url: "/_administrator/freshCoursebranchtype.htm",
		   data: "courseTrunkTypeId="+courseTrunkTypeId,
		   success: function(content){
		   		$('#branch').html(content);
			}
	});
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>
<div class="form-admin">
<s:form action="course-update" namespace="/_administrator" method="post" name="form" target="_self" id="form">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>更新课程</caption>
    <tr>
      <td width="18%">课程英文名称</td>
      <td><label for="name"></label>
      <s:textfield name="course.courseNameEn"/></td>
    </tr>
    <tr>
      <td width="18%">课程中文名称</td>
      <td><label for="name"></label>
      <s:textfield name="course.courseNameCn"/></td>
    </tr>
    <tr>
      <td width="18%">课程日文名称</td>
      <td><label for="name"></label>
      <s:textfield name="course.courseNameJp"/></td>
    </tr>
    <tr>
      <td width="18%">类别一</td>
      <td width="82%"><label for="category"></label>
        <s:select list="trunkList" listKey="courseTrunkTypeId" listValue="%{getText(courseTrunkTypeNameKey)}" name="course.coursetrunktype.courseTrunkTypeId" onchange="freshCourseBranchtype(this.value)"/>
		</td>
	</tr>
	<tr>
      <td width="18%">类别二</td>
      <td width="82%"><label for="category"></label>
        <div id="branch"><select name="course.coursebranchtype.courseBranchTypeId"><option value='<s:property value="course.coursebranchtype.courseBranchTypeId"/>'><s:property value="%{getText(course.coursebranchtype.courseBranchTypeNameKey)}"/></option></select></div>
		</td>
	</tr>
    <tr>
      <td>网络搜索关键字</td>
      <td><label for="keywords"></label>
      <s:textfield name="course.searchKeyWords"/></td>
    </tr>
    <tr>
      <td valign="top">网络搜索内容描述</td>
      <td><label for="textarea"></label>
      <s:textfield name="course.searchDescription"/></td>
    </tr>
    <tr>
      <td>课程索引图(100px*100px)</td>
      <td><label for="title"></label>
      <s:textfield name="course.pictureOne"/>
      <a href='<s:url action="archives-pictures" namespace="/_administrator"/>' target='_blank'>图片浏览</a>
      </td>
    </tr>
    <tr>
      <td>课程展示图(310px*230px)</td>
      <td><label for="title"></label>
      <s:textfield name="course.pictureTwo"/></td>
    </tr>
    <tr>
      <td>其它图片</td>
      <td><label for="title"></label>
      <s:textfield name="course.pictureThree"/></td>
    </tr>
    <tr>
      <td>其它图片</td>
      <td><label for="title"></label>
      <s:textfield name="course.pictureFour"/></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td>英文描述</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="course.courseDescriptionEn" rows="10"><s:property value="course.courseDescriptionEn"/></textarea></td>
    </tr>
    <tr>
      <td>中文描述</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="course.courseDescriptionCn" rows="10"><s:property value="course.courseDescriptionCn"/></textarea></td>
    </tr>
    <tr>
      <td>日文描述</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="ckeditor" cols="80" name="course.courseDescriptionJp" rows="10"><s:property value="course.courseDescriptionJp"/></textarea></td>
    </tr>
    </table>
  <s:hidden name="course.courseId"></s:hidden>
  <div class="save-button">
    <input type='submit' class="coolbg np" value='保存' />
    <input type='reset' class="coolbg np" value='取消' />
  </div>
</s:form>
</div>
</body>
</html>