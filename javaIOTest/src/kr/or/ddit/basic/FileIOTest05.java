package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

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
			
			FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			
			int c;
			while( (c=fr.read()) != -1) {
				System.out.print( (char)c );
			}
			
			fr.close();
			
		} catch (IOException e) {
			
		}
		

	}

}
