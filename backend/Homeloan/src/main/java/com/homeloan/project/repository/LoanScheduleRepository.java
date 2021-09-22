package com.homeloan.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeloan.project.model.LoanRepayment;

@Repository
public interface LoanScheduleRepository extends JpaRepository<LoanRepayment, String> {
	public List<LoanRepayment> getByLoanAccId(String loanAccId);
	
}
