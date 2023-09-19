package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private static JdbcBoardDaoImpl dao;

	private JdbcBoardDaoImpl() {
	}

	public static JdbcBoardDaoImpl getInstance() {
		if (dao == null)
			dao = new JdbcBoardDaoImpl();
		return dao;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board"
					+ "(board_no, board_title, board_wirter, board_date, board_cnt, board_content) "
					+ " values(board_seq.nextval, ?, ?, sysdate, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());

			cnt = pstmt.executeUpdate();

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
		return 0;
	}

	@Override
	public int deleterBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			cnt = pstmt.executeUpdate();

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
		return 0;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
						+ "board_title = ?, board_content =?, board_date = sysdate"
						+ "where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());

			cnt = pstmt.executeUpdate();

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
		return 0;
	}

	@Override
	public List<JdbcBoardVO> getAllBoard() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JdbcBoardVO> boardList = null; //반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jabc_board order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				if(boardList==null) boardList = new ArrayList<JdbcBoardVO>();
				
				JdbcBoardVO boardVo = new JdbcBoardVO();
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
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
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JdbcBoardVO boardVo = null; //반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_wirter, "
					+ "to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, "
					+ "board_content from jdbc_board "
					+ "where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				boardVo = new JdbcBoardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));

			}
			
		} catch (Exception e) {
			
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
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
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoard(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
