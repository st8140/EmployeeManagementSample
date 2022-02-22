package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.MUser;
import com.example.repository.UserMapper;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	/** ユーザー登録 */
	@Override
	public void signup(MUser mUser) {
		mapper.insertOne(mUser);
	}

}
