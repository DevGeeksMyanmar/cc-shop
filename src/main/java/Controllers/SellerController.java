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

import DAO.SellerDAO;
import DAO.BusinessDAO;
import DAO.CategoryDAO;
import DAO.CustomerDAO;
import DAO.ProductDAO;
import Models.Seller;
import Models.Business;
import Models.Category;

// this is acceptable
import Models.Order;
import Models.Record;

import Models.Customer;
import Models.Product;
import Models.Image;


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
    RequestDispatcher dispatcher = null;
	
    public SellerController() throws ClassNotFoundException, SQLException {
        super();
        con = Config.config.getConnections();
        sellerDAO = new SellerDAO();
        businessDAO = new BusinessDAO();
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }
    // Get Method
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String page = request.getParameter("page");
    	String error = request.getParameter("error");
    	String success = request.getParameter("success");
    	HttpSession session = request.getSession();
    	
    	Seller seller = (Seller) session.getAttribute("seller");
    	if(seller != null) {
    		
    		request.setAttribute("seller", seller);
    		
    		if(page != null) {
    			switch(page) {
    			
    			// seller main page --> redirect
    			case "dashboard":
    				dispatcher = request.getRequestDispatcher("/views/seller/dashboard.jsp");
    				dispatcher.forward(request, response);
    				break;

    			case "product":
    				try {
    					getAllProductWithOneImageBySellerID(request, response);
					} catch (ServletException | IOException | SQLException e) {
						e.printStackTrace();
					}
    				break;
    			
    			case "order":
    				List<Order> order = new ArrayList<Order>();
    				order = sellerDAO.getAllOrder();
					dispatcher = request.getRequestDispatcher("/views/seller/order/order.jsp");
					dispatcher.forward(request, response);
					break;
			
    			case "history":
    				List<Record> rc = new ArrayList<Record>();
    				rc = sellerDAO.getAllRecords();
		    		dispatcher = request.getRequestDispatcher("/views/seller/history/history.jsp");
					dispatcher.forward(request, response);
					break;
					
    			}
    		}
    	}else {
    		response.sendRedirect("RegisterController?page=sellerForm");
    	}
    }
    
    
    // Post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			switch(action) {
			
			
			
			}
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
	

}
