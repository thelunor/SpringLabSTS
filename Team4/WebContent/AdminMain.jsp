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
<jsp:include page="./css/css.jsp"></jsp:include>

<style type="text/css">
.input-group {
   height: 50px;
}


a {
    color: #797979;
}

a:hover {
    color: #212529;
}
h2 {
   font-family: 'Jua', sans-serif;
   text-align: center;
}
</style>
</head>

<body data-spy="scroll" data-target=".navbar-collapse">

   <div class="culmn">
      <!--Home page style-->

      <!-- Top jsp -->
      <nav
         class="navbar navbar-light navbar-expand-lg  navbar-fixed ivory no-background bootsnav">
         <jsp:include page="./WEB-INF/include/Top.jsp"></jsp:include>


         <!-- Side jsp -->
         <jsp:include page="./WEB-INF/include/Side.jsp"></jsp:include>
      </nav>
      <!--Join Sections-->
      <section id="id" class="about roomy-100">
         <div class="container">
            <div class="about_content">
            <h1 class="h3 mb-2 text-gray-800">회원 관리</h1>
            <br>
               <div class="row">
                  <div class="col-md-12">
                  <jsp:include page="./WEB-INF/tables/EmpList.jsp"></jsp:include>
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
      <jsp:include page="./WEB-INF/include/Bottom.jsp"></jsp:include>
   </div>

   <!-- JS includes -->
   <jsp:include page="./js/js.jsp"></jsp:include> 
</body>
</html>