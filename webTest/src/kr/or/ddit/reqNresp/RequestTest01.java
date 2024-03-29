package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest01
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/requestTest01.do" })
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//POST방식으로 전달되는 데이터의 문자 인코딩 방식 설정
			request.setCharacterEncoding("utf-8");
			
			//클라이언트에서 보내온 데이터 받기
			//Request객체의 getParameter()메서드 이용
			//형식) Request객체.getParameter("파라미터명");
			//		==> 해당 '파라미터명'에 설정된 '값'을 가져온다.
			//		==> 가져오는 '값'의 자료형은 'String'이다.
			String userName = request.getParameter("username");
			//<td><input type="text" size="10" name="username"></td>에서 name과 맞춰주면 됨
			String job = request.getParameter("job");
			
			// 같은 '파라미터명'이 여러개 일 경우에는 Request객체의 getParametherValues()메서드를 이용
			// 형식) Request객체.getParameterValues("파라미터명")
			//		==> 가져오는 '값'의 자료형은 String배열('String[]')이다.
			
			String[] hobbies = request.getParameterValues("hobby");
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head><meta charset='utf-8'><title>Request객체 연습</title></head>");
			out.println("<body>");
			out.println("<h3>Request객체 테스트 결과</h3>");
			out.println("<table border='1'>");
			out.println("<tr><td>이름</td>");
			out.println("<td>"+userName+"</td></tr>");
			out.println("<tr><td>직업</td>");
			out.println("<td>"+job+"</td></tr>");
			out.println("<tr><td>취미</td>");
			out.println("<td>");
			if(hobbies==null) {
				out.println("취미가 없습니다...");
			}else {
				for(String hobby : hobbies) {
					out.println(hobby + "<br>");
				}
			}
			
			out.println("</td></tr>");
			out.println("</table>");
			
			out.println("<br><hr><br>");
			out.println("<h2>Request객체의 메서드</h2>");
			out.println("<ol>");
			out.println("<li>클라이언트의 IP주소 :"+ request.getRemoteAddr()+"</li>");
			out.println("<li>요청 메서드 : "+request.getMethod()+"</li>");
			out.println("<li>ContextPath : "+request.getContextPath()+"</li>");
			out.println("<li>프로토콜 : "+request.getProtocol()+"</li>");
			out.println("<li>URL정보 :"+request.getRequestURL()+"</li>");
			out.println("<li>URI정보 :"+request.getRequestURI()+"</li>");
			out.println("</ol>");
			
			
			
			out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
