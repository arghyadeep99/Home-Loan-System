package com.homeloan.project;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.service.LoanAccountService;
import com.homeloan.project.service.LoanRepaymentCalculations;
import com.homeloan.project.service.LoanRepaymentService;
import com.homeloan.project.service.LoanAccountImpl;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = SpringApplication
				.run(HomeloanApplication.class, args);
		
		LoanAccountService loanAccountService = applicationContext.getBean(LoanAccountService.class);
		
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
		
		//PayEMI
//		Optional<LoanRepayment> LR = loanRepaymentService.getByLoanAccIdAndYrMonth(userLoanAccId,userYrMonth);
//		System.out.println((LR.get()).getEmi());
//		
//		List<LoanRepayment> LR1 = loanRepaymentService.getByLoanAccIdAndStatus(userLoanAccId, PaymentStatus.PENDING);
//		
//		double emiToBePaid = LR1.get(LR1.size()-1).getEmi();
//		double emiPaidByCustomer = 71735.47; //hard-coded, please take this from user by Controller
//		
//		if (emiPaidByCustomer == emiToBePaid) {
//			String result = loanRepaymentService.PayEmi(userLoanAccId, userYrMonth, emiPaidByCustomer);
//			if (result!= null)
//				//implement updation of SavingsAccount ka money, by deducting EMI when this section of code is invoked
//				System.out.println(result +". Your EMI has been paid and the same has been reflected in your payment schedule and savings account.");
//			else
//				System.out.println("There's been some error in your EMI Payment, please try again.");
//		}
//		else
//			System.out.println("Please pay the exact amount mentioned for EMI.");
		
		
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
//		if(lastPaidYrMonth >= 3 && (userPaymentForForeclosure == outstandingForForeclosure)) {
//			String result = loanRepaymentService.Foreclosure(userLoanAccId, lastPaidYrMonth, foreclosurePaymentDate, tenure);
//			if (result!= null) {
//				System.out.println(result + ". Foreclosure has been made. Loan Account status changed  to closed and payment schedule updated.");
//			}
//			else
//				System.out.println("There's been some error in your Foreclosure Payment, please try again.");
//		}
//		else
//			System.out.println("Check if you've paid atleast 3 months of EMI. Please pay the exact amount mentioned for Foreclosure.");
		}
	}


//Get loan_Acc_id from frontend

