<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="main-header bg-danger">
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
						<li class="nav-item dropdown hidden-caret">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="la la-envelope text-light"></i>
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								<a class="dropdown-item" href="#">Action</a>
								<a class="dropdown-item" href="#">Another action</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Something else here</a>
							</div>
						</li>
						
						<li class="nav-item dropdown">
							<a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#" aria-expanded="false">
							 <c:choose>
							 	<c:when test="${admin.image == 'assets/images/troll.jpg' }">
							 		<img src="${admin.image}" alt="user-img" width="39" class="img">
							 	</c:when>
							 	<c:otherwise>
							 		<img src="${pageContext.request.contextPath}/assets/images/admin/${admin.image}" alt="user-img" width="39" class="img">
							 	</c:otherwise>
							 </c:choose>
							 	${admin.name}</span></span> 
							 </a>
							<ul class="dropdown-menu dropdown-user">
								<li>
									<div class="user-box">
										
										<c:choose>
										 	<c:when test="${admin.image == 'assets/images/troll.jpg' }">
										 		<img src="${admin.image}" alt="user-img" width="39" class="img">
										 	</c:when>
										 	<c:otherwise>
										 		<img src="${pageContext.request.contextPath}/assets/images/admin/${admin.image}" alt="user-img" width="39" class="img">
										 	</c:otherwise>
										 </c:choose>
	                                	
										<div class="u-text">
											<h4 class="">${admin.name}</h4>
											<p class="text-muted">${admin.email}</p><a href="${pageContext.request.contextPath}/AdminController?page=profile&admin_id=${admin.id}" class="btn btn-rounded btn-danger btn-sm">View Profile</a></div>
										</div>
									</li>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="${pageContext.request.contextPath}/MessageController?page=messagePage"><i class="las la-envelope"></i> Inbox</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/LoginController?page=adminLogout"><i class="las la-power-off "></i> Logout</a>
								</ul>
								<!-- /.dropdown-user -->
							</li>
						</ul>
					</div>
				</nav>
			</div>