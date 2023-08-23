package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;

public class ListSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : "+list);
		
		//정렬은 Collections.sort()메서드를 이용하여 정렬
		//Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬
		//내부 정렬 기준 -> list가 가지고 있는 데이터의 정렬기준
		//여기서는 String의 정렬 기준을 따른다.
		//기본적으로 오름차순 정렬.
		
		Collections.sort(list);
		System.out.println("정렬 후: "+list);
		
	}

}
