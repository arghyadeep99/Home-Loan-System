package com.homeloan.project.service;

import java.util.ArrayList;
import java.util.List;

import com.homeloan.project.model.LoanRepayment;

public interface LoanRepaymentService {
	public List<LoanRepayment> getScheduleByYearMonth(int yearmonth);
	public String addToSchedule(LoanRepayment loanRepayment);
	public  String createSchedule(String loanAccId, double principal, double yearly_roi, int tenure);
	public void exportRepaymentScheduleInCsv();
	public List<LoanRepayment> getByLoanAccId(String loanAccId);
	
}


