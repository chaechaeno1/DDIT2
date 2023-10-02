package kr.or.ddit.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLoginServlet
 */
@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//userid, pass, chkid 파라미터의 값을 구한다.
		String userId = request.getParameter("userid");
		String pass = request.getParameter("pass");
		String chkId = request.getParameter("chkid"); //checkbox의 값
		
		//userid값을 갖는 Cookie객체 생성
		Cookie loginCookie = new Cookie("USERID", userId);
		
		System.out.println("CheckBox의 체크 여부 : "+chkId);
		
		if(chkId == null) { //체크박스가 체크되지 않으면 ...
			loginCookie.setMaxAge(0); //Cookie를 삭제하기 위해서 유지시간을 0으로 설정
		}
	
		// 쿠키를 다시 저장한다.
		response.addCookie(loginCookie);
		
		//ID가 'test'이고, PassWord가 '1234'이면 로그인에 성공한 것으로 보고 'cookieMain.jsp'로 이동하고
		//그렇지 않으면 'cookieLogin.jsp'로 이동되도록 한다. ==> redirect로 이동...
		
		String contextPath = request.getContextPath();
		
		if("test".equals(userId) && "1234".equals(pass)) { //로그인 성공 시
			response.sendRedirect(contextPath+"/basic/cookie/cookieMain.jsp");
		}else { // 로그인 실패 시 
			response.sendRedirect(contextPath+"/basic/cookie/cookieLogin.jsp");
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
