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
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

	<script type="text/javascript">
	$(document).ready(function(){
	    $(".add-image").click(function(){
	        var imageBox = '<div class="image-box d-flex align-items-center justify-content-between my-2">' +
	                            '<div class="form-group flex-grow-1 mr-2">' +
	                                '<label class="form-label image-label text-muted p-2 bg-light mt-2" for="image">Select Image</label>' +
	                                '<input type="file" class="form-control image-input" name="image" id="image">' +
	                            '</div>'+
	                            '<button class="remove-image btn btn-danger">Remove</button>' +
	                        '</div>';
	        $(".image-container").append(imageBox);
	    });

	    // Event listener for dynamically added remove-image buttons
	    $(".image-container").on("click", ".remove-image", function(){
	        $(this).closest(".image-box").remove(); // Remove the closest .image-box
	    });

	 // Event listener for dynamically added image inputs
	    $(".image-container").on("change", ".image-input", function(){
	        var label = $(this).siblings("label.image-label");
	        label.text("Imported").removeClass("text-muted").addClass("text-success");
	        label.removeClass("bg-light").addClass("bg-success");

	        // Find the closest image-box relative to the input that triggered the change event
	        var imageBox = $(this).closest(".image-box");
	        imageBox.addClass("bg-success"); // Change background color of the closest .image-box
	    });
	});

    </script>

	
<%@ include file="/views/seller/layout/footer.jsp"%>