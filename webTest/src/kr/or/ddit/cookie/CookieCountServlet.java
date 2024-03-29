package kr.or.ddit.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
				
		// 쿠키이름 'cnt'
		
		// 'cnt'라는 쿠키가 있는지 검사
		Cookie[] cookieArr = request.getCookies();
		
		int cnt = 0; //읽어온 쿠키값이 저장될 변수 
		
		if(cookieArr != null) {
			for(Cookie cookie : cookieArr) {
				if("cnt".equals(cookie.getName())) { // 'cnt'쿠키이름 찾기
					String value = cookie.getValue(); //현재의 쿠키값(cnt) 구하기
					cnt = Integer.parseInt(value); //현재값
				}
			}
		}
		cnt++; // cnt값 증가
		

		//쿠키 객체 생성
		Cookie cntCookie = new Cookie("cnt", String.valueOf(cnt));
		//response객체 -> 웹브라우저 
		response.addCookie(cntCookie);
		
		//출력
		
		out.println("<html>");
		out.println("<head><meta charset-'utf-8'><title> 쿠키 카운트 </title></head>");
		out.println("<body>");
		
		
		out.println("<h3> 어서오세요. 당신은 "+ cnt +"번째 방문입니다.</h3><br>");
		
		out.println("<a href='"+ request.getContextPath()+"/cookieCountServlet.do'>카운트 증가하기</a>"); //현재 페이지에서 카운트 증가하기(/cookieCountServlet.do)
		
		out.println("<a href='"+ request.getContextPath()+"/basic/cookie/cookieTest02.jsp'>시작 문서로 가기</a>");		
		out.println("</body></html>");
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
