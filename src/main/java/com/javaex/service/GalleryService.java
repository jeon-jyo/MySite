package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	// 갤러리 목록
	public List<GalleryVo> galleryList() {
		System.out.println("GalleryService.galleryList()");
		
		List<GalleryVo> galleryList = galleryDao.galleryList();
		
		return galleryList;
	}
	
	// 파일 업로드
	public void galleryUpload(GalleryVo galleryVo, MultipartFile file) {
		System.out.println("GalleryService.galleryUpload()");
		
		// 파일저장 디렉토리
		String saveDir = "C:\\Jiho\\HiMedia\\JavaStudy\\upload";
		
		// (1)파일관련 정보 추출 /////////////////////////
		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		// 저장파일명(겹치지 않아야 된다)
		String saveName = System.currentTimeMillis()
				+ UUID.randomUUID().toString()
				+ exName;
		System.out.println("saveName : " + saveName);
		
		// 파일사이즈
		long fileSize = file.getSize();
		
		// 파일 전체경로
		String filePath = saveDir + "\\" + saveName;
		
		// vo로 묶기
		galleryVo.setFilePath(filePath);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFileSize(fileSize);
		
		// Dao 만들기 -> DB저장
		int count = galleryDao.galleryUpload(galleryVo);
		if(count == 1) {
			System.out.println("등록 성공");
			
			// (2)파일저장 - 서버쪽 하드디스크에 저장 /////////////////////////
			try {
				byte[] fileDate;
				fileDate = file.getBytes();
				
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				
				bos.write(fileDate);
				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("등록 실패");
		}
	}

	// 갤러리 상세보기 ajax
	public GalleryVo galleryDetail(GalleryVo galleryVo) {
		System.out.println("GalleryService.galleryDetail()");
		
		GalleryVo vo = galleryDao.galleryDetail(galleryVo);
		
		return vo;
	}

	// 갤러리 삭제 ajax
	public int galleryDelete(GalleryVo galleryVo) {
		System.out.println("GalleryService.galleryDelete()");
		
		int count = galleryDao.galleryDelete(galleryVo);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		return count;
	}
	
}
