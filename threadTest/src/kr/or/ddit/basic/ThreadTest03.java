package kr.or.ddit.basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		//쓰레드가 수행되는 시간을 체크하기
		Thread th = new Thread(new MyRunner());
		/* 아래와 같은 형식임
		 * MyRunner2 r2 = new MyRunner2(); 
		 * Thread th2 = new Thread(r2);
		 */
		
		//1970년 1월1일 0시0분0초(표준시간) 부터 현재까지 경과한 시간을
		//밀리세컨드(1/1000 초) 단위로 반환.
		//예) 3분 경과 ==> 3*60*1000 = 180000
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join(); //현재 실행 위치에서 대상이 되는 쓰레드(지금은 변수 th)가 
						//종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과 시간 : "+(endTime - startTime));
		
		
		

	}

}

//방법 2-1 사용
class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0;
		for(long  i=1L; i<=1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : "+ sum);
		
	}
	
}
