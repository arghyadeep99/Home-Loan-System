package com.homeloan.project.service;

import java.util.List;


import java.util.Optional;

import com.homeloan.project.model.LoanAccount;


public interface LoanAccountService {
	public String addLoanAccount(LoanAccount loanAccount); //
	public List<LoanAccount> getBySeqid(String seq_id);
	public Optional<LoanAccount> getLoanAccountByLoanAccountId(String id); //
	public LoanAccount updateLoanStatus(String loan_acc_id,String status);
	public List<LoanAccount> getLoanAccounts();
	public void deleteLoanAccount(String loan_acc_id); 
}
