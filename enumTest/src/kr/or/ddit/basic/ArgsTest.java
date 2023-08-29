package kr.or.ddit.basic;

public class ArgsTest {

	/*
	 * 접근제한자 반환값타입 메서드명( 매개변수들... ){
	 * 
	 * }
	 */

	/*
	 * 매개변수: 마치 컴퓨터 프로그램을 만들 때 사용하는 숫자나 정보들을 넣어주는 '상자'나 '통' 같은 것이에요. 이러한 매개변수들은
	 * 프로그램이 어떤 동작을 하거나 값을 계산할 때 사용
	 */

//	public static void test(int a, int b) {
//		
//	}

	// 매개변수로 받은 정수들의 합계를 구하는 메서드
	// (단, 이 정수들의 개수는 상황에 따라 다를 수 있다.)
	// 배열 이용하기
	public int sumArr(int[] data) {
		int sum = 0;

		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
	
		return sum;
	}

	//가변형 인수 ==> 메서드를 호출할 때의 인수가 호출할 때 마다 다를때 사용한다.
	//
	
	public static void main(String[] args) {
		// test(10, 20);
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100, 200, 300, 400};
		// (1) 배열 선언과 초기화를 동시에 할 때
		
		// (2) 배열 선언과 초기화를 동시에 할 수도 있고 따로 할 수 도 있다.
		int[] nums2; // 배열 선언
		nums2 = new int[]{1, 2, 3, 4}; // 초기화
		
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[]{1, 2, 3, 4, 5}));
		
		
		

	}

}
