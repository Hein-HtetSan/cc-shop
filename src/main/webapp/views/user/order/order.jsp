<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>
<style>

	.address-card{
		padding: 3rem;
		border: 1px solid #ccc;
		border-radius: 5px;
		margin-bottom: 2rem;
		width: 100%;
	}


</style>


		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="${pageContext.request.contextPath}/UserController?page=main">HOME</a></li>
							<li class="active">Order</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
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
			
				<!-- row -->
				<div class="row">
				
					

						<c:forEach items="${orders}" var="order">
						<div class="col-4">
							<!-- items -->
							<div class="card address-card">
								<div class="" style="display: flex; align-items: center; justify-content: between; width: 100%;">
							        <h5>${order}</h5>
							        <a class="btn btn-danger" href="${pageContext.request.contextPath}/UserController?page=orderCancel&order_code=${order}&user_id=${customer.id}" class="remove-btn" style="">Cancel Order</a>
						    	</div>
						    
							</div>
						</div>
							<!-- end of items -->
						</c:forEach>
						
					
				</div>
				
				<!-- row -->
				
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		
		
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		
		<%@ include file="/views/user/layout/footer.jsp" %>
		
		

