package lottoProgram;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneBookTest {
	static Scanner sc = new Scanner(System.in);
	static Map<String, Phone> phoneBook = new HashMap<>();

	public static void main(String[] args) {
		while (true) {
			// 메뉴선택
			System.out.println("------------------\n" + "다음 메뉴를 선택하세요.\r\n" + "1. 전화번호 등록\n" + "2. 전화번호 수정\n"
					+ "3. 전화번호 삭제\n" + "4. 전화번호 검색\n" + "5. 전화번호 전체 출력\n" + "0. 프로그램 종료\n" + "------------------");
			System.out.print("메뉴선택 >> ");
			int inputMenu = sc.nextInt();
			sc.nextLine();

			switch (inputMenu) {
			case 1: {
				while (true) {
					// 등록
					System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");

					// ->이름, 전화번호, 주소 입력받기
					System.out.print("이름 >> ");
					String name = sc.nextLine().trim();

					System.out.print("전화번호 >> ");
					String phoneNumber = sc.nextLine();

					System.out.print("주소>> ");
					String address = sc.nextLine();

					// ->이름이 중복이면 저장X
					if (phoneBook.get(name) != null) {
						System.out.println("'" + name + "'은 이미 등록된 사람입니다.");
						continue;
					}

					// 등록
					phoneBook.put(name, new Phone(name, address, phoneNumber));
					System.out.println("'" + name + "'의 등록이 완료되었습니다.");
					break;
				}
				break;

			}
			case 2: {
				while (true) {
					// 수정
					System.out.println("수정할 이름을 입력하세요.");
					// 이름 입력하기
					System.out.print("이름 >> ");
					String name = sc.nextLine().trim();
					// 이름 다르면 재입력받기
					if (!phoneBook.containsKey(name)) {
						System.out.println("등록되지 않은 이름입니다.");
						continue;
					}
					// 전화번호입력받기
					System.out.print("전화번호 입력>> ");
					String phoneNumber = sc.nextLine();

					// 수정등록
					Phone beforePhoneBook = phoneBook.get(name);
					beforePhoneBook.setPhoneNumber(phoneNumber);

					break;
				}
				break;
			}
			case 3: {
				while (true) {
					// 삭제
					System.out.println("삭제할 이름을 입력해주세요.");
					// 이름 입력받으면 삭제
					System.out.print("이름 >>");
					String name = sc.nextLine();
					// 이름 다르면 재입력받기
					if (!phoneBook.containsKey(name)) {
						System.out.println("등록되지 않은 이름입니다.");
						continue;
					}
					// 이름삭제하기
					phoneBook.remove(name);
					break;
				}
				break;
			}
			case 4: {
				while (true) {
					// 검색
					System.out.println("검색할 이름을 입력해주세요.");
					// 이름 검색하면
					System.out.print("이름 입력>>");
					String name = sc.nextLine();
					// 이름 다르면 재입력 받기
					if (!phoneBook.containsKey(name)) {
						System.out.println("등록되지 않은 이름입니다.");
						continue;
					}
					Phone phone = phoneBook.get(name);
					// 머리
					printHead();
					// 전화번호, 주소 출력
					System.out.println("1" + "\t" + phone.getName() + "\t" + phone.getPhoneNumber() + "\t"
							+ phone.getAddress());
					break;
				}
				break;
			}
			case 5: {
				// 전체출력
				printHead();
				// 전체출력하기
				int index = 1;
				for (String name : phoneBook.keySet()) {
					Phone phone = phoneBook.get(name);
					System.out.println(index++ + "\t" + phone.getName() + "\t" + phone.getPhoneNumber() + "\t"
							+ phone.getAddress());
				}
			}
				break;

			case 0: {
				// 프로그램 종료
				System.exit(0);
			}
				break;

			default: {
				// todo 다시 입력받기
				System.out.println("잘못 입력하였습니다.");
			}
			}

		}
	}

	static void printHead() {
		System.out.println("=======================================");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("=======================================");

	}

	static class Phone {
		String name;
		String address;
		String phoneNumber;

		Phone(String name, String address, String phoneNumber) {
			this.name = name;
			this.address = address;
			this.phoneNumber = phoneNumber;
		}

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

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		@Override
		public String toString() {
			return "Phone [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
		}

	}

}
