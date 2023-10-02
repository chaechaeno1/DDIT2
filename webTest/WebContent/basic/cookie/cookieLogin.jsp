<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie Login</title>

<%
//쿠키에 저장된 ID값을 구한다.
//JSP에서 만들어진 영역
String cookieUserId = "";
String chkBox = "";

Cookie[] cookieArr = request.getCookies();
if (cookieArr != null) {
	for (Cookie cookie : cookieArr) {
		if ("USERID".equals(cookie.getName())) { //원하는 쿠키 이름을 찾는다.
	cookieUserId = cookie.getValue(); //쿠키값을 저장한다.
	chkBox = "checked";
		}
	}
}
%>



</head>
<body>
	<form action="<%=request.getContextPath()%>/cookieLoginServlet.do"
		method="post">

		<table style="margin: 0 auto;">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="userid" placeholder="ID 입력하세요."
					value="<%=cookieUserId%>"></td>
			</tr>

			<tr>
				<td>PASS :</td>
				<td><input type="password" name="pass"
					placeholder="PassWord 입력하세요."></td>
			</tr>

			<tr>
				<td colspan="2"><input type="checkbox" name="chkid" <%=chkBox%>>
					ID 기억하기</td>
			</tr>

			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="Login"></td>
			</tr>


		</table>



	</form>
</body>
</html>