package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Cart;

@Controller
public class CartController {
	@GetMapping("/cart")
	public String cart(Model model,HttpSession session) {
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cart");
    	if (cart != null) {
    		model.addAttribute("carts",cart.values());
    	}else {
    		model.addAttribute("carts",null);
    	}
		return null;
	}
}
