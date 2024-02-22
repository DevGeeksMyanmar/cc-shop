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

    </head>
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
						<li><a href="${pageContext.request.contextPath}/LoginController?page=userLogout"><i class="fa fa-power-off"></i> Logout</a></li>
					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

			
		</header>
		<!-- /HEADER -->
		
		


		
		