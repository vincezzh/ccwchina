<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片管理</title>
<style>
.picture-manage-area{ width:800px; padding:5px}
.upload-area{ height:200px; background-color:#F3F3F3; margin-bottom:6px}
.pictures-area {width:800px;}
.picture-line-wapper{ height:140px; background-color:#F8F8F8; margin-bottom:5px; padding-top:5px; padding-left:5px}
.detail-picture-wapper{ width:120px;float:left;margin-right:12px;}
.detail-picture-wapper input{ width:120px; background:#E0E0E0; border:1px solid #333}
.detail-picture{height:100px; border:3px solid #666; width:120px; margin-bottom:5px}

</style>
<script type="text/javascript">
function freshPicture(folderName) {
	window.location.href="/_administrator/archives-pictures.htm?folderName=" + folderName;
}
function deletePicture(path) {
	var folderName = document.getElementById("folderName").value;
	window.location.href="/_administrator/delete-picture.htm?folderName=" + folderName + "&path=" + path;
}
</script>
</head>

<body>
<div class="picture-manage-area">
	<div>
		选择图片目录：<s:select id="folderName" list="folderNames" value="folderName" onchange="freshPicture(this.value)"></s:select>
	</div>
    <div class="pictures-area">
    
    
    <s:iterator value="picturePaths" status="i" id="p">
	    <s:if test="%{#i.index%6} == 0">
	    	<div class="picture-line-wapper">
	    </s:if>
        	<div class="detail-picture-wapper">
                <div class="detail-picture">
                	<img src="/<s:property value='p'/>" height="100px" width="120px"/>
                </div>
                <input name="picture-url" type="text" value="/<s:property value='p'/>" onclick="javascript:this.select();"/>
                <input type="button" value="删除" onclick='deletePicture("<s:property value='p'/>")'/>
            </div>
        <s:if test="%{i.index%6} == 0">
	    	</div>
	    </s:if>
   	</s:iterator>
   		
        
    </div>
</div>



</body>
</html>
