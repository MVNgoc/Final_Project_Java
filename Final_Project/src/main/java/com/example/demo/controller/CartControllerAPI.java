package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.example.demo.model.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import utils.Utils;

@RestController
public class CartControllerAPI {
    @PostMapping("/api/cart")
    public int addToCart(@RequestBody Cart params , HttpSession session) throws Exception {
    	@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart == null) {
    		cart = new HashMap<>();
    	}
    	if (cart.containsKey(params.getId()) == true) {
    		Cart c = cart.get(params.getId());
    		c.setQuantity(c.getQuantity()+1);
    	}else {
    		cart.put(params.getId(), params);
    	}
    	session.setAttribute("cartSession", cart);
    	return cart.size();
    }
    
    @DeleteMapping("/api/cart/{productId}")
    public int removeToCart(@PathVariable(value = "productId") long productId , HttpSession session) {
    	@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart != null && cart.containsKey(productId)) {
    		cart.remove(productId);
    		session.setAttribute("cartSession", cart);
    	}
    	return cart.size();
    }
    
    @PutMapping("/api/cart")
    @ResponseStatus(HttpStatus.OK)
    public void updateCart(@RequestBody Cart params , HttpSession session) throws Exception {
    	@SuppressWarnings("unchecked")
		Map<Long,Cart> cart = (Map<Long,Cart>) session.getAttribute("cartSession");
    	if (cart == null) {
    		cart = new HashMap<>();
    	}
    	if (cart.containsKey(params.getId()) == true) {
    		Cart c = cart.get(params.getId());
    		c.setQuantity(params.getQuantity());
    	}
    	session.setAttribute("cartSession", cart);
    }
}

