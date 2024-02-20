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
					<h4 class="page-title col-md-6 col-6">Product</h4>
                	<div class="col-md-6 col-6">
                		<button type="button" class="btn btn-primary text-white pull-right"> Add New Product</button>
                	</div>
				</div>
		        <div class="row">
		           <div class="col-md-12">
		                <div class="card card-body">
		                            <div class="media align-items-center align-items-lg-start text-center text-lg-left flex-column flex-lg-row">
		                                <div class="mr-2 mb-3 mb-lg-0">
		                                    
		                                        <img src="https://i.imgur.com/5Aqgz7o.jpg" width="150" height="150" alt="">
		                                   
		                                </div>
		
		                                <div class="media-body">
		                                    <h6 class="media-title font-weight-semibold">
		                                        <a href="#" data-abc="true">Apple iPhone XR (Red, 128 GB)</a>
		                                    </h6>
		
		                                    <ul class="list-inline list-inline-dotted mb-3 mb-lg-2">
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">Phones</a></li>
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">Mobiles</a></li>
		                                    </ul>
		
		                                    <p class="mb-3">128 GB ROM | 15.49 cm (6.1 inch) Display 12MP Rear Camera | 7MP Front Camera A12 Bionic Chip Processor | Gorilla Glass with high quality display </p>
		                                </div>
		
		                                <div class="mt-3 mt-lg-0 ml-lg-3 text-center">
		                                    <h3 class="mb-0 font-weight-semibold">$459.99</h3>
		
		                                    <button type="button" class="btn btn-primary mt-4 text-white"></i> Detail</button>
		                                </div>
		                            </div>
		                        </div>
		
		                         <div class="card card-body mt-3">
		                            <div class="media align-items-center align-items-lg-start text-center text-lg-left flex-column flex-lg-row">
		                                <div class="mr-2 mb-3 mb-lg-0">
		                                    
		                                        <img src="https://i.imgur.com/Aj0L4Wa.jpg" width="150" height="150" alt="">
		                                   
		                                </div>
		
		                                <div class="media-body">
		                                    <h6 class="media-title font-weight-semibold">
		                                        <a href="#" data-abc="true">Apple iPhone XS Max (Gold, 64 GB)</a>
		                                    </h6>
		
		                                    <ul class="list-inline list-inline-dotted mb-3 mb-lg-2">
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">Phones</a></li>
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">Mobiles</a></li>
		                                    </ul>
		
		                                    <p class="mb-3">256 GB ROM | 15.49 cm (6.1 inch) Display 12MP Rear Camera | 15MP Front Camera A12 Bionic Chip Processor | Gorilla Glass with high quality display </p>
		
		                                </div>
		                                <div class="mt-3 mt-lg-0 ml-lg-3 text-center">
		                                    <h3 class="mb-0 font-weight-semibold">$612.99</h3>		
		                                    <button type="button" class="btn btn-primary mt-4 text-white"> Detail</button>
		                                </div>
		                            </div>
		                        </div>
		                        
		                        <div class="card card-body mt-3">
		                            <div class="media align-items-center align-items-lg-start text-center text-lg-left flex-column flex-lg-row">
		                                <div class="mr-2 mb-3 mb-lg-0">
		                                    
		                                        <img src="https://i.imgur.com/Aj0L4Wa.jpg" width="150" height="150" alt="">
		                                   
		                                </div>
		
		                                <div class="media-body">
		                                    <h6 class="media-title font-weight-semibold">
		                                        <a href="#" data-abc="true">Apple iPhone XS Max (Gold, 64 GB)</a>
		                                    </h6>
		
		                                    <ul class="list-inline list-inline-dotted mb-3 mb-lg-2">
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">Phones</a></li>
		                                        <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">Mobiles</a></li>
		                                    </ul>
		
		                                    <p class="mb-3">256 GB ROM | 15.49 cm (6.1 inch) Display 12MP Rear Camera | 15MP Front Camera A12 Bionic Chip Processor | Gorilla Glass with high quality display </p>
		
		                                </div>
		                                <div class="mt-3 mt-lg-0 ml-lg-3 text-center">
		                                    <h3 class="mb-0 font-weight-semibold">$612.99</h3>		
		                                    <button type="button" class="btn btn-primary mt-4 text-white">Detail</button>
		                                </div>
		                            </div>
		                        </div>                      
		        	</div>                     
		        </div>
    		</div>   
                        
			</div>
						
		</div>
				
	</div>
	<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h6 class="modal-title"><i class="la la-frown-o"></i> Under Development</h6>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body text-center">									
					<p>Currently the pro version of the <b>Ready Dashboard</b> Bootstrap is in progress development</p>
					<p>
						<b>We'll let you know when it's done</b></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/views/seller/layout/footer.jsp"%>