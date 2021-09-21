package com.homeloan.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.homeloan.project.service.UserLoginService;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {

		System.out.println("Heelloo");
		ApplicationContext applicationContext = SpringApplication.run(HomeloanApplication.class, args);
		applicationContext.getBean(UserLoginService.class);
		

	}

}
