package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class byteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];
		
		//스트림 객체 생성
		ByteArrayInputStream bin = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		try {
			// bin.available() ==> 읽어올 수 있는(남아있는) 데이터의 개수 반환
			while(bin.available()>0) {
				bin.read(temp); //배열 크기만큼 읽어오기
				bout.write(temp); //배열의 데이터 출력하기
			}
			
			outSrc = bout.toByteArray();
			
			System.out.println("inSrc => "+ Arrays.toString(inSrc));
			System.out.println("OutSrc => "+ Arrays.toString(outSrc));
			
			//스트림 닫기
			bin.close();
			bout.close();
			
		} catch (IOException e) {
			
		}

		
	}

}
