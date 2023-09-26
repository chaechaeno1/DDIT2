package kr.or.ddit.reqNresp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponserTest02
 */
@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 - redirect
	 	다른 문서로 이동한다. (직접 파라미터를 넘길 수 없다.)
	 	응답 시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 해당 URL로
	 	재 요청하는 방식 (이때 GET방식으로 요청하기 때문에 URL주소의 길이에 제한이 있다.)
	 	
	 	사용은 HttpServeltResponse객체의 sendRedirect()메서드를 이용한다.
	 	
	 	
	 	
	 */
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
