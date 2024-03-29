<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/views/seller/layout/header.jsp" %>

<style>
	.main-panel{
		overflow: hidden;
		overflow-x: scroll;
	}
	.table{
		width: 100% !important;
		overflow: scroll !important;
	}
	select{
		width: 200px !important;
	}
</style>

	<div class="wrapper">
		<!-- #### Main header #### -->
		<%@ include file="/views/seller/layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="/views/seller/layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
	</div>
	<div class="main-panel">
		<div class="content">
			<div class="container-fluid">
				<h4 class="page-title">Orders</h4>
				
							<c:if test="${not empty error}">
								<div class="alert alert-danger" role="alert" id="errorAlert">
								 <i class="las la-exclamation-circle"></i> ${error}
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="alert alert-success" role="alert" id="errorAlert">
								 <i class="las la-exclamation-circle"></i> ${success}
								</div>
							</c:if> 
				
				
				<div class="row d-flex align-items-center justify-content-between">

						<nav aria-label="Page navigation example">
							<ul class="pagination">
							  <c:if test="${currentPage != 1}">
							      <li class="page-item"><a href="${pageContext.request.contextPath}/SellerController?page=history&page_number=${currentPage - 1}&seller_id=${seller.id}&filter=${param.filter}" class="page-link">Previous</a></li>
								</c:if> 
							  <c:forEach begin="1" end="${noOfPages}" var="i"> 
					    		    <c:choose> 
					    		        <c:when test="${currentPage eq i}"> 
								            <li class="page-item"><a class="page-link bg-primary text-light" href="${pageContext.request.contextPath}/SellerController?page=history&page_number=${i}&seller_id=${seller.id}&filter=${param.filter}">${i}</a></td> 
								        </c:when> 
						   		        <c:otherwise> 
								            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/SellerController?page=history&page_number=${i}&seller_id=${seller.id}&filter=${param.filter}">${i}</a></td> 
						 		        </c:otherwise> 
					    		    </c:choose> 
					   		 </c:forEach> 
						  <c:if test="${currentPage lt noOfPages}">
						      <li class="page-item"><a href="${pageContext.request.contextPath}/SellerController?page=history&page_number=${currentPage + 1}&seller_id=${seller.id}&filter=${param.filter}" class="page-link">Next</a></td>
						  </c:if>
						  </ul>
						</nav>

						<form class="d-flex align-items-center justify-content-center" action="${pageContext.request.contextPath}/SellerController?action=history" method="POST">
							<input type="hidden" value="${seller.id}" name="seller_id">
							<input type="hidden" value="history" name="action">
							<select class="form-control" name="filter">
								<option value="all" <c:if test="${param.filter == 'all'}">selected</c:if>>All</option>
								<option value="-1"  <c:if test="${param.filter == '-1'}">selected</c:if>>Canceled</option>
								<option value="-2"  <c:if test="${param.filter == '-2'}">selected</c:if>>Called off</option>
								<option value="1"  <c:if test="${param.filter == '1'}">selected</c:if>>Headquarter</option>
								<option value="2"  <c:if test="${param.filter == '2'}">selected</c:if>>On the way</option>
							</select>
							<button type="submit" class="btn btn-primary">Filter</button>
						</form>
						
						<span class="fw-bold">History Per page: ${orders.size()}</span>
				</div>
										
		        <div class="row">  
					<table class="table">
					  <thead class="bg-primary text-light" >
					    <tr>
					      <th scope="col" style="color: white" >Order Code</th>
					      <th scope="col" style="color: white">Customer Name</th>
					      <th scope="col" style="color: white">Product Name</th>
					      <th scope="col" style="color: white">Price</th>
					      <th scope="col" style="color: white">Qty</th>
					      <th scope="col" style="color: white">Total Price</th>
					      <th scope="col" style="color: white">Order Complete Date</th>
					      <th scope="col" style="color: white">Status</th>
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach items="${orders}" var="order">
					    	<tr>
						      <td><a href="${pageContext.request.contextPath}/OrderController?page=detailOfHistory&seller_id=${seller.id}&order_code=${order.order_code}&status=${order.status}" class="btn btn-link fw-bold">${order.order_code}</a></td>
						      <td>${order.customer_name}</td>
						      <td class="fw-bold text-danger" >${order.product_name}</td>
						      <td class="">${order.price} MMKs</td>
						      <td>${order.count} </td>
						      <td class="fw-bold text-primary">${order.count * order.price } MMKs</td>
						      <td>${order.updated_at}</td>
						      <td class="text-warning">
						      <c:choose>
						      	<c:when test="${order.status==-2}"><span class="text-warning fw-bold">Called off</span></c:when>
						      	<c:when test="${order.status==-1}"><span class="text-danger fw-bold">Canceled</span></c:when>
						      	<c:when test="${order.status==1}"><span class="text-primary fw-bold">At Headquarter</span></c:when>
						      	<c:when test="${order.status==2}"><span class="text-success fw-bold">On the way</span></c:when>
						      </c:choose>
						      </td>
						    </tr>
					    </c:forEach>
					  </tbody>
					</table>         
		        </div>
		        
		        
    		</div>             
		</div>			
	</div>
<%@ include file="/views/seller/layout/footer.jsp"%>