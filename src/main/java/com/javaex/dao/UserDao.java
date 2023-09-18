package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 로그인
	public UserVo userLogin(UserVo userVo) {
		System.out.println("UserDao.userLogin()");
		
		UserVo authUser = sqlSession.selectOne("user.selectAuthUser", userVo);
		
		return authUser;
	}
	
	// 회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao.userInsert()");
		
		int count = sqlSession.insert("user.userInsert", userVo);
		
		return count;
	}
	
	// 유저 정보 조회
	public UserVo userSelect(int no) {
		System.out.println("UserDao.userSelect()");
		
		UserVo userVo = sqlSession.selectOne("user.selectUser", no);
		
		return userVo;
	}
	
	// 정보 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao.userUpdate()");
		
		int count = sqlSession.update("user.userUpdate", userVo);
		
		return count;
	}
	
}
