package Controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Map;

import javax.mail.MessagingException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Models.Message;
import DAO.MessageDAO;

import DAO.MessageDAO;
import Models.Message;



@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	RequestDispatcher dispatcher = null;
	MessageDAO msgDAO = null;
       
    public MessageController() throws ClassNotFoundException, SQLException {
        super();
        msgDAO = new MessageDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equals("delete")) {
				String msg_id = request.getParameter("id");
				boolean deleted = msgDAO.destory(Integer.parseInt(msg_id));
				if(deleted) {
					String success = "Deleted messages successfully";
					String encoded = URLEncoder.encode(success, "UTF-8");
					response.sendRedirect(request.getContextPath() + "/AdminController?page=dashboard&filter_value=all&success="+encoded);
				}
			}
			
			if(action.equals("getMsg")) {
				getSpecificMsg(request, response);
			}
			
			if(action.equals("sendMsgToUser")) {
				String email = request.getParameter("email");
				String id = request.getParameter("id");
				String text = request.getParameter("text");
				
				System.out.println("sender email " + email);
				System.out.println("sender id " + id);
				System.out.println("msg : " + text);
				
				String html = "<h4>Hello thanks for making contact to me!</h4>"
						+ "<p>"+text+"<p>"
						+ "<br><b>Have a nice day, thank you</b>";
				// then send to mail it
				try {
					System.out.println("mailing to user");
					Config.mail.sendEmail(email, "CC-Shop reply to your message!", html);
					System.out.println("done mailing");
				} catch (MessagingException e) {
					String error = "Check your internet connection!";
					String encoded = URLEncoder.encode(error, "UTF-8");
					response.sendRedirect(request.getContextPath() + "/AdminController?page=dashboard&filter_value=all&error="+encoded);
				}
				// then delete that message from database
				boolean deleted = msgDAO.destory(Integer.parseInt(id));
				if(deleted) {
					String success = "Succesfully replied.";
					String encoded = URLEncoder.encode(success, "UTF-8");
					response.sendRedirect(request.getContextPath() + "/AdminController?page=dashboard&filter_value=all&success="+encoded);
				}
			}
		}
		
	}
	
	// get the specific msg
	private void getSpecificMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		// Assuming you have a DAO method to get a message by its ID
	    MessageDAO messageDAO = null;
		try {
			messageDAO = new MessageDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Message message = messageDAO.getById(Integer.parseInt(id));

	 // Convert data to JSON
	    ObjectMapper mapper = new ObjectMapper();
	    String messageJSON = mapper.writeValueAsString(message);

	    // Prepare JSON response
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("message", messageJSON);

	 // Set the content type of the response to application/json
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonResponse.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMessage(request, response);
	}

	private void sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		BufferedReader reader = request.getReader();
	    StringBuilder jsonBuilder = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        jsonBuilder.append(line);
	    }
	    reader.close();
	    boolean flag = false;
	    String jsonString = jsonBuilder.toString();
	    Gson gson = new Gson();
	    JsonObject responseObject = new JsonObject();
	    JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
	    String name = jsonObject.get("name").getAsString();
	    String email = jsonObject.get("email").getAsString();
	    String phone = jsonObject.get("phone").getAsString();
	    String message = jsonObject.get("message").getAsString();
		
		System.out.println("name " + name);
		System.out.println("email " + email);
		System.out.println("phone " + phone);
		System.out.println("message " + message);
		
		if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !message.isEmpty()) {
			Message msg = new Message();
			msg.setEmail(email);
			msg.setName(name);
			msg.setPhone(phone);
			msg.setMessage(message);
			
			flag = msgDAO.create(msg);
		}
		if(flag) {
		    responseObject.addProperty("status", "true");
		    response.setContentType("application/json");
		    PrintWriter out = response.getWriter();
		    out.print(responseObject.toString());
		    out.flush();
		}

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
				
				List<Message> messages = messageDAO.get();
				
				request.setAttribute("messages", messages);
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
