package kr.or.ddit.basic;
// JavaDoc 문서 파일 만들기 예제



/**
 * 
 * @author PC-09
 * @version 1.0
 * 
 *  <p>
 *  파일명 : JavaDocTest.java<br>
 *  설   명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 *  
 *  변경 이력<br>
 *  --------------------------------------------<br>
 *  변경일자 : 2023-9-15<br>
 *  변경자    : 홍길동 <br>
 *  변경내용 : 최초 작성<br>
 *  --------------------------------------------<br>
 *  </p>
 *
 */
public interface JavaDocTest {
	/**
	 * 메서드명 : methodTest<br>
	 * 설      명 : 반환값이 없는 메서드<br>
	 * 
	 * @param a 첫번째 매개변수(정수형)
	 * @param b 두번째 매개변수(정수형)
	 */
	
	public void methodTest(int a, int b);
	
	/**
	 * 메서드명 : methodAdd<br>
	 * 설      명 : 반환값이 있는 메서드<br>
	 * 
	 * @param x
	 * @param y
	 * 
	 * @return 처리된 결과를 정수형으로 반환한다.
	 */
	
	public int methodAdd(int x, int y);
	
	/**
	 * 메서드명 : methodSub<br>
	 * 설      명 : 매개변수가 없고 반환값이 있는 메서드
	 * 
	 * @return 정수형으로 반환한다.
	 */
	
	public int methodSub();
	
	

}
