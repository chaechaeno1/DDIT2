package kr.or.ddit.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로 구성된 class

// (dbinfo.properties파일의 내용을 이용하여 설정하기)
// ==> ResourceBundle객체 이용하기

public class DBUtil3 {
	static ResourceBundle bundle; //ResourceBundle객체 변수 선언
	
	static {
		// 객체 생성
		bundle = ResourceBundle.getBundle("kr.or.ddit.jdbc.config.dbinfo");
		
		try {
			Class.forName(bundle.getString("driver"));				
			//Class.forName(prop.getProperty("driver"));
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		} 
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			/*
			 * conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe",
			 * "pc_09", "java");
			 */
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));

		} catch (Exception e) {
			System.out.println("DB 연결 실패...");
			e.printStackTrace();
		}
		return conn;
		
	}
}
