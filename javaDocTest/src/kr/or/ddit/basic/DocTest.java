package kr.or.ddit.basic;

public class DocTest {

	public static void main(String[] args) {
		System.out.println("test");
		
		JavaDocTest test = null;

		test.methodAdd(1, 2);
		test.methodSub();
		
		//MVC패턴에서 사용하는 클래스들 -- 역할과 쓰임
		//1) VO 또는 DTO객체 ( 무슨 용도로 사용하는 객체인지? ) 
		//2) DAO 객체 
		//3) Service객체
		//위 객체들의 역할(쓰임새)에 대하여 공부하기
		
		//데이터 뿐만 아니라 로직까지 모델에 포함
		//
	}

}
