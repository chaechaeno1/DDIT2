package List;

import java.util.ArrayList;
import java.util.List;

/* 컬렉션 문제!!
 * 1. List<User> 타입으로 만들기
 * 2. List<Map<String,Object>> 타입으로 만들기
 * 
 * --------회원정보---------
이진기
행운의 숫자 2
생년월일 1996-06-12
주문 횟수 12
주문 총금액 500000
포인트 0
-------------------------
김종현
행운의 숫자 5
생년월일 2000-08-03
주문 횟수 3
주문 총금액 150000
포인트 0
-------------------------
김기범
행운의 숫자 9
생년월일 2006-10-03
주문 횟수 7
주문 총금액 100000
포인트 0
-------------------------
최민호
행운의 숫자 4
생년월일 1999-03-20
주문 횟수 2
주문 총금액 500000
포인트 0
-------------------------
이태민
행운의 숫자 8
생년월일 2008-10-04
주문 횟수 10
주문 총금액 40000
포인트 0
 * 
 * - -----문제-------
1. 이벤트로 유저 뽑기를 할 계획
    - 1~10까지 행운의 숫자 3 명을 추첨해 당첨자에게 포인트 만원 주기
    - 전체 회원 이름, 포인트 출력
2. 10월 생일 이벤트를 할 계획
    - 10월 생일인 유저에게 포인트 5천원 주기
    - 당첨자 이름, 생일, 포인트 출력
3. VIP 기준
    - 주문횟수가 10회가 넘으면 VIP
    - VIP들 이름 출력하기
4. VIP 기준이 바뀜
    - 주문 총 금액이 20만원이 넘어야 함
    -  VIP들 이름 출력하기
 * 
 * 
 */

public class UserType {
	
	

	public static void main(String[] args) {
		List<User> userList = new ArrayList<>();
		
		userList.add(new User("이진기",2,"1996-06-12",12, 500000, 0));
		userList.add(new User("김종현",5,"2000-08-03",3, 150000, 0));
		userList.add(new User("김기범",9,"2006-10-03",7, 100000, 0));
		userList.add(new User("최민호",4,"1999-03-20",2, 500000, 0));
		userList.add(new User("이태민",8,"2008-10-04",10, 40000, 0));
		
		//유저뽑기 이벤트 메서드
		luckyDraw();
		
		

	}
	
//		1. 이벤트로 유저 뽑기를 할 계획
//    - 1~10까지 행운의 숫자 3명을 추첨해 당첨자에게 포인트 만원 주기
//    - 전체 회원 이름, 포인트 출력	
	public static void luckyDraw(){
		
		int arr[] = new int[3];
		for(int i=0; i<3;i++) {
			//1~10까지 난수 발생
			int rnd = (int) (Math.random()*10+1);
			arr[i] = rnd;
		}
		for(int rndArr : arr) {
			System.out.print(rndArr + " ");
		}
		
		
	}
	


	
	
	
//	2. 10월 생일 이벤트를 할 계획
//    - 10월 생일인 유저에게 포인트 5천원 주기
//    - 당첨자 이름, 생일, 포인트 출력
	
	
	
//	3. VIP 기준
//    - 주문횟수가 10회가 넘으면 VIP
//    - VIP들 이름 출력하기
	
	
	
//4. VIP 기준이 바뀜
//    - 주문 총 금액이 20만원이 넘어야 함
//    -  VIP들 이름 출력하기
//	
	
	
	
	
}

class User{
	
	private String name;
	private int lucky;
	private String birth;
	private int order;
	private int price;
	private int point;
	
	//생성자
	public User(String name, int lucky, String birth, int order, int price, int point) {
		super();
		this.name = name;
		this.lucky = lucky;
		this.birth = birth;
		this.order = order;
		this.price = price;
		this.point = point;
	}

	//getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLucky() {
		return lucky;
	}

	public void setLucky(int lucky) {
		this.lucky = lucky;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
	

		
	
}
