package kr.or.ddit.basic;
/*
 1~20억까지의 합계를 구하는 프로그램을
  하나의 쓰레드(싱글 쓰레드)(1개)가 단독으로 처리할 때와
  여러개의 쓰레드(멀티 쓰레드)(4개)가 협력해서 처리할 때의 경과 시간을 비교
  
 */

import com.sun.webkit.ThemeClient;

//쓰레드를 만드는 것 == 쓰레드 객체를 생성하는 것

public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리하는 쓰레드 객체 생성
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		//협력해서 처리할 쓰레드 객체 생성
		//4개의 쓰레드 생성
		SumThread[] sumArr = new SumThread[]{
			new SumThread(1L, 500_000_000L),
			new SumThread(500_000_001L, 1_000_000_000L),
			new SumThread(1_000_000_001L, 1_500_000_000L),
			new SumThread(1_500_000_001L, 2_000_000_000L),
		};	
		
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때 경과 시간 : "+(endTime-startTime));
		System.out.println();
		System.out.println("=========================================");
		
		
		//협력해서 처리하기
		startTime = System.currentTimeMillis();
		
		// 중요한 점은 쓰레드들이 순서대로 결과를 내지 않을 수 있다는 거예요!
		// 여러 개의 쓰레드를 배열에 넣어두고, 각 쓰레드를 시작시킵니다.
		for(SumThread th : sumArr) {
			th.start(); // 쓰레드를 시작하면 그 안에 있는 작업이 실행됩니다.
		}
		// 이제 쓰레드들이 일을 다 마칠 때까지 기다립니다.
		// 각각의 쓰레드가 끝날 때까지 기다리는 역할을 하는게 바로 'join'이에요.
		// 그런데 중간에 누군가가 방해를 한다면(InterruptedException),
		// 그 예외 상황을 처리해줘야 합니다.
		for(SumThread th : sumArr) {
			try {
				th.join(); // 쓰레드가 끝날 때까지 기다립니다.
			} catch (InterruptedException e) {
			}
		}
		
		// 모든 쓰레드가 일을 마친 후에 시간을 다시 재서
		// 전체 작업에 걸린 시간을 계산합니다.
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리한 경과 시간 : "+(endTime - startTime));

	}

}


//쓰레드는 여러개지만 클래스는 하나만 만들어도 됨
class SumThread extends Thread{
	private long start; //합계를 구할 영역의 시작값과 종료값이 저장될 변수 선언
	private long end;
	
	//생성자
	public SumThread(long start, long end) {
		this.start = start;
		this.end = end;
	} 
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i=start; i<=end; i++) {
			sum += i;
		}
		System.out.println(start+"부터"+end+"까지의 합계 : "+sum);
	}
	

	
	
}


