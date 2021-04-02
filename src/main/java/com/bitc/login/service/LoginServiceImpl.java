package com.bitc.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.login.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	//Mapper를 이용해서 DB의 데이터 가져오기
	@Override
	public int selectUserInfoYn(String userId, String userPw) throws Exception {
		
		return loginMapper.selectUserInfoYn(userId, userPw);
	}

}
