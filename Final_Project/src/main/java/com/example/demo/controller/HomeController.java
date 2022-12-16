package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.Utility;
import com.example.demo.model.Cart;
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
import utils.Utils;

@Controller
@ControllerAdvice
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
	
	@GetMapping("/403")
    public String error() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
        return "dad";
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
			,@RequestParam String password, @AuthenticationPrincipal CustomUserDetails loggedUser,Model model , HttpSession session)
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
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
		
		return "/changepass";
	}


	@GetMapping("/home")
	public String home(Model model,HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
		return "home";
	}
	
	@SuppressWarnings("null")
	@GetMapping("/menu")
	public String menu(Model model,HttpSession session) {
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
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
		return "menu";
	}
	
	//xử lý thêm món
	@PostMapping("/menu_add")
	public String addFood(HttpServletRequest request,@RequestParam("img-food-input") MultipartFile multipartFile
			,Model model) throws IOException {
		Product product = new Product();
		String type = request.getParameter("type");
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String typeV;
		if(type.equals("nuong")) {
			typeV = "Nướng";
		}else if(type.equals("lau")) {
			typeV = "Lẩu";
		}else if(type.equals("chay")) {
			typeV = "Chay";
		}else {
			typeV = "Fastfood";
		}
		
		if(repoProduct.findbytitle(request.getParameter("namefood")) != null) {
			model.addAttribute("error", "Tên món ăn bị trùng"); 
			return "/menu";
		}else if(repoProduct.findbyimgfood(fileName) != null){
			model.addAttribute("error", "Tên ảnh bị trùng"); 
			return "/menu";
		}else {
			product.setImg_food(fileName);
			product.setCategory_name(typeV);
			product.setDescription_food(request.getParameter("description"));
			product.setPrice((Long.parseLong(request.getParameter("price"))));
			product.setTitle(request.getParameter("namefood"));
			
			repoProduct.save(product);
			
			
			String uploadDir = "./src/main/upload/food/";
			  
			Path uploadPath = Paths.get(uploadDir);
			  
			try (InputStream inputStream = multipartFile.getInputStream()){ 
				Path filePath = uploadPath.resolve(fileName); 
				Files.copy(inputStream, filePath ,StandardCopyOption.REPLACE_EXISTING); 
			} catch (IOException e) { 
				throw new IOException("Could not save uploaded file: " + fileName); 
			}
			model.addAttribute("success", "Thêm món thành công"); 
		}

		return "/menu";
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
		reser.setStatus("Đang chờ duyệt");
		
		reserRepo.save(reser);
		
		model.addAttribute("success", "Gửi form đặt bàn thành công");
		
		return "/home";
		
	}
	
	@GetMapping("/food_orders")
	public String food_orders(HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
		return "admin/food_orders";
	}
	
//	@GetMapping("/error")
//	public String error() {
//		return "error";
//	}
	
	@GetMapping("/book_table")
	public String book_table(HttpSession session , Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "/login";
		}
		
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}

    	List<Reservation> listReser = reserRepo.findAll();
    	List<Reservation> listReserwait = new ArrayList<Reservation>();
    	
    	for(int i = 0 ; i < listReser.size() ; i++) {
			if (listReser.get(i).getStatus().equals("Đang chờ duyệt")) {
				listReserwait.add(listReser.get(i));
			}
		}

    	model.addAttribute("listReser", listReserwait);
    	
		return "admin/book_table";
	}
	
	@GetMapping("/contact")
	public String contact(HttpSession session , Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "/login";
		}
		
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
    	
    	List<Contact> listContact = contactRepo.findAll();
    	model.addAttribute("listproductContact", listContact);
    	
		return "admin/contact";
	}
	
	@RequestMapping("/viewcontact")
	@ResponseBody
	public Optional<Contact> viewcontact(Long id){
		return contactRepo.findById(id);
	}
	
	@RequestMapping(value="/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deletefood(Long id, Model model) throws IOException {
		try {
			Product product = repoProduct.findbytestId(id);	
		
			String DeleteDir = "./src/main/upload/food/"; 
			Path DeletePath = Paths.get(DeleteDir); 
			Path filePath = DeletePath.resolve(product.getImg_food());
			  
			Files.delete(filePath);
			
			repoProduct.delete(product) ;
			
			
			return "redirect:/menu";
		}catch (IOException e) {
			throw new IOException("Could not delete this: "); 
		}
		
	}
	
	@RequestMapping("/view")
	@ResponseBody
	public Optional<Product> view(Long id){
		return repoProduct.findById(id);
	}
	
	@PostMapping(value="/update")
	public String update(HttpServletRequest request,@RequestParam("img-food-editinput") MultipartFile multipartFile,Model model) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Long id = Long.parseLong(request.getParameter("editid"));
		Long price = Long.parseLong(request.getParameter("price"));
		String type = request.getParameter("category");
		String typeV;
		if(type.equals("nuong")) {
			typeV = "Nướng";
		}else if(type.equals("lau")) {
			typeV = "Lẩu";
		}else if(type.equals("chay")) {
			typeV = "Chay";
		}else {
			typeV = "Fastfood";
		}
		
		Product product = repoProduct.findbyId(id);
		if(!request.getParameter("name").equals(product.getTitle()) 
				& (repoProduct.findbytitle(request.getParameter("name")) != null)) {
			model.addAttribute("error", "Tên món ăn bị trùng"); 
			return "/menu";
			
		}else if(repoProduct.findbyimgfood(fileName) != null){
			model.addAttribute("error", "Tên ảnh bị trùng"); 
			return "/menu";
		}
		else if(fileName.isEmpty()) {
			product.setCategory_name(typeV);
			product.setDescription_food(request.getParameter("description"));
			product.setPrice(price);
			product.setTitle(request.getParameter("name"));
			repoProduct.save(product);
			model.addAttribute("success", "Cập nhật thành công");
		}else {
			
			//Xóa hình ảnh cũ
			String DeleteDir = "./src/main/upload/food/"; 
			Path DeletePath = Paths.get(DeleteDir); 
			Path filePath = DeletePath.resolve(product.getImg_food());	  
			Files.delete(filePath);
			
			product.setCategory_name(typeV);
			product.setDescription_food(request.getParameter("description"));
			product.setPrice(price);
			product.setTitle(request.getParameter("name"));
			product.setImg_food(fileName);
			//thêm hình ảnh mới
			
			try (InputStream inputStream = multipartFile.getInputStream()){ 
				Path filePath2 = DeletePath.resolve(fileName); 
				Files.copy(inputStream, filePath2 ,StandardCopyOption.REPLACE_EXISTING); 
			} catch (IOException e) { 
				throw new IOException("Could not save uploaded file: " + fileName); 
			}
			repoProduct.save(product);
			model.addAttribute("success", "Chỉnh sửa món thành công");
		}

		return "/menu";
		
	}
	
	@PostMapping(value="/deleteContact")
	public String deleteContact(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("contactid"));
		Contact contact = contactRepo.getById(id);
		contactRepo.delete(contact);

		return "redirect:/contact";
	}
}