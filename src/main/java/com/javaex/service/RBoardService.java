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

	// 게시판 목록
	public List<RBoardVo> boardList(String keyword) {
		System.out.println("RBoardService.boardList()");
		System.out.println("keyword : " + keyword);
		
		List<RBoardVo> boardList = rBoardDao.boardList(keyword);
		System.out.println(boardList);
		
		return boardList;
	}

	
}
