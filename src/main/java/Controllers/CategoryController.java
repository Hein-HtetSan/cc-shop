package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Category;
import DAO.CategoryDAO;

public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CategoryDAO categoryDAO = null;
	RequestDispatcher dispatcher = null;

    public CategoryController() throws ClassNotFoundException, SQLException {
        super();
        categoryDAO = new CategoryDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			try {
				saveCategory(request, response);  // save new category
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// create new category
	private void saveCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean flag = false;
		String name = request.getParameter("name");
		Category newCategory = new Category();
		newCategory.setName(name);
		
		if(categoryDAO.create(newCategory)) {
			response.sendRedirect(request.getContextPath() + "/AdminController?page=category");
		}
	}

}
