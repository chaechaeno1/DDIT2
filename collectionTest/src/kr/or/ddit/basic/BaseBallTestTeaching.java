package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
*/

public class BaseBallTestTeaching {
	private ArrayList<Integer> numList; // 난수가 저장될 리스트
	private ArrayList<Integer> userList; // 사용자가 입력한 값이 저장될 리스트

	private int strike, ball; // 스트라이크 개수와 볼의 개수가 저장될 변수

	private Scanner scan = new Scanner(System.in);

	// 1~9 사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드(Set 이용)
	public void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();

		// 난수 3개 만들기
		while (numSet.size() < 3) {
			numSet.add((int) (Math.random() * 9 + 1));
		}

		// 만들어진 난수를 List에 저장
		numList = new ArrayList<Integer>(numSet);

		// List의 데이터를 섞어준다.
		Collections.shuffle(numList);

	}

	// 사용자로부터 3개의 정수를 입력받아 리스트에 저장하는 메서드
	// (입력한 값들은 중복되지 않게 한다.)
	public void inputNum() {
		int n1, n2, n3; // 입력한 정수가 저장될 변수 선언

		do {
			System.out.print("숫자입력>> ");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();

			if (n1 == n2 || n1 == n3 || n2 == n3) { // 숫자 중복 검사
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력하세요.");
			}

		} while (n1 == n2 || n1 == n3 || n2 == n3);

		// 입력한 데이터를 List에 저장한다.
		// set을 쓰게되면 index값을 활용하지 못하기 때문에 list활용
		userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);

	}

	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	public void ballCount() {
		strike = 0;
		ball = 0; // 스트라이크와 볼의 개수를 초기화

		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if (numList.get(i) == userList.get(j)) { // 값이 같은지 비교
					if (i == j) { // 위치가 같은지 비교
						strike++;
					} else {
						ball++;
					}
				}
			}
		}

		// 볼카운트 결과를 출력한다.
		System.out.println(userList.get(0) + " " + userList.get(1) + " " + userList.get(2) + " ===> " + strike + "S"
				+ " " + ball + "B");

	}

	// 게임을 진행하는 메서드
	public void startGame() {
		// 난수를 만드는 메서드 호출
		createNum();
		
		// 만들어진 난수값 확인하기
//		System.out.println("컴퓨터의 난수 : "+ numList);
		
		int cnt = 0; // 몇 번 만에 맞췄는지를 저장하는 변수
		
		do {
			cnt++;
			// 사용자 입력 메서드 호출
			inputNum();
			
			//볼카운트를 계산하는 메서드
			ballCount();
			
		}while(strike!=3); // 3스트라이크가 될 때까지 반복한다.
		
		System.out.println();
		System.out.println("축하합니다!");
		System.out.println("당신은 "+cnt+"번째 만에 맞췄습니다.");
				
	}
	
	public static void main(String[] args) {
//		BaseBallTestTeaching b = new BaseBallTestTeaching();
//		b.startGame();
		
		new BaseBallTestTeaching().startGame();
		
	}

}
