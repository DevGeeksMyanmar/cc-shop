<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <div class="sidebar">
				<div class="scrollbar-inner sidebar-wrapper">
					<div class="user">
						<div class="photo">
						</div>
						<div class="info">
							<a class="" data-toggle="collapse" href="#collapseExample" aria-expanded="true">
								<span>
									<span class="user-level ">Seller</span>
								</span>
							</a>
							<div class="clearfix"></div>

						</div>
					</div>
					<ul class="nav">
						<li class="nav-item <c:if test="${request.getRequestURI() eq 'http://localhost:8080/shop-dot-com/views/seller/dashboard.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/views/seller/dashboard.jsp">
								<i class="la la-dashboard"></i>
								<p>Dashboard</p>
							</a>
						</li>
						<li class="nav-item <c:if test="${request.getRequestURI() eq '/SellerController?page=product'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/views/seller/product/product.jsp">
								<i class="la la-user"></i>
								<p>Products</p>
							</a>
						</li>
                        <li class="nav-item <c:if test = "${request.getRequestURL() eq '/SellerController?page=order' }">active</c:if>">
							<a href="${pageContext.request.contextPath}/views/seller/order/order.jsp">
								<i class="las la-user-tie"></i>
								<p>Order</p>
							</a>
						</li>
                        <li class="nav-item <c:if test = "${request.getRequestURL() eq '/SellerController?page=history' }">active</c:if>">
							<a href="${pageContext.request.contextPath}/views/seller/history/history.jsp">
								<i class="las la-store"></i>
								<p>History</p>
							</a>
						</li>
                        <li class="nav-item ">
							<a href="">
								<i class="las la-list"></i>
								<p>Setting</p>
							</a>
						</li>
					</ul>
				</div>