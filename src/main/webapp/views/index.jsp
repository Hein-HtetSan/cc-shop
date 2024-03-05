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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/style.css"/>
    
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
                            <div class="hero-slides-content" style="display: flex; flex-direction: column; justify-content: center;
                            align-items: center;">
                                <h2 data-animation="fadeInUp" data-delay="100ms">Stay Home, Shop Online</h2>
                                
                                <div>
                                	<button style="width: 200px; height: 50px; margin-top: 20px;
                                	color: #fff; font-weight: 500; border: 2px solid #fff;" id="shopnow">Start Shopping</button>
                                	
                                	<button style="width: 200px; height: 50px; margin-top: 20px;
                                	color: #000; font-weight: 700; border: 2px solid #fff; background-color: #ccc !important;" id="shopnow">Get Started</button>
                                </div>
                                
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
    <!--collection start  -->
    	<div class="section main-section mt-5">
			<!-- container -->
			<div class="container">
			
				<!-- alert -->
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
				<!-- alert -->
			
				<!-- row -->
				<div class="row">
					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="${pageContext.request.contextPath}/assets/customer/img/shop01.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Laptop<br>Collection</h3>
								<a href="${pageContext.request.contextPath}/views/user/form.jsp" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="${pageContext.request.contextPath}/assets/customer/img/shop03.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Electronic<br>Collection</h3>
								<a href="${pageContext.request.contextPath}/views/user/form.jsp" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="${pageContext.request.contextPath}/assets/customer/img/shop02.png"  alt="">
							</div>
							<div class="shop-body">
								<h3>Camera<br>Collection</h3>
								<a href="${pageContext.request.contextPath}/views/user/form.jsp" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
    <!--collection end -->
    
	
	

    <!-- ##### Featured Properties Area Start ##### -->
    <section class="featured-properties-area section-padding-100-50">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading wow fadeInUp">
                        <h2>Featured Properties</h2>
                        <p>Keep clam and shop online!</p>
                    </div>
                </div>
            </div>

            <div class="row">
                <!-- Single Featured Property -->
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="single-featured-property mb-50 wow fadeInUp" data-wow-delay="300ms">
                        <!-- Property Thumbnail -->
                        <div class="property-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/feature-img/feature-3.jpg" alt="">
                        </div>
                        <!-- Property Content -->
                        <div class="property-content">
                            <h5>Cash on Delivery</h5>
                            <p class="location">Fast & Flexible</p>
                            <p>Trust in our robust systems for worry-free transactions</p>
                            <div class="property-meta-data d-flex align-items-end justify-content-between">
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Single Featured Property -->
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="single-featured-property mb-50 wow fadeInUp" data-wow-delay="400ms">
                        <!-- Property Thumbnail -->
                        <div class="property-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/feature-img/feature-4.jpg" alt="">
                        </div>
                        <!-- Property Content -->
                        <div class="property-content">
                            <h5>Fast Delivery</h5>
                            <p class="location">Within few days</p>
                            <p>Swift delivery guaranteed : Experience speed and reliability in every order.</p>
                            <div class="property-meta-data d-flex align-items-end justify-content-between">
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Single Featured Property -->
                <div class="col-12 col-md-6 col-xl-4">
                    <div class="single-featured-property mb-50 wow fadeInUp" data-wow-delay="500ms">
                        <!-- Property Thumbnail -->
                        <div class="property-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/feature-img/feature-5.jpg" alt="">
                        </div>
                        <!-- Property Content -->
                        <div class="property-content">
                            <h5>Quality not Quantity</h5>
                            <p class="location">World-Class Goods</p>
                            <p>Uncompromising quality : Discover excellence in every product we offer.</p>
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

    <!-- ##### Elements Area Start ##### -->
    <section class="call-to-action-area bg-fixed bg-overlay-black" style="background-image: url(${pageContext.request.contextPath}/assets/landing/img/section_bg2.jpg)">
        <div class="container h-100">
            <div class="row align-items-center h-100">
                <div class="col-12">
                    <div class="cta-content text-center">
                        <h2 class="wow fadeInUp" data-wow-delay="300ms">If you want to start a Business,then you are in the RIGHT place.</h2>
                        <h6 class="wow fadeInUp" data-wow-delay="400ms">For your needs, CC shop is always here.</h6>
                        <a href="#" class="btn south-btn mt-50 wow fadeInUp" data-wow-delay="500ms">Start your own Business.</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Elements Area End ##### -->
    <!-- ##### Meet The Team Area Start ##### -->
    <section class="meet-the-team-area section-padding-100-0" id="aboutus">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading">
                        <h1>Meet Our Team</h1>
                        <p>Together we achieve more</p>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <!-- Single Team Member -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-team-member mb-100 wow fadeInUp" data-wow-delay="250ms">
                        <!-- Team Member Thumb -->
                        <div class="team-member-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/bg-img/team8.jpg" alt="">
                        </div>
                        <!-- Team Member Info -->
                        <div class="team-member-info">
                            <div class="section-heading">
                                 <img src="${pageContext.request.contextPath}/assets/landing/img/icons/prize.png" alt="">
                                <h2>Kyaw Thuta Oo</h2>
                                <p>Realtor</p>
                            </div>
                            <div class="address">
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/phone-call.png" alt=""> +95 9691560055</h6>
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/envelope.png" alt=""> kelvingaret@gmail.com</h6>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Single Team Member -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-team-member mb-100 wow fadeInUp" data-wow-delay="500ms">
                        <!-- Team Member Thumb -->
                        <div class="team-member-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/bg-img/team7.jpg" alt="">
                        </div>
                        <!-- Team Member Info -->
                        <div class="team-member-info">
                            <div class="section-heading">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/icons/prize.png" alt="">
                                <h2>Hein Htet San</h2>
                                <p>Realtor</p>
                            </div>
                            <div class="address">
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/phone-call.png" alt=""> +95 9934 828 116</h6>
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/envelope.png" alt=""> Thida1908@gmail.com</h6>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Single Team Member -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-team-member mb-100 wow fadeInUp" data-wow-delay="750ms">
                        <!-- Team Member Thumb -->
                        <div class="team-member-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/bg-img/team5.jpg" alt="">
                        </div>
                        <!-- Team Member Info -->
                        <div class="team-member-info">
                            <div class="section-heading">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/icons/prize.png" alt="">
                                <h2>Lynn Myat Bhone</h2>
                                <p>Realtor</p>
                            </div>
                            <div class="address">
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/phone-call.png" alt=""> +95 9934 828 116</h6>
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/envelope.png" alt=""> Taungpawtarlay@gmail.com</h6>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Single Team Member -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-team-member mb-100 wow fadeInUp" data-wow-delay="750ms">
                        <!-- Team Member Thumb -->
                        <div class="team-member-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/bg-img/team6.jpg" alt="">
                        </div>
                        <!-- Team Member Info -->
                        <div class="team-member-info">
                            <div class="section-heading">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/icons/prize.png" alt="">
                                <h2>Htet Aung Hlyan</h2>
                                <p>Organizer</p>
                            </div>
                            <div class="address">
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/phone-call.png" alt=""> +95 969 156 0055</h6>
                                <h6><img src="${pageContext.request.contextPath}/assets/landing/img/icons/envelope.png" alt=""> hlyan19@gmail.com</h6>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Meet The Team Area End ##### -->


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
                        <div action="" method="">
                            <div class="form-group">
                                <input type="text" class="form-control" required name="name" id="name" placeholder="Your Name">
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control" required name="phone" id="number" placeholder="Your Phone">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" required name="email" id="email" placeholder="Your Email">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" name="message" required id="message" cols="30" rows="10" placeholder="Your Message"></textarea>
                            </div>
                            <button id="submit-btn" class="btn south-btn">Send Message</button>
                        </div>
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

<script>
$(document).ready(function() {
    // Add event listener to the submit button
    $("#submit-btn").click(function() {
        console.log("clicked");

        // Collect data from the form
        var name = $("#name").val();
        var email = $("#email").val();
        var phone = $("#number").val();
        var message = $("#message").val();

        // Configure the data object
        var data = {
            name: name,
            email: email,
            phone: phone,
            message: message
        };

        // Send the AJAX request
        $.ajax({
            url: "http://localhost:9095/shop-dot-com/MessageController",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function(response) {
                console.log(response.status);
                // Handle success message here if needed
                if(name === '' || email === '' || phone === '' || message === ''){
                	Swal.fire({
                        title: "Wrong",
                        text: "Please fill all the field",
                        icon: "error"
                    });
                }else{
                	Swal.fire({
                        title: "Success",
                        text: "Message Sent Successfully",
                        icon: "success"
                    });
                	$("#name").val('');
                   	$("#email").val('');
                   	$("#number").val('');
                    $("#message").val('');
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX error:", error);
            }
        });
    });
});

	
	</script>


</body>

</html>