<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功</title>
</head>
<body>
<div align="center">
<h1>ftp文件搜索应用3.0</h1>
<h3>文件搜索</h3>
</div>
	<div>
		<s:form action="UserAction_serch" method="post" theme="simple" namespace="/">
			<table border='0' cellpadding="0" cellspacing="10" align="center">

				<tr>
					<td>搜索文件名：
					</td>
					<td><s:textfield name="fileName"></s:textfield></td>
					<td><input type="submit" value="搜索" /></td>
				</tr>
				<tr>
				<!-- 错误消息 -->
				<td><span style="color: red"><s:actionerror/></span></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>