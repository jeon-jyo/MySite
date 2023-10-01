package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 갤러리 목록
	public List<GalleryVo> galleryList() {
		System.out.println("GalleryDao.galleryList()");
		
		List<GalleryVo> galleryList = sqlSession.selectList("gallery.galleryList");
		
		return galleryList;
	}
	
	// 파일 업로드
	public int galleryUpload(GalleryVo galleryVo) {
		System.out.println("GalleryDao.galleryUpload()");
		
		int count = sqlSession.insert("gallery.galleryUpload", galleryVo);
		
		return count;
	}

	// 갤러리 상세보기 ajax
	public GalleryVo galleryDetail(GalleryVo galleryVo) {
		System.out.println("GalleryDao.galleryDetail()");
		
		GalleryVo vo = sqlSession.selectOne("gallery.galleryDetail", galleryVo);
		
		return vo;
	}
	
}
