</html><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	.main-wrapper select{
		width: 200px;
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


</style>


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
			

				
				
					<div class="row main-wrapper" style="margin-bottom: 1rem;">
						<div class="col-md-8">
							<h4 class="text-muted">Filter</h4>
							<form method="GET" action="${pageContext.request.contextPath}/UserController?page=history&user_id=${customer.id}" class="" style="width: 250px; display: flex;">
								<input type="hidden" name="page" value="history">
								<input type="hidden" name="user_id" value="${customer.id}">
								<div>
									<label class="form-label">Date</label>
									<select name="date" class="form-control">
										<option value="all" <c:if test="${param.date == 'all'}">selected</c:if>>All</option>
										<option value="recently"  <c:if test="${param.date == 'recently'}">selected</c:if>>Recently</option>
										<option value="past"  <c:if test="${param.date == 'past'}">selected</c:if>>Past</option>
									</select>
								</div>
								
								<div>
									<label class="form-label">Status</label>
									<select name="status" class="form-control">
										<option value="all" <c:if test="${param.status == 'all'}">selected</c:if>>All</option>
										<option value="-1"  <c:if test="${param.status == '-1'}">selected</c:if>>Canceled</option>
										<option value="2"  <c:if test="${param.status == '2'}">selected</c:if>>On the way</option>
									</select>
								</div>
								<div>
									<button type="submit" class="btn btn-primary" style="margin-top: 2.5rem;">Search</button>
								</div>
							</form>
						</div>
					</div>
				
				
			
				
				<div class="row main-wrapper">
					
				
					<div class="col-md-8">
					
						<c:if test="${orders.size() == 0}">
							<h4 style="margin-bottom: 10rem;">No History!</h4>
						</c:if>

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
										<c:when test="${order.status == 2}">
											<span class="text-success alert alert-success" style="color: green;">You package is on the way</span>
										</c:when>
										<c:when test="${order.status == -1}">
											<span class="text-danger alert alert-danger"  >You canceled the order!</span>
										</c:when>
										<c:when test="${order.status == -2}">
											<span class="text-warning alert alert-info">Seller canceled the order!</span>
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
									<li><a href="${pageContext.request.contextPath}/UserController?page=history&page_number=${currentPage - 1}&user_id=${customer.id}&date=${param.date}&status=${param.status}"><i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach begin="1" end="${noOfPages}" var="i"> 
									<c:choose> 
								       <c:when test="${currentPage eq i}"> 
								       		<li class="active" ><a style="color: white !important;" class="text-light" href="${pageContext.request.contextPath}/UserController?page=history&page_number=${i}&user_id=${customer.id}&date=${param.date}&status=${param.status}">${i}</a></li>
								       </c:when>
								       <c:otherwise> 
								       		<li class=""><a href="${pageContext.request.contextPath}/UserController?page=history&page_number=${i}&user_id=${customer.id}&date=${param.date}&status=${param.status}">${i}</a></li>
								       </c:otherwise>
								    </c:choose>
								</c:forEach>
								<c:if test="${currentPage lt noOfPages}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=history&page_number=${currentPage + 1}&user_id=${customer.id}&date=${param.date}&status=${param.status}"><i class="fa fa-angle-right"></i></a></li>
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

		
		
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<%@ include file="/views/user/layout/footer.jsp" %>
		
		

