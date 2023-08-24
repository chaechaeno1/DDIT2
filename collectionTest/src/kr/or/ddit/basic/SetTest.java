package kr.or.ddit.basic;

import java.util.HashSet;

public class SetTest {

	public static void main(String[] args) {
	/*
	 - List와 set의 차이점
	 1. List
	 	-데이터의 순서(index)가 있다.
	 	-중복되는 데이터를 저장할 수 있다.
	 	
	 2. set
	 	-데이터의 순서(index)가 없다.
	 	-중복되는 데이터를 저장할 수 없다.
	 */
		
		HashSet hs1 = new HashSet();
		
		//set 데이터 추가 ==> add()
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("set 데이터 ==>" + hs1.toString());
		//toString()생략 가능
		System.out.println("set 데이터 ==>" + hs1);
		// set 데이터 ==>[DD, AA, CC, BB, 1, 2, 3]
		// 입력 순서와 출력 순서가 일치하지 않음
		
		
		
	}

}
