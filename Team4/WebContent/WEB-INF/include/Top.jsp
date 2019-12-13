<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container">
    <div class="attr-nav">
        <ul>
            <li class="login">
                <%
            if(session.getAttribute("id") != null){
                out.print("<a href ='logout.d4b'>로그아웃</a>");
            }else{
                out.print("<a href ='loginok.d4b'>로그인</a>");
            }	
            %>
            </li>

            <li class="mypage">
                <%
                String id = null;
                id = (String)session.getAttribute("userid");
                if(id != null){
                    out.print("");
                }else{
                        out.print("<a href='#'>마이페이지</a>");
                }
            %>
            </li>
            <li class="side-menu">
                <%
                String id2 = null;
                id2 = (String)session.getAttribute("id");
                if(id2 != null){
                    if(id2.equals("admin")){
                        out.print("<a href='#'><i class='fa fa-bars'></i></a>");
                    }
                }
            %>
            </li>
        </ul>
    </div>
    <div class="navbar-header">
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-menu"></button>

        <a class="navbar-brand" href="./Main.jsp"> <img src="./images/logo1.png" class="logo logo-display m-top-10"
                alt="">
            <img src="./images/logo.jpg" class="logo logo-scrolled" alt="">
        </a>
    </div>
</div>