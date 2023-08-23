package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest02Teaching {

	public static void main(String[] args) {
		/* if문 다양하게 활용
		 */
		method01();
		method02();
		method03();
		method04();

	}
	public static void method01() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();

		System.out.println("5명의 이름을 입력하세요!");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 사람 이름>>");
			String name = sc.nextLine();
			nameList.add(name);
		}

		System.out.println();

		System.out.println("김씨 성을 가진 사람들..");
		for (int i = 0; i < nameList.size(); i++) {
			if (nameList.get(i).substring(0, 1).equals("김")) {
				System.out.println(nameList.get(i));
			}
		}

	}
	
	public static void method02() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();

		System.out.println("5명의 이름을 입력하세요!");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 사람 이름>>");
			String name = sc.nextLine();
			nameList.add(name);
		}

		System.out.println();

		System.out.println("김씨 성을 가진 사람들..");
		for (int i = 0; i < nameList.size(); i++) {
			if(nameList.get(i).charAt(0)=='김') {
				System.out.println(nameList.get(i));
			}
		}

	}
	
	public static void method03() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();

		System.out.println("5명의 이름을 입력하세요!");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 사람 이름>>");
			String name = sc.nextLine();
			nameList.add(name);
		}

		System.out.println();

		System.out.println("김씨 성을 가진 사람들..");
		for (int i = 0; i < nameList.size(); i++) {
			if(nameList.get(i).startsWith("김")) {
				System.out.println(nameList.get(i));
			}
		}

	}
	
	public static void method04() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> nameList = new ArrayList<String>();

		System.out.println("5명의 이름을 입력하세요!");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + "번째 사람 이름>>");
			String name = sc.nextLine();
			nameList.add(name);
		}

		System.out.println();

		System.out.println("김씨 성을 가진 사람들..");
		for (int i = 0; i < nameList.size(); i++) {
			if(nameList.get(i).indexOf("김")==0){
				System.out.println(nameList.get(i));
			}
		}

	}
	
	

}
