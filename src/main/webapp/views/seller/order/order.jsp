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
				<h4 class="page-title">Orders</h4>
		        <div class="row">  
					<table class="table">
					  <thead class="bg-primary text-light" >
					    <tr>
					      <th scope="col" style="color: white" >Order_code</th>
					      <th scope="col" style="color: white">Order_name</th>
					      <th scope="col" style="color: white">Count</th>
					      <th scope="col" style="color: white">Price</th>
					      <th scope="col" style="color: white">Status</th>
					    </tr>
					  </thead>
					  <tbody>
					  <c:forEach items ="${orders}" var="order">
						    <tr>
						      <td>${order.order_code}</td>
						      <td>${order.order_name}</td>
						      <td>${order.count}</td>
						      <td>${order.price}</td>
						     <c:if test="${order.status==0}">
						     	<td class="text-danger">Pending</td>
						     </c:if>
						     <td class="text-success">Done</td>
						    </tr>
					   </c:forEach>
					  </tbody>
					</table>         
		        </div>
		        
		        
    		</div>             
		</div>			
	</div>
<%@ include file="/views/seller/layout/footer.jsp"%>