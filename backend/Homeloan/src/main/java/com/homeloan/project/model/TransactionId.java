package com.homeloan.project.model;

import java.io.Serializable;

public class TransactionId implements Serializable{

	private String loanAccId ;
	
	private int yr_month;
	
	public TransactionId(String loanAccId, int yr_month) {
		this.loanAccId = loanAccId;
		this.yr_month = yr_month;
	}
}
