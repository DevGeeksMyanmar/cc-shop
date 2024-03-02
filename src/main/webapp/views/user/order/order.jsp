<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.Duration" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>


<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>
<style>

	.main-wrapper{
		display: flex;
		align-items: start;
		justify-content: center;
	}
	.item-wrapper{
		display: flex;
		padding: 2rem !important;
		border: 1px solid #ccc;
		align-items: center;
		justify-content: start;
		margin-bottom: 1rem;
		border-radius: 6px;
		position: relative;
	}
	.item-wrapper .remove-btn{
		position: absolute;
		top:0;
		right: 0;
		background-color: #666;
		border: none;
		border-radius: 0 6px 0 0;
	}
	.item-wrapper .remove-btn:hover{
		background-color: #D10024 !important;
	}
	.product-desc{
		width: 100%;
		margin-left: 5rem;	
		display: flex;
		align-items: center;
		justify-content: space-between;
	}
	.product-desc .product-price{
		color: #D10024;
		font-weight: 500;
	}
	.checkout-btn{
		background-color: #D10024;
		padding: 2rem 2.5rem;
		color: #fff;
		border-radius: 4px;
		font-weight: 700;
	}
	.checkout-btn:hover{
		background-color: #fff;
		border: 2px solid #D10024;
	}
	.btn-wrapper{
		width: 100%;
		display: flex;
		align-items: center;
		justify-content: end;
	}
		#loading-wrapper{
			position: absolute;
			top: 0;
			right: 0;
			width: 100%;
			height: 100vh;
			overflow: hidden;
			 /* Adjust the alpha value (0.5 in this case) to control transparency */
			z-index: 100;
			display: flex;
			align-items: center;
			justify-content: center;
		}
		#loading{
			border: none;
			box-shadow: 1px 1px 5px #ccc;
			border-radius: 10px;
			z-index: 100;
			width: 60px;
		}
		.d-none{
			display: none !important;
		}

</style>

			<!-- loading  -->
						<div id="loading-wrapper" class="d-none">
							<img src="${pageContext.request.contextPath}/assets/loading.gif" id="loading">
						</div>
						<!-- end of loading -->
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="${pageContext.request.contextPath}/UserController?page=main">HOME</a></li>
							<li class="active">Order</li>
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
			
				<div class="row main-wrapper" style="margin-bottom: 1rem;">
					<div class="col-md-8">
						<form method="GET" action="${pageContext.request.contextPath}/UserController?page=order&user_id=${customer.id}" class="" style="width: 250px; display: flex;">
							<input type="hidden" name="page" value="order">
							<input type="hidden" name="user_id" value="${customer.id}">
							<select name="filter_value" class="form-control">
								<option value="all" <c:if test="${param.filter_value == 'all'}">selected</c:if>>All</option>
								<option value="0"  <c:if test="${param.filter_value == '0'}">selected</c:if>>Preparing</option>
								<option value="1"  <c:if test="${param.filter_value == '1'}">selected</c:if>>At warehouse</option>
							</select>
							<button type="submit" class="btn btn-primary"> Filter</button>
						</form>
					</div>
				</div>
			
				
				<div class="row main-wrapper">
					
				
					<div class="col-md-8">

						<c:forEach items="${orders}" var="order">
						<!-- items -->
						<div class="row item-wrapper">
							<!-- image -->
							<div class="col-2">
								<div class="product-img" >
									<img src="${pageContext.request.contextPath}/assets/images/products/${order.image_name}" width="100px">
								</div>
							</div>
							<!-- item detail -->
							<div class="col-10 product-desc">
								<div class="">
									<h6 class="text-primary">${order.order_code}</h6>
									<h4>${order.product_name }</h4>
									<span class="product-price">${order.price} MMKs</span> x<span>${order.count}</span>  <br>
									<c:choose>
										<c:when test="${order.status == 0}">
											<h6 class="text-warning">Your package is preparing</h6>
										</c:when>
										<c:when test="${order.status == 1}">
											<h6 class="text-success">Your package is at warehouse</h6>
										</c:when>
									</c:choose>
									<small>Date: ${order.updated_at}</small>
								</div>
								<div class="">
									<c:choose>
										<c:when test="${order.status == 0}">
											<a href="${pageContext.request.contextPath}/OrderController?page=orderCancel&product_id=${order.product_id}&order_code=${order.order_code}&user_id=${customer.id}&filter_value=${param.filter_value}" id="cancel-btn" class="btn btn-danger" href="">Cancel Order</a>
										</c:when>
										<c:when test="${order.status == 1}">
											<span class="text-success alert alert-success">Your package is at warehouse</span>
										</c:when>
									</c:choose>
								</div>
							</div>
						</div>
						<!-- end of items -->
						</c:forEach>
						
						<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<ul class="store-pagination">
								<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=order&page_number=${currentPage - 1}&user_id=${customer.id}&filter_value=${param.filter_value}"><i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach begin="1" end="${noOfPages}" var="i"> 
									<c:choose> 
								       <c:when test="${currentPage eq i}"> 
								       		<li class="active" ><a style="color: white !important;" class="text-light" href="${pageContext.request.contextPath}/UserController?page=order&page_number=${i}&user_id=${customer.id}&filter_value=${param.filter_value}">${i}</a></li>
								       </c:when>
								       <c:otherwise> 
								       		<li class=""><a href="${pageContext.request.contextPath}/UserController?page=order&page_number=${i}&user_id=${customer.id}&filter_value=${param.filter_value}">${i}</a></li>
								       </c:otherwise>
								    </c:choose>
								</c:forEach>
								<c:if test="${currentPage lt noOfPages}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=order&page_number=${currentPage + 1}&user_id=${customer.id}&filter_value=${param.filter_value}"><i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</div>
						<!-- /store bottom filter -->
						
					</div>
				</div>
				
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		<script>
		// click the button then loading start
		document.getElementById("cancel-btn").addEventListener("click", function(event) {
		       console.log("clicked");
				document.getElementById("loading-wrapper").classList.remove("d-none");
		   });
		
    // Wait for the document to be ready
    document.addEventListener('DOMContentLoaded', function() {
        // Find the success alert element
        var successAlert = document.getElementById('errorAlert');
        
        // If the alert element exists
        if (successAlert) {
            // Set a timeout to hide the alert after 3 seconds
            setTimeout(function() {
                successAlert.style.display = 'none'; // Hide the alert
            }, 3000); // 3000 milliseconds = 3 seconds
        }
	    });
	</script>
		
		
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<%@ include file="/views/user/layout/footer.jsp" %>
		
		

