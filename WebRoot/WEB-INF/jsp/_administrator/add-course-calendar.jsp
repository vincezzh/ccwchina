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

function freshCourse(courseTrunkTypeId) {
	$.ajax({
		   type: "POST",
		   url: "/_administrator/refreshCourseContent.htm",
		   data: "courseTrunkTypeId="+courseTrunkTypeId,
		   success: function(content){
		   		$('#courseContent').html(content);
		   }
	});
}

function submitForm() {
	document.getElementById("form1").submit();
}

function closeWindow() {
	window.opener = null; 
	window.close();
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='/_administrator/skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->

<div class="form-admin">
  <s:form action="course-calendar-add" namespace="/_administrator" method="post" name="form" target="_self" id="form1">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>
     添加课程表
     </caption>
    <tr>
      <td width="18%">类别一</td>
      <td width="82%"><label for="category"></label>
        <s:select list="trunkList" listKey="courseTrunkTypeId" listValue="%{getText(courseTrunkTypeNameKey)}" name="courseCalendar.coursetrunktype.courseTrunkTypeId" onchange="freshCourseBranchtype(this.value);freshCourse(this.value);"/>
	  </td>
	</tr>
	<tr>
      <td width="18%">类别二</td>
      <td width="82%"><label for="category"></label>
        <div id="branch"><select name="courseBranchTypeId"><option value="-1">请选择</option></select></div>
		</td>
	</tr>
	<tr>
      <td width="18%">课程内容</td>
      <td width="82%"><label for="category"></label>
        <div id="courseContent"><s:select list="courseList" listKey="courseId" listValue="courseNameCn" name="courseIds" multiple="true" size="10"/></div>
	  </td>
	</tr>
	<tr>
      <td width="18%">地点</td>
      <td width="82%"><label for="category-name"></label>
        <s:property value="courseLocation.courseLocationName"/></td>
    </tr>
	<tr>
      <td width="18%">日期</td>
      <td width="82%"><label for="category-name"></label>
      <s:date name="date" format="yyyy-MM-dd"/></td>
    </tr>
	<tr>
      <td width="18%">时间</td>
      <td width="82%"><label for="category-name"></label>
        <s:select list="classTimes" listKey="classTimeId" listValue="classTimeContent" name="classTimeIds" multiple="true" size="4"/>
	  </td>
    </tr>
    <tr>
      <td width="18%">价格</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.pricePerPerson" value="150"/></td>
    </tr>
    <tr>
      <td width="18%">座位数</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.seatLeft" value="10"/></td>
    </tr>
    <tr>
      <td width="18%">积点</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.points" value="0"/></td>
    </tr>
    <tr>
      <td width="18%">特别类型</td>
      <td width="82%"><label for="category"></label>
        <s:select list="specialTypeList" listKey="specialTypeId" listValue="specialTypeName" name="courseCalendar.specialtype.specialTypeId"/>
	  </td>
	</tr>
	<tr>
      <td width="18%">温馨提示</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.courseAdvise"/></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    </table>
	<s:hidden name="courseCalendar.classDate">
		<s:param name="value"><s:date name="date" format="yyyy-MM-dd"/></s:param>
	</s:hidden>
	<s:hidden name="courseCalendar.courselocation.courseLocationId" value="%{courseLocation.courseLocationId}"/>
	<s:hidden name="classTimeId" value="%{classTime.classTimeId}"/>
	<s:hidden name="courseLocationId" value="%{courseLocation.courseLocationId}"/>
	<s:hidden name="courseDate">
		<s:param name="value"><s:date name="date" format="yyyyMMdd"/></s:param>
	</s:hidden>
	
  <div class="save-button">
    <input type='button' class="coolbg np" onclick="submitForm();" value='保存' />
    <input type='reset' class="coolbg np" onclick="closeWindow();" value='取消' />
  </div>
</s:form>
</div>
</body>
</html>