package com.homeloan.project.controller;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.service.MailService;

@RestController
public class MailRegistrationController {

	@Autowired
	private MailService notificationService;


	@RequestMapping("send-mail")
	public String sendLoanApproval(String email,String name,LoanAccount loanAccount
			,Map<String,List<Double>> schedule) {
		
		try {
			notificationService.sendEmail(email,name,loanAccount,schedule); 
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been sent to the user.";
	}

	
	@RequestMapping("send-mail-attachment")
	public String sendWithAttachment() throws MessagingException {

		
		/*
		 * Here we will call sendEmailWithAttachment() for Sending mail to the sender
		 * that contains a attachment.
		 */
		try {
			notificationService.sendEmailWithAttachment(""); //specify the email where mail has to be sent
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}
	
	
}
