package com.homeloan.project.service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.controller.MailRegistrationController;
import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.model.TransactionType;
import com.homeloan.project.repository.LoanAccountRepository;

@Service
public class LoanAccountImpl implements LoanAccountService {

	@Autowired
	LoanAccountRepository loanAccountRepository;
	

	@Autowired
	MailRegistrationController newuser;
	@Autowired
	SavingsAccountService savingsAccountService;
	@Autowired
	LoanRepaymentService loanRepaymentService;
	@Autowired
	LoanRepaymentCalculations loanRepaymentCalculations;
	
	@Override
	public String addLoanAccount(LoanAccount loanAccount) {		
				SavingsAccountImpl savingsAccountImpl = new SavingsAccountImpl();
				Optional<SavingsAccount> object = savingsAccountService.getSavingsAccountBySeqid(loanAccount.getSeqid());
				SavingsAccount objectOfSavingAccount = object.get();
			
				double salaryAmount = objectOfSavingAccount.getSalarayAmount();
				Map<String,List<Double>> returnedSchedule = loanRepaymentService.createSchedule(loanAccount.getLoan_acc_id(), loanAccount.getTotal_loan_amount(), loanAccount.getRoi(), loanAccount.getTenure());
				if(loanAccount.getTotal_loan_amount() <= salaryAmount * 50) {
					LoanAccount loanAccount2 = loanAccountRepository.save(loanAccount);
					String emailOfUser = objectOfSavingAccount.getEmail();
					String nameOfUser = objectOfSavingAccount.getName();
					newuser.sendLoanApproval(emailOfUser,nameOfUser,loanAccount,returnedSchedule);
					Date date = Date.valueOf("2021-09-22");
					LocalDate paymentDate = date.toLocalDate();
					
					double emi = loanRepaymentCalculations.calculateEMI(loanAccount.getTotal_loan_amount(), loanAccount.getRoi(), loanAccount.getTenure()*12);
					emi = Double.parseDouble(new DecimalFormat("#.##").format(emi));
					
					int scheduleLength = returnedSchedule.get("monthlyInterests").size();
					for(int i = 0; i<scheduleLength; i++) {
						double monthlyInterest = Double.parseDouble(new DecimalFormat("#.##").format(returnedSchedule.get("monthlyInterests").get(i)));
						double monthlyPrincipal = Double.parseDouble(new DecimalFormat("#.##").format(returnedSchedule.get("monthlyPrincipals").get(i)));
						double monthlyOutstanding =Double.parseDouble(new DecimalFormat("#.##").format(returnedSchedule.get("monthlyOutstandings").get(i)));
						LoanRepayment LRP = new LoanRepayment(loanAccount.getLoan_acc_id(), i+1 , paymentDate, TransactionType.EMI, emi, monthlyPrincipal, monthlyInterest, monthlyOutstanding, PaymentStatus.PENDING);
						paymentDate = paymentDate.plusMonths(1);
						loanRepaymentService.addToSchedule(LRP);
					}
					
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
	public LoanAccount updateLoanStatus(String loan_acc_id, String status) {
		// TODO Auto-generated method stub
		
		Optional<LoanAccount> prev_Object = getLoanAccountByLoanAccountId(loan_acc_id);
		LoanAccount returned_prev_Object  = prev_Object.get();
		returned_prev_Object.setStatus(status);
		LoanAccount loanAccountRepoReturned =  loanAccountRepository.save(returned_prev_Object);
		return loanAccountRepoReturned;
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
