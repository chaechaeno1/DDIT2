package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 문제) 5명의 사람 이름을 입력 받아 ArrayList에 추가한 후에
 	  ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 모두 출력하시오.
 	  (단, 입력은 Scanner객체를 이용)
 */

public class ArrayListTest02 {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("김민채");
		list.add("박상협");
		list.add("한동욱");
		list.add("전민균");
		list.add("정소현");
		
		String[] names = new String[5];
		
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<5; i++) {
			System.out.print(i+"번째 이름 입력: ");
			names[i] = sc.nextLine();
		}
		


		

		
		
//		ArrayList<String> list = new ArrayList<String>();
		
		

		

	}


	}


