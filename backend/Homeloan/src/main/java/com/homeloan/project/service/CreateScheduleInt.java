package com.homeloan.project.service;

import java.util.List;
import java.util.Date;

import com.homeloan.project.model.LoanRepayment;

public interface CreateScheduleInt {
	public List<LoanRepayment> getScheduleByYearMonth(int yearmonth);
	public String addSchedule();
	public double calculateEMI(double principal ,double yearly_roi, int tenure_months);
	public double calculateMonthlyInterest(double outstanding , double yearly_roi);
	public double calculatePrincipal(double emi,double interest);
	public double calculateBalanceOutstanding(double outstanding,double principal_paid);
	public void viewRepayment();
	public void exportRepaymentScheduleInCsv();
	public double calculatePrepayment();
	public double calculateForclosure();
	
}
