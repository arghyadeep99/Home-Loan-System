package com.homeloan.project.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HomeloanApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HomeloanApplication.class, args);
		System.out.println("Helellooo");
	}

}
