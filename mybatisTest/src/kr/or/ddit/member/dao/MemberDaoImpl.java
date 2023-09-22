package kr.or.ddit.member.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	// 1) 자신 class의 참조값이 저장될 변수를 private static으로 선언
	private static MemberDaoImpl dao;
	Scanner scan = new Scanner(System.in);
	InputStream in = null;
	SqlSessionFactory sqlSessionFactory = null;

	// 2) 모든 생성자의 접근제한자를 private으로 선언
	// ==> 생성자가 없으면 기본 생성자를 작성해야 한다.
	private MemberDaoImpl() {
	}

	// 3) 자신의 class의 인스턴스를 생성하고 반환하는 메서드를 public static으로 작성
	// (이 메서드의 이름은 보통 getInstance로 한다.)
	public static MemberDaoImpl getInstance() {
		if (dao == null)
			dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0; // 반환값이 저장될 변수

		try {
			session = MybatisUtil.getSqlSession();

			cnt = session.insert("member.insertMember", memVo);
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
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.delete("member.deleteMember", memId);
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

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember", memVo);
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

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;

		List<MemberVO> memList = null;

		try {
			session = MybatisUtil.getSqlSession();
			memList = session.selectList("member.getAllMember");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return memList;
	}

	@Override
	public int getMemIdCount(String memId) {

		SqlSession session = null;
		int count = 0;

		try {
			session = MybatisUtil.getSqlSession();

			count = session.selectOne("member.getMemIdCount", memId);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}

		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;

		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember2", paramMap);

			if (cnt > 0)session.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();

		}

		return cnt;
	}

}
