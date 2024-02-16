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
import javax.servlet.http.HttpSession;

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
    	String page = request.getParameter("page");
    	HttpSession session = request.getSession();
    	
    	Seller seller = (Seller) session.getAttribute("seller");
    	if(seller != null) {
    		if(page != null) {
    			switch(page) {
    			
    			// seller main page --> redirect
    			case "main":
    				dispatcher = request.getRequestDispatcher("/views/seller/dashboard.jsp");
    				dispatcher.forward(request, response);
    				break;
    				
    			}
    		}
    	}else {
    		response.sendRedirect("views/seller/form.jsp");
    	}
    }
    
    
    // Post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	
	

	
	

}
