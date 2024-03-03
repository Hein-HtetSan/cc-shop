<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="Models.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Form</title>
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
</head>
<style>
  .d-flex{display: flex !important;}
  .align-items-center{align-items: center !important;}
  .justify-content-center{justify-content: center !important;}
  .flex-column{flex-direction: column !important;}
  input{
    width: 300px !important;
  }
  .card{
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    padding: 1rem;
  }
  #loading-wrapper{
			position: absolute;
			top: 0;
			right: 0;
			width: 100%;
			height: 100vh;
			 /* Adjust the alpha value (0.5 in this case) to control transparency */
			z-index: 100;
			display: flex;
			overflow: hidden;
			align-items: center;
			justify-content: center;
		}
		#loading{
			border: none;
			box-shadow: 1px 1px 5px #ccc;
			border-radius: 10px;
			z-index: 100;
			width: 60px;
		}
		.d-none{
			display: none !important;
		}
</style>
<body>

	<c:if test="${ not empty error}">
	    <div class="alert alert-danger d-flex justify-content-between"  role="error" id="errorAlert">
	  		${error}
		</div>
	</c:if>
	
						<!-- loading  -->
						<div id="loading-wrapper" class="d-none">
							<img src="${pageContext.request.contextPath}/assets/loading.gif" id="loading">
						</div>
						<!-- end of loading -->
  
  <div class="container">

    <div class="row d-flex align-items-center justify-content-center">
      <div class="col-md-7">
        <div class="card">
          <div class="card-body" style="padding-top: 10rem;">
            <form action="${pageContext.request.contextPath}/OTPController?page=sellerCheckOTP" method="post">
            	<input type="hidden" name="email" value="${email}">
              <div class="form-group">
                <label for="" class="form-label">Enter OTP</label>
                <p class="alert alert-success">OTP is succssfully sent to your email : <b>${email}</b>. So, please check your email inbox.</p>
                <input type="text" class="form-control" name="otp" placeholder="OTP">
              </div>
				<div class="form-group" style="display: flex; align-items: center; justify-content: space-between; width: 300px;">
				    <button class="btn btn-primary" type="submit" id="get-otp">Submit</button>
				    <a href="${pageContext.request.contextPath}/OTPController?action=resendSellerOTP&email=${email}" class="btn btn-warning" id="resend-otp">Resend OTP</a>
				</div>
            </form>
            <hr>
              <a href="${pageContext.request.contextPath}/views/seller/profile/forgotPassword.jsp" class="btn btn-link " style="display: block; text-align: center;">Back</a>
          </div>
        </div>
      </div>
    </div>

  </div>


	
	<script>
		document.getElementById("get-otp").addEventListener("click", function(event) {
		       console.log("clicked");
				document.getElementById("loading-wrapper").classList.remove("d-none");
		   });
		document.getElementById("resend-otp").addEventListener("click", function(event) {
		       console.log("clicked");
				document.getElementById("loading-wrapper").classList.remove("d-none");
		   });
	    // Wait for the document to be ready
	    document.addEventListener('DOMContentLoaded', function() {
	    	
	        
        // Find the success alert element
        var alert = document.getElementById('errorAlert');
	    
	        // If the alert element exists
	        if (alert) {
	        	console.log("alert success");
	            // Set a timeout to hide the alert after 3 seconds
	            setTimeout(function() {
	                alert.style.display = 'none'; // Hide the alert
	            }, 3000); // 3000 milliseconds = 3 seconds
	        }
	    });
	</script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/admin/login.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>