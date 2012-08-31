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
<script type="text/javascript" src="/js_base/calendar/jcalendar-source.js" ></script>
<link href="/js_base/calendar/jcalendar.css"  rel="stylesheet" type="text/css"/>
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
$(document).ready(function(){  
	$('fieldset.jcalendar').jcalendar();

	$('fieldset.jcalendar').click(function(){
		$("#cloneDate").val($("#year").val() + "-" + $("#month").val() + "-" + $("#day").val());
	});

    $("#showCalendar").click(function(){
		var show = $("#calendar").css("display");
		if(show == "none") {
			$("#calendar").css("display","block");
		}else {
			$("#cloneDate").val($("#year").val() + "-" + $("#month").val() + "-" + $("#day").val());
			$("#calendar").css("display","none");
		}
    });
});

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

function cloneCoursecalendar() {
	$("#classDate").val($("#cloneDate").val());
	document.getElementById("form1").action = "/_administrator/course-calendar-clone.htm";
	document.getElementById("form1").submit();
}

function deleteCoursecalendar() {
	window.location.href = '/_administrator/course-calendar-delete.htm?courseCalendarId=<s:property value="courseCalendar.courseCalendarId"/>';
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
  <s:form action="course-calendar-update" namespace="/_administrator" method="post" name="form" target="_self" id="form1">
  <table width="90%" border="0" cellspacing="0" cellpadding="2">
     <caption>
     更新课程表
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
        <div id="branch"><select name="courseBranchTypeId"><option value='<s:property value="courseCalendar.coursebranchtype.courseBranchTypeId"/>'><s:property value="%{getText(courseCalendar.coursebranchtype.courseBranchTypeNameKey)}"/></option></select></div>
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
      	<s:select list="locationList" listKey="courseLocationId" listValue="courseLocationName" name="courseCalendar.courselocation.courseLocationId" />
      </td>
    </tr>
	<tr>
      <td width="18%">日期</td>
      <td width="82%"><label for="category-name"></label>
      <s:date name="courseCalendar.classDate" format="yyyy-MM-dd"/>
    </tr>
	<tr>
      <td width="18%">时间</td>
      <td width="82%"><label for="category-name"></label>
        <s:property value="courseCalendar.classtime.classTimeContent"/></td>
    </tr>
    <tr>
      <td width="18%">价格</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.pricePerPerson"/></td>
    </tr>
    <tr>
      <td width="18%">座位数</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.seatLeft"/></td>
    </tr>
    <tr>
      <td width="18%">积点</td>
      <td width="82%"><label for="category-name"></label>
        <s:textfield name="courseCalendar.points"/></td>
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
      <td>复制日期</td>
      <td>
      	<div>
      		<s:textfield id="cloneDate" name="date">
      			<s:param name="value"><s:date name="courseCalendar.classDate" format="yyyy-MM-dd"/></s:param>
			</s:textfield>
			<input id="showCalendar" type="button" value="日历"/>
		</div>
	    <s:include value="common/calendar.jsp"></s:include>
      </td>
    </tr>
    <tr>
      <td width="18%">复制时间</td>
      <td width="82%"><label for="category-name"></label>
        <s:select list="classTimes" listKey="classTimeId" listValue="classTimeContent" name="classTimeIds" multiple="true" size="4"/>
	  </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    </table>
	<s:hidden name="courseCalendar.classDate" id="classDate">
		<s:param name="value"><s:date name="courseCalendar.classDate" format="yyyy-MM-dd"/></s:param>
	</s:hidden>
	<s:hidden name="courseCalendar.classtime.classTimeId" value="%{courseCalendar.classtime.classTimeId}"/>
	<s:hidden name="courseCalendar.courseCalendarId" value="%{courseCalendar.courseCalendarId}"/>
	
  <div class="save-button">
    <input type='button' class="coolbg np" onclick="submitForm();" value='更新' />
    <input type='reset' class="coolbg np" onclick="closeWindow();" value='取消' />
    <input type='button' class="coolbg np" onclick="cloneCoursecalendar();" value='复制' />
    <input type='button' class="coolbg np" onclick="deleteCoursecalendar();" value='删除' />
  </div>
</s:form>
</div>
</body>
</html>