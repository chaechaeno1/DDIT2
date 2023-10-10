package kr.or.ddit.mymember.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.fileupload.service.IFileinfoService;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.MemberVO;

/**
 * Servlet implementation class MemberList
 */
@WebServlet("/memberList.do")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IMemberService service = MemberServiceImpl.getInstance();
		
		//전체 목록 가져오기
		List<MemberVO> mymemberList = service.selectAllMember();
	
		//가져온 전체 목록을 View로 보낸다.
		request.setAttribute("mymemberList", mymemberList);
	
		request.getRequestDispatcher("/basic/mymember/mymemberList.jsp").forward(request, response);	
	}
		
		
		
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
