package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Models.*;
import DAO.*;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminDAO adminDAO = null;
    CustomerDAO customerDAO = null;
    SellerDAO sellerDAO = null;
    ProductDAO productDAO = null;
    CategoryDAO categoryDAO = null;
  
    RequestDispatcher dispatcher = null;
	
    public AdminController() throws ClassNotFoundException, SQLException {
        super();
        adminDAO = new AdminDAO();
        customerDAO = new CustomerDAO();
        sellerDAO = new SellerDAO();
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }
    
    // do get
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	if(page == null || page == "") {
    		page = "user";
    	}
    	if(page != null) {
    		switch(page) {
    		case "user":
    			try {
					getAllUser(request, response); // user list
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
    			break;
    		case "seller":
    			try {
					getAllSeller(request, response); // get all seller
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
    			break;
    		case "store":
    			try {
					getAllStore(request, response);// get all store
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				} 
    		case "product":
    			try {
					getAllProduct(request, response); // get all product
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
    			break;
    		case "business":
    			break;
    		case "category":
    			try {
					getAllCategory(request, response); // get all category
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
    			break;
    		case "dashboard":
    			break;
    		default:
    			break;
    		}
    	}
    }
    
    // get all user
    private void getAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	List<Customer> userList = customerDAO.get();
    	// Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
    	request.setAttribute("userList", userList);
    	dispatcher = request.getRequestDispatcher("views/admin/user/list.jsp");
    	System.out.println(userList);
    	dispatcher.forward(request, response);
    }
    
    // get all seller
	private void getAllSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		List<Seller> sellerList = sellerDAO.get();
		// Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
		request.setAttribute("sellerList", sellerList);
		dispatcher = request.getRequestDispatcher("views/admin/seller/list.jsp");
		System.out.println(sellerList);
		dispatcher.forward(request, response);
	}
	
	// get all product
	private void getAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Product> productList = productDAO.get();
		// Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
		request.setAttribute("productList", productList);
		dispatcher = request.getRequestDispatcher("views/admin/product/list.jsp");
		System.out.println(productList);
		dispatcher.forward(request, response);
	}
	
	// get all store
	private void getAllStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Seller> sellerList = sellerDAO.get();
		// Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
		request.setAttribute("sellerList", sellerList);
		dispatcher = request.getRequestDispatcher("views/admin/store/list.jsp");
		System.out.println(sellerList);
		dispatcher.forward(request, response);
	}
	
	// get all category
	private void getAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Category> categoryList = categoryDAO.get();
		// Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
		request.setAttribute("categoryList", categoryList);
		dispatcher = request.getRequestDispatcher("views/admin/category/list.jsp");
		System.out.println(categoryList);
		dispatcher.forward(request, response);
	}
	
	// get all data count
	public Map<String, Integer> getAllCount() throws SQLException {
        Map<String, Integer> counts = new HashMap<>();
        List<Customer> userList = customerDAO.get();
        List<Product> productList = productDAO.get();
        List<Category> categoryList = categoryDAO.get();
        List<Seller> sellerList = sellerDAO.get();
        
        counts.put("user_count", userList.size());
        counts.put("product_count", productList.size());
        counts.put("seller_count", sellerList.size());
        counts.put("category_count", categoryList.size());
        counts.put("store_count", sellerList.size());

        return counts;
    }

	// do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the action type
		String action = request.getParameter("action");
		
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
		Admin newAdmin = new Admin();
		
		String name = request.getParameter("name"); // get name value
		String email = request.getParameter("email"); // get email value
		String password = request.getParameter("password"); // get password value
		String cpassword = request.getParameter("cpassword"); // get confirm password value
		String phone = request.getParameter("phone"); // get phone number 
		String image = "assets/images/troll.jpg"; // default image
		
		
		// Perform server-side validation
        if (isValid(name, email, password, cpassword)) {
        	newAdmin.setName(name);
			newAdmin.setEmail(email);
			newAdmin.setPhone(phone);
			newAdmin.setPassword(cpassword);
			newAdmin.setImage(image);
			
			// get return 
			flag = adminDAO.create(newAdmin);
			
			if(flag) {  // if flag true, then go dashboard.
				dispatcher = request.getRequestDispatcher("/views/admin/dashboard.jsp");
			    dispatcher.forward(request, response);
			}
        } else {
            // Set error message and forward back to registration form
            request.setAttribute("error", "Passwords do not match");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/form.jsp");
            dispatcher.forward(request, response);
        }

	}
	
	// login servlet
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		Admin admin = new Admin();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		admin = adminDAO.getAdminByEmail(email);
		
		if(admin.getPassword().equals(password)) {
			dispatcher = request.getRequestDispatcher("/views/admin/dashboard.jsp");
		    dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "Passwords do not match");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/form.jsp");
            dispatcher.forward(request, response);
		}
	}
	
	
	// register validation
	private boolean isValid(String username, String email, String password, String confirmPassword) {
        // Perform validation logic here
        return !username.equals("") && !email.equals("") && !password.equals("") && password.equals(confirmPassword);
    }

}
