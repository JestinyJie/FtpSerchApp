<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
</head>
<body>
	<div align="center">
		<h1>ftp文件搜索应用3.0</h1>
		<h3>搜索结果</h3>
	</div>
	<div align="center">
		<h3 style="color:red">未找到文件！</h3><br>
		<a href="${pageContext.request.contextPath }/UserAction_toSerch">点击返回</a>
	</div>
</body>
</html>