package com.homeloan.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.UserLogin;
import com.homeloan.project.repository.UserLoginRepository;

@Service
public class UserLoginServiceImpl implements  UserDetailsService{
	@Autowired
	UserLoginRepository userLoginRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLogin user = userLoginRepository.findByUserId(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserLoginService(user);
	}
}


