package kr.or.ddit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	//싱글톤 처리
	
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl(){}
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	
	
	@Override
	public MemberVO getLoginMember(MemberVO memVo) {
		SqlSession session = MybatisUtil.getSqlSession();
		MemberVO loginMemberVo = null; // 반환값이 저장될 변수
		try {
			loginMemberVo = session.selectOne("member.getLoginMember", memVo);			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		
		return loginMemberVo;
	}
	
	@Override
	public List<MemberVO> selectAllMember() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<MemberVO> selectMemberVo = null; 
		try {
			selectMemberVo = session.selectList("member.selectAllMember");			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		
		return selectMemberVo;
		
	}
	
	

}
