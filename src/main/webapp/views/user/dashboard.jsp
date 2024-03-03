<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="Models.Whistlist" %>
<%@ page import="DAO.WhistlistDAO" %>


<%@ include file="/views/user/layout/header.jsp" %>

<%@ include file="/views/user/layout/navbar.jsp" %>

<style>
    .grid-container {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
      
      gap: 20px;
    }
    .grid-item {
      background-color: #f0f0f0;
      padding: 20px;
      border: 1px solid #ccc;
    }
    .stock{
    	background-color: #D10024;
    	padding: 5px 1rem;
    	border-radius: 15px;
    	color: #fff;
    	margin-bottom: .6rem !important;
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
  
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <!-- SECTION NEW elect-->
		<div class="section search-section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="grid-container" >

							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->									
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
			

<!-- SECTION COLLECTION	-->
		<div class="section main-section">
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
				
				<!-- loading  -->
		<div id="loading-wrapper" class="d-none">
			<img src="${pageContext.request.contextPath}/assets/loading.gif" id="loading">
		</div>
		<!-- end of loading -->
			
				<!-- row -->
				<div class="row">
					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="${pageContext.request.contextPath}/assets/customer/img/shop01.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Electronic<br>Collection</h3>
								<a href="${pageContext.request.contextPath}/UserController?page=fetchByCategory&category_id=24&user_id=${customer.id}" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="${pageContext.request.contextPath}/assets/landing/img/licensed-image.jpg" alt="">
							</div>
							<div class="shop-body">
								<h3>Cosmetics<br>Collection</h3>
								<a href="${pageContext.request.contextPath}/UserController?page=fetchByCategory&category_id=2&user_id=${customer.id}" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="${pageContext.request.contextPath}/assets/landing/img/fashion.jpg" alt="">
							</div>
							<div class="shop-body">
								<h3>Fashion<br>Collection</h3>
								<a href="${pageContext.request.contextPath}/UserController?page=fetchByCategory&category_id=31&user_id=${customer.id}" class="cta-btn">Shop now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		
		<!-- SECTION NEW elect-->
		<div class="section main-section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
				
					<!-- Pagination -->
					<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<span class="store-qty">Showing ${products.size()} - ${total_product} products</span>
							<ul class="store-pagination">
								<c:if test="${currentPage != 1}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=main&page_number=${currentPage - 1}"><i class="fa fa-angle-left"></i></a></li>
								</c:if>
								<c:forEach begin="1" end="${noOfPages}" var="i"> 
									<c:choose> 
								       <c:when test="${currentPage eq i}"> 
								       		<li class="active" ><a style="color: white !important;" class="text-light" href="${pageContext.request.contextPath}/UserController?page=main&page_number=${i}">${i}</a></li>
								       </c:when>
								       <c:otherwise> 
								       		<li class=""><a href="${pageContext.request.contextPath}/UserController?page=main&page_number=${i}">${i}</a></li>
								       </c:otherwise>
								    </c:choose>
								</c:forEach>
								<c:if test="${currentPage lt noOfPages}">
									<li><a href="${pageContext.request.contextPath}/UserController?page=main&page_number=${currentPage + 1}"><i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</div>
						<!-- /store bottom filter -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="grid-container" >
						
							<c:forEach items="${products}" var="product">
									<div class="grid-item" >
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="${pageContext.request.contextPath}/assets/images/products/${product.image}" alt="">
												<div class="product-label">
													<span class="stock new">
														<c:if test="${product.count > 0 }">In Stock (${product.count })</c:if>
														<c:if test="${product.count == 0 }">Out of Stock</c:if>
													</span>
												</div>
											</div>
											<div class="product-body">
												<p class="product-category">${product.category_name}</p>
												<p class="product-category" style="font-weight: bold;">Seller: ${product.seller_name}</p>
												<h3 class="product-name"><a href="#">${product.name}</a></h3>
												<h4 class="product-price">${product.price}MMKs</h4>
												<div class="product-btns" style="margin-top: 10px !important;">
												<c:set var="product_id" value="${product.id}" />
												<c:set var="customer_id" value="${customer.id}" />
												
												<%
												    WhistlistDAO whistlistDAO = new WhistlistDAO();
												    int whist_id = whistlistDAO.get_by_customer_id_and_product_id((int) pageContext.getAttribute("product_id"), (int) pageContext.getAttribute("customer_id"));
												%>
												
												<% if (whist_id != 0) { %>
												    <button class="add-to-wishlist">
												        <a id="remove-from-wishlist-btn" class="" href="${pageContext.request.contextPath}/WhistlistController?action=removeFromWhistList&product_id=${product.id}&user_id=${customer.id}&wish_id=<%= whist_id %>">
												            <i class="fa fa-heart text-danger"></i><span class="tooltipp">Remove</span>
												        </a>
												    </button>
												<% } else { %>
												    <button class="add-to-wishlist">
												        <a id="add-to-wishlist-btn" class="" href="${pageContext.request.contextPath}/WhistlistController?action=addToWhistList&product_id=${product.id}&user_id=${customer.id}">
												            <i class="fa-regular fa-heart"></i><span class="tooltipp">Add to wishlist</span>
												        </a>
												    </button>
												<% } %>



													<button class="quick-view"><a id="quick-view-btn" class="" href="${pageContext.request.contextPath}/UserController?page=productDetail&product_id=${product.id}"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></a></button>
												</div>
											</div>
											<div class="add-to-cart">
												<c:if test="${product.count > 0 }">
													<a id="add-to-cart-btn" href="${pageContext.request.contextPath}/CartController?action=addToCart&user_id=${customer.id}&product_id=${product.id}">
														<button  class="add-to-cart-btn">
															<i class="fa fa-shopping-cart"></i> add to cart
														</button>
													</a>
												</c:if>
												<c:if test="${product.count == 0}">
													<button  class="add-to-cart-btn">
															<i class="fa fa-exclamation"></i>Out of Stock
													</button>
												</c:if>
											</div>
										</div>
										<!-- /product -->
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->									
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
		        
		        $("#add-to-cart-btn, #add-to-wishlist-btn, #remove-from-wishlist-btn, #quick-view-btn").click(function(event) {
		            console.log("clicked");
		            $("#loading-wrapper").removeClass("d-none");
		        });
		        
		        
		        
		        // If the alert element exists
		        if ($errorAlert.length) {
		            // Set a timeout to hide the alert after 3 seconds
		            setTimeout(function() {
		                // Fade out the alert over 0.5 seconds
		                $errorAlert.fadeOut(500);
		            }, 3000); // 3000 milliseconds = 3 seconds
		        }
		    });
		</script>

<%@ include file="/views/user/layout/footer.jsp" %>