package kr.or.ddit.basic;

//은행의 입출금을 쓰레드로 처리하는 예제

public class ThreadTest16 { // 공통 객체
	private int balance; // 잔액이 저장될 변수

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금을 처리하는 메서드
	public void deposit(int money) {
		balance += money;
	}

	// ★★★★
	// 출금을 처리하는 메서드(반환값 : 출금 성공 true, 출금 실패 false)
	public synchronized boolean withdraw(int money) {
		if (balance >= money) { // 잔액이 출금할 금액과 같거나 보다 크면 실행
			for (int i = 1; i <= 10000000; i++) { // 출력 시간을 지연하기 위한 코드
				int a = i + 3;
			}
			balance -= money;// 잔액에서 출금 금액을 뺀 금액을 다시 잔액으로 초기화
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		ThreadTest16 account = new ThreadTest16();
		account.setBalance(10000); // 잔액을 10000원으로 셋팅

		// 익명 구현체로 쓰레드 구현
		Runnable test = new Runnable() {

			@Override
			public void run() {
				boolean result = account.withdraw(6000); // 6000원 출금하기
				System.out.println("쓰레드 안에서 result = " + result + ", 잔액 = " + account.getBalance());

			}
		};
		//================================================================================
		
		// 익명 구현체를 이용한 Thread객체 생성
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);

		th1.start();
		th2.start();
	}

}
