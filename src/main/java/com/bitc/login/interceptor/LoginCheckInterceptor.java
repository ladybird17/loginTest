package com.bitc.login.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/*
HandlerInterceptorAdapter 클래스는 삭제되었기 때문에 HandlerInterceptor 인터페이스를 사용
preHandle() : 지정한 컨트롤러가 동작하기 직전에 먼저 수행
postHandle() : 지정한 컨트롤러가 동작 후 View를 표시하기 직전에 수행
afterCompletion() : 모든 동작이 완료된 후 수행
*/
public class LoginCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		//현재 클라이언트 정보를 받아와서 세션 생성
		HttpSession session = request.getSession();
		
		/*
		-기존 세션에 등록된 userId속성을 가지고 와서 
		 UserInfoDto 클래스타입의 객체에 저장.
		-기존 세션에 userId속성이 존재할 경우 
		 userInfoDto 클래스 객체에 데이터가 저장되고,
		 아니면 값이 비어있는 객체가 생성됨.
		*/
		//UserInfoDto user = (UserInfoDto)session.getAttribute("userId");
		
		/*
		스프링에서 지원하는 ObjectUtils 클래스의 
		isEmpty 메서드를 이용하여 위에서 생성된
		UserInfoDto 클래스 타입의 객체가 비었는지
		아니면 데이터가 있는지 확인
		 */
		if((String)session.getAttribute("userId")==null) {
			System.out.println("============interceptor============");
			System.out.println("비 로그인 상태 : ");
			System.out.println((String)session.getAttribute("userId"));
			
			response.sendRedirect("/login/loginFail");
			return false;
		}
		else {
			System.out.println("============interceptor============");
			System.out.println("로그인 상태 : ");
			System.out.println((String)session.getAttribute("userId"));
			
			session.setMaxInactiveInterval(600);//10분동안
			return true;
		}
	}
}
