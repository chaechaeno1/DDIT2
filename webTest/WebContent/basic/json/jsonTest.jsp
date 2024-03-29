<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	// 문자열 처리
	$("#strBtn").on("click",function(){
		$.ajax({
			//요청
			url: "<%=request.getContextPath()%>/json/jsonTest.do", 
			type: "post",
			data: "choice=string", //choice변수, String값
			
			//응답
			success: function(data){
				//data ="안녕하세요..."
				let htmlCode = "<h3>문자열 응답 결과</h3>";
				htmlCode += data;
				$("#result").html(htmlCode);
						
			},
			dataType: "json"
			
		});
	
	});
	
	// 배열 처리
	$("#arrBtn").on("click",function(){
		$.ajax({
			//요청
			url: "<%=request.getContextPath()%>/json/jsonTest.do", 
			type: "post",
			data: "choice=array", //choice변수, drray값
			
			//응답
			success: function(data){
				//data = [10,20,30,40,50]; <<[]는 배열
				let htmlCode = "<h3>배열 응답 결과</h3>";
				$.each(data, function(i,v){
					htmlCode += i + "번째 데이터: " + v + "<br>"
					
				});
				
				$("#result").html(htmlCode);
						
			},
			dataType: "json"
			
		});
	
	});
	
	//객체 처리하기
	$("#objBtn").on("click",function(){
		$.ajax({
			//요청
			url: "<%=request.getContextPath()%>/json/jsonTest.do", 
			type: "post",
			data: "choice=object", //choice변수
			
			//응답
			success: function(data){
				//data = {"num":1,"name":"홍길동"} <<{}는 객체 
				let htmlCode = "<h3>객체 응답 결과</h3>";
				
				htmlCode += "num : "+data.num + "<br>";
				htmlCode += "name : "+data.name + "<br>";
				$("#result").html(htmlCode);
						
			},
			dataType: "json"
			
		});
	
	});
	
	//리스트 처리하기
	$("#listBtn").on("click",function(){
		$.ajax({
			//요청
			url: "<%=request.getContextPath()%>/json/jsonTest.do", 
			type: "post",
			data: "choice=list", //choice변수
			
			//응답
			success: function(data){
				//data = [
				//			{"num":10,"name":"강감찬"},
				//			{"num":20,"name":"이순신"},
				//			{"num":30,"name":"을지문덕"}
				//		]
				let htmlCode = "<h3>LIST 응답 결과</h3>";
				
				//반복문 사용
				$.each(data, function(i,v){
					htmlCode += i +"번째 자료<br>";
					htmlCode += "num : "+v.num+"<br>";
					htmlCode += "name : "+v.name+"<hr>";
				});
				
				$("#result").html(htmlCode);
						
			},
			dataType: "json"
			
		});
	
	});
	
	//MAP 처리하기
	$("#mapBtn").on("click",function(){
		$.ajax({
			//요청
			url: "<%=request.getContextPath()%>/json/jsonTest.do", 
			type: "post",
			data: "choice=map", //choice변수
			
			//응답
			success: function(data){
				//data = {"name":"성춘향","tel":"001-1234-5678","addr":"대전시 중구 오류동"}
				let htmlCode = "<h3>MAP 응답 결과</h3>";
				
				/* htmlCode += "name : "+data.name + "<br>";
				htmlCode += "tel : "+data.tel + "<br>";
				htmlCode += "addr : "+data.addr + "<br>"; */
				
				$.each(data,function(i,v){
					htmlCode += i + " : " + v + "<br>";
				});
				
				$("#result").html(htmlCode);
						
			},
			dataType: "json"
			
		});
	
	});
	
	

});


</script>

</head>
<body>

<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrBtn" value="배 열">
	<input type="button" id="objBtn" value="객 체">
	<input type="button" id="listBtn" value="리스트">
	<input type="button" id="mapBtn" value="Map객체">

</form>
<div id="result"></div>

</body>
</html>