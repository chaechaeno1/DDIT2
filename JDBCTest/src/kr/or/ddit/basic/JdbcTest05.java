package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*
 * LPROD테이블에 새로운 데이터를 추가
 * 조건) lprod_gu값과 lprod_nm값은 직접 입력받아서 처리하고,
 * 		lprod_id값은 현재의 lprod_id값 중에서 제일 큰 값보다 1 크게 한다. -> select max(lprod_id) from lprod;
 * 
 * 		입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아서 처리 한다. -> select count(*) from lprod where lprod_gu = 입력값;
 * 		==> 0이면 없고 1이면 있는 것
 * 
 */

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String gu = sc.nextLine();
		System.out.println("lprod_gu값 입력 >> ");
		String nm = sc.nextLine();
		System.out.println("lprod_nm값 입력 >> ");
		System.out.println();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","pc_09","java");
			
			String id = "select max(lprod_id) from lprod" + 1;			
			String gu = "select count(*) from lprod where lprod_gu = 입력값;"
	
			
		} catch (Exception e) {
			
		}
		

	}

}
