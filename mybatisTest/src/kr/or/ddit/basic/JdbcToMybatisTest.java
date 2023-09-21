package kr.or.ddit.basic;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

/*
 * LPROD테이블에 새로운 데이터를 추가
 * 조건) lprod_gu값과 lprod_nm값은 직접 입력받아서 처리하고,
 * 		lprod_id값은 현재의 lprod_id값 중에서 제일 큰 값보다 1 크게 한다. -> select max(lprod_id) from lprod;
 * 
 * 		입력 받은 lprod_gu값이 이미 등록되어 있으면 다시 입력 받아서 처리 한다. -> select count(*) from lprod where lprod_gu = 입력값;
 * 		==> 0이면 없고 1이면 있는 것
 * 
 */

public class JdbcToMybatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
/*
		// 1. MyBatis 환경 설정파일 읽어오기 -> SqlSessionFactory객체 생성하기
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;

		try {
			// 1-1. 환경 설정 파일을 읽어올 스트림 객체 생성
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			// 1-2. 환경 설정 파일을 읽어와 환경 설정을 완성한 후 SqlSesstionFactory객체 를 생성
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("MyBatis 초기화 실패....");
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				}
		}
*/
		
		
		// ==============================================================================

		SqlSession session = null;

		try {
			session = MybatisUtil.getSqlSession();

			int maxnum = session.selectOne("jdbc.getMaxid");
			maxnum++;

			String gu;
			int count = 0;

			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >> ");
				gu = scan.next();

				count = session.selectOne("jdbc.getLprodguCount", gu);

				if (count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요.");
					System.out.println();
				}
			} while (count > 0);

			// ------------------------------------------------------------------

			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();

			// ------------------------------------------------------------------

			// 구성된 insert할 데이터를 VO객체에 저장
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(maxnum);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);

			int cnt = session.insert("jdbc.insertLprod", lvo);

			if (cnt > 0) {
				session.commit(); // 커밋필수!!
				System.out.println("insert 성공!!");
			} else {
				System.out.println("insert 실패...");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();

		}

	}
}
