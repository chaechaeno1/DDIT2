package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		//ArrayList의 기본적인 사용법은 Vector와 같음
		
		ArrayList list1 = new ArrayList();
		
		//-----------------------------------------------
		//add()메서드로 데이터 추가
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(false);
		list1.add(12.34);
		
		System.out.println("size : "+list1.size());
		System.out.println("list1 ==> "+list1);
		
		//-----------------------------------------------
		//get()메서드를 이용해서 데이터 꺼내오기
		System.out.println("1번째 자료 : "+list1.get(1));
		
		//-----------------------------------------------
		//데이터 끼워넣기
		list1.add(3,"ZZZ");
		System.out.println("list1 ==> "+list1);
		
		//-----------------------------------------------
		//데이터 변경하기
		String sTemp = (String)list1.set(3, "yyy");
		list1.set(3,"yyy");
		System.out.println("list1 ==> "+list1);
		System.out.println("sTemp ==> "+sTemp);
		
		//-----------------------------------------------
		//데이터 삭제하기
		list1.remove(3);
		System.out.println("3번재 자료 삭제 후 list1 ==>" + list1);
		
		list1.remove("bbb");
		System.out.println("bbb 자료 삭제 후 list1 ==>" + list1);
		System.out.println("-----------------------------------------------");
		
		//-----------------------------------------------
		//제네릭 사용
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		System.out.println("***일반 for문***");
		for(int i=0; i<list2.size();i++) {
			System.out.println(i+" ==> "+list2.get(i));
		}
		
		System.out.println("-----------------------------------------------");
		System.out.println("***향상된 for문***");
		for(String str : list2) {
			System.out.println(str);
		}
		
		System.out.println("-----------------------------------------------");
		
		//-----------------------------------------------
		//contains(비교객체) ==> 리스트에 저장된 데이터 중에서 '비교객체'가 있으면 true, 없으면 false

		System.out.println("DDDD 데이터의 존재 여부 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ 데이터의 존재 여부 : " + list2.contains("ZZZZ"));
		
		
	}

}
