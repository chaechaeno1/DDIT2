package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위바위보를 진행하는 프로그램 작성
	
	컴퓨터의 가위바위보는 난수를 이용하여 정하고, 
	사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받음
	
	입력 시간은 5초로 제한하고 카운트 다운을 진행
	5초 안에 입력이 없으면 게임에 진 것으로 처리, 프로그램을 종료
	
	5초 안에 입력이 있으면 승패를 구해서 결과를 출력
	
	결과예시) 
	1) 5초안에 입력을 못했을 경우
		-- 결 과 --
	시간 초과로 사용자가 졌습니다ㅠ_ㅠ
	
	2) 5초 안에 입력했을 경우
		-- 결 과 --
		컴퓨터 : 가위
		사용자 : 바위
		결  과  : 당신이 이겼습니다..
	
	
*/

public class ThreadTest07_수업 {
	public static boolean inputCheck; // 입력 완료여부 확인 변수

	public static void main(String[] args) {
		GameCountDown gcd = new GameCountDown();
		// GameCountDown 클래스는 Thread 클래스를 상속받았기 때문에 해당 클래스의 객체는 스레드로서 실행될 수 있는 객체가 되며,
		// start() 메서드를 호출하면 내부적으로 run() 메서드가 실행

		// 난수를 이용하여 컴퓨터의 가위바위보 정하기
		String[] data = { "가위", "바위", "보" };
		int index = (int) (Math.random() * 2) + 1; // 0~2사이의 난수 만들기
		String com = data[index]; // 컴퓨터의 가위바위보 정하기

		// 사용자로부터 가위바위보 입력받기
		gcd.start(); // 카운트다운 쓰레드 작동 시작
		String man = null;
		do {
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요!");
//		}while( !("가위".equals(man) || "바위".equals(man) || "보".equals(man)));
		} while (!"가위".equals(man) && !"바위".equals(man) && !"보".equals(man));
		// 만약 입력값이 해당 문자열 중 하나가 아니라면, !("가위".equals(man) || "바위".equals(man) ||
		// "보".equals(man)) 조건이 true가 되어 루프가 계속해서 실행됩니다. 사용자가 유효한 입력을 할 때까지 계속해서 입력을 받게
		// 됩니다.
		// 즉, 사용자가 "가위", "바위", "보" 중 하나를 입력할 때까지 프로그램은 무한히 입력을 받을 것입니다. 유효한 입력이 들어온 이후에야
		// 프로그램이 다음 단계로 진행될 수 있습니다.

		inputCheck = true;

		// 결과 판정하기
		String result = "";

		if (man.equals(com)) {
			result = "비겼습니다.";
		} else if (man.equals("가위") && com.equals("보") || man.equals("바위") && com.equals("가위")
				|| man.equals("보") && com.equals("바위")) {
			result = "사용자가 이겼습니다.";
		} else {
			result = "컴퓨터가 이겼습니다.";
		}
		// 결과 출력
		System.out.println("==========결  과==========");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("========================");
		System.out.println(result);

	}

}

class GameCountDown extends Thread {
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		for (int i = 5; i >= 1; i--) {
			if (ThreadTest07_수업.inputCheck) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("==========결  과==========");
		System.out.println("시간 초과로 당신이 졌습니다ㅠㅠ");
		System.exit(0); // 프로그램 종료
	}

}
