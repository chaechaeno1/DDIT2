package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	회원 관리를 하는 프로그램을 작성하시오.
 	(MYMEMBER 테이블 이용 - mem_id, mem_pass, mem_name, mem_tel, mem_addr)
 	
 	아래의 메뉴를 구성하고 각 메뉴의 기능을 구현하시오.
 	(CRUD 기능 구현)
 	메뉴예시)
 	== 작업 선택 ==
 	1. 자료 추가                --> C (insert)
 	2. 자료 삭제                --> D (delete)
 	3. 자료 수정                --> U (update)
 	4. 전체 자료 출력         --> R (select)
 	0. 작업 종료
 	=============
 	
 	조건)
 	1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받음)
 	2) 자료 삭제는 '회원ID'를 입력받아 처리한다.
 	3) 자료 수정은 '회원ID'는 변경되지 않는다.
 	
 	
 	
 	
 */

public class JdbcTest06 {
	Scanner sc = new Scanner(System.in);

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06().startMemberManagement();

	}

	// 프로그램 시작 메서드
	public void startMemberManagement() {
		System.out.println("==========================================");
		System.out.println("===============회원 관리 프로그램==============");
		System.out.println("==========================================");

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 추가
				insert();
				break;

			case 2: // 삭제
				delete();
				break;

			case 3: // 수정
				update();
				break;

			case 4: // 전체자료 출력
				select();
				break;

			case 0: // 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력하였습니다.");
				System.out.println("다시 입력하세요.");

			}
		}

	}

	// 전체 출력
	public void select() {

		try {

			conn = DBUtil.getConnection();
			String sql = "select mem_id, mem_pass, mem_name, mem_tel, mem_addr from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("==========================================");
				System.out.println("회원ID : " + rs.getString("mem_id"));
				System.out.println("회원PW : " + rs.getString("mem_pass"));
				System.out.println("회원이름 : " + rs.getString("mem_name"));
				System.out.println("연락처 : " + rs.getString("mem_tel"));
				System.out.println("주소 : " + rs.getString("mem_addr"));
				System.out.println("==========================================");

			}

		} catch (Exception e) {
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

	// 수정
	public void update() {

		try {
			// 조건3) 자료 수정은 '회원ID'는 변경되지 않는다.
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원ID 입력 >> ");
			String id = sc.next();

			// 등록된 ID인지 검증
			if (!isId(id)) {
				System.out.println("입력한 회원ID " + id + "는(은) 등록되지 않은 ID입니다.");
				System.out.println("등록된 ID를 입력하세요."); //
				System.out.println();
				return;
			}

			System.out.print("수정할 회원PW 입력 >> ");
			String pass = sc.next();
			System.out.print("수정할 이름 입력 >> ");
			String name = sc.next();
			System.out.print("수정할 전화번호 입력 >> ");
			String tel = sc.next();
			System.out.print("수정할 주소 입력 >> ");
			String addr = sc.next();

			conn = DBUtil.getConnection();

			String sql = "UPDATE mymember SET MEM_PASS = ?, MEM_NAME = ?, MEM_TEL = ?, MEM_ADDR =? WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);

			int rs = pstmt.executeUpdate();

			if (rs > 0) {
				System.out.println("회원정보 수정 완료!");
			} else {
				System.out.println("회원정보 수정 실패...");
			}

		} catch (Exception e) {
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

	// 삭제
	public void delete() {

		try {
			System.out.println("삭제할 회원 정보를 입력하세요.");
			// 조건2) 자료 삭제는 '회원ID'를 입력받아 처리한다.
			System.out.print("회원ID 입력 >> ");
			String id = sc.next();

			// 등록된 ID가 아닐 경우 삭제 작업 중단
			if (!isId(id)) {
				System.out.println("입력한 회원ID " + id + "는(은) 등록되지 않은 ID입니다.");
				System.out.println("등록된 ID를 입력하세요."); //
				System.out.println();
				return;
			}
			conn = DBUtil.getConnection(); // isID 메서드에서 이미 close 되어있기 때문에 if문 뒤에 와야함

			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			int cnt = pstmt.executeUpdate();

			if (cnt == 1) {
				System.out.println("delete 성공!!");
			} else {
				System.out.println("delete 실패...");
			}

		} catch (Exception e) {
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

	// 추가
	public void insert() {
		try {

			System.out.println("새로운 회원 정보를 입력하세요.");
			System.out.print("회원ID 입력 >> ");
			String id = sc.next();

			// 조건1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받음)
			if (isId(id)) {
				System.out.println("입력한 회원ID " + id + "는(은) 이미 등록된 ID입니다.");
				System.out.println("다른 ID로 다시 입력하세요.");
				System.out.println();
				return;
			}

			System.out.print("회원PW 입력 >> ");
			String pass = sc.next();
			System.out.print("회원이름 입력 >> ");
			String name = sc.next();
			System.out.print("회원 전화번호 입력 >> ");
			String tel = sc.next();
			System.out.print("회원 주소 입력 >> ");
			String addr = sc.next();

			conn = DBUtil.getConnection();

			String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ " values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);

			int rs = pstmt.executeUpdate();

			if (rs > 0) {
				System.out.println("회원 등록 완료!");
			} else {
				System.out.println("회원 등록 실패...");
			}

		} catch (Exception e) {
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

	// ID중복 확인 메서드 (boolean 사용하기)
	public boolean isId(String id) {
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM mymember WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
				return count > 0;
			}

		} catch (Exception e) {
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
		return false;

	}

	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	public int displayMenu() {
		System.out.print("----------------\n" + "다음 메뉴를 선택하세요.\n" + "1. 자료 추가\n" + "2. 자료 삭제\n" + "3. 자료 수정\n"
				+ "4. 전체 자료 출력\n" + "0. 작업 종료\n" + "----------------\n" + "메뉴선택 >>");
		return sc.nextInt();
	}

}
