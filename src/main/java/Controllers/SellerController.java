package Controllers;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.*;
import Models.*;

import java.util.*;

@MultipartConfig
@WebServlet("/SellerController")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
    SellerDAO sellerDAO = null;
    BusinessDAO businessDAO = null;
    CategoryDAO categoryDAO = null;
    ProductDAO productDAO = null;
    OrderDAO orderDAO = null;
    RequestDispatcher dispatcher = null;
	
    public SellerController() throws ClassNotFoundException, SQLException {
        super();
        con = Config.config.getConnections();
        sellerDAO = new SellerDAO();
        businessDAO = new BusinessDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
    }
    // Get Method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	String error = request.getParameter("error");
    	String success = request.getParameter("success");
    	HttpSession session = request.getSession();
    	
    	Seller seller = (Seller) session.getAttribute("seller");
    	int seller_id  = seller.getId();

    	
    		request.setAttribute("seller", seller);
    		if(page != null) {
    			switch(page) {
    			
    			// seller main page --> redirect
    			case "dashboard":
					try {
						Seller seller2 = sellerDAO.getById(seller_id);
						request.setAttribute("seller", seller2);
	    				dispatcher = request.getRequestDispatcher("/views/seller/dashboard.jsp");
	    				dispatcher.forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				break;

    			case "product":
    				try {
    					getAllProductWithOneImageBySellerID(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;	
    			case "detailPage":
    				dispatcher = request.getRequestDispatcher("/views/seller/detail/detail.jsp");
    				dispatcher.forward(request, response);
    				break;
    				
    			
    			case "order":
					orderPage(request, response);
					break;
			
    			case "history":
    				history(request, response);
					break;
					
    			case "profile":
    				profile(request, response);
    				break;
    				
    			case "editSeller":
    				editSeller(request, response);
    				break;
    			}
    		}
    	
    }
    
    
    // Post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			
			case "updateProfile":
				updateProfile(request, response);
				break;
			
			}
		}
	}
	
	// history page
	private void history(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String success = request.getParameter("success");
    	String error = request.getParameter("error");
    	String seller_id = request.getParameter("seller_id");
    	int page_number = 1;
        int recordsPerPage = 10;
     // Get counts from utility method
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
		try {
			Seller seller = sellerDAO.getById(Integer.parseInt(seller_id));
			List<Orders> orders = orderDAO.getBySellerWithPaginationWithComplete(Integer.parseInt(seller_id), (page_number-1)*recordsPerPage,
                    recordsPerPage);
			List<Orders> total_order = orderDAO.getBySeller(Integer.parseInt(seller_id));
		       
	        int noOfRecords = orderDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
	        if(error != null) request.setAttribute("error", error);
	        if(success != null) request.setAttribute("success", success);
			request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page_number);
			request.setAttribute("seller", seller);
			request.setAttribute("orders", orders);
			request.setAttribute("total_order", total_order.size());
			
			dispatcher = request.getRequestDispatcher("/views/seller/order/history.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// get all product with one image
    private void getAllProductWithOneImageBySellerID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	// get the product with seller id
    	String seller_id = request.getParameter("seller_id");
    	
    	int page_number = 1;
        int recordsPerPage = 4;
        try {
			productDAO = new ProductDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        // Get counts from utility method
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
        List<Product> products = productDAO.getAllBySellerID((page_number-1)*recordsPerPage,
                                 recordsPerPage, Integer.parseInt(seller_id));
        Seller seller = sellerDAO.getById(Integer.parseInt(seller_id));
	
        int noOfRecords = productDAO.getNoOfRecords();
        // get products count
        int count = productDAO.getProductCountBySellerId(Integer.parseInt(seller_id));
        
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
        request.setAttribute("products", products);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        request.setAttribute("seller_id", seller_id);
        request.setAttribute("product_count", count);
    	request.setAttribute("seller", seller);
        dispatcher = request.getRequestDispatcher("/views/seller/product/product.jsp");
		dispatcher.forward(request, response);
    }
    
    
 // get all product with one image by customer
    private void getAllProductWithOneImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	// get the product with seller id
    	
    	int page_number = 1;
        int recordsPerPage = 4;
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
        int noOfRecords = productDAO.getNoOfRecords();
        System.out.println(noOfRecords);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        
        String success = request.getParameter("success");
        if(success != null) request.setAttribute("success", success);
        
        request.setAttribute("products", products);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page_number);
        dispatcher = request.getRequestDispatcher("/views/seller/product/product.jsp");
		dispatcher.forward(request, response);
    }
	
    // seller profile
    private void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String success = request.getParameter("success");
		String seller_id = request.getParameter("seller_id");
		Seller seller;
		try {
			seller = sellerDAO.getById(Integer.parseInt(seller_id));
			
			request.setAttribute("seller", seller);
			if(success != null) request.setAttribute("success", success);
			
			dispatcher = request.getRequestDispatcher("views/seller/profile/index.jsp");
			dispatcher.forward(request, response);
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }

    // edit seller page
    private void editSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String seller_id = request.getParameter("seller_id");
		try {
			// get seller by id
			Seller seller = sellerDAO.getById(Integer.parseInt(seller_id));
			// get business
			List<Business> businesses = businessDAO.get();
			
			request.setAttribute("seller", seller);
			request.setAttribute("businesses", businesses);
			System.out.println(seller);
			dispatcher = request.getRequestDispatcher("views/seller/profile/edit.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // update seller profile
    private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = request.getParameter("name");
		String id = request.getParameter("seller_id");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String company = request.getParameter("company");
		String business_id = request.getParameter("business_id");
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
		
		Seller seller = new Seller();
		seller.setName(name);
		seller.setEmail(email);
		seller.setPhone(phone);
		seller.setAddress(address);
		seller.setCompany(company);
		seller.setBusiness_id(Integer.parseInt(business_id));
		seller.setId(Integer.parseInt(id));
		
		// update into database
		boolean flag = false;
		try {
			flag = sellerDAO.update(seller);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(hasFileUpload) {
			int min = 1000;
		    int max = 10000;
		    int random_number = (int) (Math.random()*(max-min+1)+min);  
		    // Process the file upload
		    String fileName = extractFileName(image);
		    updated_filename = random_number + "_" + fileName;
		    System.out.println("File Name: " + fileName);

		    //Define destination directory
	        String uploadDir = "C:\\Users\\acer\\Desktop\\cc-shop\\src\\main\\webapp\\assets\\images\\seller"; // Example: "C:/eclipse_workspace/upload"
	        
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
        
        Seller seller_image = new Seller();
        // check whether the image is exist or not
        if(hasFileUpload) {
			seller_image.setImage(updated_filename);
			seller_image.setId(Integer.parseInt(id));
		}
        boolean update_image;
		try {
			update_image = sellerDAO.updateImage(seller_image);
			 if(flag || update_image) {
		        	String success = "Updated Profile Successfully";
					String encoded = URLEncoder.encode(success, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/SellerController?page=profile&success="+encoded+"&seller_id="+id);
		        }else {
		        	String error = "Can't update the profile";
					String encoded = URLEncoder.encode(error, "UTF-8");
		    		response.sendRedirect(request.getContextPath() + "/SellerController?page=profile&error="+encoded+"&seller_id="+id);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    // order page
    private void orderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String success = request.getParameter("success");
    	String error = request.getParameter("error");
    	String seller_id = request.getParameter("seller_id");
    	int page_number = 1;
        int recordsPerPage = 10;
     // Get counts from utility method
        if (request.getParameter("page_number") != null) {
        	page_number = Integer.parseInt(request.getParameter("page_number")); 
        }
		try {
			Seller seller = sellerDAO.getById(Integer.parseInt(seller_id));
			List<Orders> orders = orderDAO.getBySellerWithPaginationWithPending(Integer.parseInt(seller_id), (page_number-1)*recordsPerPage,
                    recordsPerPage);
			List<Orders> total_order = orderDAO.getBySeller(Integer.parseInt(seller_id));
		       
	        int noOfRecords = orderDAO.getNoOfRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
	        if(error != null) request.setAttribute("error", error);
	        if(success != null) request.setAttribute("success", success);
			request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page_number);
			request.setAttribute("seller", seller);
			request.setAttribute("orders", orders);
			request.setAttribute("total_order", total_order.size());
			
			dispatcher = request.getRequestDispatcher("/views/seller/order/order.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
