package kr.or.ddit.basic;
/*
문제1) 5명의 별명을 입력 받아 ArrayList에 추가한 후 이들 중 별명의 길이가 제일 긴 별명을 출력
	(단, 별명의 길이는 같은 것이 있을 수 있음)
문제2) 문제1에서 입력할 때 각 별명의 길이가 같은 것이 있을 경우에 대하여 처리하시오.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest04Teaching {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> aliasList = new ArrayList<>();

		System.out.println("별명을 5번 입력하시오!");
		for (int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 별명 >>");
			String alias = sc.nextLine();
			aliasList.add(alias);
		}
		System.out.println();

		//제일 긴 별명의 길이가 저장될 변수 ==> 이 변수는 첫번째 데이터의 길이로 초기화
		int maxLength = aliasList.get(0).length();
		
		for(int i=1; i<aliasList.size(); i++) {
			if(maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들...");
		for(int i=0; i<aliasList.size(); i++) {
			if(maxLength == aliasList.get(i).length()) {
				System.out.println(aliasList.get(i));
			}
		}
	}

}
