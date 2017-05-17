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
	<div>
		<table border='0' cellpadding="0" cellspacing="10" align="center">
			<s:iterator value="list" var="filepath">
				<tr>
					<td align="center"><s:property value="filepath" /></td>
					<td align="center"><a
						href="${pageContext.request.contextPath }/UserAction_down.action?filepath=<s:property value='filepath'/>">
							下载</a></td>
				</tr>
			</s:iterator>
			<tr align="center">
			<td><a href="${pageContext.request.contextPath }/UserAction_toSerch">点击返回</a></td>
			</tr>
		</table>
	</div>
	
</body>
</html>