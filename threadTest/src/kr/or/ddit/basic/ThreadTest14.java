package kr.or.ddit.basic;

//쓰레드에서 객체를 공통으로 사용하는 예제

/*
 	원주율을 계산하는 쓰레드와
 	계산된 원주율을 출력하는 쓰레드가 있다.
 	
 	원주율을 관리하는 객체가 필요
 	이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.
 	
 
 */

public class ThreadTest14 {

	public static void main(String[] args) {
	

	}

}


//원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;	//공통으로 사용할 객체의 참조값이 저장될 변수
}
//계산된 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread{
	private ShareData sd;	//공통으로 사용할 객체의 참조값이 저장될 변수
}

//원주율을 관리하는 객체(공통으로 사용할 객체)
class ShareData{
	public double result;		//계산된 원주율이 저장될 변수
	public boolean isOk;		//계산이 완료되었는지 여부를 나타내는 변수
	
}
