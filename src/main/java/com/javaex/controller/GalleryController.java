package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.JsonResultVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	// 갤러리 목록
	@RequestMapping(value="/list", method= { RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVo> galleryList = galleryService.galleryList();
		
		model.addAttribute("galleryList", galleryList);
		
		// 갤러리 목록 - 포워드
		return "gallery/list";
	}
	
	// 파일 업로드
	@RequestMapping(value="/upload", method= { RequestMethod.GET, RequestMethod.POST})
	public String upload(@ModelAttribute GalleryVo galleryVo,
			@RequestParam(value="file") MultipartFile file, HttpSession session) {
		System.out.println("GalleryController.upload()");
		System.out.println("file.isEmpty() : " + file.isEmpty());

		UserVo authUser = (UserVo)session.getAttribute("authUser");
		galleryVo.setUserNo(authUser);
		
		galleryService.galleryUpload(galleryVo, file);	// DB저장, 서버에 파일 복사
		
		// 갤러리 목록 - 리다이렉트
		return "redirect:/gallery/list";
	}
	
	// 갤러리 상세보기 ajax
	@ResponseBody
	@RequestMapping(value="/detail", method= { RequestMethod.GET, RequestMethod.POST})
	public JsonResultVo detail(@ModelAttribute GalleryVo galleryVo) {
		System.out.println("GalleryController.detail()");
		
		GalleryVo vo = galleryService.galleryDetail(galleryVo);
		
		JsonResultVo jsonResultVo = new JsonResultVo();
		jsonResultVo.success(vo);
		
		return jsonResultVo;
	}
	
	// 갤러리 삭제 ajax
	@ResponseBody
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo delete(@ModelAttribute GalleryVo galleryVo) {
		System.out.println("GalleryController.delete()");
		
		GalleryVo vo = null;
		
		int count = galleryService.galleryDelete(galleryVo);
		if(count == 1) {
			return galleryVo;
		} else {
			return vo;
		}
	}
	
}
