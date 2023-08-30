package kr.or.ddit.basic;

public class ThreadTest02_연습 {

	public static void main(String[] args) {
		// 멀티 쓰레드

		// 방법1
		Thread th1 = new MyThread();
		th1.start();
		
		// 방법2
		Runnable r2 = new MyRunner3();
		Thread th2 = new Thread(r2);
		th2.start();
		
		// 방법3
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				for(int i = 1; i<=200; i++) {
					System.out.print("@");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		Thread th3 = new Thread(r3);
		th3.start();

	}

}

//방법1 Thread 클래스 생성
class MyThread extends Thread {
	@Override
	public void run() {
		// '*'문자를 200개 출력하는 기능
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
		}
	}
}

//방법2 Runnable 인터페이스 
class MyRunner3 implements Runnable {
	@Override
	public void run() {
		// '$'문자를 200개 출력하는 기능
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
		}

	}
}