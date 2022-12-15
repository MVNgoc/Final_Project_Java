package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.Utility;
import com.example.demo.model.Contact;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Product;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;

import net.bytebuddy.utility.RandomString;

@Controller
public class HomeController {

	// http://127.0.0.1:8081
	@Autowired
	private UserRepository repo;
	@Autowired
	private ProductRepository repoProduct;
	
	@GetMapping("/login")
    public String login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
        return "redirect:/home";
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "signup";
		}
		
		return "redirect:/home";
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
			user.setRole("USER");
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "redirect:/login";
		}
		
		return "/changepass";
	}
	
	@PostMapping("/changepass")
	public String changepassnew(@RequestParam String newpasscf, @RequestParam String newpass
			,@RequestParam String password, @AuthenticationPrincipal CustomUserDetails loggedUser,Model model)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(password);
		boolean isMatch = encoder.matches(password, loggedUser.getPassword());
		
		if(isMatch == false) {
			model.addAttribute("errorMsg", "Mật khẩu cũ không trùng");
			return "/changepass";
		}else if(newpass.length() < 5) {
			model.addAttribute("errorMsg", "Mật khẩu mới phải lớn hơn 5 kí tự");
			return "/changepass";
		}else if(!newpass.equals(newpasscf)) {
			model.addAttribute("errorMsg", "Mật khẩu xác nhận không trùng khớp");
			return "/changepass";
		}else if(newpass.equals(password)) {
			model.addAttribute("errorMsg", "Mật khẩu mới không được trùng với mật khẩu cũ");
			return "/changepass";
		}else {
			String encodenewPass = encoder.encode(newpass);
			loggedUser.setPassword(encodenewPass);
			User user = repo.getUserByUsername(loggedUser.getUsername());
			user.setPassword(encodenewPass);
			repo.save(user);
			model.addAttribute("success", "Đổi mật khẩu thành công");
		}
		
		return "/changepass";
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
	public String menu(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "/login";
		}
		
		List<Product> listProduct = repoProduct.findAll();
		List<Product> listProductNuong = new ArrayList<Product>();
		List<Product> listProductLau = new ArrayList<Product>();
		List<Product> listProductChay = new ArrayList<Product>();
		List<Product> listProductFastfood = new ArrayList<Product>();
		model.addAttribute("listProducts",listProduct);
		for (int i = 0 ; i < listProduct.size() ; i++) {
			if (listProduct.get(i).getCategory_name().equals("Nướng")) {
				listProductNuong.add(listProduct.get(i));
			}else if (listProduct.get(i).getCategory_name().equals("Lẩu")) {
				listProductLau.add(listProduct.get(i));
			}else if (listProduct.get(i).getCategory_name().equals("Chay")) {
				listProductChay.add(listProduct.get(i));
			}else if (listProduct.get(i).getCategory_name().equals("Fastfood")) {
				listProductFastfood.add(listProduct.get(i));
			}
		}
		model.addAttribute("listProductsNuong",listProductNuong);
		model.addAttribute("listProductsLau",listProductLau);
		model.addAttribute("listProductsChay",listProductChay);
		model.addAttribute("listProductsFastfood",listProductFastfood);
		return "menu";
	}
	
	@GetMapping("/logout")
	public String lougout() {
		return "redirect:/login";
	}
	
	//Xử lý quên password
	
	@GetMapping("/fogotpass")
	public String fogotpass() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "fogotpass";
		}
		
		return "redirect:/home";
	}
	
	@GetMapping("/fail")
	public String fail() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "/fail";
		}
		
		return "redirect:/home";
	}
	
	@GetMapping("/reset_password")
	public String changepasspw(@Param(value = "token") String token,Model model) {
		
		User user = repo.findByResetPasswordToken(token);
		if(user == null) {
			model.addAttribute("message", "Token không hợp lệ");
			return "Fail";
		}
		model.addAttribute("token", token);
		
		return "changepasspw";
	}
	
	@PostMapping("/reset_password")
	public String resetpass(@RequestParam String newpasscf, @RequestParam String newpass
			,Model model, @RequestParam String token) {
		User user = repo.findByResetPasswordToken(token);
		
		if(user == null) {
			model.addAttribute("message", "Token không hợp lệ");
			return "Fail";
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodePassword = encoder.encode(newpass);
			
			user.setPassword(encodePassword);
			user.setResetPasswordToken(null);
			repo.save(user);
			model.addAttribute("success", "Đổi mật khẩu thành công");
		}

		return "changepasspw";
	}
	
	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping("/fogotpass")
	public String fogot(@RequestParam String email,Model model, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		if(repo.findbyEmail(email) == null) {
			model.addAttribute("errorMsg", "Email này không tồn tại");
			return "/fogotpass";
		}else {
			String token = RandomString.make(45);
			User user = repo.findbyEmail(email);
			try {
				user.setResetPasswordToken(token);
				repo.save(user);
				//generate reset password link
				String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
				
				//send email to user
				sendEmail(email, resetPasswordLink);
				model.addAttribute("success", "Đã gửi mail cho bạn, vui lòng kiểm tra mail");
			}catch(UnsupportedEncodingException | MessagingException e){
				model.addAttribute("errorMsg", "Lỗi trong việc gửi email");
			}
			
			
		}
		
		return "fogotpass";
	}

	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("contact@3ae.com", "3ae Support");
		helper.setTo(email);
		
		String subject = "Đây là link để reset password của bạn";
		String content = "<p>Xin chào</p>" + 
				"<p>Bạn có yêu cầu để tạo mới mật khẩu.</p>" + 
				"<p>Nhấn vào link dưới đây để tạo mới mật khẩu: </p>" +
				"<p><b><a href=\"" + resetPasswordLink + "\"> Thay đổi mật khẩu </a><b></p>"
				+ "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình hoặc bạn không tạo yêu cầu đổi mật khẩu</p>";
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
	}
	
	//post contact
	
	@Autowired
	ContactRepository contactRepo;
	
	@PostMapping("/home")
	public String contact(Contact contact,HttpServletRequest request,Model model) {	
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate localDate = LocalDate.now();
		
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.now();

		contact.setUsername(request.getParameter("username"));
		contact.setUseremail(request.getParameter("email"));
		contact.setSubject(request.getParameter("subject"));
		contact.setContributions(request.getParameter("content"));
		contact.setDaycreate(date.format(localDate));
		contact.setTimecreate(time.format(localTime));
		
		contactRepo.save(contact);
		
		model.addAttribute("success", "Gửi phản hồi thành công");
		
		return "/home";
		
	}
	
	//post reservation
	@Autowired
	ReservationRepository reserRepo;
	
	@PostMapping("/home_process")
	public String Reser(Reservation reser,HttpServletRequest request,Model model) {
		
		DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate localDate = LocalDate.now();
		
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.now();
		
		reser.setUsername(request.getParameter("username"));
		reser.setUseremail(request.getParameter("email"));
		reser.setPeople(request.getParameter("people"));
		reser.setDayreservation(request.getParameter("datereser"));
		reser.setPhone_number(request.getParameter("phone"));
		reser.setTimereservation(request.getParameter("time"));
		reser.setContributions(request.getParameter("content"));
		reser.setDaycreate(date.format(localDate));
		reser.setTimecreate(time.format(localTime));
		
		reserRepo.save(reser);
		
		model.addAttribute("success", "Gửi form đặt bàn thành công");
		
		return "/home";
		
	}
}