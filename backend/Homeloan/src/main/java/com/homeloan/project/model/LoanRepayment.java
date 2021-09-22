package com.homeloan.project.model;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(TransactionId.class)
@Table(name="loanrepaymentschedule")
public class LoanRepayment {
	@Id
	private String loanAccId ;
	@Id
	private int yr_month;
	private String trans_date;
	//private String trans_type;
	@Enumerated(EnumType.STRING)
	private TransactionType transType;
	private double emi;
	private double principal;
	private double interest_amount;
	private double outstanding_amount;
	@Enumerated(EnumType.STRING)
	@Id
	private PaymentStatus status;
}