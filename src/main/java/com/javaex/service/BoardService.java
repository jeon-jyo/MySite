package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 게시판 목록 - 페이징 + 검색
	public Map<String, Object> boardListPaging(int crtPage, String keyword) {
		System.out.println("BoardService.boardListPaging()");
		System.out.println("crtPage : " + crtPage);
		System.out.println("keyword : " + keyword);
		
		// 글목록 계산 //////////////////////////////

		// 페이지당 글갯수	- 한 페이지에 출력되는 글갯수
		int listCnt = 10;
		
		// 현재 페이지		- 파라미터로 받는다
		crtPage = (crtPage > 0) ? crtPage : 1;
		
		// 시작 글번호
		int startRNum = (crtPage-1) * listCnt + 1;
		
		// 끝 글번호
		int endRNum = (startRNum + listCnt) - 1;
		
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("startRNum", startRNum);
		listMap.put("endRNum", endRNum);
		listMap.put("keyword", keyword);
		List<BoardVo> boardList = boardDao.boardListPaging(listMap);
		
		// 페이징 계산 //////////////////////////////
		
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		// 마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		// 시작 버튼 번호
		int startPageBtnNo = (endPageBtnNo-pageBtnCount)+1;
		
		// 전체 글갯수
		int totalCnt = boardDao.selectTotalCnt(keyword);
		System.out.println("totalCnt : " + totalCnt);
		
		// 다음화살표 유무
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCnt) {
			next = true;
		} else {
			// 다음버튼이 없을 때(false) - endPageBtnNo 다시 계산
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		// 이전화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPageBtnNo", startPageBtnNo);
		pageMap.put("endPageBtnNo", endPageBtnNo);
		pageMap.put("prev", prev);
		pageMap.put("next", next);
		pageMap.put("boardList", boardList);
		pageMap.put("keyword", keyword);

		return pageMap;
	}
	
	// 페이징 정리
	public Map<String, Object> listPagingSearch(int crtPage, String keyword) {
		System.out.println("BoardService.listPagingSearch()");
		System.out.println("crtPage : " + crtPage);
		System.out.println("keyword : " + keyword);
		
		// 글목록 계산 //////////////////////////////
		
		/*
		 * int listCnt = 5;		int listCnt = 10;
		 * 1	1	5			1	1	10
		 * 2	6	10			2	11	20
		 * 3	11	15			3	21	30
		 */
		// 페이지당 글갯수	- 한 페이지에 출력되는 글갯수
		int listCnt = 10;
		
		// 현재 페이지		- 파라미터로 받는다
		crtPage = (crtPage > 0) ? crtPage : 1;
		
		// 시작 글번호
		/*
		 * (현재 페이지-1) * 페이지당 글갯수 + 1
		 * (crtPage-1) * listCnt + 1
		 * 
		 * (1-1)*10+1 = 1		- 1부터 출력
		 * (2-1)*10+1 = 11		- 11부터 출력
		 */
		int startRNum = (crtPage-1) * listCnt + 1;
		
		// 끝 글번호
		/*
		 * (시작 글번호 + 페이지당 글갯수) - 1
		 * (startRNum + listCnt) - 1
		 * 
		 * (1 + 10) - 1 = 10	- 10까지 출력
		 * (2 + 10) - 1 - 20	- 20까지 출력
		 */
		int endRNum = (startRNum + listCnt) - 1;
		
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("startRNum", startRNum);
		listMap.put("endRNum", endRNum);
		listMap.put("keyword", keyword);
		List<BoardVo> boardList = boardDao.boardListPaging(listMap);
		
		// 페이징 계산 //////////////////////////////
		/*
		 * int pageBtnCount = 5;
		 * 1	1	5		6	6	10		15	11	15
		 * 2	1	5		7	6	10		16	11	15
		 * 3	1	5		8	6	10		17	11	15
		 * 4	1	5		9	6	10		18	11	15
		 * 5	1	5		10	6	10		19	11	15
		 */
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		// 마지막 버튼 번호
		/*
		 * (int)Math.ceil(현재페이지 / (double)페이지당버튼갯수) * 페이지당버튼갯수
		 * (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount
		 * 
		 * 	- 자바는 1/5 = 0 이라서 double로 형변환
		 * 		1/5	= 0	->	1/5.0 = 0.2
		 *  - 0.2를 올림(Math.ceil)하면 1.0이라서 다시 int로 형변환
		 * 		1.0 => 1
		 * 
		 * 1/5.0	0.2	(1.0)	1*5	->	5
		 * 2/5.0	0.4	(1.0)	1*5	->	5
		 * 3/5.0	0.6	(1.0)	1*5	->	5
		 * 4/5.0	0.8	(1.0)	1*5	->	5
		 * 5/5.0	1.0	(1.0)	1*5	->	5
		 * 
		 * 6/5.0	1.2	(2.0)	2*5	->	10
		 * 11/5.0	2.2	(3.0)	3*5	->	15
		 */
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		
		// 시작 버튼 번호
		/*
		 * (마지막버튼번호-페이지당버튼갯수)+1
		 * (endPageBtnNo-pageBtnCount)+1
		 * 
		 * (5-5)+1	->	1
		 * (10-5)+1	->	6
		 * (15-5)+1	->	11
		 */
		int startPageBtnNo = (endPageBtnNo-pageBtnCount)+1;
		
		// 전체 글갯수
		int totalCnt = boardDao.selectTotalCnt(keyword);
		System.out.println("totalCnt : " + totalCnt);
		
		// 다음화살표 유무
		/*
		 * 페이지당 글갯수 * 마지막 버튼 번호 < 전체 글갯수
		 * listCnt * endPageBtnNo < totalCnt	- true 이면 유
		 * 
		 * 10*15 = 150 < 157	- true
		 * 10*16 = 160 < 157	- false
		 */
		boolean next = false;
		if(listCnt * endPageBtnNo < totalCnt) {
			next = true;
		} else {
			// 다음버튼이 없을 때(false) - endPageBtnNo 다시 계산
			/*
			 * (int)Math.ceil(전체글갯수 / (double)페이지당 글갯수)
			 * (int)Math.ceil(totalCnt/(double)listCnt)
			 * 
			 * 157/10.0		15.7(16.0)	16
			 */
			endPageBtnNo = (int)Math.ceil(totalCnt/(double)listCnt);
		}
		
		// 이전화살표 유무
		/*
		 * 시작 버튼 번호 != 1
		 * startPageBtnNo != 1	- true 이면 유
		 */
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPageBtnNo", startPageBtnNo);
		pageMap.put("endPageBtnNo", endPageBtnNo);
		pageMap.put("prev", prev);
		pageMap.put("next", next);
		pageMap.put("boardList", boardList);
		pageMap.put("keyword", keyword);

		return pageMap;
	}
	
	// 게시판 작성
	public void boardInsert(BoardVo boardVo) {
		System.out.println("BoardService.boardInsert()");
		System.out.println("boardVo : " + boardVo);
		
		// 임시
		/*
		for(int i = 1; i <= 157; i++) {
			boardVo.setTitle(i + "번째 글 제목");
			boardVo.setContent(i + "번째 글 내용");
			
			boardDao.boardInsert(boardVo);
		}
		*/
		
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
