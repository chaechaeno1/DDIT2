package kr.or.ddit.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.FileInfoVO;
import sun.security.jca.GetInstance;

public class FileinfoDaoImpl implements IFileinfoDao {
	private static FileinfoDaoImpl dao;
	private FileinfoDaoImpl() {}
	public static FileinfoDaoImpl getInstance() {
		if(dao==null) dao = new FileinfoDaoImpl();
		return dao;
	}
	
	@Override
	public int insertFileinfo(FileInfoVO fvo) {
		SqlSession session = MybatisUtil.getSqlSession();
		int cnt = 0; //반환값이 저장될 변수
		
		try {
			cnt = session.insert("fileinfo.insertFileinfo", fvo);
			if(cnt>0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return 0;
	}

	@Override
	public List<FileInfoVO> getAllFileinfo() {
		SqlSession session = MybatisUtil.getSqlSession();
		List<FileInfoVO> fileList = null;
		
		try {
			fileList = session.selectList("fileinfo.getAllFileinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		
		return fileList;
	}

	@Override
	public FileInfoVO getFileinfo(int fileNo) {
		SqlSession session = MybatisUtil.getSqlSession();
		FileInfoVO fileVo = null; //반환값이 저장될 변수
		
		try {
			fileVo = session.selectOne("fileinfo.getFileinfo", fileNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return fileVo;
	}

}
