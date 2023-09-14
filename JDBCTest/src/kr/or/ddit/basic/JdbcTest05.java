package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc_09", "java");

			// (1) lprod_id값 구하기 ==> 현재의 lprod_id값 중 제일 큰 값보다 1 큰 값으로 한다.
			String sql = "select nvl(max(lprod_id),0) maxnum from lprod"; // maxnum은 alias
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery(); // 쿼리 실행
			int maxnum = 0;
			if (rs.next()) {
				maxnum = rs.getInt("maxnum"); // alias명 이용
				// maxnum = rs.getInt(1); //컬럼 번호 이용
			}
			maxnum++; // 제일 큰 값 증가하기
			// ------------------------------------------------------------------
			// (2) 입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아서 처리

			String gu; // 상품 분류 코드가 저장될 변수 선언
			int count = 0; // 검색한 상품 분류 코드의 개수가 저장될 변수 선언

			String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
			pstmt = conn.prepareStatement(sql2);

			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = sc.nextLine();

				pstmt.setString(1, gu);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요.");
					System.out.println();
				}

			} while (count > 0);

			// ------------------------------------------------------------------

			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = sc.next();

			// ------------------------------------------------------------------

			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm)" + " values(?,?,?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxnum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("insert 성공!!");
			} else {
				System.out.println("insert 실패...");
			}

		} catch (ClassNotFoundException e) { //exception 위에 다른 예외사항 선언
			e.printStackTrace();
		} catch (Exception e) { //모든사항 예외처리하므로 이 아래에 다른 예외처리를 하면 오류발생
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}

}
