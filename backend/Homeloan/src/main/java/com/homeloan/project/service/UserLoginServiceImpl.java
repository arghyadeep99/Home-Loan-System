package com.homeloan.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.UserLogin;
import com.homeloan.project.repository.UserLoginRepository;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	@Autowired
	UserLoginRepository userLoginRepository;

	@Override
	public  Optional<UserLogin> getUserid(String userid) {
		// TODO Auto-generated method stub
		return userLoginRepository.findById(userid);
	}

	@Override
	public String getPassword(String userid) {
		// TODO Auto-generated method stub
		return "Hie";
	}

}
