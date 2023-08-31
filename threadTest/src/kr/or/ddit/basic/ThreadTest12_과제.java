package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
10마리의 말들이 경주하는 프로그램 작성하기
*/

public class ThreadTest12_과제 {

	public static void main(String[] args) {
		//10마리 말 들어갈 리스트 생성
		List<Horse2> horses = new ArrayList<>();
		
		//10마리 말 생성
		for(int i = 1; i <10 ; i++) {
			Horse2 horse = new Horse2(i+"번 말"); //말 이름 설정
			
			//리스트에 추가
			horses.add(horse);
			
			//경주시작
			horse.start();
			
		}
		
		//모든 말의 경주가 끝날 때 까지 대기하기
		for(Horse2 horse : horses) {
			try {
				horse.join();
			} catch (InterruptedException e) {			
			}
			
		}
		
		// 들어온 순서대로 출력
		Collections.sort(horses); //말 객체들을 등수순으로 출력
		System.out.println("======경기결과======");
		for(int i=0; i<horses.size();i++) {
			System.out.println(horses.get(i).getResult(i+1));
			// horses.get(i) -> i번째 말 객체를 가져옴 (이름 출력)
			// .getResult(i + 1) -> 해당 말의 등수를 i+1로 설정하여 결과 출력
		}

	}

}

//쓰레드 클래스 명 : Horse
//멤버변수 : 말이름(String), 등수(int), 현재위치(int)
class Horse2 extends Thread implements Comparable<Horse2> {
	String name;
	int rank;
	int curruntLoc = 0; // 출발 전이므로 0으로 초기화

//	생성자
//	각각의 말이 고유한 이름을 가지고 경주에 참여하기 위함.
//	각 말은 서로 다른 이름을 가져야 하며, 이 이름은 그 말이 어떤 말인지를
//	식별하기 위해 사용. 이러한 이유로 매개변수로 이름을 받아야 함.
	public Horse2(String name) {
		this.name = name;
	}

// 조건1) 경기 구간 : 1~50구간 (반복문 사용)		
// 조건3) 경기 중 중간 중간에 아래와 같이 각 만들의 위치 나타내기
//	<예시> 
//		01번말  : ---->--------------------------------
//		02번말  : -------------->----------------------
//		...
//		10번말  : ---------->--------------------------
//			
	@Override
	public void run() {
		while (curruntLoc < 50) { // 구간 1~50
			curruntLoc += (int) (Math.random() * 4) + 1; // 4칸씩 랜덤으로 이동
			printHorsePosition();
		}

	}
	
//조건3) 경기 중 중간 중간에 아래와 같이 각 만들의 위치 나타내기 -> 출력 메서드
	private void printHorsePosition() {
		String position =""; //위치 나타내는 문자열 초기화
		for(int i=1; i <= 50; i++) { //경기구간 1~50
			if(i==curruntLoc) { //현재 위치에 도달하면
				position += ">"; // 현재위치에 > 표시
			}else { //현재위치가 아니면
				position +="-"; // 문자열에 - 표시
			}
		}
		System.out.println(name+" : "+position); //이름, 위치 출력
		//name : 말 객체의 이름 
		//position : 구간별로 -와 >로 이루어진 문자열로 현재 위치를 시각적으로 표현
	}
	
	//등수, 이름 반환 -> 출력!
	public String getResult(int rank) {
		return name+" : "+rank+"등";
	//rank(등수)값은 메인 클래스에서 경기 종료 후 등수 순서대로 전달
		
	}
	
	// 등수 오름차순 내부 정렬 (Comparable 인터페이스)
	// 조건2) 경기가 끝나면 등수 순으로 출력
	@Override
	public int compareTo(Horse2 other) {
		return Integer.compare(this.curruntLoc, other.curruntLoc);
	//compareTo 메서드는 Comparable 인터페이스를 구현하여
	//말 객체들을 위치를 기준으로 정렬하는 역할
	//Integer.compare 메서드는 두 값을 비교하여 결과에 따라 음수, 양수, 또는 0을 반환
	//this.location이 other.location보다 작다면, 음수가 반환. 
	//이 경우 현재 말 객체는 앞서 위치한 말 객체보다 앞서 정렬
	//this.location이 other.location보다 크다면, 양수가 반환. 
	//이 경우 현재 말 객체는 뒤에 위치한 말 객체보다 뒤에 정렬
	//두 위치가 같다면, 0이 반환. 이 경우 두 말 객체의 순서는 그대로 유지
	//this.curruntLoc : 현재 말 객체의 위치
	//other.curruntLoc : 비교 대상 말 객체의 위치
	}

}
