package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드로 구성된 class

// (dbinfo.properties파일의 내용을 이용하여 설정하기)
// ==> Properties객체 이용하기



public class DBUtil2 {
	static Properties prop;
	static {		
		prop = new Properties(); //properties() 객체 생성
		File f = new File("res/kr/or/ddit/jdbc/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);
			prop.load(fin);
						
			Class.forName(prop.getProperty("driver"));
			//Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ");
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(fin!=null) try {fin.close();} catch(IOException e) {} //스트림닫기
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
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));

		} catch (Exception e) {
			System.out.println("DB 연결 실패...");
			e.printStackTrace();
		}
		return conn;
		
	}
}
