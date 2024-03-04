<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="./layout/header.jsp"%>


	<div class="wrapper">
	
	
		<!-- #### Main header #### -->
		<%@ include file="./layout/navbar.jsp" %>
		<!-- #### End of main header #### -->

		<!-- ### sidebar -->
		<%@ include file="./layout/sidebar.jsp" %>
		<!-- ### End of sidebar -->
	</div>
	<div class="main-panel">
		<div class="content">
			<div class="container-fluid">
				<h4 class="page-title">Dashboard</h4>
				
				<input type="hidden" value="${seller.id}" id="seller_id">

                        <!-- Content goes here  -->
                       <div class="row">
							<div class="col-md-3">
								<div class="card card-stats card-warning">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-money"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Total Sales</p>
													<h4 class="card-title">${total_sale} MMKs</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="card card-stats card-success">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-box"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Total Orders</p>
													<h4 class="card-title">${total_order}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="card card-stats card-primary">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-users"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Customers</p>
													<h4 class="card-title">${total_user}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-3">
								<div class="card card-stats card-danger">
									<div class="card-body ">
										<div class="row">
											<div class="col-5">
												<div class="icon-big text-center">
													<i class="la la-box"></i>
												</div>
											</div>
											<div class="col-7 d-flex align-items-center">
												<div class="numbers">
													<p class="card-category">Total Products</p>
													<h4 class="card-title">${total_product}</h4>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							
						</div>
						<div class="row">
							<div class="col-md-8" style="margin-bottom: 2rem;">
									<h4>Order analysis (This week)</h4>
									<canvas id="myLineChart" width="300" height="150"></canvas>
							</div>
							<div class="col-md-4">
								<div class="row">
									<div class="col-12" >
										<h6>Top Selling Items</h6>
										<div class="card" id="media-wrapper" id="top-selling" style="padding: .8rem 1rem; border-radius: 10px;">
											<!-- item goes here -->
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
	
	<!-- Include Chart.js library from CDN -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <!-- ajax for chart and top selling -->
    <script>

 // Define sellerID after the DOM has loaded
    document.addEventListener("DOMContentLoaded", function() {
        var sellerID = document.getElementById("seller_id").value;
        var labels = [];
        var datas = [];
        
     // get the weekly data
        function fetchChartData() {
            const url = "http://localhost:9095/shop-dot-com/SellerController?action=fetchForChart";
            var ctx = document.getElementById('myLineChart').getContext('2d');
            
            const dataToSend = {
                seller_id: sellerID,
            };
            fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": 'application/json'
                    },
                    body: JSON.stringify(dataToSend)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("responding is error");
                    }
                    return response.json(); // Convert the response to JSON format
                })
                .then(data => {
                	const orders = data.data_array; // Access the JSON data
                	// Arrays to store dates and counts
                	const datesArray = [];
                	const countsArray = [];
                	console.log(orders);
                	const ordersArray = Object.entries(orders);

                	// Sort the ordersArray by date (key)
                	ordersArray.sort(([dateA], [dateB]) => {
                	    // Compare the dates (keys)
                	    return new Date(dateA) - new Date(dateB);
                	});
                	console.log(ordersArray);
                	// Iterate over key-value pairs in dates_with_total_order
                	ordersArray.forEach(([date, count]) => {
                	    datesArray.push(date);
                	    countsArray.push(count);
                	});
                	labels = datesArray;
                	datas = countsArray;
                	var data = {
                            labels: labels,
                            datasets: [{
                                label: 'Order Count',
                                data: datas,
                                fill: false, // Disable fill under the line
                                borderColor: 'rgb(75, 192, 192)', // Line color
                                lineTension: 0.4 // Curvature of the lines (0 = straight lines, 1 = very curved)
                            }]
                        };
                        var myLineChart = new Chart(ctx, {
                            type: 'line',
                            data: data,
                        });
                    
                })
                .catch(error => {
                    console.error('Error:', error);
                    // Handle the error here
                });
        }

        
        


        // Define fetchData function getting the top selling product
        function fetchData() {
            console.log("fetching");
            console.log("seller id " + sellerID);

            // Define the URL of the controller endpoint
            const url = 'http://localhost:9095/shop-dot-com/SellerController?action=fetchRealData';

            // Define the data you want to send (if any)
            const dataToSend = {
                seller_id: sellerID,
            };

            // Make the AJAX fetch request with data
            fetch(url, {
                method: 'POST', // or 'PUT', 'DELETE', etc. depending on your API
                headers: {
                    'Content-Type': 'application/json' // Specify content type as JSON
                },
                body: JSON.stringify(dataToSend) // Convert data to JSON string
            })
            .then(response => {
                // Check if the response is OK
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                // Parse JSON response
                return response.json();
            })
            .then(data => {
            	// Access the "top_products" array from the JSON response
            	const products = data.top_products;
            	const container = document.getElementById('media-wrapper');
            	console.log(products.length);
            	if(products.length === 0){
            		console.log("no product");
            		container.innerHTML = "<b>No product</b>";
            	}else{
            		container.innerHTML = "";
                	products.forEach(product => {
                	    // Create the inner card-body element
                	    const cardBodyDiv = document.createElement('div');
                	    cardBodyDiv.classList.add('card', 'card-body');
                	    // Create the inner mediaelement
                	    const mediaDiv = document.createElement('div');
                	    mediaDiv.classList.add('media', 'align-items-center', 'align-items-lg-start', 'text-center', 'text-lg-left', 'flex-column', 'flex-lg-row');
                	    // Create the inner media body
                	    const mediaBodyDiv = document.createElement('div');
                	    mediaBodyDiv.classList.add('media-body', 'd-flex');
                	    // Create the inner elements for the media body
                	    mediaBodyDiv.innerHTML = `
                	        <i class="las la-arrow-up text-success" style="font-size: 1.5rem; margin-right: 1.5rem;"></i>
                	        <h6 class="media-title font-weight-semibold">
                	            <a href="#" data-abc="true" class="" style="margin-right:1rem; font-size: 18px !important;">\${product.product_name}</a> 
                	        </h6>
                	        <ul class="list-inline list-inline-dotted mb-3 mb-lg-2 " style="margin-right:1rem">
                	            <li class="list-inline-item"><a href="#" class="text-muted" data-abc="true">\${product.price}Ks</a></li>
                	        </ul>
                	    `;
                	    // Append the inner elements
                	    mediaDiv.appendChild(mediaBodyDiv);
                	    cardBodyDiv.appendChild(mediaDiv);
                	    // Append the card to the container
                	    container.appendChild(cardBodyDiv);
                	});
            	}

            })
            .catch(error => {
                console.error('There was a problem with your fetch operation:', error);
            });
        }

        // Call fetchData() every 1500 milliseconds
        setInterval(fetchData, 1000); // fetching in real time
        setInterval(fetchChartData, 1000); // fetching in real time
        
     	
    });




    </script>
    
	
	
	
	
<%@ include file="./layout/footer.jsp"%>