package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;

	// 게시판 목록 + 검색
	public List<BoardVo> boardList(String keyword) {
		System.out.println("BoardService.boardList()");
		System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = boardDao.boardList(keyword);
//		System.out.println(boardList);
		
		return boardList;
	}
	
	// 게시판 작성
	public void boardInsert(BoardVo boardVo) {
		System.out.println("BoardService.boardInsert()");
		System.out.println("boardVo : " + boardVo);
		
		int count = boardDao.boardInsert(boardVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
	}

	// 게시판 상세보기 + 수정 폼
	public BoardVo boardDetail(int boardNo,  boolean value) {
		System.out.println("BoardService.boardDetail()");
		System.out.println("boardNo : " + boardNo);
		
		BoardVo boardVo = null;
		if(value) {
			int count = boardDao.boardHitCount(boardNo);
			if(count == 1) {
				System.out.println("조회수 업데이트");
				
				boardVo = boardDao.boardDetail(boardNo);
			}
		} else {
			boardVo = boardDao.boardDetail(boardNo);
			System.out.println("기존 정보 : " + boardVo);
		}
		
		return boardVo;
	}

	// 게시판 수정
	public void boardUpdate(BoardVo boardVo) {
		System.out.println("BoardService.boardUpdate()");
		
		int count = boardDao.boardUpdate(boardVo);
		if(count == 1) {
			System.out.println("수정 성공");
			System.out.println("변경 정보 : " + boardVo);
			
		} else {
			System.out.println("수정 실패");
		}
	}
	
	// 게시판 삭제
	public void boardDelete(int boardNo) {
		System.out.println("BoardService.boardDelete()");
		System.out.println("boardNo : " + boardNo);
		
		int count = boardDao.boardDelete(boardNo);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}
	
}
