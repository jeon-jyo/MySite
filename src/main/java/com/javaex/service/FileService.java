package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileService {

	public String save(MultipartFile file) {
		System.out.println("FileService.save()");
		
		// 파일저장 디렉토리
		String saveDir = "C:\\Jiho\\HiMedia\\JavaStudy\\upload";
		
		// (1)파일관련 정보 추출 /////////////////////////
		// 오리지널 파일명
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : " + orgName);			// sarang.jpg
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exNames : " + exName);			// .jpg
		
		System.out.println(UUID.randomUUID());				// 범용 고유 식별자 - 20bf1c4c-5c22-491f-806f-84db6c018c74
		System.out.println(System.currentTimeMillis());		// 1695696579021
		
		// 저장파일명(겹치지 않아야 된다)
		String saveName = System.currentTimeMillis()
				+ UUID.randomUUID().toString()
				+ exName;									// long + tostring() = 문자열
		System.out.println("saveName : " + saveName);		// 169569657902144926a22-4fab-40e1-8f82-5b208c01f9d5.jpg
		
		
		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize : " + fileSize);		// 473881
		
		// 파일 전체경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : " + filePath);		// C:\Jiho\HiMedia\JavaStudy\\upload\1695696781552dee33753-6f3e-4de5-b203-f771208392d4.jpg
		
		// vo로 묶기
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println("DB저장 : " + fileVo);
		
		// Dao 만들기 -> DB저장
		
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
		
		return saveName;
	}
	
}
