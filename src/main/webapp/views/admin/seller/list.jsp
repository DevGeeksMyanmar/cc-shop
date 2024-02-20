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
						<h4 class="page-title">   Sellers</h4>
						
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
                            <div class="col-12">
                                <div class="card card-tasks">
									<div class="card-header ">
										<h4 class="card-title"> <i class="las la-user-tie"></i> List</h4>
										<p class="card-category">  You can see who are using your platform.</p>
									</div>
									<div class="card-body sellerTable">
										<div class="table-full-width">
											<table class="table ">
                                                <!-- ### Table head ####  -->
												<thead>
													<tr>
														<th>
															<div class="form-check">
																<label class="form-check-label">
																	<input class="form-check-input  select-all-checkbox" type="checkbox" data-select="checkbox" data-target=".task-select">
																	<span class="form-check-sign"></span>
																</label>
															</div>
														</th>
														<th>Name</th>
														<th>Email</th>
														<th>Phone</th>
														<th>Address</th>
														<th>Action</th>
													</tr>
												</thead>
                                                <!-- #### end of table head ###  -->
												<tbody>
                                                    <!-- #### Table Body Start #####  -->
                                                    <c:if test="${sellerList != null}">
	                                                    <c:forEach items="${sellerList}" var="seller">
														<tr>
															<td>
																<div class="form-check">
																	<label class="form-check-label">
																		<input class="form-check-input task-select" type="checkbox">
																		<span class="form-check-sign"></span>
																	</label>
																</div>
															</td>
															<td>${seller.name}</td>
															<td>${seller.email}</td>
															<td>${seller.phone}</td>
															<td>${seller.address}</td>
															<td class="td-actions">
																<div class="form-button-action">
																	<a href="${pageContext.request.contextPath}/AdminController?action=sellerDetail&seller_id=${seller.id}">
																	<button type="button" data-toggle="tooltip" title="See Detail" class="btn btn-link btn-simple-primary">
																		<i class="las la-eye"></i>
																	</button>
																	</a>
																	<a href="${pageContext.request.contextPath}/AdminController?action=deleteSeller&seller_id=${seller.id}">
																	<button type="button" data-toggle="tooltip" title="Remove" class="btn btn-link btn-simple-danger">
																		<i class="las la-times"></i>
																	</button>
																	</a>
																</div>
															</td>
														</tr>
														</c:forEach>
													</c:if>
													<c:if test="${sellerList == null}">
												        <p class="text-danger fw-semibold fs-5 text-center">No seller here.</p>
												    </c:if>
													<!-- #### Table body ####  -->
												</tbody>
											</table>
										</div>
										<nav aria-label="Page navigation example">
										  <ul class="pagination">
										    <c:if test="${currentPage != 1}">
										        <li class="page-item"><a href="${pageContext.request.contextPath}/AdminController?page=sellerpage_number=${currentPage - 1}" class="page-link">Previous</a></li>
											</c:if> 
										    <c:forEach begin="1" end="${noOfPages}" var="i"> 
								              <c:choose> 
								                  <c:when test="${currentPage eq i}"> 
								                      <li class="page-item"><a class="page-link bg-primary text-light" href="${pageContext.request.contextPath}/AdminController?page=seller?page=business&page_number=${i}">${i}</a></td> 
								                  </c:when> 
								                  <c:otherwise> 
								                      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/AdminController?page=seller&page_number=${i}">${i}</a></td> 
								                  </c:otherwise> 
								              </c:choose> 
								          </c:forEach> 
										    <c:if test="${currentPage lt noOfPages}">
										        <li class="page-item"><a href="${pageContext.request.contextPath}/AdminController?page=seller&page_number=${currentPage + 1}" class="page-link">Next</a></td>
										    </c:if>
										  </ul>
										</nav>
									</div>
									
								</div>
                            </div>
                        </div>

					</div>
				</div>
				
			</div>
			
		</div>
	</div>

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




