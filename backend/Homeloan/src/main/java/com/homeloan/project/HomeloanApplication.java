package com.homeloan.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.TransactionType;
import com.homeloan.project.service.LoanRepaymentCalculationsImpl;
import com.homeloan.project.service.LoanRepaymentService;
import com.homeloan.project.service.LoanRepaymentServiceImpl;

import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;

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
//		LoanRepayment LR = new LoanRepayment();
//		LR.setStatus(PaymentStatus.PAID);//	
//		PaymentStatus getstatus = LR.getStatus();
//		System.out.println(getstatus);
//		LoanRepayment loanRepayment = new LoanRepayment();
		//Optional<LoanRepayment> result = loanRepaymentService.getLoanRepaymentByLoanAccId();
		//System.out.println(result.get());
		
//		LoanRepaymentServiceImpl LRSI = new LoanRepaymentServiceImpl();
//		String out = LRSI.createSchedule("123", 5000000, 12, 10);
//		 for(double outstanding : out) {
//			 System.out.printf("%.2f", outstanding);
//			 System.out.println();
//		 }
//		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		 Date date = new Date();
//		 String cur_date = formatter.format(date);
		Map<String,List<Double>> res =new HashMap();
		res = loanRepaymentService.createSchedule("1234", 5000000, 12, 10);
		//LoanRepayment LRP = new LoanRepayment("123",0,"2021-09-22",TransactionType.EMI,10000,4000000,5000,300000,PaymentStatus.PAID);
//		loanRepaymentService.addToSchedule(LRP);
		int scheduleLength = res.get("monthlyInterests").size();
//		System.out.println(scheduleLength);
		for(int i=0;i<scheduleLength;i++) {
			LoanRepayment LRP = new LoanRepayment("1234",i,"2021-09-22",TransactionType.EMI,71735,res.get("monthlyPrincipals").get(i),res.get("monthlyInterests").get(i),res.get("outstandings").get(i),PaymentStatus.PENDING);
			loanRepaymentService.addToSchedule(LRP);
		}
		
	}

}

//Get loan_Acc_id from frontend
//auto increment yermonth

//private String loanAccId ;
//@Id
//private int yr_month;
//private String trans_date;
////private String trans_type;
//@Enumerated(EnumType.STRING)
//private TransactionType transType;
//private double emi;
//private double principal;
//private double interest_amount;
//private double outstanding_amount;
//@Enumerated(EnumType.STRING)
//@Id
//private PaymentStatus status;