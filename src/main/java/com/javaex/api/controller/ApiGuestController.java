package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;
import com.javaex.vo.JsonResultVo;

@Controller
@RequestMapping("/api/guest")
public class ApiGuestController {
	
	@Autowired
	private GuestService guestService;

	// 방명록 목록
	@RequestMapping(value="/addList", method= { RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("ApiGuestController.addList()");

		return "guest/listAjax";
	}
	
	// 방명록 전체 데이터 가져오기 (데이터만 전송) ajax
	@ResponseBody
	@RequestMapping(value="/list", method= { RequestMethod.GET, RequestMethod.POST})
	public List<GuestVo> list() {
		System.out.println("ApiGuestController.list()");
		
		List<GuestVo> guestList = guestService.guestSelect();
		
		return guestList;	// 알려준 것은 메모리 주소값
	}
	
	// 방명록 작성 ajax
	@ResponseBody
	@RequestMapping(value="/insert", method= { RequestMethod.GET, RequestMethod.POST})
	public GuestVo insert(@ModelAttribute GuestVo guestVo) {
		System.out.println("ApiGuestController.insert()");
		
		GuestVo vo = guestService.guestWrite(guestVo);
		
		return vo;
	}
	
	// 방명록 삭제 ajax
	@ResponseBody
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public GuestVo delete(@ModelAttribute GuestVo guestVo) {
		System.out.println("ApiGuestController.delete()");
		
		GuestVo vo = null;
		
		int count = guestService.guestDelete(guestVo);
		if(count == 1) {
			return guestVo;
		} else {
			return vo;
		}
	}
	
	// ajax방명록2 ////////////////////////////////////////////////
	
	// 방명록 목록2
	@RequestMapping(value="/addList2", method= { RequestMethod.GET, RequestMethod.POST})
	public String addList2() {
		System.out.println("ApiGuestController.addList2()");

		return "guest/listAjax2";
	}
	
	// 방명록 작성 ajax2 - 요청 데이터를 json으로 받기
	@ResponseBody
	@RequestMapping(value="/insert2", method= { RequestMethod.GET, RequestMethod.POST})
	public JsonResultVo insert2(@RequestBody GuestVo guestVo) {
		System.out.println("ApiGuestController.insert2()");
		System.out.println("guestVo : " + guestVo);
		
		GuestVo vo = guestService.guestWrite(guestVo);
		
		JsonResultVo jsonResultVo = new JsonResultVo();
		jsonResultVo.success(vo);
		
		return jsonResultVo;
	}
	
	// 방명록 작성 ajax3 - 복잡한 데이터 전송 테스트
	@ResponseBody
	@RequestMapping(value="/insert3", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert3(@RequestBody List<GuestVo> guestList) {
		System.out.println("ApiGuestController.insert3()");
		System.out.println(guestList);

		return "";
	}
	
}
