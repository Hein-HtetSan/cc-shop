<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/views/seller/layout/header.jsp" %>

<style>
	input[type=file]{
		display: none;
	}
	.image-box{
		width: 100%;
		height: 40px;
		border: 1px solid #eee;
		border-radius: .4rem;
		text-align: center;
		cursor: pointer;
	}
	.image-box:hover{
		background-color: #fff !important;
	}
</style>

	<div class="wrapper">
		<!-- #### Main header #### -->
		<%@ include file="/views/seller/layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="/views/seller/layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
	</div>
	<div class="main-panel">
		<div class="content">
			<div class="container-fluid">
				<div class="row">
					<h4 class="page-title col-md-6 col-6"> Add Image for Product</h4>
                	
				</div>
		        <div class="row d-flex align-items-center justify-content-center">
		           <div class="col-12 col-md-5">
		                <div class="card">
		                   <div class="card-body">
		                   		<button class="btn btn-primary add-image">+ Image</button>
		                   		<div class="image-container">
							        <!-- Image boxes will be appended here -->
							    </div>
		                   		<div class="d-flex align-items-center justify-content-between">
		                   			<div class="form-group">
		                   				<a class="btn btn-secondary text-light" href="">Back</a>
		                   			</div>
		                   			<div class="form-group">
		                   				<button class="btn btn-primary">Save</button>
		                   			</div>
		                   		</div>
		                   </div>            
		        		</div>                     
		           </div>
    			</div>         
			</div>			
		</div>		
	</div>
	
	

	
<%@ include file="/views/seller/layout/footer.jsp"%>