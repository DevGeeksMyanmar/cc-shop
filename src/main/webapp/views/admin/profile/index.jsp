<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>CC Shop | Profile</title>
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
    .image-box{
        width: 90px;
        height: 100px;
        border: 1px solid #b4b4b4;
        border-radius: 10px;
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .plus-icon{
        width: 35px;
        height: 35px;
        border-radius: 50%;
        background-color: rgb(226, 94, 94);
        z-index: 5;
        cursor: pointer;
        position: absolute;
        top: 70px;
        left: 60px;
        text-align: center;
        transition: all .1s ease;
    }
    .pen-2{
        font-size: 18px;
        margin-top: 10px;
        color: rgb(249, 249, 249);
    }
    .plus-icon:hover{
        transform: scale(1.05);
        background: #361cdc;
    }
    img{
        width: 100%;
        height: 100%;
        border-radius: 10px;
    }
    .image-file{
        display: none;
    }
    
</style>

<body>
	<div class="wrapper  bg-light ">

							<c:if test="${not empty success}">
								<div class="alert alert-success text-center" role="alert" id="successAlert">
								${success}
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="alert alert-danger text-center" role="alert" id="successAlert">
								${error}
								</div>
							</c:if>

        <div class="row ">
        					
			<div class="col-12 d-flex align-items-center justify-content-center form-wrapper">
				<div class="card shadow rounded">
					<div class="card-header">
						<h5 class="text-muted fw-bold text-center"> Profile</h5>
					</div>
					<div class="card-body">
						<div class="row">
                            <div class="col-12 d-flex align-items-center justify-content-center">
                                <form method="post" action="${pageContext.request.contextPath}/AdminController" enctype="multipart/form-data">
                                <div class="form-group">
                                    <div class="image-box">
                                    	<input type="hidden" name="action" value="updateImage">
                                    	<input type="hidden" name="admin_id" value="${admin.id}">
                                        
                                        <c:if test="${admin.image == 'assets/images/troll.jpg'}">
	                                		<img class="image <c:if test='${admin.image != "assets/images/troll.jpg"}'> d-none </c:if>" src="${admin.image}">
	                                	</c:if>
	                                	<c:if test="${admin.image != null}">
	                                		<img class=" image <c:if test='${admin.image == "assets/images/troll.jpg"}'> d-none </c:if>" src="${pageContext.request.contextPath}/assets/images/admin/${admin.image}" class="rounded border p-2" style="width: 120px !important; height: 100px !important;">
	                                	</c:if>
                                        
                                        <label for="image" class="plus-icon text-muted"> <i class="las la-pen pen-2"></i> </label>
                                        <input type="file" class="image-file" id="image">
                                    </div>
                                </div>
                                </form>
                            </div>
                            <div class="col-12">
                                <div class="form-group text-center">
                                    Name<h6 class="fw-semibold mb-3">${admin.name}</h6>
                                    Email<h6 class="fw-semibold mb-3">${admin.email}</h6>
                                    Phone<h6 class="fw-semibold">${admin.phone}</h6>
                                </div>

                                <!-- #### Button group ####  -->
                                <div class="form-group text-center">
                                    <div class="d-flex">
                                        <a class="btn btn-primary w-100" href="${pageContext.request.contextPath}/AdminController?action=editAdmin&admin_id=${admin.id}"> <i class="las la-pen"></i> Edit </a>
                                        <div class="mx-1"></div>
                                        <a href="${pageContext.request.contextPath}/LoginController?page=adminLogout" class="btn btn-danger w-100"> <i class="las la-power-off"></i> Logout </a>
                                    </div>
                                    <hr>
                                    <a href="${pageContext.request.contextPath}/PasswordController?page=adminPasswordChange&admin_id=${admin.id}" class="btn btn-link">Change Password?</a> | <a href="" class="btn btn-link btn-danger">Forget Password?</a> 
                                    <a class="btn btn-link  d-block " href="${pageContext.request.contextPath}/AdminController?page=user" >  Back to Dashboard </a> 
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
        var successAlert = document.getElementById('successAlert');
        
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