package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import com.example.demo.model.Cart;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    	return Utils.countCart(cart);
    }
}

