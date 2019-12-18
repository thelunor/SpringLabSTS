<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XmlViewResolver</title>
</head>
<body>
	<h3>${bit}</h3>
	<p>XmlViewResolver<p>
	<a href="${pageContext.request.contextPath}">홈으로</a>
	<%
		System.out.println("xmlViewResolver");
	%>
</body>
</html>