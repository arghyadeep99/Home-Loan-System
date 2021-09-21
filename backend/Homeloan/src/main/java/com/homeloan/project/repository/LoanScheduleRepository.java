package com.homeloan.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeloan.project.model.LoanRepayment;

@Repository
public interface LoanScheduleRepository extends JpaRepository<LoanRepayment, Long> {
	
}
