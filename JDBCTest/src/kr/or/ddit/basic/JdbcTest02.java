package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID값을 입력 받아 
//		LPROD_ID가 입력한 값보다 큰 자료들을 출력하시오.
// 
public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("LPROD_ID 입력 >> ");
		int input = sc.nextInt();
		System.out.println();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:xe","pc_09","java");
			
			String sql = "select * from lprod where lprod_id >" + input;
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
						
			System.out.println("===입력값과 LPROD_ID값 비교하여 입력값보다 큰 값만 출력하기===");
			System.out.println();
			while(rs.next()) {
				
				System.out.println("LPROD_ID : " + rs.getInt("lprod_id"));
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));
				System.out.println("LPROD_NM : " + rs.getString("lprod_nm"));
				System.out.println("==========================================");
				
			}
			System.out.println("출력 작업 끝!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//5. 사용했던 자원 반납 ==> 객체가 생성된 순서의 역순으로 닫아준다
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
			
		}
		

	}


