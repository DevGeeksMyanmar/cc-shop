

<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>


<style>
    .grid-container {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
      
      gap: 20px;
    }
    .grid-item {
      background-color: #f0f0f0;
      padding: 20px;
      border: 1px solid #ccc;
    }
    .seller-img-wrapper{
    	width: 100%;
    	height: auto;
    	display: flex;
    	align-items: center;
    	justify-content: center;
    }
    .seller-image{
    	width: 120px;
    	height: 120px;
    	border: 1px solid #D10024;
    	border-radius: 50%;
    	padding: .5rem;
    }
    .container .profile-wrapper{
    	display: flex;
    	align-items: start;
    	justify-content: center;
    }
    .title{
    	color:  #D10024;
    }
    .edit-form{
    	padding: 2rem;
    	border: 1px solid #ccc;
    	border-radius: .5rem;
    }
  </style>
		
		<!-- SECTION NEW elect-->
		<div class="section">
			<!-- container -->
			<div class="container">
			
						<c:if test="${not empty success }">
						<div class="alert bg-success text-center " role="alert" id="errorAlert">
							${success}
						</div>
						</c:if>
						<c:if test="${not empty error }">
						<div class="alert bg-danger text-center " role="alert" id="errorAlert">
							${error}
						</div>
						</c:if>
				<!-- row -->	
				<a href="${pageContext.request.contextPath}/UserController?page=main" style="">Back</a>
				<div class="row profile-wrapper">
				
					<!-- user profile -->	
					<div class="col-md-5 order-details" style="margin-top: 2rem !important; margin-right: 5rem !important;">
						<div class="section-title text-center">
							<h3 class="title">Profile</h3>
						</div>
						<div class="seller-img-wrapper">
							<c:if test="${user.image == 'assets/images/troll.jpg' }">
								<img class="seller-image" src="${pageContext.request.contextPath}/${user.image}" >
							</c:if>
							<c:if test="${user.image != 'assets/images/troll.jpg' }">
								<img class="seller-image" src="${pageContext.request.contextPath}/assets/images/user/${user.image}" >
							</c:if>
						</div>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>Content</strong></div>
								<div><strong></strong></div>
							</div>
							<div class="order-products">
								<div class="order-col">
									<div>Name</div>
									<div>${user.name}</div>
								</div>
								<div class="order-col">
									<div>Email</div>
									<div>${user.email}</div>
								</div>
								<div class="order-col">
									<div>Phone</div>
									<div>${user.phone}</div>
								</div>
								<div class="order-col">
									<div>Address</div>
									<div>${user.address}</div>
								</div>
							</div>
						</div>
						<div class="row" style="display: block;">
							<div class="col" style="display: flex; align-items: center; justify-content: end">
								<a href="${pageContext.request.contextPath}/UserController?page=edit&user_id=${user.id}" class="btn btn-primary" id="editBtn" style="margin-right: .5rem;">Edit Profile</a>
								<a href="${pageContext.request.contextPath}/LoginController?page=userLogout" class="btn btn-danger"> Logout</a>
							</div>
							<hr>
							<a href="${pageContext.request.contextPath}/UserController?page=changePassword&user_id=${user.id}" style="display: block; text-align:center; margin-bottom: .9rem;">Change Password</a>
							<a href="" style="display: block; text-align:center;">Forget Password?</a>
						</div>
					</div>
					<!-- /user profile -->
					
					

				</div>
				<!-- /row -->									
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

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

<%@ include file="/views/user/layout/footer.jsp" %>