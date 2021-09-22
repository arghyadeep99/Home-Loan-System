package com.homeloan.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.repository.SavingsAccountRepository;

@Service
public class SavingsAccountImpl implements SavingsAccountService {

	@Autowired
	SavingsAccountRepository savingsAccountRepository;
	
	@Override
	public Optional<SavingsAccount> getSavingsAccountBySeqid(String seqId) {
		// TODO Auto-generated method stub
		return savingsAccountRepository.findById(seqId);
	}
	
	//don't use this
	@Override
	public Optional<SavingsAccount> getSavingsAccountByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return savingsAccountRepository.findById(accountNumber);
	}

	@Override
	public SavingsAccount updateSavingsAccount(String seqId, Double emi) {
		// TODO Auto-generated method stub
		Optional<SavingsAccount> tempObject = savingsAccountRepository.findById(seqId);
		SavingsAccount obj = tempObject.get();
		System.out.println("before update : ");
		System.out.println( obj.getAccountNumber() + " " + obj.getCurrentBalance() );
		if(obj.getCurrentBalance() < emi)
		{
			return null;
		}
		obj.setCurrentBalance( obj.getCurrentBalance() - emi);
		System.out.println("after update : ");
		System.out.println( obj.getAccountNumber() + " " + obj.getCurrentBalance() );
		SavingsAccount updatedObject =  savingsAccountRepository.save(obj);
		if(updatedObject != null)
			return updatedObject;
		else
			return null;	
	}

	@Override
	public List<SavingsAccount> getSavingsAccounts() {
		// TODO Auto-generated method stub
		return savingsAccountRepository.findAll();
	}

}
