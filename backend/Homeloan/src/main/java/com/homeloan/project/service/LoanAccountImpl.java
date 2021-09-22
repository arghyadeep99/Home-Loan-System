package com.homeloan.project.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.controller.MailRegistrationController;
import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.repository.LoanAccountRepository;

@Service
public class LoanAccountImpl implements LoanAccountService {

	@Autowired
	LoanAccountRepository loanAccountRepository;
	

	@Autowired
	MailRegistrationController newuser;
	@Autowired
	SavingsAccountService savingsAccountService;
	
	@Override
	public String addLoanAccount(LoanAccount loanAccount) {		
				
				
				SavingsAccountImpl savingsAccountImpl = new SavingsAccountImpl();
			

				Optional<SavingsAccount> object = savingsAccountService.getSavingsAccountBySeqid(loanAccount.getSeqid());
				SavingsAccount objectOfSavingAccount = object.get();
				
				double salaryAmount = objectOfSavingAccount.getSalarayAmount();
				
				if(loanAccount.getTotal_loan_amount() <= salaryAmount * 50) {
					LoanAccount loanAccount2 = loanAccountRepository.save(loanAccount);
					String emailOfUser = objectOfSavingAccount.getEmail();
					String nameOfUser = objectOfSavingAccount.getName();
					newuser.sendLoanApproval(emailOfUser,nameOfUser,loanAccount);
					return "success";
				}else {
					return "failed";
				}

	}

	@Override
	public List<LoanAccount> getBySeqid(String seqid) {
		return loanAccountRepository.getBySeqid(seqid);
	}

	@Override
	public Optional<LoanAccount> getLoanAccountByLoanAccountId(String loan_acc_id) {
		// TODO Auto-generated method stub
		return loanAccountRepository.findById(loan_acc_id);
	}

	@Override
	public List<LoanAccount> getLoanAccounts() {
		// TODO Auto-generated method stub
		return loanAccountRepository.findAll();
	}

	@Override
	public String updateLoanAccount(String loan_acc_id, LoanAccount loanAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteLoanAccount(String loan_acc_id) {
		// TODO Auto-generated method stub
		try {
			loanAccountRepository.deleteById(loan_acc_id);
			System.out.println("LoanAccount Deleted with id " + loan_acc_id);
		}catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		
	}

}
