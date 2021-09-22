package com.homeloan.project;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.service.LoanRepaymentCalculationsImpl;
import com.homeloan.project.service.LoanRepaymentService;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HomeloanApplication.class, args);
		LoanRepaymentService loanRepaymentService = applicationContext.getBean(LoanRepaymentService.class);
		
//		LoanRepaymentCalculationsImpl objj=new LoanRepaymentCalculationsImpl();
//		double emi = objj.calculateEMI(5000000, 12, 120);
//		double intr = objj.calculateMonthlyInterest(5000000, 12);
//		double pri = objj.calculatePrincipal(emi, intr);
//		double new_emi = objj.calculateEmiPostPrepayment(5000000, 215205, 12, 120);
//		System.out.println(new_emi);
//		System.out.println("Hellooo");
		LoanRepayment LR = new LoanRepayment();
		LR.setStatus(PaymentStatus.PAID);//	
		PaymentStatus getstatus = LR.getStatus();
		System.out.println(getstatus);
//		LoanRepayment loanRepayment = new LoanRepayment();
		//Optional<LoanRepayment> result = loanRepaymentService.getLoanRepaymentByLoanAccId();
		//System.out.println(result.get());
	}

}
