package com.homeloan.project.service;

import java.util.List;

import com.homeloan.project.model.LoanRepayment;

public interface LoanRepaymentCalculations {
	
	public double calculateEMI(double principal, double yearly_roi, int tenure_months);
	public double calculateMonthlyInterest(double outstanding , double yearly_roi);
	public double calculatePrincipal(double emi,double interest);
	public double calculateBalanceOutstanding(double outstanding,double principal_paid);
	public double calculatePrincipalPostPPM(double principal, double prepayment);
	public double calculateEmiPostPrepayment(double principal, double prepayment,double yearly_roi,int tenure_months);
	
}
/* when we are using pre payment and foreclosure, we need to update the database after calculating the revised*/
/*emi , monthly interest, prinicpal and outstanding balance*/
/*lumpsum */