package kr.or.ddit.basic;

/*
	10마리의 말들이 경주하는 프로그램 작성하기
	
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
	이 클래스는 말이름(String), 등수(int), 현재위치(int)를
	멤버변수로 갖는다.
	
	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다.
	(Comparable 인터페이스 구현)
	
	<조건>
	
	1. 경기 구간은 1~50구간으로 되어있다. (반복문 사용)
	
	2. 경기가 끝나면 등수 순으로 출력한다.
	
	3. 경기 중 중간 중간에 아래와 같이 각 만들의 위치를 나타내시오.
	
	
	<예시> -> 말 클래스에 경기 구간 들어가고, 중간중간 나타내는것도 쓰레드로 표현	
		01번말  : ---->--------------------------------
		02번말  : -------------->----------------------
		...
		10번말  : ---------->--------------------------
		
	
	
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadTest12 {
	public static void main(String[] args) {
		//10마리 말 들어갈 리스트 생성
		List<Horse> horses = new ArrayList<>();

		//10마리 말 생성
		for (int i = 1; i <= 10; i++) {
			Horse horse = new Horse(i+"번 말");
			//리스트에 추가
			horses.add(horse);
			//경주 시작
			horse.start();
		}

		//모든 말들의 경주가 끝날때까지 대기
		for (Horse horse : horses) {
			try {
				horse.join();
			} catch (InterruptedException e) {
			}
		}
		
		//말들의 위치에 따라 정렬, 등수 순서 출력
		Collections.sort(horses); //내부정렬 이용하여 등수 순으로 출력
		System.out.println("======경기결과======");
		for (int i = 0; i < horses.size(); i++) {
			System.out.println(horses.get(i).getResult(i + 1));
			//horses.get(i) -> 이름출력
			//.getResult(i + 1) -> 등수 ++
			
		}
	}
}


//Horse 쓰레드 클래스
//멤버변수 : 말이름(String), 등수(int), 현재위치(int)
class Horse extends Thread implements Comparable<Horse> {
	String name; //이름
	int rank; //등수
	int location = 0; //현재위치

	public Horse(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		while (location < 50) { // 구간 1~50
			location += (int) (Math.random() * 3) + 1; //3칸씩 랜덤으로
			printHorsePosition(); //현재 위치 출력
		}
	}

	//말의 위치 출력
	private void printHorsePosition() {
		String position = "";
		for(int i = 1; i <= 50; i++) {
			if(i == location) {
				position += ">"; //현재위치 > 표시
			}else {
				position += "-";
			}
		}
		System.out.println(name+" : "+position); //이름, 위치 출력
	}
	
	//등수, 이름 반환 -> 출력
	public String getResult(int rank) {
		return name+" : "+rank+"등";
	}

	// Comparable 인터페이스 -> compareTo 메서드 구현
	// 말들을 등수(오름차순)대로 정렬
	@Override
	public int compareTo(Horse other) {
		return Integer.compare(this.location, other.location);
	}
}
