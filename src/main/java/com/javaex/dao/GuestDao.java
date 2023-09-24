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
	
	// 방명록 작성 ajax
	public int selectKey(GuestVo guestVo) {
		System.out.println("GuestDao.selectKey()");
		// System.out.println("1 : " + guestVo);	// no=0

		int count = sqlSession.insert("guest.insertSelectKey", guestVo);
		// System.out.println("2 : " + guestVo);	// no=24

		return count;
	}
	
	// 방명록 데이터 1개 ajax
	public GuestVo selectGuestOne(int no) {
		System.out.println("GuestDao.selectGuestOne()");
		
		GuestVo guestVo = sqlSession.selectOne("guest.selectGuestOne", no);
	
		return guestVo;
		// return sqlSession.selectOne("guest.selectGuestOne", no);
	}
	
	// 방명록 삭제
	public int guestDelete(GuestVo guestVo) {
		System.out.println("GuestDao.guestDelete()");

		int count = sqlSession.delete("guest.guestDelete", guestVo);
		
		return count;
	}

}
