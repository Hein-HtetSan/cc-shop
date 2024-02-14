package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.*;
import DAO.*;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerDAO customerDAO = null;
	SellerDAO sellerDAO = null;
	AdminDAO adminDAO = null;
	RequestDispatcher dispatcher = null;
       
    public LoginController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
        sellerDAO = new SellerDAO();
        adminDAO = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			switch(page) {
			case "userLogin":
				try {
					userLogin(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "sellerLogin":
				try {
					sellerLogin(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "adminLogin":
				try {
					adminLogin(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// login user
	private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Customer customer = new Customer();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		customer = customerDAO.getUserByEmail(email);
		
		if(customer.getPassword().equals(password)) {
			dispatcher = request.getRequestDispatcher("/views/user/dashboard.jsp");
		    dispatcher.forward(request, response);
		}else {
			request.setAttribute("error", "Passwords do not match");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
            dispatcher.forward(request, response);
		}
	}

	// login servlet
	private void sellerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
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
	
	// login servlet
	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
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
	

}
