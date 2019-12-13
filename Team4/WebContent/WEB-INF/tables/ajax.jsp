<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:include page="../admin/AdminAuthority.jsp"></jsp:include>
<!doctype html>
<html class="no-js" lang="en">
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap"
	rel="stylesheet">

<head>
<meta charset="utf-8">
<title>Drop 4our bit</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

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
<script>
	$(function(){
		$("#search_btn").click(function(){
			$.ajax({
				url:'SearchEmpNo',
				dataType:'json',
				success:function(data){
					console.log(data);
				}
			});
		})
	});

</script>
<body data-spy="scroll" data-target=".navbar-collapse">
	<nav
		class="navbar navbar-light navbar-expand-lg  navbar-fixed ivory no-background bootsnav">
		<jsp:include page="../admin/Admin_top.jsp"></jsp:include>

		<!-- Side jsp -->
		<jsp:include page="../include/Side.jsp"></jsp:include>
	</nav>

	<section id="id" class="about roomy-100">
		<div class="container">
			<div class="about_content">
				<div class="container-fluid">
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
						action="MemberSearch.jsp" method="post">
						<div class="input-group" style="height: 40px; float: left;">
							<input type="text" class="form-control bg-light border-0 small"
								placeholder="회원 검색" aria-label="Search"
								aria-describedby="basic-addon2" id="member_search"
								name="member_search">
							<div class="input-group-append">
								<button type="submit" class="btn btn-primary" type="button" id="search_btn">
									Click</button>
							</div>
						</div>
					</form>
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">회원목록</h6>
					</div>
					<div class="card-body">
						<div class="table-responsive">

							<form action="Ex03_MemberEditok.jsp" method="post">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>사번</th>
											<th>이름</th>
											<th>직책</th>
											<th>연봉</th>
											<th>부서</th>
										</tr>
									</thead>
									<tr>
										<td><a href="MemberDetail.jsp?id=${emp.empNo }">${emp.empNo }</a></td>
										<td>${emp.eName }</td>
										<td>${emp.job }</td>
										<td>${emp.sal }</td>
										<td>${emp.dName }</td>
									</tr>
								</table>
							</form>
						</div>
						<button class="btn btn-primary"
							onclick="location.href='Member_modify.jsp?userId=${emp.empNo }' ">정보
							수정</button>
					</div>
				</div>
			</div>
		</div>
		<!--End off container -->
	</section>

	<jsp:include page="../include/Bottom.jsp"></jsp:include>

	<!-- JS includes -->
	<jsp:include page="../../js/js.jsp"></jsp:include>

</body>

</html>