package kr.or.ddit.basic;

/*
	제네릭 클래스 작성 방법
형식)
	class 클래스명<제네릭타입글자> {
		제네릭타입글자 변수명; // 변수 선언에 제네릭을 사용할 경우
		...
		
		제네릭타입글자 메서드명(매개변수들...){ // 메서드의 반환값에 제네릭을 사용할 경우
			~~~
			return 값;
		
		}
		
		반환값타입 메서드명(제네릭타입글자 변수명, ...){ //메서드의 매개변수에 제네릭을 사용할 경우
			~~~
			return 값;
		
		}
		
		
		}
	
 */




class NonGeneric{ //제네릭 쓰지 않음. 예전 방식.
	private Object value;
	
	public void setValue(Object value) {
		this.value=value;
	}
	
	public Object getValue() {
		return value;
	}
}

// T: Type 
// MyGeneric<String> mg1 = new MyGeneric<String>();
// T를 String으로 바꿔주는 것
class MyGeneric<T>{
	private T value;

	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}


	
	
	
	
	
	
	
	
}



public class GenericTest {

	public static void main(String[] args) {
		NonGeneric ng1 = new NonGeneric();
		ng1.setValue("가나다라");
		
		NonGeneric ng2 = new NonGeneric();
		ng2.setValue(100);
		
		String rtnNg1 = (String)ng1.getValue(); 
		//Obecjt에서 String 다운캐스트 되어야 하므로 오류발생, 강제형변환 해주면 됨
		System.out.println("문자열 반환값 rtnNg1 => "+rtnNg1);
		
		int rtnNg2 = (int)ng2.getValue();
		System.out.println("정수형 반환값 rtnNg2 => "+rtnNg2);
		System.out.println("=================================");
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		mg1.setValue("대덕인재개발원");
//		mg1.setValue(1000); --> 정수형이므로 오류 발생
		
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		mg2.setValue(200);
		
		String rtnMg1 = mg1.getValue();
		int rtnMg2 = mg2.getValue();
		
		System.out.println("제네릭 문자열 반환값 ===> "+rtnMg1);
		System.out.println("제네릭 정수형 반환값 ===> "+rtnMg2);
		
		

	}

}
