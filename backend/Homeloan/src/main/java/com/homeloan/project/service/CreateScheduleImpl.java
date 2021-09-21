package com.homeloan.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeloan.project.model.LoanRepayment;

@Service
public class CreateScheduleImpl implements CreateScheduleInt {

	@Override
	public List<LoanRepayment> getScheduleByYearMonth(int yearmonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addSchedule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calculateEMI(double principal, double yearly_roi, int tenure_months) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateMonthlyInterest(double outstanding, double yearly_roi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculatePrincipal(double emi, double interest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateBalanceOutstanding(double outstanding, double principal_paid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void viewRepayment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportRepaymentScheduleInCsv() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double calculatePrepayment() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateForclosure() {
		// TODO Auto-generated method stub
		return 0;
	}

}
