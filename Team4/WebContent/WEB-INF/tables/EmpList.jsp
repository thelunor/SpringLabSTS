<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.bit.dto.BitEmp"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

<style type="text/css">
.btn-group-sm:hover {
   background-color: #eee;
   border-color: #eee;
   border: 0.5px solid #eee;
   color: #ff6863;
}

.btn-group-sm {
   color: #fff;
   background-color: #ff6863;
   border-color: #ff6863;
   border: 2px solid #eee;
   padding: 0.5rem 1rem;
}
.input-group {
   height: 50px;
}

h2 {
   font-family: 'Jua', sans-serif;
   text-align: center;
}

select {
   color: #fff;
   background-color: #ff6863;
   border: 2px solid;
   border-color: #ff6863;
   padding: 0.8rem 2rem;
   margin-bottom: 1rem;
}

select {
   position: relative;
   display: inline-block;
}

option {
   display: none;
   position: absolute;
   background-color: #f9f9f9;
   min-width: 160px;
   box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
   z-index: 1;
}

option {
   color: black;
   padding: 12px 16px;
   text-decoration: none;
   display: block;
}

option:hover {
   background-color: #f1f1f1
}

select:hover option {
   display: block;
}

select:hover select {
   background-color: #eee;
   border-color: #eee;
   border: 2px solid #eee;
   color: #ff6863;
}
thead th{
text-aglin:center;
}
</style>

<%
   ArrayList<BitEmp> list = (ArrayList<BitEmp>) request.getAttribute("empList");
