package com.homeloan.project.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TransactionId implements Serializable{

	private String loanAccId;
	private int yrMonth;
	private TransactionType transactionType;
	
}
