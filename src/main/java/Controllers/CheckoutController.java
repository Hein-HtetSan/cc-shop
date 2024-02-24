package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.*;
import Models.*;
import java.util.*;


public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher dispatcher = null;
	ProductDAO productDAO = null;
	CartDAO cartDAO = null;
	CustomerDAO customerDAO = null;
	CategoryDAO categoryDAO = null;
	AddressDAO addressDAO = null;

    public CheckoutController() throws ClassNotFoundException, SQLException {
        super();
        productDAO = new ProductDAO();
        cartDAO = new CartDAO();
        customerDAO = new CustomerDAO();
        categoryDAO = new CategoryDAO();
        addressDAO = new AddressDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
				
			case "main":
				checkoutPage(request, response);
				break;
			
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
				
			case "order":
				order(request, response);
				break;
			
			}
		}
	}
	
	// checout page
	private void checkoutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String success = request.getParameter("success");
		String error = request.getParameter("error");
		// get cart items
		List<Cart> items = cartDAO.getProductinCartByUserId(Integer.parseInt(user_id));
		double total_price = 0.0;
		for(Cart item : items) {
			double temp = item.getCount() * item.getPrice();
			total_price += temp;
		}
		 try {
				// get all categories
			List<Category> categoires = categoryDAO.get();
			// get all address by user_id
			List<Address> addresses = addressDAO.getByUserID(Integer.parseInt(user_id));
			// setto setattribute
			request.setAttribute("items", items);
			request.setAttribute("total_price", total_price);
			request.setAttribute("categories", categoires);
			request.setAttribute("addresses", addresses);
			if(success != null) request.setAttribute("success", success);
			if(error != null) request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/views/user/cart/checkout.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	// order action
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
