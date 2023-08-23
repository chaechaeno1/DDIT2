package kr.or.ddit.basic;
/*
문제1) 5명의 별명을 입력 받아 ArrayList에 추가한 후 이들 중 별명의 길이가 제일 긴 별명을 출력
	(단, 별명의 길이는 모두 다르게 입력)
	
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest03 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nickNameList = new ArrayList<String>();
		
		System.out.println("5명의 별명을 입력하시오!");
		for(int i=0; i<5; i++) {
			System.out.print(i+"번째 별명:");
			String nickName = sc.nextLine();
			nickNameList.add(nickName);			
		}
		
		//입력받은 5명의 별명 중 길이가 제일 긴 별명 출력		
		String maxNickName = nickNameList.get(0);
		for(int i=0; i<5;i++) {
			maxNickName = nickNameList.get(0);
			if(nickNameList.get(i).length()>maxNickName.length()) {
				maxNickName = nickNameList.get(i);
			}
		}
		System.out.print("길이가 제일 긴 별명: "+maxNickName);
		}
		
}
