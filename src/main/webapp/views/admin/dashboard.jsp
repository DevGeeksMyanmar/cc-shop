<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    
    <%@ include file="./layout/header.jsp" %>
    
    <!-- Loading bar -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/loading-bar/loading-bar.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/loading-bar/loading-bar.js"></script>
	
	<style>
		#loading-wrapper{
			position: absolute;
			top: 0;
			right: 0;
			width: 100%;
			height: 100vh;
			 /* Adjust the alpha value (0.5 in this case) to control transparency */
			z-index: 100;
			display: flex;
			overflow: hidden;
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
			display: none;
		}
	</style>


	<div class="wrapper">
	
		
						<!-- loading  -->
						<div id="loading-wrapper" class="d-none">
							<img src="${pageContext.request.contextPath}/assets/loading.gif" id="loading">
						</div>
						<!-- end of loading -->
	
	
		<!-- #### Main header #### -->
		<%@ include file="./layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="./layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
			</div>
			
			



			<div class="main-panel">
				<div class="content">
					<div class="container-fluid">

						<h4 class="page-title">Dashboard</h4>
						
						
						
						<c:if test="${not empty success }">
						<div class="alert alert-success text-center " role="alert" id="errorAlert">
							${success}
						</div>
						</c:if>
						<c:if test="${not empty error }">
						<div class="alert alert-error text-center " role="alert" id="errorAlert">
							${error}
						</div>
						</c:if>
						

                        <!-- Content goes here  -->
                        <div class="row">
                        
							<div class="col-md-3">
								<div class="card card-stats card-warning">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-users"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Customers</p>
													<h4 class="card-title">${counts.user_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card card-stats card-success">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-user-tie"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Seller</p>
													<h4 class="card-title">${counts.seller_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card card-stats card-danger">
									<div class="card-body">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="las la-boxes"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Products</p>
													<h4 class="card-title">${counts.product_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card card-stats card-primary">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-box"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Order</p>
													<h4 class="card-title">${counts.order_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							
						</div>
						
						<div class="row main-wrapper" style="margin-bottom: 1rem;">
							
						</div>
						
						
						<!-- Pending order section -->
						<div class="row">
						
							<div class="col-md-6">
								<div class="d-flex aling-items-center justify-content-between">
									<h5 class="fw-bold">Ready to Ship <i class="las la-track"></i></h5>
									
									
									<form method="GET" action="${pageContext.request.contextPath}/AdminController?page=dashboard&admin_id=${admin.id}" class="" style="width: 250px; display: flex;">
										<input type="hidden" name="page" value="dashboard">
										<input type="hidden" name="admin_id" value="${admin.id}">
										<select name="filter_value" class="form-control" style="height: 50px ">
											<option value="all" <c:if test="${param.filter_value == 'all'}">selected</c:if>>All</option>
											<option value="today" <c:if test="${param.filter_value == 'today'}">selected</c:if>>Today</option>
											<option value="yesterday"  <c:if test="${param.filter_value == 'yesterday'}">selected</c:if>>Yesterday</option>
											<option value="lastday"  <c:if test="${param.filter_value == 'lastday'}">selected</c:if>>Last Day</option>
										</select>
										<button type="submit" class="btn btn-primary "> Filter</button>
									</form>
									
									
								</div>
								
								<table class="table mt-3">
									<thead class="thead-dark">
										<th class="fw-bold">Order Code</th>
										<th class="fw-bold">Status</th>
										<th class="fw-bold">Date</th>
										<th class="fw-bold">Status</th>
									</thead>
									<tbody>
									
										<c:forEach items="${orders}" var="order">
											<tr>
												<td>
													<a class="fw-bold text-primary fs-5" href="">${order.order_code}</a>
												</td>
												<td class="text-secondary fw-bold">Ready to ship?</td>
												<td> ${order.updated_at}</td>
												<td>
													<a href="${pageContext.request.contextPath}/OrderController?page=shipOrder&order_code=${order.order_code}&product_id=${order.product_id}&admin_id=${param.admin_id}&filter_value=${param.filter_value}" id="ship-now" class="btn btn-success">Ship Now</a>
												</td>
											</tr>
										</c:forEach>
											<c:if test="${orders.size() == 0}">
												<h6 class="text-danger">No record!</h6>
											</c:if>
									</tbody>
								</table>
								
								<nav aria-label="Page navigation example">
								  <ul class="pagination">
								    <c:if test="${currentPage != 1}">
								        <li class="page-item"><a href="${pageContext.request.contextPath}/AdminController?page=dashboard&page_number=${currentPage - 1}&filter_value=${param.filter_value}" class="page-link">Previous</a></li>
									</c:if> 
								    <c:forEach begin="1" end="${noOfPages}" var="i"> 
									      <c:choose> 
									          <c:when test="${currentPage eq i}"> 
									              <li class="page-item"><a class="page-link bg-primary text-light" href="${pageContext.request.contextPath}/AdminController?page=dashboard&page_number=${i}&filter_value=${param.filter_value}">${i}</a></td> 
									          </c:when> 
									          <c:otherwise> 
									              <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/AdminController?page=dashboard&page_number=${i}&filter_value=${param.filter_value}">${i}</a></td> 
									          </c:otherwise> 
									     </c:choose> 
									 </c:forEach> 
								    <c:if test="${currentPage lt noOfPages}">
								        <li class="page-item"><a href="${pageContext.request.contextPath}/AdminController?page=dashboard&page_number=${currentPage + 1}&filter_value=${param.filter_value}" class="page-link">Next</a></td>
								    </c:if>
								  </ul>
								</nav>
								
							</div>
							
							
														
						</div>
						
										

					</div>
				</div>
				
			</div>




<script src="https://cdn.jsdelivr.net/npm/loading-bar/dist/loading-bar.min.js"></script>
    <!-- Bootstrap JS (optional, for some features) -->
   <script>
   document.getElementById("ship-now").addEventListener("click", function(event) {
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
	    
	    console.log("loading bar is ready");
	</script>

</body>

<%@ include file="./layout/footer.jsp" %>

