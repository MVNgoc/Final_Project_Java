/* Set values + misc */
var fadeTime = 300;

$(document).ready(function() {
	/* Assign actions */
	$('.quantity input').change(function() {
		updateQuantity(this);
	});

	$('.remove button').click(function() {
		removeItem(this);
	});

	$(document).ready(function() {
		updateSumItems();
	});

	var subtotal_temp = document.getElementsByClassName("subtotal-temp");
	var basket_total = document.getElementById("basket-total");
	var sum = 0;

	for (var i = 0; i < subtotal_temp.length; i++) {
		sum += parseInt(subtotal_temp[i].innerHTML);
	}

	if (sum != 0) {
		basket_total.innerHTML = sum;
	}

	/* Recalculate cart */
	function recalculateCart(onlyTotal) {
		var subtotal = 0;

		/* Sum up row totals */
		$('.basket-product').each(function() {
			subtotal += parseFloat($(this).children('.subtotal-temp').text());
		});

		/* Calculate totals */
		var total = subtotal;
		/*If switch for update only total, update only total display*/
		if (onlyTotal) {
			/* Update total display */
			$('.total-value').fadeOut(fadeTime, function() {
				$('#basket-total').html(total.toLocaleString('it-IT', { style: 'currency', currency: 'VND' }));
				$('.total-value').fadeIn(fadeTime);
			});
		} else {
			/* Update summary display. */
			$('.final-value').fadeOut(fadeTime, function() {
				$('#basket-total').html(total.toLocaleString('it-IT', { style: 'currency', currency: 'VND' }));
				if (total == 0) {
					$('.checkout-cta').fadeOut(fadeTime);
				} else {
					$('.checkout-cta').fadeIn(fadeTime);
				}
				$('.final-value').fadeIn(fadeTime);
			});
		}
	}

	/* Format price */
	var price_tag = document.getElementsByClassName("price");
	var subtotal = document.getElementsByClassName("subtotal");

	for (var i = 0; i < price_tag.length; i++) {
		var num_price = parseInt(price_tag[i].innerHTML);
		num_price = num_price.toLocaleString('it-IT', { style: 'currency', currency: 'vnd' });
		price_tag[i].innerHTML = num_price;

		var num_subtotal = parseInt(subtotal[i].innerHTML);
		num_subtotal = num_subtotal.toLocaleString('it-IT', { style: 'currency', currency: 'vnd' });
		subtotal[i].innerHTML = num_subtotal;
	}

	var basket_total_text = $("#basket-total").text()
	var fomat_basket_total = parseInt(basket_total_text).toLocaleString('it-IT', { style: 'currency', currency: 'vnd' });
	$("#basket-total").html(fomat_basket_total);

	/* Update quantity */
	function updateQuantity(quantityInput) {
		/* Calculate line price */
		var productRow = $(quantityInput).parent().parent();
		var price = parseInt(productRow.children('.price-temp').text());
		var quantity = $(quantityInput).val();
		var linePrice = price * quantity;

		/* Update line price display and recalc cart totals */
		productRow.children('.subtotal').each(function() {
			$(this).fadeOut(fadeTime, function() {
				$(this).text(linePrice.toLocaleString('it-IT', { style: 'currency', currency: 'vnd' }));
				var temp = linePrice;
				productRow.children('.subtotal-temp').each(function() {
					$(this).text(temp);
				});
				recalculateCart();
				$(this).fadeIn(fadeTime);
			});
		});

		productRow.find('.item-quantity').text(quantity);
		updateSumItems();
	}

	function updateSumItems() {
		var sumItems = 0;
		$('.quantity input').each(function() {
			sumItems += parseInt($(this).val());
		});
		$('.total-items').text(sumItems);
	}

	/* Remove item from cart */
	function removeItem(removeButton) {
		/* Remove row from DOM and recalc cart total */
		var productRow = $(removeButton).parent().parent();
		productRow.slideUp(fadeTime, function() {
			productRow.remove();
			recalculateCart();
			updateSumItems();
		});
	}

	/* Exit Popup Send Contact and Book Table */
	var exit_icon = $(".exit-icon");

	exit_icon.click(function() {
		$(".popup-changepass-container").css("display", "none");
		$(".popup-deletefood-container").css("display", "none");
	});
	/* JS Menu Page */

	var price_foods = document.getElementsByClassName("food-price");


	for (var i = 0; i < price_foods.length; i++) {
		var price_food = price_foods[i].innerHTML;

		var numbers = parseInt(price_food);

		numbers = numbers.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });

		price_foods[i].innerHTML = numbers;
	}

	var cart_items = $(".cart-item");
	var num_cart_item = $(".num-cart-item");
	var num = 0;
	for (var i = 0; i < cart_items.length; i++) {
		num++;
	}

	num_cart_item.text(num);

	$(".add-food-icon").click(function() {
		$(".popup-addfood-container").removeClass("hide");
	})

	$("#cancel-btn").click(function() {
		$(".popup-addfood-container").addClass("hide");
	})
	
	$("#cancel-edit-btn").click(function() {
		$(".popup-editfood-container").addClass("hide");
	})
	
	$("#cancel-delete-btn").click(function() {
		$(".popup-confirmdeletefood-container").addClass("hide");
	})
	
	var edit_icon = document.getElementsByClassName("edit-icon");
	var remove_icon = document.getElementsByClassName("remove-icon");
	
	for (var i = 0; i < edit_icon.length; i++) {
		(function(i){ 
		  edit_icon[i].onclick = function() {
		      $(".popup-editfood-container").removeClass("hide");
		  }
		  remove_icon[i].onclick = function() {
		      $(".popup-confirmdeletefood-container").removeClass("hide");
		  }
		})(i);
	}

	/* Preview an image before it is uploaded */

	var img_food_input = document.getElementById("img-food-input");
	var img_food_add = document.getElementById("img-food-add");

	if (img_food_input != null) {
		img_food_input.onchange = e => {
			const [file] = img_food_input.files;
			if (file) {
				img_food_add.src = URL.createObjectURL(file);
			}
		}
	}
	
	/* JS Food Orders Page */
	var edit_order = document.getElementsByClassName("edit_order");
	for (var i = 0; i < edit_order.length; i++) {
		(function(i){ 
		  edit_order[i].onclick = function() {
		      $(".popup-orderfood-container").removeClass("hide");
		  }
		})(i);
	}
	
	$(".popup-exit-order").click(function() {
			$(".popup-orderfood-container").addClass("hide");
	})
	
	/* JS Book Table Page */
	var edit_book_table = document.getElementsByClassName("edit_book_table");
	for (var i = 0; i < edit_book_table.length; i++) {
		(function(i){ 
		  edit_book_table[i].onclick = function() {
		      $(".popup-booktable-container").removeClass("hide");
		  }
		})(i);
	}
	
	$(".popup-exit-booktable").click(function() {
			$(".popup-booktable-container").addClass("hide");
	})
	
	/* JS Contact Page */
	
	var edit_contact = document.getElementsByClassName("edit_contact");
	for (var i = 0; i < edit_contact.length; i++) {
		(function(i){ 
		  edit_contact[i].onclick = function() {
		      $(".popup-contact-container").removeClass("hide");
		  }
		})(i);
	}
	
	$(".popup-exit-contact").click(function() {
			$(".popup-contact-container").addClass("hide");
	})
	
	/* Slide show Home Page */

	var myIndex = 0;
	carousel()

	function carousel() {
		var i;
		var x = $('.mySlides');
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		myIndex++;
		if (myIndex > x.length) { myIndex = 1 }
		x[myIndex - 1].style.display = "block";
		setTimeout(carousel, 3000); // Change image every 3 seconds
	}
});

function addToCart(product) {
	fetch("/api/cart", {
		method: "Post",
		body: JSON.stringify({
			"product": {
				"id": product.id,
				"title": product.title,
				"img_food": product.img_food,
				"description_food": product.description_food,
				"price": product.price,
				"catetogry_name": product.catetogry_name
			},
			"quantity": 1
		}),
		headers: {
			"content-Type": "application/json;charset=utf-8"
		}
	}).then(function(res) {
		return res.json()
	}).then(function(data) {
		let counter = document.getElementById("cartCounter")
		counter.innerText = data
	})

}
