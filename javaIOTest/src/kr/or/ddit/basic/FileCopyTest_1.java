package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 문제)
 	'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 
 	'd:/d_other'연습용 폴더에 '펭귄_복사본.jpg'파일로 복사하는 프로그램을 작성하시오. 
 */

public class FileCopyTest_1 {

	public static void main(String[] args) {
		// 바이트 기반? 문자 기반? => 바이트 기반
		// 이미지 이므로 바이트 기반

		// 복사할 파일 생성(존재하지 않으면 오류 발생)
		File file = new File("d:/d_other/펭귄.jpg");

		if (!file.exists()) {
			System.out.println(file.getPath() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중단 합니다.");
			return;
		}

		try {

			// 입력용 스트림
			FileInputStream fin = new FileInputStream(file);
			// 출력용 스트림
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");

			// 버퍼스트림
			BufferedInputStream bin = new BufferedInputStream(fin);
			BufferedOutputStream bout = new BufferedOutputStream(fout);

			System.out.println("복사 시작!");

			// 배열을 이용한 입출력 작업
			byte[] temp = new byte[1024];
			int len = 0;
			while ((len = bin.read(temp)) != -1) {
				bout.write(temp, 0, len);
			}

			bout.close();
			bin.close();

			System.out.println("복사 완료!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
