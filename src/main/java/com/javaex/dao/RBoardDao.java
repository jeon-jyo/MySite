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

	
}
