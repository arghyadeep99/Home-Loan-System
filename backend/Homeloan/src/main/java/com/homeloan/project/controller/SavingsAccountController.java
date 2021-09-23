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
import com.homeloan.project.service.SavingsAccountService;

@RestController
public class SavingsAccountController {

	@Autowired
	SavingsAccountService savingsAccountService;

	@GetMapping("/getSavingsAccount/{id}")
	public ResponseEntity<?> getSavingsAccountBySeqid(@PathVariable("id") String id) {
		Optional<SavingsAccount> optional = savingsAccountService.getSavingsAccountBySeqid(id);
		if (optional.isPresent()) {
			return ResponseEntity.status(200).body(optional.get());
		} else {
			return ResponseEntity.status(404).body("record not found");
		}
	}
	
	
	// No need
	@GetMapping("/getSavingsAccountByAccountNumber/{acc_no}")
	public ResponseEntity<?> getSavingsAccountByAccountNumber(@PathVariable("acc_no") String acc_no) {
		Optional<SavingsAccount> saveOptional = savingsAccountService.getSavingsAccountByAccountNumber(acc_no);

		if (saveOptional.isEmpty()) {
			return ResponseEntity.status(404).body("record not found : " + "No Savings Account found");
		}

		return ResponseEntity.status(200).body(saveOptional);
	}
	
	@GetMapping("/getSavingsAccounts")
	public ResponseEntity<?> getSavingsAccounts() {
		List<SavingsAccount> savingsAccounts = savingsAccountService.getSavingsAccounts();

		if (savingsAccounts.isEmpty()) {
			return ResponseEntity.status(404).body("record not found : " + "No Savings Account found");
		}

		return ResponseEntity.status(200).body(savingsAccounts);
	}
	
	@PostMapping("/updateCurrentAmount")
	public ResponseEntity<?> updateSavingsAccount(@RequestBody SavingsAccount savingsAccount) {
		SavingsAccount updatedObject = savingsAccountService.updateSavingsAccount(savingsAccount.getSeqId(),savingsAccount.getCurrentBalance());
		return ResponseEntity.status(200).body(updatedObject);
	}
	
	
}
