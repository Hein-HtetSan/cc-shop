package Controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Config.Hash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import Models.*;
import DAO.*;

@WebServlet("/OTPController")
public class OTPController extends HttpServlet {
	
	RequestDispatcher dispatcher = null;
	AdminDAO adminDAO = null;
	CustomerDAO customerDAO = null;
	SellerDAO sellerDAO = null;
	OTPDAO otpDAO = null;
	
	public OTPController() throws ClassNotFoundException, SQLException {
		adminDAO = new AdminDAO();
		otpDAO = new OTPDAO();
		customerDAO = new CustomerDAO();
		sellerDAO = new SellerDAO();
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null) {
        	switch(action) {
        		
        	case "resendAdminOTP":
        		adminOTP(request, response);
        		break;
        		
        	case "resendSellerOTP":
        		sellerOTP(request, response);
        		break;
        		
        	case "resendUserOTP":
        		userOTP(request, response);
        		break;
        	
        	}
        }
    }

    // Method to generate OTP
    private String generateOTP() {
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }

    // Method to save OTP to the database (you can implement this based on your database interaction mechanism)
    private void saveOTPToDatabase(String email, String otp, LocalDateTime expirationTime) {
    	if(otpDAO.insertOTP(email, otp, expirationTime)) {
    		System.out.println("add to the otp database");
    	}
    }

    // Method to send OTP via email
    private void sendOTPByEmail(String email, String otp) {
        // Implementation to send OTP via email goes here
    	String html = "<h3>Email : "+email+"</h3>"
    			+ "<h5>Please Don't share this to other.</h5>"
    			+ "<p style='border: 1px solid #ccc; border-radius: 5px; padding: .8rem;'> OTP code : " + otp  + "</p>";
    	try {
			Config.mail.sendEmail(email, "OTP code as a one time password", html);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // adminOTP page
 // adminOTP page
    private void adminOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println(email);
        if (email != null && !email.isEmpty()) {
            try {
            	// check the admin email is exist or not
            	boolean is_admin_exist = checkEmail(email, "admin");
            	if(!is_admin_exist) {
            		request.setAttribute("error", "Invalid Email Address.");
                	dispatcher = request.getRequestDispatcher("views/admin/profile/forgotPassword.jsp");
                    dispatcher.forward(request, response);
                    return;
            	}
                // Check if the email exists in the OTP table
                OTP existingOTP = otpDAO.getOTPByEmail(email);
                
                if (existingOTP != null) {
                    // Email exists in the OTP table, update the existing OTP code
                    String otp = generateOTP(); // Generate new OTP
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // Set OTP expiration time (5 minutes from now)
                    otpDAO.updateOTPInDatabase(existingOTP.getOtpId(), otp, expirationTime); // Update OTP in the database
                    sendOTPByEmail(email, otp); // Send the new OTP via email
                    System.out.println("Updated OTP sent to the email");
                } else {
                    // Email doesn't exist in the OTP table, generate a new OTP and send it
                    String otp = generateOTP(); // Generate OTP
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // Set OTP expiration time (5 minutes from now)
                    saveOTPToDatabase(email, otp, expirationTime); // Save OTP to the database
                    sendOTPByEmail(email, otp); // Send OTP via email
                    System.out.println("New OTP sent to the email");
                }
                request.setAttribute("email", email);
                dispatcher = request.getRequestDispatcher("views/admin/profile/otp.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database error
            }
        } else {
        	request.setAttribute("error", "Invalid Email Address.");
        	dispatcher = request.getRequestDispatcher("views/admin/profile/forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
    
 // seller otp page
    private void sellerOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println(email);
        if (email != null && !email.isEmpty()) {
            try {
            	boolean is_seller_exist = checkEmail(email, "seller");
            	if(!is_seller_exist) {
            		request.setAttribute("error", "Invalid Email Address.");
                	dispatcher = request.getRequestDispatcher("views/seller/profile/forgotPassword.jsp");
                    dispatcher.forward(request, response);
                    return;
            	}
            	// Check if the email exists in the OTP table
                OTP existingOTP = otpDAO.getOTPByEmail(email);
                
                if (existingOTP != null) {
                    // Email exists in the OTP table, update the existing OTP code
                    String otp = generateOTP(); // Generate new OTP
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // Set OTP expiration time (5 minutes from now)
                    otpDAO.updateOTPInDatabase(existingOTP.getOtpId(), otp, expirationTime); // Update OTP in the database
                    sendOTPByEmail(email, otp); // Send the new OTP via email
                    System.out.println("Updated OTP sent to the email");
                } else {
                    // Email doesn't exist in the OTP table, generate a new OTP and send it
                    String otp = generateOTP(); // Generate OTP
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // Set OTP expiration time (5 minutes from now)
                    saveOTPToDatabase(email, otp, expirationTime); // Save OTP to the database
                    sendOTPByEmail(email, otp); // Send OTP via email
                    System.out.println("New OTP sent to the email");
                }
                request.setAttribute("email", email);
                dispatcher = request.getRequestDispatcher("views/seller/profile/otp.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database error
            }
        } else {
        	request.setAttribute("error", "Invalid Email Address.");
        	dispatcher = request.getRequestDispatcher("views/seller/profile/forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    
 // user otp page
    private void userOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        System.out.println(email);
        if (email != null && !email.isEmpty()) {
            try {
            	boolean is_seller_exist = checkEmail(email, "user");
            	if(!is_seller_exist) {
            		request.setAttribute("error", "Invalid Email Address.");
                	dispatcher = request.getRequestDispatcher("views/user/profile/forgotPassword.jsp");
                    dispatcher.forward(request, response);
                    return;
            	}
            	// Check if the email exists in the OTP table
                OTP existingOTP = otpDAO.getOTPByEmail(email);
                
                if (existingOTP != null) {
                    // Email exists in the OTP table, update the existing OTP code
                    String otp = generateOTP(); // Generate new OTP
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // Set OTP expiration time (5 minutes from now)
                    otpDAO.updateOTPInDatabase(existingOTP.getOtpId(), otp, expirationTime); // Update OTP in the database
                    sendOTPByEmail(email, otp); // Send the new OTP via email
                    System.out.println("Updated OTP sent to the email");
                } else {
                    // Email doesn't exist in the OTP table, generate a new OTP and send it
                    String otp = generateOTP(); // Generate OTP
                    LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(1); // Set OTP expiration time (5 minutes from now)
                    saveOTPToDatabase(email, otp, expirationTime); // Save OTP to the database
                    sendOTPByEmail(email, otp); // Send OTP via email
                    System.out.println("New OTP sent to the email");
                }
                request.setAttribute("email", email);
                dispatcher = request.getRequestDispatcher("views/user/profile/otp.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database error
            }
        } else {
        	request.setAttribute("error", "Invalid Email Address.");
        	dispatcher = request.getRequestDispatcher("views/user/profile/forgotPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // check the email exit or not
    private boolean checkEmail(String email, String type) {
    	boolean flag = false;
    	switch(type) {
    	case "admin": 
    		try {
				Admin admin = adminDAO.getAdminByEmail(email);
				if(admin != null) flag = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    		
    	case "user":
    		try {
				Customer customer = customerDAO.getUserByEmail(email);
				if(customer != null) flag = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    		
    	case "seller":
    		try {
				Seller seller = sellerDAO.getSellerByEmail(email);
				if(seller != null) flag = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		break;
    	}
    	return flag;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
        if (page != null) {
            switch (page) {
            // for admin
                case "adminOTP":
                    adminOTP(request, response);
                    break;
                    
                case "adminCheckOTP":
                	checkAdminOTP(request, response);
                	break;
                	
                case "updateAdminPassword":
                	updateAdminPassword(request, response);
                	break;
                	
                // for seller
                case "sellerOTP":
                    sellerOTP(request, response);
                    break;
                    
                case "sellerCheckOTP":
                	checkSellerOTP(request, response);
                	break;
                	
                case "updateSellerPassword":
                	updateSellerPassword(request, response);
                	break;
                	
                	// for customer
                case "userOTP":
                    userOTP(request, response);
                    break;
                    
                case "userCheckOTP":
                	checkUserOTP(request, response);
                	break;
                	
                case "updateUserPassword":
                	updateUserPassword(request, response);
                	break;
            }
        }
    }
    
 // adminCheckOTP page
    private void checkAdminOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String enteredOTP = request.getParameter("otp"); // Assuming you have a form field named "otp" for entering OTP
        request.setAttribute("email", email);
        if (email != null && !email.isEmpty() && enteredOTP != null && !enteredOTP.isEmpty()) {
            OTP otp = otpDAO.getOTPByEmail(email);
			if (otp != null && otp.getOtp().equals(enteredOTP) && LocalDateTime.now().isBefore(otp.getExpirationTime())) {
			    RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/profile/newPassword.jsp");
			    dispatcher.forward(request, response);
			} else {
			    request.setAttribute("error", "Invalid OTP. Please try again.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/profile/otp.jsp");
			    dispatcher.forward(request, response);
			}
        } else {
            request.setAttribute("error", "Email or OTP is empty or null. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/admin/profile/otp.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // user CheckOTP page
    private void checkUserOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String enteredOTP = request.getParameter("otp"); // Assuming you have a form field named "otp" for entering OTP
        request.setAttribute("email", email);
        if (email != null && !email.isEmpty() && enteredOTP != null && !enteredOTP.isEmpty()) {
            OTP otp = otpDAO.getOTPByEmail(email);
			if (otp != null && otp.getOtp().equals(enteredOTP) && LocalDateTime.now().isBefore(otp.getExpirationTime())) {
			    RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/profile/newPassword.jsp");
			    dispatcher.forward(request, response);
			} else {
			    request.setAttribute("error", "Invalid OTP. Please try again.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/profile/otp.jsp");
			    dispatcher.forward(request, response);
			}
        } else {
            request.setAttribute("error", "Email or OTP is empty or null. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/user/profile/otp.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // seller CheckOTP page
    private void checkSellerOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String enteredOTP = request.getParameter("otp"); // Assuming you have a form field named "otp" for entering OTP
        request.setAttribute("email", email);
        if (email != null && !email.isEmpty() && enteredOTP != null && !enteredOTP.isEmpty()) {
            OTP otp = otpDAO.getOTPByEmail(email);
			if (otp != null && otp.getOtp().equals(enteredOTP) && LocalDateTime.now().isBefore(otp.getExpirationTime())) {
			    RequestDispatcher dispatcher = request.getRequestDispatcher("views/seller/profile/newPassword.jsp");
			    dispatcher.forward(request, response);
			} else {
			    request.setAttribute("error", "Invalid OTP. Please try again.");
			    RequestDispatcher dispatcher = request.getRequestDispatcher("views/seller/profile/otp.jsp");
			    dispatcher.forward(request, response);
			}
        } else {
            request.setAttribute("error", "Email or OTP is empty or null. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/seller/profile/otp.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    // update admin password
    private void updateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String new_password = request.getParameter("new_password");
		String confirm_new_password = request.getParameter("confirm_new_password");
		request.setAttribute("email", email);
		System.out.println(email);
		try {
			Admin admin = adminDAO.getAdminByEmail(email);
			if(!new_password.isEmpty() && !confirm_new_password.isEmpty()) {
				if(new_password.equals(confirm_new_password)) {
					String hashed_password = Hash.hashPassword(new_password);
					System.out.println("got hashed");
					System.out.println(admin);
					try {
						if(adminDAO.updatePassword(hashed_password, admin.getId())) {
							System.out.println("got updated");
							String success = "Updated Password successfully";
							request.setAttribute("success", success);
							dispatcher = request.getRequestDispatcher("/views/admin/form.jsp");
							dispatcher.forward(request, response);
						}
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
				}else {
					String error = "Password Don't Match";
					request.setAttribute("error", error);
					dispatcher = request.getRequestDispatcher("/views/admin/profile/newPassword.jsp");
					dispatcher.forward(request, response);
				}
		}else {
			String error = "Invalid Password";
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/views/admin/profile/newPassword.jsp");
			dispatcher.forward(request, response);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    // end of admin password change
    
 // update seller password
    private void updateSellerPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String new_password = request.getParameter("new_password");
		String confirm_new_password = request.getParameter("confirm_new_password");
		request.setAttribute("email", email);
		System.out.println(email);
		try {
			Seller seller = sellerDAO.getSellerByEmail(email);
			if(!new_password.isEmpty() && !confirm_new_password.isEmpty()) {
				if(new_password.equals(confirm_new_password)) {
					String hashed_password = Hash.hashPassword(new_password);
					System.out.println("got hashed");
					System.out.println(seller);
					try {
						if(sellerDAO.updatePassword(hashed_password, seller.getId())) {
							System.out.println("got updated");
							String success = "Updated Password successfully";
							request.setAttribute("success", success);
							dispatcher = request.getRequestDispatcher("/views/seller/form.jsp");
							dispatcher.forward(request, response);
						}
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
				}else {
					String error = "Password Don't Match";
					request.setAttribute("error", error);
					dispatcher = request.getRequestDispatcher("/views/seller/profile/newPassword.jsp");
					dispatcher.forward(request, response);
				}
		}else {
			String error = "Invalid Password";
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/views/admin/seller/newPassword.jsp");
			dispatcher.forward(request, response);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
 // update seller password
    private void updateUserPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String new_password = request.getParameter("new_password");
		String confirm_new_password = request.getParameter("confirm_new_password");
		request.setAttribute("email", email);
		System.out.println(email);
		try {
			Customer user = customerDAO.getUserByEmail(email);
			if(!new_password.isEmpty() && !confirm_new_password.isEmpty()) {
				if(new_password.equals(confirm_new_password)) {
					String hashed_password = Hash.hashPassword(new_password);
					System.out.println("got hashed");
					System.out.println(user);
					try {
						if(customerDAO.updatePassword(hashed_password, user.getId())) {
							System.out.println("got updated");
							String success = "Updated Password successfully";
							request.setAttribute("success", success);
							dispatcher = request.getRequestDispatcher("/views/user/form.jsp");
							dispatcher.forward(request, response);
						}
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
				}else {
					String error = "Password Don't Match";
					request.setAttribute("error", error);
					dispatcher = request.getRequestDispatcher("/views/user/profile/newPassword.jsp");
					dispatcher.forward(request, response);
				}
		}else {
			String error = "Invalid Password";
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/views/user/profile/newPassword.jsp");
			dispatcher.forward(request, response);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    
//    public static void main(String args[]) {
//    	String html = "<h1>Hello</h1>";
//    	try {
//			Config.mail.sendEmail("thihansoe912@gmail.com", "Testing mail", html);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

}

