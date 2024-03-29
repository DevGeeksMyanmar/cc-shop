<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
    <!-- ##### Header Area Start ##### -->
    <header class="header-area">

        <!-- Top Header Area -->


        <!-- Main Header Area -->
        <div class="main-header-area" id="stickyHeader">
            <div class="classy-nav-container breakpoint-off">
                <!-- Classy Menu -->
                <nav class="classy-navbar justify-content-between" id="southNav">

                    <!-- Logo -->
                    <a class="nav-brand" href="index.html"><img src="${pageContext.request.contextPath}/assets/landing/img/core-img/ccshop.png" alt="" width="150"></a>

                    <!-- Navbar Toggler -->
                    <div class="classy-navbar-toggler">
                        <span class="navbarToggler"><span></span><span></span><span></span></span>
                    </div>

                    <!-- Menu -->
                    <div class="classy-menu">

                        <!-- close btn -->
                        <div class="classycloseIcon">
                            <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                        </div>

                        <!-- Nav Start -->
                        <div class="classynav">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/views/index.jsp">Home</a></li>
                                <li><a href="${pageContext.request.contextPath}/views/index.jsp#aboutus">About Us</a></li>
                                <li><a href="${pageContext.request.contextPath}/views/index.jsp#review">Review</a></li>
                                <li><a href="${pageContext.request.contextPath}/views/index.jsp#contact">Contact</a></li>
                                <li><a href="${pageContext.request.contextPath}/RegisterController?page=sellerForm" id="business">Business</a></li>
                                <li><a href="${pageContext.request.contextPath}/views/user/form.jsp" id="shopnow">Shop now</a></li>
                            </ul>
                        </div>
                        <!-- Nav End -->
                    </div>
                </nav>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->