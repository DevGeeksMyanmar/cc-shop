<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>CC Dashboard</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/ready.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/demo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css" integrity="sha512-vebUliqxrVkBy3gucMhClmyQP9On/HAWQdKDXRaAlb/FKuTbxkjPKUyqVOxAcGwFDka79eTF+YXwfke1h3/wfg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<style>

	.card{
		width: 500px !important;
	}
</style>

<body class="bg-light">
	<div class="wrapper ">
	
							<c:if test="${not empty error}">
								<div class="alert alert-danger text-center" role="alert" id="errorAlert">
								${error}
								</div>
							</c:if>

        <div class="row d-flex align-items-center justify-content-center mt-5">
        	
			<div class="col-md-6 flex-column d-flex align-items-center justify-content-center mt-4">
			
				<div class="card shadow rounded">
					<div class="card-header">
						<h5  class="text-primary fw-bold"><i class="las la-map"></i> Shipping Address</h5>
					</div>
					<div class="card-body">
						<h6>${address.street_address}, ${address.state}, ${address.city}, ${address.country}</h6>
						<span class="text-secondary fw-bold">Postal Code : ${address.postal_code}</span> <br>
						<span class="text-secondary fw-bold">Date : ${orders[0].updated_at}</span>
					</div>
				</div>
				
				<!-- notes -->
				<div class="card shadow rounded">
					<div class="card-header">
						<h5  class="w-bold text-primary"><i class="las la-file"></i> Note</h5>
					</div>
					<c:choose>
						<c:when test="${note != null }">
							<div class="card-body">
								<p>${note.text }</p>
							</div>
						</c:when>
						<c:otherwise>
							<div class="card-body">
								<p>No note for this order</p>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				
				<a class="btn btn-primary " href="${pageContext.request.contextPath}/SellerController?page=order&seller_id=${seller.id}">Understand</a>
			</div>
			
			<div class="row d-flex align-items-center justify-content-start ">
			
				<div class="card shadow rounded">
					<div class="card-header">
						<h5 class="text-muted fw-bold"> <i class="las la-box"></i> Order Detail</h5>
						<div class="d-flex align-items-center justify-content-between mb-2">
							Name: <span class="fw-bold text-secondary">${orders[0].customer_name}</span>
						</div>
						<div class="d-flex align-items-center justify-content-between mb-2">
							Seller: <span class="fw-bold text-secondary">${seller.name}</span>
						</div>
						<div class="d-flex align-items-center justify-content-between ">
							Order Code:  <span class="fw-bold text-info">${orders[0].order_code}</span>
						</div>
					</div>
					<div class="card-body">
						
						<c:forEach items="${orders}" var="order">
							<div class="d-flex align-items-center justify-content-between mb-3">
								<div>
									<span class="fs-2 fw-bold text-secondary">${order.product_name}</span> <span class="text-danger fw-bold"> x${order.count} (${order.price}) </span>
								</div>
								<div>
									<span class="fw-bold text-primary">${order.count * order.price} MMKs</span> 
								</div>
							</div>
						</c:forEach>
						<hr>
						<div class="d-flex align-items-center justify-content-between mb-3">
							<div>
								<h5 class="fs-2 fw-bold text-secondary">Total Price</h5>
							</div>
							<div>
								<h5 class="fw-bold text-primary">${total} MMKs</h5> 
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
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/core/jquery.3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/chartist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/chartist/plugin/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-mapael/jquery.mapael.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-mapael/maps/world_countries.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/chart-circle/circles.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/ready.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/demo.js"></script>
</html>