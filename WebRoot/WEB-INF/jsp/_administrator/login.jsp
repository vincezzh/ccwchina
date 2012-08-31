<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name=“robots” content=“nofollow”>
<title>管理平台</title>
<style type="text/css">
<!--
body, form{
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.login-table td div{ font-size:12px}

-->
</style></head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e5f6cf">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="/_administrator/skin/images/login_03.gif">
    
    <table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="266" background="/_administrator/skin/images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="95">
        
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="424" height="95" background="/_administrator/skin/images/login_06.gif">&nbsp;</td>
            <td width="183" background="/_administrator/skin/images/login_07.gif">
            
            <s:form action="admin_login" method="post" namespace="/_administrator">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-table">
              <tr>
                <td width="21%" height="30"><div align="center"><span>用户</span></div></td>
                <td width="79%" height="30">
                	<s:textfield name="admin.adminId" cssStyle="height:18px; width:130px; border:solid 1px #cadcb2"/>
                </td>
              </tr>
              <tr>
                <td height="30"><div align="center"><span>密码</span></div></td>
                <td height="30">
                	<s:password name="admin.password" cssStyle="height:18px; width:130px; border:solid 1px #cadcb2"/>
                </td>
              </tr>
              <tr>
                <td height="30">&nbsp;</td>
                <td height="30"><label>
                  <input name="admin-login" type="submit" id="admin-login" value="登录">
                  <input name="reset" type="reset" id="reset" value="重置">
                </label></td>
              </tr>
            </table></s:form>
            
            </td>
            <td width="255" background="/_administrator/skin/images/login_08.gif">&nbsp;</td>
          </tr>
        </table>
        
        </td>
      </tr>
      <tr>
        <td height="247" valign="top" background="/_administrator/skin/images/login_09.gif">
        </td>
      </tr>
    </table>
    
    </td>
  </tr>
  <tr>
    <td bgcolor="#a2d962">&nbsp;</td>
  </tr>
</table></body>
</html>
