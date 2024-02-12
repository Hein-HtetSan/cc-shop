package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SellerDAO;
import DAO.BusinessDAO;
import Models.Seller;
import Models.Business;
import java.util.*;


public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SellerDAO sellerDAO = null;
    BusinessDAO businessDAO = null;
    RequestDispatcher dispatcher = null;
	
    public SellerController() throws ClassNotFoundException, SQLException {
        super();
        sellerDAO = new SellerDAO();
        businessDAO = new BusinessDAO();
    }


    // Get Method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    		List<Business> businesses;
					try {
						businesses = businessDAO.get();
						request.setAttribute("businesses",businesses);
						dispatcher = request.getRequestDispatcher("views/seller/form.jsp");
			            dispatcher.forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    }
    
    
    // Post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the action type
		String action = request.getParameter("action");
		
		String name = request.getParameter("name"); // get name value
		String email = request.getParameter("email"); // get email value
		String password = request.getParameter("password"); // get password value
		String cpassword = request.getParameter("cpassword"); // get confirm password value
		String phone = request.getParameter("phone"); // get phone number 
		String image = "assets/images/troll.jpg"; // default image
		String company = request.getParameter("company");
		String business = request.getParameter("business");
		String address = request.getParameter("address");
		
		System.out.println(name);
		System.out.println(email);
		
		// doPost handle many action like register and login and so on.
		if(action != null) {
			switch(action) {
			
				case "login": // do the login process
					try {
						login(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
					break;
					
				case "register": // do the register process
					try { // check unexpected error with try and catch block
						register(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
					break;
					
				default:
					break;
					
			}
		}
		
	}
	
	
	// register servlet
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		// flag 
		boolean flag = false;
		// create new object
		Seller newSeller = new Seller();
		
		String name = request.getParameter("name"); // get name value
		String email = request.getParameter("email"); // get email value
		String password = request.getParameter("password"); // get password value
		String cpassword = request.getParameter("cpassword"); // get confirm password value
		String phone = request.getParameter("phone"); // get phone number 
		String image = "assets/images/troll.jpg"; // default image
		String company = request.getParameter("company");
		String business = request.getParameter("business");
		String address = request.getParameter("address");
		int business_id = Integer.parseInt(business);
		
		// Perform server-side validation
        if (isValid(name, email, password, cpassword)) {
        	newSeller.setName(name);
        	newSeller.setEmail(email);
        	newSeller.setPhone(phone);
        	newSeller.setPassword(cpassword);
        	newSeller.setImage(image);
        	newSeller.setCompany(company);
        	newSeller.setBusiness_id(business_id);
        	newSeller.setAddress(address);
        	
			// get return 
			flag = sellerDAO.create(newSeller);
			
			if(flag) {  // if flag true, then go dashboard.
				dispatcher = request.getRequestDispatcher("views/seller/dashboard.jsp");
			    dispatcher.forward(request, response);
			}
        } else {
            // Set error message and forward back to registration form
            request.setAttribute("error", "Passwords do not match");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/seller/form.jsp");
            dispatcher.forward(request, response);
        }

	}
	
	
	// login servlet
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Seller seller = new Seller();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		seller = sellerDAO.getSellerByEmail(email);
		
		if(seller.getPassword().equals(password)) {
			dispatcher = request.getRequestDispatcher("/views/seller/dashboard.jsp");
		    dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "Passwords do not match");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/seller/form.jsp");
            dispatcher.forward(request, response);
		}
	}
	
	
	// register validation
	private boolean isValid(String username, String email, String password, String confirmPassword) {
        // Perform validation logic here
        return !username.equals("") && !email.equals("") && !password.equals("") && password.equals(confirmPassword);
    }

}
