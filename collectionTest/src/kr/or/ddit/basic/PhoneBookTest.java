package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
	 
추가 조건)
	1) '6. 전화번호 저장' 메뉴를  추가하고 구현한다.
		- 저장파일명은 'phoneBookData.dat'로 한다.
	2) 프로그램이 시작될 떄 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
	3) 프로그램을 종료할 때 Map의 데이터가 변경(추가, 수정, 삭제 등)되면 
		저장 후 종료되도록 한다.
		
		
	 
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
	private Scanner scan;

	// 기본 생성자
	public PhoneBookTest() {
		phoneBookMap = new HashMap<String, Phone>();
		scan = new Scanner(System.in);
		// 프로그램 실행시 저장된 Data 로딩
		dataLoad();
	}

	public static void main(String[] args) {
		new PhoneBookTest().startPhoneBook();

	}

	// 프로그램을 시작하는 메서드
	public void startPhoneBook() {
		System.out.println("***********************************\n" + "	전 화 번 호 관 리 프 로 그 램    \n"
				+ "***********************************\n");
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 등록
				insert();
				break;
			case 2: // 수정
				update();
				break;
			case 3: // 삭제
				delete();
				break;
			case 4: // 검색
				search();
				break;
			case 5: // 전체출력
				displayAll();
				break;
			case 6: // 전화번호 저장
				save();
				break;
			case 0: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");

			}

		}

	}

	// 전화번호 정보를 저장하는 메서드
	private void save() {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/phoneBookData.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);

			// 객체 저장
			System.out.println("저장 시작!");
			oout.writeObject(phoneBookMap);
			oout.writeObject(null); // EOFException을 방지
			System.out.println("저장 완료!");

			oout.close(); // 스트림 닫기

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 프로그램이 시작될 떄 저장된 파일이 있으면
	// 그 데이터를 읽어와 Map에 셋팅
	private void dataLoad() {
		try {
			FileInputStream fin = new FileInputStream("d:/d_other/phoneBookData.dat");
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream oin = new ObjectInputStream(bin);

			Object obj;

			while ((obj = oin.readObject()) != null) {
				if (obj instanceof HashMap) {
					HashMap<String, Phone> data = (HashMap<String, Phone>) obj;
					phoneBookMap = data;
				}

			}

			oin.close();

			System.out.println("전화번호 로딩 완료!");
			System.out.println("==========================");

		} catch (EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 이름을 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();

		// 입력한 이름을 key값으로 Phone 객체 가져오기
		Phone p = phoneBookMap.get(name);

		if (p == null) {// 해당 key값의 데이터가 없으면...
			System.out.println(name + "님의 전화번호 정보가 없습니다.");
		} else {
			System.out.println();
			System.out.println(name + "님의 전화번호 정보");
			System.out.println("======================");
			System.out.println("이 름 : " + p.getName());
			System.out.println("전화번호 : " + p.getTel());
			System.out.println("주 소 : " + p.getAddr());
			System.out.println("======================");
		}

	}

	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();

		// 해당 이름이 없으면 삭제 작업 중단
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "님의 전화번호 정보는 등록되지 않은 정보입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}

		phoneBookMap.remove(name); // 삭제 작업

		System.out.println(name + "님의 전화번호 정보를 삭제하였습니다.");
	}

	// 전체 전화번호 정보를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("=====================================================");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("=====================================================");

		Set<String> phoneBookSet = phoneBookMap.keySet();
		if (phoneBookSet.size() == 0) {
			System.out.println("등록된 전화번호가 없습니다.");
		} else {
			int cnt = 0; // 번호 출력용 변수

			Iterator<String> it = phoneBookSet.iterator();
			while (it.hasNext()) {
				cnt++;
				String key = it.next(); // 키값(등록된 사람 이름)
				Phone p = phoneBookMap.get(key); // Value값(Phone객체)

				System.out.println(cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());

			}

		}

	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >>");
		String name = scan.next();

		// 해당 이름이 없으면 수정작업 중단
		if (!phoneBookMap.containsKey(name)) {
			System.out.println(name + "님의 전화번호 정보는 등록되지 않은 정보입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}

		System.out.print("새로운 전화번호 >>");
		String newTel = scan.next();
		scan.nextLine(); // 입력 버퍼 비우기

		System.out.print("새로운 주소 >> ");
		String newAddr = scan.nextLine();

		// 같은 key값에 새로운 데이터를 셋팅한 Phone객체를 저장하면 된다.
		// 새로 값을 입력하면, 그 전에 입력한 값은 사라지고 새로운 값으로 덮어쓰기 된다.

		phoneBookMap.put(name, new Phone(name, newTel, newAddr));

		System.out.println(name + "님의 전화번호 정보 수정 완료!!!");

	}

	// 새로운 전화번호 정보를 등록하는 메서드 (이미 등록된 사람은 등록X)
	private void insert() {
		System.out.println();
		System.out.println("새로운 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();

		// 입력받은 이름이 이미 등록된 이름인지 검사
		if (phoneBookMap.containsKey(name)) {
			System.out.println(name + "님은 이미 등록된 사람압니다.");
			System.out.println("등록 작업을 마칩니다.");
			return;
		}

		// 등록된 이름이 아닐 경우에 다음단계로
		System.out.print("전화번호>>");
		String tel = scan.next();
		scan.nextLine(); // 입력 버퍼 비우기

		System.out.print("주 소 >>");
//		String addr = scan.next();
		String addr = scan.nextLine();

		/*
		 *
		 * - Scanner객체는 입력 버퍼를 이용하여 입력 작업을 수행 즉, 입력 버퍼를 확인하여 값이 있으면 현재 입력 버퍼에 있는 값을 가져가고
		 * 없으면 새로운 데이터를 입력받은 후 가져간다.
		 * 
		 * 1. Scanner의 next(), nextInt(), nextDouble() 등 (nextLine()을 제외한 메서드) ==> 이
		 * 메서드들은 사이띄기, tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽음 2. Scanner의 nextLine()메서드
		 * ==> 한 줄 단위로 입력한다. (즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.)
		 */

		// 입력 받은 정보를 이용하여 phone객체 생성
//		Phone p = new Phone(name, tel, addr);		
//		phoneBookMap.put(name, p);

		phoneBookMap.put(name, new Phone(name, tel, addr));

		System.out.println(name + "님의 전화번호 정보 등록 완료!!!");

	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.print("----------------\n" + "다음 메뉴를 선택하세요.\n" + "1. 전화번호 등록\n" + "2. 전화번호 수정\n" + "3. 전화번호 삭제\n"
				+ "4. 전화번호 검색\n" + "5. 전화번호 전체 출력\n" + "6. 전화번호 저장\n" + "0. 프로그램 종료\n" + "----------------\n"
				+ "메뉴선택 >>");
		return scan.nextInt();
	}
}

//이름, 주소, 전화번호를 멤버로 갖는 Phone 클래스 만들기

class Phone implements Serializable {
	private String name;
	private String tel;
	private String addr;

	Phone(String name, String tel, String addr) {
		this.name = name;
		this.tel = tel;
		this.addr = addr;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}

}
