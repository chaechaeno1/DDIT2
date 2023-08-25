package kr.or.ddit.basic;


import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		//Person()을 이용하여 기본생성자 호출
		p1.setNum(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("일지매");
		p2.setNum(1);
		p2.setName("홍길동");

		Person p3 = p1;
		
		System.out.println(p1 == p2); //false
		//p1과 p2의 주소값이 다름
		//p1과 p2 객체는 서로 다른 메모리 위치에 있으므로 == 비교 시 false가 반환
		System.out.println(p1 == p3); //true
		//p1의 값을 p3에 넣었으므로 같음
		//p1과 p3는 같은 메모리 위치를 가리키므로 == 비교 시 true가 반환
		System.out.println();
		
		System.out.println(p1.equals(p2));	
		// equlas는 Object에 구현되어있는 메서드
		// == 의 뜻을 의미
		System.out.println(p1.equals(p3));
		System.out.println();
		
		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("set의 개수 : "+testSet.size());
		System.out.println();
		
		System.out.println("p1 : "+ p1.hashCode());
		System.out.println("p2 : "+ p2.hashCode());
		System.out.println("p3 : "+ p3.hashCode());
		
		/* 챗GPT 설명
		 객체 비교:
		== : 연산자는 두 객체가 메모리에서 동일한 위치를 가리키는지 비교합니다.
		equals() 메서드:  두 객체의 내용이 같은지를 비교합니다.
		 */
		
		/* 수업 설명
		- equals()메서드 		==> 두 객체의 내용이 같은지 비교하는 메서드(동등성 비교)
		- HashCode()메서드	==> 두 객체가 같은 객체인지를 비교하는 메서드(동일성 비교)
		 */
		
		
		
	}

}



class Person{ // 뒤에 아무것도 없으면 'extends Object'가 생략되어진 뜻
	private int num;
	private String name;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//shift + alt + s -> hashCode() and equals()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	
	
	
	
	
	
	
//	//num변수의 값과 name변수의 값 두가지 모두 같으면 true를 반환하도록  재정의 해보자.
//	@Override
//		public boolean equals(Object obj) {
//			if(this==obj) { //참조값(주소값)이 같은지 검사
//				return true;
//			}
//			if(obj==null) {
//				return false;
//			}
//			if(this.getClass() != obj.getClass()) {
//				return false;
//			}
//			
//			Person that = (Person)obj; //매개변수의 값을 현재 객체 유형으로 형변환 한다.
//			
//			//각 변수값들이 모두 같은지 검사 
//			return this.num == that.num && Objects.deepEquals(this.name, that.name);
//			
//			
//		}
//	
//	@Override
//		public int hashCode() {
//			return Objects.hash(num, name);
//		}
//	
//	
//	
}