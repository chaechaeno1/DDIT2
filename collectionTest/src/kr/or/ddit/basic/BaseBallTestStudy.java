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

/* *****코드짤 때 필요한 것!*****
	컴퓨터의 숫자는 난수 => Math.random() 이용
	(int)(Math.random()*(최대값-최소값+1)+최소값)
	set은 순서가 없으므로 index값 사용불가! -> list로 만들어줌
	사용자의 숫자는 입력받을 것 => Scanner 사용
	게임이 반복되려면? => while문
	스트라이크, 볼 변수 필요하겠지? => strikes, balls
	게임 시도 횟수 변수 필요!
 
 */

public class BaseBallTestStudy {

	public static void main(String[] args) {		
		// 사용자의 입력값 받기
		Scanner sc = new Scanner(System.in);

		// 컴퓨터의 난수 생성(1~9) => Set 이용
		HashSet<Integer> baseBall = new HashSet<Integer>();
		while (baseBall.size() < 3) {
			baseBall.add((int) (Math.random() * 9) + 1); //괄호처리 잘못해서 출력안보였음
		}

		// 컴퓨터의 난수 Set을 List화
		ArrayList<Integer> baseBallList = new ArrayList<Integer>(baseBall);
		Collections.shuffle(baseBallList);
		// 게임 시도 횟수
		int num = 0;

		// 게임 반복
		while (true) {
			// 사용자가 입력할 3개의 숫자를 저장하기 위해 userList생성
			ArrayList<Integer> userList = new ArrayList<>();
			
			// 사용자 입력값 받기
			System.out.println("숫자 3개를 입력하시오!");
			for (int i = 1; i <= 3; i++) {
				System.out.print(i + "번째 숫자 입력 >> ");
				int user = sc.nextInt();
				userList.add(user);
			}
			System.out.println("사용자의 숫자입력 >>" + userList);

			// 사용자와 컴퓨터의 값 비교(스트라이크, 볼 판정하기)
			int strikes = 0; 
			int balls = 0;

			for (int i = 0; i < baseBallList.size(); i++) {
				if (userList.get(i) == baseBallList.get(i)) {
					strikes++;
				} else if (baseBallList.contains(userList.get(i))) {
					balls++;
				}
			}
			System.out.println(userList + " ==> " + strikes + "S" + " " + balls + "B");
			System.out.println("=======================================");
			
			num++; //게임이 끝나지 않았으므로 시도횟수 추가
			
			if(strikes == 3) { //스트라이크 3점이면 게임종료
				System.out.println("축하합니다! 스트라이크 3점 입니다!");
				System.out.println("사용자가 게임을 시도한 횟수는 "+num+"번 입니다.");
				System.out.println("게임을 종료합니다.");
				break;
			}
		}// while문 끝

	}

}
