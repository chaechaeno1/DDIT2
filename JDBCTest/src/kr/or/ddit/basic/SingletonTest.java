package kr.or.ddit.basic;

public class SingletonTest {

	public static void main(String[] args) {
		//MySingleton test1 = new MySingleton(); 
		//외부에서 new 명령으로 생성 불가
		
		MySingleton test2 = MySingleton.getInstance();
		MySingleton	test3 = MySingleton.getInstance(); //한번만 호출됨
		
		System.out.println("test2 => " + test2.toString()); //참조값 같음
		System.out.println("test3 => " + test3); //참조값 같음
		
		System.out.println(test2 == test3); //true
		System.out.println(test2.equals(test3)); //true
		
		test3.displayTest();
		
		
		

	}

}
