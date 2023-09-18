package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDao boardDao;
	
	// 게시판 목록
	@RequestMapping(value="/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVo> boardList = boardDao.boardList("");
		
		model.addAttribute("boardList", boardList);
		
		// 게시판 목록 - 포워드
		return "board/list";
	}
	
	// 게시판 검색
	@RequestMapping(value="/search", method= { RequestMethod.GET, RequestMethod.POST})
	public String search(@RequestParam(value="keyword") String keyword, Model model) {
		System.out.println("BoardController.search()");
		System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = boardDao.searchList(keyword);
		
		model.addAttribute("boardList", boardList);
		
		// 게시판 목록 - 포워드
		return "board/list";
	}
	
	@RequestMapping(value="/search2", method= { RequestMethod.GET, RequestMethod.POST})
	public String search2(@RequestParam(value="keyword") String keyword, Model model) {
		System.out.println("BoardController.search2()");
		System.out.println("keyword : " + keyword);
		
		List<BoardVo> boardList = boardDao.boardList(keyword);
		
		model.addAttribute("boardList", boardList);
		
		// 게시판 목록 - 포워드
		return "board/list";
	}
	
	// 게시판 작성폼
	@RequestMapping(value="/writeForm", method= { RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController.writeForm()");
		
		// 게시판 작성폼 - 포워드
		return "board/writeForm";
	}
	
	// 게시판 작성
	@RequestMapping(value="/write", method= { RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("BoardController.write()");

		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setNo(authUser.getNo());
		System.out.println("boardVo : " + boardVo);
		
		int count = boardDao.boardInsert(boardVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		// 게시판 목록 - 리다이렉트
		return "redirect:/board/list";
	}
	
	// 게시판 상세보기
	@RequestMapping(value="/detail/{no}", method= { RequestMethod.GET, RequestMethod.POST})
	public String detail(@PathVariable(value="no") int boardNo, Model model) {
		System.out.println("BoardController.detail()"); 
		
		int count = boardDao.boardHitCount(boardNo);
		if(count == 1) {
			System.out.println("조회수 업데이트");
		}
		BoardVo boardVo = boardDao.boardDetail(boardNo);
		
		model.addAttribute("boardVo", boardVo);
		
		// 게시판 상세 - 포워드
		return "board/read";
	}
	
	// 게시판 수정폼
	@RequestMapping(value="/modifyForm/{no}", method= { RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@PathVariable(value="no") int boardNo, Model model) {
		System.out.println("BoardController.modifyForm()"); 
		
		BoardVo boardVo = boardDao.boardDetail(boardNo);
		System.out.println("기존 정보 : " + boardVo);
		
		model.addAttribute("boardVo", boardVo);
		
		// 게시판 상세 - 포워드
		return "board/modifyForm";
	}
	
	// 게시판 수정
	@RequestMapping(value="/modify", method= { RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController.modify()"); 
		
		int count = boardDao.boardUpdate(boardVo);
		if(count == 1) {
			System.out.println("수정 성공");
			System.out.println("변경 정보 : " + boardVo);
			
		} else {
			System.out.println("수정 실패");
		}
		
		// 게시판 목록 - 리다이렉트
		return "redirect:/board/list";
	}
	
	// 게시판 삭제
	@RequestMapping(value="/delete/{no}", method= { RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable(value="no") int boardNo, Model model) {
		System.out.println("BoardController.delete()"); 
		
		int count = boardDao.boardDelete(boardNo);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		// 게시판 목록 - 리다이렉트
		return "redirect:/board/list";
	}
	
}
