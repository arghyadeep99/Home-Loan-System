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
@Table(name="savingsaccount")
public class SavingsAccount {
	@Id
	private String seqId;
	private String accountNumber;
	private String name;
	private String email;
	private double currentBalance; 
	private double salarayAmount;
}