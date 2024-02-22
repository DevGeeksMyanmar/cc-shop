<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ include file="/views/user/layout/header.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>


<style>
    .grid-container {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
      
      gap: 20px;
    }
    .grid-item {
      background-color: #f0f0f0;
      padding: 20px;
      border: 1px solid #ccc;
    }
  </style>


 <!-- SECTION NEW elect-->
		<div class="section search-section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="grid-container" >

							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->									
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


		
		<!-- SECTION NEW elect-->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
				
				
					<c:if test="${products.size() == 0}">
						<h3>No product avilable!</h3>
					</c:if>
				
					<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<span class="store-qty">Showing ${products.size()} Products</span>
							<ul class="store-pagination">
								<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=main&page_number=${currentPage - 1}"><i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach begin="1" end="${noOfPages}" var="i"> 
									<c:choose> 
								       <c:when test="${currentPage eq i}"> 
								       		<li class="active" ><a style="color: white !important;" class="text-light" href="${pageContext.request.contextPath}/UserController?page=main&page_number=${i}">${i}</a></li>
								       </c:when>
								       <c:otherwise> 
								       		<li class=""><a href="${pageContext.request.contextPath}/UserController?page=main&page_number=${i}">${i}</a></li>
								       </c:otherwise>
								    </c:choose>
								</c:forEach>
								<c:if test="${currentPage lt noOfPages}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=main&page_number=${currentPage + 1}"><i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</div>
						<!-- /store bottom filter -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="grid-container" >
						
							<c:forEach items="${products}" var="product">
									<div class="grid-item" >
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="${pageContext.request.contextPath}/assets/images/products/${product.image}" alt="">
											</div>
											<div class="product-body">
												<p class="product-category">${product.category_name}</p>
												<p class="product-category" style="font-weight: bold;">Seller: ${product.seller_name}</p>
												<h3 class="product-name"><a href="#">${product.name}</a></h3>
												<h4 class="product-price">${product.price}MMKs</h4>
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="quick-view"><a class="" href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=${product.id}"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></a></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
											</div>
										</div>
										<!-- /product -->
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->									
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->



<%@ include file="/views/user/layout/footer.jsp" %>