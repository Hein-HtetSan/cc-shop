<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>

<%@ include file="../layout/header.jsp" %>



	<div class="wrapper">
	
	
		<!-- #### Main header #### -->
		<%@ include file="../layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="../layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
			</div>

			<div class="main-panel">
				<div class="content">
					<div class="container-fluid">
						<h4 class="page-title">   Products</h4>

                        <!-- Content goes here  -->
                        <div class="row">
                            <div class="col-12">
                                <div class="card card-tasks">
									<div class="card-header ">
										<h4 class="card-title"><i class="las la-list"></i> List</h4>
										<p class="card-category">  You can see what product are selling your platform.</p>
									</div>
									<div class="card-body sellerTable">
										<div class="table-full-width">
											<table class="table ">
                                                <!-- ### Table head ####  -->
												<thead>
													<tr>
														<th>
															<div class="form-check">
																<label class="form-check-label">
																	<input class="form-check-input  select-all-checkbox" type="checkbox" data-select="checkbox" data-target=".task-select">
																	<span class="form-check-sign"></span>
																</label>
															</div>
														</th>
														<th>Product Name</th>
														<th>Seller Name</th>
														<th>rating</th>
														<th>Price</th>
														<th>Status</th>
														<th>Action</th>
													</tr>
												</thead>
                                                <!-- #### end of table head ###  -->
												<tbody>
                                                    <!-- #### Table Body Start #####  -->
                                                    <c:if test="${productList != null}">
                                                    <c:forEach items="${productList}" var="product">
													<tr>
														<td>
															<div class="form-check">
																<label class="form-check-label">
																	<input class="form-check-input task-select" type="checkbox">
																	<span class="form-check-sign"></span>
																</label>
															</div>
														</td>
														<td>${product.name}</td>
														<td>${product.seller_name}</td>
														<td>${product.rating}</td>
														<td>1${product.price}$</td>
														<td>
															<c:choose>
															    <c:when test="${product.count > 0}">
															        <span>In Stock</span>
															    </c:when>
															    <c:otherwise>
															        <span>Out of Stock</span>
															    </c:otherwise>
															</c:choose>

														</td>
														<td class="td-actions">
															<div class="form-button-action">
																<button type="button" data-toggle="tooltip" title="See Detail" class="btn btn-link btn-simple-primary">
																	<i class="las la-eye"></i>
																</button>
																<button type="button" data-toggle="tooltip" title="Remove" class="btn btn-link btn-simple-danger">
																	<i class="las la-times"></i>
																</button>
															</div>
														</td>
													</tr>
													</c:forEach>
													</c:if>
													<c:if test="${productList == null}">
														<p class="text-danger fw-semibold fs-5 text-center">No product here.</p>
													</c:if>
													<!-- #### Table body ####  -->
												</tbody>
											</table>
										</div>
									</div>
									
								</div>
                            </div>
                        </div>

					</div>
				</div>
				
			</div>
			
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header bg-primary">
					<h6 class="modal-title"><i class="la la-frown-o"></i> Under Development</h6>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body text-center">									
					<p>Currently the pro version of the <b>Ready Dashboard</b> Bootstrap is in progress development</p>
					<p>
						<b>We'll let you know when it's done</b></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>

<%@ include file="../layout/footer.jsp" %>




