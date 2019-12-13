<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:include page="../admin/AdminAuthority.jsp"></jsp:include>
<!doctype html>
<html class="no-js" lang="en">
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">

<head>
    <meta charset="utf-8">
    <title>Drop 4our bit</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../../css/css.jsp"></jsp:include>

    <style type="text/css">
        .input-group {
            height: 50px;
        }

        h2 {
            font-family: 'Jua', sans-serif;
            text-align: center;
        }
    </style>
</head>

<body data-spy="scroll" data-target=".navbar-collapse">
    <nav class="navbar navbar-light navbar-expand-lg  navbar-fixed ivory no-background bootsnav">
        <jsp:include page="../admin/Admin_top.jsp"></jsp:include>

        <!-- Side jsp -->
        <jsp:include page="../include/Side.jsp"></jsp:include>
    </nav>
    <jsp:include page="MemberSearchResultTable.jsp"></jsp:include>
    <jsp:include page="../include/Bottom.jsp"></jsp:include>

    <!-- JS includes -->
    <jsp:include page="../../js/js.jsp"></jsp:include>

</body>

</html>