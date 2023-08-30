package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램

		/*
		 *** Thread를 사용하는 방법***
		 * 
		 * 방법1) (1)Thread클래스를 상속한 class를 작성한 후(run() 재정의) (2)class의 인스턴스를 생성 (3)생성된
		 * 인스턴스의 start()메서드를 호출해서 실행
		 */

		// 방법1
		MyThread1 th1 = new MyThread1(); // 인스턴스 생성
		th1.start(); // 쓰레드 실행

		/*
		 * 방법2-1) (1)Runnable인터페이스를 구현한 class를 작성한 후 (2)class의 인스턴스를 생성 (3)Thread클래스의
		 * 인스턴스를 생성할 때 Runnable인터페이스를 구현한 class의 인스턴스를 생성자의 인수값으로 넣어줌 (4)생성된 Thread클래스의
		 * 인스턴스의 start()메서드를 호출해서 실행
		 */

		// 방법2-1
		MyRunner2 r2 = new MyRunner2(); // Runnable인터페이스를 구현한 클래스의 인스턴스 생성
		Thread th2 = new Thread(r2); // Thread의 인스턴스를 생성할 때 Runnable구현체의 인스턴스를 주입
		th2.start();
//		MyRunner2 eee = new MyRunner2(); // 변수명이 다른 인스턴스 여러개 생성 가능

		// 방법2-2) 익명 구현체를 이용하는 방법
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");

					try {
						// Thread.sleep(시간) ==> 주어진 시간동안 작업을 잠시 멈춤
						// '시간'은 밀리세컨드 단위 사용 (즉, 1000은 1초를 의미)
						//
						Thread.sleep(100);
					} catch (InterruptedException e) {

					}
				}
			}
		};

		Thread th3 = new Thread(r3);
		th3.start();
	} // main 메서드 종료
} // ThreadTest02 클래스 종료

// 방법1
// (1)Thread클래스를 상속한 class를 작성
class MyThread1 extends Thread {
	// (2) run()메서드 재정의

	@Override
	public void run() {
		// 이 run()메서드 안에는 쓰레드에서 처리할 내용 작성
		// '*'문자를 200개 출력하는 기능
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");

			try {
				// Thread.sleep(시간) ==> 주어진 시간동안 작업을 잠시 멈춤
				// '시간'은 밀리세컨드 단위 사용 (즉, 1000은 1초를 의미)
				//
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}

	}

}

//방법2-1
// (1) Runnable인터페이스를 구현한 class를 작성
class MyRunner2 implements Runnable {
	// (2) run()메서드 재정의

	@Override
	public void run() {
		// 이 run()메서드 안에는 쓰레드에서 처리할 내용 작성
		// '$'문자를 200개 출력하는 기능
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}

}
