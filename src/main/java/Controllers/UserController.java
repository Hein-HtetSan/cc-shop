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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

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
    				
    			case "productDetail":
    				try {
						productDetail(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "fetch":
    				try {
    					fetching(request, response);
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
    				break;
    				
    			case "fetchByCategory":
					try {
						fetchByCategory(request, response);
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
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {

			}
		}
	}
	
	// main panel
	private void mainPanel (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// get all category
		List<Category> categoires = categoryDAO.get();
		// get all product
		int page_number = 1;
        int recordsPerPage = 12;
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

	// product detail
	private void productDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String product_id = request.getParameter("product_id");
		String seller_id = request.getParameter("seller_id");
		// get product detail by seller id
		Product product = (Product) productDAO.getFullDataByProductId(Integer.parseInt(product_id));
		// get product images by seller id
		List<Image> images = productDAO.getFullImagesByProductId(Integer.parseInt(product_id));
		// get all category
		List<Category> categoires = categoryDAO.get();
		// get related product
		int category_id = product.getCategory_id();
		List<Product> related_product = productDAO.getSomeProductByCategoryId(category_id);
		for(Product r : related_product) {
			System.out.print(r.getName());
		}

		request.setAttribute("images", images);
		request.setAttribute("product", product);
		request.setAttribute("seller_id", seller_id);
		request.setAttribute("categories", categoires);
		request.setAttribute("related_products", related_product);
		dispatcher = request.getRequestDispatcher("/views/user/product/detail.jsp");
		dispatcher.forward(request, response);
	}
	
	// fetch by Category
	private void fetchByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String category_id = request.getParameter("category_id");
		if(category_id.equals("all")) {
			response.sendRedirect(request.getContextPath() + "/UserController?page=main");
			return;
		}else {
			int page_number = 1;
	        int recordsPerPage = 12;
	        try {
				productDAO = new ProductDAO();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	        // Get counts from utility method
	        if (request.getParameter("page_number") != null) {
	        	page_number = Integer.parseInt(request.getParameter("page_number")); 
	        }
	        List<Category> categoires = categoryDAO.get();
			List<Product> products = productDAO.getByCategoryID((page_number-1)*recordsPerPage,
                    recordsPerPage, Integer.parseInt(category_id));
			int noOfRecords = productDAO.getNoOfRecords();
	        System.out.println(noOfRecords);
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        
	        request.setAttribute("products", products);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page_number);
			request.setAttribute("categories", categoires);
			dispatcher = request.getRequestDispatcher("/views/user/product/category.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	// fetching
	private void fetching(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		String searchTerm = request.getParameter("searchTerm");
		// get the products
		List<Product> products = productDAO.getBySearching(searchTerm);
		List<Category> categoires = categoryDAO.get();
		
		System.out.println(searchTerm);
		
		// Convert data to JSON
	    ObjectMapper mapper = new ObjectMapper();
	    String productsJSON = mapper.writeValueAsString(products);
	    String categoriesJSON = mapper.writeValueAsString(categoires);

	    // Prepare JSON response
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("products", productsJSON);
	    jsonResponse.addProperty("categories", categoriesJSON);

	    // Set content type and write JSON response
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonResponse.toString());
		
	}
	

}
