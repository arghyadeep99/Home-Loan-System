package com.homeloan.project;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.TransactionType;
import com.homeloan.project.service.LoanRepaymentCalculations;
import com.homeloan.project.service.LoanRepaymentService;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HomeloanApplication.class, args);
		LoanRepaymentService loanRepaymentService = applicationContext.getBean(LoanRepaymentService.class);
		LoanRepaymentCalculations loanRepaymentCalculations = applicationContext.getBean(LoanRepaymentCalculations.class);

		String userLoanAccId = "1234";
		double principal = 5000000.0;
		double yearly_roi = 12.0;
		int tenure = 10;
		int userYrMonth = 4;
		Date date = Date.valueOf("2021-09-22");
		LocalDate paymentDate = date.toLocalDate();
	
		double emi = loanRepaymentCalculations.calculateEMI(principal, yearly_roi, tenure*12);
		emi = Double.parseDouble(new DecimalFormat("#.##").format(emi));
//		
//		Map<String,List<Double>> res = new HashMap<String, List<Double>>();
//		res = loanRepaymentService.createSchedule(userLoanAccId, principal, yearly_roi, tenure);
//		int scheduleLength = res.get("monthlyInterests").size();
////		System.out.println(scheduleLength);
//		for(int i = 0; i<scheduleLength; i++) {
//			double monthlyInterest = Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyInterests").get(i)));
//			double monthlyPrincipal = Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyPrincipals").get(i)));
//			double monthlyOutstanding =Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyOutstandings").get(i)));
//			LoanRepayment LRP = new LoanRepayment(userLoanAccId, i+1 , paymentDate, TransactionType.EMI, emi, monthlyPrincipal, monthlyInterest, monthlyOutstanding, PaymentStatus.PENDING);
//			paymentDate = paymentDate.plusMonths(1);
//			loanRepaymentService.addToSchedule(LRP);
//		}
//		
//		//PayEMI
//		for(int j = 1; j <= userYrMonth; j++) {
//		Optional<LoanRepayment> LR = loanRepaymentService.getByLoanAccIdAndYrMonth(userLoanAccId, j);
//		System.out.println((LR.get()).getEmi());
//		
//		List<LoanRepayment> LR1 = loanRepaymentService.getByLoanAccIdAndStatus(userLoanAccId, PaymentStatus.PENDING);
//		
//		double emiToBePaid = LR1.get(LR1.size()-1).getEmi();
//		double emiPaidByCustomer = 71735.47; //hard-coded, please take this from user by Controller
//		
//		if (emiPaidByCustomer == emiToBePaid) {
//			String result = loanRepaymentService.PayEmi(userLoanAccId, j, emiPaidByCustomer);
//			if (result!= null)
//				//implement updation of SavingsAccount ka money, by deducting EMI when this section of code is invoked
//				System.out.println(result +". Your EMI has been paid and the same has been reflected in your payment schedule and savings account.");
//			else
//				System.out.println("There's been some error in your EMI Payment, please try again.");
//		}
//		else
//			System.out.println("Please pay the exact amount mentioned for EMI.");
//		}
		
		//Foreclosure
