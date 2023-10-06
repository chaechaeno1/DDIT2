<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 상대경로 -->
<img src="../../images/꽃.jpg" width="200"><br><br>

<img src="<%=request.getContextPath()%>/images/꽃.jpg" width="200"><br><br>

<img src="<%=request.getContextPath()%>/images/imageView.do?fileno=3" width="200"><br><br>




</body>
</html>