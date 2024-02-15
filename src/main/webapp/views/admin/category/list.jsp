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
						<h4 class="page-title">   Categorys</h4>

                        <!-- Content goes here  -->
                        <div class="row">
	                        <div class="col-12 col-md-5">
	                            	<div class="card shadow">
	                            		<div class="card-header">
	                            			<h6 class="text-center fw-semibold"> <i class="las la-list"></i> Add Category</h6>
	                            		</div>
	                            		<div class="card-body">
	                            			<form method="post" action="${pageContext.request.contextPath}/CategoryController?action=addCategory">
	                            				<div class="form-group">
	                            				<input type="hidden" name="action" value="saveCategory">
                            					<label class="form-label" for="name">Name</label>
                            					<input type="text" placeholder="Enter category name" name="name" class="form-control mb-4" required>
                            					<button class="btn btn-primary"> <i class="las la-save"></i> Add Category</button>
                            				</div>
                            			</form>
                            		</div>
                            	</div>
                            </div>
                            <div class="col-12 col-md-7">
                                <div class="card card-tasks">
									<div class="card-header ">
										<h4 class="card-title"> <i class="las la-list"></i> List</h4>
										<p class="card-category">  You can see who are using your platform.</p>
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
														<th>ID</th>
														<th>Name</th>
														<th>Action</th>
													</tr>
												</thead>
                                                <!-- #### end of table head ###  -->
												<tbody>
                                                    <!-- #### Table Body Start #####  -->
                                                    <c:if test="${categoryList != null}">
	                                                    <c:forEach items="${categoryList}" var="category">
														<tr>
															<td>
																<div class="form-check">
																	<label class="form-check-label">
																		<input class="form-check-input task-select" type="checkbox">
																		<span class="form-check-sign"></span>
																	</label>
																</div>
															</td>
															<td>${category.id}#</td>
															<td>${category.name}</td>
															<td class="td-actions">
																<div class="form-button-action">
																	<a href="${pageContext.request.contextPath}/AdminController?action=editCategory&category_id=${category.id}">
																	<button type="button" data-toggle="tooltip" title="Edit" class="btn btn-link btn-simple-primary">
																		<i class="las la-pen"></i>
																	</button>
																	</a>
																	<a href="${pageContext.request.contextPath}/AdminController?action=deleteCategory&category_id=${category.id}">
																	<button type="button" data-toggle="tooltip" title="Remove" class="btn btn-link btn-simple-danger">
																		<i class="las la-times"></i>
																	</button>
																	</a>
																</div>
															</td>
														</tr>
														</c:forEach>
													</c:if>
													<c:if test="${categoryList == null}">
												        <p class="text-danger fw-semibold fs-5 text-center">No category here.</p>
												    </c:if>
													<!-- #### Table body ####  -->
												</tbody>
											</table>
										</div>
										<nav aria-label="Page navigation example">
										  <ul class="pagination">
										    <c:if test="${currentPage != 1}">
										        <li class="page-item"><a href="${pageContext.request.contextPath}/AdminController?page=category&page_number=${currentPage - 1}" class="page-link">Previous</a></li>
											</c:if> 
										    <c:forEach begin="1" end="${noOfPages}" var="i"> 
								              <c:choose> 
								                  <c:when test="${currentPage eq i}"> 
								                      <li class="page-item"><a class="page-link bg-primary text-light" href="${pageContext.request.contextPath}/AdminController?page=category?page=category&page_number=${i}">${i}</a></td> 
								                  </c:when> 
								                  <c:otherwise> 
								                      <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/AdminController?page=category&page_number=${i}">${i}</a></td> 
								                  </c:otherwise> 
								              </c:choose> 
								          </c:forEach> 
										    <c:if test="${currentPage lt noOfPages}">
										        <li class="page-item"><a href="${pageContext.request.contextPath}/AdminController?page=category&page_number=${currentPage + 1}" class="page-link">Next</a></td>
										    </c:if>
										  </ul>
										</nav>
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
	<c:if test="${message}">
		<div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog" aria-labelledby="modalUpdatePro" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header bg-primary">
						<h6 class="modal-title"><i class="la la-frown-o"></i> Notification</h6>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body text-center">									
						<p>
							<b>${message}</b>
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	


</body>

<%@ include file="../layout/footer.jsp" %>




