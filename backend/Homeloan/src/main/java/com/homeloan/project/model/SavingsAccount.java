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
	private String account_no;
	private String name;
	private String email;
	private double current_balance;
}