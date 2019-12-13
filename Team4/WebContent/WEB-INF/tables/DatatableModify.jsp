<%@page import="kr.or.bit.dto.BitEmp"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   BitEmp bitemp = (BitEmp) request.getAttribute("bitempData");
   System.out.println("bitemp 하이"+bitemp);
%>
<c:set var="empdata" value="<%=bitemp%>"></c:set>
<!doctype html>
<html class="no-js" lang="en">
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap"
   rel="stylesheet">
<head>
<meta charset="utf-8">
<title>Drop 4our bit</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<jsp:include page="../../css/css.jsp"></jsp:include>

<style type="text/css">
h2 {
   font-family: 'Jua', sans-serif;
}

input {
   height: 40px;
}

form-control {
   height: 40px;
}

.genderbox {
   padding: 1px;
   border: 1px solid #cccccc;
   height: 40px;
}

.form-group input[type="text"] {
   border: 1px solid #e5e5e5;
}
/* Center the avatar image inside this container */
.imgcontainer {
   text-align: center;
}
/* Avatar image */
img.avatar {
   border-radius: 50%;
}
</style>
</head>
<body data-spy="scroll" data-target=".navbar-collapse">
   <div class="culmn">
      <!--Home page style-->

      <!-- Top jsp -->
      <nav
         class="navbar navbar-light navbar-expand-lg  navbar-fixed ivory no-background bootsnav">
         <jsp:include page="../../WEB-INF/include/Top.jsp"></jsp:include>

         <!-- Side jsp -->
         <jsp:include page="../../WEB-INF/include/Side.jsp"></jsp:include>
      </nav>
      <!--Login Sections-->
         <section id="join" class="about roomy-100">
            <form action="editOk.d4b" method="post"
               name="JoinForm" enctype="multipart/form-data">
               <div class="container">
                  <div class="about_content">
                     <div class="row">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                           <div class="signup-form">
                              <br> <br> <br>
                              <h2 style="text-align: center">사원수정</h2>
                              <br>
                              <hr>
                              <div class="form-group" style="text-align: center">
                              <%
                              if(bitemp.getSaveFileName() == null) {
                              %>
                                 <img id="preview" src= "./images/profile.png"
                                    style="width: 200px" alt="Profile" class="avatar">
                             <%
                              }else{
                            	  
                             %>
                             <div id="fileUpload">
                             <img id="preview" src="./bitimg/${empdata.saveFileName}"
                             style="width: 200px" alt="Profile" class="avatar">      
                             </div>                           
                               <%
                              }
                               %>     
                                <br> <br>
                               <%
                                if(bitemp.getSaveFileName() != null) {
                               %>
                               <h6> 파일 이름 : ${empdata.originFileName}</h6>
                               <br>
                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                  <input type="file" name="fileName"  id="getfile"><br>
                               <% 
                                }else{
                               %>
                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                 <input type="file" name="fileName"  id="getfile"><span id="fileName"></span><br>
                                 <%
                                }
                                 %>
                              </div>
                              <br>
                              <div class="form-group">
                                 <label>이름 &nbsp;&nbsp;&nbsp;&nbsp;</label> <input type="text"
                                    class="form-control" name="eName" id="eName"
                                    required="required" value="${empdata.eName}" readonly>
                              </div>
                              <div class="form-group">
                                 <label>사번 &nbsp;&nbsp;&nbsp;&nbsp;</label> <input type="text"
                                    class="form-control" name="empNo" id="empNo" value="${empdata.empNo}" readonly>
                              </div>
                              <div class="form-group">
                                 <label>직책 &nbsp;&nbsp;&nbsp;&nbsp;</label> <input type="text"
                                    class="form-control" name="job" id="job" required="required" value="${empdata.job}">
                              </div>
                              <div class="form-group">
                                 <label>급여 &nbsp;&nbsp;&nbsp;&nbsp;</label> <input type="text"
                                    class="form-control" name="sal" id="sal" required="required" value="${empdata.sal}">
                              </div>
                              <div class="form-group">
                                 <label>부서 &nbsp;&nbsp;&nbsp;&nbsp;</label> <input type="text"
                                    class="form-control" name="dName" id="dName"
                                    required="required" value="${empdata.dName}">
                              </div>
                              <div class="form-group">
                                 <div class="row">
                                    <div class="col-sm-6">
                                       <button type="submit" 
                                          class="btn btn-primary btn-block btn-lg">수정 완료</button>
                                    </div>
                                    <div class="col-sm-6">
                                       <button type="reset" onclick="location.href='List.d4b'"
                                          class="btn btn-primary btn-block btn-lg">취소</button>
                                    </div>
                                 </div>
                              </div>
                              <br> <br> <br>
                           </div>
                        </div>
                     </div>
                  </div>
                  <!--End off row-->
               </div>
               <!--End off container -->
            </form>
         </section>

         <div class="scrollup">
            <a href="#"><i class="fa fa-chevron-up"></i></a>
         </div>
         <jsp:include page="../../WEB-INF/include/Bottom.jsp"></jsp:include>
   </div>
   <jsp:include page="../../js/js.jsp"></jsp:include>
<script>
var file = document.querySelector('#getfile');

file.onchange = function () { 
    var fileList = file.files ;
    
    // 읽기
    var reader = new FileReader();
    reader.readAsDataURL(fileList [0]);

    //로드 한 후
    reader.onload = function  () {
        document.querySelector('#preview').src = reader.result ;
    }; 
}; 
</script>
</body>

</html>