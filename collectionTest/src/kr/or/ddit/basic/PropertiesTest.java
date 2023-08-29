package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
	/*
	 	Properties 객체
	 	- Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
	 	
	 	- Map은 key값과 value값에 모든 종류의 객체를 사용할 수 있지만
	 	Properties는 key값과 value값에 String만 사용할 수 있다.
	 	
	 	- Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만
	 	Properties는 setProperty(), getProperty()메서드를 통해서 데이터를 입출력 한다.
	 	
	 	- Properties는 데이터를 파일로 입출력하는 기능이 있다.
	 	
	 */
		
		Properties prop = new Properties();
		// 제네릭 쓰지않음. value값이 String으로 고정되어있기 때문.
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		int age = 30;
		//String이 아니므로 오류 발생
		//정수형을 문자열로 바꾸는 방법 2가지
		prop.setProperty("age2", age+""); 
		prop.setProperty("age2", String.valueOf(age)); 
		
		prop.setProperty("tel", "010-1111-2222");
		prop.setProperty("addr", "대전");
		
		//=============================================================
		
		String name = prop.getProperty("name");
		int tempAge = Integer.parseInt(prop.getProperty("age"));
		int tempAge2 = Integer.parseInt(prop.getProperty("age2"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+tempAge);
		System.out.println("나이2 : "+tempAge2);
		System.out.println("연락처 : "+tel);
		System.out.println("주소 : "+addr);
	}

}
