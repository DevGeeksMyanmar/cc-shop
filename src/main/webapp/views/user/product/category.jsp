<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="Models.Whistlist" %>
<%@ page import="DAO.WhistlistDAO" %>


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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

 <!-- SECTION NEW elect-->
		<div class="section search-section">
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
				<!-- alert -->
			
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
													
													<c:set var="product_id" value="${product.id}" />
												<c:set var="customer_id" value="${customer.id}" />
												
												<%
												    WhistlistDAO whistlistDAO = new WhistlistDAO();
												    int whist_id = whistlistDAO.get_by_customer_id_and_product_id((int) pageContext.getAttribute("product_id"), (int) pageContext.getAttribute("customer_id"));
												%>
												
												<% if (whist_id != 0) { %>
												    <button class="add-to-wishlist">
												        <a id="remove-from-wishlist-btn" class="" href="${pageContext.request.contextPath}/WhistlistController?action=removeFromWhistList&product_id=${product.id}&category_id=${param.category_id}&user_id=${customer.id}&wish_id=<%= whist_id %>&where=category">
												            <i class="fa fa-heart text-danger"></i><span class="tooltipp">Remove</span>
												        </a>
												    </button>
												<% } else { %>
												    <button class="add-to-wishlist">
												        <a id="add-to-wishlist-btn" class="" href="${pageContext.request.contextPath}/WhistlistController?action=addToWhistList&product_id=${product.id}&category_id=${param.category_id}&user_id=${customer.id}&where=category">
												            <i class="fa-regular fa-heart"></i><span class="tooltipp">Add to wishlist</span>
												        </a>
												    </button>
												<% } %>

													
													
													<button class="quick-view"><a class="" href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=${product.id}"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></a></button>
												</div>
											</div>
											<div class="add-to-cart">
												<a href="${pageContext.request.contextPath}/CartController?action=addToCart&user_id=${customer.id}&product_id=${product.id}&category_id=${category_id}">
													<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>			
												</a>
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
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script>
		    // Wait for the document to be ready
		    $(document).ready(function() {
		        // Find the error alert element
		        var $errorAlert = $('#errorAlert');
		        
		        // If the alert element exists
		        if ($errorAlert.length) {
		            // Set a timeout to hide the alert after 3 seconds
		            setTimeout(function() {
		                // Fade out the alert over 0.5 seconds
		                $errorAlert.fadeOut(500);
		            }, 3000); // 3000 milliseconds = 3 seconds
		        }
		    });
		</script>



<%@ include file="/views/user/layout/footer.jsp" %>