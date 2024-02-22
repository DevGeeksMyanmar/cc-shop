<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="" id="mainLink"><a href="${pageContext.request.contextPath}/UserController?page=fetchByCategory&category_id=all">All</a></li>
						<c:forEach items="${categories}" var="category">
							<li class="${param.category_id == category.id ? 'active' : ''}"><a href="${pageContext.request.contextPath}/UserController?page=fetchByCategory&category_id=${category.id}">${category.name}</a></li>
						</c:forEach>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
		
		
		
