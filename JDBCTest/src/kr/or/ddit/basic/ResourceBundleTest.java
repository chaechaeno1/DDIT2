package kr.or.ddit.basic;

import java.io.File;
import java.util.ResourceBundle;

/*
ResourceBundle객체 ==> 파일의 확장자가 '.properties'인 파일의 내용을 읽어와
					  key값과 value값을 분리해서 정보를 갖고 있는 객체
 */

public class ResourceBundleTest {

	public static void main(String[] args) {
		//ResourceBundle객체를 이용하여 '.properties'파일 읽어오기
		
		//ResourceBundle객체 생성
		// ==> 읽어올 파일을 지정할 때 '패키지명'만 지정하고 
		//	      확장자(.properties)는 지정하지 않는다.
		//File f = new File("res/kr/or/ddit/jdbc/config/dbinfo.properties"); 와 입력양식이 다르나
		//출력값은 같음
		
		ResourceBundle bundle = 
				ResourceBundle.getBundle("kr.or.ddit.jdbc.config.dbinfo");
		
		// 읽어온 내용 출력 하기
		System.out.println("driver : "+bundle.getString("driver"));
		System.out.println("driver : "+bundle.getString("url"));
		System.out.println("driver : "+bundle.getString("user"));
		System.out.println("driver : "+bundle.getString("pass"));

	}

}
