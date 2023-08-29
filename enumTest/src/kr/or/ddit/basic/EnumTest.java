package kr.or.ddit.basic;


/*
 	enum(열거형) ==> 서로 관련있는 상수들의 집합
 			   	==> 클래스처럼 보이게 하는 상수
 	
 	- 작성 위치
 		1) 일반 클래스처럼 독립된 java파일로 작성 가능
 		2) 하나의 java파일에 클래스와 같이 작성 가능
 		3) 클래스 안에 내부 클래스처럼 작성 가능
 		
 	- 열거형의 속성 및 메서드
 	1) name() 		==> 열거형 상수의 이름을 문자열로 반환
 	2) ordinal()	==> 열거형 상수가 정의된 순서값(index값)을 반환(0부터 시작)
 	3) valueOf("상수명")  ==> 지정된 열거형에서 '상수명'과 일치하는 열거형 상수를 반환
 	4) 열거형이름.상수명	==> valueOf()메서드와 같음
 	5) 열거형이름.values() ==> 열거형의 모든 상수들을 배열에 담아 반환  
 	
 	- 열거형 선언하기
 	방법1)
 		enum 열거형이름 { 상수명1, 상수명2, ..., 상수명n }
 	방법2) ==> 열거형 상수에 데이터를 셋팅해서 처리
 		enum 열거형 이름{
 			상수명1(값들...), 상수명2(값들...),... 상수명n(값들...);
 			
 			//'값들'이 저장될 변수들을 선언한다. ('값들' 개수에 맞게 변수 선언)
 			 private 자료형이름 변수명1;
 			 private 자료형이름 변수명2;
 			 private 자료형이름 변수명3;
 			 ~~~
 			 private 자료형이름 변수명n;
 			 
 			 //열거형의 생성자를 만든다.
 			 // ==> 열거형의 생성자는 '열거형 상수에 지정된 값들'을 '변수'에 셋팅하는 역할을 한다.
 			 // ==> 열거형의 생성자는 묵시적으로 접근제한자가 'private' 이다.
 			  
 			  //'변수명'은 '값들'과 개수가 같고, 각각의 '값들'과 자료형이 맞아야 한다.
 			  private 열거형이름(자료형 변수명, ...){
 			  	위에 선언한 변수들을 초기화하는 작업을 한다.
 			  	...
 			  }
 			  
 			  //위에서 구성된 '값들'을 외부에서 불러올 수 있는 getter메서드를 작성한다.
 			 
 			
 		}
 	
 	
 */


public class EnumTest {
	
	//작성위치 3번 방법(클래스 안에 내부 클래스처럼 작성 가능)
	public enum Color {RED, GREEN, BLUE}
	// Color 열거형의 RED, GREEN, BLUE 상수가 있다.
	
	public enum Count {ONE, TWO, THREE}
	

	public static void main(String[] args) {
/*//		/*
//		 * System.out.println("Red : "+ConstTest.RED);
//		 * System.out.println("Three : "+ConstTest.THREE);
//		 * 
//		 * if(ConstTest.RED == ConstTest.TWO) { System.out.println("숫자가 다릅니다....");
//		 * }else { System.out.println("숫자가 같습니다!"); }
//		 */

		Color myCol = Color.valueOf("GREEN"); //Color.GREEN; 과 같음.
		Count myCnt = Count.ONE; //Count.valueOf("ONE");과 같음.
		
		
		// name() : 열거형 상수의 이름을 문자열로 반환
		System.out.println("myCol : "+ myCol.name());
		System.out.println("myCnt : "+ myCnt.name());
		System.out.println();
		
		// ordinal() : 열거형 상수가 정의된 순서값(index값)을 반환(0부터 시작)
		System.out.println("myCol ordinal :"+ myCol.ordinal());
		System.out.println("myCnt ordinal :"+ myCnt.ordinal());
		System.out.println();
		
		//myCol == myCnt 오류발생
		//myCol의 상수와 myCont의 상수 종류가 다름
		//서로 다른 종류의 열거형 끼리의 비교 불가
//		if(myCol == myCnt) {
//			System.out.println("같다.");
//		}
		
		//if문으로 비교
		if(myCol == Color.GREEN) {
			System.out.println("같다.");
		}else {
			System.out.println("다르다.");
		}
		
		//switch문으로 비교
		//case에 열거형 이름 생략하기(Count.THREE 으로 작성하면 안됨)
		//열거형을 switch문에서 사용할 때 case문에는 '열거형 이름'을 생략
		//'상수명'만 지정해서 비교
		switch(myCnt) {
			case ONE:
				System.out.println("ONE 상수값"); break;
			case TWO:
				System.out.println("TWO 상수값"); break;		
			case THREE:
				System.out.println("THREE 상수값"); break;	
		}
		
		
		System.out.println("===================================");
	
		
	}

}