%>
<script>
   $(function() {
	     $("#delete_btn").click(function(){
	         alert("삭제되었습니다.");
	      });
      $("#search_btn").click(function(){
   		if($("#search option:selected").val()=="dName"){
	         $.ajax({
	            url : 'SearchDname',
	            type : 'post',
	            data : {
	               "dName" : $("#member_search").val()
	            },
	            dataType : 'json',
	            success : function(data) {
	            	$("#tbody").empty();
	               
	               $.each(data, function(index, element) {
	                  
	                  console.log(element);
	                  
	                  var dNametable = "";
	                  dNametable += "<tr>";
	                  dNametable += "<td>" + element.eName + "</td>";
	                  dNametable += "<td>" + element.empNo + "</td>";
	                  dNametable += "<td>" + element.job + "</td>";
	                  dNametable += "<td>" + element.sal + "</td>";
	                  dNametable += "<td>" + element.dName + "</td>";
	                  dNametable += "<td>" + "<button type='button' class='btn-group-sm' id='edit_btn' onclick=" + "location.href='editForm.d4b?empNo="+element.empNo+"'>수정</button>" + "</td>";
	                  dNametable += "<td>" + "<button type='button' class='btn-group-sm' id='delete_btn' onclick=" + "location.href='deleteInfo.d4b?empNo="+element.empNo+"'>삭제</button>" + "</td>";
	                  dNametable += "</tr>";
	
	                  $('#tbody').append(dNametable);
	
	               });
               
	            }
	         });

   		}else if($("#search option:selected").val()=="empNo"){
   			$.ajax({
	            url : 'SearchEmpNo',
	            type : 'post',
	            data : {
	               "empNo" : $("#member_search").val()
	            },
	            dataType : 'json',
	            success : function(data) {
	            	$("#tbody").empty();
	               
	               $.each(data, function(index, element) {
	                  
	                  console.log(element);
	                  
	                  var dNametable = "";
	                  dNametable += "<tr>";
	                  dNametable += "<td>" + element.eName + "</td>";
	                  dNametable += "<td>" + element.empNo + "</td>";
	                  dNametable += "<td>" + element.job + "</td>";
	                  dNametable += "<td>" + element.sal + "</td>";
	                  dNametable += "<td>" + element.dName + "</td>";
	                  dNametable += "<td>" + "<button type='button' class='btn-group-sm' id='edit_btn' onclick=" + "location.href='editForm.d4b?empNo="+element.empNo+"'>수정</button>" + "</td>";
	                  dNametable += "<td>" + "<button type='button' class='btn-group-sm' id='delete_btn' onclick=" + "location.href='deleteInfo.d4b?empNo="+element.empNo+"'>삭제</button>" + "</td>";
	                  dNametable += "</tr>";

	
	                  $('#tbody').append(dNametable);
	
	               });
	            }
	         });   				
   		}else if($("#search option:selected").val()=="eName"){
   			$.ajax({
	            url : 'SearchEname',
	            type : 'post',
	            data : {
	               "eName" : $("#member_search").val()
	            },
	            dataType : 'json',
	            success : function(data) {
	            	$("#tbody").empty();
	               
	               $.each(data, function(index, element) {
	                  
	                  console.log(element);
	                  
	                  var dNametable = "";
	                  dNametable += "<tr>";
	                  dNametable += "<td>" + element.eName + "</td>";
	                  dNametable += "<td>" + element.empNo + "</td>";
	                  dNametable += "<td>" + element.job + "</td>";
	                  dNametable += "<td>" + element.sal + "</td>";
	                  dNametable += "<td>" + element.dName + "</td>";
	                  dNametable += "<td>" + "<button type='button' class='btn-group-sm' id='edit_btn' onclick=" + "location.href='editForm.d4b?empNo="+element.empNo+"'>수정</button>" + "</td>";
	                  dNametable += "<td>" + "<button type='button' class='btn-group-sm' id='delete_btn' onclick=" + "location.href='deleteInfo.d4b?empNo="+element.empNo+"'>삭제</button>" + "</td>";
	                  dNametable += "</tr>";

	
	                  $('#tbody').append(dNametable);
	
	               });
	            }
	         });   				   			 			
   		}	
   		
   });
});
</script>
<c:set var="empList" value="<%=list%>"></c:set>

   <section id="id" class="about roomy-100">
      <div class="container">
         <div class="about_content">
            <div class="container-fluid">
               <h1 class="h3 mb-2 text-gray-800">사원 관리</h1>
               <br>
               <div class="row">
                  <div class="col-md-2">
                     <select id="search">
                        <option value="search">검색 조건</option>
                        <option id="eName" value="eName">이름</option>
                        <option id="empNo" value="empNo">사번</option>
                        <option id="dName" value="dName">부서명</option>
                     </select>
                  </div>
                  <div class="col-md-4">

                     <div class="input-group" style="height: 40px; float: left;">
                        <input type="text" class="form-control bg-light border-0"
                           placeholder="회원 검색" aria-label="Search"
                           aria-describedby="basic-addon2" id="member_search"
                           name="member_search" style="height: 50px;">
                        <div class="input-group-append">
                           <button type="submit" class="btn btn-primary" type="button"
                              id="search_btn">Click</button>
                        </div>
                     </div>
                  </div>
               </div>
               <br>
               <!-- 테이블 -->
               <div class="card-header py-3">
                
                  <h6 class="m-0 font-weight-bold text-primary">사원 목록  <a href="List.d4b" style="float: right;"><i class="far fa-arrow-alt-circle-left fa-lg"></i></a></h6>
               
               </div>
               <div class="card-body">
                  <div class="table-responsive">
                     <form action="empList.d4b" method="post">
                        <table class="table table-bordered" id="dataTable"
                           style="text-align: center; margin: auto; width: 100%;">
                           <thead>
                              <tr>
                                 <th>이름</th>
                                 <th>사번</th>
                                 <th>직책</th>
                                 <th>급여</th>
                                 <th>부서</th>
                                 <th>수정</th>
                                 <th>삭제</th>
                              </tr>
                           </thead>
                           <tbody id="tbody">
                           <c:forEach var="emp" items="<%=list%>" varStatus="status">
                              <tr>
                                 <td>${emp.eName}</td>
                                 <td>${emp.empNo}</td>
                                 <td>${emp.job}</td>
                                 <td>${emp.sal}</td>
                                 <td>${emp.dName}</td>
                                 <td><button type="button" class="btn-group-sm"
                                       id="edit_btn"
                                       onclick="location.href='editForm.d4b?empNo=${emp.empNo}'">수정</button></td>
                                 <td><button type="button" class="btn-group-sm"  id="delete_btn" onclick="location.href='deleteInfo.d4b?empNo=${emp.empNo}'">삭제</button> </td>                                       
                              </tr>
                           </c:forEach>
                           </tbody>
                        </table>
                     </form>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!--End off container -->
      
   </section>
  <!-- The Modal -->  
<!-- scroll up-->
<jsp:include page="../include/ScrollUp.jsp"></jsp:include>