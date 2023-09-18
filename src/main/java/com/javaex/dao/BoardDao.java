package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 게시판 목록
	public List<BoardVo> boardList(String keyword) {
		System.out.println("BoardDao.boardList()");
		System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = sqlSession.selectList("board.boardList2", keyword);
		System.out.println("boardList : " + boardList);
		
		return boardList;
	}
	
	public List<BoardVo> searchList(String keyword) {
		System.out.println("BoardDao.searchList()");
		System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = sqlSession.selectList("board.searchList", keyword);
		System.out.println("boardList : " + boardList);
		
		return boardList;
	}
	
	// 게시판 작성
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardDao.boardInsert()");
		
		int count = sqlSession.insert("board.boardInsert", boardVo);
		
		return count;
	}
	
	// 게시판 조회수
	public int boardHitCount(int no) {
		System.out.println("BoardDao.boardHitCount()");
		
		int count = sqlSession.update("board.boardHitCount", no);
		
		return count;
	}
	
	// 게시판 상세보기
	public BoardVo boardDetail(int no) {
		System.out.println("BoardDao.boardDetail()");
		
		BoardVo boardVo = sqlSession.selectOne("board.boardDetail", no);
		
		return boardVo;
	}
	
	// 게시판 수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardDao.boardUpdate()");

		int count = sqlSession.update("board.boardUpdate", boardVo);
		
		return count;
	}
	
	// 게시판 삭제
	public int boardDelete(int no) {
		System.out.println("BoardDao.boardDelete()");
		
		int count = sqlSession.delete("board.boardDelete", no);
		
		return count;
	}
	
}
