package com.homeloan.project.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionId implements Serializable{

	private String loanAccId;
	private int yr_month;
	private PaymentStatus status;
	
}