package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
문제2) 문제1에서 입력할 때 각 별명의 길이가 같은 것이 있을 경우에 대하여 처리하시오.
 */
public class ArrayListTest04 {

	public static void main(String[] args) {
//		method01();
		method02();
	}

	public static void method01() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nickNameList = new ArrayList<String>();

		System.out.println("(1) 5명의 별명을 입력하시오!");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 별명:");
			String nickName = sc.nextLine();
			nickNameList.add(nickName);
		}

		// 입력받은 5명의 별명 중 길이가 제일 긴 별명 출력
		String maxNickName = nickNameList.get(0);
		for (int i = 0; i < 5; i++) {
			maxNickName = nickNameList.get(0);
			if (nickNameList.get(i).length() > maxNickName.length()) {
				maxNickName = nickNameList.get(i);
				// 같은 길이의 별명이 입력되었을 경우, 나중에 입력된 별명을 선택
			} else if (nickNameList.get(i).length() == maxNickName.length()) {
				maxNickName = nickNameList.get(i);
			}
		}
		System.out.print("길이가 제일 긴 별명: " + maxNickName);
	}

	public static void method02() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nickNameList = new ArrayList<String>();

		System.out.println("(2) 5명의 별명을 입력하시오!");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 별명:");
			String nickName = sc.nextLine();
			nickNameList.add(nickName);
		}

		// 입력받은 5명의 별명 중 길이가 제일 긴 별명 출력
		// 5번째 별명부터 첫 번째 별명까지 거꾸로 루프를 돌면서, 
		// 만약 길이가 같거나 더 긴 별명을 만나면 그 별명을 선택
		String maxNickName = nickNameList.get(0);
		for (int i = 4; i >= 0; i--) {
			if(nickNameList.get(i).length() >= maxNickName.length()) {
				maxNickName = nickNameList.get(i);
			}

		}
		System.out.print("길이가 제일 긴 별명: " + maxNickName);

	}
}
