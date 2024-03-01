<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ page import="java.util.*" %>
    
    <%@ include file="../layout/header.jsp" %>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 

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

						<h4 class="page-title">This is Messages Page</h4>
						
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
						
						<c:forEach items="${messages}" var="mes">
                        <div class="card col-10">
                        
                        <div class="card-body"> 
                        
                        <div class="row">
                        
                        <div class="col-2 d-flex justify-content-center align-items-center"><i class=" fs-1 fa-solid fa-user mx-2"></i></div>
                        
                        <div class="col-8">
                        
                        <h3 class="fs-5 fw-bold card-title">${mes.name}</h3>
						
						<h5 class="card-title fs-6">${mes.email} | ${mes.phone}</h5>
						<hr class="text-danger">
						<h5 class="card-title fs-6">Message:</h5>
						
                        <p class="card-title fs-5">${mes.message}</p>  
                        
                        </div> 
                        
                        <div class="col-2"><h5 class="card-title fs-6">${mes.createdAt}</h5></div>
                        </div>
                        
                        <div class="d-flex justify-content-end gap-2">
                        	<button class="btn btn-primary">Send Message</button> 
                            <button class="btn btn-danger">Delete</button>  
                        </div>
                                      
                        </div>
                        </div>
                        </c:forEach>
                        
								<!--<table class="table">
								
								<thead class="thead-dark col-12">
								<tr>
								<th class="col-2">Name</th>
								<th class="col-2">Email</th>
								<th class="col-3">Phone</th>
								<th class="col-4">Message</th>
								</tr>
								</thead>
								
								<tbody>
								
								<tr>
								<td>${message.name}</td>
								<td>${message.email}</td>
								<td>${message.phone}</td>
								<td>${message.message}</td>
								</tr>					
								</tbody>
															
								</table>-->


						

					</div>
				</div>
				
			</div>




    <!-- Bootstrap JS (optional, for some features) -->
   <script>
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

</body>

<%@ include file="../layout/footer.jsp" %>