//		List<LoanRepayment> LR2 = loanRepaymentService.getByLoanAccIdAndStatus(userLoanAccId, PaymentStatus.PAID);
//		System.out.println(LR2);
//		int lastPaidYrMonth = LR2.get(LR2.size()-1).getYrMonth();
//		System.out.println(lastPaidYrMonth);
//		// +1 in argument below to get outstanding for next month from where it's pending.
//		Optional<LoanRepayment> LR3 = loanRepaymentService.getByLoanAccIdAndYrMonthAndTransactionType(userLoanAccId, lastPaidYrMonth + 1, TransactionType.EMI);
//		double outstandingForForeclosure = (LR3.get()).getOutstandingAmount();
//		LocalDate foreclosurePaymentDate = (LR3.get()).getPaymentDate();
//		System.out.println("Outstanding payment for YrMonth" + (lastPaidYrMonth+1) + " is: " + outstandingForForeclosure);
//		
//		double userPaymentForForeclosure = 4889127.24; //hard-coded value from YrMonth4, please take this from user by Controller
//		if(lastPaidYrMonth > 3 && (userPaymentForForeclosure == outstandingForForeclosure)) {
//			String result = loanRepaymentService.Foreclosure(userLoanAccId, lastPaidYrMonth, foreclosurePaymentDate, tenure);
//			if (result!= null) {
//				System.out.println(result + ". Foreclosure has been made. Loan Account status changed  to closed and payment schedule updated.");
//			}
//			else
//				System.out.println("There's been some error in your Foreclosure Payment, please try again.");
//		}
//		else
//			System.out.println("Check if you've paid atleast 3 months of EMI. Please pay the exact amount mentioned for Foreclosure.");
		
		
		//Prepayment
//		List<LoanRepayment> LR4 = loanRepaymentService.getByLoanAccIdAndStatus(userLoanAccId, PaymentStatus.PAID);
//		System.out.println(LR4);
//		int lastPaidYrMonth = LR4.get(LR4.size()-1).getYrMonth();
//		System.out.println(lastPaidYrMonth);
//		// +1 in argument below to get outstanding for next month from where it's pending.
//		Optional<LoanRepayment> LR5 = loanRepaymentService.getByLoanAccIdAndYrMonthAndTransactionType(userLoanAccId, lastPaidYrMonth + 1, TransactionType.EMI);
//		double currentOutstanding = (LR5.get()).getOutstandingAmount();
//		LocalDate foreclosurePaymentDate = (LR5.get()).getPaymentDate();
//		double currentMonthEmi = (LR5.get()).getEmi();
//		
//		double userPrepayment = 4*currentMonthEmi; //hard-coded value from YrMonth4, please take this from user by Controller
//		if(userPrepayment >= 3*currentMonthEmi && (currentOutstanding - userPrepayment > 0)) {
//			double newPrincipal = currentOutstanding - userPrepayment;
//			
//			Map<String,List<Double>> res = new HashMap<String, List<Double>>();
//			res = loanRepaymentService.createSchedule(userLoanAccId, newPrincipal, yearly_roi, tenure);
//			int scheduleLength = res.get("monthlyInterests").size();
//			double newEmi = (res.get("monthlyEmi")).get(0);
//			newEmi = Double.parseDouble(new DecimalFormat("#.##").format(newEmi));
////			System.out.println(scheduleLength);
//			for(int i = 0; i<scheduleLength; i++) {
//				double monthlyInterest = Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyInterests").get(i)));
//				double monthlyPrincipal = Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyPrincipals").get(i)));
//				double monthlyOutstanding =Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyOutstandings").get(i)));
//				if (i+1 == lastPaidYrMonth) {
//					LoanRepayment LRP = new LoanRepayment(userLoanAccId, i+1 , paymentDate, TransactionType.PPM, 0, userPrepayment, 0, newPrincipal, PaymentStatus.PAID);
//					//paymentDate = paymentDate.plusMonths(1);
//					loanRepaymentService.addToSchedule(LRP);
//					continue;
//				}
//				else if (i+1 > lastPaidYrMonth) {
//					LoanRepayment LRP = new LoanRepayment(userLoanAccId, i+1 , paymentDate, TransactionType.EMI, newEmi, monthlyPrincipal, monthlyInterest, monthlyOutstanding, PaymentStatus.PENDING);
//					paymentDate = paymentDate.plusMonths(1);
//					loanRepaymentService.addToSchedule(LRP);
//				}
//				else
//					paymentDate = paymentDate.plusMonths(1);
//				}
//			System.out.println("Prepayment has been made, new schedule generated.");
//			}
//		else
//			System.out.println("Error in making prepayment, please try again.");
		}
	}


//Get loan_Acc_id from frontend


