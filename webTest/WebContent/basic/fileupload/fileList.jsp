<%@page import="kr.or.ddit.vo.FileInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
//서블릿(controller)에서 보내온 자료 받기
List<FileInfoVO> fileList = (List<FileInfoVO>)request.getAttribute("fileList");

%>


<body>
<h2>전체 파일 목록</h2><br><hr><br>

<a href="<%=request.getContextPath()%>/fileUpload.do">파일업로드</a>

<table border="1">
	<thead>
		<tr>
			<th>번호</th><th>작성자</th><th>저장 파일명</th>
			<th>원본 파일명</th><th>파일 크기</th><th>날짜</th><th>비고</th>
		</tr>
	</thead>
	<tbody>
	<%
	if(fileList==null || fileList.size()==0){
	%>	
	<tr>
		<td colspan="7">파일 목록이 하나도 없습니다...</td>
	</tr>
	<%	
	}else{
		for(FileInfoVO fvo : fileList){
	%>		
		<tr>
			<td><%=fvo.getFile_no()%></td>
			<td><%=fvo.getFile_writer() %></td>
			<td><%=fvo.getSave_file_name() %></td>
			<td><%=fvo.getOrigin_file_name() %></td>
			<td><%=fvo.getFile_size() %></td>
			<td><%=fvo.getFile_date() %></td>
			<!-- 띄어쓰기있으면 안됨!! ▽ -->
			<td> <a href="<%=request.getContextPath()%>/fileDownload.do?fileno=<%=fvo.getFile_no()%>">DownLoad</a> </td>
		</tr>
	
	<%
		}
	}
	%>
	
	</tbody>
	
</table>


</body>
</html>