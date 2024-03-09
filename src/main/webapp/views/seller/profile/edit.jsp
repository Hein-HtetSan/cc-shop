<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>CC Shop | Profile</title>
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/ready.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/admin/assets/css/demo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css" integrity="sha512-vebUliqxrVkBy3gucMhClmyQP9On/HAWQdKDXRaAlb/FKuTbxkjPKUyqVOxAcGwFDka79eTF+YXwfke1h3/wfg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<style>
	.form-wrapper{
		width: 100%;
		height: 100vh;
	}
	.card{
		width: 500px !important;
	}
    .image-box{
        width: 90px;
        height: 100px;
        border: 1px solid #b4b4b4;
        border-radius: 10px;
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .plus-icon{
        width: 35px;
        height: 35px;
        border-radius: 50%;
        background-color: rgb(226, 94, 94);
        z-index: 5;
        cursor: pointer;
        position: absolute;
        top: 70px;
        left: 60px;
        text-align: center;
        transition: all .1s ease;
    }
    .pen-2{
        font-size: 18px;
        margin-top: 10px;
        color: rgb(249, 249, 249);
    }
    .plus-icon:hover{
        transform: scale(1.05);
        background: #361cdc;
    }
    img{
        width: 100%;
        height: 100%;
        border-radius: 10px;
    }
    .image-file{
        display: none;
    }
</style>

<body>
	<div class="wrapper  bg-light ">

        <div class="row ">
			<div class="col-12 d-flex align-items-center justify-content-center form-wrapper">
				<div class="card shadow rounded">
					<div class="card-header">
						<h5 class="text-muted fw-bold text-center">  <i class="las la-edit"></i> Edit Profile</h5>
					</div>
					<div class="card-body">
						<div class="row" style="margin-top: 1rem !important;">
                            <div class="col-12" >
                                <form method="post" action="${pageContext.request.contextPath}/SellerController" enctype="multipart/form-data">
                                	<input type="hidden" name="action" value="updateProfile" >
                                	<input type="hidden" name="seller_id" value="${seller.id}" >
                                	<div class="col-12 d-flex align-items-center justify-content-center">
		                            	<c:choose>
		                            		<c:when test="${seller.image == 'assets/images/troll.jpg'}">
		                            			<img src="${seller.image}" style="width: 150px !important;">
		                            		</c:when>
		                            		<c:otherwise>
		                            			<img src="${pageContext.request.contextPath}/assets/images/seller/${seller.image}" style="width: 100px !important;">
		                            		</c:otherwise>
		                            	</c:choose>
		                            </div>
                                	<div class="form-group" style="margin-bottom: -1rem !important;">
                                		<lable class="form-label" for="name">Name</lable>
                                		<input type="text" name="name" value="${seller.name }" class="form-control">
                                	</div>	
                                	<div class="form-group" style="margin-bottom: -1rem !important;">
                                		<lable class="form-label" for="email">Email</lable>
                                		<input type="email" name="email" value="${seller.email }" class="form-control" >
                                	</div>
                                	<div class="form-group" style="margin-bottom: -1rem !important;">
                                		<lable class="form-label" for="phone">Phone</lable>
                                		<input type="text" name="phone" value="${seller.phone }" class="form-control">
                                	</div>		
                                	<div class="form-group" style="margin-bottom: -1rem !important;">
                                		<lable class="form-label" for="phone">Address</lable>
                                		<input type="text" name="address" value="${seller.address }" class="form-control">
                                	</div>
                                	<div style="display: flex; align-items: center; justify-content: between;">
	                                	<div class="form-group" style="margin-bottom: -1rem !important;">
	                                		<lable class="form-label" for="phone">Company</lable>
	                                		<input type="text" name="company" value="${seller.company }" class="form-control">
	                                	</div>
	                                	<div class="form-group" style="margin-bottom: -1rem !important;">
	                                		<lable class="form-label" for="phone">Business</lable>
	                                		<select name="business_id" class="form-control">
	                                			<c:forEach items="${businesses}" var="business">
	                                				<option value="${business.id}" <c:if test="${business.id == seller.business_id}">selected</c:if> >${business.name}</option>
	                                			</c:forEach>
	                                		</select>
	                                	</div>
                                	</div>
                                	<div class="form-group" style="margin-bottom: -1rem !important;">
                                		<lable class="form-label" for="phone">Image</lable>
                                		<input type="file" name="file" class="form-control mt-2">
                                	</div>
                                	<div class="form-group" style="margin-bottom: -1rem !important;">
                                		<button class="btn btn-primary"> <i class="las la-save"></i> Update </button>
                                	</div>	
                                </form>
                                <!-- #### Button group ####  -->
                                <div class="form-group text-center" style="margin-bottom: -1rem !important;">
                                    <a class="btn btn-link  d-block " href="${pageContext.request.contextPath}/SellerController?page=profile&seller_id=${seller.id}" >  Back to Profile </a> 
                                </div>
                            </div>
                        </div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/core/jquery.3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/chartist/chartist.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/chartist/plugin/chartist-plugin-tooltip.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-mapael/jquery.mapael.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-mapael/maps/world_countries.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/chart-circle/circles.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/ready.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/admin/assets/js/demo.js"></script>
</html>