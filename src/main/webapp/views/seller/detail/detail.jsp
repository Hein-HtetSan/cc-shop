<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="/views/seller/layout/header.jsp" %>

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
			<!-- content -->
			<section class="py-5">
			  <div class="container">
			    <div class="row gx-5">
			      <aside class="col-lg-6">
			        <div class="border rounded-4 mb-3 d-flex justify-content-center">
			          <a data-fslightbox="mygalley" class="rounded-4" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big.webp">
			            <img style="max-width: 100%; max-height: 100vh; margin: auto;" class="rounded-4 fit" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big.webp" />
			          </a>
			        </div>
			        <div class="d-flex justify-content-center mb-3">
			          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big1.webp" class="item-thumb">
			            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big1.webp" />
			          </a>
			          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big2.webp" class="item-thumb">
			            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big2.webp" />
			          </a>
			          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big3.webp" class="item-thumb">
			            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big3.webp" />
			          </a>
			          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big4.webp" class="item-thumb">
			            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big4.webp" />
			          </a>
			          <a data-fslightbox="mygalley" class="border mx-1 rounded-2" target="_blank" data-type="image" href="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big.webp" class="item-thumb">
			            <img width="60" height="60" class="rounded-2" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/items/detail1/big.webp" />
			          </a>
			        </div>
			        <!-- thumbs-wrap.// -->
			        <!-- gallery-wrap .end// -->
			      </aside>
			      <main class="col-lg-6">
			        <div class="ps-lg-3">
			          <h4 class="title text-dark">
			            Quality Men's Hoodie for Winter, Men's Fashion <br />
			            Casual Hoodie
			          </h4>
			          </div>
			
			          <div class="mb-3">
			            <span class="h5">$75.00</span>
			          </div>
			
			          <p>
			            Modern look and quality demo item is a streetwear-inspired collection that continues to break away from the conventions of mainstream fashion. Made in Italy, these black and brown clothing low-top shirts for
			            men.
			          </p>
			
			          <div class="row">
			            <dt class="col-3">Type:</dt>
			            <dd class="col-9">Regular</dd>
			
			            <dt class="col-3">Color</dt>
			            <dd class="col-9">Brown</dd>
			
			            <dt class="col-3">Material</dt>
			            <dd class="col-9">Cotton, Jeans</dd>
			
			            <dt class="col-3">Brand</dt>
			            <dd class="col-9">Reebook</dd>
			          </div>
			
			          <hr />
			          	<a href="#" class="btn btn-primary border border-secondary py-2 "> Update </a>
			          	<a href="#" class="btn btn-primary border border-secondary py-2 "> Save </a>
			          	<a href="${pageContext.request.contextPath }/SellerController?page=product" class="btn btn-primary border border-secondary py-2 "> Back </a>
			          </div>

			        </div>
			      </main>
			    </div>
			  </div>
			</section>
			<!-- content -->
		
		
		</div>
	</div>
<%@ include file="/views/seller/layout/footer.jsp"%>