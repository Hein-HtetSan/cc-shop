package Controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.util.*;

import Models.*;

import DAO.*;




@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CartDAO cartDAO = null;
	ProductDAO productDAO = null;
	CategoryDAO categoryDAO = null;
	
	RequestDispatcher dispatcher = null;
       
    public CartController() throws ClassNotFoundException, SQLException {
        super();
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String error = request.getParameter("error");
		String success = request.getParameter("success");
		
		if(action != null) {
			
			switch(action) {
			
			case "main":
					list(request, response);
				break;
			
			case "addToCart":
					addToCart(request, response);
				break;
				
			case "addToCartFromDetail":
					addFromDetail(request, response);
				break;
				
			case "addToCartFromSeller":
				addToCartFromSeller(request, response);
				break;
				
			case "addToCartFromRelated":
				addFromRelated(request, response);
				break;
				
			case "deleteFromCart":
					deleteFromCart(request, response);
				break;
				
			case "validateCount":
					validateCount(request, response);
				break;
				
			case "getTotalItem":
					getTotalItem(request, response);
				break;
			
			
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			
			case "updateItemNumber":
				updateItemCount(request, response);
				break;
			
			}
		}
	}
	
	// product inside the card
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String error = request.getParameter("error");
		String success = request.getParameter("success");

		// get all category
		List<Category> categoires;
		try {
			int page_number = 1;
	        int recordsPerPage = 4;
	        // Get counts from utility method
	        if (request.getParameter("page_number") != null) {
	        	page_number = Integer.parseInt(request.getParameter("page_number")); 
	        }
			categoires = categoryDAO.get();
			List<Cart> carts = cartDAO.getProductinCartByUserIdWithPagination(Integer.parseInt(user_id), (page_number-1)*recordsPerPage,
                    recordsPerPage);
//			List<Cart> all_items = cartDAO.getProductinCartByUserId(Integer.parseInt(user_id));
			int noOfRecords = cartDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
			if(error != null) request.setAttribute("error", error);
	        if(success != null) request.setAttribute("success", success);
	        request.setAttribute("categories", categoires);
			request.setAttribute("carts", carts);
			request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page_number);
	        request.setAttribute("user_id", user_id);
