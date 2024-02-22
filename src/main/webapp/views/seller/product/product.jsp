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
					<h4 class="page-title col-md-6 col-6">Product ( ${product_count} ) </h4>
                	<div class="col-md-6 col-6">
                		<a href="${pageContext.request.contextPath}/ProductController?page=createProductPage&seller_id=${seller.id}" type="button" class="btn btn-primary text-white pull-right"> Add New Product</a>
                	</div>
				</div>
		        <div class="row">
		           <div class="col-md-12">
		           
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
							
									<nav aria-label="Page navigation example">
										  <ul class="pagination">
										    <c:if test="${currentPage != 1}">
										        <li class="page-item"><a href="${pageContext.request.contextPath}/SellerController?page=product&page_number=${currentPage - 1}&seller_id=${seller.id}" class="page-link">Previous</a></li>
											</c:if> 
										    <c:forEach begin="1" end="${noOfPages}" var="i"> 
								              <c:choose> 
								                  <c:when test="${currentPage eq i}"> 
								                      <li class="page-item"><a class="page-link bg-primary text-light" href="${pageContext.request.contextPath}/SellerController?page=product&page_number=${i}&seller_id=${seller.id}">${i}</a></td> 
								                  </c:when> 
								                  <c:otherwise> 
								                      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/SellerController?page=product&page_number=${i}&seller_id=${seller.id}">${i}</a></td> 
								                  </c:otherwise> 
								              </c:choose> 
								          </c:forEach> 
										    <c:if test="${currentPage lt noOfPages}">
										        <li class="page-item"><a href="${pageContext.request.contextPath}/SellerController?page=product&page_number=${currentPage + 1}&seller_id=${seller.id}" class="page-link">Next</a></td>
										    </c:if>
										  </ul>
										</nav> 
									
								<c:forEach items="${products}" var="product">
		                         <div class="card card-body mt-3">
		                            <div class="media align-items-center align-items-lg-start text-center text-lg-left flex-column flex-lg-row">
		                                <div class="mr-2 mb-3 mb-lg-0">
		                                    
		                                        <img src="${pageContext.request.contextPath}/assets/images/products/${product.image}" width="150" height="150" alt="">
		                                   
		                                </div>
		
		                                <div class="media-body p-3">
		                                    <h6 class="media-title font-weight-semibold">
		                                        <a href="#" data-abc="true" class="">${product.name}</a>
		                                    </h6>
		
		                                    <ul class="list-inline list-inline-dotted mb-3 mb-lg-2">
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">${product.category_name}</a></li>
		                                    </ul>
		
		                                    <p class="mb-3">
											    <c:choose>
											        <c:when test="${product.description.length() > 200}">
											            ${product.description.substring(0, 200)} ...
											        </c:when>
											        <c:otherwise>
											            ${product.description}
											        </c:otherwise>
											    </c:choose>
											</p>
		
		                                </div>
		                                <div class="mt-3 mt-lg-0 ml-lg-3 text-center">
		                                	<span class="mt-2">Number of Product: ${product.count}</span>
		                                    <h4 class="mb-0 font-weight-semibold mt-2">${product.price} MMK</h4>	
		                                    <a href="${pageContext.request.contextPath}/ProductController?page=detail&product_id=${product.id}&seller_id=${seller.id}" type="button" class="btn btn-info mt-4 text-white"> <i class="las la-exclamation-circle"></i></a>
		                                    <a href="${pageContext.request.contextPath}/ProductController?page=edit&product_id=${product.id}" class="btn btn-primary mt-4 text-white "> <i class="las la-edit"></i>  </a>
		                                    <a href="${pageContext.request.contextPath}/ProductController?page=destory&product_id=${product.id}&seller_id=${seller.id}" type="button" class="btn btn-danger mt-4 text-white"> <i class="las la-trash"></i> </a>
		                                </div>
		                            </div>
		                        </div>
		                     </c:forEach>
		                      
		                      		             
		                             
		        	</div>                     
		        </div>
    		</div>   
                        
			</div>
						
		</div>
				
	</div>

<%@ include file="/views/seller/layout/footer.jsp"%>