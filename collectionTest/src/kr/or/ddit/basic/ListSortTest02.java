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
		
		
		//Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스를 이용하여 정렬하시오.
		//(클래스명 : SortNumDesc) ->> 하단에 클래스 추가
		//<Member>
		
		Collections.sort(memList,new SortNumDesc());
		
		System.out.println("정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
	}

}

class Member {
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

}

//Member의 num값의 "내림차순"으로 정렬하는 외부 정렬 기준 클래스
class SortNumDesc implements Comparator<Member>{
	
	@Override
	public int compare(Member mem1, Member mem2) {
		return Integer.compare(mem2.getNum(), mem1.getNum());
		//Integer.compare(a, b)는 두 개의 정수를 비교하여 비교 결과를 반환하는 정적 메서드
		//내림차순 정렬을 위해 (mem2.getNum(), mem1.getNum())
		//mem2가 앞으로 가도록 내림차순 정렬
	}
	
}