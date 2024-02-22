




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
					<h4 class="page-title col-md-6 col-6"> Edit Product</h4>
					
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
                	
				</div>
		        <div class="row d-flex align-items-center justify-content-center">
		           <div class="col-12 col-md-5">
		           	<form action="${pageContext.request.contextPath}/ProductController" method="post" enctype="multipart/form-data">
		                <div class="card">
		                   <div class="card-body">
		                   		<input type="hidden" name="action" value="update">
		                   		<input type="hidden" name="product_id" value="${product.id}">
		                   		<input type="hidden" name="seller_id" value="${product.seller_id}">
		                   		<div class="form-group">
		                   			<label class="form-label" for="name">Product Name</label>
		                   			<input type="text" class="form-control" name="name" value="${product.name}">
		                   		</div>
		                   		
		                   		<div class="form-group">
		                   			<label class="form-label" for="category">Category</label>
		                   			<select class="form-control" name="category">
		                   				<option value="null">Choose Option</option>
		                   				<c:forEach items="${categories}" var="category">
		                   					<option value="${category.id}" <c:if test="${product.category_id == category.id}">selected</c:if> >${category.name}</option>
		                   				</c:forEach>
		                   			</select>
		                   		</div>
		                   		<div class="row">
		                   			<c:forEach items="${images}" var="image">
							        	<div class="col-6">
							        		<div class="card">
								        		<img src="${pageContext.request.contextPath}/assets/images/products/${image.name}" width="100px" class="card-img">
								        		<div class="card-footer">
					                        		<a href="${pageContext.request.contextPath}/ProductController?page=deleteImage&image_id=${image.id}&product_id=${product.id}" class="remove-image btn btn-danger text-light btn-sm"> <i class="las la-trash"></i> </a>
								        		</div>
								        	</div>
							        	</div>
							     	</c:forEach>
		                   		</div>
		                   		<div class="image-container">
							        <!-- Image boxes will be appended here -->
							   </div> 
							   <div class="form-group">
		                   			<div class="btn btn-primary add-image w-100">+ Image</div>
		                   		</div>
							   
		                   		<div class="form-group">
		                   			<label class="form-label" for="description">Product Description</label>
		                   			<textarea class="form-control" style="resize: none;" name="description" rows="4">${product.description}</textarea>
		                   		</div>
		                   		<div class="d-flex align-items-center justify-content-between">
		                   			<div class="form-group">
		                   				<label class="form-label" for="price">Price</label>
		                   				<input type="number" class="form-control" name="price" value="${product.price}">
		                   			</div>
		                   			<div class="form-group">
		                   				<label class="form-label" for="count">Number of Product</label>
		                   				<input type="number" class="form-control" name="count" value="${product.count}">
		                   			</div>
		                   		</div>
		                   		<div class="d-flex align-items-center justify-content-between">
		                   			<div class="form-group">
		                   				<a class="btn btn-secondary text-light" href="${pageContext.request.contextPath}/SellerController?page=product&seller_id=${seller.id}">Back</a>
		                   			</div>
		                   			<div class="form-group">
		                   				<button class="btn btn-primary" type="submit">Update</button>
		                   			</div>
		                   		</div>
		                   </div>            
		        		</div>  
		        		</form>                   
		           </div>
    			</div>         
			</div>			
		</div>		
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

	<script type="text/javascript">
	$(document).ready(function(){
	    $(".add-image").click(function(){
	        var imageBox = '<div class="image-box d-flex align-items-center justify-content-between my-2">' +
	                            '<div class="flex-grow-1 mr-2">' +
	                                '<input type="file" class="form-control" name="image" id="image">' +
	                            '</div>'+
	                            '<button class="remove-image btn btn-danger">Remove</button>' +
	                        '</div>';
	        $(".image-container").append(imageBox);
	    });

	    // Event listener for dynamically added remove-image buttons
	    $(".image-container").on("click", ".remove-image", function(){
	        $(this).closest(".image-box").remove(); // Remove the closest .image-box
	    });

	 // 
	});

    </script>
	
<%@ include file="/views/seller/layout/footer.jsp"%>