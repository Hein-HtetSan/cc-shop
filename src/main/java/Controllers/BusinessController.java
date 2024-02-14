package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Business;
import DAO.BusinessDAO;

public class BusinessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BusinessDAO businessDAO = null;
	RequestDispatcher dispatcher = null;

    public BusinessController() throws ClassNotFoundException, SQLException {
        super();
        businessDAO = new BusinessDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			try {
				saveBusiness(request, response);  // save new category
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// create new category
	private void saveBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean flag = false;
		String name = request.getParameter("name");
		Business newBusiness = new Business();
		newBusiness.setName(name);
		
		if(businessDAO.create(newBusiness)) {
			response.sendRedirect(request.getContextPath() + "/AdminController?page=business");
		}
	}

}
