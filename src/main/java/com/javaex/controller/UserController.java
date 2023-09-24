package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	// 로그인폼
	@RequestMapping(value="/loginForm", method= { RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		// 로그인폼 - 포워드
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping(value="/login", method= { RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session, Model model) {
		System.out.println("UserController.login()");
		
		UserVo authUser = userService.userLogin(userVo);
		
		if(authUser != null) {
			System.out.println("로그인 성공");
			System.out.println("authUser : " + authUser);
			
			// 로그인 - 세션에 값 넣기
			session.setAttribute("authUser", authUser);
			
			// 메인 - 리다이렉트
			return "redirect:/";

		} else {
			System.out.println("로그인 실패");
			
			model.addAttribute("result", "fail");
			
			// 로그인폼 - 리다이렉트
			return "redirect:/user/loginForm";
		}
	}
	
	// 로그아웃
	@RequestMapping(value="/logout", method= { RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		// 로그아웃 - 세션의 모든 값을 지움
		session.invalidate();
		
		// 메인 - 리다이렉트
		return "redirect:/";
	}
	
	// 회원가입폼
	@RequestMapping(value="/joinForm", method= { RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		// 회원가입폼 - 포워드
		return "user/joinForm";
	}
	
	// 중복체크 ajax
	@ResponseBody
	@RequestMapping(value="/checkId", method= { RequestMethod.GET, RequestMethod.POST})
	public String checkId(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.checkId()");
		
		String result = userService.checkId(userVo);

		return result;
	}
	
	// 회원가입
	@RequestMapping(value="/join", method= { RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController.join()");
		
		userService.userInsert(userVo);

		// 가입성공 안내페이지 - 포워드
		return "user/joinOk";
	}
	
	// 정보수정폼
	@RequestMapping(value="/modifyForm", method= { RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(HttpSession session, Model model) {
		System.out.println("UserController.modifyForm()");
		
		// 유저 정보
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		UserVo userVo = userService.userSelect(no);
		
		boolean isFemale = false;
		if(userVo.getGender().equals("female")) {
			isFemale = true;
		}
		model.addAttribute("isFemale", isFemale);
		model.addAttribute("userVo", userVo);
		
		// 수정폼 - 포워드
		return "user/modifyForm";
	}
	
	// 정보 수정
	@RequestMapping(value="/modify", method= { RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.modify()");
		
		UserVo vo = userService.userUpdate(userVo);
		if(vo != null) {
			// 세션 정보 변경
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			authUser.setName(vo.getName());
			
			session.setAttribute("authUser", authUser);
			System.out.println("authUser : " + authUser);
		}
		
		// 메인 - 리다이렉트
		return "redirect:/";
	}
	
}
