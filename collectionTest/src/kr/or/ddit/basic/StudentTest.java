package kr.or.ddit.basic;

/*
 * 클래스 main 메서드 밖에다가 만들기 !!! 오류 조심!!!!
 */

/*
문제) 학번(int), 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를
멤버로 갖는 Student클래스를 만든다.
-	이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화.
-	(이 때 총점은 세 과목의 점수를 이용해서 초기화)

-	이 Student객체는 List에 저장하여 관리.

-	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성.
	정렬 결과를 출력하시오.
	
-	(등수는 List에 전체 데이터가 추가된 후에 구해서 저장) ==> StudentTest클래스에서 처리
	

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentTest {

	// 등수를 구하는 메서드
	public void createRank(List<Student> list) {
		for (Student std1 : list) { // 등수를 구할 기준 데이터를 구하기 위한 반복문
			int rank = 1; // 처음에는 1등으로 설정해 놓고 시작한다.

			for (Student std2 : list) { // 비교 대상을 나타내는 반복문
				// 기준값보다 큰 값을 만나면 rank변수의 값을 증가 시킨다.
				if (std1.getTot() < std2.getTot()) {
					rank++;
				}
			}

			// 구해진 등수를 기준 데이터의 rank변수에 저장한다.
			std1.setRank(rank);
		}
	}

	public static void main(String[] args) {
		List<Student> stdList = new ArrayList<Student>();

		stdList.add(new Student(1, "홍길동", 75, 90, 60));
		stdList.add(new Student(3, "성춘향", 75, 90, 60));
		stdList.add(new Student(7, "강감찬", 80, 80, 75));
		stdList.add(new Student(5, "변학도", 100, 60, 75));
		stdList.add(new Student(2, "일지매", 80, 95, 100));
		stdList.add(new Student(4, "이순신", 80, 95, 100));
		stdList.add(new Student(6, "이몽룡", 80, 95, 100));

		// 등수를 구하는 메서드 호출하기
		StudentTest test = new StudentTest();
		test.createRank(stdList);

		System.out.println("정렬 전...");
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("================================");
		System.out.println();

		Collections.sort(stdList);
		System.out.println("학번의 오름차순 정렬 후...");
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("================================");
		System.out.println();

		Collections.sort(stdList);
		System.out.println("총점의 내림차순 정렬 후...");
		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("================================");
		System.out.println();

	}
}

	/*
	 * 문제) 학번(int), 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다. - 이 클래스의
	 * 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화. - (이 때 총점은 세 과목의 점수를 이용해서
	 * 초기화)
	 * 
	 * -- 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준 구현
	 */

	class Student implements Comparable<Student> {
		private int num; // 학번
		private String name; // 이름
		private int kor; // 국어점수
		private int mat; // 수학점수
		private int eng; // 영어점수
		private int tot; // 총점
		private int rank; // 등수

		// 생성자
		public Student(int num, String name, int kor, int mat, int eng) {
			super();
			this.num = num;
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.mat = mat;
			tot = kor + eng + mat;
		}

		// getter setter
		// Student
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

		public int getKor() {
			return kor;
		}

		public void setKor(int kor) {
			this.kor = kor;
		}

		public int getMat() {
			return mat;
		}

		public void setMat(int mat) {
			this.mat = mat;
		}

		public int getEng() {
			return eng;
		}

		public void setEng(int eng) {
			this.eng = eng;
		}

		public int getTot() {
			return tot;
		}

		public void setTot(int tot) {
			this.tot = tot;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		// ---
		@Override
		public String toString() {
			return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", mat=" + mat + ", eng=" + eng
					+ ", tot=" + tot + ", rank=" + rank + "]";
		}

		// 학번의 오름차순 정렬 기준 만들기
		@Override
		public int compareTo(Student std) {
			return Integer.compare(this.getNum(), std.getNum());
		}

	}

//총점의 역순으로 정렬, 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스
	class SortByTotal implements Comparator<Student> {

		@Override
		public int compare(Student std1, Student std2) { // 0, 양수, 음수로 데이터 순서 정렬
			if (std1.getTot() == std2.getTot()) {
				return std1.getName().compareTo(std2.getName());
			} else {
				return Integer.compare(std1.getTot(), std2.getTot()) * -1;
			}

		}
	}
