package utils;

import java.util.Map;

import com.example.demo.model.Cart;

public class Utils {
	public static int countCart(Map<Long, Cart> cart) {
		int q = 0;
		if (cart != null) {
			for (Cart c : cart.values())
				q += c.getQuantity();
		}
		return q;
	}
}
