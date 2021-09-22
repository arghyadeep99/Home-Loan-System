package com.homeloan.project.service;

import java.util.List;

import com.homeloan.project.model.LoanRepayment;

public interface LoanRepaymentService {
	
	public List<LoanRepayment> getScheduleByYearMonth(int yearmonth);
	public String addSchedule();
	public void viewRepayment();
	public void exportRepaymentScheduleInCsv();
	
}
