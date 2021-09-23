package com.homeloan.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.service.LoanAccountService;

@RestController
public class LoanAccountController {
	
	@Autowired
	LoanAccountService loanAccountService;

	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	
	@GetMapping("/getLoanAccount/{id}")
	public ResponseEntity<?> getLoanAccountByLoanAccountId(@PathVariable("id") String id) {
		Optional<LoanAccount> optional = loanAccountService.getLoanAccountByLoanAccountId(id);
		if (optional.isPresent()) {
			return ResponseEntity.status(200).body(optional.get());
		} else {
			return ResponseEntity.status(404).body("record not found");
		}
	}

	
	@PostMapping("/addLoanAccount")
	public ResponseEntity<?> addLoanAccount(@RequestBody LoanAccount loanAccount) {
		String res  = loanAccountService.addLoanAccount(loanAccount);
		if(res == "success")
		{
			return ResponseEntity.status(201).body(loanAccount);
		}
		return ResponseEntity.status(200).body("Sorry your requested home loan amount is larger than 50 times of your monthly salary ");
	}
	
	@GetMapping("/getLoanAccounts")
	public ResponseEntity<?> getLoanAccounts() {
		List<LoanAccount> loanAccounts = loanAccountService.getLoanAccounts();

		if (loanAccounts.isEmpty()) {
			return ResponseEntity.status(404).body("record not found : " + "No Loan Account found");
		}

		return ResponseEntity.status(200).body(loanAccounts);
	}
	
	@GetMapping("/deleteLoanAccount/{id}")
	public ResponseEntity<?> deleteLoanAccount(@PathVariable("id") String id) {
		loanAccountService.deleteLoanAccount(id);
		return ResponseEntity.status(200).body("Loan Account Deleted successfully");
	}
	
	
	@GetMapping("/getLoanAccountSeqId/{seqid}")
	public ResponseEntity<?> getBySeqid(@PathVariable("seqid") String seqid) {
		List<LoanAccount> loanAccounts = loanAccountService.getBySeqid(seqid);
		System.out.println(loanAccounts.size());
		if (loanAccounts.isEmpty()) {
			return ResponseEntity.status(404).body("record not found : " 
													+ "No Loan Account found");
		}
		
		return ResponseEntity.status(200).body(loanAccounts);
	}
	
	@PostMapping("/updateLoanStatus")
	public ResponseEntity<?> updateLoanStatus(@RequestBody LoanAccount loanAccount) {
		LoanAccount updatedObjectLoan = loanAccountService.updateLoanStatus(loanAccount.getLoan_acc_id(), loanAccount.getStatus());
		return ResponseEntity.status(200).body(updatedObjectLoan);
	}
	
	
	
	
}
