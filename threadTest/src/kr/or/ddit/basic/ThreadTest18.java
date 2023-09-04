package kr.or.ddit.basic;

/*
 	wait(), notify()를 이용한 두 쓰레드에서 번갈아 한번씩 실행하는 예제
 	(wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능)
 	
 */

public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject(); // WorkObject 객체를 생성

		// ThreadA와 ThreadB를 생성하고 시작
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);

		thA.start();
		thB.start();

	}

}

//WorkObject의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;

	// 생성자
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		synchronized (workObj) {
			workObj.notify();
		}


	}
}

//WorkObject의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;

	// 생성자
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

//공통으로 사용할 class
// 이 두 메서드는 synchronized 키워드로 동기화되어 있습니다. 
//이것은 한 번에 하나의 스레드만이 이 메서드를 실행할 수 있음을 의미
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 실행 중...");

		notify();
		try {
			wait();
		} catch (InterruptedException e) {

		}
	}

	public synchronized void methodB() {
		System.out.println("methodB()메서드에서 작업 중...");

		notify();
		try {
			wait();
		} catch (InterruptedException e) {

		}
	}
}
