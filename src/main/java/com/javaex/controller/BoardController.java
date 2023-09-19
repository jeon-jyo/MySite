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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 게시판 목록
	@RequestMapping(value="/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardController.list()");

		// boardService를 통해서 리스트를 가져온다 (boardDao 바로 X)
		List<BoardVo> boardList =  boardService.boardList("");
		
		model.addAttribute("boardList", boardList);
		
		// 게시판 목록 - 포워드
		return "board/list";
	}
	
	// 게시판 검색
	@RequestMapping(value="/search", method= { RequestMethod.GET, RequestMethod.POST})
	public String search(@RequestParam(value="keyword") String keyword, Model model) {
		System.out.println("BoardController.search()");
		
		List<BoardVo> boardList = boardService.boardList(keyword);
		
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
		boardVo.setUserNo(authUser);
		
		boardService.boardInsert(boardVo);

		// 게시판 목록 - 리다이렉트
		return "redirect:/board/list";
	}
	
	// 게시판 상세보기
	@RequestMapping(value="/detail/{no}", method= { RequestMethod.GET, RequestMethod.POST})
	public String detail(@PathVariable(value="no") int boardNo, Model model) {
		System.out.println("BoardController.detail()");
		
		BoardVo boardVo = boardService.boardDetail(boardNo, true);
		
		if (boardVo != null) {
			model.addAttribute("boardVo", boardVo);
			
			// 게시판 상세 - 포워드
			return "board/read";
		} else {
			// 게시판 목록 - 리다이렉트
			return "redirect:/board/list";
		}
	}
	
	// 게시판 수정폼
	@RequestMapping(value="/modifyForm/{no}", method= { RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@PathVariable(value="no") int boardNo, Model model) {
		System.out.println("BoardController.modifyForm()");
		
		BoardVo boardVo = boardService.boardDetail(boardNo, false);
		
		model.addAttribute("boardVo", boardVo);
		
		// 게시판 상세 - 포워드
		return "board/modifyForm";
	}
	
	// 게시판 수정
	@RequestMapping(value="/modify", method= { RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController.modify()"); 
		
		boardService.boardUpdate(boardVo);
		
		// 게시판 목록 - 리다이렉트
		return "redirect:/board/list";
	}
	
	// 게시판 삭제
	@RequestMapping(value="/delete/{no}", method= { RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable(value="no") int boardNo) {
		System.out.println("BoardController.delete()"); 
		
		boardService.boardDelete(boardNo);

		// 게시판 목록 - 리다이렉트
		return "redirect:/board/list";
	}
	
}
