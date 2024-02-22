<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>


		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="${pageContext.request.contextPath}/UserController?page=main">Back</a></li>
							<li class="active">${product.name }</li>
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
				<!-- row -->
				<div class="row">
					<!-- Product main img -->
					<div class="col-md-5 col-md-push-2">
						<div id="product-main-img">
							<c:forEach items="${images}" var="image">
								<div class="product-preview">
									<img src="${pageContext.request.contextPath}/assets/images/products/${image.name}" alt="">
								</div>
							</c:forEach>
						</div>
					</div>
					<!-- /Product main img -->

					<!-- Product thumb imgs -->
					<div class="col-md-2  col-md-pull-5">
						<div id="product-imgs">
							<c:forEach items="${images}" var="image">
								<div class="product-preview">
									<img src="${pageContext.request.contextPath}/assets/images/products/${image.name}" alt="">
								</div>
							</c:forEach>
						</div>
					</div>
					<!-- /Product thumb imgs -->

					<!-- Product details -->
					<div class="col-md-5">
						<div class="product-details">
							<h4 class="">Seller : <a class="" href="${pageContext.request.contextPath}/UserController?page=seller&seller_id=${product.seller_id}&product_id=${product.id}">${product.seller_name}</a> </h4>
							<h2 class="product-name">${product.name}</h2>
							
							<div>
								<h3 class="product-price">${product.price } MMKs</h3>
								<span class="product-available">
									<c:if test="${product.count > 0 }">In Stock (${product.count })</c:if>
									<c:if test="${product.count == 0 }">Out of Stock</c:if>
								</span>
							</div>
							<p>${product.description}</p>

							<div class="add-to-cart">
								<div class="qty-label">
									Qty
									<div class="input-number">
										<input type="number">
										<span class="qty-up">+</span>
										<span class="qty-down">-</span>
									</div>
								</div>
								<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
							</div>

							<ul class="product-btns">
								<li><a href="#"><i class="fa fa-heart-o"></i> add to wishlist</a></li>
							</ul>

							<ul class="product-links">
								<li>Category:</li>
								<li><a href="#">${product.category_name }</a></li>
							</ul>

							<ul class="product-links">
								<li>Share:</li>
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
								<li><a href="#"><i class="fa fa-envelope"></i></a></li>
							</ul>

						</div>
					</div>
					<!-- /Product details -->

					<!-- Product tab -->
					<div class="col-md-12">
						<div id="product-tab">
							<!-- product tab nav -->
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
							</ul>
							<!-- /product tab nav -->

							<!-- product tab content -->
							<div class="tab-content">
								<!-- tab1  -->
								<div id="tab1" class="tab-pane fade in active">
									<div class="row">
										<div class="col-md-12">
											<p>${product.description}</p>
										</div>
									</div>
								</div>
								<!-- /tab1  -->

								

							</div>
							<!-- /product tab content  -->
						</div>
					</div>
					<!-- /product tab -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		
		<!-- Section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-12">
						<div class="section-title text-center">
							<h3 class="title">Related Products</h3>
						</div>
					</div>

					<!-- product -->
					
						<c:forEach items="${related_products}" var="product">
						<div class="col-md-3 col-xs-6">
							<div class="product">
							<div class="product-img">
								<img src="${pageContext.request.contextPath}/assets/images/products/${product.image}" alt="">
							</div>
							<div class="product-body">
								<p class="product-category">${product.category_name}</p>
								<h3 class="product-name"><a href="#">${product.name}</a></h3>
								<h4 class="product-price">${product.price} MMKs</h4>

								<div class="product-btns">
									<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
									<button class="quick-view"><a class="" href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=${product.id}"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></a></button>
								</div>
							</div>
							<div class="add-to-cart">
								<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
							</div>
						</div>
						</div>
						</c:forEach>
					
					<!-- /product -->

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /Section -->


<%@ include file="/views/user/layout/footer.jsp" %>