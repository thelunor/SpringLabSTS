<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.bit.dto.BitEmp"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	ArrayList<BitEmp> list=(ArrayList<BitEmp>) request.getAttribute("empList");
%>
<c:set var="empList" value="<%=list%>"></c:set>
<c:forEach var="emp" items="<%=list%>" varStatus="status">
	<section id="id" class="about roomy-100">
		<div class="container">
			<div class="about_content">
				<div class="container-fluid">
					<jsp:include page="Topbar_Search.jsp"></jsp:include>
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
</c:forEach>
<!-- scroll up-->
<jsp:include page="../include/ScrollUp.jsp"></jsp:include>