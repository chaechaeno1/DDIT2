package kr.or.ddit.basic;

//3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데

//출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기

public class ThreadTest11 {

	public static void main(String[] args) {
		DisplayCharracter[] dcArr = new DisplayCharracter[] { new DisplayCharracter("홍길동"),
				new DisplayCharracter("이순신"), new DisplayCharracter("강감찬") };
		
		for(DisplayCharracter dc : dcArr) {
			dc.start();
		}
		for(DisplayCharracter dc : dcArr) {
			try {
				dc.join();
			}catch (InterruptedException e) {
				
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순 위 : "+DisplayCharracter.ranking);
		
	}

}

// A~Z까지 출력하는 쓰레드
class DisplayCharracter extends Thread {
	public static String ranking = "";
	private String name;

	// 생성자
	public DisplayCharracter(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + "의 출력 문자 ==> " + c);
			try {
				// 0~499까지의 난수를 이용하여 일시 정지 시킨다.
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {

			}
		}
		System.out.println(name + "출력 끝!!!");
		// 출력을 끝낸 순서대로 이름을 배치
		ranking += name + " ";

	}

}