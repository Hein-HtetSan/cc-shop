package Controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Models.Address;
import DAO.AddressDAO;

@WebServlet("/AddressController")
public class AddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher dispatcher = null;
	AddressDAO addressDAO = null;
       

    public AddressController() throws ClassNotFoundException, SQLException {
        super();
        addressDAO = new AddressDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			
			case "delete":
				delete(request, response);
				break;
			
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			
			case "add":
				add(request, response);
				break;
			
			}
		}
	}
	
	// add to address
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String street = request.getParameter("street_address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String postal_code = request.getParameter("postal_code");
		String country = request.getParameter("country");
		// create address object
		Address address = new Address();
		address.setCustomer_id(Integer.parseInt(user_id));
		address.setStreet_address(street);
		address.setCity(city);
		address.setState(state);
		address.setPostal_code(postal_code);
		address.setCountry(country);
		boolean flag = addressDAO.create(address);
		if(flag) {
			String success = "Added address successfully";
			String encoded = URLEncoder.encode(success, "UTF-8");
    		response.sendRedirect(request.getContextPath() + "/CheckoutController?page=main&success="+encoded+"&user_id="+user_id);
		}
	}
	
	// delete the address
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String address_id = request.getParameter("id");
		boolean flag = addressDAO.delete(Integer.parseInt(address_id));
		if(flag) {
			String success = "Deleted address successfully";
			String encoded = URLEncoder.encode(success, "UTF-8");
    		response.sendRedirect(request.getContextPath() + "/CheckoutController?page=main&success="+encoded+"&user_id="+user_id);
		}
	}
}
