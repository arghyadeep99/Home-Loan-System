package com.homeloan.project.service;

import java.util.Optional;

import com.homeloan.project.model.UserLogin;

public interface UserLoginService {
	
	public Optional<UserLogin> getUserid(String userid);
	public String getPassword(String userid);

}
