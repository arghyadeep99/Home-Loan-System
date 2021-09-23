package com.homeloan.project.model;
import java.time.LocalDate;

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
	private int yrMonth;
	private LocalDate paymentDate;
	@Enumerated(EnumType.STRING)
	@Id
	private TransactionType transactionType;
	private double emi;
	private double principal;
	private double interestAmount;
	private double outstandingAmount;
	@Enumerated(EnumType.STRING)
	private PaymentStatus status;
}