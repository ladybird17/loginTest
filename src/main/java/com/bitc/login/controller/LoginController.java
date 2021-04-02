package com.bitc.login.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitc.login.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	//사용자가 login 페이지에 접속하여 id,pw를 입력
	//login 양식 페이지
	@RequestMapping(value="/login/login", method=RequestMethod.GET)
	public String login() throws Exception{
		return "/login/login";
	}
	
	//	서버의 controller에서 service를 이용하여 db에 저장된 유저 정보를 조회
	//	login id 체크를 하는 페이지(loginCheck)
	//form에서 받은 데이터 변수명과 param 변수명이 같아야함. 아니면 오류남
	//HttpServletRequest를 통해 세션 기능 이용. 세션에 대한 설명 0402필기에 적어둠.
	
	@RequestMapping(value="/login/loginCheck", method=RequestMethod.POST)
	public String loginCheck(@RequestParam String userId, @RequestParam String userPw, HttpServletRequest request) throws Exception{
		//입력받은 정보를 바탕으로 DB에 연결하여 해당 id와 pw를 가지고 있는 사용자가 존재하는지 조회한다.
		int count = loginService.selectUserInfoYn(userId, userPw);
		
		if(count == 1) {
			//조회된 결과값 바탕으로 결과가 존재하면 세션에 등록
			//현재 서버에 접속한 클라이언트의 세션 정보를 가져옴.
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setMaxInactiveInterval(30); //세션 보존 시간 설정
			
			//세션은 해당 서버에서 로그아웃(세션삭제) 전까지 계속 남아있음
			
			return "redirect:/login/loginOK";
		}
		else {
			//결과가 없으면 loginFail 페이지로 이동
			return "redirect:/login/loginFail";
		}
		
	}
	
	//	조회 결과가 존재할 경우 loginOK 페이지로 이동
	@RequestMapping(value="/login/loginOK", method=RequestMethod.GET)
	public String loginOK(HttpServletRequest request) throws Exception{
		
		//HttpSession session = request.getSession();
		
		return "/login/loginOK";
	}
	
	//	조회 결과가 존재하지 않을 경우 loginFail 페이지로 이동하거나 메시지 출력
	@RequestMapping(value="/login/loginFail", method=RequestMethod.GET)	
	public String loginFail() throws Exception{
		return "/login/loginFail";
	}
	
	//로그아웃페이지
	@RequestMapping(value="login/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		//세션에 저장된 내용을 지정하여 삭제
		session.removeAttribute("userId");
		// 모든 세션 정보 삭제
		session.invalidate(); 
		
		return "/login/logout";
	}
	
	//세션에서 로그아웃 제대로 됐는지 이 페이지 이용해서 확인
	@RequestMapping(value="/login/sessionTest", method=RequestMethod.GET)
	public String sessionTest() throws Exception{
		return "/login/sessionTest";
	}
	
	//	매 페이지 이동시마다 로그인 여부를 확인(인터셉터로 확인)
}
