package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectTest
 */
@WebServlet("/redirectTest.do")
public class RedirectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		//파라미터 값 구하기
		String userName = request.getParameter("username");

		//setAttribute()메서드로 보낸 데이터 구하기 (형변환 필수)
		//String tel = (String) request.getAttribute("tel");
		String tel = request.getParameter("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Redirect 연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>Forward 결과</h3>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>"+userName+"</td></tr>");
		
		out.println("<tr><td>전화</td>");
		out.println("<td>"+tel+"</td></tr>");
		
		out.println("</table>");
		out.println("</body></html>");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
