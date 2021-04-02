package com.bitc.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
	/*
	- 프로그램 개발시 모든 매개변수를 dto 타입으로 사용할 수 없기 때문에
	mybatis에서는 필요한 매개변수만 묶어서 map(hashmap) 타입으로 사용한다.
	- @Param("변수명") 어노테이션을 사용하여 mapper와 연동된 xml에서
	parameterType에 map으로 선언하여 매개변수를 사용.
	 */
	int selectUserInfoYn(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;
	
	//데이터가 많은 경우에는 dto를 선언해서 사용하는게 좋음
	//int selectUserInfoYn(LoginDto user) //sql xml의 parameterType="LoginDto"
}
