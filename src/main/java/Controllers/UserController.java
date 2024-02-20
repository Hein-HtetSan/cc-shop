package Controllers;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.*;
import DAO.*;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement stmt = null;
	Statement statement = null;
	ResultSet resultset = null;
	RequestDispatcher dispatcher = null;
	
	CustomerDAO customerDAO = null;
	CategoryDAO categoryDAO = null;
	ProductDAO productDAO = null;
	

    public UserController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }

    // do get method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	HttpSession session = request.getSession();
    	
    	Customer customer = (Customer) session.getAttribute("customer");
    	if(customer != null) {
    		
    		if(page != null) {
    			switch(page) {
    			
    			// seller main page --> redirect
    			case "main":
    				try {
						mainPanel(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			}
    		}
    	}else {
    		response.sendRedirect("views/user/form.jsp");
    	}
    }
    
    // do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	// main panel
	private void mainPanel (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// get all category
		List<Category> categoires = categoryDAO.get();
		// get all product
		int page_number = 1;
        int recordsPerPage = ၁၀;
        try {
			productDAO = new ProductDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Product> products = productDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = productDAO.getNoOfRecords();
        System.out.println(noOfRecords);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        request.setAttribute("products", products);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
		
		request.setAttribute("categories", categoires);
		dispatcher = request.getRequestDispatcher("/views/user/dashboard.jsp");
		dispatcher.forward(request, response);
	}

	

}
