package com.homeloan.project.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.homeloan.project.model.UserLogin;
import com.homeloan.project.repository.UserLoginRepository;
import com.homeloan.project.service.UserLoginService;
 
@Controller
@EnableWebMvc
public class UserLoginController{
	@Autowired
	UserLoginRepository userLoginRepo;

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
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<UserLogin> listUsers = userLoginRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	/*
	@RequestMapping("/userLogin")
	public ModelAndView index () {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}
//	@ResponseBody
	
	
	
	@PostMapping("/userLogin/{id}/{password}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String userid,@PathVariable("password") String password) {
		
		Optional<UserLogin> optional = userLoginService.getUserid(userid);
		if(optional.isPresent()) {
			if(optional.get().getUserid().equals(userid) && optional.get().getPassword().equals(password)) {
				return  ResponseEntity.status(200).body("done for the day");
			}
			else {
				return ResponseEntity.status(200).body("wrong credentials");
			}
			
		}
		
		else
			return ResponseEntity.status(404).body("record not found");
		
	}*/
	
}