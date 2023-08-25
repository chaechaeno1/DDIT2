package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;

/*
문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,
	 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
	 
	 (Map의 구조는 key값으로 입력한 '이름'을 사용하고, value값으로는 'Phone클래스의 인스턴스'로 한다.
	 변수 선언 예시) HashMap<String, Phone> 변수명;
	 
	 이 프로그램에는 다음과 같은 메뉴가 있는데 각 메뉴의 기능을 구현한다.
	 
	 ----------------
	 다음 메뉴를 선택하세요.
	 1. 전화번호 등록
	 2. 전화번호 수정
	 3. 전화번호 삭제
	 4. 전화번호 검색
	 5. 전화번호 전체 출력
	 0. 프로그램 종료
	 ----------------
	 
	 - 검색, 삭제 기능은 '이름'을 입력 받아 처리한다.
	 
실행 예시) 
	 ----------------
	 다음 메뉴를 선택하세요.
	 1. 전화번호 등록
	 2. 전화번호 수정
	 3. 전화번호 삭제
	 4. 전화번호 검색
	 5. 전화번호 전체 출력
	 0. 프로그램 종료
	 ----------------
	 메뉴선택 >> 1
	 
	 새롭게 등록할 전화번호 정보를 입력하세요.
	 이      름 >> 홍길동
	 전화번호 >> 010-1234-5678
	 주      소 >> 대전시 중구 오류동
	 
	 '홍길동'전화번호 등록 완료!!!
	 
	 ----------------
	 다음 메뉴를 선택하세요.
	 1. 전화번호 등록
	 2. 전화번호 수정
	 3. 전화번호 삭제
	 4. 전화번호 검색
	 5. 전화번호 전체 출력
	 0. 프로그램 종료
	 ----------------
	 메뉴선택 >> 1
	 
	 새롭게 등록할 전화번호 정보를 입력하세요.
	 이      름 >> 홍길동	 
	 
	 '홍길동'은 이미 등록된 사람입니다..
	 ----------------
	 다음 메뉴를 선택하세요.
	 1. 전화번호 등록
	 2. 전화번호 수정
	 3. 전화번호 삭제
	 4. 전화번호 검색
	 5. 전화번호 전체 출력
	 0. 프로그램 종료
	 ----------------
	 메뉴선택 >> 5
	 
	 ----------------------------------------------------
	 번호			이름			전화번호			주소
	 ----------------------------------------------------
	 1			홍길동		010-1234-5678	대전시 중구 오류동
	 	~~~
	 	~~~
  	 ----------------------------------------------------
	 출력 끝...	  

	 ----------------
	 다음 메뉴를 선택하세요.
	 1. 전화번호 등록
	 2. 전화번호 수정
	 3. 전화번호 삭제
	 4. 전화번호 검색
	 5. 전화번호 전체 출력
	 0. 프로그램 종료
	 ----------------
	 메뉴선택 >> 0
	 
	 프로그램을 종료합니다.
	 
	 
	 
 */

public class PhoneBookTest {
	private HashMap<String, Phone> phoneBookMap;

	// Phone class
	class Phone {
		String name;
		String tel;
		String address;

		// 생성자
		Phone(String name, String tel, String address) {
			this.name = name;
			this.tel = tel;
			this.address = address;
		}

		// getter, setter
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

	}

	public void printMenu() {

		System.out.println("----------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println("----------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("----------------");
		System.out.print("메뉴선택 >>");

	}

	public void submit() { // 등록
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 >> ");
		String name = sc.nextLine();

		if (phoneBookMap.containsKey(name)) {
			System.out.println("'" + name + "'" + "은 이미 등록된 사람입니다.");
			return;
		}

		System.out.print("전화번호>> ");
		String tel = sc.nextLine();
		System.out.print("주소>> ");
		String address = sc.nextLine();

		Phone newPhone = new Phone(name, tel, address);
		phoneBookMap.put(name, newPhone);
		System.out.println("'" + name + "'" + "의 전화번호 등록이 완료되었습니다.");

	}

	public void modify() { // 수정
		Scanner sc = new Scanner(System.in);
		System.out.print("수정할 이름>> ");
		String name = sc.nextLine();

		if (!phoneBookMap.containsKey(name)) {
			System.out.println("'" + name + "'" + "은 등록되지 않은 사람입니다.");
			return;
		}

		Phone phone = phoneBookMap.get(name);

		System.out.println("새로운 전화번호>> ");
		String tel = sc.nextLine();
		System.out.println("새로운 주소>>");
		String address = sc.nextLine();

		phone.setTel(tel);
		phone.setAddress(address);

		System.out.println("'" + name + "'" + "의 전화번호 수정이 완료되었습니다.");

	}

	public void delete() { // 삭제
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 이름 >> ");
		String name = sc.nextLine();

		if (!phoneBookMap.containsKey(name)) {
			System.out.println("'" + name + "'" + "은 등록되지 않은 사람입니다.");
			return;
		}

		phoneBookMap.remove(name);
		System.out.println("'" + name + "'" + "의 전화번호 삭제가 완료되었습니다.");

	}

	public void search() { // 검색
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 이름 >> ");
		String name = sc.nextLine();

		if (!phoneBookMap.containsKey(name)) {
			System.out.println("'" + name + "'" + "은 등록되지 않은 사람입니다.");
			return;
		}

		Phone phone = phoneBookMap.get(name);

		System.out.println("===============================");
		System.out.printf("번호\t\t이름\t\t전화번호\t\t주소");
		System.out.println("===============================");
		System.out.printf("%-10d%-15s%-20s%-15s%n", "1", phone.getName(), phone.getTel(), phone.getAddress());
		System.out.println("===============================");

	}

	public void telList() { // 전체출력
		System.out.println("===============================");
		System.out.printf("번호\t\t이름\t\t전화번호\t\t주소");
		System.out.println("===============================");

		int cnt = 1;
		for (Phone phone : phoneBookMap.values()) {
			System.out.printf("%-10d%-15s%-20s%-15s%n", cnt, phone.getName(), phone.getTel(), phone.getAddress());
			cnt++;
		}
		System.out.println("===============================");
		System.out.println("출력 끝!");

	}

	public static void main(String[] args) {
		PhoneBookTest book = new PhoneBookTest();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			book.printMenu();
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1: book.submit(); break;
			case 2: book.modify(); break;
			case 3: book.delete(); break;
			case 4: book.search(); break;
			case 5: book.telList(); break;
			case 0: 
				System.out.println("프로그램 종료!");
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("메뉴선택이 잘못되었습니다.");
				break;
			
			}
			
			
		}

	}

}
