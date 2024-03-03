<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>

    <!-- Google font -->
		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/customer/css/style.css"/>

<style>

    .wrapper{
        width: 100%;
        height: 100vh;
        align-items: center;
        justify-content: center;
        display: flex;
        flex-direction: column;
        position: relative;
	    overflow: hidden; /* Hide overflowing parts of the image */
        padding: 5rem;
        text-align: center !important;
    }
    .wrapper h1{
        font-size: 7rem;
        margin-bottom: 3rem !important;
    }
    .wrapper h3{
        text-transform: capitalize;
        font-size: 3rem;
        margin-top: 1rem;
    }
    .btn-groups{
        margin-top: 3rem;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 3rem;
    }
    a{
        width: 150px;
        height: 50px;
        font-size: 2rem !important;
        font-weight: bold !important;
    }
    img {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100vh;
    z-index: -1; /* Move the image behind other content */
}
</style>
</head>
<body style="">
    
    <div class="container-fluid">

        <div class="row">
            
            <div class="col-12 wrapper" style="background-image: url(./2.png) !important; position: relative;">
            
            <img src="${pageContext.request.contextPath}/views/admin/2.png">

                <h1 class="text-danger text-center d-block">CC Shop</h1>
                <h3 class="text-secondary">Explore and Customize Your Dashboard Settings and Controls</h3>

                <div class="btn-groups">
                    <a href="${pageContext.request.contextPath}/views/admin/form.jsp" class="btn btn-danger">Login</a>
                    <a href="${pageContext.request.contextPath}/views/admin/register.jsp" class="btn btn-outline-danger">Register</a>
                </div>
            </div>

        </div>

    </div>

    <!-- jQuery Plugins -->
		<!-- jQuery Plugins -->
		<script src="${pageContext.request.contextPath}/assets/customer/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/slick.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/nouislider.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/jquery.zoom.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/customer/js/main.js"></script>
</body>
</html>