package com.homeloan.project.service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.LoanRepayment;
import com.homeloan.project.model.PaymentStatus;
import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.model.TransactionType;
import com.homeloan.project.repository.LoanAccountRepository;
import com.homeloan.project.repository.LoanScheduleRepository;
import com.homeloan.project.repository.SavingsAccountRepository;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService {
	@Autowired
	LoanScheduleRepository loanScheduleRepository;
	
	
	@Autowired
	LoanAccountService loanAccountService;
	
	@Autowired
	SavingsAccountService savingsAccountService;
	
	@Autowired
	LoanRepaymentService loanRepaymentService;

	@Override
	public Map<String,List<Double>> createSchedule(String loanAccId,
			double principal, double yearly_roi, int tenure) {
		int tenure_months = tenure*12;
		LoanRepaymentCalculationsImpl loanRepaymentCalculationsImpl = new LoanRepaymentCalculationsImpl();
		double emi = loanRepaymentCalculationsImpl.calculateEMI(principal, yearly_roi, tenure_months);
		double currentOutstanding = principal;
		double monthly_interest, monthly_principal;
		ArrayList<Double> monthlyOutstandings = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyInterests = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyPrincipals = new ArrayList<Double>(tenure_months);
		ArrayList<Double> monthlyEmi = new ArrayList<Double>(1);
		monthlyEmi.add(emi);
		//out.add(currentOutstanding);
		//System.out.println(emi);
		for(int i = 0; i < tenure_months; i++) {
			monthly_interest = loanRepaymentCalculationsImpl.calculateMonthlyInterest(currentOutstanding, yearly_roi);
			monthly_principal = loanRepaymentCalculationsImpl.calculatePrincipal(emi, monthly_interest);
			currentOutstanding = loanRepaymentCalculationsImpl.calculateBalanceOutstanding(currentOutstanding, monthly_principal);
			monthlyOutstandings.add(currentOutstanding);
			monthlyInterests.add(monthly_interest);
			monthlyPrincipals.add(monthly_principal);
		}

//		for(int i = 0; i < tenure_months; i++) {
//			System.out.printf("Monthly Interest: %.2f, Monthly Principal: %.2f, Outstanding: %.2f", monthlyInterests.get(i), monthlyPrincipals.get(i), outstandings.get(i));;
//			 System.out.println();
//			 
//		}
		Map<String,List<Double>> returnMap = new HashMap<String, List<Double>>();
		returnMap.put("monthlyInterests",monthlyInterests);
		returnMap.put("monthlyPrincipals", monthlyPrincipals);
		returnMap.put("monthlyOutstandings", monthlyOutstandings);
		returnMap.put("monthlyEmi", monthlyEmi);
		return returnMap;
	}

//	@Override
//	public void exportRepaymentScheduleInCsv() {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public List<LoanRepayment> getByLoanAccId(String loanAccId) {
		
		return loanScheduleRepository.getByLoanAccId(loanAccId);
	}

	@Override
	public String addToSchedule(LoanRepayment loanRepayment) {
		// TODO Auto-generated method stub
		LoanRepayment LR = loanScheduleRepository.save(loanRepayment);
		if (LR != null)
			return "success";
		else
			return null;
	}

	@Override
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonth(String loanAccId, int yrMonth){
		return loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId,yrMonth);
	}
	
	@Override
	public List<LoanRepayment> getByLoanAccIdAndStatus(String loanAccId, PaymentStatus status){
		
		return loanScheduleRepository.getByLoanAccIdAndStatus(loanAccId, status);
	}

	
	@Override
	public Optional<LoanRepayment> getByLoanAccIdAndYrMonthAndTransactionType(String loanAccId, int yrMonth,
			TransactionType transType) {
		
		return loanScheduleRepository.getByLoanAccIdAndYrMonthAndTransactionType(loanAccId, yrMonth, transType);
	}
	
	
	@Override
	public String PayEmi(String loanAccId, int yearMonth, double EmiPaidByCust) {
		
		
		Optional<LoanRepayment> LR = loanRepaymentService.getByLoanAccIdAndYrMonth(loanAccId,yearMonth);
		System.out.println((LR.get()).getEmi());
		
		List<LoanRepayment> LR1 = loanRepaymentService.getByLoanAccIdAndStatus(loanAccId, PaymentStatus.PENDING);
		
		double emiToBePaid = LR1.get(LR1.size()-1).getEmi();
		double emiPaidByCustomer = EmiPaidByCust; //hard-coded, please take this from user by Controller
		
		String response = "";
		
		if (emiPaidByCustomer == emiToBePaid) {
				//implement updation of SavingsAccount ka money, by deducting EMI when this section of code is invoked
				response  = ". Your EMI has been paid and the same has been reflected in your payment schedule and savings account.";
		}
			else {
				return "There's been some error in your EMI Payment, please try again.";
			}
		
		
		Optional<LoanRepayment> lr1 = loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId, yearMonth);
		Optional<LoanAccount> SeqId = loanAccountService.getLoanAccountByLoanAccountId(loanAccId);
		String seq_id = SeqId.get().getSeqid();
		if(yearMonth == 1)
		{
			loanAccountService.updateLoanStatus(loanAccId,"Ongoing");
		}
		if (lr1 != null) {
			LoanRepayment loanRepayment = lr1.get();
			loanRepayment.setStatus(PaymentStatus.PAID);
			loanScheduleRepository.save(loanRepayment);
			savingsAccountService.updateSavingsAccount(seq_id, EmiPaidByCust);
			return response;
		}
		return null;
	}

	@Override
	public String Foreclosure(String loanAccId) {
		
		List<LoanRepayment> LR2 = loanRepaymentService.getByLoanAccIdAndStatus(loanAccId, PaymentStatus.PAID);
		System.out.println(LR2);
		int lastPaidYrMonth = LR2.get(LR2.size()-1).getYrMonth();
		System.out.println(lastPaidYrMonth);
		// +1 in argument below to get outstanding for next month from where it's pending.
		Optional<LoanRepayment> LR3 = loanRepaymentService.getByLoanAccIdAndYrMonthAndTransactionType(loanAccId, lastPaidYrMonth + 1, TransactionType.EMI);
		double outstandingForForeclosure = (LR3.get()).getOutstandingAmount();
		LocalDate foreclosurePaymentDate = (LR3.get()).getPaymentDate();
		System.out.println("Outstanding payment for YrMonth" + (lastPaidYrMonth+1) + " is: " + outstandingForForeclosure);
		Optional<LoanAccount> Fortenure = loanAccountService.getLoanAccountByLoanAccountId(loanAccId);
		int tenure = Fortenure.get().getTenure();
		double userPaymentForForeclosure = outstandingForForeclosure;
		String result = " ";
		if(lastPaidYrMonth > 3 && (userPaymentForForeclosure == outstandingForForeclosure)) {
				result  =  ". Foreclosure has been made. Loan Account status changed  to closed and payment schedule updated.";
		}
		else
		{
			return "Check if you've paid atleast 3 months of EMI. Please pay the exact amount mentioned for Foreclosure.";
		}
		
		
		int tenure_months = tenure * 12;
		Optional<LoanRepayment> lr1 = loanRepaymentService.getByLoanAccIdAndYrMonth(loanAccId, lastPaidYrMonth + 1);
		if (lr1 != null) {
			loanScheduleRepository.deleteByLoanAccIdAndYrMonthAndTransactionType(loanAccId, lastPaidYrMonth + 1, TransactionType.EMI);
			LoanRepayment loanRepayment = lr1.get();
			
			loanRepayment.setStatus(PaymentStatus.PAID);
			loanRepayment.setTransactionType(TransactionType.FC);
			
			loanScheduleRepository.save(loanRepayment);
		
			loanAccountService.updateLoanStatus(loanAccId,"Closed");
			Optional<LoanAccount> SeqId = loanAccountService.getLoanAccountByLoanAccountId(loanAccId);
			String seq_id = SeqId.get().getSeqid();
			for(int i = lastPaidYrMonth+2; i <= tenure_months; i++) {
				Optional<LoanRepayment> lr2 = loanScheduleRepository.getByLoanAccIdAndYrMonth(loanAccId, i);
				LoanRepayment nextLoanRepayment = lr2.get();
				nextLoanRepayment.setEmi(0);
				nextLoanRepayment.setInterestAmount(0);
				nextLoanRepayment.setOutstandingAmount(0);
				nextLoanRepayment.setPrincipal(0);
				nextLoanRepayment.setPaymentDate(foreclosurePaymentDate);
				nextLoanRepayment.setStatus(PaymentStatus.CANCELLED);
				loanScheduleRepository.save(nextLoanRepayment);
				savingsAccountService.updateSavingsAccount(seq_id, userPaymentForForeclosure);
			}
			return "Success";
		}
		return null;
	}

	@Override
	public String PrePayment(String loanAccId, double prePaymentAmount) {
		List<LoanRepayment> LR4 = loanRepaymentService.getByLoanAccIdAndStatus(loanAccId, PaymentStatus.PAID);
		System.out.println(LR4);
		int lastPaidYrMonth = LR4.get(LR4.size()-1).getYrMonth();
		System.out.println(lastPaidYrMonth);
		// +1 in argument below to get outstanding for next month from where it's pending.
		Optional<LoanRepayment> LR5 = loanRepaymentService.getByLoanAccIdAndYrMonthAndTransactionType(loanAccId, lastPaidYrMonth + 1, TransactionType.EMI);
		double currentOutstanding = (LR5.get()).getOutstandingAmount();
		LocalDate foreclosurePaymentDate = (LR5.get()).getPaymentDate();
		double currentMonthEmi = (LR5.get()).getEmi();
		
		double userPrepayment = 4*currentMonthEmi; //hard-coded value from YrMonth4, please take this from user by Controller
		if(userPrepayment >= 3*currentMonthEmi && (currentOutstanding - userPrepayment > 0)) {
			double newPrincipal = currentOutstanding - userPrepayment;
			Map<String,List<Double>> res = new HashMap<String, List<Double>>();
			Optional<LoanAccount> SeqId = loanAccountService.getLoanAccountByLoanAccountId(loanAccId);
			
			System.out.println(loanAccId + " " + newPrincipal + " " + SeqId.get().getRoi() + " " + SeqId.get().getTenure());
			
			res = loanRepaymentService.createSchedule(loanAccId, newPrincipal, SeqId.get().getRoi(), SeqId.get().getTenure());
			int scheduleLength = res.get("monthlyInterests").size();
			double newEmi = (res.get("monthlyEmi")).get(0);
			newEmi = Double.parseDouble(new DecimalFormat("#.##").format(newEmi));
//			System.out.println(scheduleLength);
			for(int i = 0; i<scheduleLength; i++) {
				double monthlyInterest = Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyInterests").get(i)));
				double monthlyPrincipal = Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyPrincipals").get(i)));
				double monthlyOutstanding =Double.parseDouble(new DecimalFormat("#.##").format(res.get("monthlyOutstandings").get(i)));
				Date date = Date.valueOf("2021-09-23");
				LocalDate paymentDate = date.toLocalDate();
				if (i+1 == lastPaidYrMonth) {
					LoanRepayment LRP = new LoanRepayment(loanAccId, i+1 , paymentDate, TransactionType.PPM, 0, userPrepayment, 0, newPrincipal, PaymentStatus.PAID);
					//paymentDate = paymentDate.plusMonths(1);
					loanRepaymentService.addToSchedule(LRP);
					continue;
				}
				else if (i+1 > lastPaidYrMonth) {
					LoanRepayment LRP = new LoanRepayment(loanAccId, i+1 , paymentDate, TransactionType.EMI, newEmi, monthlyPrincipal, monthlyInterest, monthlyOutstanding, PaymentStatus.PENDING);
					paymentDate = paymentDate.plusMonths(1);
					loanRepaymentService.addToSchedule(LRP);
				}
				else
					paymentDate = paymentDate.plusMonths(1);
				}
			System.out.println("Prepayment has been made, new schedule generated.");
			}
		else
			System.out.println("Error in making prepayment, please try again.");
		return null;
	}
	
	


	
	
//	@Override
//	public String Prepayment(String loanAccId, int yearMonth, double EmiPaidByCust) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
