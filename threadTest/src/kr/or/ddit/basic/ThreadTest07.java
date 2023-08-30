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

	}

}

//	컴퓨터의 가위바위보는 난수를 이용하여 정하고, 

//	사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받음

class userInput extends Thread {
	HashMap<Integer, > 

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위 바위 보 중에서 하나를 입력하세요.");
		inputCheck = true; // 입력이 완료되면 inputCheck 변수를 true로 변경
		System.out.println("입력한 값 : " + str);
	}

//카운트다운을 진행하는 쓰레드 클래스
	class Timer extends Thread {
		public static boolean inputCheck;

		@Override
		public void run() {
			for (int i = 5; i >= 1; i--) {
				if (DataInput.inputCheck == true) {
					return; //
				}
				System.out.println(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
			System.out.println("==========결   과==========");
			System.out.println("시간 초과로 사용자가 졌습니다ㅠ_ㅠ");
			System.exit(0); // 프로그램 강제종료
		}
	}
}
