package com.homeloan.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.TransactionType;

public interface LoanRepaymentService {
	public String addToSchedule(LoanRepayment loanRepayment);
	public  Map<String,List<Double>> createSchedule(String loanAccId, double principal, double yearly_roi, int tenure);
	public void exportRepaymentScheduleInCsv();
	public List<LoanRepayment> getByLoanAccId(String loanAccId);
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonth(String loanAccId,int yrMonth);
	public List<LoanRepayment> getByLoanAccIdAndStatus(String loanAccId, PaymentStatus status);
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth,
			TransactionType transType);
	public void deleteByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth,
			TransactionType transType);
	public String PayEmi(String loanAccId,  int yearMonth, double EmiPaidByCust);
	public String Prepayment(String loanAccId, int yearMonth, double EmiPaidByCust);
	public String Foreclosure(String loanAccId, int yearMonth, LocalDate foreclosurePaymentDate, int tenure);

	
}


