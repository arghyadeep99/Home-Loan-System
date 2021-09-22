package com.homeloan.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import java.lang.Math;
import com.homeloan.project.model.LoanRepayment;

@Service
public class LoanRepaymentCalculationsImpl implements LoanRepaymentCalculations {


	@Override
	public double calculateEMI(double principal, double yearly_roi, int tenure_months) {
		
		double monthly_roi=(yearly_roi/12)/100;
		double monthly_roi_pn=Math.pow(monthly_roi+1, tenure_months);
		double emi = (principal*monthly_roi*monthly_roi_pn)/(monthly_roi_pn-1);
		return Math.round(emi);
	}

	@Override
	public double calculateMonthlyInterest(double outstanding, double yearly_roi) {
		double monthly_interest = outstanding*((yearly_roi/12)/100);
		return Math.round(monthly_interest);
	}

	@Override
	public double calculatePrincipal(double emi, double interest) {
		
		return emi-interest;
	}

	@Override
	public double calculateBalanceOutstanding(double outstanding, double principal_paid) {
		
		return outstanding-principal_paid;
	}



	@Override
	public double calculateEmiPostPrepayment(double principal, double prepayment,double yearly_roi,int tenure_months) {
		/*new principal for revised emi structure*/
		double new_principal = calculatePrincipalPostPPM(principal, prepayment);
		double newEmi=calculateEMI(new_principal,yearly_roi,tenure_months);
		return newEmi;
	}

	@Override
	public double calculateForeclosure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculatePrincipalPostPPM(double principal, double prepayment) {
		double new_principal = principal-prepayment;
		return new_principal;
	}

}
