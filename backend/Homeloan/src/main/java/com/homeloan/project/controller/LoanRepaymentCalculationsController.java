package com.homeloan.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.repository.LoanScheduleRepository;
import com.homeloan.project.service.LoanRepaymentCalculations;

@RestController
public class LoanRepaymentCalculationsController {

	@Autowired
	LoanRepaymentCalculations lonLoanRepaymentCalculations;

	@GetMapping("/calculateEMI/{principle}/{year_roi}/{tenure_month}")
	public ResponseEntity<?> calculateEMI(@PathVariable("principle") double principle,
			@PathVariable("year_roi") double year_roi, @PathVariable("tenure_month") int tenure_month) {
		double calculatedEmi = lonLoanRepaymentCalculations.calculateEMI(principle, year_roi, tenure_month);
		return ResponseEntity.status(200).body(calculatedEmi);
	}

	@GetMapping("/calculateMonthlyInterest/{outstanding}/{yearly_roi}")
	public ResponseEntity<?> calculateMonthlyInterest(@PathVariable("outstanding") double outstanding,
			@PathVariable("yearly_roi") double yearly_roi) {
		double calculatedMonthlyInterest = lonLoanRepaymentCalculations.calculateMonthlyInterest(outstanding,
				yearly_roi);
		return ResponseEntity.status(200).body(calculatedMonthlyInterest);
	}

	@GetMapping("/calculatePrincipal/{emi}/{interest}/{tenure_month}")
	public ResponseEntity<?> calculatePrincipal(@PathVariable("emi") double emi,
			@PathVariable("interest") double interest) {
		double calculatedPrincipal = lonLoanRepaymentCalculations.calculatePrincipal(emi, interest);
		return ResponseEntity.status(200).body(calculatedPrincipal);
	}

	@GetMapping("/calculatePrincipalPostPPM/{principal}/{prepayment}")
	public ResponseEntity<?> calculatePrincipalPostPPM(@PathVariable("principal") double principal,
			@PathVariable("prepayment") double prepayment) {
		double calculatedPrincipalPostPPM = lonLoanRepaymentCalculations.calculatePrincipalPostPPM(principal,
				prepayment);
		return ResponseEntity.status(200).body(calculatedPrincipalPostPPM);
	}

	@GetMapping("/calculateEmiPostPrepayment/{principal}/{prepayment}/{yearly_roi}/{tenure_months}")
	public ResponseEntity<?> calculateEmiPostPrepayment(@PathVariable("principal") double principal,
			@PathVariable("prepayment") double prepayment, @PathVariable("yearly_roi") double yearly_roi,
			@PathVariable("tenure_months") int tenure_months) {

		double calculatedEmiPostPrepayment = lonLoanRepaymentCalculations.calculateEmiPostPrepayment(principal,
				prepayment, yearly_roi, tenure_months);
		return ResponseEntity.status(200).body(calculatedEmiPostPrepayment);
	}

	@GetMapping("/calculateBalanceOutstanding/{outstanding}/{principalPaid}")
	public ResponseEntity<?> calculateBalanceOutstanding(@PathVariable("outstanding") double outstanding,
			@PathVariable("principalPaid") double principalPaid) {
		double cbo = lonLoanRepaymentCalculations.calculateBalanceOutstanding(outstanding, principalPaid);
		return ResponseEntity.status(201).body(cbo);

	}
}
