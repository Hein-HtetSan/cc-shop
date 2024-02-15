package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Models.*;
import DAO.*;


public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    AdminDAO adminDAO = null;
    CustomerDAO customerDAO = null;
    SellerDAO sellerDAO = null;
    ProductDAO productDAO = null;
    CategoryDAO categoryDAO = null;
    BusinessDAO businessDAO = null;
  
    RequestDispatcher dispatcher = null;
	
    public AdminController() throws ClassNotFoundException, SQLException {
        super();
        adminDAO = new AdminDAO();
        customerDAO = new CustomerDAO();
        sellerDAO = new SellerDAO();
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
        businessDAO = new BusinessDAO();
    }
    
    // do get
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	String action = request.getParameter("action");
    	
    	// Get the session object from the request
        HttpSession session = request.getSession();
        // Retrieve the object from the session
        Admin admin = (Admin) session.getAttribute("admin");
    	
        if(admin != null) {
        	
        	request.setAttribute("admin", admin);
        	
        	if(action != null) {
        		switch(action) {
        		// delete user
        		case "deleteUser":
        			String user_id = request.getParameter("user_id");
        			boolean deleteFlag;
    				try {
    					deleteFlag = customerDAO.delete(Integer.parseInt(user_id));
    					if(deleteFlag) {
    						System.out.println("delete success");
    	    				dispatcher = request.getRequestDispatcher("AdminController?page=user");
    	    				dispatcher.forward(request, response);
    	    			}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete seller
        		case "deleteSeller":
        			String seller_id = request.getParameter("seller_id");
        			boolean delete_seller;
        			try {
    					delete_seller = sellerDAO.delete(Integer.parseInt(seller_id));
    					if(delete_seller) {
    						System.out.println("delete success");
    						dispatcher = request.getRequestDispatcher("AdminController?page=seller");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;

        		// delete store
        		case "deleteStore":
        			String store_id = request.getParameter("store_id");
        			boolean delete_store;
        			try {
    					delete_store = sellerDAO.delete(Integer.parseInt(store_id));
    					if(delete_store) {
    						dispatcher = request.getRequestDispatcher("AdminController?page=store");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete category
        		case "deleteCategory":
        			String category_id = request.getParameter("category_id");
        			boolean delete_category;
        			try {
    					delete_category = categoryDAO.delete(Integer.parseInt(category_id));
    					if(delete_category) {
    						dispatcher = request.getRequestDispatcher("AdminController?page=category");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete business
        		case "deleteBusiness":
        			String business_id = request.getParameter("business_id");
        			boolean delete_business;
        			try {
    					delete_business = businessDAO.delete(Integer.parseInt(business_id));
    					if(delete_business) {
    						dispatcher = request.getRequestDispatcher("AdminController?page=business");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete product
        		case "deleteProduct":
        			String product_id = request.getParameter("product_id");
        			boolean delete_product;
        			try {
    					delete_product = productDAO.delete(Integer.parseInt(product_id));
    					if(delete_product) {
    						dispatcher = request.getRequestDispatcher("AdminController?page=product");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// edit category
        		case "editCategory":
        			String category_Id = request.getParameter("category_id");
        			try {
    					Category category = categoryDAO.getById(Integer.parseInt(category_Id));
    					Map<String, Integer> counts = getAllCount();
    			        request.setAttribute("counts", counts);
    					request.setAttribute("category", category);
    					dispatcher = request.getRequestDispatcher("views/admin/category/edit.jsp");
    					dispatcher.forward(request, response);
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// edit business
        		case "editBusiness":
        			String business_Id = request.getParameter("business_id");
        			try {
        				Business business = businessDAO.getById(Integer.parseInt(business_Id));
        				Map<String, Integer> counts = getAllCount();
        		        request.setAttribute("counts", counts);
        				request.setAttribute("business", business);
        				dispatcher = request.getRequestDispatcher("views/admin/business/edit.jsp");
        				dispatcher.forward(request, response);
        			} catch (NumberFormatException | SQLException e) {
        				e.printStackTrace();
        			}
        			break;
        			
        		// edit profile
        		case "editProfile":
        			break;
        			
        		}
        	}
        	
        	
        	// get page by actions
        	if(page == null || page == "") {
        		page = "user";
        	}
        	if(page != null) {
        		switch(page) {
        		case "user":
        			try {
    					getAllUser(request, response); // user list
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "seller":
        			try {
    					getAllSeller(request, response); // get all seller
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "store":
        			try {
    					getAllStore(request, response);// get all store
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				} 
        		case "product":
        			try {
    					getAllProduct(request, response); // get all product
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "business":
        			try {
    					getAllBusiness(request, response); // get all business
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				} 
        			break;
        		case "category":
        			try {
    					getAllCategory(request, response); // get all category
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "profile":
        			String admin_id = request.getParameter("admin_id");
        			Admin getAdmin = adminDAO.getById(Integer.parseInt(admin_id));
        			request.setAttribute("admin", getAdmin);
        			dispatcher = request.getRequestDispatcher("views/admin/profile/index.jsp");
        			dispatcher.forward(request, response);
        		case "dashboard":
        			break;
        		case "form":
        			dispatcher = request.getRequestDispatcher("views/admin/form.jsp");
        			dispatcher.forward(request, response);
        		default:
        			break;
        		}
        	}
        }else {
        	response.sendRedirect("views/admin/form.jsp");
        }
    	
    	
    	
    }
    
    // get all user
    private void getAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	int page_number = 1;
        int recordsPerPage = 5;
        try {
			customerDAO = new CustomerDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Customer> list = customerDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = customerDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("userList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("views/admin/user/list.jsp"); 
        dispatcher.forward(request, response);
    }
    
    // get all seller
	private void getAllSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int page_number = 1;
        int recordsPerPage = 5;
        try {
			sellerDAO = new SellerDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Seller> list = sellerDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = sellerDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("sellerList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("views/admin/seller/list.jsp"); 
        dispatcher.forward(request, response);
	}
	
	// get all product
	private void getAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int page_number = 1;
        int recordsPerPage = 5;
        try {
			productDAO = new ProductDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Product> list = productDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("productList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("views/admin/product/list.jsp"); 
        dispatcher.forward(request, response);
	}
	
	// get all store
	private void getAllStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int page_number = 1;
        int recordsPerPage = 5;
        try {
			sellerDAO = new SellerDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Seller> list = sellerDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = sellerDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("sellerList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("views/admin/store/list.jsp"); 
        dispatcher.forward(request, response);
	}
	
	// get all category
	private void getAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int page_number = 1;
        int recordsPerPage = 4;
        try {
			categoryDAO = new CategoryDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Category> list = categoryDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = categoryDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("categoryList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("views/admin/category/list.jsp"); 
        dispatcher.forward(request, response);
	}
	
	// get all business
	private void getAllBusiness(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int page_number = 1;
        int recordsPerPage = 4;
        try {
			businessDAO = new BusinessDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        Map<String, Integer> counts = getAllCount();
        request.setAttribute("counts", counts);
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Business> list = businessDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        int noOfRecords = businessDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("businessList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("views/admin/business/list.jsp"); 
        dispatcher.forward(request, response);
	}
	
	// get all data count
	public Map<String, Integer> getAllCount() throws SQLException {
        Map<String, Integer> counts = new HashMap<>();
        List<Customer> userList = customerDAO.get();
        List<Product> productList = productDAO.get();
        List<Category> categoryList = categoryDAO.get();
        List<Seller> sellerList = sellerDAO.get();
        List<Business> businessList = businessDAO.get();
        
        counts.put("user_count", userList.size());
        counts.put("product_count", productList.size());
        counts.put("seller_count", sellerList.size());
        counts.put("category_count", categoryList.size());
        counts.put("store_count", sellerList.size());
        counts.put("business_count", businessList.size());

        return counts;
    }
	
	private String generateUniqueImageName(String originalName) {
        // Implement your logic for generating unique names (e.g., using UUID, timestamps)
        // Consider removing sensitive information from original name to avoid leaks
        // You can replace this with your preferred method
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return timestamp + "_" + originalName;
    }

	// do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			// update image
    		case "updateImage":
    			String UPLOAD_PATH = "assets/images/";
    			
    			String id = request.getParameter("admin_id");
    			Part part = request.getPart("image");
    	        String fileName = part.getSubmittedFileName();
    	        String contentType = part.getContentType();
    	        
    	        String imageName = generateUniqueImageName(fileName); // Generate unique name
    	        File imageFile = new File(UPLOAD_PATH, imageName);
    	        String image_with_path = UPLOAD_PATH + imageName;
    	        
    	        Admin new_image_of_admin = new Admin();
    	        new_image_of_admin.setImage(image_with_path);
    	        new_image_of_admin.setId(Integer.parseInt(id));
    	        
    	        try {
					boolean flag = adminDAO.updateImage(new_image_of_admin);
					if(flag) {
						response.sendRedirect(request.getContextPath()+"/AdminController?page=profile&admin_id="+id);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
    	        
    			break;
    			
    			
			}
		}
	}

	
	


}
