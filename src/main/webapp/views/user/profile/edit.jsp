

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
				<a href="${pageContext.request.contextPath}/UserController?page=profile&user_id=${user.id}" style="">Back</a>
				<div class="row profile-wrapper">
				
					<!-- user profile -->	
							<div class="col-md-5 " style="margin-bottom: 2rem;">
					
								<form action="${pageContext.request.contextPath}/UserController" method="post" class="edit-form" enctype="multipart/form-data">
									
									<input type="hidden" name="id" value="${user.id}">
									<input type="hidden" name="action" value="updateProfile" >
									
									<h3 class="title" style="margin-bottom: 2rem;">Edit Profile</h3>
									<div class="form-group">
										<label class="form-label">Name</label>
										<input class="form-control" name="name" type="text" value="${user.name}">
									</div>
									<div class="form-group">
										<label class="form-label">Email</label>
										<input class="form-control" name="email" type="email" value="${user.email}">
									</div>
									<div class="form-group">
										<label class="form-label">Phone</label>
										<input class="form-control" name="phone" type="number" value="${user.phone}">
									</div>
									<div class="form-group">
										<label class="form-label">Address</label>
										<input class="form-control" name="address" type="text"value="${user.address}">
									</div>
									<div class="form-group">
										<label class="form-label">Choose New Image</label>
										<input class="form-control" name="file" type="file">
									</div>
									<button class="btn btn-info" type="submit">Update Profile</button>
								</form>
							
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