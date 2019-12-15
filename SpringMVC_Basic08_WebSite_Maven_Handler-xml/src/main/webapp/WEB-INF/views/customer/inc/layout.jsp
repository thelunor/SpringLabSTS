<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>
		<tiles:getAsString name="title"/>
	</title>
	<link href="notice.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<!-- header 영역 -->
	<tiles:insertAttribute name="header" />

	<!-- visual 영역 -->
	<tiles:insertAttribute name="visual" />

	<div id="main">
		<div class="top-wrapper clear">
			<!-- content 영역 -->
			<tiles:insertAttribute name="content" />		

			<!-- aside 영역 -->
			<tiles:insertAttribute name="aside" />
		</div>
	</div>

	<!-- footer 영역 -->
	<tiles:insertAttribute name="footer" />
</body>
</html>
