package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	// 첨부파일연습폼
	@RequestMapping(value="/form", method= { RequestMethod.GET, RequestMethod.POST})
	public String form() {
		System.out.println("FileController.form()");
		
		return "gallery/form";
	}
	
	// 파일 업로드
	@RequestMapping(value="/upload", method= { RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		System.out.println("FileController.upload()");
		/*
		 * System.out.println(file);
		 * 	-> org.springframework.web.multipart.commons.CommonsMultipartFile@938b135
		 *  -> 파일을 안 보내줘도 이 객체를 만들어서 확인 불가
		 */
		System.out.println("file.isEmpty() : " + file.isEmpty());	// true or false
		
		String saveName = fileService.save(file);	// DB저장, 서버에 파일 복사
		model.addAttribute("saveName", saveName);
		
		return "gallery/result";
	}
	
}
