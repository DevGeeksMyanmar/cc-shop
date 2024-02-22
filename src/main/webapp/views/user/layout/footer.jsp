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
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
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

    $('#productForm').submit(function(event) {
        event.preventDefault(); // Prevent the default form submission

        var category = $('#categorySelect').val();
        var searchQuery = $('#searchInput').val();

        $.ajax({
            type: 'GET',
            url: 'http://localhost:9095/shop-dot-com/UserController?page=fetch', // Specify the URL of your servlet here
            data: {
                category: category,
                searchTerm: searchQuery
            },
            success: function(response) {
                // Handle the response from the servlet
            	var products = JSON.parse(response.products);
                var categories = JSON.parse(response.categories);
                var gridContainer = $('.search-section .grid-container');
                
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
                                        <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
                                        <!-- You can set the href attribute dynamically -->
                                        <button class="quick-view"><a class="" href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=\${product.id}"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></a></button>
                                    </div>
                                </div>
                                <div class="add-to-cart">
                                    <button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
                                </div>
                            </div>
                            <!-- /product -->
                        </div>`;

                    // Append the product HTML to the grid container
                    $('.search-section .grid-container').append(productHTML);
                });

                
                
            },
            error: function(xhr, status, error) {
                // Handle errors
                console.error(error);
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