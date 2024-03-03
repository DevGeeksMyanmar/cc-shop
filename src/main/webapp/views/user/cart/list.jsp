<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>

<style>

	.main-wrapper{
		display: flex;
		align-items: start;
		justify-content: center;
	}
	.item-wrapper{
		display: flex;
		padding: 2rem !important;
		border: 1px solid #ccc;
		align-items: center;
		justify-content: start;
		margin-bottom: 1rem;
		border-radius: 6px;
		position: relative;
	}
	.item-wrapper .remove-btn{
		position: absolute;
		top:0;
		right: 0;
		background-color: #666;
		border: none;
		border-radius: 0 6px 0 0;
	}
	.item-wrapper .remove-btn:hover{
		background-color: #D10024 !important;
	}
	.product-desc{
		width: 100%;
		margin-left: 5rem;	
		display: flex;
		align-items: center;
		justify-content: space-between;
	}
	.product-desc .product-price{
		color: #D10024;
		font-weight: 500;
	}
	.checkout-btn{
		background-color: #D10024;
		padding: 2rem 2.5rem;
		color: #fff;
		border-radius: 4px;
		font-weight: 700;
	}
	.checkout-btn:hover{
		background-color: #fff;
		border: 2px solid #D10024;
	}
	.btn-wrapper{
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: end;
	}

