package com.homeloan.project.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.homeloan.project.service.LoanRepaymentCalculationsImpl;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HomeloanApplication.class, args);
		LoanRepaymentCalculationsImpl objj=new LoanRepaymentCalculationsImpl();
		double emi = objj.calculateEMI(5000000, 12, 120);
		double intr = objj.calculateMonthlyInterest(5000000, 12);
		double pri = objj.calculatePrincipal(emi, intr);
		double new_emi = objj.calculateEmiPostPrepayment(5000000, 215205, 12, 120);
		System.out.println(new_emi);
		System.out.println("Helellooo");
	}

}
