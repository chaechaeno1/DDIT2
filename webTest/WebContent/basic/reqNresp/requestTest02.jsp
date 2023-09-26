<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
h3{
	text-align: center;
}
</style>
</head>
<body>

<h3> requestTest02.jsp </h3>
<br><hr><br>

<h2>Request연습 Form(숫자 입력은 정수형으로 입력하세요.)</h2>
<br><hr><br>

<form action="<%=request.getContextPath()%>/requestTest02.do" method="post">
<!--  
<form action="/webTest/requestTest02.do" method="post">
-->
<input type="text" size="10" name="num1">
<select	name="cal">
			<option value="+">+</option>
			<option value="-">-</option>
			<option value="*">*</option>
			<option value="/">/</option>
			<option value="%">%</option>
		</select>
<input type="text" size="10" name="num2">
<input type="submit" value="확인">
</form>



</body>
</html>