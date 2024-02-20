<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Admin Login | Register Form</title>

	<!-- Google font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/login.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

	</head>

	
	
<body>

	<div class="materialContainer">


    <div class="box">
 
       <div class="title">LOGIN as Admin</div>
 		<form action="${pageContext.request.contextPath}/LoginController?page=adminLogin" method="post">
 			<input type="hidden" name="action" value="login">
	       <div class="input">
	        <label for="email">Email</label>
	        <input type="email" name="email" id="email">
	        <span class="spin"></span>
	     	</div>
	       <div class="input">
	          <label for="password">Password</label>
	          <input type="password" name="password" id="password">
	          <span class="spin"></span>
	       </div>
	       <div class="button login">
	          <button type="submit"><span>LOGIN</span> <i class="fa fa-check"></i></button>
	       </div>
 		</form>
       <a href="" class="pass-forgot">Forgot your password?</a>
 
    </div>
 
    <div class="overbox">
       <div class="material-button alt-2"><span class="shape"></span></div>
       <div class="title">REGISTER as Admin</div>
 		<form action="${pageContext.request.contextPath}/RegisterController?page=adminRegister" method="post">
 			<input type="hidden" name="action" value="register">
	       <div class="input">
	          <label for="name">Username</label>
	          <input type="text" name="name" id="name">
	          <span class="spin"></span>
	       </div>
	       <div class="input">
		        <label for="emails">Email</label>
		        <input type="email" name="email" id="emails">
		        <span class="spin"></span>
	     	</div>
	     	<div class="input">
		        <label for="phone">Phone</label>
		        <input type="text" name="phone" id="phone">
		        <span class="spin"></span>
		     </div>
	       <div class="input">
	          <label for="passwords">Password</label>
	          <input type="password" name="password" id="passwords">
	          <span class="spin"></span>
	       </div>
	       <div class="input">
	          <label for="cpassword">Confirm Password</label>
	          <input type="password" name="cpassword" id="cpassword">
	          <span class="spin"></span>
	       </div>
	       <div class="button">
	          <button type="submit"><span>REGISTER NOW</span></button>
	       </div>
       </form>
    </div>
    
    
 
 </div>
 
 	<c:if test="${not empty error}">
    <div class="alert alert-warning d-flex justify-content-between offset-9 w-25"  role="alert">
  		${error}
  		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	</c:if>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/admin/login.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>