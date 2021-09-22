package com.homeloan.project.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.repository.LoanScheduleRepository;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService {
	@Autowired
	LoanScheduleRepository loanScheduleRepository;

	@Override
	public List<LoanRepayment> getScheduleByYearMonth(int yearmonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSchedule(String loanAccId, double principal, double yearly_roi, int tenure) {
		int tenure_months = tenure*12;
		LoanRepaymentCalculationsImpl loanRepaymentCalculationsImpl = new LoanRepaymentCalculationsImpl();
		double emi = loanRepaymentCalculationsImpl.calculateEMI(principal, yearly_roi, tenure_months);
		double currentOutstanding = principal;
		double monthly_interest, monthly_principal;
		ArrayList<Double> outstandings = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyInterests = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyPrincipals = new ArrayList<Double>(tenure_months);
		//out.add(currentOutstanding);
		System.out.println(emi);
		for(int i = 0; i < tenure_months; i++) {
			monthly_interest = loanRepaymentCalculationsImpl.calculateMonthlyInterest(currentOutstanding, yearly_roi);
			monthly_principal = loanRepaymentCalculationsImpl.calculatePrincipal(emi, monthly_interest);
			currentOutstanding = loanRepaymentCalculationsImpl.calculateBalanceOutstanding(currentOutstanding, monthly_principal);
			outstandings.add(currentOutstanding);
			monthlyInterests.add(monthly_interest);
			monthlyPrincipals.add(monthly_principal);
		}

		for(int i = 0; i < tenure_months; i++) {
			System.out.printf("Monthly Interest: %.2f, Monthly Principal: %.2f, Outstanding: %.2f", monthlyInterests.get(i), monthlyPrincipals.get(i), outstandings.get(i));;
			 System.out.println();
			 
		}
		return "0";
	}

	@Override
	public void exportRepaymentScheduleInCsv() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LoanRepayment> getByLoanAccId(String loanAccId) {
		// TODO Auto-generated method stub
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

}
