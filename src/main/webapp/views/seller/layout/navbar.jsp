
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="main-header bg-primary">
			<div class="logo-header ">
				<a href="index.html" class="logo text-light">
					Dashboard
				</a>
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<button class="topbar-toggler more"><i class="la la-ellipsis-v text-light"></i></button>
			</div>
			<nav class="navbar navbar-header navbar-expand-lg">
				<div class="container-fluid">
				
					<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
						<li class="nav-item dropdown">
							<a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#" aria-expanded="false">
							 <!-- image -->
							 <c:choose>
							 	<c:when test="${seller.image == 'assets/images/troll.jpg' }">
							 		<img src="${seller.image}" alt="user-img" width="39" class="img">
							 	</c:when>
							 	<c:otherwise>
							 		<img src="${pageContext.request.contextPath}/assets/images/seller/${seller.image}" alt="user-img" width="39" class="img">
							 	</c:otherwise>
							 </c:choose>
							 <!-- seller image -->
							 <span class="text-light" >${seller.name}</span></span> 
							 </a>
							<ul class="dropdown-menu dropdown-user">
								<li>
									<div class="user-box">
	                                		<div class="u-img"><img src="${pageContext.request.contextPath}/assets/images/seller/${seller.image}" alt="user"></div>
										<div class="u-text">
											<h4 class="">${seller.name}</h4>
											<p class="text-muted">${seller.email}</p><a href="${pageContext.request.contextPath}/SellerController?page=profile&seller_id=${seller.id}" class="btn btn-rounded btn-primary btn-sm">View Profile</a></div>
										</div>
									</li>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#"><i class="las la-envelope"></i> Inbox</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/LoginController?page=sellerLogout"><i class="las la-power-off "></i> Logout</a>
								</ul>
								<!-- /.dropdown-user -->
							</li>
						</ul>
					</div>
				</nav>
			</div>