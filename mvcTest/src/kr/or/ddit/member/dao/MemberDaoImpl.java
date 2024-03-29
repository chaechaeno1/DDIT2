package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	//1) 자신 class의 참조값이 저장될 변수를 private static으로 선언
	private static MemberDaoImpl dao;
	
	//2) 모든 생성자의 접근제한자를 private으로 선언
	//		==> 생성자가 없으면 기본 생성자를 작성해야 한다.	
	private MemberDaoImpl() {}

	//3) 자신의 class의 인스턴스를 생성하고 반환하는 메서드를 public static으로 작성
	//(이 메서드의 이름은 보통 getInstance로 한다.)
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	


	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;	// 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료!");
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ "values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			logger.debug("PrepareStatement객체 생성완료...");
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용 데이터 ["+memVo.getMem_id() + "," + memVo.getMem_pass() + "," + memVo.getMem_name() + "," + memVo.getMem_tel() + "," + memVo.getMem_addr()+"]");
			
			cnt = pstmt.executeUpdate();
			logger.info("실행 작업 성공!!!!!!!");
			
		} catch (SQLException e) {
			logger.error("실행 작업 실패!!!", e);
			e.printStackTrace();
		} finally {
			
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			logger.info("자원 반납 완료!");
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 완료!!!");
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			logger.debug("PrepareStatement객체 생성완료...!");
			logger.debug("실행 SQL : "+sql);
			logger.debug("사용 데이터 [ "+ memId + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("실행 작업 성공..!");
			
		} catch (SQLException e) {
			logger.error("실행 작업 실패!!!", e);
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			logger.info("자원 반납 완료!!");
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 완료!!!");
			String sql = "update mymember set "
					+ " mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			
			logger.debug("PrepareStatement객체 생성완료...!");
			logger.debug("실행 SQL : "+sql);
			logger.debug("사용 데이터 [ "+ memVo.getMem_pass() +","+ memVo.getMem_name()+","+memVo.getMem_tel()+","+memVo.getMem_addr()+","+memVo.getMem_id()+"]");
			
			cnt = pstmt.executeUpdate();
			logger.info("실행 작업 성공..!");
			
		} catch (SQLException e) {
			logger.error("실행 작업 실패....");
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			logger.info("자원 반납 완료..!!!");
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null;	// 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 완료!!!");
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			
			logger.debug("실행 SQL : "+sql);
			
			rs = pstmt.executeQuery();
			logger.info("실행 작업 성공..!");
			
			while(rs.next()) {
				if(memList==null) memList = new ArrayList<MemberVO>();
				
				// 한 개의 레코드가 저장될 VO객체 생성
				MemberVO memVo = new MemberVO();
				
				// ResultSet에서 각 컬럼값들을 가져와 VO의 멤버변수에 저장한다.
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				
				
				// 구성된 VO객체를 List에 추가한다.
				memList.add(memVo);
			}
		} catch (SQLException e) {
			logger.error("실행 작업 실패....");
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			logger.info("자원 반납 완료..!!!");
		}
		
		return memList;
	}

	@Override
	public int getMemIdCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 완료!!!");
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			logger.debug("실행 SQL : "+sql);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			logger.info("실행 작업 성공..!");
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			logger.error("실행 작업 실패...!");
			e.printStackTrace();
		} finally {
			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			logger.info("자원 반납 완료!");
		}
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		// key값 정보 ==> 회원ID(memid), 수정할컬럼명(field), 수정할데이터(data)
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 완료!!!");
			String sql = "update mymember set " + paramMap.get("field") + " = ? "
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memid"));
			
			logger.debug("실행 SQL : "+sql);
			

			cnt = pstmt.executeUpdate();
			logger.info("실행 작업 성공..!");
			
		} catch (SQLException e) {
			logger.error("실행 작업 실패...");
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
			logger.info("자원 반납 완료..!");
		}
		
		return cnt;
	}
	
}
