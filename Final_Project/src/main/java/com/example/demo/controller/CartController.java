package com.example.demo.controller;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ProductRepository;

import utils.Utils;

@RestController
public class CartController {
    @Autowired
    private ProductRepository repoProduct;
    
    protected void configure(HttpSecurity http) throws Exception{
    	http.csrf().disable();
    }
    
    @PostMapping("/api/cart")
    public int addToCart(@RequestBody Cart params , HttpSession session) throws Exception {
    	Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cart");
    	if (cart == null) {
    		cart = new HashMap<>();
    	}
    	if (cart.containsKey(params.getProduct().getId()) == true) {
    		Cart c = cart.get(params.getProduct().getId());
    		c.setQuantity(c.getQuantity()+1);
    	}else {
    		cart.put(params.getProduct().getId(), params);
    	}
    	session.setAttribute("cart", cart);
    	return Utils.countCart(cart);
    }
}

