package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.vo.JdbcBoardVO;
import sun.security.jca.GetInstance;

public class JdbcBoardServiceImpl implements IJdbcBoardSerivce {
	private IJdbcBoardDao dao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImpl getInstance() {
		if(service == null) service = new JdbcBoardServiceImpl();
		return service;
	}
	

	@Override
	public int insertBoard(JdbcBoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	@Override
	public int deleterBoard(int boardNo) {
		return dao.deleterBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		int cnt = dao.setCountIncrement(boardNo);
		if(cnt==0) return null;
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String title) {
		return dao.getSearchBoard(title);
	}

	// 서비스에선 필요없지만 남겨둠 ( getBoard()에서 사용함 ) 
	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
