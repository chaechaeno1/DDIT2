<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	// JSP문서에서 HttpSession객체는 'session'이라는 이름으로 이미 생성되어 있다.

// 세션에 저장된 '로그인 정보' 가져오기
String loginId = (String) session.getAttribute("LOGINID");
%>

</head>
<body>
	<%
		if (loginId == null) { //로그인이 안되었을 때...
	%>
	<form action="<%=request.getContextPath()%>/sessionLogin.do"
		method="post">

		<table style="margin: 0 auto;" border="1">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="userid" placeholder="ID 입력하세요.">
				</td>
			</tr>

			<tr>
				<td>PASS :</td>
				<td><input type="password" name="pass"
					placeholder="PassWord 입력하세요."></td>
			</tr>

			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="Login"></td>
			</tr>


		</table>

	</form>
	<%
		} else { //로그인 되었을 때...
	%>
	<h2><%=loginId%>님 반갑습니다.
	</h2>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/sessionLogout.do">로그아웃</a>

	<%
		}
	%>


</body>
</html>