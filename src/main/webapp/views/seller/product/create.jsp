




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/views/seller/layout/header.jsp" %>

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
				<div class="row">
					<h4 class="page-title col-md-6 col-6"> Add Product</h4>
                	
				</div>
		        <div class="row d-flex align-items-center justify-content-center">
		           <div class="col-12 col-md-5">
		                <div class="card">
	            				<div class="card-body">
			                   		<div class="form-group">
			                   			<label class="form-label" for="name">Product Name</label>
			                   			<input type="text" class="form-control" name="name">
			                   		</div>
			                   		<div class="form-group">
			                   			<label class="form-label" for="name">Category</label>
			                   			<select class="form-control" name="category">
			                   				<option value="">Choose Option</option>
			                   				<c:forEach items="${categories}" var="category">
			                   					<option value="${category.id}">${category.name}</option>
			                   				</c:forEach>
			                   			</select>
			                   		</div>
			                   		<div class="form-group">
			                   			<label class="form-label" for="description">Product Description</label>
			                   			<textarea class="form-control" style="resize: none;" name="description" rows="4"></textarea>
			                   		</div>
			                   		<div class="d-flex align-items-center justify-content-between">
			                   			<div class="form-group">
			                   				<label class="form-label" for="price">Price</label>
			                   				<input type="number" class="form-control" name="price">
			                   			</div>
			                   			<div class="form-group">
			                   				<label class="form-label" for="count">Number of Product</label>
			                   				<input type="number" class="form-control" name="count">
			                   			</div>
			                   		</div>
			                   		<div class="d-flex align-items-center justify-content-between">
			                   			<div class="form-group">
			                   				<a class="btn btn-secondary text-light" href="">Back</a>
			                   			</div>
			                   			<div class="form-group">
			                   				<button class="btn btn-primary">Next</button>
			                   			</div>
			                   		</div>
			                   </div>
		        		</div>                     
		           </div>
    			</div>         
			</div>			
		</div>		
	</div>
	
<%@ include file="/views/seller/layout/footer.jsp"%>