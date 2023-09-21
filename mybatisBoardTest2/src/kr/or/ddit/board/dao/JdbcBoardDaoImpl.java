package kr.or.ddit.board.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private static JdbcBoardDaoImpl dao;
	Scanner scan = new Scanner(System.in);
	InputStream in = null;
	SqlSessionFactory sqlSessionFactory = null;


	private JdbcBoardDaoImpl() {
	}

	public static JdbcBoardDaoImpl getInstance() {
		if (dao == null)
			dao = new JdbcBoardDaoImpl();
		return dao;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("test.insertBoard", boardVo);
			if (cnt > 0) { // insert, update, delete작업이 성공하면 commit()을 처리
				session.commit();
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}
	

	@Override
	public int deleterBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.delete("test.deleteBoard", boardNo);
			if (cnt > 0) { // insert, update, delete작업이 성공하면 commit()을 처리
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.delete("test.updateBoard", boardVo);
			if (cnt > 0) { // insert, update, delete작업이 성공하면 commit()을 처리
				session.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return cnt;
	}

	
	
	
	@Override
	public List<JdbcBoardVO> getAllBoard() {
		SqlSession session = null;
		List<JdbcBoardVO> boardList = null; //반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("test.selectBoard");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		SqlSession session = null;
		JdbcBoardVO board = null;
		
		try {
			session = MybatisUtil.getSqlSession();
			board =  session.selectOne("test.selectBoardNumber", boardNo);
			
			if (board == null) {
	            System.out.println("검색된 데이터가 하나도 없습니다...");
	        }
			
		} catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (session != null)
	            session.close();
	    }

	    return board;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String title) {
		SqlSession session = null;
		List<JdbcBoardVO> boardList = null; //반환값이 저장될 변수
		
		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("test.selectSearch");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("test.updateCnt", boardNo);
			if (cnt > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return cnt;
	}

}
