package kr.or.ddit.basic;
/*
문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	 컴퓨터의 숫자는 난수를 이용하여 구한다. (1 ~ 9 사이의 난수 3개)
	 (스트라이크는 S, 볼은 B로 나타낸다.)
	 
예시)
	컴퓨터의 난수 ==> 9 5 7 

실행예시)
	숫자입력 >> 3 5 6
	3 5 6 ==> 1S 0B
	숫자입력 >> 7 8 9
	7 8 9 ==> 0S 2B
	숫자입력 >> 9 7 5
	9 7 5 ==> 1S 2B
	숫자입력 >> 9 5 7
	9 5 7 ==> 3S 0B
	
	축하합니다!
	당신은 4번 만에 맞췄습니다!
	
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class BaseBallTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 컴퓨터의 난수 생성
		HashSet<Integer> baseBall = new HashSet<Integer>();
		while (baseBall.size() < 3) {
			baseBall.add((int) (Math.random() * (9 - 1 + 1) + 1));
		}
//		System.out.println("컴퓨터의 난수(Set) >> "+baseBall);

		// 컴퓨터의 난수 : Set => 리스트화
		ArrayList<Integer> baseBallList = new ArrayList<Integer>(baseBall);
//		System.out.println("컴퓨터의 난수(List) >> " + baseBallList);
//		System.out.println(baseBall);
		Collections.shuffle(baseBallList);
//		System.out.println(baseBallList);

		int num = 0; // 게임 시도 횟수
		
		// 게임 전체 반복
		while (true) {

			// 사용자의 숫자 3개 입력받은 후 userList에 저장
			ArrayList<Integer> userList = new ArrayList<>();
			System.out.println("숫자 3개를 입력하시오!");
			for (int i = 1; i <= 3; i++) {
				System.out.print(i + "번째 숫자 입력 >> ");
				int user = sc.nextInt();
				userList.add(user);
			}
			System.out.println("사용자의 숫자입력 >> " + userList);
			

			// 사용자와 컴퓨터의 값 비교(스트라이크, 볼 판정)
			int strikes = 0;
			int balls = 0;

			for (int i = 0; i < baseBallList.size(); i++) {
				if (userList.get(i) == baseBallList.get(i)) { // 사용자 i번째 숫자와 컴퓨터 i번째 숫자가 일치하면 스트라이크
					strikes++;
				} else if (baseBallList.contains(userList.get(i))) {// 컴퓨터 숫자 전체 리스트 안에 사용자 i번째 숫자가 포함되면 볼
					balls++;
				}

			}

			System.out.println(userList.get(0) + " " + userList.get(1) + " " + userList.get(2) + " ===> " + strikes
					+ "S " + balls + "B ");
			System.out.println("===================================================");
			
			num++;

			if (strikes == 3) { // 스트라이크 3점 => 게임종료
				System.out.println("축하합니다! 스트라이크 3점입니다!");
				System.out.println("사용자가 게임을 시도한 횟수는 "+num+"번 입니다.");
				System.out.println("게임을 종료합니다.");
				break; 
			}

		} // while끝
	}
}
