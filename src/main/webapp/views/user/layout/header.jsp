<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>CC Shop</title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<!-- Include loadingbar.js from CDN -->
    	<script src="https://cdn.jsdelivr.net/npm/loading-bar/dist/loading-bar.min.js"></script>

    </head>
    
    <style>
    	.header-search form .input {
		  width: calc(100% - 160px);
		  margin-right: -4px;
		  border-radius: 40px 0px 0px 40px !important;
		}
    </style>
    
	<body>
		<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li> <h5 class=" fw-semibold" style="color: white;">Your Account Info</h5> </li>
						<li><a href="#"><i class="fa fa-phone"></i>+95-${customer.phone}</a></li>
						<li><a href="#"><i class="fa fa-envelope-o"></i>${customer.email }</a></li>
						<li><a href="#"><i class="fa fa-map-marker"></i>${customer.address }</a></li>
					</ul>
					<ul class="header-links pull-right">
						<li><a href="${pageContext.request.contextPath}/UserController?page=profile&user_id=${customer.id}"><i class="fa fa-user-o"></i> My Account</a></li>
						<li><a href="${pageContext.request.contextPath}/UserController?page=history&user_id=${customer.id}&date=all&status=all"><i class="fa fa-file"></i> History</a></li>
						<li><a href="${pageContext.request.contextPath}/UserController?page=order&user_id=${customer.id}&filter_value=all"><i class="fa fa-car"></i>Order</a></li>
						<li><a href="${pageContext.request.contextPath}/LoginController?page=userLogout"><i class="fa fa-power-off"></i> Logout</a></li>
					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="#" class="logo">
									<img src="./img/logo.png" alt="">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form method="get" action="#" id="productForm">
									<input id="searchInput" class="input" placeholder="Search here">
									<input id="customer_id" class="input" type="hidden"  value="${customer.id}">
									<button type="submit" class="search-btn">Search</button>
								</form>					
							</div>
						</div>
						<!-- /SEARCH BAR -->

						
						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">

								<!-- Wishlist -->
								<div>
									<a href="#">
										<i class="fa fa-heart-o"></i>
										<span>Your Wishlist</span>
										<div class="qty">${whistlist_count}</div>
									</a>
								</div>
								<!-- /Wishlist -->
								

								<!-- Cart -->
								<div class="dropdown">
									<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
										<i class="fa fa-shopping-cart"></i>
										<span>Your Cart</span>
										<div class="qty">${carts.size()}</div>
									</a>
									<div class="cart-dropdown">
										<div class="cart-list">
											<c:forEach items="${carts}" var="cart">
											<div class="product-widget">
												<div class="product-img">
													<img src="${pageContext.request.contextPath}/assets/images/products/${cart.image}" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=${cart.product_id}">${cart.product_name }</a></h3>
													<h4 class="product-price"><span class="qty">${cart.count}x</span>${cart.price} MMKs</h4>
												</div>
												<button class="delete">
													<a href="${pageContext.request.contextPath}/CartController?action=deleteFromCart&cart_id=${cart.id}">
														<i class="fa fa-close" style="color: white !important;"></i>
													</a>
												</button>
											</div>
											</c:forEach>
										</div>
										<div class="cart-summary">
											<small>${carts.size()} Item(s) selected</small>
										    <c:set var="totalSubtotal" value="0" />
										    <c:forEach items="${carts}" var="cart">
										        <c:set var="subtotal" value="${cart.count * cart.price}" />
										        <c:set var="totalSubtotal" value="${totalSubtotal + subtotal}" />
										    </c:forEach>
										    <h5>SUBTOTAL : ${totalSubtotal} MMKs</h5>
										</div>
										<div class="cart-btns">
											<a href="${pageContext.request.contextPath}/CartController?action=main&user_id=${customer.id}">View Cart</a>
											<a href="${pageContext.request.contextPath}/CheckoutController?page=main&user_id=${customer.id}">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
										</div>
									</div>
								</div>
								<!-- /Cart -->

								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
							
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->
		
		


		
		