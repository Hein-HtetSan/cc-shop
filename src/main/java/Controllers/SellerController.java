package Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.SellerDAO;
import DAO.BusinessDAO;
import DAO.CategoryDAO;
import Models.Seller;
import Models.Business;
import Models.Category;

import java.util.*;

@MultipartConfig
@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SellerDAO sellerDAO = null;
    BusinessDAO businessDAO = null;
    CategoryDAO categoryDAO = null;
    RequestDispatcher dispatcher = null;
	
    public SellerController() throws ClassNotFoundException, SQLException {
        super();
        sellerDAO = new SellerDAO();
        businessDAO = new BusinessDAO();
        categoryDAO = new CategoryDAO();
    }
    // Get Method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	String error = request.getParameter("error");
    	String success = request.getParameter("success");
    	HttpSession session = request.getSession();
    	
    	Seller seller = (Seller) session.getAttribute("seller");
    	if(seller != null) {
    		
    		request.setAttribute("seller", seller);
    		
    		if(page != null) {
    			switch(page) {
    			
    			// seller main page --> redirect
    			case "dashboard":
    				dispatcher = request.getRequestDispatcher("/views/seller/dashboard.jsp");
    				dispatcher.forward(request, response);
    				break;

    			case "product":
    				dispatcher = request.getRequestDispatcher("/views/seller/product/product.jsp");
    				dispatcher.forward(request, response);
    				break;
    			
    			case "order":
					dispatcher = request.getRequestDispatcher("/views/seller/order/order.jsp");
					dispatcher.forward(request, response);
					break;
			
    			case "history":
		    		dispatcher = request.getRequestDispatcher("/views/seller/history/history.jsp");
					dispatcher.forward(request, response);
					break;
					
    			case "createProductPage":
    				try {
						List<Category> categories = categoryDAO.get();
						request.setAttribute("categories", categories);
						if(error != null) request.setAttribute("error", error);
						dispatcher = request.getRequestDispatcher("/views/seller/product/create.jsp");
	    				dispatcher.forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "addProductImage":
    				dispatcher = request.getRequestDispatcher("/views/seller/product/insert_image.jsp");
    				dispatcher.forward(request, response);
    				break;
    			}
    		}
    	}else {
    		response.sendRedirect("RegisterController?page=sellerForm");
    	}
    }
    
    
    // Post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			
			case "store":
				store(request, response);
				break;
			
			}
		}
	}
	
	// store the product
	private void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String count = request.getParameter("count");
		String price = request.getParameter("price");
		String category_id = request.getParameter("category");
		String seller_id = request.getParameter("seller_id");
		List<Part> images = (List<Part>) request.getParts();
		
		if(name == null || name.equals("")) {
			String error = "Name can't be blank!";
			String encodedError = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
		}
		else if(description == null || description.equals("")) {
			String error = "Description can't be blank!";
			String encodedError = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
		}
		else if(count == null || count.equals("")) {
			String error = "Please fill the count";
			String encodedError = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
		}
		else if(price == null || price.equals("")) {
			String error = "Please insert the price!";
			String encodedError = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
		}
		else if(category_id == null || category_id.equals("")) {
			String error = "Please choose category!";
			String encodedError = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
		}
		else if(images == null || images.equals("")) {
			String error = "Please choose image!";
			String encodedError = URLEncoder.encode(error, "UTF-8");
			response.sendRedirect(request.getContextPath() +"/SellerController?page=createProductPage&seller_id="+seller_id+"&error="+encodedError);
		}
		else {
			
	        for(Part image : images) {
	        	String fileName = image.getSubmittedFileName();
		        InputStream is = image.getInputStream();
		        
		        System.out.println(fileName);
	        }
			
	        
		}
		
		
	}
	
	public boolean uploadFile(InputStream is, String path){
        boolean test = false;
        try{
            byte[] byt = new byte[is.available()];
            is.read();
            
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            
            test = true;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return test;
    }
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	
	
	
	
	

	
	

}
