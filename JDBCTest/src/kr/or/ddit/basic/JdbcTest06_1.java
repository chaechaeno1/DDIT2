package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

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

public class JdbcTest06_1 {
	private Scanner sc = new Scanner(System.in);
		
	public static void main(String[] args) {
		new JdbcTest06_1().startMember();

	}
	
	//시작메서드
	public void startMember() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1 : //추가
				insertMember();
				break;
			case 2 : //삭제
				deleteMember();
				break;
			case 3 : //수정 ==> 전체 항목 수정
				updateMember();
				break;
			case 4 : //전체출력
				displayAllMember();
				break;
			case 5 : //수정 2 ==> 원하는 항목 수정
				updateMember2();
				break;	
			case 0 : //종료
				System.out.println("작업을 마칩니다...");
				return;
			default:
				System.out.println();
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요...");
			}
		}
		
	}
	
	//회원 정보 중 원하는 항목만 수정하는 메서드
	private void updateMember2() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("수정할 회원정보를 입력하세요.");
		System.out.print("수정할 회원 ID >>");
		String id = sc.next();
		
		if(getMemIdCount(id)==0) {
			System.out.println("입력하신 "+id+"은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		int num; //수정 항목 선택 번호가 저장될 변수
		String updateField = null; //수정할 컬럼명이 저장될 변수
		String updateTitle = null; //수정할 데이터를 입력 받을 때 출력할 메시지 
		
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("1.비밀번호    2.회원이름     3.전화번호    4.회원주소");
			System.out.println("=======================================");
			System.out.print("수정할 항목 선택 >> ");
			num = sc.nextInt();
			
			switch(num) {
			case 1: updateField ="mem_pass"; updateTitle="비밀번호";
				break;				
			case 2: updateField ="mem_name"; updateTitle="회원이름";
				break;				
			case 3: updateField ="mem_tel"; updateTitle="전화번호";
				break;				
			case 4: updateField ="mem_addr"; updateTitle="주소";
				break;					
			default:
				System.out.println("수정할 항목을 잘못 선택했습니다.");
				System.out.println("다시 선택하세요...");
			}
			
		}while(num<1 || num>4);
		
		sc.nextLine(); //버퍼 비우기
		System.out.println();
		System.out.print("새로운 "+updateTitle+" >> ");
		String updateData = sc.nextLine();
		
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set "+ updateField + " = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(id +" 회원정보 수정 완료!!!");
			}else {
				System.out.println(id +" 회원정보 수정 실패...");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {} 
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
		}
		
	}
	
	//회원 정보를 수정하는 메서드
	private void updateMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("수정할 회원정보를 입력하세요.");
		System.out.print("수정할 회원 ID >>");
		String id = sc.next();
		
		if(getMemIdCount(id)==0) {
			System.out.println("입력하신 "+id+"은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.print("새로운 비밀번호 >> ");
		String newPass = sc.next().trim();
		System.out.print("새로운 회원이름 >> ");
		String newName = sc.next();
		System.out.print("새로운 전화번호 >> ");
		String newTel = sc.next();
		sc.nextLine();//버퍼 비우기
		System.out.print("새로운 주소 >> ");
		String newAddr = sc.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update mymember set mem_pass = ? , mem_name = ?, mem_tel = ? , mem_addr = ? where mem_id =?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, newName);
			pstmt.setString(3, newTel);
			pstmt.setString(4, newAddr);
			pstmt.setString(5, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(id +" 회원정보 수정 완료!!!");
			}else {
				System.out.println(id +" 회원정보 수정 실패...");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {} 
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
		}
		}
		
		
	
	
	
	
	//회원 정보를 삭제하는 메서드
	private void deleteMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.print("삭제할 회원 ID >>");
		String id = sc.next();
		
		if(getMemIdCount(id)==0) {
			System.out.println("입력하신 "+id+"은(는) 없는 회원ID 입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println(id+" 회원 정보 삭제 성공!!!");
			}else {
				System.out.println(id+" 회원 정보 삭제 실패...");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {} 
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
		}
		
		
	}
	
	// 새로운 회원 정보를 추가하는 메서드
	// 조건1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받음)
	private void insertMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("새롭게 추가할 회원 정보를 입력하세요.");
		
		
		//ID 중복검사
		int count = 0;
		String id = null; // 입력한 회원ID가 저장될 변수
		do {
			System.out.print("회원ID >>");
			id = sc.next();
			
			count = getMemIdCount(id);
			
			if(count >0) {
				System.out.println("입력한 "+id+"은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
				System.out.println();
			}
			
		}while(count>0);
		
		System.out.print("비밀번호 >>");
		String pass = sc.next().trim();
		System.out.print("회원이름 >>");
		String name = sc.next();
		System.out.print("전화번호 >>");
		String tel = sc.next();
		
		sc.nextLine(); //버퍼 비우기
		System.out.print("주소 >>");
		String addr = sc.nextLine();
		
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ " values(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int cnt = pstmt.executeUpdate(); //정수반환
			
			if(cnt>0) {
				System.out.println(id+"님의 회원 등록 완료!");
			}else {
				System.out.println(id+"님의 회원 등록 실패...");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {} 
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
		}
		
	}
	
	
	// 회원 ID를 매개변수로 받아서 해당 회원 ID의 개수를 반환하는 메서드
	private int getMemIdCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0; // 검색한 회원ID의 개수가 저장될 변수
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from mymember where mem_id = ?";		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt"); //id가 있으면!
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {} 
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {} 
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
		}
		
		return count;
	}
	
	// 전체 회원 정보 출력 메서드
	private void displayAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("===================================================================");
		System.out.println(" ID\t\t 비밀번호\t\t 이름\t 전화번호\t\t 주소");
		System.out.println("===================================================================");
		
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int cnt = 0; //출력한 데이터 개수가 저장될 변수
			
			while(rs.next()) {
				cnt++;
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				System.out.println(memId + "\t\t" + memPass + "\t\t" + memName + "\t"
						+ memTel + "\t\t" + memAddr);
			}
			if(cnt==0) {
				System.out.println("등록된 회원이 없습니다...");
			}
			System.out.println("===================================================================");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {} 
			if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {} 
			if(conn!=null) try {conn.close();} catch(SQLException e) {} 
		}
	}
	
	//메뉴를 출력하고 입력받은 선택 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.print("----------------\n" + "다음 메뉴를 선택하세요.\n" + "1. 자료 추가\n" + "2. 자료 삭제\n" + "3. 자료 수정\n"
				+ "4. 전체 자료 출력\n" + "5. 자료 수정 2\n"+"0. 작업 종료\n" + "----------------\n" + "메뉴선택 >>");
		return sc.nextInt();
		
	}
	

}
