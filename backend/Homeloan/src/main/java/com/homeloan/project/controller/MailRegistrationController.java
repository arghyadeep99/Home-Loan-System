package com.homeloan.project.controller;

import javax.mail.MessagingException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeloan.project.service.MailService;

@RestController
public class MailRegistrationController {

	@Autowired
	private MailService notificationService;
 

	@RequestMapping("send-mail")
	public String send() {
		
		

		
		try {
			notificationService.sendEmail(""); //specify the email where mail has to be sent
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
