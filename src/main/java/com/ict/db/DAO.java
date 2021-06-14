package com.ict.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class DAO {
	// MyBatis에서는 SQLsession을사용한다. 클래스를 이용해서  mapper.xml 파일의 태그들을 사용해서 SQL를 사용한다.
	// 
	
	private static SqlSession ss;
	private synchronized static SqlSession getSession() {
		if(ss==null) {
			//ss = DBService.getFactory().openSession(); //select문 커밋을 해도되고안해도된다. DB가 바뀌는 것이 아니기 때문.
		//	ss = DBService.getFactory().openSession(true);//AutoCommit(); 트랜젝션 처리를 못함.)
			ss = DBService.getFactory().openSession(false);//수동 Commit(); 
		}
		return ss;
	}



	

	// MyBatis select문은 4가지로 구분된다.
	// 결과가 여러개 일때 list<VO>
	//결과가 하나일 때  VO
	// 파라미터 값이 있을 때(파라미터가 두개이상이면 VO아니면 map을 사용해야한다. 
	// 파라미터 값이 없을 때
	public static List<VO> getList() {
			List<VO> list = null;
			//getSessionn().sql 명령과 같은 메소드 차직
			//list = getSession().selectList(mapper의 아이디를 스트롱으로 작성); 파라미터가 없는 메소드
			//list = getSession().selectList(null, 파라미터); 파라미터가 있는 메소드
			list = getSession().selectList("list");
			return list;
	}// 왜 트라이캐치를 쓰면안되는가?

	

	// 상세보기
	public static VO getSelectOne(String idx) {
			VO vo = getSession().selectOne("onelist", idx);
			return vo;
	}

	// insert  왜 스태틱을 써줘야만 하는가?
		// getSession().insert("mapper의 id", 파라미터)
			// insert, update, delete는 openSession(false)를 했기 때문에 commit를 해야 된다.
		public static int getInsert(VO vo) {
				
				int result = 0;
					result = getSession().insert("insert",vo);
				ss.commit();
				return result;
		
		}
	
	// update
		// getSession().insert("mapper의 id", 파라미터)
					// insert, update, delete는 openSession(false)를 했기 때문에 commit를 해야 된다.
	public static int getUpdate(VO vo) {
			int result = getSession().update("update", vo);
			ss.commit();
			return result;
	}

	// delete
	// getSession().insert("mapper의 id", 파라미터)
				// insert, update, delete는 openSession(false)를 했기 때문에 commit를 해야 된다.
	public static int getDelete(VO vo) {
			int result = getSession().delete("delete", vo);
			ss.commit();
			return result;
	}
}
