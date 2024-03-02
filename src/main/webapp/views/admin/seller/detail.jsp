<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>

<%@ include file="../layout/header.jsp" %>



	<div class="wrapper">
	
	
		<!-- #### Main header #### -->
		<%@ include file="../layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="../layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
			</div>

			<div class="main-panel">
				<div class="content">
					<div class="container-fluid">
						<h4 class="page-title">  Seller - ${seller.name}</h4>

                        <!-- Content goes here  -->
                        <div class="row">
	                        <div class="col-12 col-md-5 offset-md-3">
	                            	<div class="card shadow">
	                            		<div class="card-header">
	                            			<h6 class="text-center fw-semibold"> Seller Detail</h6>
	                            		</div>
	                            		<div class="card-body text-center">
	                            			<div class="row mb-3 px-2 d-flex align-items-center justify-content-center">
	                            				<div class="mx-1"></div>
	                            				<div class="col-4 border rounded p-2">
	                            					<h6>Company</h6>
	                            					<span>${seller.company}</span>
	                            				</div>
	                            				<div class="mx-1"></div>
	                            				<div class="col-4 border rounded p-2">
	                            					<h6>Business</h6>
	                            					<span>${seller.bname}</span>
	                            				</div>
	                            				<div class="mx-1"></div>
	                            				<div class="col-4 border rounded p-2 mt-2">
	                            					<h6>Product Count</h6>
	                            					<c:if test="${empty product}">
	                            						<span>No product available</span>
	                            					</c:if>
	                            					<c:if test="${not empty product}">
	                            						<span>${product.size()}</span>
	                            					</c:if>
	                            				</div>
	                            				<div class="mx-1"></div>
	                            				<div class="col-4 border rounded p-2 mt-2">
	                            					<h6>Rating</h6>
	                            					<c:if test="${empty product}">
	                            						<span>Not reviewed</span>
	                            					</c:if>
	                            					<c:if test="${not empty product}">
	                            						<span>${product[0].rating}</span>
	                            					</c:if>
	                            				</div>
	                            			</div>
	                            			<div class="row">
	                            				<div class="col-12 text-center">
	                            					<c:if test="${empty seller.image}">
	                            						<img src="assets/images/troll.jpg" width="130px" class="border rounded p-2">
	                            					</c:if>
	                            					<c:if test="${not empty seller.image }">
	                            						<img src="${pageContext.request.contextPath}/assets/images/seller/${seller.image}" width="130px" class="border rounded p-2">
	                            					</c:if>
	                            					<br>
	                            					<br>
	                            					Name : <h6 style="margin-bottom: 15px;">${seller.name}</h6>
			                            			Email : <h6 style="margin-bottom: 15px;">${seller.email}</h6>
			                            			Phone : <h6 style="margin-bottom: 15px;">${seller.phone}</h6>
			                            			Address : <h6 style="margin-bottom: 15px;">${seller.address}</h6>
	                            				</div>
	                            			</div>
	                            			<a href="${pageContext.request.contextPath}/AdminController?page=seller" class="btn btn-primary text-light">Ok</a>
                            			</div> 
                            	</div>
                            </div>
                        </div>

					</div>
				</div>
				
			</div>
			
		</div>
	</div>

</body>



<%@ include file="../layout/footer.jsp" %>




