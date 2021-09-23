package com.homeloan.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.repository.LoanScheduleRepository;
import com.homeloan.project.service.LoanRepaymentService;

@RestController
public class LoanRepaymentServiceController {

	@Autowired
	LoanRepaymentService loanRepaymentService;

	@PostMapping("/addToSchedule")
	public ResponseEntity<?> addToSchedule(@RequestBody LoanRepayment loanRepayment) {
		String res = loanRepaymentService.addToSchedule(loanRepayment);
		if (res == "success") {
			return ResponseEntity.status(201).body(loanRepayment);
		}
		return ResponseEntity.status(200).body("Sorry Schedule is not created ");
	}

	@GetMapping("/createSchedule/{loanAccId}/{principal}/{yearly_roi}/{tenure}")
	public ResponseEntity<?> createSchedule(@PathVariable("loanAccId") String loanAccId,
			@PathVariable("principal") double principal, @PathVariable("yearly_roi") double yearly_roi,
			@PathVariable("tenure") int tenure) {
		Map<String, List<Double>> Schedule = loanRepaymentService.createSchedule(loanAccId, principal, yearly_roi,
				tenure);

		if (Schedule.isEmpty()) {
			return ResponseEntity.status(404).body("record not found : " + "No Schedule");
		}
		return ResponseEntity.status(200).body(Schedule);
	}

	@GetMapping("/getByLoanAccId/{loanAccId}")
	public ResponseEntity<?> getByLoanAccId(@PathVariable("loanAccId") String loanAccId) {
		List<LoanRepayment> loanRepaymentRecords = loanRepaymentService.getByLoanAccId(loanAccId);
		if (loanRepaymentRecords.isEmpty()) {
			return ResponseEntity.status(404).body("No record found");
		}
		return ResponseEntity.status(200).body(loanRepaymentRecords);
	}

	@GetMapping("/getByLoanAccIdAndYrMonth/{loanAccId}/{yrMonth}")
	public ResponseEntity<?> getByLoanAccIdAndYrMonth(@PathVariable("loanAccId") String loanAccId,
			@PathVariable("yrMonth") int yrMonth) {
		Optional<LoanRepayment> LY = loanRepaymentService.getByLoanAccIdAndYrMonth(loanAccId, yrMonth);
		if (LY.isPresent()) {
			return ResponseEntity.status(200).body(LY.get());
		}
		return ResponseEntity.status(404).body("No record found");
	}
	
	@GetMapping("/payEmi/{loanAccId}/{yearMonth}/{EmiPaidByCust}")
	public ResponseEntity<?> PayEmi(@PathVariable("loanAccId") String loanAccId, 
	                                    @PathVariable("yearMonth") int yearMonth, 
	                                    @PathVariable("EmiPaidByCust") double EmiPaidByCust) {
	    String lrs = loanRepaymentService.PayEmi(loanAccId, yearMonth, EmiPaidByCust);
	        return ResponseEntity.status(201).body(lrs);
	    }
	
	@GetMapping("/foreclosure/{loanAccId}")
	public ResponseEntity<?> Foreclosure(@PathVariable("loanAccId") String loanAccId) {
	    String fc = loanRepaymentService.Foreclosure(loanAccId);
	        return ResponseEntity.status(200).body(fc);
	    }
	
	@GetMapping("/prepayment/{loanAccId}/{prePaymentAmount}")
	public ResponseEntity<?> prepayment(@PathVariable("loanAccId") String loanAccId,@PathVariable("prePaymentAmount") double prePaymentAmount) {
	    String fc = loanRepaymentService.PrePayment(loanAccId, prePaymentAmount);
	        return ResponseEntity.status(200).body(fc);
	    }
}
