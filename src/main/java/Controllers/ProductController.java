package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import Models.Product;
import DAO.ProductDAO;


public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductDAO productDAO = null;
	RequestDispatcher dispatcher = null;
       
    public ProductController() throws ClassNotFoundException, SQLException {
        super();
        productDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		
		if(page != null) {
			switch(page) {
			// create page for product
			case "createPage":
				
				break;
				
			// edit page for product
			case "editPage":
				
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null) {
			switch(action) {
			//save product
			case "store":
				
				break;
			
			//destory product
			case "destory":
				
				break;
				
			//update product content
			case "updateContent":
				
				break;
				
			//update product image
			case "updateImage":
				
				break;
			}
		}
	}

}
