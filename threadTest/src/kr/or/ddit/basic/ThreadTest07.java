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
 		결  과  : 당신이 이겼습니다.
 	
 	
 */

public class ThreadTest07 {

	public static void main(String[] args) {

		Thread game = new Game();
		Thread timer = new Timer();

		game.start();
		timer.start();

	}

}

//1) 
//컴퓨터의 가위바위보는 난수를 이용하여 정하고, 
//사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받음 

class Game extends Thread {
	public static boolean inputCheck = false;

	@Override
	public void run() {
		// 컴퓨터 난수 발생
		int comNum = (int) (Math.random() * 3 + 1);
		String computer;
		switch (comNum) {
		case 1:
			computer = "가위";
			break;
		case 2:
			computer = "바위";
			break;
		case 3:
			computer = "보";
			break;
		default:
			computer = "잘못입력";
		}
		String userInput = JOptionPane.showInputDialog("입력하세요!");
		inputCheck = true;

		// 사용자 입력값
		int userNum = 0;
		if (userInput.equals("가위")) {
			userNum = 1;
		} else if (userInput.equals("바위")) {
			userNum = 2;
		} else if (userInput.equals("보")) {
			userNum = 3;
		}

		int res = (userNum - comNum + 3) % 3;
		String[] number = {"가위", "바위", "보"};
		System.out.println("======결 과======");
		System.out.println("컴퓨터 : " + number[comNum-1]);
		System.out.println("사용자 : " + number[userNum-1]);

		if (res == 1 || res == -2) {
			System.out.println("결 과 : 사용자가 이겼습니다.");
		} else if (res == -1 || res == 2) {
			System.out.println("결 과 : 컴퓨터가 이겼습니다.");
		} else {
			System.out.println("결 과 : 비겼습니다.");
		}

	}

}

//2)입력 시간은 5초로 제한하고 카운트 다운을 진행
//> Timer Thread
//2-1) 5초 안에 입력이 없으면 게임에 진 것으로 처리, 프로그램을 종료
//2-2) 5초 안에 입력이 있으면 승패를 구해서 결과를 출력

class Timer extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			// 입력 완료 여부 검사
			// ==> 입력이 완료되면 쓰레드를 종료시킴
			if (Game.inputCheck == true) {
				return; // run()메서드가 종료되면 쓰레드도 종료.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("======결과======");
		System.out.println("시간초과로 사용자가 졌습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램 강제종료
	}

}
