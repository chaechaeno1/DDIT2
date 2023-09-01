package kr.or.ddit.basic;

import java.util.Arrays;

/*
10마리의 말들이 경주하는 프로그램 작성하기

말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 이 클래스는 말이름(String), 등수(int), 현재위치(int)를
멤버 변수로 갖는다. 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다.
(Comparable 인터페이스 구현)

조건)

1) 경기 구간은 1 ~ 50구간으로 되어 있다.
2) 경기가 끝나면 등수 순으로 출력한다.
3) 경기 중 중간 중간에 아래와 같이 각 말들의 위치를 나타내시오.
예시)
  01번말 : -------->--------------------------------
  02번말 : -------------->--------------------------
  ...
  10번말 : ----------->-----------------------------
*/

public class ThreadTest12_수업 {

	public static void main(String[] args) {
		Horse3[] horses = new Horse3[] { new Horse3("01번말"), new Horse3("02번말"), new Horse3("03번말"), new Horse3("04번말"),
				new Horse3("05번말"), new Horse3("06번말"), new Horse3("07번말"), new Horse3("08번말"), new Horse3("09번말"),
				new Horse3("10번말") };

		GameBoard gb = new GameBoard(horses);

		// 경주 시작 ==> 쓰레드 시작
		for (Horse3 h : horses) {
			h.start();
		}
		gb.start();

		// 모든 말의 경기가 끝날때까지 기다린다.
		for (Horse3 h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {

			}
		}

		try {
			gb.join();
		} catch (InterruptedException e) {

		}

		System.out.println();
		System.out.println("경기 끝!!");
		System.out.println();
		
		//등수의 오름차순으로 정렬
		Arrays.sort(horses);
		
		System.out.println("======경기결과======");
		for (Horse3 h : horses) {
			System.out.println(h);
		}
	}

}

/*
 * 말은 Horse라는 이름의 쓰레드 클래스로 작성하는데 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버 변수로
 * 갖는다. 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기능이 있다. (Comparable 인터페이스 구현)
 */

class Horse3 extends Thread implements Comparable<Horse3> {
	// 등수를 구하기 위한 변수(각 말들(쓰레드들)이 공통으로 사용)
	public static int curRank = 0;

	private String horseName; // 말이름
	private int rank; // 등수
	private int location; // 현재위치

	// 생성자
	public Horse3(String horseName) {
		this.horseName = horseName;
	}

	// 게터세터
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다.";
	}

	// 등수의 오름차순을 정렬하는 정렬 기준 구성하기
	@Override
	public int compareTo(Horse3 horse) {
		return Integer.compare(this.rank, horse.getRank());
	}

	@Override
	public void run() {
		// 경기 구간은 1 ~ 50 구간
		for (int i = 1; i <= 50; i++) {
			this.location = i; // 말의 현재 위치 지정
			try {
				Thread.sleep((int) (Math.random() * 500)); // 0~499사이의 난수
			} catch (InterruptedException e) {

			}
		} // 현재 말의 경기가 끝난다. ==> 등수를 구해서 저장한다.
		curRank++;
		this.rank = curRank;

	}

}

/*
 * 3) 경기 중 중간 중간에 아래와 같이 각 말들의 위치를 나타내시오. 예시) 01번말 :
 * -------->-------------------------------- 02번말 :
 * -------------->-------------------------- ... 10번말 :
 * ----------->-----------------------------
 */

class GameBoard extends Thread {
	private Horse3[] horses;

	// 생성자
	public GameBoard(Horse3[] horses) {
		this.horses = horses;
	}

	@Override
	public void run() {
		while (true) {
			// 모든 말들의 경기가 종료되었는지 여부를 검사
			if (Horse3.curRank == horses.length) {
				break;
			}
			//각 텀 사이의 빈 줄 추가
			for(int i=1;i<=15;i++) {
				System.out.println();
			}
				
			for (Horse3 h : horses) {
				System.out.print(h.getHorseName() + " : ");

				for (int i = 1; i <= 50; i++) {
					if (h.getLocation() == i) {// 말의 현재 위치 확인
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				System.out.println(); // 줄바꿈

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}

	}
}
