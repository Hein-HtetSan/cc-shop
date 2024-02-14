package Controllers;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Customer;
import Models.Seller;
import DAO.CustomerDAO;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement stmt = null;
	Statement statement = null;
	ResultSet resultset = null;
	RequestDispatcher dispatcher = null;
	CustomerDAO customerDAO = null;

    public UserController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
    }

    // do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	

	

}
