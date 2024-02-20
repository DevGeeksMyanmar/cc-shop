package Controllers;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Models.Customer;
import Models.Seller;
import DAO.CustomerDAO;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con = null;
	PreparedStatement stmt = null;
	Statement statement = null;
	ResultSet resultset = null;
	RequestDispatcher dispatcher = null;
	CustomerDAO customerDAO = null;

    public UserController() throws ClassNotFoundException, SQLException {
        super();
        customerDAO = new CustomerDAO();
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
    				dispatcher = request.getRequestDispatcher("/views/user/dashboard.jsp");
    				dispatcher.forward(request, response);
    				break;
    				
    			}
    		}
    	}else {
    		response.sendRedirect("views/seller/form.jsp");
    	}
    }
    
    // do post method
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	

	

}
