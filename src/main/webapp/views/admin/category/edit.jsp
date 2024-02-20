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
						<h4 class="page-title">  Edit Categorys</h4>

                        <!-- Content goes here  -->
                        <div class="row">
	                        <div class="col-12 col-md-5 offset-md-3">
	                            	<div class="card shadow">
	                            		<div class="card-header">
	                            			<h6 class="text-center fw-semibold"> <i class="las la-edit"></i> Edit Category</h6>
	                            		</div>
	                            		<div class="card-body">
	                            			<form method="post" action="${pageContext.request.contextPath}/CategoryController?action=updateCategory">
	                            				<div class="form-group">
	                            				<input type="hidden" name="action" value="saveCategory">
                            					<label class="form-label" for="name">Name</label>
                            					<input type="hidden" name="category_id" value="${category.id}">
                            					<input type="text" placeholder="Enter category name" name="name" value="${category.name}" class="form-control mb-4" required>
                            					<a href="${pageContext.request.contextPath}/AdminController?page=category" class="btn btn-secondary"> <i class="las la-arrow-left"></i> Cancel</a>
                            					<button class="btn btn-primary"> <i class="las la-save"></i> Update Category</button>
                            				</div>
                            			</form>
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




