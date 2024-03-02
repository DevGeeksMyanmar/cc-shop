<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Form</title>
  <!-- Google font -->
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

  <!-- Bootstrap -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/bootstrap.min.css"/>

  <!-- Slick -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/slick.css"/>
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/slick-theme.css"/>

  <!-- nouislider -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/nouislider.min.css"/>

  <!-- Font Awesome Icon -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/font-awesome.min.css">

  <!-- Custom stlylesheet -->
  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/style.css"/>
</head>
<style>
  .d-flex{display: flex !important;}
  .align-items-center{align-items: center !important;}
  .justify-content-center{justify-content: center !important;}
  .flex-column{flex-direction: column !important;}
  input{
    width: 300px !important;
  }
  .card{
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 1rem;
  }
</style>
<body>

	<c:if test="${!empty error}">
    <div class="alert alert-danger d-flex justify-content-between"  role="error" id="errorAlert">
  		${error}
	</div>
	</c:if>
  
  <div class="container">

    <div class="row d-flex align-items-center justify-content-center">
      <div class="col-md-7">
        <div class="card">
          <img src="${pageContext.request.contextPath}/assets/landing/img/core-img/ccshop.png" alt="" width="200" style="margin-left: 5rem; margin-bottom: -2rem;">
          <div class="card-header">
            <h3 class="text-center fw-bold text-danger">WELCOME BACK</h3>
            <h5 class="text-center">(Seller Dashboard)</h5>
            <span class="text-center text-primary">Make business success in our platform.</span>
          </div>
          <hr>
          <div class="card-body">
            <form action="${pageContext.request.contextPath}/LoginController?page=sellerLogin" method="post">
              <div class="form-group">
                <label for="" class="form-label">Email</label>
                <input type="email" class="form-control" name="email">
              </div>
              <div class="form-group">
                <label for="" class="form-label">Password</label>
                <input type="password" class="form-control" name="password">
              </div>
              <div class="form-group">
                <button class="btn btn-danger" type="submit">Login</button>
              </div>
            </form>
            <hr>
            <a href="${pageContext.request.contextPath}/RegisterController?page=sellerForm" class="btn btn-link " style="display: block; text-align: center;">Register to be seller.</a>
              <a href="" class="btn btn-link btn-primary" style="display: block; text-align: center;">Forgot Password?</a>
              <a href="${pageContext.request.contextPath}/views/index.jsp" class="btn btn-link " style="display: block; text-align: center;">Back</a>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/admin/login.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>