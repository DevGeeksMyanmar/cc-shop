package Controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import Models.*;
import DAO.*;

@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement stmt = null;
	Statement statement = null;
	ResultSet resultset = null;
	RequestDispatcher dispatcher = null;
	
	CustomerDAO customerDAO = null;
	CategoryDAO categoryDAO = null;
	ProductDAO productDAO = null;
	SellerDAO sellerDAO = null;
	

    public UserController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
        sellerDAO = new SellerDAO();
    }

    // do get method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	HttpSession session = request.getSession();
    	
    	Customer customer = (Customer) session.getAttribute("customer");
    	if(customer != null) {
    		
    		if(page != null) {
    			switch(page) {
    			
    			// seller main page --> redirect
    			case "main":
    				try {
						mainPanel(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "productDetail":
    				try {
						productDetail(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "fetch":
    				try {
    					fetching(request, response);
    				} catch (ServletException | IOException | SQLException e) {
    					e.printStackTrace();
    				}
    				break;
    				
    			case "fetchByCategory":
					try {
						fetchByCategory(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "seller":
    				try {
						getSellerAndProduct(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "profile":
					try {
						getUserProfile(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "edit":
    				try {
						edit(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			case "changePassword":
					try {
						changePasswordPage(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    				
    			}
    		}
    	}else {
    		response.sendRedirect("views/user/form.jsp");
    	}
    }
    
    // do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
				
			case "updateProfile":
				try {
					update(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
			
			}
		}
	}
	
	// update user profile
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Part image = request.getPart("file");
		boolean hasFileUpload = false;
		String updated_filename = "assets/images/troll.jpg";
		
		// Iterate through the parts
		for (Part part : request.getParts()) {
		  // Check if the part is a file upload
		   if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
		        hasFileUpload = true;
		        break; // No need to check further, we found at least one file upload
		   }
		}
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setId(Integer.parseInt(id));
		
		// update into database
		boolean flag = customerDAO.update(customer);
		
		if(hasFileUpload) {
			int min = 1000;
		    int max = 10000;
		    int random_number = (int) (Math.random()*(max-min+1)+min);  
		    // Process the file upload
		    String fileName = extractFileName(image);
		    updated_filename = random_number + "_" + fileName;
		    System.out.println("File Name: " + fileName);

		    //Define destination directory
	        String uploadDir = "C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\user"; // Example: "C:/eclipse_workspace/upload"
	        
	        // Write file to the destination directory
	        OutputStream out = null;
	        InputStream fileContent = null;
	        try {
	            out = new FileOutputStream(new File(uploadDir + File.separator + updated_filename));
	            fileContent = image.getInputStream();

	            int read;
	            final byte[] bytes = new byte[1024];
	            while ((read = fileContent.read(bytes)) != -1) {
	                out.write(bytes, 0, read);
	            }
	        } catch (FileNotFoundException fne) {
	            // Handle file not found exception
	            fne.printStackTrace();
	        } finally {
	            if (out != null) {
	                out.close();
	            }
	            if (fileContent != null) {
	                fileContent.close();
	            }
	        }
		}
        
        Customer customer_image = new Customer();
        // check whether the image is exist or not
        if(hasFileUpload) {
			customer_image.setImage(updated_filename);
			customer_image.setId(Integer.parseInt(id));
		}
        boolean update_image = customerDAO.updateImage(customer_image);
        if(flag || update_image) {
        	String success = "Updated Profile Successfully";
			String encoded = URLEncoder.encode(success, "UTF-8");
    		response.sendRedirect(request.getContextPath() + "/UserController?page=profile&success="+encoded+"&user_id="+id);
        }else {
        	String error = "Can't update the profile";
			String encoded = URLEncoder.encode(error, "UTF-8");
    		response.sendRedirect(request.getContextPath() + "/UserController?page=profile&error="+encoded+"&user_id="+id);
        }
	}
	
	// edit page
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String user_id = request.getParameter("user_id");
		Customer user = customerDAO.getById(Integer.parseInt(user_id));
		request.setAttribute("user", user);
		dispatcher = request.getRequestDispatcher("/views/user/profile/edit.jsp");
		dispatcher.forward(request, response);
	}
	
	// change password page
		private void changePasswordPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
			String success = request.getParameter("success");
			String error = request.getParameter("error");
			String user_id = request.getParameter("user_id");
			Customer user = customerDAO.getById(Integer.parseInt(user_id));
			request.setAttribute("user", user);
			request.setAttribute("success", success);
			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/views/user/profile/password.jsp");
			dispatcher.forward(request, response);
		}
		
	
	// user profile
	private void getUserProfile (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String success = request.getParameter("success");
		String error = request.getParameter("error");
		String user_id = request.getParameter("user_id");
		Customer user = customerDAO.getById(Integer.parseInt(user_id));
		request.setAttribute("user", user);
		request.setAttribute("success", success);
		request.setAttribute("error", error);
		dispatcher = request.getRequestDispatcher("/views/user/profile/index.jsp");
		dispatcher.forward(request, response);
	}
	
	// main panel
	private void mainPanel (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// get all category
		List<Category> categoires = categoryDAO.get();
		// get all product
		int page_number = 1;
        int recordsPerPage = 12;
        try {
			productDAO = new ProductDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Product> products = productDAO.getAll((page_number-1)*recordsPerPage,
                                 recordsPerPage);
        List<Product> get_all_products = productDAO.get();
        int product_count = get_all_products.size();
        
        int noOfRecords = productDAO.getNoOfRecords();
        System.out.println(noOfRecords);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        request.setAttribute("products", products);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
		request.setAttribute("categories", categoires);
		request.setAttribute("total_product", product_count);
		dispatcher = request.getRequestDispatcher("/views/user/dashboard.jsp");
		dispatcher.forward(request, response);
	}

	// product detail
	private void productDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String product_id = request.getParameter("product_id");
		String seller_id = request.getParameter("seller_id");
		// get product detail by seller id
		Product product = (Product) productDAO.getFullDataByProductId(Integer.parseInt(product_id));
		// get product images by seller id
		List<Image> images = productDAO.getFullImagesByProductId(Integer.parseInt(product_id));
		// get all category
		List<Category> categoires = categoryDAO.get();
		// get related product
		int category_id = product.getCategory_id();
		List<Product> related_product = productDAO.getSomeProductByCategoryId(category_id);
		for(Product r : related_product) {
			System.out.print(r.getName());
		}

		request.setAttribute("images", images);
		request.setAttribute("product", product);
		request.setAttribute("seller_id", seller_id);
		request.setAttribute("categories", categoires);
		request.setAttribute("related_products", related_product);
		dispatcher = request.getRequestDispatcher("/views/user/product/detail.jsp");
		dispatcher.forward(request, response);
	}
	
	// fetch by Category
	private void fetchByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String category_id = request.getParameter("category_id");
		if(category_id.equals("all")) {
			response.sendRedirect(request.getContextPath() + "/UserController?page=main");
			return;
		}else {
			int page_number = 1;
	        int recordsPerPage = 12;
	        try {
				productDAO = new ProductDAO();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	        // Get counts from utility method
	        if (request.getParameter("page_number") != null) {
	        	page_number = Integer.parseInt(request.getParameter("page_number")); 
	        }
	        List<Category> categoires = categoryDAO.get();
			List<Product> products = productDAO.getByCategoryID((page_number-1)*recordsPerPage,
                    recordsPerPage, Integer.parseInt(category_id));
			int noOfRecords = productDAO.getNoOfRecords();
	        System.out.println(noOfRecords);
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        
	        request.setAttribute("products", products);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page_number);
			request.setAttribute("categories", categoires);
			dispatcher = request.getRequestDispatcher("/views/user/product/category.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	// fetching
	private void fetching(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		String searchTerm = request.getParameter("searchTerm");
		// get the products
		List<Product> products = productDAO.getBySearching(searchTerm);
		List<Category> categoires = categoryDAO.get();
		
		System.out.println(searchTerm);
		
		// Convert data to JSON
	    ObjectMapper mapper = new ObjectMapper();
	    String productsJSON = mapper.writeValueAsString(products);
	    String categoriesJSON = mapper.writeValueAsString(categoires);

	    // Prepare JSON response
	    JsonObject jsonResponse = new JsonObject();
	    jsonResponse.addProperty("products", productsJSON);
	    jsonResponse.addProperty("categories", categoriesJSON);

	    // Set content type and write JSON response
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(jsonResponse.toString());
		
	}
	
	
	// get seller and his product
	private void getSellerAndProduct(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException, SQLException {
		String seller_id = request.getParameter("seller_id");
		String product_id = request.getParameter("product_id");
		
		// get all product
		int page_number = 1;
        int recordsPerPage = 6;
        try {
			productDAO = new ProductDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        
        List<Category> categoires = categoryDAO.get();
        List<Product> products = productDAO.getAllBySellerID((page_number-1)*recordsPerPage,
                                 recordsPerPage, Integer.parseInt(seller_id));
        Seller seller = sellerDAO.getById(Integer.parseInt(seller_id));
        int total_product = productDAO.getProductCountBySellerId(Integer.parseInt(seller_id));
       
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
		request.setAttribute("products", products);
		request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
		request.setAttribute("categories", categoires);
		request.setAttribute("seller", seller);
		request.setAttribute("product_count", total_product);
		request.setAttribute("product_id", product_id);
		dispatcher = request.getRequestDispatcher("/views/user/product/seller.jsp");
		dispatcher.forward(request, response);
	}
	
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

}
