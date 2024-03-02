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

						<h4 class="page-title">This is Messages Page</h4>
						
						<c:if test="${not empty success }">
						<div class="alert alert-success text-center " role="alert" id="errorAlert">
							${success}
						</div>
						</c:if>
						<c:if test="${not empty error }">
						<div class="alert alert-error text-center " role="alert" id="errorAlert">
							${error}
						</div>
						</c:if>
						

                        <!-- Table  -->
                        <div class="row">
								<table class="table">
								
								<thead class="thead-dark col-12">
								<tr>
								<th class="col-2">Name</th>
								<th class="col-2">Email</th>
								<th class="col-3">Phone</th>
								<th class="col-4">Message</th>
								</tr>
								</thead>
								<c:forEach items="${messages}" var="message">
								<tbody>
								
								<tr>
								<td>${message.name}</td>
								<td>${message.email}</td>
								<td>${message.phone}</td>
								<td>${message.message}</td>
								</tr>					
								</tbody>
								</c:forEach>							
								</table>
						</div>

						

					</div>
				</div>
				
			</div>




    <!-- Bootstrap JS (optional, for some features) -->
   <script>
    // Wait for the document to be ready
    document.addEventListener('DOMContentLoaded', function() {
        // Find the success alert element
        var successAlert = document.getElementById('errorAlert');
        
        // If the alert element exists
        if (successAlert) {
            // Set a timeout to hide the alert after 3 seconds
            setTimeout(function() {
                successAlert.style.display = 'none'; // Hide the alert
            }, 3000); // 3000 milliseconds = 3 seconds
        }
	    });
	</script>

</body>

<%@ include file="../layout/footer.jsp" %>

