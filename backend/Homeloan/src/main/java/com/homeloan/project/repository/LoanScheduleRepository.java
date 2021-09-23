package com.homeloan.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.TransactionType;

@Repository
public interface LoanScheduleRepository extends JpaRepository<LoanRepayment, String> {
	public List<LoanRepayment> getByLoanAccId(String loanAccId);
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonth(String loanAccId,int yrMonth);
	public List<LoanRepayment> getByLoanAccIdAndStatus(String loanAccId, PaymentStatus status);
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth, TransactionType transType);
	@Transactional
	public void deleteByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth, TransactionType transactionType);

}
