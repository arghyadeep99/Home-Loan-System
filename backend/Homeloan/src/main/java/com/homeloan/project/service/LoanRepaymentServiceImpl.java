package com.homeloan.project.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
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
	public String addSchedule() {
		// TODO Auto-generated method stub
		return null;
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
	public List<LoanRepayment> findByLoanAccId(String loanAccId) {
		// TODO Auto-generated method stub
		return loanScheduleRepository.getByLoanAccId(loanAccId);
	}

}
