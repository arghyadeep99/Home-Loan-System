package com.homeloan.project.service;

import java.util.List;
import java.util.Optional;

import com.homeloan.project.model.SavingsAccount;

public interface SavingsAccountService {
	public Optional<SavingsAccount> getSavingsAccountBySeqid(String seqId);
	public Optional<SavingsAccount> getSavingsAccountByAccountNumber(String accountNumber); //
	public SavingsAccount updateSavingsAccount(String seqId, Double emi);
	public List<SavingsAccount> getSavingsAccounts();
}
