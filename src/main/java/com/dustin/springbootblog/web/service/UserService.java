package com.dustin.springbootblog.web.service;

import com.dustin.springbootblog.model.User;

public interface UserService {
	
	User checkUser(String username, String password);
	
}
