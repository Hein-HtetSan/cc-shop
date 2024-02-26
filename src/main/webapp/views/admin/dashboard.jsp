<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
    
    <%@ include file="./layout/header.jsp" %>


	<div class="wrapper">
	
	
		<!-- #### Main header #### -->
		<%@ include file="./layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="./layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
			</div>



			<div class="main-panel">
				<div class="content">
					<div class="container-fluid">

						<h4 class="page-title">Dashboard</h4>
						
						<c:if test="${not empty success }">
						<div class="alert alert-success text-center " role="alert" id="errorAlert">
							${success}
						</div>
						</c:if>
						<c:if test="${not empty error }">
						<div class="alert alert-error text-center " role="alert" id="errorAlert">
							${error}
						</div>
						</c:if>
						

                        <!-- Content goes here  -->
                        <div class="row">
                        
							<div class="col-md-3">
								<div class="card card-stats card-warning">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-users"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Customers</p>
													<h4 class="card-title">${counts.user_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card card-stats card-success">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-user-tie"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Seller</p>
													<h4 class="card-title">${counts.seller_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card card-stats card-danger">
									<div class="card-body">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="las la-boxes"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Products</p>
													<h4 class="card-title">${counts.product_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3">
								<div class="card card-stats card-primary">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-box"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Order</p>
													<h4 class="card-title">${counts.order_count}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							
						</div>
						
						<!-- Pending order section -->
						<div class="row">
						
							<div class="col-md-6">
								<h5 class="fw-bold">Ready to Ship</h5>
								<table class="table">
									<thead class="thead-dark">
										<th class="fw-bold">Order Code</th>
										<th class="fw-bold">Status</th>
										<th class="fw-bold">Status</th>
									</thead>
									<tbody>
										<c:forEach items="${orders}" var="order">
											<tr>
												<td>
													<a class="fw-bold text-primary fs-5" href="">${order.order_code}</a>
												</td>
												<td class="text-danger fw-bold">Ready to ship?</td>
												<td>
													<a href="${pageContext.request.contextPath}/" class="btn btn-success">Ship Now</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
						</div>
						<!--  end of pending order section -->
						

					</div>
				</div>
				
			</div>




    <!-- Bootstrap JS (optional, for some features) -->
   <script>
    // Wait for the document to be ready
    document.addEventListener('DOMContentLoaded', function() {
        // Find the success alert element
        var successAlert = document.getElementById('errorAlert');
        
        // If the alert element exists
        if (successAlert) {
            // Set a timeout to hide the alert after 3 seconds
            setTimeout(function() {
                successAlert.style.display = 'none'; // Hide the alert
            }, 3000); // 3000 milliseconds = 3 seconds
        }
	    });
	</script>

</body>

<%@ include file="./layout/footer.jsp" %>

