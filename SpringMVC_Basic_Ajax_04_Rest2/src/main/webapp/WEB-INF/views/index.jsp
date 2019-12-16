<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/rest.css">
	<script
		src="${pageContext.request.contextPath}/resources/rest.js"></script>      
	
</head>
<body>
<div class="wrapper">
    <div class="fresh-table toolbar-color-azure full-screen-table" style="align-content: center;">
<div class="container-fluid">
<div class="row">
<div class="col-sm-6">
<fieldset>
	<legend>JSON</legend>
	<input type="button" value="RestController" id="restconBtn">
</fieldset>
</div>
</div>

	<hr>
	<div id="exp"></div>
		<span id="menuView"></span>
	</div>
	</div>
</div>
</body>
</html>