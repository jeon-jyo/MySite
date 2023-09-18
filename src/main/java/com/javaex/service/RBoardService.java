package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {
	
	@Autowired
	private RBoardDao rBoardDao;

	// 게시판 목록 + 검색
	public List<RBoardVo> boardList(String keyword) {
		System.out.println("RBoardService.boardList()");
		System.out.println("keyword : " + keyword);
		
		List<RBoardVo> boardList = rBoardDao.boardList(keyword);
		System.out.println(boardList);
		
		return boardList;
	}

	// 게시판 작성
	public void boardInsert(RBoardVo rBoardVo) {
		System.out.println("RBoardService.boardInsert()");
		System.out.println("rBoardVo : " + rBoardVo);
		
		int count;
		
		if(rBoardVo.getGroupNo() == 0) {
			count = rBoardDao.boardInsert(rBoardVo);

		} else {
			count = rBoardDao.boardOrderCount(rBoardVo);
			
			if(count == 1) {
				System.out.println("그룹 순서 업데이트");
				
				count = rBoardDao.boardInsert(rBoardVo);
			}
		}
		
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
	}

	// 게시판 상세보기 + 수정 폼
	public RBoardVo boardDetail(int boardNo, boolean value) {
		System.out.println("RBoardService.boardDetail()");
		System.out.println("boardNo : " + boardNo);
		
		RBoardVo rBoardVo = null;
		if(value) {
			int count = rBoardDao.boardHitCount(boardNo);
			if(count == 1) {
				System.out.println("조회수 업데이트");
				
				rBoardVo = rBoardDao.boardDetail(boardNo);
			}
		} else {
			rBoardVo = rBoardDao.boardDetail(boardNo);
			System.out.println("기존 정보 : " + rBoardVo);
		}
		
		return rBoardVo;
	}
	
	// 게시판 수정
	public void boardUpdate(RBoardVo rBoardVo) {
		System.out.println("RBoardService.boardUpdate()");
		
		int count = rBoardDao.boardUpdate(rBoardVo);
		if(count == 1) {
			System.out.println("수정 성공");
			System.out.println("변경 정보 : " + rBoardVo);
			
		} else {
			System.out.println("수정 실패");
		}
	}
	
	// 게시판 삭제
	public void boardDelete(int boardNo) {
		System.out.println("RBoardService.boardDelete()");
		System.out.println("boardNo : " + boardNo);
		
		int count = rBoardDao.boardDelete(boardNo);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}



	
}