//	        request.setAttribute("all_item", all_items);
			dispatcher = request.getRequestDispatcher("/views/user/cart/list.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// add to cart
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String product_id = request.getParameter("product_id");
		String category_id = request.getParameter("category_id");
		int count = 1;
		try {
			// check the item is whether exist or not in cart
			Cart fetch_cart = cartDAO.getByProductID(Integer.parseInt(product_id), Integer.parseInt(user_id));
			if(fetch_cart != null) {
				if(category_id == null) {
					String error = "Item already exist! You can go checkout page and make changes.";
					String encoded = URLEncoder.encode(error, "UTF-8");
					response.sendRedirect(request.getContextPath() + "/UserController?page=main&error="+encoded);
					return;
				}else {
					String error = "Item already exist! You can go checkout page and make changes.";
					String encoded = URLEncoder.encode(error, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/UserController?page=fetchByCategory&category_id="+category_id+"&user_id="+user_id+"&error="+encoded);
		    		return;
				}
			}else {
				Product specific_product = productDAO.getProductById(Integer.parseInt(product_id));
				int price = specific_product.getPrice();
				Cart cart = new Cart();
				cart.setProduct_id(Integer.parseInt(product_id));
				cart.setCustomer_id(Integer.parseInt(user_id));
				cart.setCount(count);
				cart.setPrice(price);

				boolean flag = cartDAO.create(cart); // if items not exist then add
				
				if(category_id != null && flag == true) {
					String success = "Add to cart success";
					String encoded = URLEncoder.encode(success, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/UserController?page=fetchByCategory&category_id="+category_id+"&user_id="+user_id+"&success="+encoded);
		    		return;
				}else {
					String success = "Add to cart success";
					String encoded = URLEncoder.encode(success, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/UserController?page=main&success="+encoded);
		    		return;
				}
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// add to cart from related product
	private void addFromRelated(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String product_id = request.getParameter("product_id");
		String seller_id = request.getParameter("seller_id");
		String previous_product_id = request.getParameter("previous_product_id");
		System.out.println("pre : " + previous_product_id);
		int count = 1;
		try {
			// check the item is whether exist or not in cart
			Cart fetch_cart = cartDAO.getByProductID(Integer.parseInt(product_id), Integer.parseInt(user_id));
			if(fetch_cart != null) {
				String error = "Item already exist! You can go checkout page and make changes.";
				String encoded = URLEncoder.encode(error, "UTF-8");
				  	response.sendRedirect(request.getContextPath() + "/UserController?page=productDetail&error="+encoded+"&product_id="+previous_product_id);
				  	return;
			}else {
				Product specific_product = productDAO.getProductById(Integer.parseInt(product_id));
				int price = specific_product.getPrice();
				Cart cart = new Cart();
				cart.setProduct_id(Integer.parseInt(product_id));
				cart.setCustomer_id(Integer.parseInt(user_id));
				cart.setCount(count);
				cart.setPrice(price);

				boolean flag = cartDAO.create(cart); // if items not exist then add
				
				if(flag) {
					String success = "Add to cart success";
					String encoded = URLEncoder.encode(success, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/UserController?page=productDetail&success="+encoded+"&product_id="+previous_product_id);
				}else {
					String error = "Can't add the item";
					String encoded = URLEncoder.encode(error, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/UserController?page=productDetail&error="+encoded+"&product_id="+previous_product_id);
				}
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// add to cart from detail
		private void addFromDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String user_id = request.getParameter("user_id");
			String product_id = request.getParameter("product_id");
			String count = request.getParameter("quantity");
			System.out.println(user_id);
			System.out.println(product_id);
			System.out.println(count);
			try {
				// check the item is whether exist or not in cart
				Cart fetch_cart = cartDAO.getByProductID(Integer.parseInt(product_id), Integer.parseInt(user_id));
				if(fetch_cart != null) {
					JsonObject jsonResponse = new JsonObject();
		            jsonResponse.addProperty("error", "Items already exist in your cart!");
		            
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write(jsonResponse.toString());
		            return;
				}else {
					Product specific_product = productDAO.getProductById(Integer.parseInt(product_id));
					int price = specific_product.getPrice();
					Cart cart = new Cart();
					cart.setProduct_id(Integer.parseInt(product_id));
					cart.setCustomer_id(Integer.parseInt(user_id));
					cart.setCount(Integer.parseInt(count));
					cart.setPrice(price);

					boolean flag = cartDAO.create(cart); // if items not exist then add
					
					if(flag) {
						JsonObject jsonResponse = new JsonObject();
			            jsonResponse.addProperty("success", "Added to the cart successfully!");
			            
			            response.setContentType("application/json");
			            response.setCharacterEncoding("UTF-8");
			            response.getWriter().write(jsonResponse.toString());
					}
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		private void addToCartFromSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String user_id = request.getParameter("user_id");
			String product_id = request.getParameter("product_id");
			String seller_id = request.getParameter("seller_id");
			String previous_product_id = request.getParameter("previous_product_id");
			System.out.println("pre : " + previous_product_id);
			int count = 1;
			try {
				// check the item is whether exist or not in cart
				Cart fetch_cart = cartDAO.getByProductID(Integer.parseInt(product_id), Integer.parseInt(user_id));
				if(fetch_cart != null) {
					String error = "Item already exist! You can go checkout page and make changes.";
					String encoded = URLEncoder.encode(error, "UTF-8");
					  	response.sendRedirect(request.getContextPath() + "/UserController?page=seller&seller_id="+seller_id+"&error="+encoded+"&product_id="+previous_product_id);
					  	return;
				}else {
					Product specific_product = productDAO.getProductById(Integer.parseInt(product_id));
					int price = specific_product.getPrice();
					Cart cart = new Cart();
					cart.setProduct_id(Integer.parseInt(product_id));
					cart.setCustomer_id(Integer.parseInt(user_id));
					cart.setCount(count);
					cart.setPrice(price);

					boolean flag = cartDAO.create(cart); // if items not exist then add
					
					if(flag) {
						String success = "Add to cart success";
						String encoded = URLEncoder.encode(success, "UTF-8");
			    		response.sendRedirect(request.getContextPath() + "/UserController?page=seller&seller_id="+seller_id+"&success="+encoded+"&product_id="+previous_product_id);
					}else {
						String error = "Can't add the item";
						String encoded = URLEncoder.encode(error, "UTF-8");
			    		response.sendRedirect(request.getContextPath() + "/UserController?page=seller&seller_id="+seller_id+"&error="+encoded+"&product_id="+previous_product_id);
					}
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}

	// delete from cart
		private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			String user_id = request.getParameter("user_id");
			String where = request.getParameter("where");
			String cart_id = request.getParameter("cart_id");
			
			boolean flag = cartDAO.delete(Integer.parseInt(cart_id));
			
			if(flag == true && where == null) {
				String success = "Remove from cart success";
				String encoded = URLEncoder.encode(success, "UTF-8");
	    		response.sendRedirect(request.getContextPath() + "/UserController?page=main&success="+encoded);
			}else {
				String success = "Remove from cart success";
				String encoded = URLEncoder.encode(success, "UTF-8");
	    		response.sendRedirect(request.getContextPath() + "/CartController?action=main&success="+encoded+"&user_id="+user_id);
			}
		}
		
	// valide product count
		private void validateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String quantity = request.getParameter("quantity");
			String product_id = request.getParameter("product_Id");
			System.out.println(quantity);
			System.out.println(product_id);
			// check the product count
			try {
				Product product = productDAO.getProductById(Integer.parseInt(product_id));
				
				System.out.println(product.getName() + " have count - " + product.getCount());
				
				if(Integer.parseInt(quantity) > product.getCount()) {
					 // If quantity exceeds available count
		            JsonObject jsonResponse = new JsonObject();
		            jsonResponse.addProperty("message", "Only " + product.getCount() + " number of products are available!");
		            jsonResponse.addProperty("maxQuantity", product.getCount());
		            
		            product = null;
		            
		            response.setContentType("application/json");
		            response.setCharacterEncoding("UTF-8");
		            response.getWriter().write(jsonResponse.toString());
				}
			
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		// update item count from cart
		private void updateItemCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String user_id = request.getParameter("user_id");
			String product_id = request.getParameter("product_id");
			String change = request.getParameter("change");
			String cart_id = request.getParameter("cart_id");
			int int_change_value = Integer.parseInt(change);
			
			System.out.println(user_id);
			System.out.println(product_id);
			System.out.println(cart_id);
			System.out.println(change + "\n");
			
			// check the product count
			try {
				Product product = productDAO.getProductById(Integer.parseInt(product_id));
				
				System.out.println(product.getName() + " have count - " + product.getCount());
				
				// if product goes to 0 then delete it
				if(int_change_value == 0) {
					boolean item_deleted = cartDAO.delete(Integer.parseInt(cart_id));
					if(item_deleted) {
						System.out.println("item deleted");
						JsonObject jsonResponse = new JsonObject();
		                 jsonResponse.addProperty("maxQuantity", int_change_value);

		                 response.setContentType("application/json");
		                 response.setCharacterEncoding("UTF-8");
		                 response.getWriter().write(jsonResponse.toString());
					}
				}
				
				
				// if item count exceeded then return error
				if(int_change_value > product.getCount()) {
					 // If quantity exceeds available count
	                 JsonObject jsonResponse = new JsonObject();
	                 jsonResponse.addProperty("message", "Only " + product.getCount() + " number of products are available!");
	                 jsonResponse.addProperty("maxQuantity", product.getCount());
	                 
	                 product = null;
	                 
	                 response.setContentType("application/json");
	                 response.setCharacterEncoding("UTF-8");
	                 response.getWriter().write(jsonResponse.toString());
				}else {
					// update item count section
					if(int_change_value > product.getCount()) int_change_value -= 1;
					// increase the item count
					boolean increased = cartDAO.update(Integer.parseInt(cart_id), int_change_value);
					if(increased) {
						// If quantity exceeds available count
		                 JsonObject jsonResponse = new JsonObject();
		                 jsonResponse.addProperty("message", "count changed to " + int_change_value);
		                 
		                 product = null;
		                 
		                 response.setContentType("application/json");
		                 response.setCharacterEncoding("UTF-8");
		                 response.getWriter().write(jsonResponse.toString());
					}
				}
				
				
						
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	// get all cart item by userid
	private void getTotalItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		System.out.println(user_id);
		List<Cart> all_items = cartDAO.getProductinCartByUserId(Integer.parseInt(user_id));
		// total item 
		int total_item = all_items.size();
		double total_price = 0.0;
		for(Cart item : all_items) {
			double temp = item.getCount() * item.getPrice();
			total_price += temp;
		}
		JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("total_price", total_price);
        jsonResponse.addProperty("total_item", total_item);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
	}
	
}
