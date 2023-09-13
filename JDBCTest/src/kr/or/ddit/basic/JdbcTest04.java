package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * INSERT문 
 */
public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc_09", "java");
			System.out.println("=== 계좌 정보 추가하기 ===");
			System.out.print("계좌 번호 >> ");
			String bankNo = scan.next();
			
			System.out.print("은 행 명 >> ");
			String bankName = scan.next();
			
			System.out.print("예금주 이름 >> ");
			String userName = scan.next();
			
			/* 방법1)
			 * //Statement객체를 이용해서 추가하기 
			 * String sql =
			 * "insert into bankinfo(bank_no, bank_name, bank_user_name, bank_date) " +
			 * " values('" + bankNo + "', '"+ bankName + "','"+ userName +"',sysdate)";
			 * 
			 * System.out.println("확인용 sql : "+sql);
			 * 
			 * stmt = conn.createStatement();
			 * 
			 * //select문을 실행할 때는 excuteQuery()메서드 사용하고 //select문이 아닐 때(insert, update,
			 * delete 등)는 executeUpdate()메서드를 사용한다. //executeUpdate()메서드의 반환값 ==> 정수값(작업에
			 * 성공한 레코드 수)
			 * 
			 * int cnt = stmt.executeUpdate(sql);
			 */
			
			//방법2) 
			//PreparedStatement객체를 이용해서 추가하기
			//SQL문을 작성할 때  SQL문에 데이터가 들어갈 자리는 물음표(?)로 표시해서 작성한다.
			String sql = "insert into bankinfo(bank_no, bank_name, bank_user_name, bank_date) " 
						+ " values(?, ?, ?,sysdate)";
			
			// PreparedStatement객체 생성 ==> 객체를 생성할 때 SQL문을 인수값으로 넣어준다.
			pstmt = conn.prepareStatement(sql); //stmt는 ()안에 아무것도 없음
			
			//SQL문의 물음표(?)에 들어갈 데이터를 셋팅한다.
			//형식) PreparedStatement객체.set자료형이름(물음표번호, 셋팅할 데이터)
			//		==> 물음표번호는 1번부터 시작한다.
			
			pstmt.setString(1,bankNo);
			pstmt.setString(2,bankName);
			pstmt.setString(3,userName);
			
			//데이터 셋팅이 완료되면 SQL문을 실행한다. //stmt는 ()안에 sql있음
			int cnt = pstmt.executeUpdate();
						
			System.out.println("반환값 => "+cnt);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
	}

}
