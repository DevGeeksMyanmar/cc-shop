

<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>


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
    .seller-img-wrapper{
    	width: 100%;
    	height: auto;
    	display: flex;
    	align-items: center;
    	justify-content: center;
    }
    .seller-image{
    	width: 120px;
    	height: 120px;
    	border: 1px solid #777;
    	border-radius: 50%;
    	padding: .5rem;
    }
    .profile-summary{
    	display: flex;
    	align-items: center;
    	justify-content: space-around;
    	margin-top: 20px;
    }
    .profile-summary h5, .title{
    	color: #D10024;
    }
  </style>
		
		<!-- SECTION NEW elect-->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
				
					<a href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=${product_id}" style="margin-bottom: 1rem;">Back</a>
					<h3 class="title" style="margin-top: 1rem !important;">Seller Information</h3>

					<!-- Seller profile -->	
					<div class="col-md-5 order-details" style="margin-top: 4rem !important; margin-right: 5rem !important;">
						<div class="section-title text-center">
							<h3 class="title">Profile</h3>
						</div>
						<div class="seller-img-wrapper">
							<img class="seller-image" src="${seller.image}" >
						</div>
						<div class="profile-summary">							
							<div class="col-4">
								<h5 class="" style="text-align: center;">Company</h5>
								<h6 style="text-align: center;">${seller.company}</h6>
							</div>
							<div class="col-4">
								<h5 class="" style="text-align: center;">Avaliable Product</h5>
								<h6 style="text-align: center;">${product_count}</h6>
							</div>
							<div class="col-4">
								<h5 class="" style="text-align: center;">Rating</h5>
								<h6 style="text-align: center;">
									<c:if test="${seller.rating == 0}">Not Reviewed</c:if>
									<c:if test="${seller.rating > 0}">${seller.rating}</c:if>
								</h6>
							</div>
						</div>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>Content</strong></div>
								<div><strong></strong></div>
							</div>
							<div class="order-products">
								<div class="order-col">
									<div>Name</div>
									<div>${seller.name}</div>
								</div>
								<div class="order-col">
									<div>Email</div>
									<div>${seller.email}</div>
								</div>
								<div class="order-col">
									<div>Phone</div>
									<div>${seller.phone}</div>
								</div>
								<div class="order-col">
									<div>Address</div>
									<div>${seller.address}</div>
								</div>
							</div>
						</div>
					</div>
					
					<!-- /seller profile -->
					

					<!-- Products tab & slick -->
					<div class="col-md-6">
					
					<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<span class="store-qty">Showing ${products.size()} Products</span>
							<ul class="store-pagination">
								<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=seller&seller_id=${products[0].seller_id}&product_id=${product_id}&page_number=${currentPage - 1}"><i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach begin="1" end="${noOfPages}" var="i"> 
									<c:choose> 
								       <c:when test="${currentPage eq i}"> 
								       		<li class="active" ><a style="color: white !important;" class="text-light" href="${pageContext.request.contextPath}/UserController?page=seller&seller_id=${products[0].seller_id}&product_id=${product_id}&page_number=${i}">${i}</a></li>
								       </c:when>
								       <c:otherwise> 
								       		<li class=""><a href="${pageContext.request.contextPath}/UserController?page=seller&seller_id=${products[0].seller_id}&product_id=${product_id}&page_number=${i}">${i}</a></li>
								       </c:otherwise>
								    </c:choose>
								</c:forEach>
								<c:if test="${currentPage lt noOfPages}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=seller&seller_id=${products[0].seller_id}&product_id=${product_id}&page_number=${currentPage + 1}"><i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</div>
						<!-- /store bottom filter -->
					
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