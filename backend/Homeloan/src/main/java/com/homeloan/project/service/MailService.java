package com.homeloan.project.service;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.homeloan.project.model.LoanAccount;


@Service
public class MailService {
	
	private String demoname;
	private int demoamount;

	/*
	 * The Spring Framework provides an easy abstraction for sending email by using
	 * the JavaMailSender interface, and Spring Boot provides auto-configuration for
	 * it as well as a starter module.
	 */
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
		this.demoname = " Ram ";
		this.demoamount = 500000;
	}

	/**
	 * 
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(String email,String name,LoanAccount loanAccount,
			Map<String,List<Double>> schedule) throws MailException {

		/*
		 * This JavaMailSender Interface is used to send Mail in Spring Boot. This
		 * JavaMailSender extends the MailSender Interface which contains send()
		 * function. SimpleMailMessage Object is required because send() function uses
		 * object of SimpleMailMessage as a Parameter
		 */
		
		
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		String schedule_p =  "";
		
		for(int i = 0; i < loanAccount.getTenure() * 12; i++) {
		schedule_p = schedule_p.concat("Month " + (i+1) + " ) Monthly Interest: " + String.valueOf(schedule.get("monthlyInterests").get(i)) + " Monthly Principal: " + String.valueOf(schedule.get("monthlyPrincipals").get(i)) + " Outstanding: " + String.valueOf(schedule.get("monthlyOutstandings").get(i)) + "\n\n");
	}
		
		
		mail.setTo(email);
		mail.setSubject("Loan Approved");
		mail.setText("Congratulations "+ name + " your loan Account ID is : (" + loanAccount.getLoan_acc_id() + ") has been approved for "
				+ " \n amount of  : " + loanAccount.getTotal_loan_amount() + " ₹" + 
				" \n Rate of Interest : " + loanAccount.getRoi() + 
				" \n Tenure Of : " + loanAccount.getTenure() + " \n\n SCHEDULE FOR EMI's \n"
				+schedule_p
				);

		/*
		 * This send() contains an Object of SimpleMailMessage as an Parameter
		 */
		javaMailSender.send(mail);
	}

	/**
	 * This function is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(String email) throws MailException, MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(email);
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");
		
		
		
		
		ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(message);
		
	}

}
