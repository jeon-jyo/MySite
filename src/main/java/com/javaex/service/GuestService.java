package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;

	// 방명록 목록
	public List<GuestVo> guestSelect() {
		System.out.println("GuestService.guestSelect()");
		
		List<GuestVo> guestList = guestDao.guestSelect();
		
		return guestList;
	}

	// 방명록 작성
	public void guestInsert(GuestVo guestVo) {
		System.out.println("GuestService.guestInsert()");
		
		int count = guestDao.guestInsert(guestVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
	}
	
	// 방명록 작성 ajax
	public GuestVo guestWrite(GuestVo guestVo) {
		System.out.println("GuestService.guestWrite()");
		// System.out.println("1 : " + guestVo);	// no=0
		
		int count = guestDao.selectKey(guestVo);
		// System.out.println("2 : " + guestVo);	// no=24
		
		if(count != 0) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}

		// no 의 데이터 1개
		int no = guestVo.getNo();
		GuestVo vo = guestDao.selectGuestOne(no);
		
		return vo;
	}
	
	// 방명록 삭제
	public int guestDelete(GuestVo guestVo) {
		System.out.println("GuestService.guestDelete()");
		System.out.println("guestVo : " + guestVo);
		
		int count = guestDao.guestDelete(guestVo);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		return count;
	}
	
}
