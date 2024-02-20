<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>Change Password</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/ready.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/demo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css" integrity="sha512-vebUliqxrVkBy3gucMhClmyQP9On/HAWQdKDXRaAlb/FKuTbxkjPKUyqVOxAcGwFDka79eTF+YXwfke1h3/wfg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<style>
	.form-wrapper{
		width: 100%;
		height: 100vh;
	}
	.card{
		width: 500px !important;
	}
</style>

<body>
	<div class="wrapper bg-light">
	
							<c:if test="${not empty error}">
								<div class="alert alert-danger text-center" role="alert" id="errorAlert">
								${error}
								</div>
							</c:if>

        <div class="row ">
			<div class="col-12 d-flex align-items-center justify-content-center form-wrapper">
				<div class="card shadow rounded">
					<div class="card-header">
						<h5 class="text-muted fw-bold"> <i class="las la-key"></i> Change Password</h5>
					</div>
					<div class="card-body">
						<form action="${pageContext.request.contextPath}/PasswordController" method="post" class="">
							<input type="hidden" name="action" value="updateAdminPassword">
							<input type="hidden" name="admin_id" value="${admin.id}">
							<div class="form-group">
								<label for="cpassword" class="fw-semibold text-muted">Current Password</label>
								<input type="password" class="form-control" name="current_password" placeholder="*******" >
							</div>
							<div class="form-group">
								<label for="cpassword" class="fw-semibold text-muted">New Password</label>
								<input type="password" class="form-control" name="new_password" placeholder="*******" >
							</div>
							<div class="form-group">
								<label for="cpassword" class="fw-semibold text-muted">Confirm New Password</label>
								<input type="password" class="form-control" name="confirm_new_password" placeholder="*******" >
							</div>
							<div class="form-group">
								<a href="${pageContext.request.contextPath}/AdminController?page=profile&admin_id=${admin.id}" class="btn btn-secondary"><i class="las la-arrow-left"></i> Back</a>
								<button type="submit" class="btn btn-primary"><i class="las la-key"></i> Save	</button>
							</div>
						</form>
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