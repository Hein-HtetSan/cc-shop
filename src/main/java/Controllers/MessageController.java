package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MessageDAO;
import Models.Message;


@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null;
	
	MessageDAO messageDAO=null;
	
    public MessageController() throws ClassNotFoundException, SQLException {
        super();
        messageDAO=new MessageDAO();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
	
		if(page!= null) {
			switch(page) {
			case "messagePage":
				dispatcher=request.getRequestDispatcher("/views/admin/message/message.jsp");
				dispatcher.forward(request, response);
				break;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action=request.getParameter("action");
		
		if(action != null) {
			switch(action) {
			case "sendMessage" : 
				
				sendMessage(request,response);
				
			}
		}
		
	}
	
	
	private  void sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String message=request.getParameter("message");
		
		Message mes=new Message();
		
		mes.setName(name);
		mes.setPhone(phone);
		mes.setEmail(email);
		mes.setMessage(message);
		
		
		boolean inserted=messageDAO.create(mes);
		if(inserted) {
			request.setAttribute("status","Message Sent Successfully");
			dispatcher=request.getRequestDispatcher("/views/index.jsp");
			dispatcher.forward(request, response);
		}
		
		
		
		
		
		
	}
}
