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
        	
        	// action section
        	if(action != null) {
        		switch(action) {
        		// delete user
        		case "deleteUser":  // action delete user in admin panel
        			String user_id = request.getParameter("user_id");
        			boolean deleteFlag;
    				try {
    					deleteFlag = customerDAO.delete(Integer.parseInt(user_id));
    					if(deleteFlag) {
    						System.out.println("delete success");
    						request.setAttribute("success", "Deleted User Successfully");
    	    				dispatcher = request.getRequestDispatcher("AdminController?page=user");
    	    				dispatcher.forward(request, response);
    	    			}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete seller
        		case "deleteSeller": // action delete seller in admin panel
        			String seller_id = request.getParameter("seller_id");
        			boolean delete_seller;
        			try {
    					delete_seller = sellerDAO.delete(Integer.parseInt(seller_id));
    					if(delete_seller) {
    						System.out.println("delete success");
    						request.setAttribute("success", "Deleted Seller Successfully");
    						dispatcher = request.getRequestDispatcher("AdminController?page=seller");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;

        		// delete store
        		case "deleteStore": // action delete store in admin panel
        			String store_id = request.getParameter("store_id");
        			boolean delete_store;
        			try {
    					delete_store = sellerDAO.delete(Integer.parseInt(store_id));
    					if(delete_store) {
    						request.setAttribute("success", "Deleted Store Successfully");
    						dispatcher = request.getRequestDispatcher("AdminController?page=store");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete category
        		case "deleteCategory": // action delete category in admin panel
        			String category_id = request.getParameter("category_id");
        			boolean delete_category;
        			try {
    					delete_category = categoryDAO.delete(Integer.parseInt(category_id));
    					if(delete_category) {
    						request.setAttribute("success", "Deleted Category Successfully");
    						dispatcher = request.getRequestDispatcher("AdminController?page=category");
    						dispatcher.forward(request, response);
    					}
    				} catch (NumberFormatException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        			
        		// delete business
        		case "deleteBusiness": // action delete business in admin panel
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
        		case "deleteProduct":  // action delete product in admin panel
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
        		case "editCategory": // action edit category in admin panel
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
        		case "editBusiness": // action edit business in admin panel
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
        		case "editAdmin": // action edit profile in admin profile
        			String admin_id = request.getParameter("admin_id");
        			try {
        				Admin admin1 = adminDAO.getById(Integer.parseInt(admin_id));
        				request.setAttribute("admin", admin1);
        				System.out.println(admin1);
        				dispatcher = request.getRequestDispatcher("views/admin/profile/edit.jsp");
        				dispatcher.forward(request, response);
        			} catch (NumberFormatException e) {
        				e.printStackTrace();
        			}
        			break;
        			
        		// user detail page in admin panel
        		case "userDetail":
        			String user_id1 = request.getParameter("user_id");
        			try {
        				Map<String, Integer> counts = getAllCount();
        		        request.setAttribute("counts", counts);
						Customer getCustomer = customerDAO.getById(Integer.parseInt(user_id1));
						request.setAttribute("user", getCustomer);
						dispatcher  = request.getRequestDispatcher("/views/admin/user/detail.jsp");
						dispatcher.forward(request, response);
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
        			break;
        			
        		// seller detail page in admin panel
        		case "sellerDetail":
        			String seller_id1 = request.getParameter("seller_id");
        			try {
        				Map<String, Integer> counts = getAllCount();
						Seller getSeller = sellerDAO.getById(Integer.parseInt(seller_id1));
						System.out.println(getSeller);
						List<Product> getProduct_by_seller = productDAO.getProductBySellerId(Integer.parseInt(seller_id1));
						request.setAttribute("seller", getSeller);
						request.setAttribute("product", getProduct_by_seller);
						request.setAttribute("counts", counts);
						dispatcher  = request.getRequestDispatcher("/views/admin/seller/detail.jsp");
						System.out.println(counts);
						dispatcher.forward(request, response);
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
        			break;
        			
        		
        		case "storeDetail":
        			String seller_id2 = request.getParameter("seller_id");
        			try {
        				Map<String, Integer> counts = getAllCount();
						Seller getSeller = sellerDAO.getById(Integer.parseInt(seller_id2));
						List<Product> getProduct_by_seller = productDAO.getProductBySellerId(Integer.parseInt(seller_id2));
						request.setAttribute("seller", getSeller);
						request.setAttribute("product", getProduct_by_seller);
						request.setAttribute("counts", counts);
						dispatcher  = request.getRequestDispatcher("/views/admin/store/detail.jsp");
						System.out.println(counts);
						dispatcher.forward(request, response);
					} catch (NumberFormatException | SQLException e) {
						e.printStackTrace();
					}
        			break;
        		
        		}
        	}
        	
        	// for page section
        	if(page != null) {
        		switch(page) {
        		case "user":  // user page in admin panel
        			try {
    					getAllUser(request, response); // user list
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "seller": // seller page in admin panel
        			try {
    					getAllSeller(request, response); // get all seller
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "store": // store page in admin panel
        			try {
    					getAllStore(request, response);// get all store
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				} 
        		case "product": // product page in admin panel
        			try {
    					getAllProduct(request, response); // get all product
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "business": // business page in admin panel
        			try {
    					getAllBusiness(request, response); // get all business
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				} 
        			break;
        		case "category": // category page in admin panel
        			try {
    					getAllCategory(request, response); // get all category
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
        			break;
        		case "profile": // profile page in admin panel
        			String success = request.getParameter("success");
        			String admin_id = request.getParameter("admin_id");
        			Admin getAdmin = adminDAO.getById(Integer.parseInt(admin_id));
        			
        			
        			request.setAttribute("admin", getAdmin);
        			if(success != null) request.setAttribute("success", success);
        			
        			dispatcher = request.getRequestDispatcher("views/admin/profile/index.jsp");
        			dispatcher.forward(request, response);
        		case "dashboard": // dashboard page in admin panel
        			Map<String, Integer> counts = null;
					try {
						counts = getAllCount();
						request.setAttribute("counts", counts);
	        			dispatcher = request.getRequestDispatcher("views/admin/dashboard.jsp");
	        			dispatcher.forward(request, response);
					} catch (SQLException e) {
						e.printStackTrace();
					}
        			break;
        			
            	
            			
        		case "form": // form page for admin panel
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
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
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
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
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
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
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
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
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
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
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
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
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
		HttpSession session = request.getSession();
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
    			
    		// update profile
        	case "updateProfile":
        			String admin_Id = request.getParameter("admin_id");
        			System.out.println(admin_Id);
        		try {
        			Admin updatedAdmin = new Admin();
        			updatedAdmin.setId(Integer.parseInt(admin_Id));
        			updatedAdmin.setName(request.getParameter("name"));
        			updatedAdmin.setEmail(request.getParameter("email"));
        			updatedAdmin.setPhone(request.getParameter("phone"));
					if(adminDAO.update(updatedAdmin)) {
						Admin re_get_admin = adminDAO.getById(Integer.parseInt(admin_Id));
						session.setAttribute("admin", re_get_admin);
						response.sendRedirect(request.getContextPath()+"/AdminController?page=user");
				}
						
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
        	break;
    			
    			
			}
		}
	}

	
	


}
