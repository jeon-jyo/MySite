package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;

@Controller
@RequestMapping("/rBoard")
public class RBoardController {

	@Autowired
	private RBoardService rBoardService;

	// 게시판 목록
	@RequestMapping(value="/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("RBoardController.list()");

		List<RBoardVo> boardList =  rBoardService.boardList("");
		
		model.addAttribute("boardList", boardList);
		
		// 게시판 목록 - 포워드
		return "rboard/list";
	}
	
}
