package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private GuestDao guestDao;
	
	// 방명록 목록
	@RequestMapping(value="/addList", method= { RequestMethod.GET, RequestMethod.POST})
	public String main(Model model) {
		System.out.println("GuestController.addList()");
		
		List<GuestVo> guestList = guestDao.guestSelect();
		
		model.addAttribute("guestList", guestList);
		
		// 방명록 목록 - 포워드
		return "guest/addList";
	}
	
	// 방명록 작성
	@RequestMapping(value="/insert", method= { RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.insert()");

		int count = guestDao.guestInsert(guestVo);
		if(count == 1) {
			System.out.println("등록 성공");
		} else {
			System.out.println("등록 실패");
		}
		
		// 방명록 목록 - 리다이렉트
		return "redirect:/guest/addList";
	}
	
	// 방명록 삭제폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable(value="no") int GuestNo, Model model) {
		System.out.println("GuestController.deleteForm()");
		System.out.println("GuestNo : " + GuestNo);
	
		model.addAttribute("no", GuestNo);
		
		// 방명록 삭제폼 - 포워드
		return "guest/deleteForm";
	}
	
	// 방명록 삭제
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("GuestController.delete()");
		System.out.println("guestVo : " + guestVo);
		
		int count = guestDao.guestDelete(guestVo);
		if(count == 1) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		// 방명록 목록 - 리다이렉트
		return "redirect:/guest/addList";
	}
	
}
