package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();

		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-1111-1111"));
		memList.add(new Member(9, "성춘향", "010-1111-1111"));
		memList.add(new Member(3, "강감찬", "010-1111-1111"));
		memList.add(new Member(6, "일지매", "010-1111-1111"));
		memList.add(new Member(2, "변학도", "010-1111-1111"));

		System.out.println("정렬 전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("============================================");

		// Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스를 이용하여 정렬하시오.
		// (클래스명 : SortNumDesc) ->> 하단에 클래스 추가
		// <Member>

		Collections.sort(memList, new SortNumDesc());
		//외부 정렬 기준을 주었기 때문에 오류가 없음

		System.out.println("num기준 내림차순 정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("============================================");

		Collections.sort(memList); 
		/*
		 	==> sort에 오류
		 	==> Member객체의 정렬 기준이 정해져있지 않음
		 	==> 내부 정렬기준을 만들어주면 오류 사라짐
		*/
		
		System.out.println("name기준 오름차순 정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("============================================");

	}

}

//Member의 회원이름(name)을 기준으로 오름차순이 되도록 내부 정렬 기준 추가하기
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;

	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	//내부 정렬 기준을 설정하는 메서드(name의 오름차순 정렬 기준 만들기)
	@Override
	public int compareTo(Member mem) {
		if(this.getName().compareTo(mem.getName()) > 0) {
			return 1;
		}else if(this.getName().compareTo(mem.getName()) <0) {
			return -1;
		}
		return 0;
	}

}

//Member의 num값의 "내림차순"으로 정렬하는 외부 정렬 기준 클래스
class SortNumDesc implements Comparator<Member> {

//	@Override
//	public int compare(Member mem1, Member mem2) {
//		return Integer.compare(mem2.getNum(), mem1.getNum());
		// Integer.compare(a, b)는 두 개의 정수를 비교하여 비교 결과를 반환하는 정적 메서드
		// 내림차순 정렬을 위해 (mem2.getNum(), mem1.getNum())
		// mem2가 앞으로 가도록 내림차순 정렬
//	}

	@Override
	public int compare(Member mem1, Member mem2) {
		// Member의 num값의 내림차순으로 정렬
//		if (mem1.getNum() > mem2.getNum()) {
//			return -1;
//		} else if (mem1.getNum() < mem2.getNum()) {
//			return 1;
//		} else {
//			return 0;
//		}
		
		//Wrapper클래스를 이용하는 방법1
		//내림차순 정렬을 위해 -1 곱해줌
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) *-1;
		
		//Wrapper클래스를 이용하는 방법2
		return Integer.compare(mem1.getNum(), mem2.getNum()) *-1;
		
	}
}