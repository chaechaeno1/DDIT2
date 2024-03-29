package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		// ArrayList의 기본적인 사용법은 Vector와 같음

		ArrayList list1 = new ArrayList();

		// -----------------------------------------------
		// add()메서드로 데이터 추가
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(false);
		list1.add(12.34);

		System.out.println("size : " + list1.size());
		System.out.println("list1 ==> " + list1);

		// -----------------------------------------------
		// get()메서드를 이용해서 데이터 꺼내오기
		System.out.println("1번째 자료 : " + list1.get(1));

		// -----------------------------------------------
		// 데이터 끼워넣기
		list1.add(3, "ZZZ");
		System.out.println("list1 ==> " + list1);

		// -----------------------------------------------
		// 데이터 변경하기
		String sTemp = (String) list1.set(3, "yyy");
		list1.set(3, "yyy");
		System.out.println("list1 ==> " + list1);
		System.out.println("sTemp ==> " + sTemp);

		// -----------------------------------------------
		// 데이터 삭제하기
		list1.remove(3);
		System.out.println("3번재 자료 삭제 후 list1 ==>" + list1);

		list1.remove("bbb");
		System.out.println("bbb 자료 삭제 후 list1 ==>" + list1);
		System.out.println("-----------------------------------------------");

		// -----------------------------------------------
		// 제네릭 사용

		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");

		System.out.println("***일반 for문***");
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " ==> " + list2.get(i));
		}

		System.out.println("-----------------------------------------------");
		System.out.println("***향상된 for문***");
		for (String str : list2) {
			System.out.println(str);
		}

		System.out.println("-----------------------------------------------");

		// -----------------------------------------------
		// contains(비교객체) ==> 리스트에 저장된 데이터 중에서 '비교객체'가 있으면 true, 없으면 false

		System.out.println("DDDD 데이터의 존재 여부 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ 데이터의 존재 여부 : " + list2.contains("ZZZZ"));

		// -----------------------------------------------
		// indexOf(비교객체)
		// lastIndexOf(비교객체) ==> 리스트에 '비교객체'가 있으면 '비교객체'가 저장된 index값을 반환, 없으면 -1 반환

		// indexOf()메서드는 앞에서 뒤쪽 방향으로 검색, lastIndexOf()메서드는 뒤에서 앞쪽 방향으로 검색
		// 찾아진 데이터가 많으면 첫번째로 찾은 데이터의 위치(index)값을 반환

		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");

		System.out.println("list2 => " + list2);
		System.out.println("DDDD의 위치값 : " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 위치값 : " + list2.indexOf("ZZZZ"));
		System.out.println("DDDD의 위치값 : " + list2.lastIndexOf("DDDD"));
		System.out.println("-----------------------------------------------");

		// -----------------------------------------------
		// toArray() ==> 리스트 안의 데이터를 배열로 변환하여 반환
		// ==> 반환되는 배열은 기본적으로 Object형 배열

		// toArray(new 제네릭타입이름[0]) ==> 제네릭타입의 배열로 변환해서 반환

		Object[] strArr = list2.toArray();
		// String[] strArr = (String[])list2.toArray(); -> Object[]을 String[]으로 변환 불가
		System.out.println("list의 개수 : " + list2.size());
		System.out.println("배열의 개수 : " + strArr.length);
		System.out.println("-----------------------------------------------");
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(i + "번째 자료 ==> " + strArr[i]);
		}
		System.out.println("-----------------------------------------------");

		// 제네릭 타입의 배열로 변환해서 가져오기
		// ArrayList<String> list2 = new ArrayList<String>(); 으로 만들어줬었음
		String[] strArr2 = list2.toArray(new String[0]);

		for (String s : strArr2) {
			System.out.println(s);
		}

	}
}
