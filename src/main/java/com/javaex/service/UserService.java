package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	// 로그인
	public UserVo userLogin(UserVo userVo) {
		System.out.println("UserService.userLogin()");
		
		UserVo authUser = userDao.userLogin(userVo);
		
		return authUser;
	}
	
	// 중복체크 ajax
	public String checkId(UserVo userVo) {
		System.out.println("UserService.checkId()");
		
		UserVo vo = userDao.checkId(userVo);
		if(vo == null) {
			return "";
		} else {
			return vo.getId();
		}
	}

	// 회원가입
	public void userInsert(UserVo userVo) {
		System.out.println("UserService.userInsert()");
		
		int count = userDao.userInsert(userVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
	}

	// 정보수정폼
	public UserVo userSelect(int no) {
		System.out.println("UserService.userSelect()");
		
		UserVo userVo = userDao.userSelect(no);
		System.out.println("기존 정보 : " + userVo);
		
		return userVo;
	}

	// 정보 수정
	public UserVo userUpdate(UserVo userVo) {
		System.out.println("UserService.userUpdate()");
		System.out.println("변경 정보 : " + userVo);
		
		UserVo vo = null;
		
		int count = userDao.userUpdate(userVo);
		if(count == 1) {
			System.out.println("수정 성공");
			
			int userNo = userVo.getNo();
			vo = userDao.userSelect(userNo);
			
			return vo;
			
		} else {
			System.out.println("수정 실패");
			return vo;
		}
	}
	
}
