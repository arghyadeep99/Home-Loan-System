package com.homeloan.project;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.service.LoanAccountService;
import com.homeloan.project.service.LoanAccountImpl;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(HomeloanApplication.class, args);
		
		LoanAccountService loanAccountService = applicationContext.getBean(LoanAccountService.class);
		
		
//		LoanAccount loanAccount = new LoanAccount("8765432109","1234567890",10000.00,7.0,20,"APPROVED");
//		String result = loanAccountService.addLoanAccount(loanAccount);	
	}

}
