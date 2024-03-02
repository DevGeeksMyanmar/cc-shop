<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- FOOTER -->
		<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Help Center</h3>
								<p>It's our pleasure to help your needs.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 TawWin Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+959-686-118-284</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>khanthtet@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
								<c:forEach items="${categories}" var="category">
							<li class="${param.category_id == category.id ? 'active' : ''}"><a href="${pageContext.request.contextPath}/UserController?page=fetchByCategory&category_id=${category.id}&user_id=${customer.id}">${category.name}</a></li>
						</c:forEach>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

					
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="${pageContext.request.contextPath}/UserController?page=profile&user_id=${customer.id}">My Account</a></li>
									<li><a href="${pageContext.request.contextPath}/CartController?action=main&user_id=${customer.id}">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="${pageContext.request.contextPath}/UserController?page=order&user_id=${customer.id}&filter_value=all">Track My Order</a></li>
									<li><a href="${pageContext.request.contextPath}/UserController?page=history&user_id=${customer.id}&date=all&status=all">History</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			
		</footer>
		<!-- /FOOTER -->
		
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>

$(document).ready(function() {
	
	$('#alert-error').hide(); // hide the error first
	$("#alert-success").hide(); // hide the success mesage box first
	
    $('#productForm').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission

        var category = $('#categorySelect').val();
        var searchQuery = $('#searchInput').val();
        var customerID = $("#customer_id").val();

        $.ajax({
            type: 'GET',
            url: 'http://localhost:9095/shop-dot-com/UserController?page=fetch', // Specify the URL of your servlet here
            data: {
                category: category,
                searchTerm: searchQuery,
                customerID: customerID,
            },
            success: function(response) {
                // Handle the response from the servlet
            	var products = JSON.parse(response.products);
                var categories = JSON.parse(response.categories);
                var gridContainer = $('.search-section .grid-container');
                var customerID = response.customerID;
                
                if(searchQuery != ''){
                	$('.main-section').hide();
                	gridContainer.show();
                }else{
                	$('.main-section').show();
                	gridContainer.hide();
                }
                               
             // Clear previous content
                gridContainer.empty();
                
             // Loop through products and append HTML for each product
             products.forEach(function(item) {
				    // Access properties within each object using bracket notation
				    console.log("ID:", item.id);
				    console.log("Name:", item.name);
				    console.log("Price:", item.price);
				    console.log("Count:", item.count);
				    console.log("customer:", customerID);
				    // Access other properties as needed
			});
             // Assuming `products` is your array of products
                products.forEach(function(product) {
                    var productHTML = 
                    	`<div class="grid-item">
                            <!-- product -->
                            <div class="product">
                                <div class="product-img">
                                    <!-- You can set the image source dynamically if you have image URLs -->
                                    <img src="${pageContext.request.contextPath}/assets/images/products/\${product.image}" alt="">
                                </div>
                                <div class="product-body">
                                    <p class="product-category">Category: \${product.category_name}</p>
                                    <p class="product-category" style="font-weight: bold;">Seller: \${product.seller_name}</p>
                                    <h3 class="product-name">\${product.name}</h3>
                                    <h4 class="product-price">\${product.price} MMKs</h4>
                                    <div class="product-btns">
                                        <button class="add-to-wishlist">
                                        <a class="" href="${pageContext.request.contextPath}/WhistlistController?action=addToWhistList&product_id=\${product.id}&user_id=\${customer.id}"> 
                                        	<i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span>
                                        </a>
                                        </button>
                                        <!-- You can set the href attribute dynamically -->
                                        <button class="quick-view"><a class="" href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=\${product.id}"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></a></button>
                                    </div>
                                </div>
                                <div class="add-to-cart">
                                	<a href="${pageContext.request.contextPath}/CartController?action=addToCart&product_id=\${product.id}&user_id=\${customerID}">
                                		<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                	</a>
                                </div>
                            </div>
                            <!-- /product -->
                        </div>`;

                    // Append the product HTML to the grid container
                    $('.search-section .grid-container').append(productHTML);
                });
             
             
                if(products.length == 0) {
                	$('.search-section .grid-container').html("<h4 style='margin-bottom: 10rem !important;''>No product found</h4>");
                }

                
                
            },
            error: function(xhr, status, error) {
                // Handle errors
                console.error(error);
            }
        });
    });
    
    
 // Ajax request to send data to servlet
    $('#quantity').on('change', function() {
    var quantity = $(this).val();
    var product_id = $("#product_id").val();
	    $.ajax({
	        type: 'GET',
	        url: "http://localhost:9095/shop-dot-com/CartController?action=validateCount",
	        data: { 
	            quantity: quantity,
	            product_Id: product_id
	        },
	        success: function(response) {
	            var message = response.message;
	            var count = response.maxQuantity;	            
	            console.log(message);
	            console.log(count)
	            if (quantity > count) {
	                // If quantity exceeds max count, set the value to max count and show error
	                $('#quantity').val(count);
	                $('#alert-error').text("Only " + count + " number of products are available!").fadeIn(500).delay(2000).fadeOut(500);
	            } else {
	                // If quantity is within the limit, hide the error message
	                $('#alert-error').fadeOut(500);
	            }
	        },
	        error: function(xhr, status, error) {
	            // Handle error
	            console.error('Error sending data:', error);
	        }
	    });
	});
 
 
 // add to cart btn from detail page
    $(".main-add-to-cart-btn").click(function(event) {
        // Prevent default button behavior (optional)
        event.preventDefault();
        console.log("clicked")

        // Get the product ID from the hidden input
        var productId = $("#product_id").val();
        var user_id = $("#user_id").val();

        // Get the selected quantity from the number input
        var quantity = parseInt($("#quantity").val());
        console.log(productId);
        console.log(quantity);
        console.log(user_id);

        // Send the data to the servlet using AJAX
        $.ajax({
            url: "http://localhost:9095/shop-dot-com/CartController?action=addToCartFromDetail",
            method: "GET",
            data: {
                user_id: user_id,
                product_id: productId,
                quantity: quantity
            },
            success: function(response) {
            	console.log("data sent");
            	var success = response.success;
	            var error = response.error;
	            
	            console.log(success);
	            console.log(error);
	            
	            if(success !== undefined){
	            	$('#alert-success').text("Add to cart successfully!").fadeIn(500).delay(2000).fadeOut(500);
	            }else{
	            	$('#alert-success').fadeOut(500);
	            }
	            
	            if(error !== undefined){
	            	$('#alert-error').text("The item already exist in cart").fadeIn(500).delay(2000).fadeOut(500);
	            }else{
	            	$('#alert-error').fadeOut(500);
	            }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // Handle errors (e.g., display error message to user)
                console.error("Error adding to cart:", textStatus, errorThrown);
            }
        });
    });

 
 

    // Track input on the search input field
    $('#searchInput').on('input', function() {
        // Automatically submit the form when user types
        $('#productForm').submit();
    });
    
    var currentUrl = window.location.href;

    // Check if the current URL matches the desired route URL
    if (currentUrl.indexOf("/UserController?page=main") !== -1) {
        // Add the 'active' class to the list item
        $('li#mainLink').addClass('active');
    }
    
    
 // Find the error alert element
    var $errorAlert = $('#alert-success');
    
    // If the alert element exists
    if ($errorAlert.length) {
        // Set a timeout to hide the alert after 3 seconds
        setTimeout(function() {
            // Fade out the alert over 0.5 seconds
            $errorAlert.fadeOut(500);
        }, 3000); // 3000 milliseconds = 3 seconds
    }
    
 
    
});

</script>

		<!-- jQuery Plugins -->
		<script src="${pageContext.request.contextPath}/assets/customer/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/slick.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/nouislider.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/jquery.zoom.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/main.js"></script>

	</body>
</html>