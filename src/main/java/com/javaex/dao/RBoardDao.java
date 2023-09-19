package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 게시판 목록 + 검색
	public List<RBoardVo> boardList(String keyword) {
		System.out.println("RBoardDao.boardList()");
		
		List<RBoardVo> boardList = sqlSession.selectList("rboard.boardList", keyword);
		
		return boardList;
	}

	// 게시판 작성
	public int boardInsert(RBoardVo rBoardVo) {
		System.out.println("RBoardDao.boardInsert()");
		
		int count = sqlSession.insert("rboard.boardInsert", rBoardVo);
		
		return count;
	}

	// 그룹 순서
	public int boardOrderCount(RBoardVo rBoardVo) {
		System.out.println("RBoardDao.boardOrderCount()");
		
		int count = sqlSession.update("rboard.boardOrderCount", rBoardVo);
		
		return count;
	}
	
	// 게시판 조회수
	public int boardHitCount(int no) {
		System.out.println("RBoardDao.boardHitCount()");
		
		int count = sqlSession.update("rboard.boardHitCount", no);
		
		return count;
	}
	
	// 게시판 상세보기
	public RBoardVo boardDetail(int no) {
		System.out.println("RBoardDao.boardDetail()");
		
		RBoardVo rBoardVo = sqlSession.selectOne("rboard.boardDetail", no);
		
		return rBoardVo;
	}
	
	// 게시판 수정
	public int boardUpdate(RBoardVo rBoardVo) {
		System.out.println("RBoardDao.boardUpdate()");

		int count = sqlSession.update("rboard.boardUpdate", rBoardVo);
		
		return count;
	}
	
	// 그룹 내 큰 순서
	public RBoardVo boardMaxOrder(int no) {
		System.out.println("RBoardDao.boardMaxOrder()");

		RBoardVo rBoardVo = sqlSession.selectOne("rboard.boardMaxOrder", no);

		return rBoardVo;
	}

	// 삭제 처리
	public int boardDeleteUpdate(RBoardVo rBoardVo) {
		System.out.println("RBoardDao.boardDeleteUpdate()");

		int count = sqlSession.update("rboard.boardDeleteUpdate", rBoardVo);
		
		return count;
	}

	// 게시판 삭제
	public int boardDelete(int no) {
		System.out.println("RBoardDao.boardDelete()");
		
		int count = sqlSession.delete("rboard.boardDelete", no);
		
		return count;
	}

}
