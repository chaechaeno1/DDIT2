package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : "+ v1.size());
		// 배열의 크기는 배열명.length()
		
		// ----------------------------------------------------------
		// 데이터 추가하기1 : add(추가할 데이터)
		// 반환값 : 추가 성공(true), 추가 실패(false)
		
		v1.add("aaaa");
		// 컬렉션은 객체만 저장가능, "aaaa"는 String 객체
		v1.add(new Integer(111));
		//111 자체는 객체가 아님, 그러나 요즘에는 래퍼클래스 사용하지 않고도 사용가능
		v1.add(123);
		//오토 박싱, 오토 언박싱이 지원됨
		v1.add('a');
		v1.add(true);
		
		boolean r = v1.add(3.14);
		
		System.out.println("현재 크기 : " +v1.size());
		System.out.println("r의 반환값 : "+r);
		
		// ----------------------------------------------------------
		// 데이터 추가하기2 : addElement(추가할 데이터)
		// ==> 이전 버전의 프로그램과 호환성을 위해서 남아있는 메서드
		
		v1.addElement("CCC");
		System.out.println("v1 ==> "+v1.toString());
		//출력시 toString() 생략 가능
		System.out.println("v1 ==> "+v1);
		
		// ----------------------------------------------------------
		// 데이터 추가하기3 : add(index, 추가할데이터)
		// ==> 'index'번째에 '추가할 데이터'를 끼워넣기
		// ==> 'index'는 0부터 시작
		// ==> 반환값 없음
		
		v1.add(1, "KKK");
		System.out.println("v1 ==> "+v1);
		
		// ----------------------------------------------------------
		// 데이터 꺼내오기 : get(index)
		// ==> 'index'번째의 데이터를 꺼내서 반환
		
		System.out.println("0번째 데이터 : "+v1.get(0));
		
		// v1.add(new Integer(111)); --> 원본은 integer지만 Object로 감싸져있는 상태
		//자식객체 -> 부모객체 자동형변환
		//부모객체 -> 자식객체에 형변환 필수 
		
		//Integer iTemp = (Integer)v1.get(2);
		int iTemp = (int)v1.get(2);		
		System.out.println("2번째 데이터 : "+iTemp);
		
		// ----------------------------------------------------------
		// 데이터 수정(변경)하기 : set(index, 새로운데이터)
		// ==> 'index'번째의 데이터를 '새로운데이터'로 변경
		// ==> 반환값 : 변경되기 전의 원래의 데이터가 반환
		
		String sTemp = (String)v1.set(0, "zzz");
		System.out.println("v1 ==> "+v1);
		System.out.println("원래의 데이터 : "+sTemp);
		
		// ----------------------------------------------------------
		// 데이터 삭제하기1 : remove(index)
		// ==> 'index'번째의 데이터 삭제
		// ==> 반환값 : 삭제된 데이터
		
		v1.remove(0);
		System.out.println("삭제 후 v1 ==> "+v1);
		
		sTemp = (String)v1.remove(0);
		System.out.println("삭제 후 v1 ==> "+v1);
		System.out.println("삭제된 데이터 : "+sTemp);

		// ----------------------------------------------------------
		// 데이터 삭제하기2 : remove(삭제할데이터)
		// ==> '삭제할데이터'를 찾아서 삭제 
		// ==> '삭제할데이터'가 여러개이면 이 중에 제일 첫번째 자료가 삭제
		// ==> 반환값 : 삭제성공(true), 삭제실패(false)
		// ==> '삭제할데이터'가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환하여 사용
		
		v1.remove("CCC");
		System.out.println("CCC 삭제 후 v1 ==> "+v1);
		
		//v1.remove(123); 
		//123을 index로 인식 --> 오류발생(Array index out of range: 123)
		
		//v1.remove(new Integer(123)); //방법1 ==> 자바버전 1.9이상에서는 사용 불가
		v1.remove(Integer.valueOf(123)); //방법2
		System.out.println("123 삭제 후 v1 ==> "+v1);
		
		//v1.remove('a'); --> 오류발생(Array index out of range: 97)
		//문자형이 숫자로 변환되어 a -> 97로 인식
		
		v1.remove(Character.valueOf('a'));
		System.out.println("a 삭제 후 v1 ==> "+v1);
		
		v1.remove(3.14);
		System.out.println("3.14 삭제 후 v1 ==> "+v1);
		
		v1.remove(true);
		System.out.println("true 삭제 후 v1 ==> "+v1);
		
		// ----------------------------------------------------------		
		// 전체 데이터 삭제 : clear();
		v1.clear();
		System.out.println("clear() 후 v1 ==> "+v1);
		System.out.println("현재 크기 : "+v1.size());
		System.out.println();
		
		// ----------------------------------------------------------		
		/**
		 * 
		 */
		
		
		
	}

}
