<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8">
    <title>Drop 4our bit</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <jsp:include page="./css/css.jsp"></jsp:include>

    <style type="text/css">
        .home {
            background: url(./images/home.jpg) no-repeat bottom center !important;
            background-size: cover;
            position: relative;
        }

        .input-group {
            height: 45px;
        }
    </style>


</head>

<body data-spy="scroll" data-target=".navbar-collapse">


    <!-- Preloader -->
    <div id="loading">
        <div id="loading-center">
            <div id="loading-center-absolute">
                <div class="object" id="object_one"></div>
                <div class="object" id="object_two"></div>
                <div class="object" id="object_three"></div>
                <div class="object" id="object_four"></div>
            </div>
        </div>
    </div>
    <!--End off Preloader -->
    <div class="culmn">
    
        <!-- Top jsp -->
        <nav class="navbar navbar-light navbar-expand-lg  navbar-fixed white no-background bootsnav">
            <jsp:include page="./WEB-INF/include/Top.jsp"></jsp:include>
            <!-- Start Side Menu -->
            <jsp:include page="./WEB-INF/include/Side.jsp"></jsp:include>
            <!-- End Side Menu -->

        </nav>
        <!--Home Sections-->
        <jsp:include page="./WEB-INF/include/Home.jsp"></jsp:include>

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