<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--
                       _oo0oo_
                      o8888888o
                      88" . "88
                      (| -_- |)
                      0\  =  /0
                    ___/`---'\___
                  .' \\|     |// '.
                 / \\|||  :  |||// \
                / _||||| -:- |||||- \
               |   | \\\  -  /// |   |
               | \_|  ''\---/''  |_/ |
               \  .-\__  '-'  ___/-. /
             ___'. .'  /--.--\  `. .'___
          ."" '<  `.___\_<|>_/___.' >' "".
         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
         \  \ `_.   \_ __\ /__ _/   .-` /  /
     =====`-.____`.___ \_____/___.-`___.-'=====
                       `=---='

     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            ဘုရား        တရား        သံဃာ
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>CC Shop</title>

    <!-- Favicon  -->
    <link rel="icon" href="${pageContext.request.contextPath}/assets/landing/img/core-img/ccshop.png">

    <!-- Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/landing/style.css">
    
    <!--sweet alert cdn link-->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>

<style>	
	*{
		scroll-behavior: smooth;
	}
</style>

<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="south-load"></div>
    </div>

    <!-- ##### Header Area Start ##### -->
    <%@ include file="./layout/header.jsp" %>
    <!-- ##### Header Area End ##### -->

    <!-- ##### Hero Area Start ##### -->
    <section class="hero-area">
        <div class="hero-slides owl-carousel">
            <!-- Single Hero Slide -->
            <div class="single-hero-slide bg-img" style="background-image: url(${pageContext.request.contextPath}/assets/landing/img/slide/no-one.jpg);">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="hero-slides-content">
                                <h2 data-animation="fadeInUp" data-delay="100ms">Stay Home, Shop Online</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Single Hero Slide -->
            <div class="single-hero-slide bg-img" style="background-image: url(${pageContext.request.contextPath}/assets/landing/img/slide/slide-2.jpg);">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="hero-slides-content">
                                <h2 data-animation="fadeInUp" data-delay="100ms">For your Necessity</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Single Hero Slide -->
            <div class="single-hero-slide bg-img" style="background-image: url(${pageContext.request.contextPath}/assets/landing/img/slide/no-3.jpg);">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="hero-slides-content">
                                <h2 data-animation="fadeInUp" data-delay="100ms">Find your perfect product</h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Hero Area End ##### -->


    <!-- ##### Featured Properties Area Start ##### -->
    <section class="featured-properties-area section-padding-100-50">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading wow fadeInUp">
                        <h2>Featured Properties</h2>
                        <p>Products with perfect quality and efficient quantity!</p>
                    </div>
                </div>
            </div>

            <div class="row">

                <!-- Single Featured Property -->
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="single-featured-property mb-50 wow fadeInUp" data-wow-delay="100ms">
                        <!-- Property Thumbnail -->
                        <div class="property-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/feature-img/feature-1.jpg" alt="">
                        </div>
                        <!-- Property Content -->
                        <div class="property-content">
                            <h5>Best Interaction</h5>
                            <p class="location">IVA Verified</p>
                            <p>Simple platform , clear listing , secure transcations - buy confidently.</p>
                            <div class="property-meta-data d-flex align-items-end justify-content-between">
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Single Featured Property -->
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="single-featured-property mb-50 wow fadeInUp" data-wow-delay="200ms">
                        <!-- Property Thumbnail -->
                        <div class="property-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/feature-img/feature-2.jpg" alt="">
                        </div>
                        <!-- Property Content -->
                        <div class="property-content">
                            <h5>Strong Security</h5>
                            <p class="location">Security 2.0</p>
                            <p>Robust measure for safe transactions , ensuring user confidence.</p>
                            <div class="property-meta-data d-flex align-items-end justify-content-between">
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Single Featured Property -->
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="single-featured-property mb-50 wow fadeInUp" data-wow-delay="600ms">
                        <!-- Property Thumbnail -->
                        <div class="property-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/feature-img/feature-6.jpg" alt="">
                        </div>
                        <!-- Property Content -->
                        <div class="property-content">
                            <h5>Customer Service</h5>
                            <p class="location">24 hour Active Agents</p>
                            <p>Reliable support , anytime , anywhere : We are here for you.</p>
                            <div class="property-meta-data d-flex align-items-end justify-content-between">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Featured Properties Area End ##### -->

    <!-- ##### Call To Action Area Start ##### -->
    <section class="call-to-action-area bg-fixed bg-overlay-black" style="background-image: url(${pageContext.request.contextPath}/assets/landing/img/section-bg.jpg)">
        <div class="container h-100">
            <div class="row align-items-center h-100">
                <div class="col-12">
                    <div class="cta-content text-center">
                        <h2 class="wow fadeInUp" data-wow-delay="300ms">Are You Looking For Something To Buy??</h2>
                        <h6 class="wow fadeInUp" data-wow-delay="400ms">For your needs, CC shop is always here.</h6>
                        <a href="#" class="btn south-btn mt-50 wow fadeInUp" data-wow-delay="500ms">Buy Now</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Call To Action Area End ##### -->

        <!-- ##### Elements Area Start ##### -->
        <section class="elements-area section-padding-100-0" id="mile">
            <div class="container">
                <div class="row">
                    <!-- ##### Progress Bars & Accordions ##### -->
                    <div class="col-12">
                        <div class="elements-title">
                            <h2> Goals & Commitment to Customer Satisfaction</h2>
                        </div>
                    </div>
    
                    <!-- ##### Accordians ##### -->
                    <div class="col-12 col-lg-6">
                        <div class="accordions mb-100" id="accordion" role="tablist" aria-multiselectable="true">
                            <!-- single accordian area -->
                            <div class="panel single-accordion">
                                <h6><a role="button" class="" aria-expanded="true" aria-controls="collapseOne" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">Increase Customer Engagement
                                        <span class="accor-open"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                        <span class="accor-close"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                        </a></h6>
                                <div id="collapseOne" class="accordion-content collapse show">
                                    <p>We aim to captivate our audience with insightful blog posts, interactive features like polls and quizzes, and by showcasing user-generated content and social media feeds. Our personalized email newsletters keep customers informed and engaged with our brand.</p>
                                </div>
                            </div>
                            <!-- single accordian area -->
                            <div class="panel single-accordion">
                                <h6>
                                    <a role="button" class="collapsed" aria-expanded="true" aria-controls="collapseTwo" data-parent="#accordion" data-toggle="collapse" href="#collapseTwo">Improve Conversion Rate
                                            <span class="accor-open"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                            <span class="accor-close"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                            </a>
                                </h6>
                                <div id="collapseTwo" class="accordion-content collapse">
                                    <p>To drive sales, we focus on compelling product descriptions, professional photography, and authentic customer testimonials. Clear calls-to-action, limited-time offers, and comparison guides help guide customers toward making a purchase decision.</p>
                                </div>
                            </div>
                            <!-- single accordian area -->
                            <div class="panel single-accordion">
                                <h6>
                                    <a role="button" aria-expanded="true" aria-controls="collapseThree" class="collapsed" data-parent="#accordion" data-toggle="collapse" href="#collapseThree">Enhance Customer Loyalty and Retention
                                            <span class="accor-open"><i class="fa fa-plus" aria-hidden="true"></i></span>
                                            <span class="accor-close"><i class="fa fa-minus" aria-hidden="true"></i></span>
                                        </a>
                                </h6>
                                <div id="collapseThree" class="accordion-content collapse">
                                    <p>We prioritize customer loyalty by offering rewards through our loyalty program, providing personalized recommendations, and delivering educational content. Exclusive access to deals, responsive customer support, and targeted email campaigns further nurture our customer relationships, encouraging repeat purchases and brand loyalty.</p>
                                </div>
                            </div>
                        </div>
                    </div>
      
                    <!-- ##### Tabs ##### -->
                    <div class="col-12 col-lg-6">
                        <div class="south-tabs-content">
                            <ul class="nav nav-tabs" id="myTab" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link" id="tab--1" data-toggle="tab" href="#tab1" role="tab" aria-controls="tab1" aria-selected="false">Commitment-1</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link active" id="tab--2" data-toggle="tab" href="#tab2" role="tab" aria-controls="tab2" aria-selected="false">Commitment-2</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" id="tab--3" data-toggle="tab" href="#tab3" role="tab" aria-controls="tab3" aria-selected="true">Commitment-3</a>
                                </li>
                            </ul>
    
                            <div class="tab-content mb-100" id="myTabContent">
                                <div class="tab-pane fade" id="tab1" role="tabpanel" aria-labelledby="tab--1">
                                    <div class="south-tab-content">
                                        <!-- Tab Text -->
                                        <div class="south-tab-text">
                                            <h6>Quality Assurance</h6>
                                            <p>At Our CC Shop, we're dedicated to providing you with products of the highest quality. Each item in our collection undergoes rigorous testing and inspection to ensure it meets our standards of excellence.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade show active" id="tab2" role="tabpanel" aria-labelledby="tab--2">
                                    <div class="south-tab-content">
                                        <!-- Tab Text -->
                                        <div class="south-tab-text">
                                            <h6>Secure Shopping Experience</h6>
                                            <p>Shop with confidence knowing that your data is protected and your privacy is respected. Your trust is invaluable to us, and we're committed to earning it every time you shop with us.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="tab3" role="tabpanel" aria-labelledby="tab--3">
                                    <div class="south-tab-content">
                                        <!-- Tab Text -->
                                        <div class="south-tab-text">
                                            <h6>Hassle-Free Returns and Exchanges</h6>
                                            <p>Simply contact our customer service team, and we'll guide you through the process of returning or exchanging your item. Your satisfaction is our priority, and we're here to ensure that you have a positive shopping experience with us.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    
                <div class="row">
                    <!-- ##### Milestones ##### -->
                    <div class="col-12">
                        <div class="elements-title">
                            <h2>Milestones</h2>
                        </div>
                    </div>
    
                    <!-- Single Cool Fact -->
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="single-cool-fact-area mb-100">
                            <img src="img/icons/prize2.png" alt="">
                            <h2><span class="counter">14</span></h2>
                            <h6>Achievements</h6>
                        </div>
                    </div>
                    <!-- Single Cool Fact -->
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="single-cool-fact-area mb-100">
                            <img src="img/icons/new2.png" alt="">
                            <h2>+<span class="counter">500</span></h2>
                            <h6>Happy clients</h6>
                        </div>
                    </div>
                    <!-- Single Cool Fact -->
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="single-cool-fact-area mb-100">
                            <img src="img/icons/house2.png" alt="">
                            <h2><span class="counter">200</span></h2>
                            <h6>Retailers on Website</h6>
                        </div>
                    </div>
                    <!-- Single Cool Fact -->
                    <div class="col-12 col-sm-6 col-lg-3">
                        <div class="single-cool-fact-area mb-100">
                            <img src="img/icons/house1.png" alt="">
                            <h2><span class="counter">323</span></h2>
                            <h6>Finished Dealerships</h6>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- ##### Elements Area End ##### -->
    

    <!-- ##### Testimonials Area Start ##### -->
    <section class="south-testimonials-area section-padding-100" id="review">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading wow fadeInUp" data-wow-delay="250ms">
                        <h2>Client Reviews</h2>
                        <p>Check our positive reviews of clients.</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="testimonials-slides owl-carousel wow fadeInUp" data-wow-delay="500ms">

                        <!-- Single Testimonial Slide -->
                        <div class="single-testimonial-slide text-center">
                            <h5>Fantastic Selection, Great Service!</h5>
                            <p>I was amazed by the variety of products available on this website. The ordering process was seamless, and my items arrived promptly. Highly recommend.</p>

                            <div class="testimonial-author-info">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/blog-img/c-3.jpg" alt="">
                                <p>Ko Kyaw Gyi, <span>Customer</span></p>
                            </div>
                        </div>

                        <!-- Single Testimonial Slide -->
                        <div class="single-testimonial-slide text-center">
                            <h5>Top-Notch Quality and Fast Shipping!</h5>
                            <p>I ordered a few items from this site, and I couldn't be happier with the quality. The shipping was lightning-fast, and everything arrived in perfect condition. Will definitely be shopping here again!</p>

                            <div class="testimonial-author-info">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/blog-img/c-2.jpg" alt="">
                                <p>Sayar San, <span>Customer</span></p>
                            </div>
                        </div>

                        <!-- Single Testimonial Slide -->
                        <div class="single-testimonial-slide text-center">
                            <h5>Excellent Customer Support, Highly Impressed!</h5>
                            <p>Had a question about one of the products, and the customer support team was incredibly helpful and responsive. They went above and beyond to assist me. A+ service!</p>

                            <div class="testimonial-author-info">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/blog-img/c-1.jpg" alt="">
                                <p>Lar Lar, <span>Customer</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Testimonials Area End ##### -->

    <!-- Contact Info Start -->
    
    <section class="south-contact-area section-padding-100" id="contact">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="contact-heading">
                        <h6>Contact info</h6>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12 col-lg-4">
                    <div class="content-sidebar">
                        <!-- Office Hours -->
                        <div class="weekly-office-hours">
                            <ul>
                                <li class="d-flex align-items-center justify-content-between"><span>Monday - Friday</span> <span>09:00 AM - 19:00 PM</span></li>
                                <li class="d-flex align-items-center justify-content-between"><span>Saturday</span> <span>09:00 AM - 14:00 PM</span></li>
                                <li class="d-flex align-items-center justify-content-between"><span>Sunday</span> <span>Closed</span></li>
                            </ul>
                        </div>
                        <!-- Address -->
                        <div class="address mt-30">
                            <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/phone-call.png" alt=""> +95 9934 828 116</h6>
                            <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/envelope.png" alt=""> DevGeeksMyanmar@gmail.com</h6>
                            <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/location.png" alt="">University of Computer Studies<br>Shwe Pyi Thar , Yangon</h6>
                        </div>
                    </div>
                </div>

                <!-- Contact Form Area -->
                <div class="col-12 col-lg-8" >
                    <div class="contact-form">
                        <form action="${pageContext.request.contextPath}/MessageController?action=sendMessage" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" name="name" id="contact-name" placeholder="Your Name">
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control" name="phone" id="contact-number" placeholder="Your Phone">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" name="email" id="contact-email" placeholder="Your Email">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="message" id="message" cols="30" rows="10" placeholder="Your Message"></textarea>
                            </div>
                            <button type="submit" class="btn south-btn">Send Message</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<c:if test="${not empty status}"> <input type="hidden" id="status" value="${status}"> </c:if>
    <!-- Contact Info End -->

   <!-- ##### Footer Area Start ##### -->
   <%@ include file="./layout/footer.jsp" %>
<!-- ##### Footer Area End ##### -->

	<script>
	var status=document.getElementById("status").value;
	if(status=="Message Sent Successfully"){
		Swal.fire({
			  title: "Success",
			  text: "Message Sent Successfully",
			  icon: "success"
			});
	}
	
	</script>

    <!-- jQuery (Necessary for All JavaScript Plugins) -->
    <script src="${pageContext.request.contextPath}/assets/landing/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="${pageContext.request.contextPath}/assets/landing/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${pageContext.request.contextPath}/assets/landing/js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="${pageContext.request.contextPath}/assets/landing/js/plugins.js"></script>
    <script src="${pageContext.request.contextPath}/assets/landing/js/classy-nav.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/landing/js/jquery-ui.min.js"></script>
    <!-- Active js -->
    <script src="${pageContext.request.contextPath}/assets/landing/js/active.js"></script>



</body>

</html>