package kr.or.ddit.basic;

// 데몬쓰레드 연습 ==> 자동 저장하는 기능

public class ThreadTest08 {

	public static void main(String[] args) {
		AutoSaveThread autosave = new AutoSaveThread();
		
		autosave.setDaemon(true); // 데몬쓰레드로 설정하기(반드시 start()메서드 호출 전에 설정)
		autosave.start();
		try {
			for(int i=1; i<=20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
			
		} catch (InterruptedException e) {

		}
		System.out.println("main 쓰레드 종료!");

	}

}

// 자동 저장하는 쓰레드 ==> 3초에 한번씩 자동 저장 하는 쓰레드
class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	private void save() {
		System.out.println("작업한 내용을 저장합니다.");
	}
	

	
	@Override
	public void run() {
		while(true) { //무한반복
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {

			}
			save();
		}
	}
	
}