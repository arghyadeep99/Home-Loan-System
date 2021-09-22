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
@Table(name="loanaccount")
public class LoanAccount {
	@Id
	private String loan_acc_id;
	private String seqid;
	private double total_loan_amount;
	private double roi;
	private int tenure;
	private String status;
}