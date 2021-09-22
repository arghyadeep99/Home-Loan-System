package com.homeloan.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.homeloan.project.model.UserLogin;
@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, String>{
	@Query("SELECT u FROM UserLogin u WHERE u.userid = ?1")
	public UserLogin findByUserId(String userid);
	
}
