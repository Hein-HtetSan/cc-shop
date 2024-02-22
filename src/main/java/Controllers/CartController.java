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


import java.util.*;

import Models.*;

import DAO.*;




@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CartDAO cartDAO = null;
	ProductDAO productDAO = null;
	
	RequestDispatcher dispatcher = null;
       
    public CartController() throws ClassNotFoundException, SQLException {
        super();
        cartDAO = new CartDAO();
        productDAO = new ProductDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String error = request.getParameter("error");
		String success = request.getParameter("success");
		
		if(action != null) {
			
			switch(action) {
			
			case "addToCart":
					addToCart(request, response);
				break;
			
			
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	// add to cart
	private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String product_id = request.getParameter("product_id");
		int count = 1;
		try {
			Product specific_product = productDAO.getProductById(Integer.parseInt(product_id));
			int price = specific_product.getPrice();
			Cart cart = new Cart();
			cart.setProduct_id(Integer.parseInt(product_id));
			cart.setCustomer_id(Integer.parseInt(user_id));
			cart.setCount(count);
			cart.setPrice(price);
			boolean flag = cartDAO.create(cart);
			if(flag) {
				String success = "Add to cart success";
				String encoded = URLEncoder.encode(success, "UTF-8");
	    		response.sendRedirect(request.getContextPath() + "/UserController?page=main&success="+encoded);
			}else {
				String error = "Can't update the profile";
				String encoded = URLEncoder.encode(error, "UTF-8");
	    		response.sendRedirect(request.getContextPath() + "/UserController?page=main&error"+encoded);
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}
