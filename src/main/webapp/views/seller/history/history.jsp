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
			<div class="container-fluid">
				<h4 class="page-title">History</h4>
		        <div class="row">  
					<table class="table">
					  <thead class="bg-primary text-light" >
					    <tr>
					      <th scope="col" style="color: white" >ID</th>
					      <th scope="col" style="color: white">Price</th>
					      <th scope="col" style="color: white">Order_code</th>
					      <th scope="col" style="color: white">Count</th>
					      <th scope="col" style="color: white">Product_id</th>
					      <th scope="col" style="color: white">Customer_id</th>
					      <th scope="col" style="color: white">Shipping_id</th>
					      <th scope="col" style="color: white">Seller_id</th>
					      <th scope="col" style="color: white">Created_at</th>
					      <th scope="col" style="color: white">Updated_at</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <td>1</td>
					      <td>$100</td>
					      <td>1001</td>
					      <td>321</td>
					      <td>1</td>
					      <td>1</td>
					      <td>1234</td>
					      <td>50</td>
					      <td>1.1.2021</td>
					      <td>31.1.2024</td>
					    </tr>
					    <tr>
						     <td>1</td>
					      <td>$100</td>
					      <td>1001</td>
					      <td>321</td>
					      <td>1</td>
					      <td>1</td>
					      <td>1234</td>
					      <td>50</td>
					      <td>1.1.2021</td>
					      <td>31.1.2024</td>
					    </tr>
					  </tbody>
					</table>         
		        </div>
		        
		        
    		</div>             
		</div>			
	</div>
<%@ include file="/views/seller/layout/footer.jsp"%>