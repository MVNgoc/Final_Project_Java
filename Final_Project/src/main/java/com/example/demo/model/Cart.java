package com.example.demo.model;

public class Cart {
		private long id;
		private String title;
		private String img_food;
		private Long price;
		private int quantity;

		public Cart() {
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getImg_food() {
			return img_food;
		}

		public void setImg_food(String img_food) {
			this.img_food = img_food;
		}

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
}
