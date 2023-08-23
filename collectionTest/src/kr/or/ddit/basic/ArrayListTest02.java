package kr.or.ddit.basic;
/*
문제) 5명의 사람 이름을 입력 받아 ArrayList에 추가한 후에
	  ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 모두 출력하시오.
	  (단, 입력은 Scanner객체를 이용)
*/

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        //이름 입력받기
        ArrayList<String> names = new ArrayList<>(); 
        //이름 입력 -> String

        System.out.println("***5명의 이름을 입력하세요!***");
        for (int i = 0; i < 5; i++) {
            System.out.print(i+"번째 이름 입력: ");
            String name = sc.nextLine().trim();
            names.add(name);
        }

        System.out.print("김씨 성을 가진 이름들:");
        //첫번째 글자가 '김'인 사람을 출력
        for (String name : names) {
            int index = name.indexOf("김");
            if (index != -1) {
                System.out.print(name+"|");
            }
        }
    }
}