</style>


		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="${pageContext.request.contextPath}/UserController?page=main">HOME</a></li>
							<li class="active">Cart</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
			
			
				<!-- alert -->
					<c:if test="${not empty success }">
						<div class="alert bg-success text-center " role="alert" id="errorAlert">
							${success}
						</div>
					</c:if>
					<c:if test="${not empty error }">
						<div class="alert bg-danger text-center " role="alert" id="errorAlert">
							${error}
						</div>
					</c:if>
					<div class="alert bg-danger text-center " role="alert" id="alert-error">
							<!-- error message goes here -->
					</div>
					<div class="alert bg-success text-center " role="alert" id="alert-success">
							<!-- error message goes here -->
					</div>
			
				<!-- row -->
				<div class="row main-wrapper">
				
					<div class="col-md-8">
					
						
							<!-- customer id -->
							<input type="hidden" id="customer_id" value="${customer.id}" >
							<!-- end of custome id -->
								<div class="cart-summary" id="cart-list-summary">
				        			<small class="product-count-in-summary"> <span id="total_item"></span> Product(s) selected</small>
				        		    <h5>SUBTOTAL : <span id="total_price"></span> MMKs</h5>
				        		</div>
								<h4 class="text-center status-for-summary" id="cart-status" style="margin-bottom: 8rem">Empty cart! :(</h4>
							
						
					
						<c:forEach items="${carts}" var="cart">
						<!-- items -->
						<div class="row item-wrapper">
							<!-- image -->
							<div class="col-2">
								<div class="product-img" >
									<img src="${pageContext.request.contextPath}/assets/images/products/${cart.image}" width="100px">
								</div>
							</div>
							<!-- item detail -->
							<div class="col-10 product-desc">
								<div class="">
									<h4>${cart.product_name }</h4>
									<span class="product-price">${cart.price} MMKs</span> 
								</div>
								<div class="">
									<div class="input-number">
										<input type="hidden" id="cart_id" value="${cart.id}" >
										<input type="hidden" id="user_id" value="${customer.id}">
										<input type="hidden" id="product_id" value="${cart.product_id}" >
										<input type="number" id="item_count" value="${cart.count}" style="width: 80px !important;" readonly>
										<span class="qty-up">+</span>
										<span class="qty-down">-</span>
									</div>
								</div>
							</div>
							<button class="remove-btn">
								<a href="${pageContext.request.contextPath}/CartController?action=deleteFromCart&where=cartList&cart_id=${cart.id}&user_id=${user_id}">
									<i class="fa fa-close" style="color: white !important;"></i>
								</a>
							</button>
						</div>
						
						<!-- end of items -->
						</c:forEach>
						
						<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<ul class="store-pagination">
								<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/CartController?action=main&page_number=${currentPage - 1}&user_id=${user_id}"><i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach begin="1" end="${noOfPages}" var="i"> 
									<c:choose> 
								       <c:when test="${currentPage eq i}"> 
								       		<li class="active" ><a style="color: white !important;" class="text-light" href="${pageContext.request.contextPath}/CartController?action=main&page_number=${i}&user_id=${user_id}">${i}</a></li>
								       </c:when>
								       <c:otherwise> 
								       		<li class=""><a href="${pageContext.request.contextPath}/CartController?action=main&page_number=${i}&user_id=${user_id}">${i}</a></li>
								       </c:otherwise>
								    </c:choose>
								</c:forEach>
								<c:if test="${currentPage lt noOfPages}">
									<li><a href="${pageContext.request.contextPath}/CartController?action=main&page_number=${currentPage + 1}&user_id=${user_id}"><i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</div>
						<!-- /store bottom filter -->
						
						
						<a href="${pageContext.request.contextPath}/CheckoutController?page=main&user_id=${customer.id}" class="checkout-btn">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
						
						
					</div>
				</div>
				
				<!-- row -->
				
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		
		
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script>
			
		    // Wait for the document to be ready
		    $(document).ready(function() {
		        // Find the error alert element
		        var $errorAlert = $('#errorAlert');
		        $('#alert-error').hide();
		        $('#alert-success').hide();
		        
		        // If the alert element exists
		        if ($errorAlert.length) {
		            // Set a timeout to hide the alert after 3 seconds
		            setTimeout(function() {
		                // Fade out the alert over 0.5 seconds
		                $errorAlert.fadeOut(500);
		            }, 3000); // 3000 milliseconds = 3 seconds
		        }
		    });
		    
		    var userID = $('#customer_id').val(); // get the user id
		    console.log(userID);
		    
		 // Function to fetch total items from servlet
		    function fetchTotalItemsFromServlet(userId) {
		    	console.log("fetching");
		        $.ajax({
		            url: 'http://localhost:8080/shop-dot-com/CartController?action=getTotalItem', // Replace with your servlet URL
		            method: 'GET',
		            data: {
		                action: 'getTotalItem',
		                user_id: userID
		            },
		            success: function(response) {
		                var total_item = response.total_item;
		                var total_price = response.total_price;
		                
		                $("#total_item").text(total_item);
		                $("#total_price").text(total_price);
		                if(parseInt(total_item) < 1){
		                	$("#cart-status").show();
		                	$(".checkout-btn").hide();
		                	$("#cart-list-summary").hide();
		                	$(".store-pagination").hide();
		                }else{
		                	$("#cart-status").hide();
		                	$("#cart-list-summary").show();
		                	$(".checkout-btn").show();
		                	$(".store-pagination").show();
		                }
		            },
		            error: function(xhr, status, error) {
		                console.error('Error:', error);
		                // Handle error
		            }
		        });
		    }

		    // Call fetchTotalItemsFromServlet() initially
		    fetchTotalItemsFromServlet(userID);

		    // Call fetchTotalItemsFromServlet() every 5 seconds (for example)
		    setInterval(function() {
		        fetchTotalItemsFromServlet(userID);
		    }, 2000); // 5000 milliseconds = 5 seconds
		    
		 // Event listener for increasing count
		    $('.qty-up').on('click', function() {
		        var itemWrapper = $(this).closest('.item-wrapper'); // Find the closest item wrapper
		        var userId = itemWrapper.find('#user_id').val();
		        var productId = itemWrapper.find('#product_id').val();
		        var itemCount = itemWrapper.find('#item_count');
		        var cartId = itemWrapper.find('#cart_id').val();
		        updateProductCount(userId, productId, itemCount, cartId, 1); // +1 for increment
		    });

		    // Event listener for decreasing count
		    $('.qty-down').on('click', function() {
		        var itemWrapper = $(this).closest('.item-wrapper'); // Find the closest item wrapper
		        var userId = itemWrapper.find('#user_id').val();
		        var productId = itemWrapper.find('#product_id').val();
		        var itemCount = itemWrapper.find('#item_count');
		        var cartId = itemWrapper.find('#cart_id').val();
		        updateProductCount(userId, productId, itemCount, cartId, -1); // -1 for decrement
		    });

		    // Function to update product count via AJAX
		    function updateProductCount(userId, productId, itemCount, cartId, change) {
		    	 var currentCount = parseInt(itemCount.val());
		        var newCount = currentCount + change;
		        var itemWrapper = itemCount.closest('.item-wrapper');
		        
		        // Send AJAX request to update count
		        $.ajax({
		            url: "http://localhost:8080/shop-dot-com/CartController?action=updateItemNumber", // Replace with your servlet URL
		            method: 'POST',
		            data: {
		                user_id: userId,
		                product_id: productId,
		                change: newCount, // +1 for increment, -1 for decrement
		                cart_id: cartId,
		            },
		            success: function(response) {
		            	
		            	var message = response.message;
			            var count = response.maxQuantity;
			            
			            console.log("message " + message);
			            console.log("count from servlet " + count);
			            console.log(newCount);

			            if (newCount > count) {
			                // Get the current count value from the input field of the clicked item
   							itemCount.val(count);
			                $('#alert-error').text("Only " + count + " number of products are available!").fadeIn(500).delay(2000).fadeOut(500);
			            }
			            if(count < 1){
			            	console.log("under 0 delete it");
			            	// Display the alert message for 300 milliseconds after removing the item wrapper
				            $('#alert-success').text("Item removed from cart").fadeIn(500).delay(300).fadeOut(500);

				            // Find the wrapper element containing the item and fade it out
				            itemWrapper.fadeOut(500, function() {
				                // After fade out completes, remove the wrapper element from the DOM
				                itemWrapper.remove();
				            });
			            }
		            },
		            error: function(xhr, status, error) {
		                console.error('Error:', error);
		                // Handle error
		            }
		        });
		    }
		</script>
		
		<%@ include file="/views/user/layout/footer.jsp" %>
		
		

