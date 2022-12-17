package com.example.demo.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Cart;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Food_order;
import com.example.demo.model.User;
import com.example.demo.repository.Food_orderRepository;
import com.example.demo.repository.UserRepository;

@Controller
@ControllerAdvice
public class CartController {
	@Autowired
	private UserRepository repo;
	@Autowired
	private Food_orderRepository repoFood;
	
	@GetMapping("/cart")
	public String cart(Model model,HttpSession session , @AuthenticationPrincipal CustomUserDetails loggedUser) {
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
    	User user = repo.getUserByUsername(loggedUser.getUsername());
    	model.addAttribute("userphone",user.getPhone());
		return "cart";
	}
	
	@PostMapping("/cart_pay")
	public String payOrder(HttpSession session , HttpServletRequest request,Model model,@AuthenticationPrincipal CustomUserDetails loggedUser) {
		@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
		User user = repo.getUserByUsername(loggedUser.getUsername());
    	if (cart != null) {
    		String s = "";
    		Long d = (long) 0;
    		Long total = (long) 0;
    		Food_order food_order = new Food_order();
    		for (Cart c : cart.values()) {
    			s = s + c.getTitle() + ',' + c.getQuantity() + ',';
    			d = d + c.getQuantity();
    			c.setQuantity(1);
    			total = total + c.getPrice()*c.getQuantity();
    		}
			food_order.setEmail(user.getEmail());
			food_order.setFood_name(s);
			food_order.setPhone_number(request.getParameter("phone_number_editor"));
			food_order.setQuantity(d);
			food_order.setTotal_price(total);
			food_order.setUser_address(request.getParameter("address_customer"));
			food_order.setUsername(loggedUser.getUsername());
			food_order.setStatus("Đang chờ duyệt");
			repoFood.save(food_order);
    	}
		return "redirect:/cart";
	}
}
