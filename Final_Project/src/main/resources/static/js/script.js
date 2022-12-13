/* Set values + misc */
var fadeTime = 300;

$(document).ready(function () {
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
	
	/* Recalculate cart */
	function recalculateCart(onlyTotal) {
	  var subtotal = 0;
	
	  /* Sum up row totals */
	  $('.basket-product').each(function() {
	    subtotal += parseFloat($(this).children('.subtotal').text());
	  });
	
	  /* Calculate totals */
	  var total = subtotal;
	
	  /*If switch for update only total, update only total display*/
	  if (onlyTotal) {
	    /* Update total display */
	    $('.total-value').fadeOut(fadeTime, function() {
	      $('#basket-total').html(total.toFixed(2));
	      $('.total-value').fadeIn(fadeTime);
	    });
	  } else {
	    /* Update summary display. */
	    $('.final-value').fadeOut(fadeTime, function() {
	      $('#basket-total').html(total.toFixed(3));
	      if (total == 0) {
	        $('.checkout-cta').fadeOut(fadeTime);
	      } else {
	        $('.checkout-cta').fadeIn(fadeTime);
	      }
	      $('.final-value').fadeIn(fadeTime);
	    });
	  }
	}
	
	/* Update quantity */
	function updateQuantity(quantityInput) {
	  /* Calculate line price */
	  var productRow = $(quantityInput).parent().parent();
	  var price = parseFloat(productRow.children('.price').text());
	  var quantity = $(quantityInput).val();
	  var linePrice = price * quantity;
	
	  /* Update line price display and recalc cart totals */
	  productRow.children('.subtotal').each(function() {
	    $(this).fadeOut(fadeTime, function() {
	      $(this).text(linePrice.toFixed(3));
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
	
	/* Show Popup Send Contact and Book Table */
	
	var btn_bookatable = $(".btn-booktable");
	var btn_contact = $(".btn-contact");
     
    btn_bookatable.click(function() {
	  	$(".popup-bookatable-container").addClass("showPopup");
	});
	
	btn_contact.click(function() {
	  	$(".popup-contact-container").addClass("showPopup");
	});
	
	/* Exit Popup Send Contact and Book Table */
	var exit_icon = $(".exit-icon");
	
	exit_icon.click(function() {
	  	$(".popup-contact-container").removeClass("showPopup");
	  	$(".popup-bookatable-container").removeClass("showPopup");
	});
	/* JS Menu Page */
	
	var price_foods = document.getElementsByClassName("food-price");
	
	
	for(var i = 0; i < price_foods.length; i++){
	    var price_food = price_foods[i].innerHTML;
	    
	    var numbers = parseInt(price_food);
	
		numbers = numbers.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
		
		price_foods[i].innerHTML = numbers;
	}
	
	var cart_items = $(".cart-item");
	var num_cart_item = $(".num-cart-item");
	var num = 0;
	for(var i = 0; i < cart_items.length; i++) {
		num++;
	}
	
	num_cart_item.text(num);
	
	/* Slide show Home Page */
	
	var myIndex = 0;
	carousel();
	
	function carousel() {
	  var i;
	  var x = $('.mySlides');
	  for (i = 0; i < x.length; i++) {
	    x[i].style.display = "none";  
	  }
	  myIndex++;
	  if (myIndex > x.length) {myIndex = 1}    
	  x[myIndex-1].style.display = "block";  
	  setTimeout(carousel, 4000); // Change image every 4 seconds
	}
});
