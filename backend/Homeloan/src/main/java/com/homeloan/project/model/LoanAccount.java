package com.homeloan.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

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
	private double roi=7.00; 
	private int tenure;
	private String status = "Approved"; 
}