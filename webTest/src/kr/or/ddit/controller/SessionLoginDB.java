package kr.or.ddit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class SessionLoginDB
 */
@WebServlet("/sessionLoginDB.do")
public class SessionLoginDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMemberService service;
	
	public SessionLoginDB() {
		service=MemberServiceImpl.getInstance();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 입력한 회원 ID와 패스워드를 받는다.
		request.setCharacterEncoding("utf-8");
		
		String userID = request.getParameter("userid");
		String pass = request.getParameter("pass");
		
		// 입력받은 데이터를 VO에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(userID);
		memVo.setMem_pass(pass);
		
		//Service에게 VO데이터를 주고 결과를 받아온다.
		MemberVO loginMemberVo = service.getLoginMember(memVo);
		
		HttpSession session = request.getSession();
		//받은 결과를 이용하여 로그인 여부를 검사
		//	==> 받은 결과가 null이 아니면 로그인 성공
		if(loginMemberVo!=null) { //로그인 성공이면...
			// 세션에 로그인 회원 정보를 저장한다.
			session.setAttribute("loginMember", loginMemberVo); 
			
		}
		
		// 로그인 페이지로 이동한다.
		response.sendRedirect(request.getContextPath()+"/basic/session/dbLogin/sessionLoginDB.jsp");
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
