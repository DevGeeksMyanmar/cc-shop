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
			<!-- content -->
			<section class="py-5">
			  <div class="container">
			    <div class="row gx-5">
			      <aside class="col-lg-6">
			        <div class="border rounded-4 mb-3 d-flex justify-content-center">
					    <a id="mainImageLink" data-fslightbox="mygalley" class="rounded-4" data-type="image" href="">
					        <img id="mainImage" style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit" src="${pageContext.request.contextPath}/assets/images/products/${images[0].name}" />
					    </a>
					</div>
					<div class="d-flex justify-content-center mb-3">
					    <c:forEach items="${images}" var="image" varStatus="loop">
					        <a data-fslightbox="mygalley" class="border mx-1 rounded-2" data-type="image" href="#" class="item-thumb" onclick="changeMainImage('${image.name}', event)">
					            <img width="60" height="60" class="rounded-2" src="${pageContext.request.contextPath}/assets/images/products/${image.name}" />
					        </a>
					    </c:forEach>
					</div>
			        <!-- thumbs-wrap.// -->
			        <!-- gallery-wrap .end// -->
			      </aside>
			      <main class="col-lg-6">
			        <div class="ps-lg-3">
			          <h4 class="title text-dark">
			            ${product.name}
			          </h4>
			          </div>
			
			          <div class="mb-3">
			            <span class="h5">${product.price} MMKs</span>
			          </div>
			
			          <p>
			            ${product.description }
			          </p>
			
			          <div class="row">
			          
			            <dt class="col-3">Cateogry:</dt>
			            <dd class="col-9">${product.category_name}</dd>
			            
			            <dt class="col-3">Stock:</dt>
			            <dd class="col-9">
			            	<c:if test="${product.count > 0 }">
			            		<span class="bg-primary text-light px-2 py-1 rounded">In Stock - ${product.count}</span>
			            	</c:if>
			            	<c:if test="${product.count < 0 }">
			            		<span class="bg-primary text-light px-2 py-1 rounded">Out of Stock</span>
			            	</c:if>
			            </dd>
			            
			          </div>
			
			          <hr />
			          	<a href="${pageContext.request.contextPath}/ProductController?page=edit&product_id=${product.id}&seller_id=${seller.id}" class="btn btn-warning border border-secondary py-2 "> Edit </a>
			          	<a href="${pageContext.request.contextPath}/SellerController?page=product&seller_id=${seller.id}" class="btn btn-primary border border-secondary py-2 "> Ok </a>
			          </div>

			        </div>
			      </main>
			    </div>
			  </div>
			</section>
			<!-- content -->

		</div>
	</div>
	
	<script>
	    function changeMainImage(imageName, event) {
	        // Prevent the default behavior of the anchor tag
	        event.preventDefault();
	
	        var mainImageLink = document.getElementById('mainImageLink');
	        var mainImage = document.getElementById('mainImage');
	        var newImageUrl = "${pageContext.request.contextPath}/assets/images/products/" + imageName;
	
	        // Update the href of the main image link
	        mainImageLink.setAttribute('href', newImageUrl);
	
	        // Update the src of the main image
	        mainImage.setAttribute('src', newImageUrl);
	    }
	</script>

	
<%@ include file="/views/seller/layout/footer.jsp"%>