package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();

		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");

		System.out.println("정렬 전 : " + list);

		// 정렬은 Collections.sort()메서드를 이용하여 정렬
		// Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬
		// 내부 정렬 기준 -> list가 가지고 있는 데이터의 정렬기준
		// 여기서는 String의 정렬 기준을 따른다.
		// 기본적으로 오름차순 정렬.

		Collections.sort(list);
		System.out.println("정렬 후 : " + list);
		
		Collections.shuffle(list); //자료 섞기
		System.out.println("자료 섞기 후 : "+list);
		
		//외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("정렬 후 : " + list);

	}

}

/*
 * 정렬(sort)과 관련된 interface는 comparable, Comparator 이렇게 두 가지가 있다.
 * 
 * - Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 때(내부 정렬 기준) 구현하는 인터페이스 -
 * Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때(외부 정렬 기준) 구현하는 인터페이스
 * 
 * - Comparable에서는 compareTo()메서드를 재정의하고 - Comparator에서는 compare()메서드를 재정의
 * 
 * - String클래스, Wrapper클래스, Date클래스, File클래스 등에는 이미 내부 정렬 기준이 구현되어 있음 (8가지
 * 기본자료형을 객체화 -> Wrapper클래스, 내부 정렬 기준은 오름차순으로 처리하도록 구현)
 */

//정렬 방식을 정해주는 class를 작성 (=외부 정렬 기준 클래스)
//Comparator 인터페이스를 구현해서 작성

class Desc implements Comparator<String> {
	// compare()메서드의 매개변수는 서로 인접한 2개의 데이터가 저장된다고 가정하여 처리

	// compare()메서드의 반환값의 의미
	// 반환값이 0 : 두 값이 같다. (순서는 바뀌지 않음)
	// 반환값이 '양수' : 앞,뒤의 순서 바꿈
	// 반환값이 '음수' : 순서 바꾸지 않음

	// 예) 오름차순 정렬 ==> 앞의 값이 크면 양수 반환, 같으면 0, 앞의 값이 작으면 음수 반환하도록 구현

	@Override
	public int compare(String str1, String str2) {
		// "내림차순"으로 구현
		// String은 내부 정렬기준(오름차순)이 이미 있음
		if (str1.compareTo(str2) > 0) {
			return -1; // 음수 반환
		} else if (str1.compareTo(str2) < 0) { //
			return 1; // 양수 반환
		} else {
			return 0;
		}
		

	}
}
