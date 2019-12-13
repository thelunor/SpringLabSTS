<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 이름 검색</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax(//parameter json 객체
				{
					url: "/SearchEname?eName=",
					type: "POST", 
					dataType : "json", 
					success : function(data){ 
		            data:{eName:$("#id").val()},						
						$.each(data,function(index,obj){
							$('#treedata').append(obj.id + " / " + obj.name + " / " + obj.title + " / " + obj.content + "<br>");	
						});
					}, 
					error : function(xhr){ 
						alert(xhr.status); 
					}
				
				}
		
		);
	});
</script>
</head>
<body>

</body>
</html>