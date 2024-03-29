package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 Buffered스트림 사용 예제

		try {
			//이클립스에서 자바 프로그램이 실행되는 현재 위치는 
			//해당 프로젝트 폴더가 현재 위치가 된다.
			FileReader fr 
//					= new FileReader("C:/Users/PC-09/git/DDIT2/javaIOTest/src/kr/or/ddit/basic/FileTest01.java");
					= new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			//문자 기반의 Buffered스트림에는 줄 단위로 입출력하는 기능이 있다.
			String temp = "";
			
			for(int i=1; (temp= br.readLine()) !=null; i++){
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close();
			
		} catch (IOException e) {

		}

	}

}
