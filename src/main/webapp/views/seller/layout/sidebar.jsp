<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <div class="sidebar">
				<div class="scrollbar-inner sidebar-wrapper">
					<div class="user">
						<div class="photo">
						
									<c:if test="${seller.image == 'assets/images/troll.jpg'}">
                                		<img class="<c:if test='${seller.image != "assets/images/troll.jpg"}'> d-none </c:if>" src="${seller.image}">
                                	</c:if>
                                	<c:if test="${seller.image != null}">
                                		<img class="<c:if test='${seller.image == "assets/images/troll.jpg"}'> d-none </c:if>" src="${pageContext.request.contextPath}/assets/images/admin/${seller.image}" class="rounded border p-2" style="width: 120px !important; height: 100px !important;">
                                	</c:if>
						</div>
						<div class="info">
							<a class="" data-toggle="collapse" href="#collapseExample" aria-expanded="true">
								<span>
									${seller.name}
									<span class="user-level ">Seller</span>
								</span>
							</a>
							<div class="clearfix"></div>

						</div>
					</div>
					<ul class="nav">
						<li class="nav-item ${param.page == 'dashboard' ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/SellerController?page=dashboard">
								<i class="la la-dashboard"></i>
								<p>Dashboard</p>
							</a>
						</li>
						<li class="nav-item ${param.page == 'product' ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/SellerController?page=product&seller_id=${seller.id}">
								<i class="la la-user"></i>
								<p>Products</p>
							</a>
						</li>
                        <li class="nav-item ${param.page == 'order' ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/SellerController?page=order">
								<i class="las la-user-tie"></i>
								<p>Order</p>
							</a>
						</li>
                        <li class="nav-item ${param.page == 'history' ? 'active' : ''}">
							<a href="${pageContext.request.contextPath}/SellerController?page=history">
								<i class="las la-store"></i>
								<p>History</p>
							</a>
						</li>
                        <li class="nav-item ">
							<a href="">
								<i class="las la-list"></i>
								<p>Setting</p>
							</a>
						</li>
					</ul>
				</div>