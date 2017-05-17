<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ftp文件搜索应用</title>
</head>
<body>
<div align="center">
<h1>ftp文件搜索应用3.0</h1>
<h3>登录</h3>
</div>
	<div>
		<s:form action="UserAction_login" method="post" theme="simple" namespace="/">
			<table border='0' cellpadding="0" cellspacing="10" align="center">

				<tr>
					<td>ftp域名<span style="color: red">*</span>：
					</td>
					<td><s:textfield name="url"></s:textfield></td>
				</tr>
				<tr>
					<td>端口号（默认可不填）：</td>
					<td><s:textfield name="port"></s:textfield></td>
				</tr>
				<tr>
					<td>用户名<span style="color: red">*</span>：
					</td>
					<td><s:textfield name="username"></s:textfield></td>
				</tr>

				<tr>
					<td>密码<span style="color: red">*</span>：
					</td>
					<td><s:password name="password"></s:password></td>
				</tr>

				<tr align="center">
					<td><input type="submit" value="登录" /></td>
				</tr>
				<tr>
				<!-- 错误消息 -->
				<td><span style="color: red"><s:actionerror/></span></td>
					<!-- <td><span style="color: red"><s:property
								value="#request.errorMsg" /></span></td> -->
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>