<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

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

h2{
font-family: 'Jua', sans-serif;
text-align: center;
}
</style>
</head>

<body data-spy="scroll" data-target=".navbar-collapse">

	<div class="culmn">
		<!--Home page style-->
			
		<!-- Top jsp -->
		<nav class="navbar navbar-light navbar-expand-lg  navbar-fixed ivory no-background bootsnav">
			<jsp:include page="../admin/Admin_top.jsp"></jsp:include>


			<!-- Side jsp -->
			<jsp:include page="../include/Side.jsp"></jsp:include>
		</nav>
		<!--Join Sections-->

		<section id="id" class="about roomy-100">
							
			<div class="container">
				<div class="about_content">

        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Tables</h1>
         <jsp:include page="Topbar_Search.jsp"></jsp:include>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <jsp:include page="DatatableModify.jsp"></jsp:include>
          </div>

        </div>
					</div>
				</div>
			<!--End off container -->
		</section>


		<!-- scroll up-->
		<div class="scrollup">
			<a href="#"><i class="fa fa-chevron-up"></i></a>
		</div>
		<!-- End off scroll up -->
		<jsp:include page="../include/Bottom.jsp"></jsp:include>
	</div>

	<!-- JS includes -->
	<jsp:include page="../../js/js.jsp"></jsp:include>


</body>
</html>