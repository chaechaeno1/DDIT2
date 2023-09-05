package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		//바이트 기반 스트림을 이용한 파일로 출력하기 예제
		try {
			FileOutputStream fout = 
					new FileOutputStream("d:/d_other/out.txt");
			//파일 없으면 새로 만들어줌, 있으면 파일 덮어쓰기하므로 주의!!!
			
			for(char ch='A'; ch<='Z'; ch++) {
				fout.write(ch); // ch변수의 데이터를 파일로 출력
			}
			
			System.out.println("출력 작업 완료!!!");
			
			//스트림 닫기
			fout.close(); 
			
		} catch (IOException e) {
			
		}
		
		
		//
		

	}

}
