<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>
		<tiles:getAsString name="title"/>		
	</title>
	<!-- css 설정 영역 -->
	<link href="<tiles:getAsString name="css"/>" type="text/css" rel="stylesheet" />
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
