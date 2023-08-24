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
import java.util.HashSet;
import java.util.Scanner;

public class BaseBallTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashSet<Integer> baseBall = new HashSet<Integer>();
		while(baseBall.size() < 3) {
			baseBall.add((int)(Math.random()*(9-1+1)+1));
		}
		
		ArrayList<String> userList = new ArrayList<>();
		System.out.println("숫자 3개를 입력하시오!");
		for(int i=1; i<=3; i++) {
			System.out.print(i+"번째 숫자 입력 >> ");
			String user = sc.nextLine();
			userList.add(user);
		}
		
		//컴퓨터의 난수 : Set => 리스트화
		ArrayList<Integer> baseBallList = new ArrayList<Integer>(baseBall);
		for(int i=0; i<baseBallList.size(); i++) {
			System.out.println(i + "번째 자료 ==> " + baseBallList.get(i));
		}
		
		//사용자와 컴퓨터의 값 비교(스트라이크, 볼 판정)

	
	}
	
}


