<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<! DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>
			<!-- title 영역 -->
			<tiles:getAsString name="title"/>
		</title>
			<!-- css 설정 영역  -->
		    <!-- <link href="login.css" type="text/css" rel="stylesheet" /> -->
		<link href='<tiles:getAsString name="css"/>' type="text/css" rel="stylesheet" />
		<script src="http://code.jquery.com/jquery-2.2.4.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#btnCheckUid').click(function(){
					if($('#userid').val() === ""){
						alert("아이디를 입력하세요");
						$('#userid').focus();
					} else {
						$.ajax(
								{
									type:"post",
									url:"idCheck.htm",
									data:{"userid" : $('#userid').val()},
									success:function(data){ //{"result":""}
										if(data.result=="Fail"){
											alert('중복된 아이디 입니다.');
											$('#userid').focus();
											$('#userid').val("");
										} else {
											alert('사용가능한 아이디 입니다.');
											$('#pwd').focus();
										}
									}
								}	
						);
					}
					
				});
			});
		</script>
	</head>
	<body>
		<!-- Header 영역 -->
		<tiles:insertAttribute name="header" />
		<!-- Visual 영역 -->
		<tiles:insertAttribute name="visual" />
		<div id="main">
			<div class="top-wrapper clear">
				<!-- Content 영역 -->
				<tiles:insertAttribute name="content" />
				<!-- Aside(navi) 영역 -->
				<tiles:insertAttribute name="aside" />
			</div>
		</div>
		<!-- Footer 영역 -->
		<tiles:insertAttribute name="footer" />
	</body>
</html>
