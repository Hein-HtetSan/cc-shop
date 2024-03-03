package Controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.*;
import DAO.*;
import Models.*;


@WebServlet("/WhistlistController")
public class WhistlistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	WhistlistDAO wishDAO = null;
	ProductDAO productDAO = null;
	CategoryDAO categoryDAO = null;

    public WhistlistController() throws ClassNotFoundException, SQLException {
        super();
        wishDAO = new WhistlistDAO();
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			
			switch(action) {
			
			case "addToWhistList":
				do_add_to_whistlist(request, response);
				break;
				
			case "removeFromWhistList":
				remove_from_whistlist(request, response);
				break;
				
			case "wishlistPage":
				page(request, response);
				break;
			
			}
			
		}
	}
	
	// whistlist page
	private void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String success = request.getParameter("success");
		String error = request.getParameter("error");
		
		System.out.println("wishlist page is working");
		List<Integer> product_lists = wishDAO.getProductIDByUserID(Integer.parseInt(user_id));
		List<Product> products = new ArrayList<Product>();
		List<Category> categoires = new ArrayList<Category>();
		// get all category
		try {
			categoires = categoryDAO.get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		for(Integer list : product_lists ) {
			try {
				products.add(productDAO.getProductsByID(list));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for(Product product : products) {
			System.out.println(product.getName());
		}
		
		if(success != null) request.setAttribute("success", success);
		if(error != null) request.setAttribute("error", error);
		request.setAttribute("categories", categoires);
		request.setAttribute("products", products);
		dispatcher = request.getRequestDispatcher("/views/user/product/wishlist.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// add to whistlist
	private void do_add_to_whistlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String product_id = request.getParameter("product_id");
		String user_id = request.getParameter("user_id");
		String where = request.getParameter("where");
		
		boolean flag = wishDAO.create(Integer.parseInt(product_id), Integer.parseInt(user_id));
		
		
		String success = "Add to whistlist successfully!";
		String encoded = URLEncoder.encode(success, "UTF-8");
		if(flag) {
			if(where != null) {
				switch(where) {
				case "detail":
					response.sendRedirect(request.getContextPath() + "/UserController?page=productDetail&product_id="+product_id+"&success="+encoded);
					break;
					
				case "category":
					String category_id = request.getParameter("category_id");
					response.sendRedirect(request.getContextPath() + "/UserController?page=fetchByCategory&category_id="+category_id+"&user_id="+user_id+"&success="+encoded);
					break;
					
				}
			}else {
				response.sendRedirect(request.getContextPath() + "/UserController?page=main&success="+encoded);
			}
		}
	}
	
	
	// remove from whishlist
	// add to whistlist
		private void remove_from_whistlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String product_id = request.getParameter("product_id");
			String user_id = request.getParameter("user_id");
			String wish_id = request.getParameter("wish_id");
			String where = request.getParameter("where");
			
			String success = "Removed from whistlist successfully!";
			String encoded = URLEncoder.encode(success, "UTF-8");
			
			boolean flag = wishDAO.remove(Integer.parseInt(wish_id));
			if(flag) {
				if(where != null) {
					switch(where) {
					case "detail":
						response.sendRedirect(request.getContextPath() + "/UserController?page=productDetail&product_id="+product_id+"&success="+encoded);
						break;
						
					case "category":
						String category_id = request.getParameter("category_id");
						response.sendRedirect(request.getContextPath() + "/UserController?page=fetchByCategory&category_id="+category_id+"&user_id="+user_id+"&success="+encoded);
						break;
						
					case "wishlist":
						response.sendRedirect(request.getContextPath() + "/WhistlistController?action=wishlistPage&user_id="+user_id+"&success="+encoded);
						break;
						
					}
				}else {
					response.sendRedirect(request.getContextPath() + "/UserController?page=main&success="+encoded);
				}
			}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
