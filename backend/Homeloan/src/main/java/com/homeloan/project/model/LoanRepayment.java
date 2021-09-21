package com.homeloan.project.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="loanrepaymentschedule")
public class LoanRepayment {
	@Id
	private String loan_acc_id ;
	@Id
	private int year_month ;
	private String trans_date;
	//private String trans_type;
	private enum trans_type{EMI,PPM};
	private double emi;
	private double principal;
	private double interest_amount;
	private double outstanding_amount;
	private enum status{PENDING,PAID,CANCELLED};
}