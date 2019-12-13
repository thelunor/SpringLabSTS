<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userid") == null || !session.getAttribute("userid").equals("admin")) {
		 out.print("<script>location.href='Login_form.jsp'</script>");
	}
%>