package com.homeloan.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.homeloan.project.model.UserLogin;
@EnableJpaRepositories
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String>{
	
}
