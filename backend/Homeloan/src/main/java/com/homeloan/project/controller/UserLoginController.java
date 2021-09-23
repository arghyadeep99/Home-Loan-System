package com.homeloan.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.homeloan.project.model.LoanAccount;
import com.homeloan.project.model.SavingsAccount;
import com.homeloan.project.model.UserLogin;
import com.homeloan.project.repository.UserLoginRepository;
import com.homeloan.project.service.SavingsAccountService;
 
@Controller
@EnableWebMvc
public class UserLoginController{
	@Autowired
	UserLoginRepository userLoginRepo;
	
	@Autowired
	SavingsAccountService savingsAccountService;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserLogin());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegister(UserLogin user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		userLoginRepo.save(user);
		
		return "register_success";
	}
	
//	@GetMapping("/users")
//	public String listUsers(Model model) {
//		List<UserLogin> listUsers = userLoginRepo.findAll();
//		model.addAttribute("listUsers", listUsers);
//		return "users";
//	}
	
	@GetMapping("/users")
	public String accountDetails(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserLogin user = userLoginRepo.findByUserId(auth.getName());
		SavingsAccount account = savingsAccountService.getSavingsAccountBySeqid(user.getSeq_id()).get();
		model.addAttribute("account", account);
		return "users";
	}
	
	@GetMapping("/loanApplication")
	public String loanApplication(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserLogin user = userLoginRepo.findByUserId(auth.getName());
		SavingsAccount account = savingsAccountService.getSavingsAccountBySeqid(user.getSeq_id()).get();
		model.addAttribute("account", account);
		Map<String, String> loanObject = new HashMap<>();
		loanObject.put("seqId", user.getSeq_id());
		loanObject.put("amount", "");
		loanObject.put("tenure", "");
		model.addAttribute("seqId", user.getSeq_id());
		model.addAttribute("amount", "");
		model.addAttribute("tenure", "");
		model.addAttribute("loanObject",loanObject);
		LoanAccount loan_acc = new LoanAccount();
		loan_acc.setSeqid(user.getSeq_id());
		model.addAttribute("loanAccObject", loan_acc);
//		System.out.println("************************"+model..toString());
		return "loan_application";
	}
	
	@PostMapping("/loan_process")
	public String loanProcess(LoanAccount loanAccObject)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserLogin user = userLoginRepo.findByUserId(auth.getName());
		loanAccObject.setSeqid(user.getSeq_id());
		System.out.println("*************************************"+loanAccObject.toString());
		return "loan_applied";
	}
	
//	@PostMapping("/loan_applied")
//	public String loanApplied(LoanAccount loanAccObject)
//	{
//		System.out.println("*************************************"+loanAccObject.toString());
//		return "users";
//	}


}