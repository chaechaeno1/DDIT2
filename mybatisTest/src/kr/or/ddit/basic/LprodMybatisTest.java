package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.vo.LprodVO;

//MyBatis를 이용하여 DB자료를 처리하는 순서 및 방법

public class LprodMybatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 1. Mybatis의 환경 설정 파일(mybatis-config.xml)을 읽어와서 처리하여
		//    SqlSessionFactory객체 생성
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			// 1-1. 환경 설정 파일을 읽어올 스트림 객체 생성
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			// 1-2. 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SqlSesstionFactory객체 를 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
			
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if(in!=null) try { in.close();} catch(IOException e) {}
		}
		//=========================================================================================
		
		// 2. Mapper에 등록된 SQL문들 중에서 실행할 SQL문을 호출해서 원하는 작업을 수행한다.
		
		// 2-1. insert 연습
		System.out.println("insert 작업 시작....");
		
		System.out.print("Lprod_id 입력 >> ");
		int lprodId = scan.nextInt();
		
		System.out.print("Lprod_gu 입력 >> ");
		String lprodGu = scan.next();
		
		System.out.print("Lprod_id 입력 >> ");
		String lprodNm = scan.next();
		
		// 1) 저장할 데이터들을 VO객체에 저장한다.
		LprodVO lprodVo = new LprodVO();
		lprodVo.setLprod_id(lprodId);
		lprodVo.setLprod_gu(lprodGu);
		lprodVo.setLprod_nm(lprodNm);

		SqlSession session = null;		
		try {
			// 2) SqlSessionFactory객체의 openSession()메서드를 이용하여 
			//    SQL문을 호출해서 실행할 수 있는 SqlSession객체를 생성
			//    형식) SqlSessionFactory객체.openSession(논리값);
			//	    '논리값'이 true이면 AutoCommit이 활성화된 상태
			//		'논리값'이 false이거나 생략되면 AutoCommit이 비활성화 상태
			session = sqlSessionFactory.openSession();
			
		} catch (Exception e) {
			
		}
		
		
		
		
		
	}

}
