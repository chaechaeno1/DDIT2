package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
	/*
		한글이 저장된 파일 읽어오기
		(한글의 인코딩 방식을 지정해서 읽어오기)
	*/
		
		try {
			//FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			//파일 저장시 인코딩 방식을 ANSI로 했기 떄문에 출력시 오류
			//이클립스 환경설정에서 대부분의 기본값을 utf-8로 주었기 때문에
			
			//FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			
			//바이트 기반 스트림
			FileInputStream fin = new FileInputStream("d:/d_other/test_utf8.txt");
			
			//(1) 기본 인코딩 방식으로 읽어온다.
			//InputStreamReader isr = new InputStreamReader(fin);
			
			//(2) 인코딩 방식 지정해서 읽어오기
			// 인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI 방식)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			InputStreamReader isr = new InputStreamReader(fin, "utf-8");
			
			
			
			int c;
			while( (c=isr.read()) != -1) {
				System.out.print( (char)c );
			}
			
			isr.close();
			
		} catch (IOException e) {
			
		}
		

	}

}
