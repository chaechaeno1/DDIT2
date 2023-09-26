package kr.or.ddit.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String num1s = request.getParameter("num1");
		String num2s = request.getParameter("num2");
		String cals = request.getParameter("cal");
		
		int num1 = Integer.parseInt(num1s);
		int num2 = Integer.parseInt(num2s);
		double res = 0; //결과 저장 변수
		
		/* switch문 작성시에 형변환 먼저 안해주고 case 시작해도됨...
		switch(cals) {
		case "+" : res = num1 + num2; break;
		case "-" : res = num1 - num2; break;
		case "*" : res = num1 * num2; break;
		case "/" : res = (double)num1 / num2; break;
		case "%" : res = num1 % num2; break;		
		}
		*/
		
		boolean calcOk = true;
		if(cals.equals("+")) {
			res = num1+num2;
		}else if(cals.equals("-")) {
			res = num1-num2;
		}else if(cals.equals("*")) {
			res = num1*num2;
		}else if(cals.equals("/")) {
			if(num2!=0) {
			res = (double)num1/num2;
			}else {
				calcOk = false;
			}
		}else if(cals.equals("%")) {
			if(num2!=0) {
			res = num1%num2;
			}else {
				calcOk = false;
			}
		}else {
			System.out.println("잘못입력");
			return;
		}
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charest=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Request연습 계산기</title></head>");
		out.println("<body>");
		out.println("<h3>requestTest02.jsp</h3>");
		out.println("<h3>(uri매핑 : "+request.getRequestURI()+")</h3>");
		out.println("<hr>");
		out.println("<h2> 계산 결과  </h2>");
		out.println("<hr>");
		out.printf("%d %s %d = ", num1, cals, num2);
		if(calcOk) { //계산성공
			out.println(res);
		}else { //계산실패
			out.println("계산 불능(0으로 나누기)");
		}
		//out.println(num1 +" "+ cals +" "+ num2 +" = "+ res);

		

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
