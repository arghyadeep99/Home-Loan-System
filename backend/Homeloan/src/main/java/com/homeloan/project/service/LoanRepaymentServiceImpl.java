package com.homeloan.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.TransactionType;
import com.homeloan.project.repository.LoanScheduleRepository;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService {
	@Autowired
	LoanScheduleRepository loanScheduleRepository;

	@Override
	public Map<String,List<Double>> createSchedule(String loanAccId, double principal, double yearly_roi, int tenure) {
		int tenure_months = tenure*12;
		LoanRepaymentCalculationsImpl loanRepaymentCalculationsImpl = new LoanRepaymentCalculationsImpl();
		double emi = loanRepaymentCalculationsImpl.calculateEMI(principal, yearly_roi, tenure_months);
		double currentOutstanding = principal;
		double monthly_interest, monthly_principal;
		ArrayList<Double> monthlyOutstandings = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyInterests = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyPrincipals = new ArrayList<Double>(tenure_months);
		//out.add(currentOutstanding);
		//System.out.println(emi);
		for(int i = 0; i < tenure_months; i++) {
			monthly_interest = loanRepaymentCalculationsImpl.calculateMonthlyInterest(currentOutstanding, yearly_roi);
			monthly_principal = loanRepaymentCalculationsImpl.calculatePrincipal(emi, monthly_interest);
			currentOutstanding = loanRepaymentCalculationsImpl.calculateBalanceOutstanding(currentOutstanding, monthly_principal);
			monthlyOutstandings.add(currentOutstanding);
			monthlyInterests.add(monthly_interest);
			monthlyPrincipals.add(monthly_principal);
		}

//		for(int i = 0; i < tenure_months; i++) {
//			System.out.printf("Monthly Interest: %.2f, Monthly Principal: %.2f, Outstanding: %.2f", monthlyInterests.get(i), monthlyPrincipals.get(i), outstandings.get(i));;
//			 System.out.println();
//			 
//		}
		Map<String,List<Double>> returnMap = new HashMap<String, List<Double>>();
		returnMap.put("monthlyInterests",monthlyInterests);
		returnMap.put("monthlyPrincipals", monthlyPrincipals);
		returnMap.put("monthlyOutstandings", monthlyOutstandings);
		return returnMap;
	}

	@Override
	public void exportRepaymentScheduleInCsv() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LoanRepayment> getByLoanAccId(String loanAccId) {
		
		return loanScheduleRepository.getByLoanAccId(loanAccId);
	}

	@Override
	public String addToSchedule(LoanRepayment loanRepayment) {
		// TODO Auto-generated method stub
		LoanRepayment LR = loanScheduleRepository.save(loanRepayment);
		if (LR != null)
			return "success";
		else
			return null;
	}

	@Override
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonth(String loanAccId, int yrMonth){
		return loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId,yrMonth);
	}
	
	@Override
	public List<LoanRepayment> getByLoanAccIdAndStatus(String loanAccId, PaymentStatus status){
		
		return loanScheduleRepository.getByLoanAccIdAndStatus(loanAccId, status);
	}

	
	@Override
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth,
			TransactionType transType) {
		
		return loanScheduleRepository.getByLoanAccIdAndYrMonthAndTransactionType(loanAccId, yrMonth, transType);
	}
	
	@Override
	public void deleteByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth,
			TransactionType transType) {
		
		loanScheduleRepository.deleteByLoanAccIdAndYrMonthAndTransactionType(loanAccId, yrMonth, transType);
	}
	
	
	@Override
	public String PayEmi(String loanAccId, int yearMonth, double EmiPaidByCust) {
		
		//TODO: Implement updating SavingsAccount here. Can use EmiPaidByCust variable.
		
		Optional<LoanRepayment> lr1 = loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId, yearMonth);
		if (lr1 != null) {
			LoanRepayment loanRepayment = lr1.get();
			loanRepayment.setStatus(PaymentStatus.PAID);
			loanScheduleRepository.save(loanRepayment);
			return "Status updated";
		}
		return null;
	}

	@Override
	public String Foreclosure(String loanAccId, int lastPaidYrMonth, LocalDate foreclosurePaymentDate, int tenure) {
		
		//TODO: Implement updating SavingsAccount here. Can use EmiPaidByCust variable.
		int tenure_months = tenure * 12;
		Optional<LoanRepayment> lr1 = loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId, lastPaidYrMonth + 1);
		if (lr1 != null) {
			LoanRepayment loanRepayment = lr1.get();
			
			loanRepayment.setStatus(PaymentStatus.PAID);
			loanRepayment.setTransactionType(TransactionType.FC);
			
			loanScheduleRepository.save(loanRepayment);
			
			loanScheduleRepository.deleteByLoanAccIdAndYrMonthAndTransactionType(loanAccId, lastPaidYrMonth + 1, TransactionType.EMI);
			
			for(int i = lastPaidYrMonth+2; i <= tenure_months; i++) {
				Optional<LoanRepayment> lr2 = loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId, i);
				LoanRepayment nextLoanRepayment = lr2.get();
				nextLoanRepayment.setEmi(0);
				nextLoanRepayment.setInterestAmount(0);
				nextLoanRepayment.setOutstandingAmount(0);
				nextLoanRepayment.setPrincipal(0);
				nextLoanRepayment.setPaymentDate(foreclosurePaymentDate);
				nextLoanRepayment.setStatus(PaymentStatus.CANCELLED);
				loanScheduleRepository.save(nextLoanRepayment);
			}
			return "Success";
		}
		return null;
	}

	@Override
	public String Prepayment(String loanAccId, int yearMonth, double EmiPaidByCust) {
		// TODO Auto-generated method stub
		return null;
	}

}
