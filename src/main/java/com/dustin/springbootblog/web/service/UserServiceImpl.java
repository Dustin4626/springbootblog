package com.dustin.springbootblog.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.dustin.springbootblog.model.User;
import com.dustin.springbootblog.web.dao.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User checkUser(String username, String password) {
		
		//springframework util MD5 加密
		String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
		
		User user = userRepository.findByUsernameAndPassword(username, md5Pwd);
		return user;
	}

}
