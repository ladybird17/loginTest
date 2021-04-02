package com.bitc.login.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bitc.login.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*
		로그인 체크를 위한 클래스가 자동생성되어
		매 페이지를 열 때마다 로그인 체크를 진행함
		
		addPathPtterns("패턴") : 인터셉터를 사용할 컨트롤러를 선택
		excludePathPatterns(“주소”) : 사용하지 않을 컨트롤러를 선택
		*/
		registry.addInterceptor(new LoginCheckInterceptor())
		.addPathPatterns("/login/*")
		.excludePathPatterns("/login/loginFail")
		.excludePathPatterns("/login/login")
		.excludePathPatterns("/login/loginCheck");

	}
	
}
