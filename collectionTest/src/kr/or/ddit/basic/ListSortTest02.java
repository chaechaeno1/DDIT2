package kr.or.ddit.basic;

import java.util.ArrayList;

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
