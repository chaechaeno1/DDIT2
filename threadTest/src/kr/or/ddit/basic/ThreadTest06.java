package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();

		th1.start();
		th2.start();
		
		
	}

}

// 데이터를 입력하는 쓰레드 클래스

class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요!");
		System.out.println("입력한 값 : " + str);
	}

}

// 카운트다운을 진행하는 쓰레드 클래스
class CountDown extends Thread {
	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); //프로그램 강제종료
	}
}
