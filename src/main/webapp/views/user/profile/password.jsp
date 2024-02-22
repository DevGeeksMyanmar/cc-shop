

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
    .edit-user-form {
      display: none;
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
				<a href="${pageContext.request.contextPath}/UserController?page=profile&user_id=${user.id}" style="">Back</a>
				<div class="row profile-wrapper">
				
					<!-- user profile -->	
					<div class="col-md-5 change-password-form">
							
								<form action="${pageContext.request.contextPath}/PasswordController" method="post" class="edit-form">
									<input type="hidden" name="action" value="updateUserPassword" />
									<input type="hidden" name="user_id" value="${user.id}" />
									<h3 class="title" style="margin-bottom: 2rem;">Change Password</h3>
									<div class="form-group">
										<label class="form-label">Current password</label>
										<input class="form-control" type="password" name="current_password">
									</div>
									<div class="form-group">
										<label class="form-label">New password</label>
										<input class="form-control" type="password" name="new_password">
									</div>
									<div class="form-group">
										<label class="form-label">Confirm new password</label>
										<input class="form-control" type="password"  name="confirm_new_password">
									</div>
									<button class="btn btn-info" type="submit">Update Profile</button>
								</form>
							
					</div>
					

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