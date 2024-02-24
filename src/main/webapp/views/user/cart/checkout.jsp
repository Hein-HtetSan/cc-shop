<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<%@ include file="/views/user/layout/withoutSearchBar.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>

<style>
	
	.address-wrapper{
		width: 100%;
		height: 250px;
		border: none;
		overflow-y: scroll;
		padding: 0 2rem 0 1rem;
		margin-bottom: 3rem;
	}
	.address-card{
		padding: 3rem;
		border: 1px solid #ccc;
		border-radius: 5px;
		margin-bottom: 2rem;
	}
	.new-address{
		padding: 1rem 2rem;
		border: none;
		background-color: #D10024;
		border-radius: 2rem;
		color: #fff;
	}
	.new-address:hover{
		background-color: #D10020;
	}
	.remove-btn{
		margin-left: 2.5rem; 
		margin-top: 2rem;
		border: none;
		background-color: #D10024;
		border-radius: 2rem;
		color: #fff;
		padding: .5rem 1rem;
	}
	.remove-btn:hover{
		color: #fff;
		background-color: #000;
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
							<li><a href="${pageContext.request.contextPath}/CartController?action=main&user_id=${customer.id}">BACK</a></li>
							<li class="active">checkout</li>
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
				
					<div class="col-md-7">

						<!-- Shiping Details -->
						<div class="shiping-details">
							<div class="section-title">
								<h3 class="title">Shiping address</h3>
							</div>
							
							
								<c:choose>
									<c:when test="${addresses.size() > 0 }">
									<div class="address-wrapper">
										<c:forEach items="${addresses}" var="address">
										<!-- address card -->
										<div class="card address-card">
										    <div class="row" style="display: flex; align-items: center; margin-bottom: 2rem;">
										        <div class="col-4" style="margin-right: 1rem; padding: 1rem;">
										        	<input type="hidden" id="address_id" value="${address.id}" >
										            <input type="radio" class="address-radio" name="selected-address">
										        </div>
										        <div class="col-8">
										            <h5>${address.street_address}, ${address.city }, ${address.state }</h5>
										            <h6>Country - ${address.country}</h6>
										            <span class="">Postal Code - ${address.postal_code}</span>
										        </div>
										    </div>
										    <a href="${pageContext.request.contextPath}/AddressController?action=delete&id=${address.id}&user_id=${customer.id}" class="remove-btn" style="">Remove</a>
										</div>
										</c:forEach>
										<!-- end of address card -->
									</div>
									</c:when>
									<c:otherwise>
										<h5 class="text-center">No Address, Please create one!</h5>
									</c:otherwise>
								</c:choose>
							
							
							<!-- Order notes -->
							<div class="order-notes" style="margin-bottom: 2rem;">
								<h5>Notes</h5>
								<textarea class="input" placeholder="Order Notes"></textarea>
							</div>
							<!-- /Order notes -->
							
							<h3 class="title" style="margin-bottom: 1rem;">Add new address</h3>
							<div class="input-checkbox">
								<input type="checkbox" id="shiping-address">
								<label for="shiping-address">
									<span></span>
									Check to add new address
								</label>
								<form class="caption" action="${pageContext.request.contextPath}/AddressController" method="post">
									<input type="hidden" name="action" value="add" >
									<input type="hidden" name="user_id" value="${customer.id}" >
									<div class="form-group">
										<input class="input" type="text" name="street_address" placeholder="Street Address">
									</div>
									<div class="form-group">
										<input class="input" type="text" name="city" placeholder="City">
									</div>
									<div class="form-group">
										<input class="input" type="text" name="state" placeholder="State">
									</div>
									<div class="form-group">
										<input class="input" type="text" name="postal_code" placeholder="Postal Code">
									</div>
									<div class="form-group">
										<input class="input" type="text" name="country" placeholder="Country">
									</div>
									<div class="form-group">
										<button type="submit" class="new-address">Create new address</button>
									</div>
								</form>
							</div>
						</div>
						<!-- /Shiping Details -->
					</div>
					
					<!-- Order Details -->
					<div class="col-md-5 order-details">
						<div class="section-title text-center">
							<h3 class="title">Your Order</h3>
						</div>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>PRODUCT</strong></div>
								<div><strong>TOTAL</strong></div>
							</div>
							<div class="order-products">
								<c:forEach items="${items}" var="item">
									<div class="order-col">
										<div>${item.count}x ${item.product_name}</div>
										<div>${item.price} MMKs</div>
									</div>
								</c:forEach>
							</div>
							<div class="order-col">
								<div>Shiping</div>
								<div><strong>FREE</strong></div>
							</div>
							<div class="order-col">
								<div><strong>TOTAL</strong></div>
								<div><strong class="order-total">${total_price} <small>MMKs</small></strong></div>
							</div>
						</div>
						<div class="input-checkbox">
							<input type="checkbox" id="terms">
							<label for="terms">
								<span></span>
								I've read and accept the <a href="#">terms & conditions</a>
							</label>
						</div>
						<a href="#" id="order-submit" class="primary-btn order-submit">Place order</a>
					</div>
					<!-- /Order Details -->
				</div>
				<!-- row -->
				
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script>
		    // Wait for the document to be ready
		    $(document).ready(function() {
		        // Find the error alert element
		        var $errorAlert = $('#errorAlert');
		        $('#alert-error').hide();
		        $('#alert-success').hide();
		        
		        // If the alert element exists
		        if ($errorAlert.length) {
		            // Set a timeout to hide the alert after 3 seconds
		            setTimeout(function() {
		                // Fade out the alert over 0.5 seconds
		                $errorAlert.fadeOut(500);
		            }, 3000); // 3000 milliseconds = 3 seconds
		        }
		        
		        // Event listener for the button click
		        $('#order-submit').click(function(e) {
		        	e.preventDefault();
		            // Find the checked radio button
		            var selectedAddressId = $('.address-radio:checked').siblings('#address_id').val();

		            // Check if any radio button is checked
		            if (selectedAddressId) {
		                // Send the selected address ID to the servlet using AJAX
		                $.ajax({
		                    url: 'http://localhost:9095/shop-dot-com/CheckoutController?action=order',
		                    method: 'POST',
		                    data: {
		                        addressId: selectedAddressId,
		                    },
		                    success: function(response) {
		                        // Handle success response if needed
		                        console.log('Address ID sent successfully');
		                    },
		                    error: function(xhr, status, error) {
		                        // Handle error if needed
		                        console.error('Error:', error);
		                    }
		                });
		            } else {
		                // Handle the case when no radio button is checked
		                console.log('No address selected');
		            }
		        });
		        
		    });
		</script>
		
		
		<%@ include file="/views/user/layout/footer.jsp" %>
		
		

