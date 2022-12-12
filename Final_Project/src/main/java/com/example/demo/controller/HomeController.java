package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class HomeController {

	// http://127.0.0.1:8081
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/login")
    public String login() {
        return "login";
    }
	
	@PostMapping("/fail_login")
	public String handleFailedLogin(@RequestParam String username, ModelMap model) {
		if(repo.findbyUsername(username) == null) {
			model.addAttribute("errorMsg", "Username không tồn tại");
			return "/login";
		}else {
			model.addAttribute("errorMsg", "Username hoặc mật khẩu sai");
			return "/login";
		}

	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user, @RequestParam String cfpassword
			,@RequestParam String password, ModelMap model) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(password);
	
		if(repo.findbyEmail(user.getEmail()) != null) {
			model.addAttribute("errorMsg", "email bi trung");
			return "/signup";
		}
		else if(repo.findbyUsername(user.getUsername()) != null){
			model.addAttribute("errorMsg", "Username da bi trung");
			return "/signup";
		}
		else if(password.length() < 5) {
			model.addAttribute("errorMsg", "Password >5");
			return "/signup";
		}
		else if(!password.equals(cfpassword)) {
			model.addAttribute("errorMsg", "Confirm password wrong");
			return "/signup";
		}else if(repo.findbyPhone(user.getPhone()) != null) {
			model.addAttribute("errorMsg", "Phone has been used");
			return "/signup";
		}else {
			user.setPassword(encodePassword);
			repo.save(user);
			model.addAttribute("success", "Register success");
			user.setAddress("");
			user.setEmail("");
			user.setUsername("");
			user.setPhone("");
			user.setName("");
			user.setAddress("");
			user.setPassword("");
			cfpassword = "";
		}
		
		return "/signup";
	}

	@GetMapping("/changepass")
	public String changepass() {
		return "changepass";
	}

	@GetMapping("/cart")
	public String cart() {
		return "cart";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/logout")
	public String lougout() {
		return "redirect:/login";
	}
}
