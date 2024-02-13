<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>South - Real Estate Agency Template | About Us</title>

    <!-- Favicon  -->
    <link rel="icon" href="${pageContext.request.contextPath}/assets/landing/img/core-img/favicon.ico">

    <!-- Style CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/landing/style.css">

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

    <!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img" style="background-image: url(${pageContext.request.contextPath}/assets/landing/img/slide/slide-3.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="breadcumb-content">
                        <h3 class="breadcumb-title">About us</h3>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->

    <!-- ##### About Content Wrapper Start ##### -->
    <section class="about-content-wrapper section-padding-100">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-8">
                    <div class="section-heading text-left wow fadeInUp" data-wow-delay="250ms">
                        <h2>We value Customer Satisfaction,Integrity & Innovation.</h2>
                        <p> We strive to ensure that you find exactly what you're looking for in our website.</p>
                    </div>
                    <div class="about-content">
                        <img class="wow fadeInUp" data-wow-delay="350ms" src="${pageContext.request.contextPath}/assets/landing/img/bg-img/about.jpg" alt="">
                        <p class="wow fadeInUp" data-wow-delay="450ms"><p><i>Customer Satisfaction: </i> Your satisfaction is our top priority. We are committed to exceeding your expectations by delivering exceptional products and service.</p>

                        <p><i>Integrity: </i> We believe in honesty, transparency, and integrity in all aspects of our business. You can trust us to uphold the highest ethical standards in everything we do.</p>
                            
                        <p> <i>Innovation: </i> We're constantly seeking new ways to innovate and improve our offerings to better serve our customers. From cutting-edge products to innovative shopping features, we're dedicated to staying ahead of the curve.</p></p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### About Content Wrapper End ##### -->

    <!-- ##### Meet The Team Area Start ##### -->
    <section class="meet-the-team-area section-padding-100-0">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading">
                        <h1>Meet Our Team</h1>
                        <p>ရှေ့သို့လှမ်းချီ မြန်မာပြည်</p>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <!-- Single Team Member -->
                <div class="col-12 col-sm-6 col-lg-3">
                    <div class="single-team-member mb-100 wow fadeInUp" data-wow-delay="250ms">
                        <!-- Team Member Thumb -->
                        <div class="team-member-thumb">
                            <img src="${pageContext.request.contextPath}/assets/landing/img/bg-img/team1.jpg" alt="">
                        </div>
                        <!-- Team Member Info -->
                        <div class="team-member-info">
                            <div class="section-heading">
                                <img src="img/icons/prize.png" alt="">
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
                            <img src="img/bg-img/team2.jpg" alt="">
                        </div>
                        <!-- Team Member Info -->
                        <div class="team-member-info">
                            <div class="section-heading">
                                <img src="${pageContext.request.contextPath}/assets/landing/img/icons/prize.png" alt="">
                                <h2>Mya Thida</h2>
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
                            <img src="img/bg-img/team3.jpg" alt="">
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
                            <img src="img/bg-img/team3.jpg" alt="">
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

</body>

</html>