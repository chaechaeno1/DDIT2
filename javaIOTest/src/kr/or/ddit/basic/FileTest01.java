package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		//File객체 만들기 연습
		
		//1. new File(String 파일 또는 경로)
		//==> 디렉토리와 디렉토리 사이 도는 디렉토리와 파일명 사이의 
		//	  구분 문자는 '/'를 사용하거나 '\'를 사용할 수 있다.
		
		
//		File file1 = new File("d:/d_other/test.txt"); // 구분 문자를 '/'로 사용
		File file1 = new File("d:\\d_other\\test.txt"); // 구분 문자를 '\'로 사용
		System.out.println("파일명 : "+file1.getName());
		System.out.println("디렉토리 여부 : "+file1.isDirectory());
		System.out.println("파일 여부 : "+file1.isFile());
		System.out.println();
		
		//폴더명으로 끝남 -> 디렉토리
		File file2 = new File("d:/d_other");
		System.out.println("파일명 : "+file2.getName());
		System.out.println("디렉토리 여부 : "+file2.isDirectory());
		System.out.println("파일 여부 : "+file2.isFile());
		System.out.println();
		
		
		
		
	}

}
