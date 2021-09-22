package com.homeloan.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeloan.project.model.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, String> {
	
}
