package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;

	// 방명록 목록
	public List<GuestVo> guestSelect() {
		System.out.println("GuestDao.guestSelect()");

		List<GuestVo> guestList = sqlSession.selectList("guest.guestSelect");
		
		return guestList;
	}
	
	// 방명록 작성
	public int guestInsert(GuestVo guestVo) {
		System.out.println("GuestDao.guestInsert()");

		int count = sqlSession.insert("guest.guestInsert", guestVo);
		
		return count;
	}
	
	// 방명록 삭제
	public int guestDelete(GuestVo guestVo) {
		System.out.println("GuestDao.guestDelete()");

		int count = sqlSession.delete("guest.guestDelete", guestVo);
		
		return count;
	}
	
}
