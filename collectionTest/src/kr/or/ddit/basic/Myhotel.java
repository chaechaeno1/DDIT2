package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Myhotel {
	private HashMap<Integer, Room> hotelMap = new HashMap<>();
	private Scanner sc = new Scanner(System.in);

	// 생성자 ==> 객실 초기화 작업
	public Myhotel() {
		for (int i = 200; i < 500; i += 100) {
			for (int j = 1; j < 10; j++) { //j대신 i기입 실수X
				switch (i / 100) {
				case 2:
					hotelMap.put(i + j, new Room(i + j, "싱글룸"));
					break;
				case 3:
					hotelMap.put(i + j, new Room(i + j, "더블룸"));
					break;
				case 4:
					hotelMap.put(i + j, new Room(i + j, "스위트룸"));
					break;
				}

			}

		}

	}

	public static void main(String[] args) {
		new Myhotel().startHotel();

	}

	// 호텔 업무 시작
	private void startHotel() {
		System.out.print("*********************************************\n" + "       	호텔문을 열었습니다. 어서오십시요.\n"
				+ "*********************************************" + "\n");
		while (true) {
			int choice = selectMenu();

			switch (choice) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태
				roomStatus();
				break;
			case 4: // 호텔 운영 종료
				System.out.print("*********************************************\n" + "       	호텔문을 닫았습니다.\n"
						+ "*********************************************" + "\n");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");

			}

		}

	}

	// 메뉴선택
	private int selectMenu() {
		System.out.print("-----------------------------------------------------------\n" + "어떤 업무를 하시겠습니까?\n"
				+ "1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료\n"
				+ "-----------------------------------------------------------\n" + "업무 선택>>");
		return sc.nextInt();

	}

	// 1. 체크인
	private void checkIn() {
		System.out.print("----------------------------------------------\n" + "   체크인 작업\n"
				+ "----------------------------------------------\r\n" + " * 201~209 : 싱글룸\n" + " * 301~309 : 더블룸\n"
				+ " * 401~409 : 스위트룸\n" + "----------------------------------------------\n" + "방 번호 입력 >>");
		int roomNo = sc.nextInt();

		// 이미 체크인 된 방 검증하기
		if (hotelMap.containsKey(roomNo)) {
		    Room InRoom = hotelMap.get(roomNo);
		    if (InRoom != null && InRoom.getGuestName().equals("-")) {
                sc.nextLine(); 
                System.out.print("누구를 체크인 하시겠습니까?\n이름 입력 >> ");
                String guestName = sc.nextLine().trim();
                InRoom.setGuestName(guestName);
                System.out.println("체크인이 완료되었습니다.");
		    } else {
		        System.out.println(roomNo + "호 객실은 이미 손님이 있습니다.");
		    }
		} else {
		    System.out.println(roomNo + "호 객실은 존재하지 않습니다.");
		}
    }

	// 2. 체크아웃
	private void checkOut() {
		System.out.print("----------------------------------------------\n" + "   체크아웃 작업\n"
				+ "----------------------------------------------\n" + "체크아웃 할 방 번호를 입력하세요.\n" + "방번호 입력 >> ");
		 int roomNo = sc.nextInt();

		 if (hotelMap.containsKey(roomNo)) {
	            if (!hotelMap.get(roomNo).getGuestName().equals("-")) {
	                System.out.println(roomNo + "호 객실의 " + hotelMap.get(roomNo).getGuestName() + "님 체크아웃을 완료하였습니다.");
	                hotelMap.get(roomNo).setGuestName("-");
	            } else {
	                System.out.println(roomNo + "호 객실에는 체크인 한 사람이 없습니다.");
	            }
	        } else {
	            System.out.println(roomNo + "호 객실은 존재하지 않습니다.");
	        }
	}

	// 3. 객실상태
	
	private void roomStatus() {
        System.out.println("----------------------------------------------");
        System.out.println("		현재 객실 상태");
        System.out.println("----------------------------------------------");
        System.out.println("방 번호\t 방 종류\t 투숙객 이름");
        System.out.println("----------------------------------------------");
        
        // 200대 출력
        for (int roomNo : hotelMap.keySet()) {
            Room room = hotelMap.get(roomNo);
            if (roomNo >= 200 && roomNo < 300) {
                System.out.println(roomNo + "\t" + room.getRoomType() + "\t" + room.getGuestName());
            }
        }
        
        // 300대 출력
        for (int roomNo : hotelMap.keySet()) {
            Room room = hotelMap.get(roomNo);
            if (roomNo >= 300 && roomNo < 400) {
                System.out.println(roomNo + "\t" + room.getRoomType() + "\t" + room.getGuestName());
            }
        }
        
        // 400대 출력
        for (int roomNo : hotelMap.keySet()) {
            Room room = hotelMap.get(roomNo);
            if (roomNo >= 400 && roomNo < 500) {
                System.out.println(roomNo + "\t" + room.getRoomType() + "\t" + room.getGuestName());
            }
        }
        
        System.out.println("----------------------------------------------");
    }
}


class Room {
	int roomNo;
	String roomType;
	String guestName;

	Room(int roomNo, String roomType) {
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.guestName = "-";
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", roomType=" + roomType + ", guestName=" + guestName + ", getRoomNo()="
				+ getRoomNo() + ", getRoomType()=" + getRoomType() + ", getGuestName()=" + getGuestName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
