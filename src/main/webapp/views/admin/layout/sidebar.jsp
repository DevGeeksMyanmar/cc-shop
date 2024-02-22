<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <div class="sidebar">
				<div class="scrollbar-inner sidebar-wrapper">
					<div class="user">
						<div class="photo">
									<c:if test="${admin.image == 'assets/images/troll.jpg'}">
                                		<img class="<c:if test='${admin.image != "assets/images/troll.jpg"}'> d-none </c:if>" src="${admin.image}">
                                	</c:if>
                                	<c:if test="${admin.image != null}">
                                		<img class="<c:if test='${admin.image == "assets/images/troll.jpg"}'> d-none </c:if>" src="${pageContext.request.contextPath}/assets/images/admin/${admin.image}" class="rounded border p-2" style="width: 120px !important; height: 100px !important;">
                                	</c:if>
						</div>
						<div class="info">
							<a class="" data-toggle="collapse" href="#collapseExample" aria-expanded="true">
								<span>
									${admin.name}
									<span class="user-level ">Administrator</span>
								</span>
							</a>
							<div class="clearfix"></div>

						</div>
					</div>
					<ul class="nav">
						<li class="nav-item <c:if test="${request.getRequestURI() eq 'http://localhost:8080/shop-dot-com/views/admin/dashboard.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=dashboard">
								<i class="la la-dashboard"></i>
								<p>Dashboard</p>
							</a>
						</li>
						<li class="nav-item <c:if test="${request.getRequestURI() eq '/AdminController?page=user'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=user">
								<i class="la la-user"></i>
								<p>Users</p>
								<span class="badge badge-count">${counts.user_count}</span>
							</a>
						</li>
                        <li class="nav-item <c:if test="${request.getRequestURI() eq '/shop-dot-com/views/admin/seller/list.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=seller">
								<i class="las la-user-tie"></i>
								<p>Sellers</p>
								<span class="badge badge-count">${counts.seller_count}</span>
							</a>
						</li>
                        <li class="nav-item <c:if test="${request.getRequestURI() eq '/shop-dot-com/views/admin/store/list.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=store">
								<i class="las la-store"></i>
								<p>Store</p>
								<span class="badge badge-count">${counts.store_count}</span>
							</a>
						</li>
                        <li class="nav-item <c:if test="${request.getRequestURI() eq '/shop-dot-com/views/admin/product/list.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=product">
								<i class="las la-list"></i>
								<p>Products</p>
								<span class="badge badge-count">${counts.product_count}</span>
							</a>
						</li>
						<li class="nav-item <c:if test="${request.getRequestURI() eq '/shop-dot-com/views/admin/product/list.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=category">
								<i class="las la-clipboard-list"></i>
								<p>Category</p>
								<span class="badge badge-count">${counts.category_count}</span>
							</a>
						</li>
						<li class="nav-item <c:if test="${request.getRequestURI() eq '/shop-dot-com/views/admin/product/list.jsp'}">active</c:if>">
							<a href="${pageContext.request.contextPath}/AdminController?page=business">
								<i class="las la-building"></i>
								<p>Business</p>
								<span class="badge badge-count">${counts.business_count}</span>
							</a>
						</li>
						<li class="nva-item d-flex align-items-center justify-content-center">
							<img src="ccshop.png" width="150px" alt="">
						</li>
					</ul>
				</div>