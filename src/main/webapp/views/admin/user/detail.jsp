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
						<h4 class="page-title">  User - ${user.name}</h4>

                        <!-- Content goes here  -->
                        <div class="row">
	                        <div class="col-12 col-md-5 offset-md-3">
	                            	<div class="card shadow">
	                            		<div class="card-header">
	                            			<h6 class="text-center fw-semibold"> User Detail</h6>
	                            		</div>
	                            		<div class="card-body">
	                            			<div class="row">
	                            				<div class="col-4 d-flex align-items-start justify-content-center">
	                            					<c:if test="${empty user.image}">
	                            						<img src="assets/images/troll.jpg" width="130px" class="border rounded p-2">
	                            					</c:if>
	                            					<c:if test="${not empty user.image }">
	                            						<img src="${user.image}" width="130px" class="border rounded p-2">
	                            					</c:if>
	                            				</div>
	                            				<div class="col-8">
	                            					Name : <h6 style="margin-bottom: 15px;">${user.name}</h6>
			                            			Email : <h6 style="margin-bottom: 15px;">${user.email}</h6>
			                            			Phone : <h6 style="margin-bottom: 15px;">${user.phone}</h6>
			                            			Address : <h6 style="margin-bottom: 15px;">${user.address}</h6>
	                            				</div>
	                            			</div>
	                            			<a href="${pageContext.request.contextPath}/AdminController?page=user" class="btn btn-primary text-light">Ok</a>
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




