package lottoProgram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class lottoProgram {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		lottoProgram lotto = new lottoProgram();

		// 로또프로그램 화면 출력
		lotto.printMenu();

		// 유저가 메뉴선택
		lotto.selectMenu();

		// 로또구입
		int inputMoney = lotto.lottoBuy();
		System.out.println("유저 입력금액 : " + inputMoney);

		// 로또생성
		lotto.createLottoNumber(inputMoney);

		// 거스름돈
		lotto.printRemainMoney(inputMoney);

	}

	// 로또 프로그램 화면 출력
	public void printMenu() {
		System.out.println("==========================\n" + "\tLotto 프로그램\n" + "--------------------------\n"
				+ "1. Lotto 구입\n" + "2. 프로그램 종료\n" + "==========================\n");
	}

	
	// 유저 -> 메뉴 선택
	public void selectMenu() {
		//todo 1번이나 2번을 안누르면 다시 고르게 한다. 0을 누르면 나가기.
		System.out.print("메뉴선택 : ");
		int userInput = sc.nextInt();
		
		switch(userInput){
			case 1 : return;
			case 2 : System.exit(0);
			default: selectMenu();
		}
	}

	// 로또구입
	public int lottoBuy() {
		//실패일 경우 다시 입력받게 하기
		System.out.println("Lotto 구입 시작\n" + "(1000원에 로또번호 하나입니다.)");

		// 유저 금액입력
		System.out.print("금액입력 : ");
		int inputMoney = sc.nextInt();

		// -> 금액이 천원 이하면 구매X
		if (inputMoney < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			return lottoBuy();
		}

		// -> 금액이 10만원 이상이면 구입X
		if (inputMoney > 100000) {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			return lottoBuy();
		}
		return inputMoney;

	}

	// 로또생성
	public void createLottoNumber(int inputMoney) {
		// ->로또 생성 (난수)
		int count = inputMoney / 1000; // 로또 횟수
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		for (int i = 0; i < count; i++) {
			Set<Integer> set = new HashSet<>();

			// 중복없는 6개 숫자
			while (set.size() < 6) {
				int rnd = (int) (Math.random() * 45) + 1;
				set.add(rnd);
			}
			List<Integer> list = new ArrayList<>(set);
			list.sort(Comparator.naturalOrder());
			System.out.println("로또번호" + (i + 1) + " : " + list);
		}

	}

	// 거스름돈
	public void printRemainMoney(int inputMoney) {
		int remain = inputMoney % 1000; // 거스름돈
		System.out.println("거스름돈 : " + remain);
		System.out.println("받은 금액은 " + inputMoney + "원이고 거스름돈은 " + remain + "원 입니다.");

	}

}
