package kr.or.ddit.basic;
/*
  로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
      거스름돈도 계산하여 출력한다.)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
/*
  구입, 프로그램 종료
 */
import java.util.Scanner;

public class LottoStore {
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new LottoStore().startLottoStore();

	}

	// 시작 메서드
	public void startLottoStore() {
		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 구입
				buyLotto();
				break;
			case 2: // 프로그램 종료
				System.out.println("감사합니다!");
				return;
			default:
				System.out.println("작업번호를 잘못 입력하였습니다.");
				System.out.println("작업번호는 '1'또는 '2'를 입력하세요.");

			}
		}

	}

	// 로또 구입을 처리하는 메서드
	private void buyLotto() {
		System.out.print("\n" + "Lotto 구입 시작\n" + "\n" + "(1000원에 로또번호 하나입니다.)\n" + "금액 입력 >>");
		int money = scan.nextInt();
		if (money < 1000) { // 입력 금액이 1000원 미만일 때
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			return;
		}
		if (money > 100000) { // 입력 금액이 100000원 초과일 때
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			return;
		}

		// 금액에 맞는 로또 번호 만들기
		HashSet<Integer> lottoSet = new HashSet<Integer>();

		// 입력한 금액을 이용하여 구입할 로또의 매수를 구한다.
		int count = money / 1000;

		System.out.println();
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for (int i = 1; i <= count; i++) { // 로또의 매수만큼 반복
			// 1장 분의 로또번호를 만든다. (1~45사이의 중복되지 않는 번호 6개만들기)
			while (lottoSet.size() < 6) {
				lottoSet.add((int) (Math.random() * 45) + 1);
			}

			// Set에 저장된 로또번호를 갖는 List객체 생성
			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);

			Collections.sort(lottoList);
			
			System.out.println("로또번호 "+i+" : "+lottoList);
			
			// 작업에 사용한 Set객체를 비워준다.
			lottoSet.clear();
		}
		
		//거스름돈 계산 및 출력
		System.out.println();
		System.out.println("받은 금액은 "+money+"원이고 거스름돈은 "+(money%1000)+"원입니다.");

	}
	


	// 메뉴 출력 및 작업번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.print("===========" + "===================\n"
				+ "         Lotto 프로그램\n" + "==============================\n" + "	 1. Lotto 구입\n"
				+ "	 2. 프로그램 종료\n" + "==============================\n" + "메뉴선택 >> ");
		return scan.nextInt();

	}

}
